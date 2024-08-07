USE [master]
GO
/****** Object:  Database [FUCarRentingSystem]    Script Date: 7/9/2024 11:57:31 ******/
CREATE DATABASE [FUCarRentingSystem]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FUCarRentingSystem', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SE160429\MSSQL\DATA\FUCarRentingSystem.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'FUCarRentingSystem_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SE160429\MSSQL\DATA\FUCarRentingSystem_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [FUCarRentingSystem] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FUCarRentingSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FUCarRentingSystem] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET ARITHABORT OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FUCarRentingSystem] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FUCarRentingSystem] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET  ENABLE_BROKER 
GO
ALTER DATABASE [FUCarRentingSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FUCarRentingSystem] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET RECOVERY FULL 
GO
ALTER DATABASE [FUCarRentingSystem] SET  MULTI_USER 
GO
ALTER DATABASE [FUCarRentingSystem] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FUCarRentingSystem] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FUCarRentingSystem] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FUCarRentingSystem] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [FUCarRentingSystem] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [FUCarRentingSystem] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'FUCarRentingSystem', N'ON'
GO
ALTER DATABASE [FUCarRentingSystem] SET QUERY_STORE = OFF
GO
USE [FUCarRentingSystem]
GO
/****** Object:  Table [dbo].[ACCOUNTS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCOUNTS](
	[accountID] [varchar](255) NOT NULL,
	[accountName] [varchar](255) NOT NULL,
	[role] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[accountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CARPRODUCERS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CARPRODUCERS](
	[producerID] [varchar](255) NOT NULL,
	[address] [varchar](255) NOT NULL,
	[country] [varchar](255) NOT NULL,
	[procuderName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[producerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CARRENTALS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CARRENTALS](
	[carRentalID] [int] NOT NULL,
	[pickupDate] [datetime] NULL,
	[rentPrice] [float] NOT NULL,
	[returnDate] [datetime] NULL,
	[status] [varchar](255) NULL,
	[carID] [varchar](255) NULL,
	[customerID] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[carRentalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CARS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CARS](
	[carID] [varchar](255) NOT NULL,
	[capacity] [varchar](255) NULL,
	[carModelYear] [varchar](255) NULL,
	[carName] [varchar](255) NULL,
	[color] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[importDate] [datetime] NULL,
	[rentPrice] [float] NOT NULL,
	[status] [varchar](255) NULL,
	[producerID] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CUSTOMERS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CUSTOMERS](
	[customerID] [varchar](255) NOT NULL,
	[birthday] [datetime] NULL,
	[customerName] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[identityCard] [varchar](255) NULL,
	[licenceDate] [datetime] NULL,
	[licenceNumber] [int] NOT NULL,
	[mobile] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[accountID] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hibernate_sequence]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hibernate_sequence](
	[next_val] [numeric](19, 0) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[REVIEWS]    Script Date: 7/9/2024 11:57:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[REVIEWS](
	[id] [int] NOT NULL,
	[comment] [varchar](255) NULL,
	[reviewStar] [int] NOT NULL,
	[carID] [varchar](255) NULL,
	[customerID] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ACCOUNTS] ([accountID], [accountName], [role]) VALUES (N'A1', N'Admin', 1)
INSERT [dbo].[ACCOUNTS] ([accountID], [accountName], [role]) VALUES (N'A2', N'Customer', 2)
GO
INSERT [dbo].[CARPRODUCERS] ([producerID], [address], [country], [procuderName]) VALUES (N'P1', N'HCM', N'VN', N'Alex')
INSERT [dbo].[CARPRODUCERS] ([producerID], [address], [country], [procuderName]) VALUES (N'P2', N'HN', N'VN', N'Diggo')
INSERT [dbo].[CARPRODUCERS] ([producerID], [address], [country], [procuderName]) VALUES (N'P3', N'DN', N'VN', N'Chan')
GO
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (1, CAST(N'2024-07-04T00:00:00.000' AS DateTime), 2000000, CAST(N'2024-07-05T00:00:00.000' AS DateTime), N'success', N'C1', N'C3')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (2, CAST(N'2024-02-04T00:00:00.000' AS DateTime), 8500000, CAST(N'2024-02-14T00:00:00.000' AS DateTime), N'success', N'C2', N'C3')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (3, CAST(N'2024-04-28T00:00:00.000' AS DateTime), 5600000, CAST(N'2024-04-30T00:00:00.000' AS DateTime), N'success', N'C3', N'C1')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (6, CAST(N'2024-07-08T00:00:00.000' AS DateTime), 5000000, NULL, N'on processing', N'C2', N'C3')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (7, CAST(N'2024-07-09T00:00:00.000' AS DateTime), 5000000, NULL, N'on processing', N'C2', N'C4')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (8, CAST(N'2024-07-09T00:00:00.000' AS DateTime), 2000000, NULL, N'on processing', N'C1', N'C3')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (9, CAST(N'2024-07-09T00:00:00.000' AS DateTime), 8500000, NULL, N'on processing', N'C3', N'C3')
INSERT [dbo].[CARRENTALS] ([carRentalID], [pickupDate], [rentPrice], [returnDate], [status], [carID], [customerID]) VALUES (10, CAST(N'2024-07-09T00:00:00.000' AS DateTime), 5000000, NULL, N'on processing', N'C2', N'C4')
GO
INSERT [dbo].[CARS] ([carID], [capacity], [carModelYear], [carName], [color], [description], [importDate], [rentPrice], [status], [producerID]) VALUES (N'C1', N'2L', N'2009', N'Honda Civic', N'White', N'Good', CAST(N'2009-05-05T00:00:00.000' AS DateTime), 2000000, N'on', N'P1')
INSERT [dbo].[CARS] ([carID], [capacity], [carModelYear], [carName], [color], [description], [importDate], [rentPrice], [status], [producerID]) VALUES (N'C2', N'2.8L', N'2019', N'Mercedes', N'Black', N'Extremely Good', CAST(N'2020-01-04T00:00:00.000' AS DateTime), 5000000, N'on', N'P2')
INSERT [dbo].[CARS] ([carID], [capacity], [carModelYear], [carName], [color], [description], [importDate], [rentPrice], [status], [producerID]) VALUES (N'C3', N'3L', N'2015', N'Lamborghini', N'Green', N'Very Good', CAST(N'2016-09-24T00:00:00.000' AS DateTime), 8500000, N'deleted', N'P3')
GO
INSERT [dbo].[CUSTOMERS] ([customerID], [birthday], [customerName], [email], [identityCard], [licenceDate], [licenceNumber], [mobile], [password], [accountID]) VALUES (N'C1', CAST(N'2002-05-07T00:00:00.000' AS DateTime), N'Hoang Son Ha', N'hoangsonha@gmail.com', N'123456789', CAST(N'2002-04-07T00:00:00.000' AS DateTime), 1234, N'123456789', N'1', N'A1')
INSERT [dbo].[CUSTOMERS] ([customerID], [birthday], [customerName], [email], [identityCard], [licenceDate], [licenceNumber], [mobile], [password], [accountID]) VALUES (N'C3', CAST(N'2005-05-05T00:00:00.000' AS DateTime), N'Chau Phuong', N'chauquynhphuong@gmail.com', N'889384204', CAST(N'2005-05-05T00:00:00.000' AS DateTime), 123456, N'0334387994', N'123456', N'A2')
INSERT [dbo].[CUSTOMERS] ([customerID], [birthday], [customerName], [email], [identityCard], [licenceDate], [licenceNumber], [mobile], [password], [accountID]) VALUES (N'C4', CAST(N'2005-05-05T00:00:00.000' AS DateTime), N'Nguyen Kim Ngan', N'Ngan@gamil.com', N'3423423', CAST(N'2005-05-05T00:00:00.000' AS DateTime), 5654656, N'654645654', N'123', N'A2')
INSERT [dbo].[CUSTOMERS] ([customerID], [birthday], [customerName], [email], [identityCard], [licenceDate], [licenceNumber], [mobile], [password], [accountID]) VALUES (N'C6', CAST(N'2005-05-05T00:00:00.000' AS DateTime), N'Alex', N'alex@gmail.com', N'3423423', CAST(N'2005-05-05T00:00:00.000' AS DateTime), 123, N'4343584954', N'passmonhsf301', N'A2')
GO
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (CAST(11 AS Numeric(19, 0)))
GO
INSERT [dbo].[REVIEWS] ([id], [comment], [reviewStar], [carID], [customerID]) VALUES (1, N'Good', 5, N'C1', N'C1')
INSERT [dbo].[REVIEWS] ([id], [comment], [reviewStar], [carID], [customerID]) VALUES (2, N'Very Good', 5, N'C2', N'C3')
INSERT [dbo].[REVIEWS] ([id], [comment], [reviewStar], [carID], [customerID]) VALUES (3, N'Very Good', 5, N'C3', N'C3')
GO
ALTER TABLE [dbo].[CARRENTALS]  WITH CHECK ADD  CONSTRAINT [FKbk0pyw7x3iv2ko9jb3fdcujub] FOREIGN KEY([customerID])
REFERENCES [dbo].[CUSTOMERS] ([customerID])
GO
ALTER TABLE [dbo].[CARRENTALS] CHECK CONSTRAINT [FKbk0pyw7x3iv2ko9jb3fdcujub]
GO
ALTER TABLE [dbo].[CARRENTALS]  WITH CHECK ADD  CONSTRAINT [FKqw86dyil44clx8hj0yukbhplt] FOREIGN KEY([carID])
REFERENCES [dbo].[CARS] ([carID])
GO
ALTER TABLE [dbo].[CARRENTALS] CHECK CONSTRAINT [FKqw86dyil44clx8hj0yukbhplt]
GO
ALTER TABLE [dbo].[CARS]  WITH CHECK ADD  CONSTRAINT [FK3cjpxgbfyt9jia2pj9uap2g8q] FOREIGN KEY([producerID])
REFERENCES [dbo].[CARPRODUCERS] ([producerID])
GO
ALTER TABLE [dbo].[CARS] CHECK CONSTRAINT [FK3cjpxgbfyt9jia2pj9uap2g8q]
GO
ALTER TABLE [dbo].[CUSTOMERS]  WITH CHECK ADD  CONSTRAINT [FK9wkgho49y058rq1yfw9wh9gpx] FOREIGN KEY([accountID])
REFERENCES [dbo].[ACCOUNTS] ([accountID])
GO
ALTER TABLE [dbo].[CUSTOMERS] CHECK CONSTRAINT [FK9wkgho49y058rq1yfw9wh9gpx]
GO
ALTER TABLE [dbo].[REVIEWS]  WITH CHECK ADD  CONSTRAINT [FK3o0yoxn1e7yl9ivsompebiuab] FOREIGN KEY([carID])
REFERENCES [dbo].[CARS] ([carID])
GO
ALTER TABLE [dbo].[REVIEWS] CHECK CONSTRAINT [FK3o0yoxn1e7yl9ivsompebiuab]
GO
ALTER TABLE [dbo].[REVIEWS]  WITH CHECK ADD  CONSTRAINT [FK9e2cjq5nqsqfc9np6ge8291xr] FOREIGN KEY([customerID])
REFERENCES [dbo].[CUSTOMERS] ([customerID])
GO
ALTER TABLE [dbo].[REVIEWS] CHECK CONSTRAINT [FK9e2cjq5nqsqfc9np6ge8291xr]
GO
USE [master]
GO
ALTER DATABASE [FUCarRentingSystem] SET  READ_WRITE 
GO
