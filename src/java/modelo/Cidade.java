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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Alvondi
 */
@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
    
    @Id
    @Column(name = "codigo", nullable = false)
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotEmpty(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome deve ter no máximo {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message = "O Estado deve ser informado")
    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "codigo", nullable = false)
    private Estado estado;

    public Cidade() {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
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
        final Cidade other = (Cidade) obj;
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
