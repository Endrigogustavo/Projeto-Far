-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11-Set-2023 às 19:34
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pharcom`
--

CREATE DATABASE `pharcom`;


USE `pharcom`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `Id _Categoria` int(11) NOT NULL,
  `Descrição` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `Id _Cliente` int(11) NOT NULL,
  `Nome` varchar(200) NOT NULL,
  `Data de Nascimento` date NOT NULL,
  `Usuário` varchar(300) NOT NULL,
  `Senha` varchar(40) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `CPF` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `controle`
--

CREATE TABLE `controle` (
  `ID_Func` int(11) NOT NULL,
  `ID_Remedio` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionário`
--

CREATE TABLE `funcionário` (
  `ID_Func` int(11) NOT NULL,
  `Nome_Func` varchar(50) NOT NULL,
  `Usuário` varchar(50) NOT NULL,
  `Nivel_ID` int(11) NOT NULL,
  `Senha` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Data_Nasc` date NOT NULL,
  `CPF` varchar(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `nível-restrição`
--

CREATE TABLE `nível-restrição` (
  `Nivel_ID` int(11) NOT NULL,
  `Descrição` varchar(80) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `registro_compra`
--

CREATE TABLE `registro_compra` (
  `Id _Remédio` int(11) NOT NULL,
  `Id _Cliente` int(11) NOT NULL,
  `Data da compra` date NOT NULL,
  `Quantidade` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `remedio`
--

CREATE TABLE `remedio` (
  `Id_Rem` int(11) NOT NULL,
  `Nome_Rem` varchar(200) NOT NULL,
  `Categoria_Num` int(11) NOT NULL,
  `Preço` float NOT NULL,
  `Estoque` int(11) NOT NULL,
  `Descrição` varchar(80) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Id _Categoria`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id _Cliente`);

--
-- Índices para tabela `funcionário`
--
ALTER TABLE `funcionário`
  ADD PRIMARY KEY (`ID_Func`);

--
-- Índices para tabela `nível-restrição`
--
ALTER TABLE `nível-restrição`
  ADD PRIMARY KEY (`Nivel_ID`);

--
-- Índices para tabela `remedio`
--
ALTER TABLE `remedio`
  ADD PRIMARY KEY (`Id_Rem`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `Id _Categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Id _Cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcionário`
--
ALTER TABLE `funcionário`
  MODIFY `ID_Func` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `nível-restrição`
--
ALTER TABLE `nível-restrição`
  MODIFY `Nivel_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `remedio`
--
ALTER TABLE `remedio`
  MODIFY `Id_Rem` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
