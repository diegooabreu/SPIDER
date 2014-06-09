/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "relacaosubcondicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relacaosubcondicao.findAll", query = "SELECT r FROM Relacaosubcondicao r"),
    @NamedQuery(name = "Relacaosubcondicao.findByIdSubcondicao1", query = "SELECT r FROM Relacaosubcondicao r WHERE r.relacaosubcondicaoPK.idSubcondicao1 = :idSubcondicao1"),
    @NamedQuery(name = "Relacaosubcondicao.findByIdSubcondicao2", query = "SELECT r FROM Relacaosubcondicao r WHERE r.relacaosubcondicaoPK.idSubcondicao2 = :idSubcondicao2"),
    @NamedQuery(name = "Relacaosubcondicao.findByRelacao", query = "SELECT r FROM Relacaosubcondicao r WHERE r.relacaosubcondicaoPK.relacao = :relacao")})
public class Relacaosubcondicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelacaosubcondicaoPK relacaosubcondicaoPK;
    @ManyToMany(mappedBy = "relacaosubcondicaoList")
    private List<Historicorisco> historicoriscoList;
    @JoinColumn(name = "idSubcondicao2", referencedColumnName = "idSubcondicao", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subcondicao subcondicao;
    @JoinColumn(name = "idSubcondicao1", referencedColumnName = "idSubcondicao", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subcondicao subcondicao1;

    public Relacaosubcondicao() {
    }

    public Relacaosubcondicao(RelacaosubcondicaoPK relacaosubcondicaoPK) {
        this.relacaosubcondicaoPK = relacaosubcondicaoPK;
    }

    public Relacaosubcondicao(int idSubcondicao1, int idSubcondicao2, String relacao) {
        this.relacaosubcondicaoPK = new RelacaosubcondicaoPK(idSubcondicao1, idSubcondicao2, relacao);
    }

    public RelacaosubcondicaoPK getRelacaosubcondicaoPK() {
        return relacaosubcondicaoPK;
    }

    public void setRelacaosubcondicaoPK(RelacaosubcondicaoPK relacaosubcondicaoPK) {
        this.relacaosubcondicaoPK = relacaosubcondicaoPK;
    }

    @XmlTransient
    public List<Historicorisco> getHistoricoriscoList() {
        return historicoriscoList;
    }

    public void setHistoricoriscoList(List<Historicorisco> historicoriscoList) {
        this.historicoriscoList = historicoriscoList;
    }

    public Subcondicao getSubcondicao() {
        return subcondicao;
    }

    public void setSubcondicao(Subcondicao subcondicao) {
        this.subcondicao = subcondicao;
    }

    public Subcondicao getSubcondicao1() {
        return subcondicao1;
    }

    public void setSubcondicao1(Subcondicao subcondicao1) {
        this.subcondicao1 = subcondicao1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relacaosubcondicaoPK != null ? relacaosubcondicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relacaosubcondicao)) {
            return false;
        }
        Relacaosubcondicao other = (Relacaosubcondicao) object;
        if ((this.relacaosubcondicaoPK == null && other.relacaosubcondicaoPK != null) || (this.relacaosubcondicaoPK != null && !this.relacaosubcondicaoPK.equals(other.relacaosubcondicaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Relacaosubcondicao[ relacaosubcondicaoPK=" + relacaosubcondicaoPK + " ]";
    }
    
}
