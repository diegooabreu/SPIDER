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
@Table(name = "avaliacaocategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacaocategoria.findByIdCategoriaDeRisco", query = "SELECT a FROM Avaliacaocategoria a WHERE a.idCategoriaDeRisco = :idCategoriaDeRisco"),
    @NamedQuery(name = "Avaliacaocategoria.findAll", query = "SELECT a FROM Avaliacaocategoria a"),
    @NamedQuery(name = "Avaliacaocategoria.findByIdavaliacaoCategoria", query = "SELECT a FROM Avaliacaocategoria a WHERE a.idavaliacaoCategoria = :idavaliacaoCategoria"),
    @NamedQuery(name = "Avaliacaocategoria.findByPontosFortes", query = "SELECT a FROM Avaliacaocategoria a WHERE a.pontosFortes = :pontosFortes")})
public class Avaliacaocategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idavaliacaoCategoria")
    private Integer idavaliacaoCategoria;
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
    @JoinColumn(name = "idCategoriaDeRisco", referencedColumnName = "idCategoriaDeRisco")
    @ManyToOne(optional = false)
    private Categoriaderisco idCategoriaDeRisco;

    public Avaliacaocategoria() {
    }

    public Avaliacaocategoria(Integer idavaliacaoCategoria) {
        this.idavaliacaoCategoria = idavaliacaoCategoria;
    }

    public Integer getIdavaliacaoCategoria() {
        return idavaliacaoCategoria;
    }

    public void setIdavaliacaoCategoria(Integer idavaliacaoCategoria) {
        this.idavaliacaoCategoria = idavaliacaoCategoria;
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

    public Categoriaderisco getIdCategoriaDeRisco() {
        return idCategoriaDeRisco;
    }

    public void setIdCategoriaDeRisco(Categoriaderisco idCategoriaDeRisco) {
        this.idCategoriaDeRisco = idCategoriaDeRisco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idavaliacaoCategoria != null ? idavaliacaoCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacaocategoria)) {
            return false;
        }
        Avaliacaocategoria other = (Avaliacaocategoria) object;
        if ((this.idavaliacaoCategoria == null && other.idavaliacaoCategoria != null) || (this.idavaliacaoCategoria != null && !this.idavaliacaoCategoria.equals(other.idavaliacaoCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliacaocategoria[ idavaliacaoCategoria=" + idavaliacaoCategoria + " ]";
    }
    
}
