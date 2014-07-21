/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.tabelas;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class RiscoTabela {
    
    private boolean monitorar;
    private Integer idRisco;
    private Date dataIdentificacao;
    private String emissor;
    private int probabilidade;
    private String impacto;
    private String statusRisco;
    private String descricao;
    private int prioridade;
    private double grauSeveridade;
    private String identificacao;

    public boolean isMonitorar() {
        return monitorar;
    }

    public void setMonitorar(boolean monitorar) {
        this.monitorar = monitorar;
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

    public double getGrauSeveridade() {
        return grauSeveridade;
    }

    public void setGrauSeveridade(double grauSeveridade) {
        this.grauSeveridade = grauSeveridade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    
    
    
}
