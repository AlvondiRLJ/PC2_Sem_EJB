/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import modelo.PessoaJuridica;

/**
 *
 * @author Alvondi
 */
public class PessoaJuridicaDAO<T> extends GenericDAO<PessoaJuridica> implements Serializable{
    
    public PessoaJuridicaDAO(){
        super();
        // definir a classe persistente
        super.setPersistentClass(PessoaJuridica.class);
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