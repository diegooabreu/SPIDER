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
public class RelacaosubcondicaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idSubcondicao1")
    private int idSubcondicao1;
    @Basic(optional = false)
    @Column(name = "idSubcondicao2")
    private int idSubcondicao2;
    @Basic(optional = false)
    @Column(name = "relacao")
    private String relacao;

    public RelacaosubcondicaoPK() {
    }

    public RelacaosubcondicaoPK(int idSubcondicao1, int idSubcondicao2, String relacao) {
        this.idSubcondicao1 = idSubcondicao1;
        this.idSubcondicao2 = idSubcondicao2;
        this.relacao = relacao;
    }

    public int getIdSubcondicao1() {
        return idSubcondicao1;
    }

    public void setIdSubcondicao1(int idSubcondicao1) {
        this.idSubcondicao1 = idSubcondicao1;
    }

    public int getIdSubcondicao2() {
        return idSubcondicao2;
    }

    public void setIdSubcondicao2(int idSubcondicao2) {
        this.idSubcondicao2 = idSubcondicao2;
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
        hash += (int) idSubcondicao1;
        hash += (int) idSubcondicao2;
        hash += (relacao != null ? relacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacaosubcondicaoPK)) {
            return false;
        }
        RelacaosubcondicaoPK other = (RelacaosubcondicaoPK) object;
        if (this.idSubcondicao1 != other.idSubcondicao1) {
            return false;
        }
        if (this.idSubcondicao2 != other.idSubcondicao2) {
            return false;
        }
        if ((this.relacao == null && other.relacao != null) || (this.relacao != null && !this.relacao.equals(other.relacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RelacaosubcondicaoPK[ idSubcondicao1=" + idSubcondicao1 + ", idSubcondicao2=" + idSubcondicao2 + ", relacao=" + relacao + " ]";
    }
    
}
