/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controller.AvaliacaoprojetoJpaController;
import javax.persistence.NoResultException;
import model.Avaliacaoprojeto;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class AvaliacaoProjetoFacade {

    public Avaliacaoprojeto buscaAvaliacao(Projeto projeto) {
        AvaliacaoprojetoJpaController avaliacaoProjetoJpaController = new AvaliacaoprojetoJpaController();

        return avaliacaoProjetoJpaController.getAvaliacaoByIdProjeto(projeto);
    }

    public void alterarAvaliacao(Avaliacaoprojeto avaliacaoProjeto) {
        AvaliacaoprojetoJpaController avaliacaoProjetoJpaController = new AvaliacaoprojetoJpaController();

        try {
            avaliacaoProjetoJpaController.edit(avaliacaoProjeto);
        } catch (Exception e) {
            System.out.println("Erro no método alterarAvaliacao da classe AvaliacaoProjetoFacade");
            e.printStackTrace();
        }
    }

    public void criarAvaliacao(Avaliacaoprojeto avaliacaoProjeto) {
        AvaliacaoprojetoJpaController avaliacaoProjetoJpaController = new AvaliacaoprojetoJpaController();

        avaliacaoProjetoJpaController.create(avaliacaoProjeto);
    }

    public Avaliacaoprojeto buscaAvaliacaoByIdProjeto(int idProjeto) {
        Avaliacaoprojeto avaliacaoProjeto = new Avaliacaoprojeto();
        AvaliacaoprojetoJpaController avaliacaoProjetoJpaController = new AvaliacaoprojetoJpaController();
        ProjetoFacade projetoFacade = new ProjetoFacade();
        try {
            avaliacaoProjeto = avaliacaoProjetoJpaController.getAvaliacaoByIdProjeto(projetoFacade.getProjeto(idProjeto));
        } catch (NoResultException e) {
            return new Avaliacaoprojeto();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return avaliacaoProjeto;
    }

}
