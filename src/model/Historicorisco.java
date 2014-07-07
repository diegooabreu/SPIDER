/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "historicorisco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicorisco.findAll", query = "SELECT h FROM Historicorisco h"),
    @NamedQuery(name = "Historicorisco.findByIdRisco", query = "SELECT h FROM Historicorisco h WHERE h.idRisco.idRisco = :idRisco"),
    @NamedQuery(name = "Historicorisco.findByIdProjetoAndStatusRisco", query = "SELECT h FROM Historicorisco h WHERE h.idRisco.contem.projeto.idProjeto = :idProjeto AND h.idRisco.statusRisco = :statusRisco"),
    @NamedQuery(name = "Historicorisco.findByIdHistoricoRisco", query = "SELECT h FROM Historicorisco h WHERE h.idHistoricoRisco = :idHistoricoRisco"),
    @NamedQuery(name = "Historicorisco.findByDataOcorrencia", query = "SELECT h FROM Historicorisco h WHERE h.dataOcorrencia = :dataOcorrencia")})
public class Historicorisco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistoricoRisco")
    private Integer idHistoricoRisco;
    @Basic(optional = false)
    @Column(name = "dataOcorrencia")
    @Temporal(TemporalType.DATE)
    private Date dataOcorrencia;
    @JoinTable(name = "historicorisco2", joinColumns = {
        @JoinColumn(name = "idHistoricoRisco", referencedColumnName = "idHistoricoRisco")}, inverseJoinColumns = {
        @JoinColumn(name = "idSubcondicao1", referencedColumnName = "idSubcondicao1"),
        @JoinColumn(name = "idSubcondicao2", referencedColumnName = "idSubcondicao2"),
        @JoinColumn(name = "relacao", referencedColumnName = "relacao")})
    @ManyToMany
    private List<Relacaosubcondicao> relacaosubcondicaoList;
    @JoinTable(name = "ocorrenciasubcondicao", joinColumns = {
        @JoinColumn(name = "idHistoricoRisco", referencedColumnName = "idHistoricoRisco")}, inverseJoinColumns = {
        @JoinColumn(name = "idSubcondicao", referencedColumnName = "idSubcondicao")})
    @ManyToMany
    private List<Subcondicao> subcondicaoList;
    @JoinColumn(name = "idRisco", referencedColumnName = "idRisco")
    @ManyToOne(optional = false)
    private Risco idRisco;

    public Historicorisco() {
    }

    public Historicorisco(Integer idHistoricoRisco) {
        this.idHistoricoRisco = idHistoricoRisco;
    }

    public Historicorisco(Integer idHistoricoRisco, Date dataOcorrencia) {
        this.idHistoricoRisco = idHistoricoRisco;
        this.dataOcorrencia = dataOcorrencia;
    }

    public Integer getIdHistoricoRisco() {
        return idHistoricoRisco;
    }

    public void setIdHistoricoRisco(Integer idHistoricoRisco) {
        this.idHistoricoRisco = idHistoricoRisco;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    @XmlTransient
    public List<Relacaosubcondicao> getRelacaosubcondicaoList() {
        return relacaosubcondicaoList;
    }

    public void setRelacaosubcondicaoList(List<Relacaosubcondicao> relacaosubcondicaoList) {
        this.relacaosubcondicaoList = relacaosubcondicaoList;
    }

    @XmlTransient
    public List<Subcondicao> getSubcondicaoList() {
        return subcondicaoList;
    }

    public void setSubcondicaoList(List<Subcondicao> subcondicaoList) {
        this.subcondicaoList = subcondicaoList;
    }

    public Risco getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Risco idRisco) {
        this.idRisco = idRisco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoRisco != null ? idHistoricoRisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicorisco)) {
            return false;
        }
        Historicorisco other = (Historicorisco) object;
        if ((this.idHistoricoRisco == null && other.idHistoricoRisco != null) || (this.idHistoricoRisco != null && !this.idHistoricoRisco.equals(other.idHistoricoRisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Historicorisco[ idHistoricoRisco=" + idHistoricoRisco + " ]";
    }
    
}
