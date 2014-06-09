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
@Table(name = "projeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p"),
    @NamedQuery(name = "Projeto.findByIdProjeto", query = "SELECT p FROM Projeto p WHERE p.idProjeto = :idProjeto"),
    @NamedQuery(name = "Projeto.findByNomeProjeto", query = "SELECT p FROM Projeto p WHERE p.nomeProjeto = :nomeProjeto"),
    @NamedQuery(name = "Projeto.findByResponsavelProjeto", query = "SELECT p FROM Projeto p WHERE p.responsavelProjeto = :responsavelProjeto"),
    @NamedQuery(name = "Projeto.findByResponsavelGerenciaRiscos", query = "SELECT p FROM Projeto p WHERE p.responsavelGerenciaRiscos = :responsavelGerenciaRiscos")})
public class Projeto implements Serializable {
    @Column(name = "nomeArquivoPlanoRisco")
    private String nomeArquivoPlanoRisco;
    @Basic(optional = false)
    @Column(name = "concluido")
    private boolean concluido;
    @Column(name = "caminhoPlanoDeRisco")
    private String caminhoPlanoDeRisco;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProjeto")
    private Integer idProjeto;
    @Basic(optional = false)
    @Column(name = "nomeProjeto")
    private String nomeProjeto;
    @Basic(optional = false)
    @Column(name = "responsavelProjeto")
    private String responsavelProjeto;
    @Basic(optional = false)
    @Column(name = "responsavelGerenciaRiscos")
    private String responsavelGerenciaRiscos;
    @Lob
    @Column(name = "descricaoProjeto")
    private String descricaoProjeto;
    @Lob
    @Column(name = "planoDeRisco")
    private String planoDeRisco;
    @JoinColumn(name = "idOrganizacao", referencedColumnName = "idOrganizacao")
    @ManyToOne(optional = false)
    private Organizacao idOrganizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProjeto")
    private List<Avaliacaoprojeto> avaliacaoprojetoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projeto")
    private List<Contem> contemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProjeto")
    private List<Pontodecontrole> pontodecontroleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProjeto")
    private List<Aviso> avisoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProjeto")
    private List<Marcodoprojeto> marcodoprojetoList;

    public Projeto() {
    }

    public Projeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Projeto(Integer idProjeto, String nomeProjeto, String responsavelProjeto, String responsavelGerenciaRiscos) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.responsavelProjeto = responsavelProjeto;
        this.responsavelGerenciaRiscos = responsavelGerenciaRiscos;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getResponsavelProjeto() {
        return responsavelProjeto;
    }

    public void setResponsavelProjeto(String responsavelProjeto) {
        this.responsavelProjeto = responsavelProjeto;
    }

    public String getResponsavelGerenciaRiscos() {
        return responsavelGerenciaRiscos;
    }

    public void setResponsavelGerenciaRiscos(String responsavelGerenciaRiscos) {
        this.responsavelGerenciaRiscos = responsavelGerenciaRiscos;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getPlanoDeRisco() {
        return planoDeRisco;
    }

    public void setPlanoDeRisco(String planoDeRisco) {
        this.planoDeRisco = planoDeRisco;
    }

    public Organizacao getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Organizacao idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    @XmlTransient
    public List<Avaliacaoprojeto> getAvaliacaoprojetoList() {
        return avaliacaoprojetoList;
    }

    public void setAvaliacaoprojetoList(List<Avaliacaoprojeto> avaliacaoprojetoList) {
        this.avaliacaoprojetoList = avaliacaoprojetoList;
    }

    @XmlTransient
    public List<Contem> getContemList() {
        return contemList;
    }

    public void setContemList(List<Contem> contemList) {
        this.contemList = contemList;
    }

    @XmlTransient
    public List<Pontodecontrole> getPontodecontroleList() {
        return pontodecontroleList;
    }

    public void setPontodecontroleList(List<Pontodecontrole> pontodecontroleList) {
        this.pontodecontroleList = pontodecontroleList;
    }

    @XmlTransient
    public List<Aviso> getAvisoList() {
        return avisoList;
    }

    public void setAvisoList(List<Aviso> avisoList) {
        this.avisoList = avisoList;
    }

    @XmlTransient
    public List<Marcodoprojeto> getMarcodoprojetoList() {
        return marcodoprojetoList;
    }

    public void setMarcodoprojetoList(List<Marcodoprojeto> marcodoprojetoList) {
        this.marcodoprojetoList = marcodoprojetoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjeto != null ? idProjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.idProjeto == null && other.idProjeto != null) || (this.idProjeto != null && !this.idProjeto.equals(other.idProjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Projeto[ idProjeto=" + idProjeto + " ]";
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public String getCaminhoPlanoDeRisco() {
        return caminhoPlanoDeRisco;
    }

    public void setCaminhoPlanoDeRisco(String caminhoPlanoDeRisco) {
        this.caminhoPlanoDeRisco = caminhoPlanoDeRisco;
    }

    public String getNomeArquivoPlanoRisco() {
        return nomeArquivoPlanoRisco;
    }

    public void setNomeArquivoPlanoRisco(String nomeArquivoPlanoRisco) {
        this.nomeArquivoPlanoRisco = nomeArquivoPlanoRisco;
    }
    
}
