//Đã sửa
package DAO;


import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class KhachHangDAO {
    
    ConnectDB connection= new ConnectDB();
    
    public KhachHangDAO()
    {
        
    }
    
    public ArrayList docDSKH() throws SQLException, Exception {       
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khachhang";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    KhachHangDTO  kh = new KhachHangDTO();
                    kh.setIDKhachHang(result.getString("IDKhachHang"));
                    kh.setHoKhachHang(result.getString("HoKhachHang"));
                    kh.setTenKhachHang(result.getString("TenKhachHang"));
                    kh.setDiaChiNhanHang(result.getString("DiaChiNhanHang"));
                    kh.setSoDienThoai(result.getString("SoDienThoai"));
                    dskh.add(kh);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khách hàng");
        } finally {
            connection.closeConnect();
        }
        return dskh;   
    }
    
public void them(KhachHangDTO kh ) throws SQLException {
        try {
            String qry = "INSERT INTO khachhang values (";
            qry = qry + "'" + kh.getIDKhachHang() + "'";
            qry = qry + "," + "'" + kh.getHoKhachHang() + "'";
            qry = qry + "," + "'" + kh.getTenKhachHang() + "'";
            qry = qry + "," + "'" + kh.getDiaChiNhanHang() + "'";
            qry = qry + "," + "'" + kh.getSoDienThoai() + "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }        
    }
    public void sua(KhachHangDTO kh) throws SQLException
    {
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "HoKhachHang=" + "'" + kh.getHoKhachHang() + "'";
            qry = qry + ",TenKhachHang=" + "'" + kh.getTenKhachHang() + "'";
            qry = qry + ",DiaChiNhanHang=" + "'" + kh.getDiaChiNhanHang() + "'";
            qry = qry + ",SoDienThoai=" + "'" + kh.getSoDienThoai() + "'";
            qry = qry + " " + "where IDKhachHang='" + kh.getIDKhachHang() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
    public void xoa(String IDKhachHang) {
        try {
            String qry = "DELETE FROM khachhang";
            qry = qry + " " + "WHERE IDKhachHang = '" + IDKhachHang+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }  
 
}































