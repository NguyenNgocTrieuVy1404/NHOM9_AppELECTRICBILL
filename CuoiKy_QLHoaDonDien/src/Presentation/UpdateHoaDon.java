package Presentation;

import java.sql.SQLException;

import domain.HoaDonModel;
import domain.model.HoaDon;

public class UpdateHoaDon extends Command {
    
    public UpdateHoaDon(HoaDonModel hoaDonModel, HoaDon hoaDon) {
        super(hoaDonModel, hoaDon);
    }
    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        hoaDonModel.suaHoaDon(hoaDon);
    }
}
