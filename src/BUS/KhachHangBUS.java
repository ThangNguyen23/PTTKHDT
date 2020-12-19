//Đã sửa
package BUS;

import DAO.KhachHangDAO;
import java.util.ArrayList;
import DTO.KhachHangDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangBUS {
   public static ArrayList<KhachHangDTO> dskh;
   public KhachHangBUS()
    {
        
    }
    public  void  docDSKH() throws Exception 
    {
        KhachHangDAO khdata=new KhachHangDAO();
        if(dskh==null) 
            dskh=new ArrayList<KhachHangDTO>();
        dskh =khdata.docDSKH();
        autosetTongChiTieu();
    }
    public void  them(KhachHangDTO kh)
    {
        try
        {
            KhachHangDAO khdata=new KhachHangDAO();
            khdata.them(kh);
            if(dskh!=null)
            dskh.add(kh);
        }
        catch (Exception ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(KhachHangDTO kh,int i)
    {
        try
        {

           KhachHangDAO khdata=new KhachHangDAO();
           khdata.sua(kh);
           if(dskh!=null)
           dskh.set(i, kh);
        }
        catch (Exception ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(ID); // update trạng thái lên database
        KhachHangDTO DTO=dskh.get(index); // sửa lại thông tin trong list
        if(dskh!=null)
            dskh.remove(DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dskh.size(); i++) {
            if (dskh.get(i).getIDKhachHang().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public KhachHangDTO getKhachHangDTO(String idkh) {
        for (KhachHangDTO khDTO : dskh) {
            if (khDTO.getIDKhachHang().equals(idkh)) {
                return khDTO;
            }
        }
        return null;
    }

    public ArrayList<KhachHangDTO> getKhachHangDTO() {
    return dskh;
    }
    
    public static String getMaKhachHangCuoi()
    {
        if(dskh == null)
        {
            dskh = new ArrayList<>();
        }
        if(dskh.size() > 0)
        {
            String ma;
         ma = dskh.get(dskh.size()-1).getIDKhachHang();
         return ma;
        }
         return null;
    }
    public static float getTongChiTieu(String IDKhachHang){
        float tong=0;
        for(int i=0;i<HoaDonBUS.HD.size();i++){
            if(HoaDonBUS.HD.get(i).getIDKhachHang()==IDKhachHang){
                tong+=HoaDonBUS.HD.get(i).getThanhTien();
            }
        }
        return tong;
    }
    private void autosetTongChiTieu(){
        for(int i=0;i<dskh.size();i++){
            dskh.get(i).setTongChiTieu(getTongChiTieu(dskh.get(i).getIDKhachHang()));
        }
    }
}

