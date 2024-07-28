package domain;

import domain.model.HoaDon;

public abstract class ThanhTien {
    protected double result;

    public abstract void ThanhTien(HoaDon hoaDon);

    public double getResult() {
        return result;
    }
}