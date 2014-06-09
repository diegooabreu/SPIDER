/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mariano
 */
@Embeddable
public class ContemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idProjeto")
    private int idProjeto;
    @Basic(optional = false)
    @Column(name = "idCategoriaDeRisco")
    private int idCategoriaDeRisco;

    public ContemPK() {
    }

    public ContemPK(int idProjeto, int idCategoriaDeRisco) {
        this.idProjeto = idProjeto;
        this.idCategoriaDeRisco = idCategoriaDeRisco;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdCategoriaDeRisco() {
        return idCategoriaDeRisco;
    }

    public void setIdCategoriaDeRisco(int idCategoriaDeRisco) {
        this.idCategoriaDeRisco = idCategoriaDeRisco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProjeto;
        hash += (int) idCategoriaDeRisco;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContemPK)) {
            return false;
        }
        ContemPK other = (ContemPK) object;
        if (this.idProjeto != other.idProjeto) {
            return false;
        }
        if (this.idCategoriaDeRisco != other.idCategoriaDeRisco) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ContemPK[ idProjeto=" + idProjeto + ", idCategoriaDeRisco=" + idCategoriaDeRisco + " ]";
    }
    
}
