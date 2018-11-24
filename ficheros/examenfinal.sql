-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2017 a las 16:17:52
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `examenfinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `id` int(11) NOT NULL,
  `nombre` char(40) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`id`, `nombre`) VALUES
(1, 'Autor UNO'),
(2, 'Autor DOS'),
(3, 'Autor TRES'),
(4, 'Autor CUATRO'),
(5, 'Autor CINCO'),
(6, 'Autor SEIS'),
(7, 'Autor SIETE'),
(8, 'Autor OCHO'),
(9, 'Autor NUEVE'),
(10, 'Autor DIEZ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editoriales`
--

CREATE TABLE `editoriales` (
  `id` int(11) NOT NULL,
  `descripcion` char(40) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `editoriales`
--

INSERT INTO `editoriales` (`id`, `descripcion`) VALUES
(1, 'Editorial UNO'),
(2, 'Editorial DOS'),
(3, 'Editorial TRES'),
(4, 'Editorial CUATRO'),
(5, 'Editorial CINCO'),
(6, 'Editorial SEIS'),
(7, 'Editorial SIETE'),
(8, 'Editorial OCHO'),
(9, 'Editorial NUEVE'),
(10, 'Editorial DIEZ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` int(11) NOT NULL,
  `tema` int(11) NOT NULL,
  `editorial` int(11) NOT NULL,
  `titulo` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `autor` int(11) DEFAULT NULL,
  `disponible` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `isbn` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  `portada` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `fechapublicacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `tema`, `editorial`, `titulo`, `autor`, `disponible`, `isbn`, `precio`, `portada`, `fechapublicacion`) VALUES
(0, 1, 10, 'INTRODUCCION A  ANSI SQL', 8, 'S', '978-84-415-9812', 7.77, 'portada.jpg', '2015-09-07'),
(1, 4, 7, 'ACCESS 2000', 1, 'S', '978-84-415-2134', 33.21, '1.jpg', '2010-05-19'),
(2, 1, 5, 'ACTIONSCRIPT3 PARA FLASH CS3', 1, 'N', '598-84-495-2130', 12.75, '2.jpg', '2009-02-16'),
(3, 2, 6, 'HTML', 2, 'S', '808-24-115-7131', 24.35, '3.jpg', '2013-05-29'),
(4, 4, 8, 'MODELOS ECONOMICOS Y FINANCIEROS CON EXCEL 2007', 2, 'S', '638-14-015-7124', 15.86, '4.jpg', '2014-12-19'),
(5, 4, 9, 'WORD 2000', 2, 'S', '278-84-415-2138', 21.75, '5.jpg', '2011-08-10'),
(6, 3, 10, 'RED HAT LINUX FEDORA', 2, 'N', '428-84-415-2039', 14.78, '7.jpg', '2017-05-30'),
(7, 1, 1, 'VISUAL BASIC .NET', 3, 'S', '798-04-415-5134', 18.42, '6.jpg', '2011-05-07'),
(8, 1, 1, 'LENGUAJE ENSAMBLADOR DE LOS 80X86', 3, 'S', '178-84-415-3139', 38.25, '8.jpg', '2003-05-29'),
(9, 1, 2, 'VISUAL C# 2005', 3, 'N', '678-94-515-2914', 12.45, '9.jpg', '2011-10-19'),
(10, 1, 2, 'DELPHI 7', 3, 'S', '278-84-215-2171', 24.5, '10.jpg', '2014-05-29'),
(11, 2, 2, 'FINAL CUT PRO 6', 3, 'S', '178-84-415-2130', 27.45, '11.jpg', '2012-09-19'),
(12, 2, 2, 'PHOTOSHOP CS', 4, 'S', '278-64-842-9247', 17.5, '12.jpg', '2013-05-29'),
(14, 3, 3, 'ENRUTADORES CISCO', 4, 'S', '972-84-415-6756', 19.35, '14.jpg', '2013-05-19'),
(15, 1, 10, 'PHP 5', 7, 'S', '978-84-415-9812', 8.45, 'portada2.jpg', '2011-11-11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temas`
--

CREATE TABLE `temas` (
  `id` int(11) NOT NULL,
  `descripcion` char(40) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `temas`
--

INSERT INTO `temas` (`id`, `descripcion`) VALUES
(1, 'PROGRAMACION'),
(2, 'DISEÃ‘O'),
(3, 'SISTEMAS Y REDES'),
(4, 'OFIMATICA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `editoriales`
--
ALTER TABLE `editoriales`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tema` (`tema`),
  ADD KEY `edit` (`editorial`),
  ADD KEY `autor` (`autor`);

--
-- Indices de la tabla `temas`
--
ALTER TABLE `temas`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`tema`) REFERENCES `temas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `libros_ibfk_2` FOREIGN KEY (`editorial`) REFERENCES `editoriales` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `libros_ibfk_3` FOREIGN KEY (`autor`) REFERENCES `autores` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
