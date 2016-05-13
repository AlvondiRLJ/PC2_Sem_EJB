/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PessoaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pessoa;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private PessoaDAO<Pessoa> dao;
    private Pessoa usuarioLogado;
    private String usuario;
    private String senha; 
    
    public ControleLogin() {
        dao = new PessoaDAO<Pessoa>();
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        if (dao.login(usuario, senha)){
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            try {
                dao.merge(usuarioLogado);
            } catch (Exception e) {
                Util.mensagemErro("Erro ao persistir acesso do usuário: "+e.getMessage());
            }
            Util.mensagemInformacao("Login efetuado com sucesso");
            return "/index";
        } else {
            Util.mensagemErro("Usuario ou senha inválidos");
            return "/login";
        }
    }
        
    public String efetuarLogOut(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso");
        return "/index";
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Pessoa usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
