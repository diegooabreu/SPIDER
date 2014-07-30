/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.tabelas;

import java.util.Date;
import org.omg.CORBA.DATA_CONVERSION;

/**
 *
 * @author Diogo
 */
public class PlanosPendentesTabela {
    
    private boolean realizado;
    private String identificacaoPlano;
    private Date dataLimite;
    private String idPlanoDeMitigacao;

    /**
     * @return the realizado
     */
    public boolean isRealizado() {
        return realizado;
    }

    /**
     * @param realizado the realizado to set
     */
    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    /**
     * @return the identificacaoPlano
     */
    public String getIdentificacaoPlano() {
        return identificacaoPlano;
    }

    /**
     * @param identificacaoPlano the identificacaoPlano to set
     */
    public void setIdentificacaoPlano(String identificacaoPlano) {
        this.identificacaoPlano = identificacaoPlano;
    }

    /**
     * @return the dataLimite
     */
    public Date getDataLimite() {
        return dataLimite;
    }

    /**
     * @param dataLimite the dataLimite to set
     */
    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    /**
     * @return the idPlanoDeMitigacao
     */
    public String getIdPlanoDeMitigacao() {
        return idPlanoDeMitigacao;
    }

    /**
     * @param idPlanoDeMitigacao the idPlanoDeMitigacao to set
     */
    public void setIdPlanoDeMitigacao(String idPlanoDeMitigacao) {
        this.idPlanoDeMitigacao = idPlanoDeMitigacao;
    }
    
    
}
