CREATE TABLE HoaDon (
    MaHoaDon NVARCHAR(20) PRIMARY KEY,
    MaKhachHang NVARCHAR(20),
    HoTen NVARCHAR(50),
    NgayRaHoaDon NVARCHAR(20),
    QuocTich NVARCHAR(50),
    DoiTuongKhachHang ENUM('Sinh hoạt', 'Kinh doanh', 'Sản xuất'),
    SoLuong INT,
    DonGia FLOAT,
    DinhMuc INT,
    ThanhTien DECIMAL(10)
);

INSERT INTO HoaDon (MaHoaDon, MaKhachHang, HoTen, NgayRaHoaDon, QuocTich, DoiTuongKhachHang, SoLuong, DonGia, DinhMuc, ThanhTien) 
VALUES
	('HD001', 'KH001', 'Thanh Phong', '2023-01-15', 'Việt Nam', 'Sinh hoạt', 10, 100.0, 5, 6250),  
	('HD002', 'KH002', 'Nguyễn Vỹ', '2023-01-16', 'Việt Nam', 'Kinh doanh', 5, 150.0, 3, 3000),  
	('HD003', 'KH003', 'Lục Huy', '2023-01-17', 'Việt Nam', 'Sản xuất', 20, 200.0, 10, 45000), 
	('HD004', 'KH004', 'Lê Đóm Thái', '2023-01-18', 'Việt Nam', 'Sinh hoạt', 15, 80.0, 7, 10000),
	('HD005', 'KH005', 'Phạm Trù Triết Học', '2023-01-19', 'Việt Nam', 'Kinh doanh', 8, 120.0, 4, 5040),
	('HD006', 'KH001', 'John Doe', '2023-01-15', 'Mỹ', 'Kinh doanh', 10, 100.0, NULL, 1000.00),
	('HD007', 'KH002', 'Jane Smith', '2023-01-16', 'Anh', 'Kinh doanh', 5, 150.0, NULL, 750.00);

select*  from Hoadon;
/*DELETE FROM HoaDon WHERE MaHoaDon ='HD007';*/
SELECT * FROM HoaDon WHERE MaHoaDon ='HD001';