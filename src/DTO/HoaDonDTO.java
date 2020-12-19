//Đã sửa
package DTO;

import java.time.LocalDate;
public class HoaDonDTO {
    private String IDHoaDon,IDNhanVien,IDKhachHang,TrangThai;
    private float ThanhTien;
    private LocalDate NgayBan;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String IDHoaDon, String IDNhanVien, String IDKhachHang, String TrangThai, float ThanhTien, LocalDate NgayBan) {
        this.IDHoaDon = IDHoaDon;
        this.IDNhanVien = IDNhanVien;
        this.IDKhachHang = IDKhachHang;
        this.TrangThai = TrangThai;
        this.ThanhTien = ThanhTien;
        this.NgayBan = NgayBan;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public LocalDate getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(LocalDate NgayBan) {
        this.NgayBan = NgayBan;
    }
    
}
