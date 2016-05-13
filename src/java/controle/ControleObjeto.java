/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ObjetoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Objeto;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleObjeto")
@ViewScoped
public class ControleObjeto {
    
    private ObjetoDAO<Objeto> dao;
    private Objeto objeto;

    public ControleObjeto() {
        dao = new ObjetoDAO();
    }
    
    public String listar() {
        return "/privado/objeto/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Objeto();
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
            Util.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }
    
    public void remover(Integer id) {
        try {
//            objeto = dao.getObjectById(id);
//            dao.remove(objeto);
            dao.remove(id);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + e.getMessage());
        }
    }
    
    public ObjetoDAO getDao() {
        return dao;
    }

    public void setDao(ObjetoDAO dao) {
        this.dao = dao;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }
    
}
