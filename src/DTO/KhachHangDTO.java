//Đã sửa
package DTO;
public class KhachHangDTO {
    private String IDKhachHang,HoKhachHang,TenKhachHang,DiaChiNhanHang,SoDienThoai;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String IDKhachHang, String HoKhachHang, String TenKhachHang, String DiaChiNhanHang, String SoDienThoai) {
        this.IDKhachHang = IDKhachHang;
        this.HoKhachHang = HoKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.DiaChiNhanHang = DiaChiNhanHang;
        this.SoDienThoai = SoDienThoai;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getHoKhachHang() {
        return HoKhachHang;
    }

    public void setHoKhachHang(String HoKhachHang) {
        this.HoKhachHang = HoKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getDiaChiNhanHang() {
        return DiaChiNhanHang;
    }

    public void setDiaChiNhanHang(String DiaChiNhanHang) {
        this.DiaChiNhanHang = DiaChiNhanHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    
}
