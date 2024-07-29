package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import Persistence.HoaDonPersistence;
import domain.model.HoaDon;

public class HoaDonModel_Impl extends Observable implements HoaDonModel{
    private HoaDonPersistence hoaDonPersistence;
    
    public HoaDonModel_Impl(HoaDonPersistence hoaDonPersistence){
        this.hoaDonPersistence = hoaDonPersistence;
    }
    @Override
    public void themHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException{
        hoaDonPersistence.themHoaDon(hoaDon);
        changeState();
    }

    @Override
    public void xoaHoaDon(String maHoaDon) throws ClassNotFoundException, SQLException {
        hoaDonPersistence.xoaHoaDon(maHoaDon);
        changeState();
    }

    @Override
    public void suaHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException {
        hoaDonPersistence.suaHoaDon(hoaDon);
        changeState();
    }
    @Override
    public List<HoaDon> timKiemHoaDon(String hoTen) throws ClassNotFoundException, SQLException {
        return hoaDonPersistence.timKiemHoaDon(hoTen);
    }
    
    @Override
    public List<HoaDon> layTatCaHoaDon() throws ClassNotFoundException, SQLException {
        return hoaDonPersistence.layTatCaHoaDon();
    }

    @Override
    public Map<String, Integer> tinhTongSoLuongTheoLoai() throws ClassNotFoundException, SQLException {
        return hoaDonPersistence.tinhTongSoLuongTheoLoai();
    }
    @Override
    public Double avgThanhTienNuocNgoai() throws ClassNotFoundException, SQLException {
        return hoaDonPersistence.avgThanhTienNuocNgoai();
    }
    @Override
    public List<HoaDon> xuatHoaDonByMonth(String thang) throws ClassNotFoundException, SQLException {
        return hoaDonPersistence.xuatHoaDonByMonth(thang);
    }

    public void changeState(){
        setChanged();
        notifyObservers();
    }

}
