-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Авг 19 2019 г., 18:24
-- Версия сервера: 10.1.26-MariaDB-0+deb9u1
-- Версия PHP: 7.0.33-0+deb9u3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `testmyprog`
--

-- --------------------------------------------------------

--
-- Структура таблицы `UserAccounts`
--

CREATE TABLE `UserAccounts` (
  `userAccountName` varchar(15) NOT NULL,
  `firstName` varchar(10) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `city` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `UserAccounts`
--

INSERT INTO `UserAccounts` (`userAccountName`, `firstName`, `lastName`, `age`, `city`) VALUES
('dan3000', 'Danila', 'TEST2', 16, 'Tver'),
('olegr_', 'Oleg', 'RakitinTestLastName', 21, 'Saint-Petersburg'),
('test', 'testName', 'testLastName', 99, 'testCity'),
('test2', 'firstTest2', 'lastTest2', 14, 'testCity2');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `UserAccounts`
--
ALTER TABLE `UserAccounts`
  ADD PRIMARY KEY (`userAccountName`) USING BTREE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
