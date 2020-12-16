-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 15, 2020 lúc 02:47 PM
-- Phiên bản máy phục vụ: 10.4.16-MariaDB
-- Phiên bản PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webbandongho`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadonban`
--

CREATE TABLE `chitiethoadonban` (
  `IDChiTietHoaDonBan` int(11) NOT NULL,
  `IDHoaDonBan` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDSanPham` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadonban`
--

INSERT INTO `chitiethoadonban` (`IDChiTietHoaDonBan`, `IDHoaDonBan`, `IDSanPham`, `SoLuong`) VALUES
(1, 'DB004', 'SP027', 1),
(2, 'DB004', 'SP034', 1),
(3, 'DB004', 'SP012', 1),
(4, 'DB003', 'SP055', 1),
(5, 'DB002', 'SP059', 1),
(6, 'DB001', 'SP044', 1),
(7, 'DB001', 'SP005', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadonnhap`
--

CREATE TABLE `chitiethoadonnhap` (
  `IDChiTietHoaDonNhap` int(11) NOT NULL,
  `IDHoaDonNhap` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDSanPham` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadonnhap`
--

INSERT INTO `chitiethoadonnhap` (`IDChiTietHoaDonNhap`, `IDHoaDonNhap`, `IDSanPham`, `SoLuong`) VALUES
(1, 'DN004', 'SP053', 4),
(2, 'DN004', 'SP050', 2),
(3, 'DN003', 'SP018', 3),
(4, 'DN002', 'SP058', 4),
(5, 'DN002', 'SP066', 5),
(6, 'DN001', 'SP060', 3),
(7, 'DN001', 'SP045', 1),
(8, 'DN001', 'SP025', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonban`
--

CREATE TABLE `hoadonban` (
  `IDHoaDonBan` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDKhachHang` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `ThanhTien` float NOT NULL,
  `NgayBan` date NOT NULL,
  `TrangThai` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadonban`
--

INSERT INTO `hoadonban` (`IDHoaDonBan`, `IDKhachHang`, `IDNhanVien`, `ThanhTien`, `NgayBan`, `TrangThai`) VALUES
('DB001', 'KH003', 'NV001', 10000000, '2020-10-20', ' '),
('DB002', 'KH005', 'NV002', 4650000, '2020-11-20', ' '),
('DB003', 'KH004', 'NV004', 5710000, '2020-11-27', ' '),
('DB004', 'KH001', 'NV004', 9217000, '2020-12-01', ' ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonnhap`
--

CREATE TABLE `hoadonnhap` (
  `IDHoaDonNhap` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhaCungCap` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `ThanhTien` float NOT NULL,
  `NgayNhap` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadonnhap`
--

INSERT INTO `hoadonnhap` (`IDHoaDonNhap`, `IDNhaCungCap`, `IDNhanVien`, `ThanhTien`, `NgayNhap`) VALUES
('DN001', 'NC002', 'NV001', 33022000, '2020-02-20'),
('DN002', 'NC004', 'NV002', 51510000, '2020-06-01'),
('DN003', 'NC001', 'NV001', 31092000, '2020-06-29'),
('DN004', 'NC005', 'NV001', 29000000, '2020-10-24');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `IDKhachHang` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `HoKhachHang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChiNhanHang` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`IDKhachHang`, `HoKhachHang`, `TenKhachHang`, `DiaChiNhanHang`, `SoDienThoai`) VALUES
('KH000', 'admin', 'admin', ' ', ' '),
('KH001', 'Nguyễn', 'Phát', 'Dang Chat', '0982765221'),
('KH002', 'Lê', 'Nhân', 'Duong Ba Trac', '0788889378'),
('KH003', 'Nguyễn', 'Nguyên', 'Tam Danh', '0944449394'),
('KH004', 'Cao', 'Hưng', 'Ta Quang Buu', '0909189189'),
('KH005', 'Nguyễn', 'Huy', 'Au Duong Lan', '0906600189');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaiday`
--

CREATE TABLE `loaiday` (
  `IDDay` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiDay` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaiday`
--

INSERT INTO `loaiday` (`IDDay`, `TenLoaiDay`) VALUES
('LD001', 'Dây da'),
('LD002', 'Dây vải'),
('LD003', 'Dây cao su'),
('LD004', 'Thép không gỉ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `IDNhaCungCap` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhacungCap` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`IDNhaCungCap`, `TenNhacungCap`, `DiaChi`, `SoDienThoai`) VALUES
('NC001', 'Nhật Bản', 'Tan Binh', '0123456789'),
('NC002', 'Mỹ', 'Tan Phu', '0234567891'),
('NC003', 'Đan Mạch', 'Thu Duc', '0345678912'),
('NC004', 'Thụy Sĩ', 'Binh Tan', '0456789123'),
('NC005', 'Thụy Điển', 'Binh Thanh', '0567891234'),
('NC006', 'Hồng Kông', 'Phu Nhuan', '0678912345');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `IDNhanVien` char(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `HoNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Gmail` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ChucVu` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`IDNhanVien`, `HoNhanVien`, `TenNhanVien`, `Gmail`, `GioiTinh`, `SoDienThoai`, `ChucVu`) VALUES
('NV001', 'Nguyễn Minh ', 'Nguyên', 'MinhNguyen@gmail.com', 'Nam', '0327377921', 'Giám đốc'),
('NV002', 'Nguyễn Quốc ', 'Tuấn', 'QuocTuan@gmail.com', 'Nam', '0909000888', 'Nhân viên'),
('NV003', 'Lê Trí', 'Nhân', 'TriNhan@gmail.com', 'chưa biết', '0304050621', 'Quản lý'),
('NV004', 'Nguyễn Tấn ', 'Phát', 'TanPhat@gmail.com', 'Nam', '0903221155', 'Nhân viên'),
('NV005', 'Nguyễn', 'Thịnh', 'NguyenThinh@gmail.com', 'Nam', '0203040101', 'Nhân viên'),
('NV006', 'Cao Quốc', 'Hưng', 'QuocHung@gmail.com', 'Nam', '0530125124', 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `IDPhanQuyen` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TenQuyen` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MoTaQuyen` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`IDPhanQuyen`, `TenQuyen`, `MoTaQuyen`) VALUES
('PQ000', 'admin', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLTaiKhoanQLPhanQuyenQLThongKe'),
('PQ001', 'Quản lý', 'QLBanHangQLNhapHangQLMonAnQLNguyenLieuQLCongThucQLHoaDonQLHDNhapQLKhuyenMaiQLKhachHangQLNhanVienQLNhaCungCapQLThongKe'),
('PQ002', 'Bán hàng', 'QLBanHangQLHoaDonQLKhachHang'),
('PQ003', 'Nhập hàng', 'QLNhapHangQLHDNhap'),
('PQ004', 'Bán và nhập hàng', 'QLBanHangQLNhapHangQLHoaDonQLHDNhapQLKhachHang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `IDSanPham` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TenSanPham` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `IDDay` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDThuongHieu` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhaCungCap` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `BaoHanh` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Gia` float NOT NULL,
  `HinhAnh` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`IDSanPham`, `TenSanPham`, `IDDay`, `IDThuongHieu`, `IDNhaCungCap`, `BaoHanh`, `SoLuong`, `Gia`, `HinhAnh`) VALUES
('SP001', 'Orient_FAG02004B0', 'LD001', 'TH011', 'NC001', '2 năm', 10, 5810000, 'Orient_FAG02004B0.jpg'),
('SP002', 'Orient_FAL00003W0', 'LD004', 'TH011', 'NC001', '2 năm', 10, 6170000, 'Orient_FAL00003W0.jpg'),
('SP003', 'Orient_FET0P002B0', 'LD004', 'TH011', 'NC001', '2 năm', 10, 9080000, 'Orient_FET0P002B0.jpg'),
('SP004', 'Orient_FGW01004A0', 'LD001', 'TH011', 'NC001', '2 năm', 10, 3810000, 'Orient_FGW01004A0.jpg'),
('SP005', 'Orient_RA-AC0011S10B', 'LD001', 'TH011', 'NC001', '2 năm', 10, 7080000, 'Orient_RA-AC0011S10B.jpg'),
('SP006', 'Fouette_OR-FAIRYIII', 'LD001', 'TH007', 'NC006', '2 năm', 10, 2550000, 'Fouette_OR-FAIRYIII.jpg'),
('SP007', 'Fouette_OR-5', 'LD001', 'TH007', 'NC006', '2 năm', 10, 1550000, 'Fouette_OR-5.jpg'),
('SP008', 'Fouette_OR-STAR', 'LD001', 'TH007', 'NC006', '2 năm', 10, 1550000, 'Fouette_OR-STAR.jpg'),
('SP009', 'Fouette_OR-LOVE', 'LD001', 'TH007', 'NC006', '2 năm', 10, 1550000, 'Fouette_OR-LOVE.jpg'),
('SP010', 'Casio_EFV-540DC-1BVUDF', 'LD004', 'TH003', 'NC001', '2 năm', 10, 4489000, 'Casio_EFV-540DC-1BVUDF.jpg'),
('SP011', 'Casio_MTP-VD300L-1EUDF', 'LD001', 'TH003', 'NC001', '2 năm', 10, 1457000, 'Casio_MTP-VD300L-1EUDF.jpg'),
('SP012', 'Casio_LTP-1170N-9ARDF', 'LD004', 'TH003', 'NC001', '2 năm', 10, 1387000, 'Casio_LTP-1170N-9ARDF.jpg'),
('SP013', 'Casio_MW-240-7EVDF', 'LD003', 'TH003', 'NC001', '2 năm', 10, 517000, 'Casio_MW-240-7EVDF.jpg'),
('SP014', 'Casio_AE1200WHD', 'LD004', 'TH003', 'NC001', '2 năm', 10, 1246000, 'Casio_AE1200WHD.jpg'),
('SP015', 'G-Shock_DW-5600MS-1DR', 'LD003', 'TH008', 'NC001', '2 năm', 10, 3169000, 'G-Shock_DW-5600MS-1DR.jpg'),
('SP016', 'G-Shock_GAX-100MSB-1ADR', 'LD003', 'TH008', 'NC001', '2 năm', 10, 5100000, 'G-Shock_GAX-100MSB-1ADR.jpg'),
('SP017', 'Baby-G_MSG-S200-7ADR', 'LD003', 'TH008', 'NC001', '2 năm', 10, 3737000, 'Baby-G_MSG-S200-7ADR.jpg'),
('SP018', 'G-Shock_GST-S310D-1A9DR', 'LD004', 'TH008', 'NC001', '2 năm', 10, 10364000, 'G-Shock_GST-S310D-1A9DR.jpg'),
('SP019', 'G-Shock_GA-200RG-1ADR', 'LD003', 'TH008', 'NC001', '2 năm', 10, 5452000, 'G-Shock_GA-200RG-1ADR.jpg'),
('SP020', 'Citizen_NH8350-59L', 'LD004', 'TH004', 'NC001', '2 năm', 10, 5520000, 'Citizen_NH8350-59L.jpg'),
('SP021', 'Citizen_BH3003-51A', 'LD004', 'TH004', 'NC001', '2 năm', 10, 4300000, 'Citizen_BH3003-51A.jpg'),
('SP022', 'Citizen_BI1050-05A', 'LD002', 'TH004', 'NC001', '2 năm', 10, 2700000, 'Citizen_BI1050-05A.jpg'),
('SP023', 'Citizen_BM9012-02A', 'LD001', 'TH004', 'NC001', '2 năm', 10, 6600000, 'Citizen_BM9012-02A.jpg'),
('SP024', 'Citizen_CA4425-10X', 'LD001', 'TH004', 'NC001', '2 năm', 10, 7750000, 'Citizen_CA4425-10X.jpg'),
('SP025', 'Fossil_ES4821', 'LD004', 'TH006', 'NC002', '2 năm', 10, 3480000, 'Fossil_ES4821.jpg'),
('SP026', 'Fossil_ES4534', 'LD004', 'TH006', 'NC002', '2 năm', 10, 4020000, 'Fossil_ES4534.jpg'),
('SP027', 'Fossil_ES4837', 'LD004', 'TH006', 'NC002', '2 năm', 10, 3480000, 'Fossil_ES4837.jpg'),
('SP028', 'Fossil_ES4433', 'LD004', 'TH006', 'NC002', '2 năm', 10, 3380000, 'Fossil_ES4433.jpg'),
('SP029', 'Fossil_ES4671', 'LD004', 'TH006', 'NC002', '2 năm', 10, 3480000, 'Fossil_ES4671.jpg'),
('SP030', 'Skagen_SKW6654', 'LD001', 'TH014', 'NC003', '2 năm', 10, 2960000, 'Skagen_SKW6654.jpg'),
('SP031', 'Skagen_SKW6454', 'LD002', 'TH014', 'NC003', '2 năm', 10, 4630000, 'Skagen_SKW6454.jpg'),
('SP032', 'Skagen_SKW6578', 'LD001', 'TH014', 'NC003', '2 năm', 10, 2960000, 'Skagen_SKW6578.jpg'),
('SP033', 'Skagen_SKW2699', 'LD004', 'TH014', 'NC003', '2 năm', 10, 3280000, 'Skagen_SKW2699.jpg'),
('SP034', 'Skagen_456SSS', 'LD004', 'TH014', 'NC003', '2 năm', 10, 4350000, 'Skagen_456SSS.jpg'),
('SP035', 'Seiko_SPB121J1', 'LD001', 'TH013', 'NC001', '2 năm', 10, 24840000, 'Seiko_SPB121J1.jpg'),
('SP036', 'Seiko_SSC715P1', 'LD004', 'TH013', 'NC001', '2 năm', 10, 8720000, 'Seiko_SSC715P1.jpg'),
('SP037', 'Seiko_SRPC91K1', 'LD003', 'TH013', 'NC001', '2 năm', 10, 11820000, 'Seiko_SRPC91K1.jpg'),
('SP038', 'Seiko_SSA383K1', 'LD002', 'TH013', 'NC001', '2 năm', 10, 7540000, 'Seiko_SSA383K1.jpg'),
('SP039', 'Seiko_SSA810J1', 'LD004', 'TH013', 'NC001', '2 năm', 10, 13070000, 'Seiko_SSA810J1.jpg'),
('SP040', 'OP_89322GS-T', 'LD004', 'TH010', 'NC001', '2 năm', 10, 2840000, 'OP_89322GS-T.jpg'),
('SP041', 'OP_130MS-GL-T-06', 'LD001', 'TH010', 'NC001', '2 năm', 10, 2000000, 'OP_130MS-GL-T-06.jpg'),
('SP042', 'OP_9908AGS-D-88', 'LD004', 'TH010', 'NC001', '2 năm', 10, 6970000, 'OP_9908AGS-D-88.jpg'),
('SP043', 'OP_5695MS-T', 'LD004', 'TH010', 'NC001', '2 năm', 10, 2800000, 'OP_5695MS-T.jpg'),
('SP044', 'OP_5695LSK-V', 'LD004', 'TH010', 'NC001', '2 năm', 10, 2920000, 'OP_5695LSK-V.jpg'),
('SP045', 'MichaelKors_MK3191', 'LD004', 'TH009', 'NC002', '2 năm', 10, 6910000, 'MichaelKors_MK3191.jpg'),
('SP046', 'MichaelKors_MK4409', 'LD004', 'TH009', 'NC002', '2 năm', 10, 6760000, 'MichaelKors_MK4409.jpg'),
('SP047', 'MichaelKors_MK8752', 'LD004', 'TH009', 'NC002', '2 năm', 10, 6910000, 'MichaelKors_MK8752.jpg'),
('SP048', 'MichaelKors_MK8631', 'LD001', 'TH009', 'NC002', '2 năm', 10, 5400000, 'MichaelKors_MK8631.jpg'),
('SP049', 'MichaelKors_MK2715', 'LD001', 'TH009', 'NC002', '2 năm', 10, 5270000, 'MichaelKors_MK2715.jpg'),
('SP050', 'DanielWellington_DW00100014', 'LD001', 'TH005', 'NC005', '2 năm', 10, 5300000, 'DanielWellington_DW00100014.jpg'),
('SP051', 'DanielWellington_DW00100311', 'LD002', 'TH005', 'NC005', '2 năm', 10, 3500000, 'DanielWellington_DW00100311.jpg'),
('SP052', 'DanielWellington_DW00100164', 'LD004', 'TH005', 'NC005', '2 năm', 10, 4100000, 'DanielWellington_DW00100164.jpg'),
('SP053', 'DanielWellington_DW00100277', 'LD002', 'TH005', 'NC005', '2 năm', 10, 4600000, 'DanielWellington_DW00100277.jpg'),
('SP054', 'DanielWellington_DW00100135', 'LD001', 'TH005', 'NC005', '2 năm', 10, 5300000, 'DanielWellington_DW00100135.jpg'),
('SP055', 'Candino_C45582', 'LD001', 'TH002', 'NC004', '2 năm', 10, 5710000, 'Candino_C42922.jpg'),
('SP056', 'Candino_C42922', 'LD001', 'TH002', 'NC004', '2 năm', 10, 4650000, 'Candino_C44714.jpg'),
('SP057', 'Candino_C44714', 'LD001', 'TH002', 'NC004', '2 năm', 10, 5710000, 'Candino_C46401.jpg'),
('SP058', 'Candino_C46401', 'LD001', 'TH002', 'NC004', '2 năm', 10, 6750000, 'Candino_C42921.jpg'),
('SP059', 'Candino_C42921', 'LD001', 'TH002', 'NC004', '2 năm', 10, 4650000, 'Saga_53229 SVMWSV-6.jpg'),
('SP060', 'Saga_53229 SVMWSV-6', 'LD004', 'TH012', 'NC002', '2 năm', 10, 6384000, 'Saga_80727 GPMWGP-2L.jpg'),
('SP061', 'Saga_80727 GPMWGP-2L', 'LD004', 'TH012', 'NC002', '2 năm', 10, 6004000, 'Saga_53555 SVMWSV-2.jpg'),
('SP062', 'Saga_53555 SVMWSV-2', 'LD004', 'TH012', 'NC002', '2 năm', 10, 5244000, 'Saga_71865 GPMWGP-2L.jpg'),
('SP063', 'Saga_71865 GPMWGP-2L', 'LD004', 'TH012', 'NC002', '2 năm', 10, 6764000, 'Saga_71865 GPMWGP-2L.jpg'),
('SP064', 'Saga_53624 RGBDBK-2', 'LD004', 'TH012', 'NC002', '2 năm', 10, 6384000, 'Saga_53624 RGBDBK-2.jpg'),
('SP065', 'Adriatica_A3694.51B3QZ', 'LD004', 'TH001', 'NC004', '2 năm', 10, 6210000, 'Adriatica_A3694.51B3QZ.jpg'),
('SP066', 'Adriatica_A8109.5153Q', 'LD004', 'TH001', 'NC004', '2 năm', 10, 4440000, 'Adriatica_A8109.5153Q.jpg'),
('SP067', 'Adriatica_A3603.5113QZ', 'LD004', 'TH001', 'NC004', '2 năm', 10, 5490000, 'Adriatica_A3603.5113QZ.jpg'),
('SP068', 'Adriatica_A3508.1143QZ', 'LD004', 'TH001', 'NC004', '2 năm', 10, 5310000, 'Adriatica_A3508.1143QZ.jpg'),
('SP069', 'Adriatica_A3143.2111Q', 'LD004', 'TH001', 'NC004', '2 năm', 10, 5610000, 'Adriatica_A3143.2111Q.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoankh`
--

CREATE TABLE `taikhoankh` (
  `TaiKhoan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `IDNhanVien` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `IDPhanQuyen` char(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoankh`
--

INSERT INTO `taikhoankh` (`TaiKhoan`, `MatKhau`, `IDNhanVien`, `IDPhanQuyen`) VALUES
('admin', '123456', 'NV001', 'PQ000'),
('caohung', 'iklmno', 'NV003', 'PQ001'),
('lenhan', '290620', 'NV002', 'PQ002'),
('nguyenhuy', 'qwerty', 'NV004', 'PQ003'),
('nguyennguyen', '456789', 'NV005', 'PQ004'),
('nguyenphat', 'abcdef', 'NV006', 'PQ004');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `IDThuongHieu` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `TenThuongHieu` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`IDThuongHieu`, `TenThuongHieu`) VALUES
('TH001', 'ADRIATICA'),
('TH002', 'CANDINO'),
('TH003', 'CASIO'),
('TH004', 'CITIZEN'),
('TH005', 'DANIEL WELLINGTON'),
('TH006', 'FOSSIL'),
('TH007', 'FOUETTÉ'),
('TH008', 'G-SHOCK & BABY-G'),
('TH009', 'MICHAEL KORS'),
('TH010', 'OP'),
('TH011', 'ORIENT'),
('TH012', 'SAGA'),
('TH013', 'SEIKO'),
('TH014', 'SKAGEN');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadonban`
--
ALTER TABLE `chitiethoadonban`
  ADD PRIMARY KEY (`IDChiTietHoaDonBan`),
  ADD KEY `IDHoaDon` (`IDHoaDonBan`),
  ADD KEY `IDSanPham` (`IDSanPham`);

--
-- Chỉ mục cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD PRIMARY KEY (`IDChiTietHoaDonNhap`),
  ADD KEY `IDHoaDonNhap` (`IDHoaDonNhap`),
  ADD KEY `IDSanPham` (`IDSanPham`);

--
-- Chỉ mục cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD PRIMARY KEY (`IDHoaDonBan`),
  ADD KEY `IDKhachHang` (`IDKhachHang`),
  ADD KEY `IDNhanVien` (`IDNhanVien`);

--
-- Chỉ mục cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD PRIMARY KEY (`IDHoaDonNhap`),
  ADD KEY `IDNhaCungCap` (`IDNhaCungCap`),
  ADD KEY `IDNhanVien` (`IDNhanVien`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`IDKhachHang`);

--
-- Chỉ mục cho bảng `loaiday`
--
ALTER TABLE `loaiday`
  ADD PRIMARY KEY (`IDDay`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`IDNhaCungCap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`IDNhanVien`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`IDPhanQuyen`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`IDSanPham`),
  ADD KEY `IDThuongHieu` (`IDThuongHieu`),
  ADD KEY `IDDay` (`IDDay`),
  ADD KEY `IDNhaCungCap` (`IDNhaCungCap`);

--
-- Chỉ mục cho bảng `taikhoankh`
--
ALTER TABLE `taikhoankh`
  ADD PRIMARY KEY (`TaiKhoan`),
  ADD KEY `IDNhanVien` (`IDNhanVien`),
  ADD KEY `IDPhanQuyen` (`IDPhanQuyen`);

--
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`IDThuongHieu`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadonban`
--
ALTER TABLE `chitiethoadonban`
  ADD CONSTRAINT `chitiethoadonban_ibfk_1` FOREIGN KEY (`IDHoaDonBan`) REFERENCES `hoadonban` (`IDHoaDonBan`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadonban_ibfk_2` FOREIGN KEY (`IDSanPham`) REFERENCES `sanpham` (`IDSanPham`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitiethoadonnhap`
--
ALTER TABLE `chitiethoadonnhap`
  ADD CONSTRAINT `chitiethoadonnhap_ibfk_1` FOREIGN KEY (`IDHoaDonNhap`) REFERENCES `hoadonnhap` (`IDHoaDonNhap`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadonnhap_ibfk_2` FOREIGN KEY (`IDSanPham`) REFERENCES `sanpham` (`IDSanPham`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD CONSTRAINT `hoadonban_ibfk_1` FOREIGN KEY (`IDKhachHang`) REFERENCES `khachhang` (`IDKhachHang`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadonban_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadonnhap`
--
ALTER TABLE `hoadonnhap`
  ADD CONSTRAINT `hoadonnhap_ibfk_1` FOREIGN KEY (`IDNhaCungCap`) REFERENCES `nhacungcap` (`IDNhaCungCap`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadonnhap_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`IDThuongHieu`) REFERENCES `thuonghieu` (`IDThuongHieu`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`IDDay`) REFERENCES `loaiday` (`IDDay`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`IDNhaCungCap`) REFERENCES `nhacungcap` (`IDNhaCungCap`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoankh`
--
ALTER TABLE `taikhoankh`
  ADD CONSTRAINT `taikhoankh_ibfk_1` FOREIGN KEY (`IDPhanQuyen`) REFERENCES `phanquyen` (`IDPhanQuyen`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoankh_ibfk_2` FOREIGN KEY (`IDNhanVien`) REFERENCES `nhanvien` (`IDNhanVien`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
