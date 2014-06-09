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
@Table(name = "categoriaderisco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriaderisco.findAll", query = "SELECT c FROM Categoriaderisco c"),
    @NamedQuery(name = "Categoriaderisco.findByIdCategoriaDeRisco", query = "SELECT c FROM Categoriaderisco c WHERE c.idCategoriaDeRisco = :idCategoriaDeRisco"),
    @NamedQuery(name = "Categoriaderisco.findByNomeCategoria", query = "SELECT c FROM Categoriaderisco c WHERE c.nomeCategoria = :nomeCategoria"),
    @NamedQuery(name = "Categoriaderisco.findByDescricaoCategoria", query = "SELECT c FROM Categoriaderisco c WHERE c.descricaoCategoria = :descricaoCategoria"),
    @NamedQuery(name = "Categoriaderisco.findByPossiveisOrigens", query = "SELECT c FROM Categoriaderisco c WHERE c.possiveisOrigens = :possiveisOrigens"),
    @NamedQuery(name = "Categoriaderisco.findByCriteriosParaImpacto", query = "SELECT c FROM Categoriaderisco c WHERE c.criteriosParaImpacto = :criteriosParaImpacto"),
    @NamedQuery(name = "Categoriaderisco.findByCriteriosParaProbabilidade", query = "SELECT c FROM Categoriaderisco c WHERE c.criteriosParaProbabilidade = :criteriosParaProbabilidade"),
    })
public class Categoriaderisco implements Serializable {
    @Basic(optional = false)
    @Column(name = "earOrganizacional")
    private boolean earOrganizacional;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoriaDeRisco")
    private Integer idCategoriaDeRisco;
    @Basic(optional = false)
    @Column(name = "nomeCategoria")
    private String nomeCategoria;
    @Column(name = "descricaoCategoria")
    private String descricaoCategoria;
    @Column(name = "possiveisOrigens")
    private String possiveisOrigens;
    @Column(name = "criterios_para_impacto")
    private String criteriosParaImpacto;
    @Column(name = "criterios_para_probabilidade")
    private String criteriosParaProbabilidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaderisco")
    private List<Contem> contemList;
    @OneToMany(mappedBy = "fkidCategoriaDeRisco")
    private List<Categoriaderisco> categoriaderiscoList;
    @JoinColumn(name = "fk_idCategoriaDeRisco", referencedColumnName = "idCategoriaDeRisco")
    @ManyToOne
    private Categoriaderisco fkidCategoriaDeRisco;
    @JoinColumn(name = "idOrganizacao", referencedColumnName = "idOrganizacao")
    @ManyToOne(optional = false)
    private Organizacao idOrganizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaDeRisco")
    private List<Avaliacaocategoria> avaliacaocategoriaList;

    public Categoriaderisco() {
    }

    public Categoriaderisco(Integer idCategoriaDeRisco) {
        this.idCategoriaDeRisco = idCategoriaDeRisco;
    }

    public Categoriaderisco(Integer idCategoriaDeRisco, String nomeCategoria) {
        this.idCategoriaDeRisco = idCategoriaDeRisco;
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getIdCategoriaDeRisco() {
        return idCategoriaDeRisco;
    }

    public void setIdCategoriaDeRisco(Integer idCategoriaDeRisco) {
        this.idCategoriaDeRisco = idCategoriaDeRisco;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public String getPossiveisOrigens() {
        return possiveisOrigens;
    }

    public void setPossiveisOrigens(String possiveisOrigens) {
        this.possiveisOrigens = possiveisOrigens;
    }

    public String getCriteriosParaImpacto() {
        return criteriosParaImpacto;
    }

    public void setCriteriosParaImpacto(String criteriosParaImpacto) {
        this.criteriosParaImpacto = criteriosParaImpacto;
    }

    public String getCriteriosParaProbabilidade() {
        return criteriosParaProbabilidade;
    }

    public void setCriteriosParaProbabilidade(String criteriosParaProbabilidade) {
        this.criteriosParaProbabilidade = criteriosParaProbabilidade;
    }

    @XmlTransient
    public List<Contem> getContemList() {
        return contemList;
    }

    public void setContemList(List<Contem> contemList) {
        this.contemList = contemList;
    }

    @XmlTransient
    public List<Categoriaderisco> getCategoriaderiscoList() {
        return categoriaderiscoList;
    }

    public void setCategoriaderiscoList(List<Categoriaderisco> categoriaderiscoList) {
        this.categoriaderiscoList = categoriaderiscoList;
    }

    public Categoriaderisco getFkidCategoriaDeRisco() {
        return fkidCategoriaDeRisco;
    }

    public void setFkidCategoriaDeRisco(Categoriaderisco fkidCategoriaDeRisco) {
        this.fkidCategoriaDeRisco = fkidCategoriaDeRisco;
    }

    public Organizacao getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Organizacao idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    @XmlTransient
    public List<Avaliacaocategoria> getAvaliacaocategoriaList() {
        return avaliacaocategoriaList;
    }

    public void setAvaliacaocategoriaList(List<Avaliacaocategoria> avaliacaocategoriaList) {
        this.avaliacaocategoriaList = avaliacaocategoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaDeRisco != null ? idCategoriaDeRisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriaderisco)) {
            return false;
        }
        Categoriaderisco other = (Categoriaderisco) object;
        if ((this.idCategoriaDeRisco == null && other.idCategoriaDeRisco != null) || (this.idCategoriaDeRisco != null && !this.idCategoriaDeRisco.equals(other.idCategoriaDeRisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Categoriaderisco[ idCategoriaDeRisco=" + idCategoriaDeRisco + " ]";
    }

    public boolean getEarOrganizacional() {
        return earOrganizacional;
    }

    public void setEarOrganizacional(boolean earOrganizacional) {
        this.earOrganizacional = earOrganizacional;
    }
    
}
