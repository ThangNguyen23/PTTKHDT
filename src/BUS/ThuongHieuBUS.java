//Đang sửa
package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThuongHieuBUS {
    public static ArrayList<ThuongHieuDTO> dsth;
   public ThuongHieuBUS()
    {
        
    }
    public  void  docTH() throws Exception 
    {
        ThuongHieuDAO thdata=new ThuongHieuDAO();
        if(dsth==null) dsth=new ArrayList<ThuongHieuDTO>();
        dsth =thdata.docTH();
    }
    public void them(ThuongHieuDTO th)
    {
        try
        {
            ThuongHieuDAO thdata=new ThuongHieuDAO();
            thdata.them(th);
            if(dsth!=null)
            dsth.add(th);
        }
        catch (Exception ex) {
           Logger.getLogger(ThuongHieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(ThuongHieuDTO th,int i)
    {
        try
        {

           ThuongHieuDAO thdata=new ThuongHieuDAO();
           thdata.sua(th);
           if(dsth!=null)
           dsth.set(i, th);
        }
        catch (Exception ex) {
           Logger.getLogger(ThuongHieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        ThuongHieuDAO data = new ThuongHieuDAO();
        data.xoa(ID); // update trạng thái lên database
        ThuongHieuDTO DTO=dsth.get(index); // sửa lại thông tin trong list
        if(dsth!=null)
        dsth.remove(DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dsth.size(); i++) {
            if (dsth.get(i).getIDThuongHieu().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public ThuongHieuDTO getThuongHieuDTO(String idth) {
        for (ThuongHieuDTO thDTO : dsth) {
            if (thDTO.getIDThuongHieu().equals(idth)) {
                return thDTO;
            }
        }
        return null;
    }

    public ArrayList<ThuongHieuDTO> getThuongHieuDTO() {
    return dsth;
    }
    
    public static String getMaThuongHieuCuoi() //lấy mã cuối để tăng
    {
        if(dsth == null)
        {
            dsth = new ArrayList<>();
        }
        if(dsth.size() > 0)
        {
            String ma;
         ma = dsth.get(dsth.size()-1).getIDThuongHieu();
         return ma;
        }
         return null;
    }
}
