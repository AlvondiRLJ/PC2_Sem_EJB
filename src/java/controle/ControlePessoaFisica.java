/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CidadeDAO;
import dao.PessoaFisicaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.PessoaFisica;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable{
    
    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO daoCidade;

    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO();
        daoCidade = new CidadeDAO();
    }
    
    public String listar() {
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new PessoaFisica();
    }
    
    public void salvar() {
        try {
            if (objeto.getCodigo()== null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
        }
    }
    
    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());
        }
    }
    
    public void excluir(Integer id){
        try {
//            objeto = dao.getObjectById(id);
//            dao.remove(objeto);
            dao.remove(id);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }
    
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }
    
}
