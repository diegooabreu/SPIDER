/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "historicoalteracao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicoalteracao.findAll", query = "SELECT h FROM Historicoalteracao h"),
    @NamedQuery(name = "Historicoalteracao.findByIdHistoricoAlteracao", query = "SELECT h FROM Historicoalteracao h WHERE h.idHistoricoAlteracao = :idHistoricoAlteracao"),
    @NamedQuery(name = "Historicoalteracao.findByDataAlteracao", query = "SELECT h FROM Historicoalteracao h WHERE h.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "Historicoalteracao.findByIdRisco", query = "SELECT h FROM Historicoalteracao h WHERE h.idRisco = :idRisco")})
public class Historicoalteracao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistoricoAlteracao")
    private Integer idHistoricoAlteracao;
    @Lob
    @Column(name = "descricaoAlteracao")
    private String descricaoAlteracao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;
    @JoinColumn(name = "idRisco", referencedColumnName = "idRisco")
    @ManyToOne(optional = false)
    private Risco idRisco;

    public Historicoalteracao() {
    }

    public Historicoalteracao(Integer idHistoricoAlteracao) {
        this.idHistoricoAlteracao = idHistoricoAlteracao;
    }

    public Integer getIdHistoricoAlteracao() {
        return idHistoricoAlteracao;
    }

    public void setIdHistoricoAlteracao(Integer idHistoricoAlteracao) {
        this.idHistoricoAlteracao = idHistoricoAlteracao;
    }

    public String getDescricaoAlteracao() {
        return descricaoAlteracao;
    }

    public void setDescricaoAlteracao(String descricaoAlteracao) {
        this.descricaoAlteracao = descricaoAlteracao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
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
        hash += (idHistoricoAlteracao != null ? idHistoricoAlteracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicoalteracao)) {
            return false;
        }
        Historicoalteracao other = (Historicoalteracao) object;
        if ((this.idHistoricoAlteracao == null && other.idHistoricoAlteracao != null) || (this.idHistoricoAlteracao != null && !this.idHistoricoAlteracao.equals(other.idHistoricoAlteracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Historicoalteracao[ idHistoricoAlteracao=" + idHistoricoAlteracao + " ]";
    }
    
}
