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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Alvondi
 */
@Entity
@Table(name = "envolvido")
public class Envolvido implements Serializable {
    
    @Id
    @Column(name = "codigo", nullable = false)
    @SequenceGenerator(name = "seq_envolvido", sequenceName = "seq_envolvido_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_envolvido", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotEmpty(message = "O tipo deve ser informado")
    @Length(max = 50, message = "O tipo deve ter no máximo {max} caracteres")
    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;
    @NotEmpty(message = "A descrição deve ser informada")
    @Length(max = 50, message = "A descrição deve ter no máximo {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "contrato", referencedColumnName = "codigo", nullable = false)
    private Contrato contrato;
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "codigo", nullable = false)
    private Pessoa pessoa;
    
    public Envolvido() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.codigo);
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
        final Envolvido other = (Envolvido) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
