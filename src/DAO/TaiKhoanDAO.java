//Đã sửa
package DAO;

//import Connect.ConnectDB;
import DTO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoanDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSTK() throws Exception {
        ArrayList<TaiKhoanDTO> dstk = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM taikhoankh";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    TaiKhoanDTO  tk = new TaiKhoanDTO();
                    tk.setTaiKhoan(rs.getString("TaiKhoan"));
                    tk.setIDNhanVien(rs.getString("IDNhanVien"));
                    tk.setIDPhanQuyen(rs.getString("IDPhanQuyen"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    dstk.add(tk);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng tài khoản nhân viên");
        } 
        return dstk;   
    }
    public void them(TaiKhoanDTO tk ) {
        try{
            String qry ="INSERT INTO taikhoankh values (";
            qry = qry + "'" + tk.getTaiKhoan()+ "'";
            qry = qry + "," + "'" + tk.getMatKhau()+ "'";
            qry = qry + "," + "'" + tk.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + tk.getIDPhanQuyen()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);  
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(TaiKhoanDTO tk){
        try{
             String qry="Update taikhoankh Set ";
                    qry = qry + "MatKhau= '"+tk.getMatKhau()+"'";
                    qry = qry + ",IDNhanVien= '"+tk.getIDNhanVien()+"'";
                    qry = qry + ",IDPhanQuyen= '"+tk.getIDPhanQuyen()+"'";
                    qry = qry + " "+" WHERE TaiKhoan='"+tk.getTaiKhoan()+"'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  TaiKhoan){
        try{
            String qry = "DELETE FROM taikhoankh";
            qry = qry + " " + "WHERE TaiKhoan = '" + TaiKhoan+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        }catch(Exception ex){
            
        }
    }
    
}



















