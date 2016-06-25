-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 05 Juin 2016 à 13:13
-- Version du serveur :  10.1.9-MariaDB
-- Version de PHP :  5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestionnote`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateur`
--

INSERT INTO `administrateur` (`id`, `login`, `password`) VALUES
(16, 'mahdi', 'mmmm'),
(17, 'mahdikallel', 'fmtXLqgNkrY='),
(18, 'mahdi32', 'hGNLLNoOlA==');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `dateNaiss` date NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `cin` int(8) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `enseignant`
--

INSERT INTO `enseignant` (`id`, `nom`, `prenom`, `dateNaiss`, `adresse`, `cin`, `tel`, `login`, `password`) VALUES
(14, 'Nabil', 'Moalla', '1992-06-24', 'manzel ', 15566699, '21105555', 'moalla1', 'fVYxF8Inzb6plFmyiM+wKA=='),
(23, 'Ameur', 'Moalla', '1992-06-24', 'manzel ', 15566699, '21105555', 'moalla2', 'napil'),
(25, 'Ahmed', 'Abdelhedi', '1991-06-12', 'Gremda', 1556669, '2558877744', 'ahmed', 'ahmed'),
(26, 'REA', 'Samir', '2011-03-10', 'zef', 38138223, '78627', 'mahdik', 'gitf720f'),
(27, 'mahdi', 'kallel', '1992-04-10', 'Sfax', 11016581, '21104577', 'mahdikallel', 'fmtXLqgNkrY='),
(29, 'Taher', 'mehdoui', '1992-12-10', 'Sousse', 100025, '598498', 'SDKJF', 'eDIwOrU+r5+pb4e5'),
(30, 'mahdi', 'makni', '1596-11-11', 'Mp', 52222222, '3666666', 'mah', 'hGNL'),
(31, 'jhigi', 'bkh', '1992-10-10', '75275', 75855758, '753775', 'moalla25', 'fmtXLqgNkrY='),
(32, 'test', 'test', '1922-10-10', 'sdlkf', 5998, '26655656', 'testP', 'i2dWPA=='),
(33, 'Samir', 'dsklfjg', '1929-10-10', 'ldskhfg', 1561165, 'djshg', 'test32', 'i2dWPKQN');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `dateNaiss` date NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `cin` int(8) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `idGroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `dateNaiss`, `adresse`, `cin`, `tel`, `login`, `password`, `idGroup`) VALUES
(15, 'Ahmed', 'Abdelhedi', '2016-06-09', 'dER', 5265, '265655', 'AHMED', 'STQV', 5),
(16, 'm', 'm', '1922-10-10', 'Mahdi', 255665, '625565', 'mahdikallel', 'fmtXLqgNkrY=', 2),
(17, 'testEtud', 'testEtud', '1922-10-10', 'Mahdi', 6666, '265656', 'testE', 'i2dWPA==', 2),
(18, 'Tamer', 'dskfjg', '1992-12-10', 'sdlkjhg', 2656, 'kdsjhg', 'test23', 'i2dWPKMO', 2),
(19, 'g', 'g', '1999-12-10', 'dfg', 49, 'fgdg', 'mmm', 'hG9QNQ==', 2);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `id` int(11) NOT NULL,
  `nomGourpe` varchar(50) NOT NULL,
  `abreviation` varchar(50) NOT NULL,
  `idNiveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`id`, `nomGourpe`, `abreviation`, `idNiveau`) VALUES
(1, 'Admin reseau', 'ARS', 327),
(2, 'Genie Procede', 'GP', 328),
(5, 'Genie Informatique', 'GI', 328);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `volumeCour` float NOT NULL,
  `VolumeTp` float NOT NULL,
  `volumeTd` float NOT NULL,
  `coefficient` float NOT NULL,
  `credit` int(11) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `idGroupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`id`, `libelle`, `volumeCour`, `VolumeTp`, `volumeTd`, `coefficient`, `credit`, `idEnseignant`, `idGroupe`) VALUES
(1, 'Math', 40, 0, 25, 3, 6, 23, 2),
(5, 'EJ', 50, 25, 25, 3, 6, 25, 1),
(9, 'IPSec', 60, 15, 15, 4, 6, 14, 1),
(10, 'EJB  2.0 ', 20, 20, 20, 3, 6, 23, 1),
(11, 'Corba', 40, 15, 15, 3, 6, 23, 1),
(12, 'Base de DonnÃ©e', 15, 15, 15, 3, 4, 23, 5),
(13, 'Sience', 15, 15, 15, 2, 4, 32, 1),
(14, 'SAS', 30, 20, 20, 20, 20, 32, 1);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `nbrGrp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `niveau`
--

INSERT INTO `niveau` (`id`, `libelle`, `nbrGrp`) VALUES
(327, '2 eme Annee', NULL),
(328, '3 eme anne', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `noteTP` float DEFAULT NULL,
  `noteDS` float DEFAULT NULL,
  `noteExam` float DEFAULT NULL,
  `notePresence` float DEFAULT NULL,
  `numCompostage` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `idSession` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`id`, `noteTP`, `noteDS`, `noteExam`, `notePresence`, `numCompostage`, `idEtudiant`, `idSession`, `idMatiere`) VALUES
(124, 0, 0, 0, 0, 15983705, 15, 3, 5),
(125, 0, 0, 0, 0, 151115611, 15, 3, 11),
(126, 0, 0, 0, 0, 16708501, 16, 3, 1),
(127, 0, 0, 0, 0, 17325391, 17, 3, 1),
(128, 0, 0, 0, 0, 16428001, 16, 1, 1),
(129, 0, 0, 0, 0, 17342281, 17, 1, 1),
(130, 0, 0, 0, 0, 18905371, 18, 3, 1),
(131, 0, 0, 0, 0, 157046012, 15, 3, 12),
(132, 0, 0, 0, 0, 19733451, 19, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `session`
--

INSERT INTO `session` (`id`, `libelle`) VALUES
(1, 'Controle'),
(3, 'Principale');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idGroup` (`idGroup`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idNiveau` (`idNiveau`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idGroupe` (`idGroupe`),
  ADD KEY `idEnseignant` (`idEnseignant`) USING BTREE;

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idSession` (`idSession`),
  ADD KEY `idMatiere` (`idMatiere`),
  ADD KEY `idEtudiant` (`idEtudiant`) USING BTREE;

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=329;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;
--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`idGroup`) REFERENCES `groupe` (`id`);

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_2` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matiere_ibfk_3` FOREIGN KEY (`idGroupe`) REFERENCES `groupe` (`id`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`idSession`) REFERENCES `session` (`id`),
  ADD CONSTRAINT `note_ibfk_3` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
