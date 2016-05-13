/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Pessoa;

/**
 *
 * @author Alvondi
 */
@FacesConverter(value = "converterPessoa")
public class ConverterPessoa implements Serializable, Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pessoa.class, Integer.parseInt(string));
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        if (o == null){
            return null;
        }
        Pessoa obj = (Pessoa) o;
        return obj.getCodigo().toString();
    }
    
}