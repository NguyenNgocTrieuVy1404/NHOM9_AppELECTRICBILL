package Presentation;

import java.sql.SQLException;

import domain.HoaDonModel;
import domain.ThanhTien;
import domain.model.HoaDon;

public abstract class Command {
    protected HoaDonModel hoaDonModel;
    protected HoaDon hoaDon;
    protected ThanhTien thanhTien;
    
    public Command(HoaDon hoaDon,ThanhTien thanhTien){
        this.hoaDon = hoaDon;
        this.thanhTien = thanhTien;
    }

    public Command(HoaDonModel hoaDonModel, HoaDon hoaDon){
        this.hoaDonModel = hoaDonModel;
        this.hoaDon = hoaDon;
    }
    
    public abstract void execute() throws ClassNotFoundException, SQLException;

}
