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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "risco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Risco.findAllPOrdemGrauDeEsposicaoAndStatus", query = "SELECT r FROM Risco r WHERE r.contem.projeto = :idProjeto and r.statusRisco = :statusRisco order by r.prioridade asc, r.grauSeveridade desc"),
    @NamedQuery(name = "Risco.findRiscosByIdProjeto", query = "SELECT r FROM Risco r WHERE r.contem.projeto = :idProjeto"),
    @NamedQuery(name = "Risco.findAll", query = "SELECT r FROM Risco r"),
    @NamedQuery(name = "Risco.findByIdRisco", query = "SELECT r FROM Risco r WHERE r.idRisco = :idRisco"),
    @NamedQuery(name = "Risco.findByDataIdentificacao", query = "SELECT r FROM Risco r WHERE r.dataIdentificacao = :dataIdentificacao"),
    @NamedQuery(name = "Risco.findByEmissor", query = "SELECT r FROM Risco r WHERE r.emissor = :emissor"),
    @NamedQuery(name = "Risco.findByProbabilidade", query = "SELECT r FROM Risco r WHERE r.probabilidade = :probabilidade"),
    @NamedQuery(name = "Risco.findByImpacto", query = "SELECT r FROM Risco r WHERE r.impacto = :impacto"),
    @NamedQuery(name = "Risco.findByStatusRisco", query = "SELECT r FROM Risco r WHERE r.statusRisco = :statusRisco"),
    @NamedQuery(name = "Risco.findByPrioridade", query = "SELECT r FROM Risco r WHERE r.prioridade = :prioridade"),
    @NamedQuery(name = "Risco.findByGrauSeveridade", query = "SELECT r FROM Risco r WHERE r.grauSeveridade = :grauSeveridade"),
    @NamedQuery(name = "Risco.findByIdentificacao", query = "SELECT r FROM Risco r WHERE r.identificacao = :identificacao")})
    @NamedQuery(name = "Risco.findAllPOrdemGrauDeEsposicao", query = "SELECT r FROM Risco r WHERE r.contem.projeto = :idProjeto order by r.prioridade asc, r.grauSeveridade desc")

public class Risco implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRisco")
    private List<Subcondicao> subcondicaoList;
    @Basic(optional = false)
    @Column(name = "grauSeveridade")
    private double grauSeveridade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRisco")
    private List<Historicorisco> historicoriscoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRisco")
    private List<Planomitigacao> planomitigacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRisco")
    private List<Planocontingencia> planocontingenciaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRisco")
    private Integer idRisco;
    @Basic(optional = false)
    @Column(name = "dataIdentificacao")
    @Temporal(TemporalType.DATE)
    private Date dataIdentificacao;
    @Basic(optional = false)
    @Column(name = "emissor")
    private String emissor;
    @Basic(optional = false)
    @Column(name = "probabilidade")
    private int probabilidade;
    @Basic(optional = false)
    @Column(name = "impacto")
    private String impacto;
    @Basic(optional = false)
    @Column(name = "statusRisco")
    private String statusRisco;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "prioridade")
    private int prioridade;
    @Basic(optional = false)
    @Column(name = "identificacao")
    private String identificacao;
//    @JoinTable(name = "relacaorisco", joinColumns = {
//        @JoinColumn(name = "idRisco1", referencedColumnName = "idRisco")}, inverseJoinColumns = {
//        @JoinColumn(name = "idRisco2", referencedColumnName = "idRisco")})
//    @ManyToMany
//    private List<Risco> riscoList;
//    @ManyToMany(mappedBy = "riscoList")
//    private List<Risco> riscoList1;
    @JoinTable(name = "relacaoentreriscos", joinColumns = {
        @JoinColumn(name = "idRiscoInfluenciador", referencedColumnName = "idRisco")}, inverseJoinColumns = {
        @JoinColumn(name = "idRiscoInfluenciado", referencedColumnName = "idRisco")})
    @ManyToMany
    private List<Risco> riscoList2;
    @ManyToMany(mappedBy = "riscoList2")
    private List<Risco> riscoList3;
    @JoinColumns({
        @JoinColumn(name = "idProjeto", referencedColumnName = "idProjeto"),
        @JoinColumn(name = "idCategoriaDeRisco", referencedColumnName = "idCategoriaDeRisco")})
    @ManyToOne(optional = false)
    private Contem contem;

    public Risco() {
    }

    public Risco(Integer idRisco) {
        this.idRisco = idRisco;
    }

    public Risco(Integer idRisco, Date dataIdentificacao, String emissor, int probabilidade, String impacto, String statusRisco, String descricao, int prioridade, int grauSeveridade, String identificacao) {
        this.idRisco = idRisco;
        this.dataIdentificacao = dataIdentificacao;
        this.emissor = emissor;
        this.probabilidade = probabilidade;
        this.impacto = impacto;
        this.statusRisco = statusRisco;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.grauSeveridade = grauSeveridade;
        this.identificacao = identificacao;
    }

    public Integer getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Integer idRisco) {
        this.idRisco = idRisco;
    }

    public Date getDataIdentificacao() {
        return dataIdentificacao;
    }

    public void setDataIdentificacao(Date dataIdentificacao) {
        this.dataIdentificacao = dataIdentificacao;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public int getProbabilidade() {
        return probabilidade;
    }

    public void setProbabilidade(int probabilidade) {
        this.probabilidade = probabilidade;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getStatusRisco() {
        return statusRisco;
    }

    public void setStatusRisco(String statusRisco) {
        this.statusRisco = statusRisco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

//    @XmlTransient
//    public List<Risco> getRiscoList() {
//        return riscoList;
//    }
//
//    public void setRiscoList(List<Risco> riscoList) {
//        this.riscoList = riscoList;
//    }

//    @XmlTransient
//    public List<Risco> getRiscoList1() {
//        return riscoList1;
//    }
//
//    public void setRiscoList1(List<Risco> riscoList1) {
//        this.riscoList1 = riscoList1;
//    }

    @XmlTransient
    public List<Risco> getRiscoList2() {
        return riscoList2;
    }

    public void setRiscoList2(List<Risco> riscoList2) {
        this.riscoList2 = riscoList2;
    }

    @XmlTransient
    public List<Risco> getRiscoList3() {
        return riscoList3;
    }

    public void setRiscoList3(List<Risco> riscoList3) {
        this.riscoList3 = riscoList3;
    }

    public Contem getContem() {
        return contem;
    }

    public void setContem(Contem contem) {
        this.contem = contem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRisco != null ? idRisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Risco)) {
            return false;
        }
        Risco other = (Risco) object;
        if ((this.idRisco == null && other.idRisco != null) || (this.idRisco != null && !this.idRisco.equals(other.idRisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Risco[ idRisco=" + idRisco + " ]";
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

    @XmlTransient
    public List<Historicorisco> getHistoricoriscoList() {
        return historicoriscoList;
    }

    public void setHistoricoriscoList(List<Historicorisco> historicoriscoList) {
        this.historicoriscoList = historicoriscoList;
    }

    public double getGrauSeveridade() {
        return grauSeveridade;
    }

    public void setGrauSeveridade(double grauSeveridade) {
        this.grauSeveridade = grauSeveridade;
    }

    @XmlTransient
    public List<Subcondicao> getSubcondicaoList() {
        return subcondicaoList;
    }

    public void setSubcondicaoList(List<Subcondicao> subcondicaoList) {
        this.subcondicaoList = subcondicaoList;
    }
    
}
