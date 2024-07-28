package Presentation;

import java.sql.SQLException;

import domain.HoaDonModel;
import domain.model.*;


public class AddHoaDon extends Command{
    public AddHoaDon(HoaDonModel hoaDonModel, HoaDon hoaDon) {
        super(hoaDonModel, hoaDon);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        hoaDonModel.themHoaDon(hoaDon); 
    }

}
