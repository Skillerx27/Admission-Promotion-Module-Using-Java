-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2019 at 11:15 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `markssubmissionandreportmodule`
--

-- --------------------------------------------------------

--
-- Table structure for table `complain`
--

CREATE TABLE `complain` (
  `id` int(3) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `complain` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complain`
--

INSERT INTO `complain` (`id`, `user_name`, `complain`) VALUES
(1, 'monirul', 'hi\r\n'),
(2, 'monirul', 'mr sohan does not have any contribution on this project');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `user_name` varchar(25) NOT NULL,
  `program` varchar(10) DEFAULT NULL,
  `ftm` int(3) DEFAULT '0',
  `mtm` int(3) DEFAULT '0',
  `fm` int(4) DEFAULT '0',
  `am` int(3) DEFAULT '0',
  `total_marks` int(4) AS ((ftm * .25) + (mtm * .25) + (fm * .4) + am) VIRTUAL,
  `grade` varchar(3) AS (case when ((ftm*.25)+(mtm*.25)+(fm*.4)+am)>89 then 'A' when ((ftm*.25)+(mtm*.25)+(fm*.4)+am)>79 then 'B' when ((ftm*.25)+(mtm*.25)+(fm*.4)+am)>69 then 'C' when ((ftm*.25)+(mtm*.25)+(fm*.4)+am)>59 then 'D' else 'F' end) VIRTUAL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`user_name`, `program`, `ftm`, `mtm`, `fm`, `am`) VALUES
('monirul', 'BCSE', 92, 100, 80, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_name` varchar(25) NOT NULL,
  `user_type` enum('admin','faculty','student') NOT NULL,
  `password` varchar(25) NOT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `contact_no` varchar(15) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_name`, `user_type`, `password`, `full_name`, `contact_no`, `email`) VALUES
('admin', 'admin', 'password', 'Mohammad Sajid Shahriar', '+8801674342278', 'sajid@iubat.edu'),
('faculty', 'faculty', 'password', 'Mohammad Sajid Shahriar', '+8801674342278', 'sajid@iubat.edu'),
('monirul', 'student', 'password', 'Md. Monirul Islam', '+8801714226505', '17103333@iubat.edu'),
('s2', 'student', '123', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`user_name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complain`
--
ALTER TABLE `complain`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
