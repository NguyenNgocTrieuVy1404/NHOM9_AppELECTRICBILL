package domain;

import domain.model.HoaDon;
public class ThanhTienNuocNgoai extends ThanhTien{

    @Override
    public void ThanhTien(HoaDon hoaDon) {
        int soLuong = hoaDon.getSoLuong();
        double donGia = hoaDon.getDonGia();
    
        result = soLuong * donGia;
    }

    public double getResult() {
        return result;
    }
    
}
