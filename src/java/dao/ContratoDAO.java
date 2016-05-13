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
import modelo.Contrato;

/**
 *
 * @author Alvondi
 */
public class ContratoDAO<T> extends GenericDAO<Contrato> implements Serializable{
    
    public ContratoDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(Contrato.class);
        // denifir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("numero", "Numero", "like"));
        // definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        // inicializar o filtro
        super.setFilter("");
        // inicializar o conversor da ordem
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
    @Override
    public Contrato getObjectById(Integer id) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            Contrato obj = (Contrato) em.find(Contrato.class, id);
            obj.getAditivos().size();
            obj.getObjetos().size();
            obj.getEnvolvidos().size();
            return obj;
        } catch (Exception e) {
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        
        
        // inicializar as coleções pela chamada do método size()        
        
    }
    
}
