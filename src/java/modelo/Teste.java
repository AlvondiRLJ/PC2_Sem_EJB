/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvondi
 */
public class Teste {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            Cidade cidade = new Cidade();
            cidade.setNome("Passo Fundo");
            cidade.setEstado(em.find(Estado.class, 1));
            
            em.persist(cidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            
        } finally {
            em.close();
            emf.close();
        }
        
    }
}
