//Đã sửa
package DTO;

import java.time.LocalDate;
public class HoaDonNhapDTO {
    private String IDHoaDonNhap,IDNhanVien,IDNhaCungCap;
    private float ThanhTien;
    private LocalDate NgayNhap;

    public HoaDonNhapDTO() {
    }

    public HoaDonNhapDTO(String IDHoaDonNhap, String IDNhanVien, String IDNhaCungCap, float ThanhTien, LocalDate NgayNhap) {
        this.IDHoaDonNhap = IDHoaDonNhap;
        this.IDNhanVien = IDNhanVien;
        this.IDNhaCungCap = IDNhaCungCap;
        this.ThanhTien = ThanhTien;
        this.NgayNhap = NgayNhap;
    }

    public String getIDHoaDonNhap() {
        return IDHoaDonNhap;
    }

    public void setIDHoaDonNhap(String IDHoaDonNhap) {
        this.IDHoaDonNhap = IDHoaDonNhap;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public LocalDate getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(LocalDate NgayNhap) {
        this.NgayNhap = NgayNhap;
    }
    
}