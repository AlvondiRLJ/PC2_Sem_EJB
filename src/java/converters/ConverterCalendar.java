/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alvondi
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Converter, Serializable{
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(string));
            return c;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        Calendar obj = (Calendar) o;
        return sdf.format(obj.getTime());
    }
    
}
