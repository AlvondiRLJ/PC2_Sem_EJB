/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import modelo.Objeto;

/**
 *
 * @author Alvondi
 */
public class ObjetoDAO<T> extends GenericDAO<Objeto> implements Serializable {
    
    public ObjetoDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(Objeto.class);
        // denifir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("descricao", "Descricao", "like"));
        // definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        // inicializar o filtro
        super.setFilter("");
        // inicializar o conversor da ordem
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
}
