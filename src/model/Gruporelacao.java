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
 * @author Diego
 */
@Entity
@Table(name = "gruporelacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruporelacao.findGruporelacaoByRisco", query = "SELECT gr1 FROM Gruporelacao gr1 where gr1.idSubcondicao1 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) or gr1.idSubcondicao2 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) and gr1.idRelacao1 in (SELECT gr.idGrupo FROM Gruporelacao gr where gr.idSubcondicao1 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) or gr.idSubcondicao2 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) ) or gr1.idRelacao2 in (SELECT gr.idGrupo FROM Gruporelacao gr where gr.idSubcondicao1 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) or gr.idSubcondicao2 in (select s.idSubcondicao from Subcondicao s where s.idRisco = :idRisco) )"),
    @NamedQuery(name = "Gruporelacao.findAll", query = "SELECT g FROM Gruporelacao g"),
    @NamedQuery(name = "Gruporelacao.findByIdGrupo", query = "SELECT g FROM Gruporelacao g WHERE g.idGrupo = :idGrupo"),
    @NamedQuery(name = "Gruporelacao.findByIdSubcondicao1", query = "SELECT g FROM Gruporelacao g WHERE g.idSubcondicao1 = :idSubcondicao1"),
    @NamedQuery(name = "Gruporelacao.findByIdSubcondicao2", query = "SELECT g FROM Gruporelacao g WHERE g.idSubcondicao2 = :idSubcondicao2"),
    @NamedQuery(name = "Gruporelacao.findByIdRelacao1", query = "SELECT g FROM Gruporelacao g WHERE g.idRelacao1 = :idRelacao1"),
    @NamedQuery(name = "Gruporelacao.findByIdRelacao2", query = "SELECT g FROM Gruporelacao g WHERE g.idRelacao2 = :idRelacao2"),
    @NamedQuery(name = "Gruporelacao.findByRelacao", query = "SELECT g FROM Gruporelacao g WHERE g.relacao = :relacao")})
public class Gruporelacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private Integer idGrupo;
    @Column(name = "idSubcondicao1")
    private Integer idSubcondicao1;
    @Column(name = "idSubcondicao2")
    private Integer idSubcondicao2;
    @Column(name = "idRelacao1")
    private Integer idRelacao1;
    @Column(name = "idRelacao2")
    private Integer idRelacao2;
    @Column(name = "relacao")
    private String relacao;

    public Gruporelacao() {
    }

    public Gruporelacao(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdSubcondicao1() {
        return idSubcondicao1;
    }

    public void setIdSubcondicao1(Integer idSubcondicao1) {
        this.idSubcondicao1 = idSubcondicao1;
    }

    public Integer getIdSubcondicao2() {
        return idSubcondicao2;
    }

    public void setIdSubcondicao2(Integer idSubcondicao2) {
        this.idSubcondicao2 = idSubcondicao2;
    }

    public Integer getIdRelacao1() {
        return idRelacao1;
    }

    public void setIdRelacao1(Integer idRelacao1) {
        this.idRelacao1 = idRelacao1;
    }

    public Integer getIdRelacao2() {
        return idRelacao2;
    }

    public void setIdRelacao2(Integer idRelacao2) {
        this.idRelacao2 = idRelacao2;
    }

    public String getRelacao() {
        return relacao;
    }

    public void setRelacao(String relacao) {
        this.relacao = relacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruporelacao)) {
            return false;
        }
        Gruporelacao other = (Gruporelacao) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Gruporelacao[ idGrupo=" + idGrupo + " ]";
    }
    
}
