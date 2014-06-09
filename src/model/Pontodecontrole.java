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
@Table(name = "pontodecontrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pontodecontrole.findPontodecontroleByIdProjeto", query = "SELECT p FROM Pontodecontrole p WHERE p.idProjeto = :idProjeto"),
    @NamedQuery(name = "Pontodecontrole.findAll", query = "SELECT p FROM Pontodecontrole p"),
    @NamedQuery(name = "Pontodecontrole.findByIdPontoDeControle", query = "SELECT p FROM Pontodecontrole p WHERE p.idPontoDeControle = :idPontoDeControle"),
    @NamedQuery(name = "Pontodecontrole.findByNomePontoDeControle", query = "SELECT p FROM Pontodecontrole p WHERE p.nomePontoDeControle = :nomePontoDeControle"),
    @NamedQuery(name = "Pontodecontrole.findByDataPontoControle", query = "SELECT p FROM Pontodecontrole p WHERE p.dataPontoControle = :dataPontoControle"),
    @NamedQuery(name = "Pontodecontrole.findByDescricaoPontoControle", query = "SELECT p FROM Pontodecontrole p WHERE p.descricaoPontoControle = :descricaoPontoControle")})
public class Pontodecontrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPontoDeControle")
    private Integer idPontoDeControle;
    @Basic(optional = false)
    @Column(name = "nomePontoDeControle")
    private String nomePontoDeControle;
    @Basic(optional = false)
    @Column(name = "dataPontoControle")
    @Temporal(TemporalType.DATE)
    private Date dataPontoControle;
    @Column(name = "descricaoPontoControle")
    private String descricaoPontoControle;
    @JoinColumn(name = "idProjeto", referencedColumnName = "idProjeto")
    @ManyToOne(optional = false)
    private Projeto idProjeto;

    public Pontodecontrole() {
    }

    public Pontodecontrole(Integer idPontoDeControle) {
        this.idPontoDeControle = idPontoDeControle;
    }

    public Pontodecontrole(Integer idPontoDeControle, String nomePontoDeControle, Date dataPontoControle) {
        this.idPontoDeControle = idPontoDeControle;
        this.nomePontoDeControle = nomePontoDeControle;
        this.dataPontoControle = dataPontoControle;
    }

    public Integer getIdPontoDeControle() {
        return idPontoDeControle;
    }

    public void setIdPontoDeControle(Integer idPontoDeControle) {
        this.idPontoDeControle = idPontoDeControle;
    }

    public String getNomePontoDeControle() {
        return nomePontoDeControle;
    }

    public void setNomePontoDeControle(String nomePontoDeControle) {
        this.nomePontoDeControle = nomePontoDeControle;
    }

    public Date getDataPontoControle() {
        return dataPontoControle;
    }

    public void setDataPontoControle(Date dataPontoControle) {
        this.dataPontoControle = dataPontoControle;
    }

    public String getDescricaoPontoControle() {
        return descricaoPontoControle;
    }

    public void setDescricaoPontoControle(String descricaoPontoControle) {
        this.descricaoPontoControle = descricaoPontoControle;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPontoDeControle != null ? idPontoDeControle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pontodecontrole)) {
            return false;
        }
        Pontodecontrole other = (Pontodecontrole) object;
        if ((this.idPontoDeControle == null && other.idPontoDeControle != null) || (this.idPontoDeControle != null && !this.idPontoDeControle.equals(other.idPontoDeControle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pontodecontrole[ idPontoDeControle=" + idPontoDeControle + " ]";
    }
    
}
