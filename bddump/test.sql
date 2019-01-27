-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Янв 27 2019 г., 19:02
-- Версия сервера: 8.0.13
-- Версия PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `commission`
--

CREATE TABLE `commission` (
  `commission_id` int(255) NOT NULL,
  `subjectCommission` char(255) NOT NULL,
  `authorCommission` int(255) NOT NULL,
  `periodExecution` date DEFAULT NULL,
  `signControl` char(255) DEFAULT NULL,
  `signExecution` char(255) DEFAULT NULL,
  `сommissionText` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `commission`
--

INSERT INTO `commission` (`commission_id`, `subjectCommission`, `authorCommission`, `periodExecution`, `signControl`, `signExecution`, `сommissionText`) VALUES
(2, 'sub', 12, '2002-02-20', 'sign', 'exec', 'zxc');

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(255) NOT NULL,
  `lastName` char(50) NOT NULL,
  `firstName` char(50) NOT NULL,
  `middleName` char(50) DEFAULT NULL,
  `position` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `employee`
--

INSERT INTO `employee` (`employee_id`, `lastName`, `firstName`, `middleName`, `position`) VALUES
(12, '23', '25', '23', '11111111111111'),
(14, '6145', 'firstName', '23', 'Должность');

-- --------------------------------------------------------

--
-- Структура таблицы `employee_commission`
--

CREATE TABLE `employee_commission` (
  `employee_id` int(255) NOT NULL,
  `commission_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `employee_commission`
--

INSERT INTO `employee_commission` (`employee_id`, `commission_id`) VALUES
(14, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `employee_old`
--

CREATE TABLE `employee_old` (
  `id` int(11) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `employee_old`
--

INSERT INTO `employee_old` (`id`, `firstName`, `lastName`) VALUES
(6, 'бновил', 'Это'),
(7, 'Привет', '123');

-- --------------------------------------------------------

--
-- Структура таблицы `organization`
--

CREATE TABLE `organization` (
  `id` int(255) NOT NULL,
  `nameOfCompany` char(255) NOT NULL,
  `physicalAdress` char(255) DEFAULT NULL,
  `legalAddress` char(255) DEFAULT NULL,
  `employee_id` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `organization`
--

INSERT INTO `organization` (`id`, `nameOfCompany`, `physicalAdress`, `legalAddress`, `employee_id`) VALUES
(2, '12234', '1255', '1235', 12);

-- --------------------------------------------------------

--
-- Структура таблицы `subdivision`
--

CREATE TABLE `subdivision` (
  `id` int(255) NOT NULL,
  `subdivisionName` char(255) NOT NULL,
  `contactDetails` char(255) DEFAULT NULL,
  `employee_id` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `subdivision`
--

INSERT INTO `subdivision` (`id`, `subdivisionName`, `contactDetails`, `employee_id`) VALUES
(1, 'name', 'contact', 12),
(2, 'name2', 'contact', 14);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `commission`
--
ALTER TABLE `commission`
  ADD PRIMARY KEY (`commission_id`),
  ADD KEY `fk_commission_employee` (`authorCommission`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Индексы таблицы `employee_commission`
--
ALTER TABLE `employee_commission`
  ADD PRIMARY KEY (`employee_id`,`commission_id`),
  ADD KEY `fk_commission_M` (`commission_id`);

--
-- Индексы таблицы `employee_old`
--
ALTER TABLE `employee_old`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `organization`
--
ALTER TABLE `organization`
  ADD PRIMARY KEY (`id`),
  ADD KEY `o-m` (`employee_id`);

--
-- Индексы таблицы `subdivision`
--
ALTER TABLE `subdivision`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_subdiv_employee` (`employee_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `commission`
--
ALTER TABLE `commission`
  MODIFY `commission_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `employee_old`
--
ALTER TABLE `employee_old`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `organization`
--
ALTER TABLE `organization`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `subdivision`
--
ALTER TABLE `subdivision`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `commission`
--
ALTER TABLE `commission`
  ADD CONSTRAINT `fk_commission_employee` FOREIGN KEY (`authorCommission`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `employee_commission`
--
ALTER TABLE `employee_commission`
  ADD CONSTRAINT `fk_commission_M` FOREIGN KEY (`commission_id`) REFERENCES `commission` (`commission_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_employee_M` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `organization`
--
ALTER TABLE `organization`
  ADD CONSTRAINT `o-m` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Ограничения внешнего ключа таблицы `subdivision`
--
ALTER TABLE `subdivision`
  ADD CONSTRAINT `fk_subdiv_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
