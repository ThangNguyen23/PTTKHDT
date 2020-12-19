//Đã sửa
package DTO;
public class ThuongHieuDTO {
    private String IDThuongHieu,TenThuongHieu;

    public ThuongHieuDTO() {
    }

    public ThuongHieuDTO(String IDThuongHieu, String TenThuongHieu) {
        this.IDThuongHieu = IDThuongHieu;
        this.TenThuongHieu = TenThuongHieu;
    }

    public String getIDThuongHieu() {
        return IDThuongHieu;
    }

    public void setIDThuongHieu(String IDThuongHieu) {
        this.IDThuongHieu = IDThuongHieu;
    }

    public String getTenThuongHieu() {
        return TenThuongHieu;
    }

    public void setTenThuongHieu(String TenThuongHieu) {
        this.TenThuongHieu = TenThuongHieu;
    }
    
}
