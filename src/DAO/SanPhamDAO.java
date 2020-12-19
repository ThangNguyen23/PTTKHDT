//Đã sửa
package DAO;

import DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SanPhamDAO {

    ConnectDB connection = new ConnectDB();

    public SanPhamDAO() {

    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<SanPhamDTO> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM sanpham";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    SanPhamDTO sanpham = new SanPhamDTO();
                    sanpham.setIDSanPham(result.getString("IDSanPham"));
                    sanpham.setTenSanPham(result.getString("TenSanPham"));
                    sanpham.setIDDay(result.getString("IDDay"));
                    sanpham.setIDThuongHieu(result.getString("IDThuongHieu"));
                    sanpham.setIDNhaCungCap(result.getString("IDNhaCungCap"));
                    sanpham.setBaoHanh(result.getString("BaoHanh"));
                    sanpham.setSoLuong(result.getInt("SoLuong"));
                    sanpham.setGia(result.getFloat("Gia"));
                    sanpham.setHinhAnh(result.getString("HinhAnh"));
                    dssp.add(sanpham);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng sản phẩm");
        }
        return dssp;
    }

    public void them(SanPhamDTO sanpham) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO sanpham values (";
            qry = qry + "'" + sanpham.getIDSanPham() + "'";
            qry = qry + "," + "'" + sanpham.getTenSanPham() + "'";
            qry = qry + "," + "'" + sanpham.getIDDay() + "'";
            qry = qry + "," + "'" + sanpham.getIDThuongHieu() + "'";
            qry = qry + "," + "'" + sanpham.getIDNhaCungCap() + "'";
            qry = qry + "," + "'" + sanpham.getBaoHanh() + "'";
            qry = qry + "," + "'" + sanpham.getSoLuong() + "'";
            qry = qry + "," + "'" + sanpham.getGia() + "'";
            qry = qry + "," + "'" + sanpham.getHinhAnh() + "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }
    public void sua(SanPhamDTO sanpham) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update sanpham Set ";
            qry = qry + "TenSanPham=" + "'" + sanpham.getTenSanPham() + "'";
            qry = qry + ",IDDay=" + "'" + sanpham.getIDDay() + "'";
            qry = qry + ",IDThuongHieu=" + "'" + sanpham.getIDThuongHieu() + "'";
            qry = qry + ",IDNhaCungCap=" + "'" + sanpham.getIDNhaCungCap() + "'";
            qry = qry + ",BaoHanh=" + "'" + sanpham.getBaoHanh() + "'";
            qry = qry + ",SoLuong=" + "'" + sanpham.getSoLuong() + "'";
            qry = qry + ",Gia=" + "'" + sanpham.getGia() + "'";
            qry = qry + ",HinhAnh=" + "'" + sanpham.getHinhAnh() + "'";
            qry = qry + " " + "where IDSanPham='" + sanpham.getIDSanPham() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
    

    public void xoa(String IDSanPham) {
        try {
            String qry = "DELETE FROM sanpham";
            qry = qry + " " + "WHERE IDSanPham = '" + IDSanPham+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
            
        }
    }
}














