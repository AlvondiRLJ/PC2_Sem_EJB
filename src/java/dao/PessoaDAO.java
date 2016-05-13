/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Pessoa;

/**
 *
 * @author Alvondi
 */
public class PessoaDAO<T> extends GenericDAO<Pessoa> implements Serializable {
    
    public PessoaDAO() {
//        super();
        super.setPersistentClass(Pessoa.class);
        super.getListOrder().add(new Order("codigo", "Codigo", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
//        super.getListOrder().add(new Order("apelido", "Apelido", "like"));
//        super.getListOrder().add(new Order("cidade.nome", "Cidade", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
    public boolean login(String usuario, String senha) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("from Pessoa where upper(email) = :usuario and upper(senhaacesso) = :senha");
            query.setParameter("usuario", usuario.toUpperCase());
            query.setParameter("senha", senha.toUpperCase());
            if (!query.getResultList().isEmpty()){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public Pessoa localizaPorNomeUsuario(String usuario){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("from Pessoa where upper(email) = :usuario");
            query.setParameter("usuario", usuario.toUpperCase());
            Pessoa obj = (Pessoa) query.getSingleResult();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }
}
