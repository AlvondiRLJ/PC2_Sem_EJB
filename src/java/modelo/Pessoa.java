/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Alvondi
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_codigo",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message="O nome não deve possuir mais de {max} caracteres")
    @Column(name = "nome",length = 50,nullable = false)
    private String nome;
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email deve ser informado")
    @Length(max = 50, message="O email não deve possuir mais de {max} caracteres")
    @Column(name = "email",length = 50,nullable = false, unique = true) // unique para email por causa do login
    private String email;
    @NotBlank(message = "O endereço deve ser informado")
    @Length(max = 50, message="O endereço não deve possuir mais de {max} caracteres")
    @Column(name = "endereco",length = 50,nullable = false)
    private String endereco;
    @NotBlank(message = "O complemento deve ser informado")
    @Length(max = 50, message="O complemento não deve possuir mais de {max} caracteres")
    @Column(name = "complemento",length = 50,nullable = false)
    private String complemento;
    @NotBlank(message = "O bairro deve ser informado")
    @Length(max = 30, message="O bairro não deve possuir mais de {max} caracteres")
    @Column(name = "bairro",length = 30,nullable = false)
    private String bairro;
    @NotBlank(message = "O cep deve ser informado")
    @Length(max = 9, message="O cep não deve possuir mais de {max} caracteres")
    @Column(name = "cep",length = 9,nullable = false)
    private String cep;
    @NotNull(message = "O campo administrador deve ser informado")
    @Column(name = "administrador", nullable = false)
    private Boolean administrador;
    @NotEmpty(message = "A senha deve ser informada")
    @Length(max = 20, message = "A senha não pode ter mais de {max} caracteres")
    @Column(name = "senhaacesso", length = 20, nullable = false)
    private String senhaAcesso;
    @NotNull(message = "A cidade deve ser informada")
    @ManyToOne
    @JoinColumn(name = "cidade",referencedColumnName = "codigo",nullable = false)
    private Cidade cidade;
    
    public Pessoa() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return nome;
    }
    
}
