/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alvondi
 */
@Entity
@Table(name = "aditivo")
public class Aditivo implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_aditivo", sequenceName = "seq_aditivo_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_aditivo",strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotNull(message = "O período deve ser informado")
    @Temporal(TemporalType.DATE)
    @Column(name = "periodo", nullable = false)
    private Calendar periodo;
    @NotNull(message = "O reajuste deve ser informado")
    @Column(name = "reajustevalor", nullable = false, columnDefinition = "decimal(12,2)")
    private Double reajustevalor;
    @ManyToOne
    @JoinColumn(name = "contrato_id", referencedColumnName = "codigo", nullable = false)
    private Contrato contrato;

    public Aditivo() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Calendar getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Calendar periodo) {
        this.periodo = periodo;
    }

    public Double getReajustevalor() {
        return reajustevalor;
    }

    public void setReajustevalor(Double reajustevalor) {
        this.reajustevalor = reajustevalor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Aditivo other = (Aditivo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "codigo";
    }
    
    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
}
