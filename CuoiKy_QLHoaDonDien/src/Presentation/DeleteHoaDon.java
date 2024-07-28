package Presentation;

import java.sql.SQLException;

import domain.HoaDonModel;
import domain.model.HoaDon;

public class DeleteHoaDon extends Command {

    public DeleteHoaDon(HoaDonModel hoaDonModel, HoaDon hoaDon) {
        super(hoaDonModel, hoaDon);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        hoaDonModel.xoaHoaDon(hoaDon.getMaHoaDon());
    }

}
