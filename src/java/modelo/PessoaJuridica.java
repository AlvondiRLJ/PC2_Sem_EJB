/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author Alvondi
 */
@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable {
    
    @NotBlank(message = "O CNPJ deve ser informado")
    @Length(max = 14, message = "O CNPJ não deve possuir mais de {max} digitos")
    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;
    @NotBlank(message = "A IE deve ser informado")
    @Length(max = 20, message = "A IE não deve possuir mais de {max} digitos")
    @Column(name = "ie", length = 20, nullable = false)
    private String ie;
    @NotBlank(message = "O Nome Fantasia deve ser informado")
    @Length(max = 50, message="O Nome Fantasia não deve possuir mais de {max} caracteres")
    @Column(name = "nomefantasia",length = 50,nullable = false)
    private String nomeFantasia;
    
    public PessoaJuridica() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    
}
