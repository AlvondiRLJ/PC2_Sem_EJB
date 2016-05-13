/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "objeto")
public class Objeto implements Serializable {
    
    @Id
    @Column(name = "codigo", nullable = false)
    @SequenceGenerator(name = "seq_objeto", sequenceName = "seq_objeto_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_objeto", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotEmpty(message = "A descrição deve ser informada")
    @Length(max = 50, message = "A descrição deve ter no máximo {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valor;
    @ManyToMany
    @JoinTable(name = "contrato_objeto", 
            joinColumns = 
                    @JoinColumn(name = "objeto", referencedColumnName = "codigo"),
                inverseJoinColumns = 
                    @JoinColumn(name = "contrato", referencedColumnName = "codigo"))
    List<Contrato> contratos = new ArrayList<>();
    
    public Objeto() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.codigo);
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
        final Objeto other = (Objeto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
