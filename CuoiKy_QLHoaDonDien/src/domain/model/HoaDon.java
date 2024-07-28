package domain.model;

public class HoaDon {
    private String maHoaDon;
    private String maKhachHang;
    private String hoTen;
    private String ngayRaHoaDon;
    private String quocTich;
    private String doiTuongKhachHang;
    private int soLuong;
    private double donGia;
    private int dinhMuc;
    private double thanhTien;

    // Constructor 
    public HoaDon(String maHoaDon, String maKhachHang, String hoTen, String ngayRaHoaDon,
                  String quocTich, String doiTuongKhachHang, int soLuong, double donGia,
                  int dinhMuc, double thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.ngayRaHoaDon = ngayRaHoaDon;
        this.quocTich = quocTich;
        this.doiTuongKhachHang = doiTuongKhachHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.dinhMuc = dinhMuc;
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNgayRaHoaDon() {
        return ngayRaHoaDon;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public String getDoiTuongKhachHang() {
        return doiTuongKhachHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getDinhMuc() {
        return dinhMuc;
    }

    public double getThanhTien() {
        return thanhTien;
    }
}
