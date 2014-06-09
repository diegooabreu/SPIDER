/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "politicaorganizacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Politicaorganizacional.findAll", query = "SELECT p FROM Politicaorganizacional p"),
    @NamedQuery(name = "Politicaorganizacional.findByIdPoliticaOrganizacional", query = "SELECT p FROM Politicaorganizacional p WHERE p.idPoliticaOrganizacional = :idPoliticaOrganizacional")})
public class Politicaorganizacional implements Serializable {
    @Column(name = "nomeArquivoPolitica")
    private String nomeArquivoPolitica;
    @Column(name = "caminhoArquivo")
    private String caminhoArquivo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPoliticaOrganizacional")
    private Integer idPoliticaOrganizacional;
    @Lob
    @Column(name = "politicaOrganizacional")
    private String politicaOrganizacional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPoliticaOrganizacional")
    private List<Organizacao> organizacaoList;

    public Politicaorganizacional() {
    }

    public Politicaorganizacional(Integer idPoliticaOrganizacional) {
        this.idPoliticaOrganizacional = idPoliticaOrganizacional;
    }

    public Integer getIdPoliticaOrganizacional() {
        return idPoliticaOrganizacional;
    }

    public void setIdPoliticaOrganizacional(Integer idPoliticaOrganizacional) {
        this.idPoliticaOrganizacional = idPoliticaOrganizacional;
    }

    public String getPoliticaOrganizacional() {
        return politicaOrganizacional;
    }

    public void setPoliticaOrganizacional(String politicaOrganizacional) {
        this.politicaOrganizacional = politicaOrganizacional;
    }

    @XmlTransient
    public List<Organizacao> getOrganizacaoList() {
        return organizacaoList;
    }

    public void setOrganizacaoList(List<Organizacao> organizacaoList) {
        this.organizacaoList = organizacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoliticaOrganizacional != null ? idPoliticaOrganizacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Politicaorganizacional)) {
            return false;
        }
        Politicaorganizacional other = (Politicaorganizacional) object;
        if ((this.idPoliticaOrganizacional == null && other.idPoliticaOrganizacional != null) || (this.idPoliticaOrganizacional != null && !this.idPoliticaOrganizacional.equals(other.idPoliticaOrganizacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Politicaorganizacional[ idPoliticaOrganizacional=" + idPoliticaOrganizacional + " ]";
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getNomeArquivoPolitica() {
        return nomeArquivoPolitica;
    }

    public void setNomeArquivoPolitica(String nomeArquivoPolitica) {
        this.nomeArquivoPolitica = nomeArquivoPolitica;
    }
    
}
