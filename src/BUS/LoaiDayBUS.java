//Đang sửa
package BUS;

import DAO.LoaiDayDAO;
import DTO.LoaiDayDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaiDayBUS {
    public static ArrayList<LoaiDayDTO> dsld;
   public LoaiDayBUS()
    {
        
    }
    public  void  docLD() throws Exception 
    {
        LoaiDayDAO thdata=new LoaiDayDAO();
        if(dsld==null) dsld=new ArrayList<LoaiDayDTO>();
        dsld =thdata.docLD();
    }
    public void them(LoaiDayDTO th)
    {
        try
        {
            LoaiDayDAO thdata=new LoaiDayDAO();
            thdata.them(th);
            if(dsld!=null)
            dsld.add(th);
        }
        catch (Exception ex) {
           Logger.getLogger(LoaiDayBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(LoaiDayDTO th,int i)
    {
        try
        {

           LoaiDayDAO thdata=new LoaiDayDAO();
           thdata.sua(th);
           if(dsld!=null)
           dsld.set(i, th);
        }
        catch (Exception ex) {
           Logger.getLogger(LoaiDayBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        LoaiDayDAO data = new LoaiDayDAO();
        data.xoa(ID); // update trạng thái lên database
        LoaiDayDTO DTO=dsld.get(index); // sửa lại thông tin trong list
        if(dsld!=null)
        dsld.remove(DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dsld.size(); i++) {
            if (dsld.get(i).getIDLoaiDay().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public LoaiDayDTO getLoaiDayDTO(String idth) {
        for (LoaiDayDTO thDTO : dsld) {
            if (thDTO.getIDLoaiDay().equals(idth)) {
                return thDTO;
            }
        }
        return null;
    }

    public ArrayList<LoaiDayDTO> getLoaiDayDTO() {
    return dsld;
    }
    
    public static String getMaLoaiDayCuoi() //lấy mã cuối để tăng
    {
        if(dsld == null)
        {
            dsld = new ArrayList<>();
        }
        if(dsld.size() > 0)
        {
            String ma;
         ma = dsld.get(dsld.size()-1).getIDLoaiDay();
         return ma;
        }
         return null;
    }
}
