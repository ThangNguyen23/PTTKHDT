//Đã sửa
package DAO;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HoaDonDAO {

    ConnectDB connection = new ConnectDB();

    public HoaDonDAO() {

    }

    public ArrayList docHD() throws SQLException, Exception { //cần ghi lại khi qua class khác
        connection = new ConnectDB();
        ArrayList<HoaDonDTO> HD = new ArrayList<>();
        try {
            String qry = "SELECT * FROM hoadonban";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setIDHoaDon(result.getString("IDHoaDonBan"));
                    hd.setIDNhanVien(result.getString("IDNhanVien"));
                    hd.setIDKhachHang(result.getString("IDKhachHang"));
                    hd.setThanhTien(result.getFloat("ThanhTien"));
                    hd.setNgayBan(result.getDate("NgayBan").toLocalDate());
                    hd.setTrangThai(result.getString("TrangThai"));
                    HD.add(hd);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng hóa đơn");
        }
        return HD;
    }

    public void them(HoaDonDTO HD) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO hoadonban values (";
            qry = qry + "'" + HD.getIDHoaDon()+ "'";
            qry = qry + "," + "'" + HD.getIDKhachHang()+ "'";
            qry = qry + "," + "'" + HD.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + HD.getThanhTien()+ "'";
            qry = qry + "," + "'" + HD.getNgayBan()+ "'";
            qry = qry + "," + "'" + HD.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }
    public void sua(HoaDonDTO HD) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update hoadonban Set ";
            qry = qry + "IDNhanVien=" + "'" + HD.getIDNhanVien()+ "'";
            qry = qry + ",IDKhachHang=" + "'" + HD.getIDKhachHang()+ "'";
            qry = qry + ",ThanhTien=" + "'" + HD.getThanhTien()+ "'";
            qry = qry + ",NgayBan=" + "'" + HD.getNgayBan()+ "'";
            qry = qry + ",TrangThai=" + "'" + HD.getTrangThai()+ "'";
            qry = qry+" "+" WHERE IDHoaDonBan=' "+HD.getIDHoaDon()+"'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
    public void xoa(String IDHoaDon) { 
        try {
            String qry = "DELETE FROM hoadonban";
            qry = qry + " " + "WHERE IDHoaDon = '" + IDHoaDon+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

}