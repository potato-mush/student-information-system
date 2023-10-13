-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2023 at 01:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_info`
--

-- --------------------------------------------------------

--
-- Table structure for table `students_tbl`
--

CREATE TABLE `students_tbl` (
  `id` int(11) NOT NULL,
  `student_ID` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `middlename` varchar(255) NOT NULL,
  `year_level` varchar(255) NOT NULL,
  `course` varchar(255) NOT NULL,
  `birthDate` date NOT NULL,
  `address` varchar(255) NOT NULL,
  `guardian` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students_tbl`
--

INSERT INTO `students_tbl` (`id`, `student_ID`, `firstname`, `lastname`, `middlename`, `year_level`, `course`, `birthDate`, `address`, `guardian`, `contact`) VALUES
(12, 'c21-7361-435', 'qwerty', 'jkljkl', 'jkl', '1st Year', 'BSIT', '2023-04-10', 'asd', 'asd', '+630123456789'),
(13, 'c21-1565-836', 'asd', 'asd', 'asd', '1st Year', 'BSIT', '2023-04-10', 'asd', 'asd', '+630123456789'),
(14, 'c21-1425-908', 'asd', 'asd', 'asd', '1st Year', 'BSIT', '2023-05-23', 'asdasdasd', 'asdasdasd', '+631234567890'),
(15, 'c21-9925-829', 'qwe', 'qwe', 'qwe', '2nd Year', 'BSHM', '2023-05-31', 'qweqweqwe', 'qweqweqwe', '+631234567890'),
(16, 'c21-4203-240', 'uio', 'uio', 'uio', '3rd Year', 'BSBA', '2023-05-21', 'uiouiouio', 'uiouiouio', '+631234567890'),
(17, 'c21-9366-982', 'jkl', 'jkl', 'jkl', '4th Year', 'BEED', '2023-05-01', 'jkljkljkl', 'jkljkljkl', '+631234567890');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students_tbl`
--
ALTER TABLE `students_tbl`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `students_tbl`
--
ALTER TABLE `students_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
