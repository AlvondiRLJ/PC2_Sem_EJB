/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ContratoDAO;
import dao.EnvolvidoDAO;
import dao.ObjetoDAO;
import dao.PessoaDAO;
import dao.PessoaFisicaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Aditivo;
import modelo.Contrato;
import modelo.Envolvido;
import modelo.Objeto;
import modelo.Pessoa;
import modelo.PessoaFisica;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleContrato")
@ViewScoped
public class ControleContrato implements Serializable {
    
    private ContratoDAO<Contrato> dao;
    private Contrato objeto;
    private Aditivo aditivo;
    private Boolean novoAditivo;
    private Envolvido envolvido;
    private Boolean novoEnvolvido;
    private Objeto obj;
    private ObjetoDAO<Objeto> daoObjeto;
    private EnvolvidoDAO<Envolvido> daoEnvolvido;
    private PessoaFisicaDAO<PessoaFisica> daoPessoaFisica;
    private PessoaDAO<Pessoa> daoPessoa;

    public ControleContrato() {
        dao = new ContratoDAO<>();
        daoObjeto = new ObjetoDAO<>();
        daoEnvolvido = new EnvolvidoDAO<>();
        daoPessoaFisica = new PessoaFisicaDAO<>();
        daoPessoa = new PessoaDAO<>();
    }
    
    public String listar() {
        return "/privado/contrato/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Contrato();
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
//            dao.remove(objeto);     estava dando errado por causa do ID para Contrato
            dao.remove(id);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }
    
    public void novoAditivo(){
        aditivo = new Aditivo();
        novoAditivo = true;
    }
    
    public void alterarAditivo(int index){
        aditivo = objeto.getAditivos().get(index);
        novoAditivo = false;
    }
    
    public void salvarAditivo(){
        if (novoAditivo){
            objeto.adicionarAditivo(aditivo);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerAditivo(int index){
        objeto.removerAditivo(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void adicionarObjeto(){
        objeto.adicionarObjetos(obj);
        Util.mensagemInformacao("Objeto adicionado com sucesso");
    }
    
    public void removerObjeto(Objeto obj){
        objeto.removerObjetos(obj);
        Util.mensagemInformacao("Objeto removido com sucesso");
    }
    
    public ContratoDAO getDao() {
        return dao;
    }

    public void setDao(ContratoDAO dao) {
        this.dao = dao;
    }

    public Contrato getObjeto() {
        return objeto;
    }

    public void setObjeto(Contrato objeto) {
        this.objeto = objeto;
    }

    public Aditivo getAditivo() {
        return aditivo;
    }

    public void setAditivo(Aditivo aditivo) {
        this.aditivo = aditivo;
    }
    
    public Boolean getNovoAditivo() {
        return novoAditivo;
    }

    public void setNovoAditivo(Boolean novoAditivo) {
        this.novoAditivo = novoAditivo;
    }

    public Objeto getObj() {
        return obj;
    }

    public void setObj(Objeto obj) {
        this.obj = obj;
    }

    public ObjetoDAO<Objeto> getDaoObjeto() {
        return daoObjeto;
    }

    public void setDaoObjeto(ObjetoDAO<Objeto> daoObjeto) {
        this.daoObjeto = daoObjeto;
    }
    
    public Envolvido getEnvolvido() {
        return envolvido;
    }

    public void setEnvolvido(Envolvido envolvido) {
        this.envolvido = envolvido;
    }

    public Boolean getNovoEnvolvido() {
        return novoEnvolvido;
    }

    public void setNovoEnvolvido(Boolean novoEnvolvido) {
        this.novoEnvolvido = novoEnvolvido;
    }
    
    public void novoEnvolvido(){
        envolvido = new Envolvido();
        novoEnvolvido = true;
    }
    
    public void alterarEnvolvido(int index){
        envolvido = objeto.getEnvolvidos().get(index);
        novoEnvolvido = false;
    }
    
    public void salvarEnvolvido(){
        if (novoEnvolvido){
            objeto.adicionarEnvolvido(envolvido);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerEnvolvido(int index){
        objeto.removerEnvolvido(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public PessoaFisicaDAO<PessoaFisica> getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO<PessoaFisica> daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
    
}


