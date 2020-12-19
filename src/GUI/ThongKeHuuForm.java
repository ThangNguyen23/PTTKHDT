//Đang sửa
package GUI;

import BUS.*;
import DTO.*;
import GUI.GUIMyTable;
import Report.PriceFormatter;
import button.MoreButton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import button.DateButton;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author DELL
 */
public class ThongKeHuuForm {

}

class ThongKeSanPham extends JPanel {
    SanPhamBUS qlspBUS = new SanPhamBUS();
    NhanVienBUS qlnvBUS = new NhanVienBUS();
    KhachHangBUS qlkhBUS = new KhachHangBUS();
    HoaDonBUS qlhdBUS = new HoaDonBUS();
    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
    HoaDonNhapBUS qlhdnBUS = new HoaDonNhapBUS();
    ChiTietHoaDonBUS qlcthdBUS = new ChiTietHoaDonBUS();
    ChiTietHoaDonNhapBUS qlcthdnBUS = new ChiTietHoaDonNhapBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    GUIMyTable tb;

    public ThongKeSanPham() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel các nút
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Nguyệu liệu nhập", "Sản phẩm bán ra"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

//        panel tìm kiếm theo thời gian
        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        btnRefresh.addActionListener((ae) -> {
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thống kê từng cái
        tb = new GUIMyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    
    private void soLuongSanPhamNhap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã phiếu nhập", "Tên nhà cung cấp", "Ngày nhập", "Số lượng", "Ðơn giá", "Tổng chi"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);//tìm kiếm theo thời gian

        int tongTatCa = 0;
        float tongTien = 0;
        for (SanPhamDTO nl : qlspBUS.getSanPhamDTO()) {
            int tongSoLuong = 0;
            float tongtiennguyenlieu = 0;
            tb.addRow(new String[]{nl.getIDSanPham(), nl.getTenSanPham(), "", "", "", "", PriceFormatter.format((float) nl.getGia()), ""});
            
            for (HoaDonNhapDTO hdn : qlhdnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                ChiTietHoaDonNhapDTO cthdn = qlcthdnBUS.getChiTiet(hdn.getIDHoaDonNhap(), nl.getIDSanPham());
      
                if (cthdn != null) {
                    tb.addRow(new String[]{"", "",
                        cthdn.getIDHoaDonNhap(),
                        qlnccBUS.getNhaCungCapDTO(hdn.getIDNhaCungCap()).getTenNhaCungCap(),
                        String.valueOf(hdn.getNgayNhap()),
                        String.valueOf(cthdn.getSoLuong()+ " chiếc "),
                        "",
                       PriceFormatter.format((float)cthdn.getSoLuong() * cthdn.getGiaNhap())

                    });
                    tongSoLuong += cthdn.getSoLuong();
                    tongtiennguyenlieu += cthdn.getSoLuong() * cthdn.getGiaNhap();
                }                  
                            
            }

            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong)+ " chiếc ", "",PriceFormatter.format(tongtiennguyenlieu)});
            tb.addRow(new String[]{"", "", "", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
            tongTien += tongtiennguyenlieu;
        }
        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)+ " chiếc ","",PriceFormatter.format(tongTien)});
    }

    private void soLuongSanPhamBan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã hóa don", "Tên nhân viên", "Ngày lập", "Số lượng", "Ðơn giá", "Tổng thu"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        float tongTien = 0;
        for (SanPhamDTO ma : qlspBUS.getSanPhamDTO()) {
            int tongSoLuong = 0;
            float tongTienHoaDonTungSanPham = 0;
            tb.addRow(new String[]{ma.getIDSanPham(), ma.getTenSanPham(), "", "", "", "", PriceFormatter.format(ma.getGia()), ""});

            for (HoaDonDTO hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayTu(), -1, -1)) {
                ChiTietHoaDonDTO cthd = qlcthdBUS.getChiTiet(hd.getIDHoaDon(), ma. getIDSanPham());
                if (cthd != null) {
                    tb.addRow(new String[]{"", "",
                        hd.getIDHoaDon(),
                        qlnvBUS.getNhanVienDTO(hd.getIDNhanVien()).getTenNhanVien(),
                        String.valueOf(hd.getNgayBan()),
                        String.valueOf(cthd.getSoLuong()+ " chiếc "), "", PriceFormatter.format(cthd.getSoLuong() * cthd.getDonGia())
                    });
                    tongSoLuong += cthd.getSoLuong();
                    tongTienHoaDonTungSanPham += cthd.getSoLuong() * cthd.getDonGia();
                }                              
            }

            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong)+ " chiếc ", "", PriceFormatter.format(tongTienHoaDonTungSanPham)});
            tb.addRow(new String[]{"", "", "", "", "", ""});
            tongTatCa += tongSoLuong;
            tongTien += tongTienHoaDonTungSanPham;
        }

        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)+ " chiếc ","",  PriceFormatter.format(tongTien)});

    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Nguyệu liệu nhập")) {
            soLuongSanPhamNhap();
        }
        if (cbTieuChi.getSelectedItem().equals("Sản phẩm bán ra")) {
            soLuongSanPhamBan();
        }
    }
}

class ThongKeNhanVien extends JPanel {

    SanPhamBUS qlmaBUS = new SanPhamBUS();
    NhanVienBUS qlnvBUS = new NhanVienBUS();
    KhachHangBUS qlkhBUS = new KhachHangBUS();
    HoaDonBUS qlhdBUS = new HoaDonBUS();
    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
    HoaDonNhapBUS qlhdnBUS = new HoaDonNhapBUS();
    ChiTietHoaDonBUS qlcthdBUS = new ChiTietHoaDonBUS();
    ChiTietHoaDonNhapBUS qlcthdnBUS = new ChiTietHoaDonNhapBUS();
    SanPhamBUS qlnlBUS= new SanPhamBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    GUIMyTable tb;

    public ThongKeNhanVien() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Sản phẩm"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        btnRefresh.addActionListener((ae) -> {
            try {
                qlmaBUS.docDSSanPham();
                qlnvBUS.docDSNV();
                qlhdBUS.docHD();
                qlcthdBUS.docCTHD();
            } catch (Exception ex) {
                Logger.getLogger(ThongKeNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new GUIMyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    public void tongTienTungNhanVien_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        for (NhanVienDTO nv : qlnvBUS.getNhanVienDTO()) {
            float tongTien = 0;
            tb.addRow(new String[]{nv.getIDNhanVien(), nv.getTenNhanVien(), "", ""});

            for (HoaDonDTO hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (nv.getIDNhanVien().equals(hd.getIDNhanVien())) {
                    tb.addRow(new String[]{"", "",
                        hd.getIDHoaDon(),
                        String.valueOf(hd.getNgayBan()),
                        PriceFormatter.format((float) hd.getThanhTien())
                    });
                    tongTien += hd.getThanhTien();
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
            tb.addRow(new String[]{"", "", "", "", ""});

            tongTatCa += tongTien;
        }
        tb.addRow(new String[]{"", "", "", "Tổng thu", PriceFormatter.format(tongTatCa)});
    }

    public void SanPhamCuaTungNhanVien_searchOnChange() {
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng "});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;
        
        for (NhanVienDTO nv : qlnvBUS.getNhanVienDTO()) {
            int tongSoLuong = 0;
            tb.addRow(new String[]{nv.getIDNhanVien(), nv.getTenNhanVien(), "", "", "", "", ""});

            for (HoaDonDTO hd : qlhdBUS.search("Mã nhân viên", nv.getIDNhanVien(), mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) { // tương đối -> sai
                tb.addRow(new String[]{"", "", hd.getIDHoaDon(), String.valueOf(hd.getNgayBan()), "", "", ""});

                for (ChiTietHoaDonDTO cthd : qlcthdBUS.search("Mã hóa đơn", hd.getIDHoaDon(), -1, -1, -1, -1)) { // tương đối -> sai
                    tongSoLuong += cthd.getSoLuong();
                    tb.addRow(new String[]{"", "", "", "",
                        cthd.getIDSanPham(),
                        qlmaBUS.getSanPhamDTO(cthd.getIDSanPham()).getTenSanPham(),
                        String.valueOf(cthd.getSoLuong())+" chiếc"
                    });
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "Tổng số sản phẩm", String.valueOf(tongSoLuong)+" chiếc"});
            tb.addRow(new String[]{"", "", "", "", "", "",""});

            tongTatCa += tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "Tổng bán ra", String.valueOf(tongTatCa)+" chiếc"});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungNhanVien_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Sản phẩm")) {
            SanPhamCuaTungNhanVien_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeKhachHang extends JPanel {

    SanPhamBUS qlmaBUS = new SanPhamBUS();
    NhanVienBUS qlnvBUS = new NhanVienBUS();
    KhachHangBUS qlkhBUS = new KhachHangBUS();
    HoaDonBUS qlhdBUS = new HoaDonBUS();
    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
    HoaDonNhapBUS qlhdnBUS = new HoaDonNhapBUS();
    ChiTietHoaDonBUS qlcthdBUS = new ChiTietHoaDonBUS();
    ChiTietHoaDonNhapBUS qlcthdnBUS = new ChiTietHoaDonNhapBUS();
    SanPhamBUS qlnlBUS= new SanPhamBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    GUIMyTable tb;

    public ThongKeKhachHang() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Sản phẩm đã đặt"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        btnRefresh.addActionListener((ae) -> {
            try {
                qlmaBUS.docDSSanPham();
                qlcthdBUS.docCTHD();
                qlhdBUS.docHD();
                qlkhBUS.docDSKH();
            } catch (Exception ex) {
                Logger.getLogger(ThongKeKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new GUIMyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    //Thong ke tong tien hoa don cua tung khach hang
    public void tongTienTungKhachHang_searchOnChange() {
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        for (KhachHangDTO kh : qlkhBUS.getKhachHangDTO()) {
            float tongTien = 0;
            tb.addRow(new String[]{kh.getIDKhachHang(), kh.getTenKhachHang(), "", "", ""});

            for (HoaDonDTO hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (kh.getIDKhachHang().equals(hd.getIDKhachHang())) {
                    tb.addRow(new String[]{"", "",
                        hd.getIDHoaDon(),
                        String.valueOf(hd.getNgayBan()),
                        PriceFormatter.format((float) hd.getThanhTien())
                    });
                    tongTien += hd.getThanhTien();
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongTien;
        }

        tb.addRow(new String[]{"", "", "", "Tổng thanh toán", PriceFormatter.format(tongTatCa)});
    }

    //Thong ke san pham va so luong mua cua tung khach hang
    public void SanPhamCuaTungKhachHang_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng "});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;
        //sai ở search hóa đơn bus
        for (KhachHangDTO kh : qlkhBUS.getKhachHangDTO()) {
            int tongSoLuong = 0;
            tb.addRow(new String[]{kh.getIDKhachHang(), kh.getTenKhachHang(), "", "", "", "", ""});

            for (HoaDonDTO hd : qlhdBUS.search("Mã khách hàng", kh.getIDKhachHang(), mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) { // tương đối -> sai 
                tb.addRow(new String[]{"", "", hd.getIDHoaDon(), String.valueOf(hd.getNgayBan()), "", "", ""});

                for (ChiTietHoaDonDTO cthd : qlcthdBUS.search("Mã hóa đơn", hd.getIDHoaDon(), -1, -1, -1, -1)) { // tương đối -> sai
                    tongSoLuong += cthd.getSoLuong();
                    tb.addRow(new String[]{"", "", "", "",
                        cthd.getIDSanPham(),
                        qlmaBUS.getSanPhamDTO(cthd.getIDSanPham()).getTenSanPham(),
                        String.valueOf(cthd.getSoLuong())
                    });
                }
            }
             tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "Số chiếc sản phẩm", String.valueOf(tongSoLuong)+ " chiếc "});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)+ " chiếc "});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungKhachHang_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Sản phẩm đã đặt")) {
            SanPhamCuaTungKhachHang_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeNhaCungCap extends JPanel {

    SanPhamBUS qlmaBUS = new SanPhamBUS();
    NhanVienBUS qlnvBUS = new NhanVienBUS();
    KhachHangBUS qlkhBUS = new KhachHangBUS();
    HoaDonBUS qlhdBUS = new HoaDonBUS();
    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
    HoaDonNhapBUS qlhdnBUS = new HoaDonNhapBUS();
    ChiTietHoaDonBUS qlcthdBUS = new ChiTietHoaDonBUS();
    ChiTietHoaDonNhapBUS qlcthdnBUS = new ChiTietHoaDonNhapBUS();
    SanPhamBUS qlnlBUS= new SanPhamBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    GUIMyTable tb;
    JButton btnRefresh = new JButton("Làm mới");

    public ThongKeNhaCungCap() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Số lượng sản phẩm", "Tổng thành tiền"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        btnRefresh.addActionListener((ae) -> {
            try {
                qlmaBUS.docDSSanPham();
                qlhdnBUS.docHDN();
                qlcthdnBUS.docCTHDN();
                qlnccBUS.docDSNCC();
            } catch (Exception ex) {
                Logger.getLogger(ThongKeNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);
        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new GUIMyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }
        
    private void tongTienThanhToan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Mã hóa đơn nhập", "Ngày lập", "Mã sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        float tongTatCa = 0;
        for(NhaCungCapDTO ncc: qlnccBUS.getNhaCungCapDTO()) {
            float tongTien = 0;
            tb.addRow(new String[]{ncc.getIDNhaCungCap(), ncc.getTenNhaCungCap(), "", "", "", "", "", ""});
            for (HoaDonNhapDTO hdn : qlhdnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (hdn.getIDNhaCungCap().equals(ncc.getIDNhaCungCap())) {
                    tb.addRow(new String[]{"", "", hdn.getIDHoaDonNhap(), String.valueOf(hdn.getNgayNhap()), "", "", "", ""});

                    for (ChiTietHoaDonNhapDTO cthdn : qlcthdnBUS.search("Mã hóa đơn nhập", hdn.getIDHoaDonNhap())) {
                        tongTien += cthdn.getSoLuong() * cthdn.getGiaNhap();
                        tb.addRow(new String[]{"", "", "", "",
                            cthdn.getIDSanPham(),
                            PriceFormatter.format(cthdn.getGiaNhap()),
                            String.valueOf(cthdn.getSoLuong()+ " chiếc "),
                            PriceFormatter.format(cthdn.getSoLuong() * cthdn.getGiaNhap())});
                    }
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "", "", PriceFormatter.format(tongTien)});
            tongTatCa += tongTien;
        }
        tb.addRow(new String[]{"", "", "", "", "", "", "",""});
        
        tb.addRow(new String[]{"", "", "", "", "", "", "Tổng tiền:", PriceFormatter.format(tongTatCa)});
    }

    private void soLuongSanPhamCungCap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Mã hóa đơn nhập", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        for(NhaCungCapDTO ncc: qlnccBUS.getNhaCungCapDTO()) {           
            int tongSoLuong = 0;
            tb.addRow(new String[]{ncc.getIDNhaCungCap(), ncc.getTenNhaCungCap(), "", "", "", "", ""});
            for (HoaDonNhapDTO hdn : qlhdnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (hdn.getIDNhaCungCap().equals(ncc.getIDNhaCungCap())) {
                    tb.addRow(new String[]{"", "", hdn.getIDHoaDonNhap(), String.valueOf(hdn.getNgayNhap()), "", "", ""});

                    for (ChiTietHoaDonNhapDTO cthdn : qlcthdnBUS.search("Mã hóa đơn nhập", hdn.getIDHoaDonNhap())) {
                        tongSoLuong += cthdn.getSoLuong();
                        tb.addRow(new String[]{"", "", "", "",
                            cthdn.getIDSanPham(),
                            qlnlBUS.getSanPhamDTO(cthdn.getIDSanPham()).getTenSanPham(),
                            String.valueOf(cthdn.getSoLuong()+ " chiếc ")
                        });
                    }
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "", String.valueOf(tongSoLuong)+ " chiếc "});
            tongTatCa+=tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "", ""});
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả:", String.valueOf(tongTatCa)+ " chiếc "});
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Số lượng sản phẩm")) {
            soLuongSanPhamCungCap();
        }
        if (cbTieuChi.getSelectedItem().equals("Tổng thành tiền")) {
            tongTienThanhToan();
        }
    }
}
