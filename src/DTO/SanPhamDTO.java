//Đã sửa
package DTO;
public class SanPhamDTO {
    private String  IDSanPham,TenSanPham,IDDay,IDThuongHieu,IDNhaCungCap,BaoHanh,HinhAnh;
    private int SoLuong;
    private float Gia;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String IDSanPham, String TenSanPham, String IDDay, String IDThuongHieu, String IDNhaCungCap, String BaoHanh, int SoLuong, float Gia, String HinhAnh) {
        this.IDSanPham = IDSanPham;
        this.TenSanPham = TenSanPham;
        this.IDDay = IDDay;
        this.IDThuongHieu = IDThuongHieu;
        this.IDNhaCungCap = IDNhaCungCap;
        this.BaoHanh = BaoHanh;
        this.HinhAnh = HinhAnh;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getIDDay() {
        return IDDay;
    }

    public void setIDDay(String IDDay) {
        this.IDDay = IDDay;
    }

    public String getIDThuongHieu() {
        return IDThuongHieu;
    }

    public void setIDThuongHieu(String IDThuongHieu) {
        this.IDThuongHieu = IDThuongHieu;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public String getBaoHanh() {
        return BaoHanh;
    }

    public void setBaoHanh(String BaoHanh) {
        this.BaoHanh = BaoHanh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }
    
}