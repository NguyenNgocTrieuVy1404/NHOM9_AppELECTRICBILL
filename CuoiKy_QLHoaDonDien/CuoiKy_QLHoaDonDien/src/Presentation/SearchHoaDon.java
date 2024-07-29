package Presentation;

import java.sql.SQLException;
import java.util.List;

import domain.HoaDonModel;
import domain.model.HoaDon;

public class SearchHoaDon extends Command{
    private List<HoaDon> result;
    public SearchHoaDon(HoaDonModel hoaDonModel, HoaDon hoaDon) {
        super(hoaDonModel, hoaDon);
    }

    @Override
    public void execute() throws ClassNotFoundException, SQLException {
        result = hoaDonModel.timKiemHoaDon(hoaDon.getHoTen());   
    }

    public List<HoaDon> getResult() {
        return result;
    }

}
