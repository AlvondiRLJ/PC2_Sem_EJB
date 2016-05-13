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
import modelo.Objeto;

/**
 *
 * @author Alvondi
 */
@FacesConverter(value = "converterObjeto")
public class ConverterObjeto implements Serializable, Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Objeto.class, Integer.parseInt(string));
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
        Objeto obj = (Objeto) o;
        return obj.getCodigo().toString();
    }
    
}
