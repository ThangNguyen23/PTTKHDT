//Đã sửa
package Excel;

import BUS.HoaDonBUS;
import BUS.HoaDonNhapBUS;
import BUS.KhachHangBUS;
import BUS.LoaiDayBUS;
import BUS.SanPhamBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import BUS.ThuongHieuBUS;
import DTO.HoaDonDTO;
import DTO.HoaDonNhapDTO;
import DTO.KhachHangDTO;
import DTO.LoaiDayDTO;
import DTO.SanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import DTO.ThuongHieuDTO;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DocExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);

    public DocExcel() {

    }
    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
    //Đọc file excel sản phẩm
    public void docFileExcelSanPham() {
        fd.setTitle("Nhập dữ liệu sản phẩm từ excel"); //set Tiêu đề
        String url = getFile(); //tạo file
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream); //Tạo workbook excel mới
            HSSFSheet sheet = workbook.getSheetAt(0); // Tạo sheet excel mới
            Iterator<Row> rowIterator = sheet.iterator(); //Set row trong sheet
            Row row1 = rowIterator.next(); //Tạo biến row
//Tạo biến khi trùng, đếm số lần thêm, ghi đè, bỏ qua
            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
//Tạo cell (ô) - giá trị, 
                    String idsp = cellIterator.next().getStringCellValue(); 
                    String tensp = cellIterator.next().getStringCellValue();
                    String idday = cellIterator.next().getStringCellValue();
                    String idth = cellIterator.next().getStringCellValue();
                    String idncc = cellIterator.next().getStringCellValue();
                    String bh = cellIterator.next().getStringCellValue();
                    int sl = (int) cellIterator.next().getNumericCellValue();
                    float gia = (float) cellIterator.next().getNumericCellValue();
                    String hinhanh = cellIterator.next().getStringCellValue();

                    SanPhamBUS sanphamBUS = new SanPhamBUS();
                    SanPhamDTO sanphamOld = sanphamBUS.getSanPhamDTO(idsp);

                    if (sanphamOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã sản phẩm", "Tên sản phẩm", "Mã loại dây", "Mã thương hiệu", "Mã nhà cung cấp", "Bảo hành", "Số lượng", "Giá", "Hình ảnh" });
                            mtb.addRow(new String[]{
                                "Cũ:", sanphamOld.getIDSanPham(),
                                sanphamOld.getTenSanPham(),
                                sanphamOld.getIDDay(),
                                sanphamOld.getIDThuongHieu(),
                                sanphamOld.getIDNhaCungCap(),
                                sanphamOld.getBaoHanh(),
                                String.valueOf(sanphamOld.getSoLuong()),
                                String.valueOf(sanphamOld.getGia()),
                                sanphamOld.getHinhAnh()
                            });

                            mtb.addRow(new String[]{
                                "Mới:", idsp, tensp, idday, idth, idncc, bh, String.valueOf(sl), String.valueOf(gia), hinhanh                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            SanPhamDTO DTO=new SanPhamDTO(idsp, tensp, idday, idth, idncc, bh, sl, gia, hinhanh);
                            sanphamBUS.sua(DTO,SanPhamBUS.timViTri(idsp));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        SanPhamDTO sanpham = new SanPhamDTO(idsp, tensp, idday, idth, idncc, bh, sl, gia, hinhanh);
                        sanphamBUS.them(sanpham);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel loại dây
    public void docFileExcelLoaiDay() {
        fd.setTitle("Nhập dữ liệu loại dây từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String idld = cellIterator.next().getStringCellValue();
                    String tenld = cellIterator.next().getStringCellValue();

                    LoaiDayBUS loaidayBUS = new LoaiDayBUS();
                    LoaiDayDTO loaidayOld = loaidayBUS.getLoaiDayDTO(idld);

                    if (loaidayOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"","Mã loại dây", "Tên loại dây"});
                            mtb.addRow(new String[]{
                                "Cũ:", loaidayOld.getIDLoaiDay(),
                                loaidayOld.getTenLoaiDay()
                              });
                            mtb.addRow(new String[]{
                                "Mới:", idld, tenld                       
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            LoaiDayDTO DTO=new LoaiDayDTO(idld, tenld);
                            loaidayBUS.sua(DTO,LoaiDayBUS.timViTri(idld));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        LoaiDayDTO loaiday = new LoaiDayDTO(idld, tenld);
                        loaidayBUS.them(loaiday);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Công thức
    public void docFileExcelThuongHieu() {
        fd.setTitle("Nhập dữ liệu thương hiệu từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String idth = cellIterator.next().getStringCellValue();
                    String tenth = cellIterator.next().getStringCellValue();
                    
                    
                    ThuongHieuBUS thuonghieuBUS = new ThuongHieuBUS();
                    ThuongHieuDTO thuonghieuOld = thuonghieuBUS.getThuongHieuDTO(idth);

                    if (thuonghieuOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã thương hiệu", "Tên thương hiệu"});
                            mtb.addRow(new String[]{
                                "Cũ:", thuonghieuOld.getIDThuongHieu(),
                                thuonghieuOld.getTenThuongHieu()
                                
                            });
                            mtb.addRow(new String[]{
                                "Mới:", idth, tenth                     
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            ThuongHieuDTO DTO=new ThuongHieuDTO(idth, tenth);
                            thuonghieuBUS.sua(DTO,ThuongHieuBUS.timViTri(idth));

                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        ThuongHieuDTO ct = new ThuongHieuDTO(idth, tenth);
                        thuonghieuBUS.them(ct);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Hóa đơn
    
    public void docFileExcelHoaDon() {
        fd.setTitle("Nhập dữ liệu hóa đơn từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String idhd = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String idkh = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String tt = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    float tien = (float) cellIterator.next().getNumericCellValue();
                    LocalDate ngayBan = LocalDate.parse(cellIterator.next().getStringCellValue());

                    HoaDonBUS hoadonBUS = new HoaDonBUS();
                    HoaDonDTO hdOld = hoadonBUS.getHoaDonDTO(idhd);
                   
                    if (hdOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Trạng thái", "Thành tiền", "Ngày bán"});
                            mtb.addRow(new String[]{
                                "Cũ:", hdOld.getIDHoaDon(),
                                hdOld.getIDNhanVien(),
                                hdOld.getIDKhachHang(),
                                hdOld.getTrangThai(),
                                String.valueOf(hdOld.getThanhTien()),
                                String.valueOf(hdOld.getNgayBan())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", idhd, idnv, idkh, tt, String.valueOf(tien), String.valueOf(ngayBan)           
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            HoaDonDTO DTO = new HoaDonDTO(idhd, idnv, idkh, tt, tien, ngayBan);
                            hoadonBUS.sua(DTO,HoaDonBUS.timViTri(idhd));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        HoaDonDTO hd = new HoaDonDTO(idhd, idnv, idkh, tt, tien, ngayBan);
                        hoadonBUS.them(hd);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel Hóa đơn nhập
    public void docFileExcelHoaDonNhap() {
        fd.setTitle("Nhập dữ liệu hóa đơn nhập từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String idhdn = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String idncc = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    float tien = (float) cellIterator.next().getNumericCellValue();
                    LocalDate ngaynhap = LocalDate.parse(cellIterator.next().getStringCellValue());                  

                    HoaDonNhapBUS hdnBUS = new HoaDonNhapBUS();
                    HoaDonNhapDTO hdnOld = hdnBUS.getHoaDonNhapDTO(idhdn);
                   
                    if (hdnOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã hóa đơn", "Mã nhân viên", "Mã nhà cung cấp", "Thành tiền", "Ngày nhập" });
                            mtb.addRow(new String[]{
                                "Cũ:", hdnOld.getIDHoaDonNhap(),
                                hdnOld.getIDNhanVien(),
                                hdnOld.getIDNhaCungCap(),
                                String.valueOf(hdnOld.getThanhTien()),
                                String.valueOf(hdnOld.getNgayNhap()),
                            });
                            mtb.addRow(new String[]{
                                "Mới:", idhdn, idnv, idncc, String.valueOf(tien), String.valueOf(ngaynhap)            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            HoaDonNhapDTO DTO = new HoaDonNhapDTO(idhdn, idnv, idncc, tien, ngaynhap);
                            hdnBUS.sua(DTO,HoaDonNhapBUS.timViTri(idhdn));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        HoaDonNhapDTO hdn = new HoaDonNhapDTO(idhdn, idnv, idncc, tien, ngaynhap);
                        hdnBUS.them(hdn);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Khuyến mãi
    public void docFileExcelKhuyenMai() {
//        fd.setTitle("Nhập dữ liệu khuyến mãi từ excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(new File(url));
//
//            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//            HSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            Row row1 = rowIterator.next();
//
//            String hanhDongKhiTrung = "";
//            int countThem = 0;
//            int countGhiDe = 0;
//            int countBoQua = 0;
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    String id = cellIterator.next().getStringCellValue();
//                    String ten = cellIterator.next().getStringCellValue();
//                    int tiengiam = (int) cellIterator.next().getNumericCellValue();
//                    LocalDate ngaybd = LocalDate.parse(cellIterator.next().getStringCellValue());
//                    LocalDate ngaykt = LocalDate.parse(cellIterator.next().getStringCellValue());
//                    String noidung = cellIterator.next().getStringCellValue();
//
//                    KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();
//                    KhuyenMaiDTO kmOld = khuyenmaiBUS.getKhuyenMaiDTO(id);
//                   
//                    if (kmOld != null) {
//                        if (!hanhDongKhiTrung.contains("tất cả")) {
//                            MyTable mtb = new MyTable();
//                            mtb.setHeaders(new String[]{"Mã khuyến mãi", "Tên chương trình", "Tiền giảm", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung" });
//                            mtb.addRow(new String[]{
//                                "Cũ:", kmOld.getIDKhuyenMai(),
//                                kmOld.getTenChuongTrinh(),
//                                String.valueOf(kmOld.getTienGiam()),
//                                String.valueOf(kmOld.getNgayBatDau()),
//                                String.valueOf(kmOld.getNgayKetThuc()),
//                                kmOld.getNoiDungGiamGia(),
//                            });
//                            mtb.addRow(new String[]{
//                                "Mới:", id, ten, String.valueOf(tiengiam), String.valueOf(ngaybd), String.valueOf(ngaykt), noidung               
//                            });
//
//                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
//                            hanhDongKhiTrung = mop.getAnswer();
//                        }
//                        if (hanhDongKhiTrung.contains("Ghi đè")) {
//                            KhuyenMaiDTO DTO = new KhuyenMaiDTO(id, ten, tiengiam, ngaybd, ngaykt, noidung, "Hiện");
//                            khuyenmaiBUS.sua(DTO,KhuyenMaiBUS.timViTri(id));
//                            countGhiDe++;
//                        } else {
//                            countBoQua++;
//                        }                      
//                        
//                    //Khi database trống    
//                     
//                    } else {          
//                        KhuyenMaiDTO km = new KhuyenMaiDTO(id, ten, tiengiam, ngaybd, ngaykt, noidung, "Hiện");
//                        khuyenmaiBUS.them(km);
//                        countThem++;
//                    }
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Đọc thành công, "
//                    + "Thêm " + countThem
//                    + "; Ghi đè " + countGhiDe
//                    + "; Bỏ qua " + countBoQua
//                    + ". Vui lòng làm mới để thấy kết quả");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//           
//            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
//            }
//        }
    }
    
     //Đọc file excel Khách hàng
    public void docFileExcelKhachHang() {
        fd.setTitle("Nhập dữ liệu món ăn từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String idkh = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String diachi = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    float tongchitieu = (float) cellIterator.next().getNumericCellValue();

                    KhachHangBUS khBUS = new KhachHangBUS();
                    KhachHangDTO khOld = khBUS.getKhachHangDTO(idkh);

                    
                    
                    if (khOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã khách hàng", "Họ", "Tên", "Địa chỉ", "SĐT", "Tổng chi tiêu" });
                            mtb.addRow(new String[]{
                                "Cũ:", khOld.getIDKhachHang(),
                                khOld.getHoKhachHang(),
                                khOld.getTenKhachHang(),
                                khOld.getDiaChiNhanHang(),
                                khOld.getSoDienThoai(),
                                String.valueOf(khOld.getTongChiTieu())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", idkh, ho, ten, diachi, sdt, String.valueOf(tongchitieu)                   
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            KhachHangDTO DTO=new KhachHangDTO(idkh, ho, ten, diachi, sdt, tongchitieu);
                            khBUS.sua(DTO,KhachHangBUS.timViTri(idkh));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        KhachHangDTO khaha = new KhachHangDTO(idkh, ho, ten, diachi, sdt, tongchitieu);
                        khBUS.them(khaha);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Nhân viên
    public void docFileExcelNhanVien() {
        fd.setTitle("Nhập dữ liệu nhân viên từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String gmail = cellIterator.next().getStringCellValue();
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String chucvu = cellIterator.next().getStringCellValue();

                    NhanVienBUS nvBUS = new NhanVienBUS();
                    NhanVienDTO nvOld = nvBUS.getNhanVienDTO(id);

                    if (nvOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã nhân viên", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Chức vụ" });
                            mtb.addRow(new String[]{
                                "Cũ:", nvOld.getIDNhanVien(),
                                nvOld.getHoNhanVien(),
                                nvOld.getTenNhanVien(),
                                nvOld.getGmail(),
                                nvOld.getGioiTinh(),
                                nvOld.getSoDienThoai(),
                                nvOld.getChucVu(),
                                
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ho, ten, gmail, gioitinh, sdt, chucvu                         
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhanVienDTO DTO=new NhanVienDTO(id, ho, ten, gmail, gioitinh, sdt, chucvu);
                            nvBUS.sua(DTO,LoaiDayBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        NhanVienDTO nhanvien = new NhanVienDTO(id, ho, ten, gmail, gioitinh, sdt, chucvu);
                        nvBUS.them(nhanvien);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Nhà cung cấp
    public void docFileExcelNhaCungCap() {
        fd.setTitle("Nhập dữ liệu nhà cung cấp từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String diachi = cellIterator.next().getStringCellValue();

                    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
                    NhaCungCapDTO nccOld = nccBUS.getNhaCungCapDTO(id);

                    if (nccOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã nhà cung cấp", "Tên", "SĐT", "Địa chỉ" });
                            mtb.addRow(new String[]{
                                "Cũ:", nccOld.getIDNhaCungCap(),
                                nccOld.getTenNhaCungCap(),
                                nccOld.getSoDienThoai(),
                                nccOld.getDiaChi()
                            });
                            
                            mtb.addRow(new String[]{
                                "Mới:", id, ten, sdt, diachi                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhaCungCapDTO DTO=new NhaCungCapDTO(id, ten, sdt, diachi);
                            nccBUS.sua(DTO,NhaCungCapBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        NhaCungCapDTO nhacc = new NhaCungCapDTO(id, ten, sdt, diachi);
                        nccBUS.them(nhacc);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Tài khoản
    public void docFileExcelTaiKhoan() {
        fd.setTitle("Nhập dữ liệu tài khoản từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue();
                    String idquyen = cellIterator.next().getStringCellValue();
                    String matkhau = cellIterator.next().getStringCellValue();


                    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
                    TaiKhoanDTO tkOld = tkBUS.getTaiKhoanDTO(id);

                    if (tkOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Tài khoản", "Mã nhân viên", "Mã quyền", "Mật khẩu"});
                            mtb.addRow(new String[]{
                                "Cũ:", tkOld.getTaiKhoan(),
                                tkOld.getIDNhanVien(),
                                tkOld.getIDPhanQuyen(),
                                tkOld.getMatKhau(),

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idnv, idquyen, matkhau                  
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            TaiKhoanDTO DTO=new TaiKhoanDTO(id, idnv, idquyen, matkhau);
                            tkBUS.sua(DTO,TaiKhoanBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        TaiKhoanDTO taka = new TaiKhoanDTO(id, idnv, idquyen, matkhau);
                        tkBUS.them(taka);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Phân quyền
    public void docFileExcelPhanQuyen() {
        fd.setTitle("Nhập dữ liệu phân quyền từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String mota = cellIterator.next().getStringCellValue();

                    PhanQuyenBUS pqBUS = new PhanQuyenBUS();
                    PhanQuyenDTO pqOld = pqBUS.getPhanQuyenDTO(id);
                    

                    if (pqOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã quyền", "Tên quyền"});
                            mtb.addRow(new String[]{
                                "Cũ:", pqOld.getIDPhanQuyen(),
                                pqOld.getTenQuyen()

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ten                          
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            PhanQuyenDTO DTO=new PhanQuyenDTO(id, ten, mota);
                            pqBUS.sua(DTO,PhanQuyenBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        PhanQuyenDTO phaqu = new PhanQuyenDTO(id, ten, mota);
                        pqBUS.them(phaqu);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
}




