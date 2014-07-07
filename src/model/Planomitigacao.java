/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "planomitigacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planomitigacao.findPlanoMitigacaoByIdPontodecontroleEStatusRisco", query = "SELECT p FROM Planomitigacao p WHERE p.idPontoDeControle = :idPontodecontrole and p.idRisco.statusRisco = :statusRisco"),
    @NamedQuery(name = "Planomitigacao.findPlanoMitigacaoByIdMarcoDoProjetoEStatusRisco", query = "SELECT p FROM Planomitigacao p WHERE p.idMarcoDoProjeto = :idMarcoDoProjeto and p.idRisco.statusRisco = :statusRisco"),
    @NamedQuery(name = "Planomitigacao.findPlanoMitigacaoByIdMarcoDoProjeto", query = "SELECT p FROM Planomitigacao p WHERE p.idMarcoDoProjeto = :idMarcoDoProjeto"),
    @NamedQuery(name = "Planomitigacao.findPlanoMitigacaoByIdPontodecontrole", query = "SELECT p FROM Planomitigacao p WHERE p.idPontoDeControle = :idPontodecontrole"),
    @NamedQuery(name = "Planomitigacao.findPlanoMitigacaoByIdRisco", query = "SELECT p FROM Planomitigacao p WHERE p.idRisco = :idRisco"),
    @NamedQuery(name = "Planomitigacao.findAll", query = "SELECT p FROM Planomitigacao p"),
    @NamedQuery(name = "Planomitigacao.findByIdPlanoMitigacao", query = "SELECT p FROM Planomitigacao p WHERE p.idPlanoMitigacao = :idPlanoMitigacao"),
    @NamedQuery(name = "Planomitigacao.findByResponsavel", query = "SELECT p FROM Planomitigacao p WHERE p.responsavel = :responsavel"),
    @NamedQuery(name = "Planomitigacao.findByDataLimite", query = "SELECT p FROM Planomitigacao p WHERE p.dataLimite = :dataLimite"),
    @NamedQuery(name = "Planomitigacao.findByDataRealizacao", query = "SELECT p FROM Planomitigacao p WHERE p.dataRealizacao = :dataRealizacao")})
public class Planomitigacao implements Serializable {
    @JoinColumn(name = "idPontoDeControle", referencedColumnName = "idPontoDeControle")
    @ManyToOne
    private Pontodecontrole idPontoDeControle;
    @JoinColumn(name = "idMarcoDoProjeto", referencedColumnName = "idMarcoDoProjeto")
    @ManyToOne
    private Marcodoprojeto idMarcoDoProjeto;
    @Basic(optional = false)
    @Column(name = "identificacaoPlanoMitigacao")
    private String identificacaoPlanoMitigacao;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanoMitigacao")
    private Integer idPlanoMitigacao;
    @Lob
    @Column(name = "descricaoPlanoMitigacao")
    private String descricaoPlanoMitigacao;
    @Basic(optional = false)
    @Column(name = "responsavel")
    private String responsavel;
    @Basic(optional = false)
    @Column(name = "dataLimite")
    @Temporal(TemporalType.DATE)
    private Date dataLimite;
    @Basic(optional = false)
    @Lob
    @Column(name = "comoRealizar")
    private String comoRealizar;
    @Lob
    @Column(name = "informacoesAdicionais")
    private String informacoesAdicionais;
    @Column(name = "dataRealizacao")
    @Temporal(TemporalType.DATE)
    private Date dataRealizacao;
    @JoinColumn(name = "idRisco", referencedColumnName = "idRisco")
    @ManyToOne(optional = false)
    private Risco idRisco;

    public Planomitigacao() {
    }

    public Planomitigacao(Integer idPlanoMitigacao) {
        this.idPlanoMitigacao = idPlanoMitigacao;
    }

    public Planomitigacao(Integer idPlanoMitigacao, String responsavel, Date dataLimite, String comoRealizar) {
        this.idPlanoMitigacao = idPlanoMitigacao;
        this.responsavel = responsavel;
        this.dataLimite = dataLimite;
        this.comoRealizar = comoRealizar;
    }

    public Integer getIdPlanoMitigacao() {
        return idPlanoMitigacao;
    }

    public void setIdPlanoMitigacao(Integer idPlanoMitigacao) {
        this.idPlanoMitigacao = idPlanoMitigacao;
    }

    public String getDescricaoPlanoMitigacao() {
        return descricaoPlanoMitigacao;
    }

    public void setDescricaoPlanoMitigacao(String descricaoPlanoMitigacao) {
        this.descricaoPlanoMitigacao = descricaoPlanoMitigacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getComoRealizar() {
        return comoRealizar;
    }

    public void setComoRealizar(String comoRealizar) {
        this.comoRealizar = comoRealizar;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Risco getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Risco idRisco) {
        this.idRisco = idRisco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanoMitigacao != null ? idPlanoMitigacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planomitigacao)) {
            return false;
        }
        Planomitigacao other = (Planomitigacao) object;
        if ((this.idPlanoMitigacao == null && other.idPlanoMitigacao != null) || (this.idPlanoMitigacao != null && !this.idPlanoMitigacao.equals(other.idPlanoMitigacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Planomitigacao[ idPlanoMitigacao=" + idPlanoMitigacao + " ]";
    }

    public String getIdentificacaoPlanoMitigacao() {
        return identificacaoPlanoMitigacao;
    }

    public void setIdentificacaoPlanoMitigacao(String identificacaoPlanoMitigacao) {
        this.identificacaoPlanoMitigacao = identificacaoPlanoMitigacao;
    }

    public Pontodecontrole getIdPontoDeControle() {
        return idPontoDeControle;
    }

    public void setIdPontoDeControle(Pontodecontrole idPontoDeControle) {
        this.idPontoDeControle = idPontoDeControle;
    }

    public Marcodoprojeto getIdMarcoDoProjeto() {
        return idMarcoDoProjeto;
    }

    public void setIdMarcoDoProjeto(Marcodoprojeto idMarcoDoProjeto) {
        this.idMarcoDoProjeto = idMarcoDoProjeto;
    }
 
}
