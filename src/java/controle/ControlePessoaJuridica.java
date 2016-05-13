/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CidadeDAO;
import dao.PessoaJuridicaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.PessoaJuridica;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controlePessoaJuridica")
@ViewScoped
public class ControlePessoaJuridica implements Serializable {
    
    private PessoaJuridicaDAO<PessoaJuridica> dao;
    private PessoaJuridica objeto;
    private CidadeDAO daoCidade;

    public ControlePessoaJuridica() {
        dao = new PessoaJuridicaDAO();
        daoCidade = new CidadeDAO();
    }
    
    public String listar() {
        return "/privado/pessoajuridica/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new PessoaJuridica();
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
    
    public PessoaJuridicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaJuridicaDAO dao) {
        this.dao = dao;
    }

    public PessoaJuridica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaJuridica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }
    
}