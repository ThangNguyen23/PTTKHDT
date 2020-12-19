//Đã sửa
package BUS;

import DAO.ChiTietHoaDonNhapDAO;
import DTO.ChiTietHoaDonNhapDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ChiTietHoaDonNhapBUS {
   public static ArrayList<ChiTietHoaDonNhapDTO> dscthdn;
   public ChiTietHoaDonNhapBUS()
    {
        
    }
    public  void  docCTHDN() throws Exception 
    {
        ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
        if (dscthdn == null) {
            dscthdn = new ArrayList<>();
        }
        dscthdn = cthdn.docCTHDN(); // đọc dữ liệu từ database
        autosetDonGiavaThanhTien();
    }
    public void  them(ChiTietHoaDonNhapDTO CTHDNDTO)
    {
        ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
        cthdn.them(CTHDNDTO);//ghi vào database
        if(dscthdn !=null){
            dscthdn.add(CTHDNDTO);//cập nhật arraylist
        }
    }

     public void trusoluong(ChiTietHoaDonNhapDTO ctHDN){
         SanPhamBUS bus=new SanPhamBUS();
         for(SanPhamDTO DTO:SanPhamBUS.dsSanPham)
         {
             if(ctHDN.getIDSanPham().equals(DTO.getIDSanPham()))
             {
                 int i=SanPhamBUS.timViTri(DTO.getIDSanPham());
                 DTO.setSoLuong(DTO.getSoLuong()+ctHDN.getSoLuong());
                 SanPhamBUS.dsSanPham.set(i, DTO);
                 bus.sua(DTO, i);
                 return;
             }
         }
     }
    public  ArrayList<ChiTietHoaDonNhapDTO> getChiTietHoaDonNhapDTO() {
        return dscthdn;
    }
    
    public ChiTietHoaDonNhapDTO getChiTiet(String mahd, String manl) {
        for (ChiTietHoaDonNhapDTO cthdnDTO : dscthdn) {
            if (cthdnDTO.getIDHoaDonNhap().equals(mahd) && cthdnDTO.getIDSanPham().equals(manl)) {
                return cthdnDTO;
            }
        }
        return null;
    }
    public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTiet(String mahdn) throws Exception  {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        if(dscthdn==null)
        {
            docCTHDN();
        }
        for (ChiTietHoaDonNhapDTO ctHDN : dscthdn) {
            if (ctHDN.getIDHoaDonNhap().equals(mahdn)) {
                result.add(ctHDN);
            }
        }
        return result;
    }
    private void autosetDonGiavaThanhTien(){
        for(int i=0;i<dscthdn.size();i++){
            dscthdn.get(i).setGiaNhap(SanPhamBUS.dsSanPham.get(SanPhamBUS.timViTri(dscthdn.get(i).getIDSanPham())).getGia());
        }
       for(int i=0;i<dscthdn.size();i++){
            dscthdn.get(i).setThanhTien(dscthdn.get(i).getGiaNhap()*dscthdn.get(i).getSoLuong());
        }
    }
    public ArrayList<ChiTietHoaDonNhapDTO> search(String type, String value) {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        dscthdn.forEach((cthdn) -> {
            if (type.equals("Tất cả")) {
                if (cthdn.getIDHoaDonNhap().toLowerCase().contains(value.toLowerCase())
                        || cthdn.getIDSanPham().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(cthdn.getGiaNhap()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(cthdn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(cthdn);
                }
            } else {
                switch (type) {
                    case "Mã hóa đơn nhập":
                        if (cthdn.getIDHoaDonNhap().toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Mã nguyên liệu":
                        if (cthdn.getIDSanPham().toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(cthdn.getGiaNhap()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(cthdn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                }
            }

        });        
        return result;
    }
}











