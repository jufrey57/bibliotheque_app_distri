-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le :  jeu. 25 oct. 2018 à 16:40
-- Version du serveur :  10.3.9-MariaDB
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bibliothequedb`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
CREATE TABLE IF NOT EXISTS `auteur` (
  `ID_auteur` varchar(60) COLLATE utf8_bin NOT NULL,
  `Nom` varchar(35) COLLATE utf8_bin NOT NULL,
  `Prenom` varchar(35) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_auteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `ID_emprunt` varchar(60) COLLATE utf8_bin NOT NULL,
  `Date_debut` int(11) NOT NULL,
  `Date_fin` int(11) NOT NULL,
  `ID_Usager` varchar(60) COLLATE utf8_bin NOT NULL,
  `ID_exemplaire` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_emprunt`),
  KEY `usager` (`ID_Usager`),
  KEY `exemplaire` (`ID_exemplaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
CREATE TABLE IF NOT EXISTS `exemplaire` (
  `ID_exemplaire` varchar(60) COLLATE utf8_bin NOT NULL,
  `Vices` text COLLATE utf8_bin NOT NULL,
  `Date_acquisition` date NOT NULL,
  `ID_oeuvre` varchar(60) COLLATE utf8_bin NOT NULL,
  `ID_emprunt` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_exemplaire`),
  KEY `oeuvre` (`ID_oeuvre`),
  KEY `emprunt` (`ID_emprunt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `oeuvre`
--

DROP TABLE IF EXISTS `oeuvre`;
CREATE TABLE IF NOT EXISTS `oeuvre` (
  `ID_oeuvre` varchar(60) COLLATE utf8_bin NOT NULL,
  `Nom` varchar(255) COLLATE utf8_bin NOT NULL,
  `Auteur` varchar(60) COLLATE utf8_bin NOT NULL,
  `NbrResa` int(11) NOT NULL,
  PRIMARY KEY (`ID_oeuvre`),
  KEY `auteur.ID_auteur` (`Auteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `ID_reservation` varchar(60) COLLATE utf8_bin NOT NULL,
  `DateDemande` date NOT NULL,
  `ID_Oeuvre` varchar(60) COLLATE utf8_bin NOT NULL,
  `ID_Usager` varchar(60) COLLATE utf8_bin NOT NULL,
  `Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_reservation`),
  KEY `oeuvre.ID_oeuvre` (`ID_Oeuvre`) USING BTREE,
  KEY `usager.ID_Usager` (`ID_Usager`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `usager`
--

DROP TABLE IF EXISTS `usager`;
CREATE TABLE IF NOT EXISTS `usager` (
  `ID_usager` varchar(60) COLLATE utf8_bin NOT NULL,
  `Nom` varchar(35) COLLATE utf8_bin NOT NULL,
  `Prenom` varchar(35) COLLATE utf8_bin NOT NULL,
  `Age` int(11) NOT NULL,
  `Mail` varchar(50) COLLATE utf8_bin NOT NULL,
  `Abonnement_valide` tinyint(1) NOT NULL,
  `retards` int(11) NOT NULL,
  PRIMARY KEY (`ID_usager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `oeuvre`
--
ALTER TABLE `oeuvre`
  ADD CONSTRAINT `FK_ID_AUTEUR` FOREIGN KEY (`Auteur`) REFERENCES `auteur` (`ID_auteur`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_ID_OEUVRE` FOREIGN KEY (`ID_Oeuvre`) REFERENCES `oeuvre` (`ID_oeuvre`),
  ADD CONSTRAINT `FK_ID_USAGER` FOREIGN KEY (`ID_Usager`) REFERENCES `usager` (`ID_usager`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
