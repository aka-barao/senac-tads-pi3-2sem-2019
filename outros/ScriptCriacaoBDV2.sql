-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema empresa_tades_v2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema empresa_tades_v2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empresa_tades_v2` ;
USE `empresa_tades_v2` ;

-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`categoria_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`categoria_produto` (
  `id_categoria_produto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NULL,
  PRIMARY KEY (`id_categoria_produto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL DEFAULT 0,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(200) NULL,
  `id_categoria_produto` INT NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id_produto`, `id_categoria_produto`),
  UNIQUE INDEX `idProduto_UNIQUE` (`id_produto` ASC) VISIBLE,
  INDEX `fk_Produto_CategoriaProduto1_idx` (`id_categoria_produto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_CategoriaProduto1`
    FOREIGN KEY (`id_categoria_produto`)
    REFERENCES `empresa_tades_v2`.`categoria_produto` (`id_categoria_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`unidade_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`unidade_empresa` (
  `id_unidade_empresa` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NULL,
  `tipo_unidade` INT NOT NULL,
  PRIMARY KEY (`id_unidade_empresa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `data_hora` DATETIME NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  PRIMARY KEY (`id_venda`, `id_unidade_empresa`),
  INDEX `fk_Venda_UnidadeEmpresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_UnidadeEmpresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades_v2`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`departamento` (
  `id_departamento` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_departamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`cargo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`cargo` (
  `id_cargo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_cargo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`funcionario` (
  `id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `id_cargo` INT NOT NULL,
  `id_departamento` INT NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL DEFAULT 'Nome não definido',
  `data_nascimento` DATE NULL,
  `cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  INDEX `fk_Funcionario_Cargo1_idx` (`id_cargo` ASC) VISIBLE,
  INDEX `fk_funcionario_departamento1_idx` (`id_departamento` ASC) VISIBLE,
  INDEX `fk_funcionario_unidade_empresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Cargo1`
    FOREIGN KEY (`id_cargo`)
    REFERENCES `empresa_tades_v2`.`cargo` (`id_cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_departamento1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `empresa_tades_v2`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_unidade_empresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades_v2`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL DEFAULT 'Nome não definido',
  `data_nascimento` DATE NULL,
  `cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`quantidade_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`quantidade_estoque` (
  `id_produto` INT NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  `quantidade_estoque` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_produto`, `id_unidade_empresa`),
  INDEX `fk_Produto_has_UnidadeEmpresa_UnidadeEmpresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  INDEX `fk_Produto_has_UnidadeEmpresa_Produto1_idx` (`id_produto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_UnidadeEmpresa_Produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `empresa_tades_v2`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_UnidadeEmpresa_UnidadeEmpresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades_v2`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`venda_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`venda_produto` (
  `id_venda` INT NOT NULL,
  `id_produto` INT NOT NULL,
  PRIMARY KEY (`id_venda`, `id_produto`),
  INDEX `fk_Venda_has_Produto_Produto1_idx` (`id_produto` ASC) VISIBLE,
  INDEX `fk_Venda_has_Produto_Venda1_idx` (`id_venda` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_has_Produto_Venda1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `empresa_tades_v2`.`venda` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_Produto_Produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `empresa_tades_v2`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades_v2`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades_v2`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `id_funcionario` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_usuario_funcionario1_idx` (`id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_funcionario1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `empresa_tades_v2`.`funcionario` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
