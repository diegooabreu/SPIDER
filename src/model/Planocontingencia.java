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
@Table(name = "planocontingencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planocontingencia.findPlanoContingenciaEmAcao", query = "SELECT p FROM Planocontingencia p WHERE p.idRisco.statusRisco = :statusRisco and p.dataLimite = :dataLimite and p.idRisco.contem.projeto = :idProjeto"),
    @NamedQuery(name = "Planocontingencia.findPlanoContingenciaByIdRisco", query = "SELECT p FROM Planocontingencia p WHERE p.idRisco = :idRisco"),
    @NamedQuery(name = "Planocontingencia.findAll", query = "SELECT p FROM Planocontingencia p"),
    @NamedQuery(name = "Planocontingencia.findByIdPlanoContingencia", query = "SELECT p FROM Planocontingencia p WHERE p.idPlanoContingencia = :idPlanoContingencia"),
    @NamedQuery(name = "Planocontingencia.findByResponsavel", query = "SELECT p FROM Planocontingencia p WHERE p.responsavel = :responsavel"),
    @NamedQuery(name = "Planocontingencia.findByDataLimite", query = "SELECT p FROM Planocontingencia p WHERE p.dataLimite = :dataLimite"),
    @NamedQuery(name = "Planocontingencia.findByDataRealizacao", query = "SELECT p FROM Planocontingencia p WHERE p.dataRealizacao = :dataRealizacao")})
public class Planocontingencia implements Serializable {
    @JoinColumn(name = "idPontoDeControle", referencedColumnName = "idPontoDeControle")
    @ManyToOne
    private Pontodecontrole idPontoDeControle;
    @JoinColumn(name = "idMarcoDoProjeto", referencedColumnName = "idMarcoDoProjeto")
    @ManyToOne
    private Marcodoprojeto idMarcoDoProjeto;
    @Basic(optional = false)
    @Column(name = "identificacaoPlanoContingencia")
    private String identificacaoPlanoContingencia;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanoContingencia")
    private Integer idPlanoContingencia;
    @Lob
    @Column(name = "descricaoPlanoContingencia")
    private String descricaoPlanoContingencia;
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

    public Planocontingencia() {
    }

    public Planocontingencia(Integer idPlanoContingencia) {
        this.idPlanoContingencia = idPlanoContingencia;
    }

    public Planocontingencia(Integer idPlanoContingencia, String responsavel, Date dataLimite, String comoRealizar) {
        this.idPlanoContingencia = idPlanoContingencia;
        this.responsavel = responsavel;
        this.dataLimite = dataLimite;
        this.comoRealizar = comoRealizar;
    }

    public Integer getIdPlanoContingencia() {
        return idPlanoContingencia;
    }

    public void setIdPlanoContingencia(Integer idPlanoContingencia) {
        this.idPlanoContingencia = idPlanoContingencia;
    }

    public String getDescricaoPlanoContingencia() {
        return descricaoPlanoContingencia;
    }

    public void setDescricaoPlanoContingencia(String descricaoPlanoContingencia) {
        this.descricaoPlanoContingencia = descricaoPlanoContingencia;
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
        hash += (idPlanoContingencia != null ? idPlanoContingencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planocontingencia)) {
            return false;
        }
        Planocontingencia other = (Planocontingencia) object;
        if ((this.idPlanoContingencia == null && other.idPlanoContingencia != null) || (this.idPlanoContingencia != null && !this.idPlanoContingencia.equals(other.idPlanoContingencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Planocontingencia[ idPlanoContingencia=" + idPlanoContingencia + " ]";
    }

    public String getIdentificacaoPlanoContingencia() {
        return identificacaoPlanoContingencia;
    }

    public void setIdentificacaoPlanoContingencia(String identificacaoPlanoContingencia) {
        this.identificacaoPlanoContingencia = identificacaoPlanoContingencia;
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
