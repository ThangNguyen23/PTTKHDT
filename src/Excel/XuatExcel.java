//Đã sửa
package Excel;
import BUS.*;
import DTO.*;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Admin
 */
public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
        // Xuất file Excel Món Ăn   
    public void xuatFileExcelSanPham() {
        fd.setTitle("Xuất dữ liệu sản phẩm ra excel"); //Set tên
        String url = getFile(); //Kiểm tra getfile()
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//Đọc và ghi file định dạng Microsoft Excel (XLS – định dạng hỗ trợ của Excel 2003) - Workbook: file
            HSSFSheet sheet = workbook.createSheet("Sản phẩm");//Tạo bảng tính Món Ăn

            SanPhamBUS monanBUS = new SanPhamBUS(); //Tạo biến monanBUS
            ArrayList<SanPhamDTO> list = monanBUS.getSanPhamDTO();  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột) 
            row.createCell(0, CellType.STRING).setCellValue("Mã sản phẩm"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("Tên sản phẩm"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("Mã loại dây");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("Mã thương hiệu");//Hàng 0. cột 3- kiểu String, giá trị Giá
            row.createCell(4, CellType.STRING).setCellValue("Mã nhà cung cấp");//Hàng 0. cột 4- kiểu String, giá trị Hình Ảnh
            row.createCell(5, CellType.STRING).setCellValue("Bảo hành");//Hàng 0. cột 5- kiểu String, giá trị Loại
            row.createCell(6, CellType.STRING).setCellValue("Số lượng");//Hàng 0. cột 6- kiểu String, giá trị Số lượng
            row.createCell(7, CellType.STRING).setCellValue("Giá");
            row.createCell(8, CellType.STRING).setCellValue("Hình ảnh");
//Tạo vòng lập for chạy hết giá trị của list
            for (SanPhamDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ma.getIDSanPham()); 
                row.createCell(1, CellType.STRING).setCellValue(ma.getTenSanPham());
                row.createCell(2, CellType.STRING).setCellValue(ma.getIDDay());
                row.createCell(3, CellType.STRING).setCellValue(ma.getIDThuongHieu()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(4, CellType.STRING).setCellValue(ma.getIDNhaCungCap());
                row.createCell(5, CellType.STRING).setCellValue(ma.getBaoHanh());
                row.createCell(6, CellType.NUMERIC).setCellValue(ma.getSoLuong());
                row.createCell(7, CellType.NUMERIC).setCellValue(ma.getGia());
                row.createCell(8, CellType.STRING).setCellValue(ma.getHinhAnh());
            }
//Tạo vòng lập từ 0 tới rownum để set lại kích thước cột cho gọn
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }
//Tiến hành tạo file và ghi file
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Nguyên Liệu
    public void xuatFileExcelLoaiDay() {
        fd.setTitle("Xuất dữ liệu loại dây ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loại dây");

            LoaiDayBUS ldBUS = new LoaiDayBUS();
            ArrayList<LoaiDayDTO> list = ldBUS.getLoaiDayDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã loại dây");
            row.createCell(1, CellType.STRING).setCellValue("Tên loại dây");


            for (LoaiDayDTO nl : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(nl.getIDLoaiDay());
                row.createCell(1, CellType.STRING).setCellValue(nl.getTenLoaiDay());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Công Thức
        public void xuatFileExcelThuongHieu() {
        fd.setTitle("Xuất dữ liệu thương hiệu ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Thương hiệu");

            ThuongHieuBUS thBUS = new ThuongHieuBUS();
            ArrayList<ThuongHieuDTO> list = thBUS.getThuongHieuDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã thương hiệu");
            row.createCell(1, CellType.STRING).setCellValue("Tên thương hiệu");

            for (ThuongHieuDTO ct : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ct.getIDThuongHieu());
                row.createCell(1, CellType.STRING).setCellValue(ct.getIDThuongHieu());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Hóa Đơn
    public void xuatFileExcelHoaDon() {
        fd.setTitle("Xuất dữ liệu Hóa đơn ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa Đơn");

            HoaDonBUS hdBUS = new HoaDonBUS();
            ArrayList<HoaDonDTO> list = hdBUS.getHoaDonDTO();
            ChiTietHoaDonBUS cthdBUS=new ChiTietHoaDonBUS();
            NhanVienBUS nvBUS=new NhanVienBUS();
            KhachHangBUS khBUS=new KhachHangBUS();
            SanPhamBUS maBUS=new SanPhamBUS();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(3, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(4, CellType.STRING).setCellValue("Thành tiền");
            row.createCell(5, CellType.STRING).setCellValue("Ngày bán");
            
//            row.createCell(7, CellType.STRING).setCellValue("Món Ăn");
//            row.createCell(8, CellType.STRING).setCellValue("Số lượng");
//            row.createCell(9, CellType.STRING).setCellValue("Đơn giá");
//            row.createCell(10, CellType.STRING).setCellValue("Thành tiền");
            

            for (HoaDonDTO hd : list) {
                rownum++;
                row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue(hd.getIDHoaDon());
            row.createCell(1, CellType.STRING).setCellValue(hd.getIDNhanVien());//+" - "+nvBUS.getNhanVienDTO(hd.getIDNhanVien()).getTenNhanVien());
            row.createCell(2, CellType.STRING).setCellValue(hd.getIDKhachHang());//+" - "+khBUS.getKhachHangDTO(hd.getIDKhachHang()).getTenKhachHang());
            row.createCell(3, CellType.STRING).setCellValue(hd.getTrangThai());//+" - "+kmBUS.getKhuyenMaiDTO(hd.getIDKhuyenMai()).getTenChuongTrinh());
            row.createCell(4, CellType.NUMERIC).setCellValue(hd.getThanhTien());
            row.createCell(5, CellType.STRING).setCellValue(String.valueOf(hd.getNgayBan()));
            
//            for (ChiTietHoaDonDTO cthd : cthdBUS.getAllChiTiet(hd.getIDHoaDon())) {
//                    rownum++;
//                    row = sheet.createRow(rownum);
//
//                    String ma = cthd.getIDSanPham();
//
//                    row.createCell(7, CellType.STRING).setCellValue(ma + " - " + maBUS.getSanPhamDTO(ma).getTenSanPham());
//                    row.createCell(8, CellType.NUMERIC).setCellValue(cthd.getSoLuong());
//                    row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getDonGia());
//                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getDonGia() * cthd.getSoLuong());
//                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    //Xuất file Excel Hóa Đơn Nhập
    public void xuatFileExcelHoaDonNhap() throws Exception {
        fd.setTitle("Xuất dữ liệu Hóa đơn nhập ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa Đơn Nhập");

            HoaDonNhapBUS hdnBUS = new HoaDonNhapBUS();
            ArrayList<HoaDonNhapDTO> list = hdnBUS.getHoaDonNhapDTO();
            ChiTietHoaDonNhapBUS cthdnBUS = new ChiTietHoaDonNhapBUS();
            NhanVienBUS nvBUS = new NhanVienBUS();
            NhaCungCapBUS nccBUS = new NhaCungCapBUS();
            LoaiDayBUS nlBUS = new LoaiDayBUS();            
            

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Mã nhà cung cấp");
            row.createCell(3, CellType.STRING).setCellValue("Thành tiền");
            row.createCell(4, CellType.STRING).setCellValue("Ngày nhập");
            
//            row.createCell(5, CellType.STRING).setCellValue("Nguyên liệu");
//            row.createCell(6, CellType.STRING).setCellValue("Số lượng");
//            row.createCell(7, CellType.STRING).setCellValue("Giá nhập");
//            row.createCell(8, CellType.STRING).setCellValue("Thành tiền");

            for (HoaDonNhapDTO hdn : list) {
                rownum++;
                row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue(hdn.getIDHoaDonNhap());
            row.createCell(1, CellType.STRING).setCellValue(hdn.getIDNhanVien());//+" - "+nvBUS.getNhanVienDTO(hdn.getIDNhanVien()).getTenNhanVien());
            row.createCell(2, CellType.STRING).setCellValue(hdn.getIDNhaCungCap());//+" - "+nccBUS.getNhaCungCapDTO(hdn.getIDNhaCungCap()).getTenNhaCungCap());
            row.createCell(3, CellType.STRING).setCellValue(hdn.getThanhTien());            
            row.createCell(4, CellType.STRING).setCellValue(String.valueOf(hdn.getNgayNhap()));
            
            
//            for (ChiTietHoaDonNhapDTO cthdn : cthdnBUS.getAllChiTiet(hdn.getIDHoaDonNhap())) {
//                    rownum++;
//                    row = sheet.createRow(rownum);
//
//                    String nl = cthdn.getIDLoaiDay();
//
//                    row.createCell(5, CellType.STRING).setCellValue(nl + " - " + nlBUS.getLoaiDayDTO(nl).getTenLoaiDay());
//                    row.createCell(6, CellType.NUMERIC).setCellValue(cthdn.getSoLuong());
//                    row.createCell(7, CellType.NUMERIC).setCellValue(cthdn.getGiaNhap());
//                    row.createCell(8, CellType.NUMERIC).setCellValue(cthdn.getGiaNhap() * cthdn.getSoLuong());
//                }
            
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }     
    
    //Xuất file Excel Khuyến Mãi
    public void xuatFileExcelKhuyenMai() {
//        fd.setTitle("Xuất dữ liệu Khuyến mãi ra excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileOutputStream outFile = null;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Khuyến Mãi");
//
//            KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
//            ArrayList<KhuyenMaiDTO> list = kmBUS.getKhuyenMaiDTO();
//
//            int rownum = 0;
//            Row row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.STRING).setCellValue("Mã khuyến mãi");
//            row.createCell(1, CellType.STRING).setCellValue("Tên chương trình");
//            row.createCell(2, CellType.STRING).setCellValue("Tiền giảm");
//            row.createCell(3, CellType.STRING).setCellValue("Ngày bắt đầu");
//            row.createCell(4, CellType.STRING).setCellValue("Ngày kết thúc");
//            row.createCell(5, CellType.STRING).setCellValue("Nội dung giảm giá");
//            
//
//            for (KhuyenMaiDTO km : list) {
//                rownum++;
//                row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.STRING).setCellValue(km.getIDKhuyenMai());
//            row.createCell(1, CellType.STRING).setCellValue(km.getTenChuongTrinh());
//            row.createCell(2, CellType.NUMERIC).setCellValue(km.getTienGiam());
//            row.createCell(3, CellType.STRING).setCellValue(String.valueOf(km.getNgayBatDau()));
//            row.createCell(4, CellType.STRING).setCellValue(String.valueOf(km.getNgayKetThuc()));
//            row.createCell(5, CellType.STRING).setCellValue(km.getNoiDungGiamGia());
//            }
//            
//            for (int i = 0; i < rownum; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            File file = new File(url);
//            file.getParentFile().mkdirs();
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//
//            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (outFile != null) {
//                    outFile.close();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }// Không có khuyến mãi
    //Xuất file Excel Khách Hàng
    public void xuatFileExcelKhachHang() {
        fd.setTitle("Xuất dữ liệu Khách hàng ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khách hàng");

            KhachHangBUS khBUS = new KhachHangBUS();
            ArrayList<KhachHangDTO> list = khBUS.getKhachHangDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("ID");
            row.createCell(1, CellType.STRING).setCellValue("Họ");
            row.createCell(2, CellType.STRING).setCellValue("Tên");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("SĐT");
            row.createCell(5, CellType.STRING).setCellValue("Tổng chi tiêu");

            for (KhachHangDTO kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(kh.getIDKhachHang());
                row.createCell(1, CellType.STRING).setCellValue(kh.getHoKhachHang());
                row.createCell(2, CellType.STRING).setCellValue(kh.getTenKhachHang());
                row.createCell(3, CellType.STRING).setCellValue(kh.getDiaChiNhanHang());
                row.createCell(4, CellType.STRING).setCellValue(kh.getSoDienThoai());
                row.createCell(5, CellType.NUMERIC).setCellValue(kh.getTongChiTieu());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Nhân viên
    public void xuatFileExcelNhanVien() {
        fd.setTitle("Xuất dữ liệu Nhân viên ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhân Viên");

            NhanVienBUS nvBUS = new NhanVienBUS();
            ArrayList<NhanVienDTO> list = nvBUS.getNhanVienDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("ID");
            row.createCell(1, CellType.STRING).setCellValue("Họ");
            row.createCell(2, CellType.STRING).setCellValue("Tên");
            row.createCell(3, CellType.STRING).setCellValue("Gmail");
            row.createCell(4, CellType.STRING).setCellValue("Giới tính");
            row.createCell(5, CellType.STRING).setCellValue("SĐT");
            row.createCell(6, CellType.STRING).setCellValue("Chức vụ");

            for (NhanVienDTO nv : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(nv.getIDNhanVien());
                row.createCell(1, CellType.STRING).setCellValue(nv.getHoNhanVien());
                row.createCell(2, CellType.STRING).setCellValue(nv.getTenNhanVien());
                row.createCell(3, CellType.STRING).setCellValue(nv.getGmail());
                row.createCell(4, CellType.STRING).setCellValue(nv.getGioiTinh());
                row.createCell(5, CellType.STRING).setCellValue(nv.getSoDienThoai());
                row.createCell(6, CellType.STRING).setCellValue(nv.getChucVu());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //Xuất file Excel Nhà Cung Cấp
    public void xuatFileExcelNhaCungCap() {
        fd.setTitle("Xuất dữ liệu Nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà Cung Cấp");

            NhaCungCapBUS nccBUS = new NhaCungCapBUS();
            ArrayList<NhaCungCapDTO> list = nccBUS.getNhaCungCapDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("ID");
            row.createCell(1, CellType.STRING).setCellValue("Tên");
            row.createCell(2, CellType.STRING).setCellValue("SĐT");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");

            for (NhaCungCapDTO ncc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(ncc.getIDNhaCungCap());
                row.createCell(1, CellType.STRING).setCellValue(ncc.getTenNhaCungCap());
                row.createCell(2, CellType.STRING).setCellValue(ncc.getSoDienThoai());
                row.createCell(3, CellType.STRING).setCellValue(ncc.getDiaChi());

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Tài Khoản
    public void xuatFileExcelTaiKhoan() {
        fd.setTitle("Xuất dữ liệu Tài khoản ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài Khoản");

            TaiKhoanBUS tkBUS = new TaiKhoanBUS();
            ArrayList<TaiKhoanDTO> list = tkBUS.getTaiKhoanDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Tài khoản");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Mã Quyền");
            row.createCell(3, CellType.STRING).setCellValue("Mật Khẩu");

            for (TaiKhoanDTO tk : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(tk.getTaiKhoan());
                row.createCell(1, CellType.STRING).setCellValue(tk.getIDNhanVien());
                row.createCell(2, CellType.STRING).setCellValue(tk.getIDPhanQuyen());
                row.createCell(3, CellType.STRING).setCellValue(tk.getMatKhau());


            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Phân Quyền
    public void xuatFileExcelPhanQuyen() {
        fd.setTitle("Xuất dữ liệu Phân quyền ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Phân Quyền");

            PhanQuyenBUS pqBUS = new PhanQuyenBUS();
            ArrayList<PhanQuyenDTO> list = pqBUS.getPhanQuyenDTO();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.STRING).setCellValue("Mã quyền");
            row.createCell(1, CellType.STRING).setCellValue("Tên quyền");
            row.createCell(2, CellType.STRING).setCellValue("Mô tả");
            

            for (PhanQuyenDTO pq : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.STRING).setCellValue(pq.getIDPhanQuyen());
                row.createCell(1, CellType.STRING).setCellValue(pq.getTenQuyen());
                row.createCell(2, CellType.STRING).setCellValue(pq.getMoTaQuyen());
                


            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Xuất file Excel Thống kê
//    public void xuatFileExcelThongKe() {
//        fd.setTitle("Xuất dữ liệu Thống kê ra excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileOutputStream outFile = null;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Thống Kê");
//
//            ThongKeBUS tkBUS = new ThongKeBUS();
//            ArrayList<PhanQuyenDTO> list = tkBUS.getPhanQuyenDTO();
//
//            int rownum = 0;
//            Row row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.STRING).setCellValue("Mã quyền");
//            row.createCell(1, CellType.STRING).setCellValue("Tên quyền");
//            row.createCell(2, CellType.STRING).setCellValue("Mô tả quyền");
//
//            for (PhanQuyenDTO pq : list) {
//                rownum++;
//                row = sheet.createRow(rownum);
//
//                row.createCell(0, CellType.STRING).setCellValue(pq.getIDPhanQuyen());
//                row.createCell(1, CellType.STRING).setCellValue(pq.getTenQuyen());
//                row.createCell(2, CellType.STRING).setCellValue(pq.getMoTaQuyen());
//
//
//            }
//            for (int i = 0; i < rownum; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            File file = new File(url);
//            file.getParentFile().mkdirs();
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//
//            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (outFile != null) {
//                    outFile.close();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}
    


