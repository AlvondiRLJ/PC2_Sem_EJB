/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import modelo.Envolvido;

/**
 *
 * @author Alvondi
 */
public class EnvolvidoDAO<T> extends GenericDAO<Envolvido> implements Serializable {
    
    public EnvolvidoDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(Envolvido.class);
        // denifir a lista de ordenações
        super.getListOrder().add(new Order("tipo", "Tipo", "like"));
        super.getListOrder().add(new Order("descricao", "Descricao", "like"));
        // definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        // inicializar o filtro
        super.setFilter("");
        // inicializar o conversor da ordem
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
}
