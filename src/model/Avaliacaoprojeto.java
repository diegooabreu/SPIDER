/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "avaliacaoprojeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacaoprojeto.findAll", query = "SELECT a FROM Avaliacaoprojeto a"),
    @NamedQuery(name = "Avaliacaoprojeto.findByIdAvaliacao", query = "SELECT a FROM Avaliacaoprojeto a WHERE a.idAvaliacao = :idAvaliacao"),
    @NamedQuery(name = "Avaliacaoprojeto.findByIdProjeto", query = "SELECT a FROM Avaliacaoprojeto a WHERE a.idProjeto = :idProjeto")})
public class Avaliacaoprojeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAvaliacao")
    private Integer idAvaliacao;
    @Lob
    @Column(name = "pontosFortes")
    private String pontosFortes;
    @Lob
    @Column(name = "pontosFracos")
    private String pontosFracos;
    @Lob
    @Column(name = "oportunidadesMelhoria")
    private String oportunidadesMelhoria;
    @Lob
    @Column(name = "informacoesAdicionais")
    private String informacoesAdicionais;
    @JoinColumn(name = "idProjeto", referencedColumnName = "idProjeto")
    @ManyToOne(optional = false)
    private Projeto idProjeto;

    public Avaliacaoprojeto() {
    }

    public Avaliacaoprojeto(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getPontosFortes() {
        return pontosFortes;
    }

    public void setPontosFortes(String pontosFortes) {
        this.pontosFortes = pontosFortes;
    }

    public String getPontosFracos() {
        return pontosFracos;
    }

    public void setPontosFracos(String pontosFracos) {
        this.pontosFracos = pontosFracos;
    }

    public String getOportunidadesMelhoria() {
        return oportunidadesMelhoria;
    }

    public void setOportunidadesMelhoria(String oportunidadesMelhoria) {
        this.oportunidadesMelhoria = oportunidadesMelhoria;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
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
        hash += (idAvaliacao != null ? idAvaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacaoprojeto)) {
            return false;
        }
        Avaliacaoprojeto other = (Avaliacaoprojeto) object;
        if ((this.idAvaliacao == null && other.idAvaliacao != null) || (this.idAvaliacao != null && !this.idAvaliacao.equals(other.idAvaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliacaoprojeto[ idAvaliacao=" + idAvaliacao + " ]";
    }
    
}
