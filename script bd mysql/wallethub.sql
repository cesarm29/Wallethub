-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2018 a las 04:43:43
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wallethub`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `load_log`
--

CREATE TABLE `load_log` (
  `id` bigint(22) NOT NULL,
  `datetime` datetime NOT NULL,
  `ip` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `action` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `state` int(11) NOT NULL,
  `navigator` text COLLATE latin1_spanish_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log_search`
--

CREATE TABLE `log_search` (
  `id` int(11) NOT NULL,
  `ip` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `comment` text COLLATE latin1_spanish_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `load_log`
--
ALTER TABLE `load_log`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `log_search`
--
ALTER TABLE `log_search`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `load_log`
--
ALTER TABLE `load_log`
  MODIFY `id` bigint(22) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `log_search`
--
ALTER TABLE `log_search`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
