package Persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.model.HoaDon;

public interface HoaDonPersistence {
    // Thêm hóa đơn
    void themHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException;
    
    // Xóa hóa đơn theo mã hóa đơn
    void xoaHoaDon(String maHoaDon) throws ClassNotFoundException, SQLException;
    
    // Sửa hóa đơn
    void suaHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException;
    
    // Tìm kiếm hóa đơn theo tên KH
    List<HoaDon> timKiemHoaDon(String hoTen) throws ClassNotFoundException, SQLException;
    
    // Lấy danh sách tất cả hóa đơn
    List<HoaDon> layTatCaHoaDon() throws SQLException, ClassNotFoundException;

    // Tổng số lượng cho từng loại khách
    Map<String,Integer> tinhTongSoLuongTheoLoai() throws ClassNotFoundException, SQLException;

    //Tính trung bình thành tiền của khách hàng người nước ngoài.
    Double avgThanhTienNuocNgoai() throws ClassNotFoundException, SQLException;

    //Xuất ra các hoá đơn trong tháng nào đó.
    List<HoaDon> xuatHoaDonByMonth(String thang) throws ClassNotFoundException, SQLException;
    
}
