//Đã sửa
package DTO;

public class LoaiDayDTO {
    private String IDLoaiDay,TenLoaiDay;

    public LoaiDayDTO() {
    }

    public LoaiDayDTO(String IDLoaiDay, String TenLoaiDay) {
        this.IDLoaiDay = IDLoaiDay;
        this.TenLoaiDay = TenLoaiDay;
    }

    public String getIDLoaiDay() {
        return IDLoaiDay;
    }

    public void setIDLoaiDay(String IDLoaiDay) {
        this.IDLoaiDay = IDLoaiDay;
    }

    public String getTenLoaiDay() {
        return TenLoaiDay;
    }

    public void setTenLoaiDay(String TenLoaiDay) {
        this.TenLoaiDay = TenLoaiDay;
    }
    
}
