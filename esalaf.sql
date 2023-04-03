-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 03 avr. 2023 à 14:57
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `esalaf`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prenom` varchar(80) NOT NULL,
  `telephone` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `telephone`) VALUES
(9, 'Ibrahim', 'Mohammed', '0734568284'),
(10, 'El barrodi', 'Salim', '0543218765'),
(11, 'Oubrahim', 'Naima', '0609098288'),
(12, 'Ibrahim', 'Azzedinne', '0640089591'),
(13, 'Ait erradi', 'Morad', '0687980023'),
(14, 'Marrodi', 'Maria', '0678900023'),
(15, 'Khatib', 'Youssef', '0678231456'),
(16, 'Ennaji', 'Kawthar', '0534218765'),
(17, 'Sabiri', 'Abdallah', '0666909966'),
(18, 'Ibrahim', 'ElHassane', '0689004235'),
(19, 'Ibrahim', 'Manal', '0603256464');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `client` varchar(80) NOT NULL,
  `produits` varchar(600) NOT NULL,
  `prixtotal` float NOT NULL,
  `date` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `client`, `produits`, `prixtotal`, `date`) VALUES
(2, 'Azzedine Ibrahim', 'tomate\nfromage\noeufs\nlimonade', 40, '01/01/2023'),
(3, 'Kawthar Ennaji', 'Kingcokies\nraibi', 4.5, '02/03/2023'),
(4, 'Youssef Khatib', 'dun up\nraibi', 6, '03/31/2023'),
(5, 'El hassane Ibrahim', 'pain\ncola ice\njus d\'orange Abtal\nLait\nthon tomate', 70, '02/04/2023'),
(7, 'Mohamed Ibrahim', 'Pain au chocolat\nHuile\nSucre\nFarine\nBanane\nOrange', 80, '31/03/2023'),
(8, 'Fatima Alami', 'oeufs\nriz\nlait', 26, '02/09/2022');

-- --------------------------------------------------------

--
-- Structure de la table `credit`
--

CREATE TABLE `credit` (
  `id` int(11) NOT NULL,
  `client` varchar(80) NOT NULL,
  `prix` float NOT NULL,
  `datecredit` varchar(80) NOT NULL,
  `datefin` varchar(80) NOT NULL,
  `etat` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `credit`
--

INSERT INTO `credit` (`id`, `client`, `prix`, `datecredit`, `datefin`, `etat`) VALUES
(1, 'Manal Ibrahim', 70, '04/01/2023', '15/01/2023', 'En cours'),
(4, 'Azzedinne Ibrahim', 300, '02/03/2023', '15/04/2023', 'En cours'),
(5, 'Kawthar Ennaji', 900, '03/03/2023', '03/05/2023', ' remboursé');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(80) NOT NULL,
  `quantitestock` int(11) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `nom`, `quantitestock`, `prix`) VALUES
(2, 'Lait', 30, 4),
(3, 'oeufs', 80, 2),
(4, 'Fromage rouge', 30, 30.5),
(5, 'Huile 1L', 35, 35),
(6, 'Sucre 1kg', 90, 20);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `credit`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `credit`
--
ALTER TABLE `credit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
