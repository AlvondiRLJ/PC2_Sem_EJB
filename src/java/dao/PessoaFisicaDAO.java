/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import modelo.PessoaFisica;

/**
 *
 * @author Alvondi
 */
public class PessoaFisicaDAO<T> extends GenericDAO<PessoaFisica> implements Serializable {
    
    public PessoaFisicaDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(PessoaFisica.class);
        // denifir a lista de ordenações
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        // definir a ordem atual
        super.setCurrentOrder(super.getListOrder().get(1));
        // inicializar o filtro
        super.setFilter("");
        // inicializar o conversor da ordem
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
    
}
