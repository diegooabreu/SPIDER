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
public class CondicaoTabela {
    
    private boolean statusCondicao;
    private int idCondicao;
    private String identificacaoCondicao;
    private String descricaoCondicao;
    //private Date dataOcorrenciaCondicao;

    
    public boolean isStatusCondicao() {
        return statusCondicao;
    }

    public void setStatusCondicao(boolean statusCondicao) {
        this.statusCondicao = statusCondicao;
    }

    public int getIdCondicao() {
        return idCondicao;
    }

    public void setIdCondicao(int idCondicao) {
        this.idCondicao = idCondicao;
    }

    public String getIdentificacaoCondicao() {
        return identificacaoCondicao;
    }

    public void setIdentificacaoCondicao(String identificacaoCondicao) {
        this.identificacaoCondicao = identificacaoCondicao;
    }

    public String getDescricaoCondicao() {
        return descricaoCondicao;
    }

    public void setDescricaoCondicao(String descricaoCondicao) {
        this.descricaoCondicao = descricaoCondicao;
    }

    //public Date getDataOcorrenciaCondicao() {
    //    return dataOcorrenciaCondicao;
    //}

    //public void setDataOcorrenciaCondicao(Date dataOcorrenciaCondicao) {
    //    this.dataOcorrenciaCondicao = dataOcorrenciaCondicao;
    //}
    
    
    
}
