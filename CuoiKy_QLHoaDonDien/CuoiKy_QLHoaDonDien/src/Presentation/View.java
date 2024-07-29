package Presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Persistence.ConnectionDB;
import Persistence.HoaDonPersistence_Impl;
import Persistence.HoaDonPersistence;
import domain.*;
import domain.model.HoaDon;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private JPanel jmainPanel;
    private JMenuBar menuBar = null;
    private JLabel maHDLabel, maKHLabel, hoTenLabel, ngayHDLabel, doiTuongLabel, soLuongLabel, donGiaLabel,
            thanhTienLabel, dinhMucLabel, quocTichJLabel;
    private JTextField txtMaHoaDon, txtMaKhachHang, txtHoTen, txtSoLuong, txtDonGia, txtThanhtien,
            txtDinhMuc, txtQuocTich;
    private JSpinner spinnerNgayHoaDon;
    private JComboBox<String> comboDoiTuong;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnTinhTien, btnReset;
    private DefaultTableModel jTableModel;
    private JTable jTableSet;

    private HoaDonModel hoaDonModel;
    private CommandProcessor commandProcessorRemote;

    public void setCommandProcessorRemote(CommandProcessor commandProcessorRemote) {
        this.commandProcessorRemote = commandProcessorRemote;
    }

    public void setHoaDonModel(HoaDonModel hoaDonModel) {
        this.hoaDonModel = hoaDonModel;
        ((Observable) hoaDonModel).addObserver(this);
        SelectAllHoaDon();
    }

    public View() {
        buildPanel();
        add(jmainPanel);
        setJMenuBar(menuBar);
        setTitle("Quản Lý Hóa Đơn Tiền Điện");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void buildPanel() {
        JPanel jPanelRemote = new JPanel(new GridBagLayout());
        JMenuBar menuBar = new JMenuBar();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JMenu menu = new JMenu("Chức năng khác");
        JMenuItem menuItemTongSoLuong = new JMenuItem("Tính tổng số lượng cho từng loại khách hàng");
        JMenuItem menuItemTrungBinhThanhTien = new JMenuItem(
                "Tính trung bình thành tiền của khách hàng người nước ngoài");
        JMenuItem menuItemHoaDonThang = new JMenuItem("Xuất ra các hóa đơn trong tháng");

        menu.add(menuItemTongSoLuong);
        menu.add(menuItemTrungBinhThanhTien);
        menu.add(menuItemHoaDonThang);
        menuBar.add(menu);
        jPanelRemote.add(menuBar);
        gbc.gridy++;
        gbc.gridx = 0;

        gbc.gridx++;
        JLabel titleLabel = new JLabel("HÓA ĐƠN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        jPanelRemote.add(titleLabel, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        maHDLabel = new JLabel("Mã Hóa Đơn:");
        jPanelRemote.add(maHDLabel, gbc);
        gbc.gridx++;
        txtMaHoaDon = new JTextField(20);
        jPanelRemote.add(txtMaHoaDon, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        maKHLabel = new JLabel("Mã Khách Hàng:");
        jPanelRemote.add(maKHLabel, gbc);
        gbc.gridx++;
        txtMaKhachHang = new JTextField(20);
        jPanelRemote.add(txtMaKhachHang, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        hoTenLabel = new JLabel("Họ Tên:");
        jPanelRemote.add(hoTenLabel, gbc);
        gbc.gridx++;
        txtHoTen = new JTextField(20);
        jPanelRemote.add(txtHoTen, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        ngayHDLabel = new JLabel("Ngày Ra Hóa Đơn:");
        jPanelRemote.add(ngayHDLabel, gbc);
        gbc.gridx++;
        spinnerNgayHoaDon = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerNgayHoaDon, "yyyy-MM-dd");
        spinnerNgayHoaDon.setEditor(dateEditor);
        jPanelRemote.add(spinnerNgayHoaDon, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        quocTichJLabel = new JLabel("Quốc Tịch:");
        jPanelRemote.add(quocTichJLabel, gbc);
        gbc.gridx++;
        txtQuocTich = new JTextField(20);
        jPanelRemote.add(txtQuocTich, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        doiTuongLabel = new JLabel("Đối Tượng:");
        jPanelRemote.add(doiTuongLabel, gbc);
        gbc.gridx++;
        // Tạo JComboBox với các giá trị sẵn có
        String[] doiTuongOptions = { "Sinh hoạt", "Kinh doanh", "Sản xuất" };
        comboDoiTuong = new JComboBox<>(doiTuongOptions);
        jPanelRemote.add(comboDoiTuong, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        soLuongLabel = new JLabel("Số Lượng:");
        jPanelRemote.add(soLuongLabel, gbc);
        gbc.gridx++;
        txtSoLuong = new JTextField(20);
        jPanelRemote.add(txtSoLuong, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        donGiaLabel = new JLabel("Đơn Giá:");
        jPanelRemote.add(donGiaLabel, gbc);
        gbc.gridx++;
        txtDonGia = new JTextField(20);
        jPanelRemote.add(txtDonGia, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        dinhMucLabel = new JLabel("Định Mức:");
        jPanelRemote.add(dinhMucLabel, gbc);
        gbc.gridx++;
        txtDinhMuc = new JTextField(20);
        jPanelRemote.add(txtDinhMuc, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        thanhTienLabel = new JLabel("Thành Tiền:");
        jPanelRemote.add(thanhTienLabel, gbc);
        gbc.gridx++;
        txtThanhtien = new JTextField(20);
        txtThanhtien.setEnabled(false);
        jPanelRemote.add(txtThanhtien, gbc);
        btnTinhTien = new JButton("Tính tiền");
        gbc.gridx++;
        gbc.gridx++;

        // Button
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnSearch = new JButton("Tìm kiếm");
        btnTinhTien = new JButton("Tính tiền");
        btnReset = new JButton("Reset");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnTinhTien);
        buttonPanel.add(btnReset);

        // Table
        String[] columnNames = { "Mã Hóa Đơn", "Mã KH", "Tên", "Ngày Ra HD", "Quốc Tịch", "Đối Tượng", "Số Lượng",
                "Đơn Giá", "Định Mức", "Thành Tiền" };
        jTableModel = new DefaultTableModel(columnNames, 0);
        jTableSet = new JTable(jTableModel);

        jmainPanel = new JPanel(new BorderLayout());

        jmainPanel.add(new JScrollPane(jTableSet), BorderLayout.CENTER);
        jmainPanel.add(buttonPanel, BorderLayout.SOUTH);
        jmainPanel.add(jPanelRemote, BorderLayout.NORTH);

        // Action Listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddHoaDon();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    suaHoaDon();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    xoaHoaDon();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemHoaDon();
            }
        });
        btnTinhTien.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ThanhTien();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jTableModel.setRowCount(0);
                SelectAllHoaDon();
            }
        });

        menuItemTongSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tongSoLuongTungLoaiKH();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuItemTrungBinhThanhTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    avgThanhTienNuocNgoai();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuItemHoaDonThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuatHoaDonByMonth();
            }
        });

        jTableSet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && jTableSet.getSelectedRow() != -1) {
                    int selectedRow = jTableSet.getSelectedRow();

                    // Lấy dữ liệu từ dòng đã chọn
                    String maHoaDon = jTableSet.getValueAt(selectedRow, 0).toString();
                    String maKhachHang = jTableSet.getValueAt(selectedRow, 1).toString();
                    String hoTen = jTableSet.getValueAt(selectedRow, 2).toString();
                    String ngayHD = jTableSet.getValueAt(selectedRow, 3).toString();
                    String quocTich = jTableSet.getValueAt(selectedRow, 4).toString();
                    String doiTuong = jTableSet.getValueAt(selectedRow, 5).toString();
                    String soLuong = jTableSet.getValueAt(selectedRow, 6).toString();
                    String donGia = jTableSet.getValueAt(selectedRow, 7).toString();
                    String dinhMuc = jTableSet.getValueAt(selectedRow, 8).toString();
                    String thanhTien = jTableSet.getValueAt(selectedRow, 9).toString();

                    // Cập nhật các JTextField
                    txtMaHoaDon.setText(maHoaDon);
                    txtMaKhachHang.setText(maKhachHang);
                    txtHoTen.setText(hoTen);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date ngayRaHoaDon;
                    try {
                        ngayRaHoaDon = sdf.parse(ngayHD);
                        if (ngayRaHoaDon != null) {
                            spinnerNgayHoaDon.setValue(ngayRaHoaDon);
                        } else {
                            System.out.println("Ngày hóa đơn không hợp lệ: " + ngayHD);
                        }

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    txtQuocTich.setText(quocTich);
                    comboDoiTuong.setSelectedItem(doiTuong);
                    txtSoLuong.setText(soLuong);
                    txtDonGia.setText(donGia);
                    txtDinhMuc.setText(dinhMuc);
                    txtThanhtien.setText(thanhTien);
                }
            }
        });
    }

    private void SelectAllHoaDon() {
        try {
            List<HoaDon> danhSachHoaDon = hoaDonModel.layTatCaHoaDon();
            System.out.println("Số lượng bản ghi: " + danhSachHoaDon.size());
            // xóa vs làm mới
            jTableModel.setRowCount(0);
            for (HoaDon hoaDon : danhSachHoaDon) {
                Object[] rowData = {
                        hoaDon.getMaHoaDon(),
                        hoaDon.getMaKhachHang(),
                        hoaDon.getHoTen(),
                        hoaDon.getNgayRaHoaDon(),
                        hoaDon.getQuocTich(),
                        hoaDon.getDoiTuongKhachHang(),
                        hoaDon.getSoLuong(),
                        hoaDon.getDonGia(),
                        hoaDon.getDinhMuc(),
                        hoaDon.getThanhTien()
                };
                jTableModel.addRow(rowData);
            }
            jTableModel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void AddHoaDon() throws ClassNotFoundException, SQLException {
        try {
            String maHoaDon = txtMaHoaDon.getText();
            String maKhachHang = txtMaKhachHang.getText();
            String hoTen = txtHoTen.getText();
            String quocTich = txtQuocTich.getText();
            String doiTuongKhachHang = (String) comboDoiTuong.getSelectedItem();
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            int dinhMuc = Integer.parseInt(txtDinhMuc.getText());
            double thanhTien = Double.parseDouble(txtThanhtien.getText());
            
            java.util.Date utilDate = (java.util.Date) spinnerNgayHoaDon.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayRaHoaDonStr = dateFormat.format(utilDate);
            
            HoaDon hoaDon = new HoaDon(maHoaDon, maKhachHang, hoTen, ngayRaHoaDonStr, quocTich, doiTuongKhachHang,
                    soLuong,
                    donGia, dinhMuc, thanhTien);

            Command addHoaDon = new AddHoaDon(hoaDonModel, hoaDon);
            commandProcessorRemote.execute(addHoaDon);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaHoaDon() throws ClassNotFoundException, SQLException {
        try {
            String maHoaDon = txtMaHoaDon.getText();

            HoaDon hoaDon = new HoaDon(maHoaDon, null, null, null, null, null, 0, 0, 0, 0);
            Command xoaHoaDon = new DeleteHoaDon(hoaDonModel, hoaDon);
            commandProcessorRemote.execute(xoaHoaDon);
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa hóa đơn: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void timKiemHoaDon() {
        try {
            String hoTen = JOptionPane.showInputDialog(this, "Nhập tên khách hàng để tìm:");
            HoaDon hoaDon = new HoaDon(null, null, hoTen, null, null, null, 0, 0, 0, 0);
            if (hoTen != null && !hoTen.isEmpty()) {
                // List<HoaDon> hoaDon = hoaDonModel.timKiemHoaDon(hoTen);
                Command search = new SearchHoaDon(hoaDonModel, hoaDon);
                commandProcessorRemote.execute(search);
                List<HoaDon> hoaDonList = ((SearchHoaDon) search).getResult();
                if (hoaDonList != null && !hoaDonList.isEmpty()) {
                    jTableModel.setRowCount(0);
                    for (HoaDon item : hoaDonList) {
                        Object[] rowData = {
                                item.getMaHoaDon(),
                                item.getMaKhachHang(),
                                item.getHoTen(),
                                item.getNgayRaHoaDon(),
                                item.getQuocTich(),
                                item.getDoiTuongKhachHang(),
                                item.getSoLuong(),
                                item.getDonGia(),
                                item.getDinhMuc(),
                                item.getThanhTien()
                        };
                        jTableModel.addRow(rowData);
                    }
                    jTableModel.fireTableDataChanged();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaHoaDon() throws ClassNotFoundException, SQLException {
        try {
            String maHoaDon = txtMaHoaDon.getText();
            String maKhachHang = txtMaKhachHang.getText();
            String hoTen = txtHoTen.getText();
            String quocTich = txtQuocTich.getText();
            String doiTuongKhachHang = (String) comboDoiTuong.getSelectedItem();
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            int dinhMuc = Integer.parseInt(txtDinhMuc.getText());
            double thanhTienKH = Double.parseDouble(txtThanhtien.getText());

            java.util.Date utilDate = (java.util.Date) spinnerNgayHoaDon.getValue();
            // Định dạng ngày tháng thành chuỗi
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayRaHoaDonStr = dateFormat.format(utilDate);

            HoaDon hoaDon = new HoaDon(maHoaDon, maKhachHang, hoTen, ngayRaHoaDonStr, quocTich, doiTuongKhachHang,
                    soLuong,
                    donGia, dinhMuc, thanhTienKH);
            Command suaHoaDon = new UpdateHoaDon(hoaDonModel, hoaDon);
            commandProcessorRemote.execute(suaHoaDon);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ThanhTien() throws ClassNotFoundException, SQLException {
        String quocTich = txtQuocTich.getText();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        double donGia = Double.parseDouble(txtDonGia.getText());
        int dinhMuc = Integer.parseInt(txtDinhMuc.getText());

        HoaDon hoaDon = new HoaDon(null, null, null, null, quocTich, null, soLuong, donGia, dinhMuc, 0);
        if (quocTich.equals("Việt Nam")) {
            ThanhTien thanhTien = new ThanhTienVietNam();
            Command tinhtien = new ThanhTienVN(hoaDon, thanhTien);
            commandProcessorRemote.execute(tinhtien);
            double result = thanhTien.getResult();
            JOptionPane.showMessageDialog(null, " Tổng tiền là: " + result, "Thông Báo",
                    JOptionPane.INFORMATION_MESSAGE);
            txtThanhtien.setText("" + result);
        } else {
            ThanhTien thanhTien1 = new ThanhTienNuocNgoai();
            Command tinhtien = new ThanhTienNN(hoaDon, thanhTien1);
            commandProcessorRemote.execute(tinhtien);
            double result = thanhTien1.getResult();
            JOptionPane.showMessageDialog(null, " Tổng tiền là: " + result, "Thông Báo",
                    JOptionPane.INFORMATION_MESSAGE);
            txtThanhtien.setText("" + result);
        }
    }

    private void tongSoLuongTungLoaiKH() throws ClassNotFoundException, SQLException {
        Map<String, Integer> tongSoLuong = hoaDonModel.tinhTongSoLuongTheoLoai();
        StringBuilder sb = new StringBuilder();
        sb.append("Tổng số lượng theo loại khách:\n");
        for (Map.Entry<String, Integer> entry : tongSoLuong.entrySet()) {
            String loai = entry.getKey();
            int SoLuong = entry.getValue();
            sb.append(loai + ": " + SoLuong + "\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void avgThanhTienNuocNgoai() throws ClassNotFoundException, SQLException {
        Double avg = hoaDonModel.avgThanhTienNuocNgoai();
        String message = "Trung bình thành tiền của khách hàng người nước ngoài là: " + avg;
        JOptionPane.showMessageDialog(null, message, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void xuatHoaDonByMonth() {
        String thang = JOptionPane.showInputDialog(null, "Nhập tháng:", "Nhập tháng",
                JOptionPane.QUESTION_MESSAGE);
        if (thang != null && !thang.trim().isEmpty()) {
            try {
                List<HoaDon> hoaDonList = hoaDonModel.xuatHoaDonByMonth(thang);
                if (hoaDonList == null || hoaDonList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không có dữ liệu cho tháng " + thang, "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                jTableModel.setRowCount(0);
                for (HoaDon item : hoaDonList) {
                    Object[] rowData = {
                            item.getMaHoaDon(),
                            item.getMaKhachHang(),
                            item.getHoTen(),
                            item.getNgayRaHoaDon(),
                            item.getQuocTich(),
                            item.getDoiTuongKhachHang(),    
                            item.getSoLuong(),
                            item.getDonGia(),
                            item.getDinhMuc(),
                            item.getThanhTien()
                    };
                    jTableModel.addRow(rowData);
                }
                jTableModel.fireTableDataChanged();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearFields() {
        txtMaHoaDon.setText("");
        txtMaKhachHang.setText("");
        txtHoTen.setText("");
        txtQuocTich.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtDinhMuc.setText("");
        txtThanhtien.setText("");
    }

    @Override
    public void update(Observable o, Object arg) {
        SelectAllHoaDon();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        CommandProcessor commandProcessorRemote = CommandProcessor.makeCommandProcessor();
        ConnectionDB connectionDB = ConnectionDB.getInstance();
        HoaDonPersistence hoaDonPersistence = new HoaDonPersistence_Impl(connectionDB);
        HoaDonModel hoaDonModel = new HoaDonModel_Impl(hoaDonPersistence);
        View view = new View();
        view.setHoaDonModel(hoaDonModel);
        view.setCommandProcessorRemote(commandProcessorRemote);
        
    }
}