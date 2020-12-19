//Đã sửa
package DTO;
public class PhanQuyenDTO {
    private String IDPhanQuyen,TenQuyen,MoTaQuyen;

    public PhanQuyenDTO() {
    }

    public PhanQuyenDTO(String IDPhanQuyen, String TenQuyen, String MoTaQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
        this.TenQuyen = TenQuyen;
        this.MoTaQuyen = MoTaQuyen;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        this.TenQuyen = TenQuyen;
    }

    public String getMoTaQuyen() {
        return MoTaQuyen;
    }

    public void setMoTaQuyen(String MoTaQuyen) {
        this.MoTaQuyen = MoTaQuyen;
    }
    
}
