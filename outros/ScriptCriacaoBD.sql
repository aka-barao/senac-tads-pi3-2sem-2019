-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema empresa_tades
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema empresa_tades
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empresa_tades` ;
USE `empresa_tades` ;

-- -----------------------------------------------------
-- Table `empresa_tades`.`categoria_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`categoria_produto` (
  `id_categoria_produto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NULL,
  PRIMARY KEY (`id_categoria_produto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`produto` (
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
    REFERENCES `empresa_tades`.`categoria_produto` (`id_categoria_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`unidade_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`unidade_empresa` (
  `id_unidade_empresa` INT NOT NULL,
  `descricao` VARCHAR(100) NULL,
  `tipo_unidade` INT NOT NULL,
  PRIMARY KEY (`id_unidade_empresa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `data_hora` DATETIME NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  PRIMARY KEY (`id_venda`, `id_unidade_empresa`),
  INDEX `fk_Venda_UnidadeEmpresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_UnidadeEmpresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`pessoa` (
  `id_pessoa` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL DEFAULT 'Valor Não Atribuido',
  `data_nascimento` DATE NULL,
  `cpf` VARCHAR(45) NOT NULL DEFAULT 'Valor Não Atribuido',
  PRIMARY KEY (`id_pessoa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`departamento` (
  `id_departamento` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_departamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`cargo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`cargo` (
  `id_cargo` INT NOT NULL,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_cargo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`funcionario` (
  `id_funcionario` INT NOT NULL,
  `id_pessoa` INT NOT NULL,
  `id_cargo` INT NOT NULL,
  `id_departamento` INT NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  PRIMARY KEY (`id_funcionario`, `id_pessoa`),
  INDEX `fk_Funcionario_Pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  INDEX `fk_Funcionario_Cargo1_idx` (`id_cargo` ASC) VISIBLE,
  INDEX `fk_funcionario_departamento1_idx` (`id_departamento` ASC) VISIBLE,
  INDEX `fk_funcionario_unidade_empresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `empresa_tades`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Cargo1`
    FOREIGN KEY (`id_cargo`)
    REFERENCES `empresa_tades`.`cargo` (`id_cargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_departamento1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `empresa_tades`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_unidade_empresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`cliente` (
  `id_cliente` INT NOT NULL,
  `id_pessoa` INT NOT NULL,
  PRIMARY KEY (`id_cliente`, `id_pessoa`),
  INDEX `fk_Cliente_Pessoa1_idx` (`id_pessoa` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `empresa_tades`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`quantidade_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`quantidade_estoque` (
  `id_produto` INT NOT NULL,
  `id_unidade_empresa` INT NOT NULL,
  `quantidade_estoque` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_produto`, `id_unidade_empresa`),
  INDEX `fk_Produto_has_UnidadeEmpresa_UnidadeEmpresa1_idx` (`id_unidade_empresa` ASC) VISIBLE,
  INDEX `fk_Produto_has_UnidadeEmpresa_Produto1_idx` (`id_produto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_UnidadeEmpresa_Produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `empresa_tades`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_UnidadeEmpresa_UnidadeEmpresa1`
    FOREIGN KEY (`id_unidade_empresa`)
    REFERENCES `empresa_tades`.`unidade_empresa` (`id_unidade_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresa_tades`.`venda_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresa_tades`.`venda_produto` (
  `id_venda` INT NOT NULL,
  `id_produto` INT NOT NULL,
  PRIMARY KEY (`id_venda`, `id_produto`),
  INDEX `fk_Venda_has_Produto_Produto1_idx` (`id_produto` ASC) VISIBLE,
  INDEX `fk_Venda_has_Produto_Venda1_idx` (`id_venda` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_has_Produto_Venda1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `empresa_tades`.`venda` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_Produto_Produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `empresa_tades`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
