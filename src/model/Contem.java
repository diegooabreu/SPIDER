/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "contem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contem.findAll", query = "SELECT c FROM Contem c"),
    @NamedQuery(name = "Contem.findByIdProjeto", query = "SELECT c FROM Contem c WHERE c.contemPK.idProjeto = :idProjeto"),
    @NamedQuery(name = "Contem.findByIdCategoriaDeRisco", query = "SELECT c FROM Contem c WHERE c.contemPK.idCategoriaDeRisco = :idCategoriaDeRisco")})
public class Contem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContemPK contemPK;
    @JoinColumn(name = "idCategoriaDeRisco", referencedColumnName = "idCategoriaDeRisco", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoriaderisco categoriaderisco;
    @JoinColumn(name = "idProjeto", referencedColumnName = "idProjeto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contem")
    private List<Risco> riscoList;

    public Contem() {
    }

    public Contem(ContemPK contemPK) {
        this.contemPK = contemPK;
    }

    public Contem(int idProjeto, int idCategoriaDeRisco) {
        this.contemPK = new ContemPK(idProjeto, idCategoriaDeRisco);
    }

    public ContemPK getContemPK() {
        return contemPK;
    }

    public void setContemPK(ContemPK contemPK) {
        this.contemPK = contemPK;
    }

    public Categoriaderisco getCategoriaderisco() {
        return categoriaderisco;
    }

    public void setCategoriaderisco(Categoriaderisco categoriaderisco) {
        this.categoriaderisco = categoriaderisco;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @XmlTransient
    public List<Risco> getRiscoList() {
        return riscoList;
    }

    public void setRiscoList(List<Risco> riscoList) {
        this.riscoList = riscoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contemPK != null ? contemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contem)) {
            return false;
        }
        Contem other = (Contem) object;
        if ((this.contemPK == null && other.contemPK != null) || (this.contemPK != null && !this.contemPK.equals(other.contemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Contem[ contemPK=" + contemPK + " ]";
    }
    
}
