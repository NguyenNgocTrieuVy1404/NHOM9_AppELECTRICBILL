package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import domain.model.HoaDon;

public class HoaDonPersistence_Impl implements HoaDonPersistence {
    private ConnectionDB connectionDB;
    //connection = ConnectionDB.getInstance().getConnection();

    public HoaDonPersistence_Impl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public List<HoaDon> layTatCaHoaDon() throws SQLException, ClassNotFoundException {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "SELECT * FROM HoaDon";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String maHoaDon = rs.getString("MaHoaDon");
            String maKhachHang = rs.getString("MaKhachHang");
            String hoTen = rs.getString("HoTen");
            String ngayraHD = rs.getString("NgayRaHoaDon");
            String quocTich = rs.getString("QuocTich");
            String doiTuong = rs.getString("DoiTuongKhachHang");
            int soLuong = rs.getInt("SoLuong");
            float donGia = rs.getFloat("DonGia");
            int dinhMuc = rs.getInt("DinhMuc");
            float thanhTien = rs.getFloat("ThanhTien");

            HoaDon hoaDon = new HoaDon(maHoaDon, maKhachHang, hoTen, ngayraHD, quocTich, doiTuong, soLuong,
                    donGia, dinhMuc, thanhTien);
            danhSachHoaDon.add(hoaDon);
        }
        return danhSachHoaDon;
    }

    @Override
    public void suaHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "UPDATE HoaDon SET MaKhachHang = ?, HoTen = ?, NgayRaHoaDon = ?, QuocTich = ?, DoiTuongKhachHang = ?, SoLuong = ?, DonGia = ?, DinhMuc = ?, ThanhTien = ? WHERE MaHoaDon = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, hoaDon.getMaKhachHang());
        preparedStatement.setString(2, hoaDon.getHoTen());
        preparedStatement.setString(3, hoaDon.getNgayRaHoaDon());
        preparedStatement.setString(4, hoaDon.getQuocTich());
        preparedStatement.setString(5, hoaDon.getDoiTuongKhachHang());
        preparedStatement.setInt(6, hoaDon.getSoLuong());
        preparedStatement.setDouble(7, hoaDon.getDonGia());
        preparedStatement.setInt(8, hoaDon.getDinhMuc());
        preparedStatement.setDouble(9, hoaDon.getThanhTien());
        preparedStatement.setString(10, hoaDon.getMaHoaDon());

        try {
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được cập nhật thành công!", "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn để cập nhật!", "Thông Báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            // Hiển thị thông báo lỗi nếu có ngoại lệ SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void themHoaDon(HoaDon hoaDon) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "INSERT INTO HoaDon (MaHoaDon, MaKhachHang, HoTen, NgayRaHoaDon, QuocTich, DoiTuongKhachHang, SoLuong, DonGia, DinhMuc, ThanhTien) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, hoaDon.getMaHoaDon());
        preparedStatement.setString(2, hoaDon.getMaKhachHang());
        preparedStatement.setString(3, hoaDon.getHoTen());
        preparedStatement.setString(4, hoaDon.getNgayRaHoaDon());
        preparedStatement.setString(5, hoaDon.getQuocTich());
        preparedStatement.setString(6, hoaDon.getDoiTuongKhachHang());
        preparedStatement.setInt(7, hoaDon.getSoLuong());
        preparedStatement.setDouble(8, hoaDon.getDonGia());
        preparedStatement.setInt(9, hoaDon.getDinhMuc());
        preparedStatement.setDouble(10, hoaDon.getThanhTien());

        try {
            preparedStatement.executeUpdate();
            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được thêm thành công!", "Thông Báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            // Hiển thị thông báo lỗi nếu có ngoại lệ SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<HoaDon> timKiemHoaDon(String hoTen) throws ClassNotFoundException, SQLException {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE HoTen LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + hoTen + "%");

        ResultSet rs = preparedStatement.executeQuery();
        boolean found = false;

        while (rs.next()) {
            String maHoaDon = rs.getString("MaHoaDon");
            String maKhachHang = rs.getString("MaKhachHang");
            String hoTenKH = rs.getString("HoTen");
            String ngayraHD = rs.getString("NgayRaHoaDon");
            String quocTich = rs.getString("QuocTich");
            String doiTuong = rs.getString("DoiTuongKhachHang");
            int soLuong = rs.getInt("SoLuong");
            float donGia = rs.getFloat("DonGia");
            int dinhMuc = rs.getInt("DinhMuc");
            float thanhTien = rs.getFloat("ThanhTien");

            HoaDon hoaDon = new HoaDon(maHoaDon, maKhachHang, hoTenKH, ngayraHD, quocTich, doiTuong, soLuong,
                    donGia, dinhMuc, thanhTien);
            danhSachHoaDon.add(hoaDon);
            found = true;
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng", "Thông Báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return danhSachHoaDon;
    }

    @Override
    public void xoaHoaDon(String maHoaDon) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, maHoaDon);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được xóa thành công!", "Thông Báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Hàm kết nối
    // public static Connection getConnection() throws SQLException, ClassNotFoundException {
    //     String url = "jdbc:mysql://localhost:3306/dbqlhoadontiendien";
    //     String user = "root";
    //     String password = "123456789@Vy";

    //     // Nạp driver JDBC của MySQL
    //     Class.forName("com.mysql.cj.jdbc.Driver");
    //     // Tạo kết nối
    //     Connection connection = DriverManager.getConnection(url, user, password);
    //     return connection;
    // }

    @Override
    public Map<String, Integer> tinhTongSoLuongTheoLoai() throws ClassNotFoundException, SQLException {
        Map<String, Integer> tongSoLuongMap = new HashMap<>();
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "SELECT DoiTuongKhachHang, SUM(SoLuong) AS TongSoLuong FROM HoaDon GROUP BY DoiTuongKhachHang";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String doiTuongKhachHang = rs.getString("DoiTuongKhachHang");
            int tongSoLuong = rs.getInt("TongSoLuong");
            tongSoLuongMap.put(doiTuongKhachHang, tongSoLuong);
            
        }
        return tongSoLuongMap;
    }

    @Override
    public Double avgThanhTienNuocNgoai() throws ClassNotFoundException, SQLException {
        Double trungBinhThanhTien =null;
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "SELECT AVG(ThanhTien) AS TrungBinhThanhTien\n" + //
                        "FROM HoaDon\n" + //
                        "WHERE QuocTich NOT LIKE 'Việt Nam';";
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            trungBinhThanhTien = rs.getDouble("TrungBinhThanhTien");
        }
        return trungBinhThanhTien;
    }

    @Override
    public List<HoaDon> xuatHoaDonByMonth(String thang) throws ClassNotFoundException, SQLException {
        List<HoaDon> listHoaDon = new ArrayList<>();
        Connection connection = null;
        connection = connectionDB.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE MONTH(NgayRaHoaDon) = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,thang);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String maHoaDon = rs.getString("MaHoaDon");
            String maKhachHang = rs.getString("MaKhachHang");
            String hoTen = rs.getString("HoTen");
            String ngayraHD = rs.getString("NgayRaHoaDon");
            String quocTich = rs.getString("QuocTich");
            String doiTuong = rs.getString("DoiTuongKhachHang");
            int soLuong = rs.getInt("SoLuong");
            float donGia = rs.getFloat("DonGia");
            int dinhMuc = rs.getInt("DinhMuc");
            float thanhTien = rs.getFloat("ThanhTien");

            HoaDon hoaDon = new HoaDon(maHoaDon, maKhachHang, hoTen, ngayraHD, quocTich, doiTuong, soLuong,
                    donGia, dinhMuc, thanhTien);
            listHoaDon.add(hoaDon);
        
        }
        return listHoaDon;
    }
}