//Đã sửa
package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
// copy paste qua hết
public class SanPhamBUS {

    public static ArrayList<SanPhamDTO> dsSanPham;

    public SanPhamBUS() {

    }

    public void docDSSanPham() throws Exception //cần ghi lại khi qua class khác
    {
        SanPhamDAO data = new SanPhamDAO();
        if (dsSanPham == null) {
            dsSanPham = new ArrayList<>();
        }
        dsSanPham = data.docDB(); // đọc dữ liệu từ database

    }

    public void them(SanPhamDTO sanPham) //cần ghi lại khi qua class khác
    {
        SanPhamDAO data = new SanPhamDAO();
        data.them(sanPham);//ghi vào database
        if(dsSanPham!=null)
            dsSanPham.add(sanPham);//cập nhật arraylist
    }

    public void sua(SanPhamDTO sanPham, int i) //cần ghi lại khi qua class khác
    {
        SanPhamDAO data = new SanPhamDAO();
        data.sua(sanPham);
        if(dsSanPham!=null)
            dsSanPham.set(i, sanPham);
    }

    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        SanPhamDAO data = new SanPhamDAO();
        data.xoa(ID); // update trạng thái lên database
        SanPhamDTO DTO=dsSanPham.get(index); // sửa lại thông tin trong list
        if(dsSanPham!=null)
            dsSanPham.remove(DTO);
    }
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dsSanPham.size(); i++) {
            if (dsSanPham.get(i).getIDSanPham().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    public ArrayList<SanPhamDTO> getSanPhamDTO() {
        return dsSanPham;
    }
    public SanPhamDTO getSanPhamDTO(String idsanpham) {
        for (SanPhamDTO maDTO : dsSanPham) {
            if (maDTO.getIDSanPham().equals(idsanpham)) {
                return maDTO;
            }
        }
        return null;
    }
 
    public static String getMaSanPhamCuoi()
    {
        if(dsSanPham == null)
        {
            dsSanPham = new ArrayList<>();
        }
        if(dsSanPham.size() > 0)
        {
            String ma;
            ma = dsSanPham.get(dsSanPham.size()-1).getIDSanPham();
            return ma;
        }
         return null;
    }
    
    public static boolean timMaSanPham(String maSanPham)
    {
        if(dsSanPham == null)
        {
            dsSanPham = new ArrayList<>();
        }
        for(SanPhamDTO sanPhamDTO : dsSanPham)
        {
            if(sanPhamDTO.getIDSanPham().equals(maSanPham))
            {
                return true;
            }
        }
         return false;
    }
}












