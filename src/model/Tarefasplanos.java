/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diogo
 */
@Entity
@Table(name = "tarefasplanos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarefasplanos.findAll", query = "SELECT t FROM Tarefasplanos t"),
    @NamedQuery(name = "Tarefasplanos.findByIdTarefaPlanos", query = "SELECT t FROM Tarefasplanos t WHERE t.idTarefaPlanos = :idTarefaPlanos"),
    @NamedQuery(name = "Tarefasplanos.findByDescricaoDoPlano", query = "SELECT t FROM Tarefasplanos t WHERE t.descricaoDoPlano = :descricaoDoPlano"),
    @NamedQuery(name = "Tarefasplanos.findByTipoPlano", query = "SELECT t FROM Tarefasplanos t WHERE t.tipoPlano = :tipoPlano"),
    @NamedQuery(name = "Tarefasplanos.findByRealizado", query = "SELECT t FROM Tarefasplanos t WHERE t.realizado = :realizado"),
    @NamedQuery(name = "Tarefasplanos.findByDataDeRealizacao", query = "SELECT t FROM Tarefasplanos t WHERE t.dataDeRealizacao = :dataDeRealizacao"),
    @NamedQuery(name = "Tarefasplanos.findByIdPlanoContigencia", query = "SELECT t FROM Tarefasplanos t WHERE t.idPlanoContigencia = :idPlanoContigencia"),
    @NamedQuery(name = "Tarefasplanos.findByIdPlanoMitigacao", query = "SELECT t FROM Tarefasplanos t WHERE t.idPlanoMitigacao = :idPlanoMitigacao")})
public class Tarefasplanos implements Serializable {
    @JoinColumn(name = "risco_idRisco", referencedColumnName = "idRisco")
    @ManyToOne(optional = false)
    private Risco riscoidRisco;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTarefaPlanos")
    private Integer idTarefaPlanos;
    @Basic(optional = false)
    @Column(name = "descricaoDoPlano")
    private String descricaoDoPlano;
    @Column(name = "tipoPlano")
    private String tipoPlano;
    @Column(name = "realizado")
    private Boolean realizado;
    @Column(name = "dataDeRealizacao")
    @Temporal(TemporalType.DATE)
    private Date dataDeRealizacao;
    @Column(name = "idPlanoContigencia")
    private Integer idPlanoContigencia;
    @Column(name = "idPlanoMitigacao")
    private Integer idPlanoMitigacao;

    public Tarefasplanos() {
    }

    public Tarefasplanos(Integer idTarefaPlanos) {
        this.idTarefaPlanos = idTarefaPlanos;
    }

    public Tarefasplanos(Integer idTarefaPlanos, String descricaoDoPlano) {
        this.idTarefaPlanos = idTarefaPlanos;
        this.descricaoDoPlano = descricaoDoPlano;
    }

    public Integer getIdTarefaPlanos() {
        return idTarefaPlanos;
    }

    public void setIdTarefaPlanos(Integer idTarefaPlanos) {
        this.idTarefaPlanos = idTarefaPlanos;
    }

    public String getDescricaoDoPlano() {
        return descricaoDoPlano;
    }

    public void setDescricaoDoPlano(String descricaoDoPlano) {
        this.descricaoDoPlano = descricaoDoPlano;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public Date getDataDeRealizacao() {
        return dataDeRealizacao;
    }

    public void setDataDeRealizacao(Date dataDeRealizacao) {
        this.dataDeRealizacao = dataDeRealizacao;
    }

    public Integer getIdPlanoContigencia() {
        return idPlanoContigencia;
    }

    public void setIdPlanoContigencia(Integer idPlanoContigencia) {
        this.idPlanoContigencia = idPlanoContigencia;
    }

    public Integer getIdPlanoMitigacao() {
        return idPlanoMitigacao;
    }

    public void setIdPlanoMitigacao(Integer idPlanoMitigacao) {
        this.idPlanoMitigacao = idPlanoMitigacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarefaPlanos != null ? idTarefaPlanos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefasplanos)) {
            return false;
        }
        Tarefasplanos other = (Tarefasplanos) object;
        if ((this.idTarefaPlanos == null && other.idTarefaPlanos != null) || (this.idTarefaPlanos != null && !this.idTarefaPlanos.equals(other.idTarefaPlanos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tarefasplanos[ idTarefaPlanos=" + idTarefaPlanos + " ]";
    }

    public Risco getRiscoidRisco() {
        return riscoidRisco;
    }

    public void setRiscoidRisco(Risco riscoidRisco) {
        this.riscoidRisco = riscoidRisco;
    }
    
}
