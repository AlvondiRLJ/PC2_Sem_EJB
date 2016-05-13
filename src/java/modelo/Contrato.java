/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Alvondi
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "contrato")
public class Contrato implements Serializable {
    
    @Id
    @Column(name = "codigo", nullable = false)
    @SequenceGenerator(name = "seq_contrato", sequenceName = "seq_contrato_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_contrato", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotEmpty(message = "O número do contrato deve ser informado")
    @Length(max = 10, message = "O numero do contrato não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;
    @NotNull(message = "A data da assinatura deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataassinatura", nullable = false)
    private Calendar dataAssinatura;
    @NotNull(message = "A data de início deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "datainicio", nullable = false)
    private Calendar dataInicio;
    @NotNull(message = "A data de fim deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "datafim", nullable = false)
    private Calendar dataFim;
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valor;
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "codigo asc")
    private List<Aditivo> aditivos = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "contrato_objeto", 
            joinColumns = 
                    @JoinColumn(name = "contrato", referencedColumnName = "codigo"),
                inverseJoinColumns = 
                    @JoinColumn(name = "objeto", referencedColumnName = "codigo"))
    private List<Objeto> objetos = new ArrayList<>();
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "codigo asc")
    private List<Envolvido> envolvidos = new ArrayList<>();

    public Contrato() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Calendar getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(Calendar dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codigo);
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
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero;
    }

    public List<Aditivo> getAditivos() {
        return aditivos;
    }

    public void setAditivos(List<Aditivo> aditivos) {
        this.aditivos = aditivos;
    }
    
    public void adicionarAditivo(Aditivo obj){
        obj.setContrato(this);
        this.aditivos.add(obj);
    }
    
    public void removerAditivo(int index){
        this.aditivos.remove(index);
    }
    
    public void removerTodosAditivos(){
        this.aditivos.clear();
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }
    
    public void adicionarObjetos(Objeto obj){
        if (!this.getObjetos().contains(obj)){
            this.getObjetos().add(obj);
        }
    }
    
    public void removerObjetos(Objeto obj){
        this.getObjetos().remove(obj);
    }

    public List<Envolvido> getEnvolvidos() {
        return envolvidos;
    }

    public void setEnvolvidos(List<Envolvido> envolvidos) {
        this.envolvidos = envolvidos;
    }
    
    public void adicionarEnvolvido(Envolvido obj){
        obj.setContrato(this);
        this.envolvidos.add(obj);
    }
    
    public void removerEnvolvido(int index){
        this.envolvidos.remove(index);
    }
    
    public void removerTodosEnvolvidos(){
        this.envolvidos.clear();
    }
    
}
