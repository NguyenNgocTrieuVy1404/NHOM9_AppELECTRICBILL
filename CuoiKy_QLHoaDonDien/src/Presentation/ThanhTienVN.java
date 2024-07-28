package Presentation;

import java.sql.SQLException;

import domain.ThanhTien;
import domain.model.HoaDon;

public class ThanhTienVN extends Command{

    public ThanhTienVN(HoaDon hoaDon, ThanhTien thanhTien) {
        super(hoaDon, thanhTien);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        thanhTien.ThanhTien(hoaDon);
    }

}
