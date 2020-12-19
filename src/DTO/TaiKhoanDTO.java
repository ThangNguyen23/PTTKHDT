//Đã sửa
package DTO;
public class TaiKhoanDTO {
    private String TaiKhoan,IDNhanVien,IDPhanQuyen,MatKhau;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String TaiKhoan, String IDNhanVien, String IDPhanQuyen, String MatKhau) {
        this.TaiKhoan = TaiKhoan;
        this.IDNhanVien = IDNhanVien;
        this.IDPhanQuyen = IDPhanQuyen;
        this.MatKhau = MatKhau;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    
    
}