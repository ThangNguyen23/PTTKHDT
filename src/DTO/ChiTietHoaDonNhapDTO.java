//Đã sửa
package DTO;
public class ChiTietHoaDonNhapDTO {
    private String IDHoaDonNhap,IDSanPham;
    private int SoLuong;
    private float GiaNhap,ThanhTien;
    public ChiTietHoaDonNhapDTO() {
    }

    public String getIDHoaDonNhap() {
        return IDHoaDonNhap;
    }

    public void setIDHoaDonNhap(String IDHoaDonNhap) {
        this.IDHoaDonNhap = IDHoaDonNhap;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(float GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
