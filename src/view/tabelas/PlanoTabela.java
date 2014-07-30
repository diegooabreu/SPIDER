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
public class PlanoTabela {
    
    private boolean realizado;
    
    private int idPlano;
    private String identificacaoPlano;
    private String descricaoPlano;
    private String responsavel;
    
    private Date dataLimite;
    private String identificacaoRisco;
            
    private String Tipo;    
    private Date dataRealizacao;

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getIdentificacaoRisco() {
        return identificacaoRisco;
    }

    public void setIdentificacaoRisco(String identificacaoRisco) {
        this.identificacaoRisco = identificacaoRisco;
    }

    
    
    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getIdentificacaoPlano() {
        return identificacaoPlano;
    }

    public void setIdentificacaoPlano(String identificacaoPlano) {
        this.identificacaoPlano = identificacaoPlano;
    }

    public String getDescricaoPlano() {
        return descricaoPlano;
    }

    public void setDescricaoPlano(String descricaoPlano) {
        this.descricaoPlano = descricaoPlano;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    
    
    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }
    
    
    
    
}
