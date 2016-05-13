/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Estado;
import java.io.Serializable;


/**
 *
 * @author Alvondi
 */
public class EstadoDAO<T> extends GenericDAO<Estado> implements Serializable {
    
    public EstadoDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(Estado.class);
        // definir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        // definir a ordenação padrão
        super.setCurrentOrder(super.getListOrder().get(1));
        // inicializo filtro
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
        
    }
    
}
