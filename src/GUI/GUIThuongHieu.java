//Đang sửa, còn phần tool và excel
package GUI;

import BUS.ThuongHieuBUS;
import BUS.Tool;
import DTO.ThuongHieuDTO;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

public class GUIThuongHieu extends GUIFormContent {
    public static String array_ThuongHieu[] = {"Mã thương hiệu", "Tên"};
    public GUIMyTable table_ThuongHieu;
    private static JDialog Them_ThuongHieu;
    private static JDialog Sua;
    private final JLabel label_ThuongHieu[] = new JLabel[array_ThuongHieu.length];
    private JTextField txt_ThuongHieu_Them[] = new JTextField[array_ThuongHieu.length];
    //Phần textfield của sửa
    private JTextField txt_ThuongHieu_Sua[] = new JTextField[array_ThuongHieu.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private ThuongHieuBUS BUS = new ThuongHieuBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;

    public GUIThuongHieu() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_ThuongHieu
        table_ThuongHieu = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_ThuongHieu.setHeaders(array_ThuongHieu);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_ThuongHieu.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_ThuongHieu.setBounds(0, 0, GUImenu.width_content, 600);
        panel.add(table_ThuongHieu);

        return panel;
    }

    //Hàm tạo Dialog thêm thương hiệu
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_ThuongHieu = new JDialog(f);
        Them_ThuongHieu.setLayout(null);
        Them_ThuongHieu.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_ThuongHieu.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_ThuongHieu.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm thương hiệu");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_ThuongHieu.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_ThuongHieu.length; i++) {
            label_ThuongHieu[i] = new JLabel(array_ThuongHieu[i]);
            label_ThuongHieu[i].setBounds(100, y, 100, 30);
            Them_ThuongHieu.add(label_ThuongHieu[i]);

            txt_ThuongHieu_Them[i] = new JTextField();
            txt_ThuongHieu_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_ThuongHieu.add(txt_ThuongHieu_Them[i]);
        }
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_ThuongHieu, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    ThuongHieuDTO DTO = new ThuongHieuDTO();
                            DTO.setIDThuongHieu(txt_ThuongHieu_Them[0].getText());
                            DTO.setTenThuongHieu(txt_ThuongHieu_Them[1].getText());
                    if(checkTextThem(DTO))
                    {
                        

                    BUS.them(DTO); //thêm thương hiệu bên BUS đã có thêm vào database
                    table_ThuongHieu.addRow(DTO);
                    //clear textfield trong Them
                    for (int i = 0; i < array_ThuongHieu.length; i++) {
                        txt_ThuongHieu_Them[i].setText("");
                    }
                    
                    Them_ThuongHieu.dispose();
                    }
                    

                }else
                    cohieu = 0;
            }
        });
        Them_ThuongHieu.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_ThuongHieu.length; i++) {
                    txt_ThuongHieu_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_ThuongHieu.dispose();
            }
        });

        Them_ThuongHieu.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_ThuongHieu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String ma = Tool.tangMa3(ThuongHieuBUS.getMaThuongHieuCuoi()); //tăng mã
        txt_ThuongHieu_Them[0].setText(ma); //set mã
        txt_ThuongHieu_Them[0].setEditable(false);
        Them_ThuongHieu.setVisible(true);

    }

    //Hàm tạo Dialog sửa món ăn
    private void Sua_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Sua = new JDialog(f);
        Sua.setLayout(null);
        Sua.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Sua.setLocationRelativeTo(null);
        Sua.setUndecorated(true);
        //Tạo tiêu đề
        JLabel Title = new JLabel("Sửa thương hiệu");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_ThuongHieu.length; i++) {
            label_ThuongHieu[i] = new JLabel(array_ThuongHieu[i]);
            label_ThuongHieu[i].setBounds(100, y, 100, 30);
            Sua.add(label_ThuongHieu[i]);
            txt_ThuongHieu_Sua[i] = new JTextField();
            txt_ThuongHieu_Sua[i].setBounds(200, y, 150, 30);

            y += 40;
            Sua.add(txt_ThuongHieu_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    ThuongHieuDTO DTO = new ThuongHieuDTO();
                            DTO.setIDThuongHieu(txt_ThuongHieu_Sua[0].getText());
                            DTO.setTenThuongHieu(txt_ThuongHieu_Sua[1].getText());
                    if(checkTextSua(DTO))
                    {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                    buttonLuu_Sua(DTO);
                    Sua.dispose();
                    }
                    
                }else
                    cohieu = 0;

            }
        });
        Sua.add(Luu);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                Sua.dispose();
            }
        });
        Sua.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Sua.addWindowListener(new WindowAdapter() {

            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });
        Sua.setVisible(true);

    }

    //Hàm lưu dữ liệu khi sửa
    public void buttonLuu_Sua(ThuongHieuDTO DTO) {
        int row = table_ThuongHieu.tb.getSelectedRow();
        int colum = table_ThuongHieu.tb.getSelectedColumn();
        String maThuongHieu = table_ThuongHieu.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_ThuongHieu.length; j++) {
                table_ThuongHieu.tbModel.setValueAt(txt_ThuongHieu_Sua[j].getText(), row, j);
            }

            table_ThuongHieu.tb.setModel(table_ThuongHieu.tbModel);

            //Sửa dữ liệu trong database và arraylist trên bus
            
            //Tìm vị trí của row cần sửa
            int index = ThuongHieuBUS.timViTri(maThuongHieu);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//        }
    }

    @Override
    protected void Them_click(MouseEvent evt) {

        Them_Frame();
    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_ThuongHieu.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_ThuongHieu_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_ThuongHieu.length; j++) {
                txt_ThuongHieu_Sua[j].setText(table_ThuongHieu.tb.getValueAt(i, j).toString());
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_ThuongHieu.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maThuongHieu = table_ThuongHieu.tbModel.getValueAt(row, 0).toString();
                //truyền mã thương hiệu vào hàm timViTri ở ThuongHieuBUS 
                int index = ThuongHieuBUS.timViTri(maThuongHieu);
                //Xóa hàng ở table
                table_ThuongHieu.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maThuongHieu, index);
            }
        }

    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_ThuongHieu.clear();
        for (ThuongHieuDTO DTO : ThuongHieuBUS.dsth) {
                table_ThuongHieu.addRow(DTO);
            
        }
    }

    public void docDB() {
        ThuongHieuBUS Bus = new ThuongHieuBUS();
        if (ThuongHieuBUS.dsth == null) {
            try {
                Bus.docTH();
            } catch (Exception ex) {
                Logger.getLogger(GUIThuongHieu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (ThuongHieuDTO monAnDTO : ThuongHieuBUS.dsth) {
                table_ThuongHieu.addRow(monAnDTO);

            
        }
    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbsearch = new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x = 400;
        cbSearch = new JComboBox<>(array_ThuongHieu);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);

        search = new JTextField();
        search.setBorder(new TitledBorder(array_ThuongHieu[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);
        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();

        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);

        JButton LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(x += 320, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                search.setText("");
                LamMoi();

            }
        });
        TimKiem.add(LamMoi);

        return TimKiem;
    }


    public boolean checkTextThem(ThuongHieuDTO DTO) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (DTO.getTenThuongHieu().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(DTO.getTenThuongHieu()))) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu không được chứa ký tự đặc biệt");
            txt_ThuongHieu_Them[1].requestFocus();
        } else if (!Tool.isLength50(DTO.getTenThuongHieu())) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu không được quá 50 ký tự");
            txt_ThuongHieu_Them[1].requestFocus();
        } else {
            return true;

        }
        return false;
    }

    public boolean checkTextSua(ThuongHieuDTO DTO) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (DTO.getTenThuongHieu().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(DTO.getTenThuongHieu()))) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu không được chứa ký tự đặc biệt");
            txt_ThuongHieu_Sua[1].requestFocus();
        } else if (!Tool.isLength50(DTO.getTenThuongHieu())) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu không được quá 50 ký tự");
            txt_ThuongHieu_Sua[1].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    private void addDocumentListener(JTextField tx) { // để cho hàm tìm kiếm
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }
        });
    }
    
    public void txtSearchOnChange() {
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchTH(search.getText(),cbSearch.getSelectedItem().toString()), table_ThuongHieu); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<ThuongHieuDTO> nhaCungCapDTO, GUIMyTable myTable) {
        myTable.clear();
        for (ThuongHieuDTO nhaCungCap : nhaCungCapDTO) {
            table_ThuongHieu.addRow(nhaCungCap);
        }
    }
}
