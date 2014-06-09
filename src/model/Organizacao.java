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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "organizacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizacao.findAll", query = "SELECT o FROM Organizacao o"),
    @NamedQuery(name = "Organizacao.findByIdOrganizacao", query = "SELECT o FROM Organizacao o WHERE o.idOrganizacao = :idOrganizacao"),
    @NamedQuery(name = "Organizacao.findByNomeOrganizacao", query = "SELECT o FROM Organizacao o WHERE o.nomeOrganizacao = :nomeOrganizacao"),
    @NamedQuery(name = "Organizacao.findByCnpj", query = "SELECT o FROM Organizacao o WHERE o.cnpj = :cnpj")})
public class Organizacao implements Serializable {
    @Basic(optional = false)
    @Column(name = "cnpj")
    private long cnpj;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrganizacao")
    private Integer idOrganizacao;
    @Basic(optional = false)
    @Column(name = "nomeOrganizacao")
    private String nomeOrganizacao;
    @Lob
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganizacao")
    private List<Projeto> projetoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganizacao")
    private List<Categoriaderisco> categoriaderiscoList;
    @JoinColumn(name = "idPoliticaOrganizacional", referencedColumnName = "idPoliticaOrganizacional")
    @ManyToOne(optional = false)
    private Politicaorganizacional idPoliticaOrganizacional;

    public Organizacao() {
    }

    public Organizacao(Integer idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public Organizacao(Integer idOrganizacao, String nomeOrganizacao, int cnpj) {
        this.idOrganizacao = idOrganizacao;
        this.nomeOrganizacao = nomeOrganizacao;
        this.cnpj = cnpj;
    }

    public Integer getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Integer idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public String getNomeOrganizacao() {
        return nomeOrganizacao;
    }

    public void setNomeOrganizacao(String nomeOrganizacao) {
        this.nomeOrganizacao = nomeOrganizacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @XmlTransient
    public List<Projeto> getProjetoList() {
        return projetoList;
    }

    public void setProjetoList(List<Projeto> projetoList) {
        this.projetoList = projetoList;
    }

    @XmlTransient
    public List<Categoriaderisco> getCategoriaderiscoList() {
        return categoriaderiscoList;
    }

    public void setCategoriaderiscoList(List<Categoriaderisco> categoriaderiscoList) {
        this.categoriaderiscoList = categoriaderiscoList;
    }

    public Politicaorganizacional getIdPoliticaOrganizacional() {
        return idPoliticaOrganizacional;
    }

    public void setIdPoliticaOrganizacional(Politicaorganizacional idPoliticaOrganizacional) {
        this.idPoliticaOrganizacional = idPoliticaOrganizacional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganizacao != null ? idOrganizacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizacao)) {
            return false;
        }
        Organizacao other = (Organizacao) object;
        if ((this.idOrganizacao == null && other.idOrganizacao != null) || (this.idOrganizacao != null && !this.idOrganizacao.equals(other.idOrganizacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Organizacao[ idOrganizacao=" + idOrganizacao + " ]";
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
    
}
