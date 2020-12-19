//Đã sửa
package DAO;
import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhaCungCapDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSNCC() throws Exception {
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM nhacungcap";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    NhaCungCapDTO  ncc = new NhaCungCapDTO();
                    ncc.setIDNhaCungCap(rs.getString("IDNhaCungCap"));
                    ncc.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                    ncc.setSoDienThoai(rs.getString("SoDienThoai"));
                    ncc.setDiaChi(rs.getString("DiaChi"));
                    dsncc.add(ncc);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng nhà cung cấp");
        } 
        return dsncc;   
    }
    public void them(NhaCungCapDTO ncc ) {
        try{
            String qry ="INSERT INTO nhacungcap values(";
            qry = qry+"'"+ncc.getIDNhaCungCap()+"'";
            qry = qry+","+"'"+ncc.getTenNhaCungCap()+"'";
            qry = qry+","+"'"+ncc.getSoDienThoai()+"'";
            qry = qry+","+"'"+ncc.getDiaChi()+"'";
            qry = qry+")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(NhaCungCapDTO ncc ){
        try{
             String qry="Update nhacungcap Set ";
                    qry = qry + "TenNhaCungCap=" + "'" + ncc.getTenNhaCungCap() + "'";
                    qry = qry + ",SoDienThoai=" + "'" + ncc.getSoDienThoai() + "'";
                    qry = qry + ",DiaChi=" + "'" + ncc.getDiaChi() + "'";                    
                    qry = qry + " " + "where IDNhaCungCap='" + ncc.getIDNhaCungCap() + "'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDNhaCungCap){
        try {
            String qry = "DELETE FROM nhacungcap";
            qry = qry + " " + "WHERE IDNhaCungCap = '" + IDNhaCungCap+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }
    
}

























