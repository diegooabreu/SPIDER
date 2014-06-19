/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MarcosSenna
 */
@Entity
@Table(name = "marcodoprojeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcodoprojeto.findByIdProjeto", query = "SELECT m FROM Marcodoprojeto m WHERE m.idProjeto = :idProjeto"),
    @NamedQuery(name = "Marcodoprojeto.findAll", query = "SELECT m FROM Marcodoprojeto m"),
    @NamedQuery(name = "Marcodoprojeto.findByIdMarcoDoProjeto", query = "SELECT m FROM Marcodoprojeto m WHERE m.idMarcoDoProjeto = :idMarcoDoProjeto"),
    @NamedQuery(name = "Marcodoprojeto.findByNomeMarcoDoProjeto", query = "SELECT m FROM Marcodoprojeto m WHERE m.nomeMarcoDoProjeto = :nomeMarcoDoProjeto"),
    @NamedQuery(name = "Marcodoprojeto.findByDataMarcoProjeto", query = "SELECT m FROM Marcodoprojeto m WHERE m.dataMarcoProjeto = :dataMarcoProjeto"),
    @NamedQuery(name = "Marcodoprojeto.findByDescricaoMarcoProjeto", query = "SELECT m FROM Marcodoprojeto m WHERE m.descricaoMarcoProjeto = :descricaoMarcoProjeto")})
public class Marcodoprojeto implements Serializable {
    @OneToMany(mappedBy = "idMarcoDoProjeto")
    private List<Planomitigacao> planomitigacaoList;
    @OneToMany(mappedBy = "idMarcoDoProjeto")
    private List<Planocontingencia> planocontingenciaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarcoDoProjeto")
    private Integer idMarcoDoProjeto;
    @Column(name = "nomeMarcoDoProjeto")
    private String nomeMarcoDoProjeto;
    @Column(name = "dataMarcoProjeto")
    @Temporal(TemporalType.DATE)
    private Date dataMarcoProjeto;
    @Column(name = "descricaoMarcoProjeto")
    private String descricaoMarcoProjeto;
    @JoinColumn(name = "idProjeto", referencedColumnName = "idProjeto")
    @ManyToOne(optional = false)
    private Projeto idProjeto;

    public Marcodoprojeto() {
    }

    public Marcodoprojeto(Integer idMarcoDoProjeto) {
        this.idMarcoDoProjeto = idMarcoDoProjeto;
    }

    public Integer getIdMarcoDoProjeto() {
        return idMarcoDoProjeto;
    }

    public void setIdMarcoDoProjeto(Integer idMarcoDoProjeto) {
        this.idMarcoDoProjeto = idMarcoDoProjeto;
    }

    public String getNomeMarcoDoProjeto() {
        return nomeMarcoDoProjeto;
    }

    public void setNomeMarcoDoProjeto(String nomeMarcoDoProjeto) {
        this.nomeMarcoDoProjeto = nomeMarcoDoProjeto;
    }

    public Date getDataMarcoProjeto() {
        return dataMarcoProjeto;
    }

    public void setDataMarcoProjeto(Date dataMarcoProjeto) {
        this.dataMarcoProjeto = dataMarcoProjeto;
    }

    public String getDescricaoMarcoProjeto() {
        return descricaoMarcoProjeto;
    }

    public void setDescricaoMarcoProjeto(String descricaoMarcoProjeto) {
        this.descricaoMarcoProjeto = descricaoMarcoProjeto;
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
        hash += (idMarcoDoProjeto != null ? idMarcoDoProjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcodoprojeto)) {
            return false;
        }
        Marcodoprojeto other = (Marcodoprojeto) object;
        if ((this.idMarcoDoProjeto == null && other.idMarcoDoProjeto != null) || (this.idMarcoDoProjeto != null && !this.idMarcoDoProjeto.equals(other.idMarcoDoProjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Marcodoprojeto[ idMarcoDoProjeto=" + idMarcoDoProjeto + " ]";
    }

    @XmlTransient
    public List<Planocontingencia> getPlanocontingenciaList() {
        return planocontingenciaList;
    }

    public void setPlanocontingenciaList(List<Planocontingencia> planocontingenciaList) {
        this.planocontingenciaList = planocontingenciaList;
    }

    @XmlTransient
    public List<Planomitigacao> getPlanomitigacaoList() {
        return planomitigacaoList;
    }

    public void setPlanomitigacaoList(List<Planomitigacao> planomitigacaoList) {
        this.planomitigacaoList = planomitigacaoList;
    }
    
}
