//Đã sửa
package DAO;
import DTO.ThuongHieuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ThuongHieuDAO {
    ConnectDB connection = new ConnectDB();

    public ThuongHieuDAO() {

    }

    public ArrayList docTH() throws SQLException, Exception { //cần ghi lại khi qua class khác
        connection = new ConnectDB();
        ArrayList<ThuongHieuDTO> TH = new ArrayList<>();
        try {
            String qry = "SELECT * FROM thuonghieu";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    ThuongHieuDTO th = new ThuongHieuDTO();
                    th.setIDThuongHieu(result.getString("IDThuongHieu"));
                    th.setTenThuongHieu(result.getString("TenThuongHieu"));
                    TH.add(th);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng hóa đơn");
        }
        return TH;
    }

    public void them(ThuongHieuDTO TH) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO thuonghieu values (";
            qry = qry + "'" + TH.getIDThuongHieu()+ "'";
            qry = qry + "," + "'" + TH.getTenThuongHieu()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }
    public void sua(ThuongHieuDTO TH) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update thuonghieu Set ";
            qry = qry + "TenThuongHieu=" + "'" + TH.getTenThuongHieu()+ "'";
            qry = qry+" "+" WHERE IDThuongHieu=' "+TH.getIDThuongHieu()+"'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
    public void xoa(String IDThuongHieu) {
        try {
            String qry = "DELETE FROM thuonghieu";
            qry = qry + " " + "WHERE IDThuongHieu = '" + IDThuongHieu+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}

