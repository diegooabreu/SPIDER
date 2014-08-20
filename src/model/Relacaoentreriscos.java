/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ITAU
 */
@Entity
@Table(name = "relacaoentreriscos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relacaoentreriscos.findAll", query = "SELECT r FROM Relacaoentreriscos r"),
    @NamedQuery(name = "Relacaoentreriscos.findByIdRelacaoEntreRiscoscol", query = "SELECT r FROM Relacaoentreriscos r WHERE r.idRelacaoEntreRiscoscol = :idRelacaoEntreRiscoscol"),
    @NamedQuery(name = "Relacaoentreriscos.findByIdRiscoInfluenciador", query = "SELECT r FROM Relacaoentreriscos r WHERE r.idRiscoInfluenciador = :idRiscoInfluenciador"),
    @NamedQuery(name = "Relacaoentreriscos.findByIdRiscoInfluenciado", query = "SELECT r FROM Relacaoentreriscos r WHERE r.idRiscoInfluenciado = :idRiscoInfluenciado")})
public class Relacaoentreriscos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRelacaoEntreRiscoscol")
    private Integer idRelacaoEntreRiscoscol;
    @Basic(optional = false)
    @Column(name = "idRiscoInfluenciador")
    private int idRiscoInfluenciador;
    @Basic(optional = false)
    @Column(name = "idRiscoInfluenciado")
    private int idRiscoInfluenciado;

    public Relacaoentreriscos() {
    }

    public Relacaoentreriscos(Integer idRelacaoEntreRiscoscol) {
        this.idRelacaoEntreRiscoscol = idRelacaoEntreRiscoscol;
    }

    public Relacaoentreriscos(Integer idRelacaoEntreRiscoscol, int idRiscoInfluenciador, int idRiscoInfluenciado) {
        this.idRelacaoEntreRiscoscol = idRelacaoEntreRiscoscol;
        this.idRiscoInfluenciador = idRiscoInfluenciador;
        this.idRiscoInfluenciado = idRiscoInfluenciado;
    }

    public Integer getIdRelacaoEntreRiscoscol() {
        return idRelacaoEntreRiscoscol;
    }

    public void setIdRelacaoEntreRiscoscol(Integer idRelacaoEntreRiscoscol) {
        this.idRelacaoEntreRiscoscol = idRelacaoEntreRiscoscol;
    }

    public int getIdRiscoInfluenciador() {
        return idRiscoInfluenciador;
    }

    public void setIdRiscoInfluenciador(int idRiscoInfluenciador) {
        this.idRiscoInfluenciador = idRiscoInfluenciador;
    }

    public int getIdRiscoInfluenciado() {
        return idRiscoInfluenciado;
    }

    public void setIdRiscoInfluenciado(int idRiscoInfluenciado) {
        this.idRiscoInfluenciado = idRiscoInfluenciado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacaoEntreRiscoscol != null ? idRelacaoEntreRiscoscol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relacaoentreriscos)) {
            return false;
        }
        Relacaoentreriscos other = (Relacaoentreriscos) object;
        if ((this.idRelacaoEntreRiscoscol == null && other.idRelacaoEntreRiscoscol != null) || (this.idRelacaoEntreRiscoscol != null && !this.idRelacaoEntreRiscoscol.equals(other.idRelacaoEntreRiscoscol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "view.Relacaoentreriscos[ idRelacaoEntreRiscoscol=" + idRelacaoEntreRiscoscol + " ]";
    }
    
}
