/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.EstadoDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Estado;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleEstado")
@SessionScoped
public class ControleEstado implements Serializable {
    
    private EstadoDAO<Estado> dao;
    private Estado objeto;

    public ControleEstado() {
        dao = new EstadoDAO();
    }
    
    public String listar() {
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public String novo() {
        objeto = new Estado();
        return "formulario";
    }
    
    public String salvar() {
        try {
            if (objeto.getCodigo()== null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            return "listar";
        } catch (Exception e) {
            e.printStackTrace();
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
            return "formulario";
        }
    }
    
    public String cancelar() {
        objeto = null;
        return "listar";
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
    
    public EstadoDAO getDao() {
        return dao;
    }

    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }
    
}