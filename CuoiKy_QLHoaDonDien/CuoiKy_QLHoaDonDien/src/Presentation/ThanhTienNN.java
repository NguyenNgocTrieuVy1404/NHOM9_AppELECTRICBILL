package Presentation;

import java.sql.SQLException;

import domain.ThanhTien;
import domain.model.HoaDon;

public class ThanhTienNN extends Command {

    public ThanhTienNN(HoaDon hoaDon, ThanhTien thanhTien) {
        super(hoaDon, thanhTien);
    }
    
    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        thanhTien.ThanhTien(hoaDon);
    }

}
