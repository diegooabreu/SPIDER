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
import javax.persistence.ManyToMany;
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
@Table(name = "subcondicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcondicao.findAll", query = "SELECT s FROM Subcondicao s"),
    @NamedQuery(name = "Subcondicao.findByIdSubcondicao", query = "SELECT s FROM Subcondicao s WHERE s.idSubcondicao = :idSubcondicao"),
    @NamedQuery(name = "Subcondicao.findByStatusSubcondicao", query = "SELECT s FROM Subcondicao s WHERE s.statusSubcondicao = :statusSubcondicao"),
    @NamedQuery(name = "Subcondicao.findSubcondicaoByIdRisco", query = "SELECT s FROM Subcondicao s WHERE s.idRisco = :idRisco")})
public class Subcondicao implements Serializable {
    @Column(name = "identificacaoSubcondicao")
    private String identificacaoSubcondicao;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubcondicao")
    private Integer idSubcondicao;
    @Lob
    @Column(name = "descricaoSubcondicao")
    private String descricaoSubcondicao;
    @Basic(optional = false)
    @Column(name = "statusSubcondicao")
    private String statusSubcondicao;
    @ManyToMany(mappedBy = "subcondicaoList")
    private List<Historicorisco> historicoriscoList;
    @JoinColumn(name = "idRisco", referencedColumnName = "idRisco")
    @ManyToOne(optional = false)
    private Risco idRisco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcondicao")
    private List<Relacaosubcondicao> relacaosubcondicaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcondicao1")
    private List<Relacaosubcondicao> relacaosubcondicaoList1;

    public Subcondicao() {
    }

    public Subcondicao(Integer idSubcondicao) {
        this.idSubcondicao = idSubcondicao;
    }

    public Subcondicao(Integer idSubcondicao, String statusSubcondicao) {
        this.idSubcondicao = idSubcondicao;
        this.statusSubcondicao = statusSubcondicao;
    }

    public Integer getIdSubcondicao() {
        return idSubcondicao;
    }

    public void setIdSubcondicao(Integer idSubcondicao) {
        this.idSubcondicao = idSubcondicao;
    }

    public String getDescricaoSubcondicao() {
        return descricaoSubcondicao;
    }

    public void setDescricaoSubcondicao(String descricaoSubcondicao) {
        this.descricaoSubcondicao = descricaoSubcondicao;
    }

    public String getStatusSubcondicao() {
        return statusSubcondicao;
    }

    public void setStatusSubcondicao(String statusSubcondicao) {
        this.statusSubcondicao = statusSubcondicao;
    }

    @XmlTransient
    public List<Historicorisco> getHistoricoriscoList() {
        return historicoriscoList;
    }

    public void setHistoricoriscoList(List<Historicorisco> historicoriscoList) {
        this.historicoriscoList = historicoriscoList;
    }

    public Risco getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Risco idRisco) {
        this.idRisco = idRisco;
    }

    @XmlTransient
    public List<Relacaosubcondicao> getRelacaosubcondicaoList() {
        return relacaosubcondicaoList;
    }

    public void setRelacaosubcondicaoList(List<Relacaosubcondicao> relacaosubcondicaoList) {
        this.relacaosubcondicaoList = relacaosubcondicaoList;
    }

    @XmlTransient
    public List<Relacaosubcondicao> getRelacaosubcondicaoList1() {
        return relacaosubcondicaoList1;
    }

    public void setRelacaosubcondicaoList1(List<Relacaosubcondicao> relacaosubcondicaoList1) {
        this.relacaosubcondicaoList1 = relacaosubcondicaoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubcondicao != null ? idSubcondicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcondicao)) {
            return false;
        }
        Subcondicao other = (Subcondicao) object;
        if ((this.idSubcondicao == null && other.idSubcondicao != null) || (this.idSubcondicao != null && !this.idSubcondicao.equals(other.idSubcondicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Subcondicao[ idSubcondicao=" + idSubcondicao + " ]";
    }

    public String getIdentificacaoSubcondicao() {
        return identificacaoSubcondicao;
    }

    public void setIdentificacaoSubcondicao(String identificacaoSubcondicao) {
        this.identificacaoSubcondicao = identificacaoSubcondicao;
    }
    
}
