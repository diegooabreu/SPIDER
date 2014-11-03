SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema spider_rm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spider_rm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `spider_rm` ;

-- -----------------------------------------------------
-- Table `spider_rm`.`politicaOrganizacional`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`politicaOrganizacional` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`politicaOrganizacional` (
  `idPoliticaOrganizacional` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `politicaOrganizacional` LONGTEXT NULL,
  `caminhoArquivo` VARCHAR(200) NULL,
  `nomeArquivoPolitica` VARCHAR(200) NULL,
  PRIMARY KEY (`idPoliticaOrganizacional`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`organizacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`organizacao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`organizacao` (
  `idOrganizacao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomeOrganizacao` VARCHAR(45) NOT NULL,
  `cnpj` VARCHAR(150) NOT NULL,
  `endereco` LONGTEXT NULL,
  `idPoliticaOrganizacional` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idOrganizacao`),
  INDEX `fk_organizacao_politicaOrganizacional_idx` (`idPoliticaOrganizacional` ASC),
  CONSTRAINT `fk_organizacao_politicaOrganizacional`
    FOREIGN KEY (`idPoliticaOrganizacional`)
    REFERENCES `spider_rm`.`politicaOrganizacional` (`idPoliticaOrganizacional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`categoriaDeRisco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`categoriaDeRisco` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`categoriaDeRisco` (
  `idCategoriaDeRisco` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomeCategoria` VARCHAR(45) NOT NULL,
  `descricaoCategoria` TEXT NULL,
  `possiveisOrigens` TEXT NULL,
  `criterios_para_impacto` TEXT NULL,
  `criterios_para_probabilidade` TEXT NULL,
  `idOrganizacao` INT UNSIGNED NOT NULL,
  `fk_idCategoriaDeRisco` INT UNSIGNED NULL,
  `earOrganizacional` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idCategoriaDeRisco`),
  INDEX `fk_categoriaDeRisco_organizacao1_idx` (`idOrganizacao` ASC),
  INDEX `fk_categoriaDeRisco_categoriaDeRisco1_idx` (`fk_idCategoriaDeRisco` ASC),
  CONSTRAINT `fk_categoriaDeRisco_organizacao1`
    FOREIGN KEY (`idOrganizacao`)
    REFERENCES `spider_rm`.`organizacao` (`idOrganizacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categoriaDeRisco_categoriaDeRisco1`
    FOREIGN KEY (`fk_idCategoriaDeRisco`)
    REFERENCES `spider_rm`.`categoriaDeRisco` (`idCategoriaDeRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`projeto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`projeto` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`projeto` (
  `idProjeto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomeProjeto` VARCHAR(45) NOT NULL,
  `responsavelProjeto` VARCHAR(45) NOT NULL,
  `responsavelGerenciaRiscos` VARCHAR(45) NOT NULL,
  `descricaoProjeto` MEDIUMTEXT NULL,
  `planoDeRisco` LONGTEXT NULL,
  `idOrganizacao` INT UNSIGNED NOT NULL,
  `concluido` TINYINT(1) NOT NULL,
  `caminhoPlanoDeRisco` VARCHAR(200) NULL,
  `nomeArquivoPlanoRisco` VARCHAR(200) NULL,
  PRIMARY KEY (`idProjeto`),
  INDEX `fk_projeto_organizacao1_idx` (`idOrganizacao` ASC),
  CONSTRAINT `fk_projeto_organizacao1`
    FOREIGN KEY (`idOrganizacao`)
    REFERENCES `spider_rm`.`organizacao` (`idOrganizacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`contem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`contem` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`contem` (
  `idProjeto` INT UNSIGNED NOT NULL,
  `idCategoriaDeRisco` INT UNSIGNED NOT NULL,
  INDEX `fk_contem_projeto1_idx` (`idProjeto` ASC),
  PRIMARY KEY (`idProjeto`, `idCategoriaDeRisco`),
  INDEX `fk_contem_categoriaDeRisco1_idx` (`idCategoriaDeRisco` ASC),
  CONSTRAINT `fk_contem_projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `spider_rm`.`projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contem_categoriaDeRisco1`
    FOREIGN KEY (`idCategoriaDeRisco`)
    REFERENCES `spider_rm`.`categoriaDeRisco` (`idCategoriaDeRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`aviso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`aviso` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`aviso` (
  `idAviso` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricaoAviso` TEXT NULL,
  `dataLimite` DATE NULL,
  `idProjeto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idAviso`),
  INDEX `fk_aviso_projeto1_idx` (`idProjeto` ASC),
  CONSTRAINT `fk_aviso_projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `spider_rm`.`projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`avaliacaoProjeto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`avaliacaoProjeto` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`avaliacaoProjeto` (
  `idAvaliacao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pontosFortes` MEDIUMTEXT NULL,
  `pontosFracos` MEDIUMTEXT NULL,
  `oportunidadesMelhoria` MEDIUMTEXT NULL,
  `informacoesAdicionais` MEDIUMTEXT NULL,
  `idProjeto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idAvaliacao`),
  INDEX `fk_avaliacao_projeto1_idx` (`idProjeto` ASC),
  CONSTRAINT `fk_avaliacao_projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `spider_rm`.`projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`pontoDeControle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`pontoDeControle` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`pontoDeControle` (
  `idPontoDeControle` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomePontoDeControle` VARCHAR(45) NOT NULL,
  `dataPontoControle` DATE NOT NULL,
  `descricaoPontoControle` TEXT NULL,
  `idProjeto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPontoDeControle`),
  INDEX `fk_pontoDeControle_projeto1_idx` (`idProjeto` ASC),
  CONSTRAINT `fk_pontoDeControle_projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `spider_rm`.`projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`marcoDoProjeto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`marcoDoProjeto` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`marcoDoProjeto` (
  `idMarcoDoProjeto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomeMarcoDoProjeto` VARCHAR(30) NULL,
  `dataMarcoProjeto` DATE NULL,
  `descricaoMarcoProjeto` VARCHAR(45) NULL,
  `idProjeto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idMarcoDoProjeto`),
  INDEX `fk_marcoDoProjeto_projeto1_idx` (`idProjeto` ASC),
  CONSTRAINT `fk_marcoDoProjeto_projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `spider_rm`.`projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`risco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`risco` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`risco` (
  `idRisco` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dataIdentificacao` DATE NOT NULL,
  `emissor` VARCHAR(45) NOT NULL,
  `probabilidade` INT NOT NULL,
  `impacto` VARCHAR(45) NOT NULL,
  `statusRisco` VARCHAR(45) NOT NULL,
  `descricao` LONGTEXT NOT NULL,
  `prioridade` INT NOT NULL,
  `grauSeveridade` DOUBLE NOT NULL,
  `idProjeto` INT UNSIGNED NOT NULL,
  `idCategoriaDeRisco` INT UNSIGNED NOT NULL,
  `identificacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRisco`),
  INDEX `fk_risco_contem1_idx` (`idProjeto` ASC, `idCategoriaDeRisco` ASC),
  CONSTRAINT `fk_risco_contem1`
    FOREIGN KEY (`idProjeto` , `idCategoriaDeRisco`)
    REFERENCES `spider_rm`.`contem` (`idProjeto` , `idCategoriaDeRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`historicoAlteracao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`historicoAlteracao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`historicoAlteracao` (
  `idHistoricoAlteracao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricaoAlteracao` TEXT NULL,
  `dataAlteracao` DATE NULL,
  `idRisco` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idHistoricoAlteracao`),
  INDEX `fk_historicoAlteracao_risco1_idx` (`idRisco` ASC),
  CONSTRAINT `fk_historicoAlteracao_risco1`
    FOREIGN KEY (`idRisco`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`planoMitigacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`planoMitigacao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`planoMitigacao` (
  `idPlanoMitigacao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `identificacaoPlanoMitigacao` VARCHAR(45) NOT NULL,
  `descricaoPlanoMitigacao` TEXT NULL,
  `responsavel` VARCHAR(45) NOT NULL,
  `comoRealizar` TEXT NOT NULL,
  `informacoesAdicionais` TEXT NULL,
  `dataRealizacao` DATE NULL,
  `idRisco` INT UNSIGNED NOT NULL,
  `idMarcoDoProjeto` INT UNSIGNED NULL,
  `idPontoDeControle` INT UNSIGNED NULL,
  PRIMARY KEY (`idPlanoMitigacao`),
  INDEX `fk_planoMitigacao_risco1_idx` (`idRisco` ASC),
  INDEX `fk_planoMitigacao_marcoDoProjeto1_idx` (`idMarcoDoProjeto` ASC),
  INDEX `fk_planoMitigacao_pontoDeControle1_idx` (`idPontoDeControle` ASC),
  CONSTRAINT `fk_planoMitigacao_risco1`
    FOREIGN KEY (`idRisco`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planoMitigacao_marcoDoProjeto1`
    FOREIGN KEY (`idMarcoDoProjeto`)
    REFERENCES `spider_rm`.`marcoDoProjeto` (`idMarcoDoProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planoMitigacao_pontoDeControle1`
    FOREIGN KEY (`idPontoDeControle`)
    REFERENCES `spider_rm`.`pontoDeControle` (`idPontoDeControle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`planoContingencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`planoContingencia` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`planoContingencia` (
  `idPlanoContingencia` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `identificacaoPlanoContingencia` VARCHAR(45) NOT NULL,
  `descricaoPlanoContingencia` TEXT NULL,
  `responsavel` VARCHAR(45) NOT NULL,
  `comoRealizar` TEXT NOT NULL,
  `informacoesAdicionais` TEXT NULL,
  `dataRealizacao` DATE NULL,
  `idRisco` INT UNSIGNED NOT NULL,
  `idMarcoDoProjeto` INT UNSIGNED NULL,
  `idPontoDeControle` INT UNSIGNED NULL,
  PRIMARY KEY (`idPlanoContingencia`),
  INDEX `fk_planoContingencia_risco1_idx` (`idRisco` ASC),
  INDEX `fk_planoContingencia_marcoDoProjeto1_idx` (`idMarcoDoProjeto` ASC),
  INDEX `fk_planoContingencia_pontoDeControle1_idx` (`idPontoDeControle` ASC),
  CONSTRAINT `fk_planoContingencia_risco1`
    FOREIGN KEY (`idRisco`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planoContingencia_marcoDoProjeto1`
    FOREIGN KEY (`idMarcoDoProjeto`)
    REFERENCES `spider_rm`.`marcoDoProjeto` (`idMarcoDoProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planoContingencia_pontoDeControle1`
    FOREIGN KEY (`idPontoDeControle`)
    REFERENCES `spider_rm`.`pontoDeControle` (`idPontoDeControle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`historicoRisco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`historicoRisco` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`historicoRisco` (
  `idHistoricoRisco` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dataOcorrencia` DATE NOT NULL,
  `idRisco` INT UNSIGNED NOT NULL,
  `statusRisco` VARCHAR(45) NULL,
  PRIMARY KEY (`idHistoricoRisco`),
  INDEX `fk_historicoRisco_risco1_idx` (`idRisco` ASC),
  CONSTRAINT `fk_historicoRisco_risco1`
    FOREIGN KEY (`idRisco`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`subcondicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`subcondicao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`subcondicao` (
  `idSubcondicao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `identificacaoSubcondicao` VARCHAR(45) NOT NULL,
  `descricaoSubcondicao` TEXT NOT NULL,
  `statusSubcondicao` VARCHAR(45) NOT NULL,
  `idRisco` INT UNSIGNED NOT NULL,
  `dataOcorrencia` DATE NULL,
  PRIMARY KEY (`idSubcondicao`),
  INDEX `fk_subcondicao_risco1_idx` (`idRisco` ASC),
  CONSTRAINT `fk_subcondicao_risco1`
    FOREIGN KEY (`idRisco`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`ocorrenciaSubcondicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`ocorrenciaSubcondicao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`ocorrenciaSubcondicao` (
  `idHistoricoRisco` INT UNSIGNED NOT NULL,
  `idSubcondicao` INT UNSIGNED NOT NULL,
  INDEX `fk_ocorrenciaSubcondicao_historicoRisco1_idx` (`idHistoricoRisco` ASC),
  INDEX `fk_ocorrenciaSubcondicao_subcondicao1_idx` (`idSubcondicao` ASC),
  PRIMARY KEY (`idHistoricoRisco`, `idSubcondicao`),
  CONSTRAINT `fk_ocorrenciaSubcondicao_historicoRisco1`
    FOREIGN KEY (`idHistoricoRisco`)
    REFERENCES `spider_rm`.`historicoRisco` (`idHistoricoRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocorrenciaSubcondicao_subcondicao1`
    FOREIGN KEY (`idSubcondicao`)
    REFERENCES `spider_rm`.`subcondicao` (`idSubcondicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`avaliacaoCategoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`avaliacaoCategoria` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`avaliacaoCategoria` (
  `idavaliacaoCategoria` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pontosFortes` VARCHAR(45) NULL,
  `pontosFracos` TEXT NULL,
  `oportunidadesMelhoria` TEXT NULL,
  `informacoesAdicionais` TEXT NULL,
  `idCategoriaDeRisco` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idavaliacaoCategoria`),
  UNIQUE INDEX `idavaliacaoCategoria_UNIQUE` (`idavaliacaoCategoria` ASC),
  INDEX `fk_avaliacaoCategoria_categoriaDeRisco1_idx` (`idCategoriaDeRisco` ASC),
  CONSTRAINT `fk_avaliacaoCategoria_categoriaDeRisco1`
    FOREIGN KEY (`idCategoriaDeRisco`)
    REFERENCES `spider_rm`.`categoriaDeRisco` (`idCategoriaDeRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`relacaoEntreRiscos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`relacaoEntreRiscos` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`relacaoEntreRiscos` (
  `idRelacaoEntreRiscoscol` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idRiscoInfluenciador` INT UNSIGNED NOT NULL,
  `idRiscoInfluenciado` INT UNSIGNED NOT NULL,
  INDEX `fk_relacaoEntreRiscos_risco2_idx` (`idRiscoInfluenciado` ASC),
  PRIMARY KEY (`idRelacaoEntreRiscoscol`),
  CONSTRAINT `fk_relacaoEntreRiscos_risco1`
    FOREIGN KEY (`idRiscoInfluenciador`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relacaoEntreRiscos_risco2`
    FOREIGN KEY (`idRiscoInfluenciado`)
    REFERENCES `spider_rm`.`risco` (`idRisco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spider_rm`.`grupoRelacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spider_rm`.`grupoRelacao` ;

CREATE TABLE IF NOT EXISTS `spider_rm`.`grupoRelacao` (
  `idGrupo` INT NOT NULL AUTO_INCREMENT,
  `idSubcondicao1` INT NULL,
  `idSubcondicao2` INT NULL,
  `idRelacao1` INT NULL,
  `idRelacao2` INT NULL,
  `relacao` VARCHAR(45) NULL,
  PRIMARY KEY (`idGrupo`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


# MODELO DE BANCO DE DADOS INICIAL

use spider_rm;

# Inserindo politica inicial em branco
insert into politicaOrganizacional(politicaOrganizacional) values("Política Organizacional da empresa");

#Inserindo organizacao
insert into organizacao(nomeOrganizacao,cnpj,endereco,idPoliticaOrganizacional) values("UFPA","724567","Rua Augusto Corrêa",1);

#inserindo categorias de risco
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional) values("Técnico", 1 , true);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional) values("Externo", 1 , true);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional) values("Organizacional", 1 , true);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional) values("Gerenciamento de Projetos", 1 , true);

insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Requisitos", 1 , true, 1);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Tecnologia", 1 , true, 1);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Complexidade e Interfaces", 1 , true, 1);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Desempenhos e Confiabilidade", 1 , true, 1);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Qualidade", 1 , true, 1);

insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Subcontratadas e Fornecedores", 1 , true, 2);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Regulamentos", 1 , true, 2);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Mercado", 1 , true, 2);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Cliente", 1 , true, 2);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Clima", 1 , true, 2);

insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Dependências do Projeto", 1 , true, 3);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Recursos", 1 , true, 3);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Financiamento", 1 , true, 3);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Priorização", 1 , true, 3);

insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Estimativa", 1 , true, 4);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Planejamento", 1 , true, 4);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Controle", 1 , true, 4);
insert into categoriaderisco(nomeCategoria, idOrganizacao, earOrganizacional, fk_idCategoriaDeRisco) values("Comunicação", 1 , true, 4);