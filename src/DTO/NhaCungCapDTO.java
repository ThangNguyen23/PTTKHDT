//Đã sửa
package DTO;
public class NhaCungCapDTO {
    private String IDNhaCungCap,TenNhaCungCap,SoDienThoai,DiaChi;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(String IDNhaCungCap, String TenNhaCungCap, String SoDienThoai, String DiaChi) {
        this.IDNhaCungCap = IDNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
}

