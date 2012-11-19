-- phpMyAdmin SQL Dump
-- version 2.10.0.2
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generatie Tijd: 22 Oct 2007 om 23:49
-- Server versie: 5.0.41
-- PHP Versie: 5.2.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `outfit4you`
-- 

-- --------------------------------------------------------

-- 
-- Tabel structuur voor tabel `categorie`
-- 

CREATE TABLE `categorie` (
  `categorie_id` int(11) NOT NULL auto_increment,
  `naam` varchar(50) NOT NULL,
  `omschrijving` text NOT NULL,
  PRIMARY KEY  (`categorie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- 
-- Gegevens worden uitgevoerd voor tabel `categorie`
-- 

INSERT INTO `categorie` (`categorie_id`, `naam`, `omschrijving`) VALUES 
(1, 'T-shirts', 'Mooie t-shirts in de aanbieding'),
(2, 'Broeken', 'Mooie broeken vanaf 3,-'),
(3, 'Strings', 'int.toString()');

-- --------------------------------------------------------

-- 
-- Tabel structuur voor tabel `order`
-- 

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL auto_increment,
  `naam` varchar(50) NOT NULL,
  `adres` varchar(50) NOT NULL,
  `postcode` varchar(7) NOT NULL,
  `woonplaats` varchar(20) NOT NULL,
  `notes` text NOT NULL,
  `betaalmethode` varchar(20) NOT NULL,
  `datum` date NOT NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------

-- 
-- Tabel structuur voor tabel `orderregel`
-- 

CREATE TABLE `orderregel` (
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `aantal` tinyint(5) NOT NULL,
  KEY `product_id` (`product_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Gegevens worden uitgevoerd voor tabel `orderregel`
-- 


-- --------------------------------------------------------

-- 
-- Tabel structuur voor tabel `product`
-- 

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL auto_increment,
  `categorie_id` int(11) NOT NULL,
  `naam` varchar(50) NOT NULL,
  `prijs` double NOT NULL,
  `omschrijving` text NOT NULL,
  PRIMARY KEY  (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- 
-- Gegevens worden uitgevoerd voor tabel `product`
-- 

INSERT INTO `product` (`product_id`, `categorie_id`, `naam`, `prijs`, `omschrijving`) VALUES 
(1, 1, 'Een Puma shirt', 5, 'Jaa, een echte puma'),
(2, 1, 'Paradise', 30, 'Een mooi paradise t-shirt'),
(3, 2, 'Cars', 4.25, 'Een handige Cars tuinbroek voor in de tuin!'),
(4, 2, 'Rode driekwart zomerbroek', 25.65, 'super fancy driekwart broek'),
(5, 2, 'Gele driekwart zomerbroek', 13, 'Met deze gele broek ben jij het echt helemaal deze zomer!'),
(6, 2, 'Paarse zwembroek', 3, 'Een paarse zwembroek '),
(8, 3, 'product.toString()', 9, 'Het resultaat van een geselecteerd product voor een kleine prijs!.'),
(9, 3, 'String naam = (String) prouct.getProductName();', 2.25, 'De productnaam'),
(10, 2, 'Heren Onderbroek', 2.5, 'Onderbroeken in setjes van 3, in de kleuren Geel, oranje en bruin.'),
(11, 2, 'Dames onderbroek', 3.5, 'Onderbroeken in setjes van 5, in de kleuren zwart, goud en zilver'),
(12, 2, 'Heren spijkerbroek ', 7.9, 'In de kleuren blauw, grijs en geel verkrijgbaar!'),
(13, 2, 'Dames spijkerbroek', 8.9, 'Afgewerkt met zilveren strips aan de achterkant');