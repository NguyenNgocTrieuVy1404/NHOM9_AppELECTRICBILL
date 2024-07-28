package domain;

import domain.model.HoaDon;

public class ThanhTienVietNam extends ThanhTien{

    @Override
    public void ThanhTien(HoaDon hoaDon) {
        int soLuong = hoaDon.getSoLuong();
        double donGia = hoaDon.getDonGia();
        int dinhMuc = hoaDon.getDinhMuc();
    
        if (soLuong <= dinhMuc) {
            result =  soLuong * donGia;
        } else {
            result = soLuong * donGia * dinhMuc + (soLuong - dinhMuc) * donGia * 2.5;
        }
    }

    public double getResult() {
        return result;
    } 
}

