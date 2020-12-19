//Đã sửa
package DAO;

import DTO.ChiTietHoaDonNhapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class ChiTietHoaDonNhapDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docCTHDN() throws Exception {
        ArrayList<ChiTietHoaDonNhapDTO> CTHDN = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM chitiethoadonnhap";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    ChiTietHoaDonNhapDTO  cthdn = new ChiTietHoaDonNhapDTO();
                    cthdn.setIDHoaDonNhap(rs.getString("IDHoaDonNhap"));
                    cthdn.setIDSanPham(rs.getString("IDSanPham"));
                    cthdn.setSoLuong(rs.getInt("SoLuong"));
                    CTHDN.add(cthdn);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng chi tiết hóa đơn nhập");
        } 
        return CTHDN;   
    }
    public void them(ChiTietHoaDonNhapDTO  cthdn ) {
        try{
            String qry ="INSERT INTO chitiethoadonnhap values (";
            qry = qry + "'" + cthdn.getIDHoaDonNhap()+ "'";
            qry = qry + "," + "'" + cthdn.getIDSanPham()+ "'";
            qry = qry + "," + "'" + cthdn.getSoLuong()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);           
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
}







