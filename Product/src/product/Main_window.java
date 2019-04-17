
package product;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Main_window extends javax.swing.JFrame {

    /**
     * Creates new form Main_window
     */
    public Main_window() throws SQLException {
        initComponents();
        show_product_JTable();
        //layKetNoi();
    }
    String imgPath=null;
    int pos;
   public Connection layKetNoi(){
       Connection con =null;
       try {
           con=DriverManager.getConnection("jdbc:mysql://localhost/products_db","root","");
          // JOptionPane.showMessageDialog(null,"ket noi thanh cong");
           return con;
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"ket kong  noi thanh cong");
           return null;
       }
   }
   // kiem tra file nhap lieu
   public boolean checkInputs()
   {
       if(txt_name.getText()==null
               || txt_price.getText()==null
               || txt_name.getText()==null
               || txt_addDate.getDate()==null)
       {
           return false;
           
       }
       else{
           try {
               Float.parseFloat(txt_price.getText());
               return true;
           } catch (Exception e) {
               return false;
           }
          
       }
   }
   
   public ImageIcon Resize(String imagePath,byte[] pic){
       ImageIcon myImage=null;
       if(imagePath !=null){
           myImage=new ImageIcon(imagePath);
           
       }else
       {
           myImage=new ImageIcon(pic);
       }
       Image img=myImage.getImage();
       Image img2=img.getScaledInstance(lbImg.getWidth(),lbImg.getHeight(),Image.SCALE_SMOOTH);
       ImageIcon image=new ImageIcon(img2);
       return image;
       
   }
   public ImageIcon ResizeImage(String imagePath,byte[] pic){
       ImageIcon myImage=null;
       if(imagePath!=null){
           myImage=new ImageIcon(imagePath);
       }
       else{
           myImage=new ImageIcon(pic);
       }
       Image img=myImage.getImage();
       Image img2=img.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(),Image.SCALE_SMOOTH);
       ImageIcon image=new ImageIcon(img2);
       return image;
   }
   
   // load du lieu vao jTable
   public ArrayList<Product> layProduct() throws SQLException
   {
       ArrayList<Product> productList=new ArrayList<Product>();
       Connection con=layKetNoi();
       String sql="select * from products";
       Statement st;
       ResultSet rs;
       Product product =new Product();
       try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                product=new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
//               product.setId(rs.getInt("id"));
//               product.setName(rs.getString("name"));
//               product.setPrice(Float.parseFloat(rs.getString("price")));
//               product.setAddDate(rs.getString("add_date"));
//               product.setPricture(rs.getBytes("images"));
                productList.add(product);
            }
       }catch(Exception ex)
       {
           
       }
       return productList;
   }
   // show du lieu
   public void show_product_JTable() throws SQLException
   {
       ArrayList<Product> list=layProduct();
       DefaultTableModel modle=(DefaultTableModel) JTable_Product.getModel();
       // clear Jtable
       modle.setRowCount(0);
       Object[] row =new Object[4];
       for(int i=0;i<list.size();i++)
       {
           row[0]=list.get(i).getId();
           row[1]=list.get(i).getName();
           row[2]=list.get(i).getPrice();
           row[3]=list.get(i).getAddDate();
           modle.addRow(row);
       }
   }
   // load vao iteam
   public void showItem(int index) throws SQLException, ParseException
   {
       txt_id.setText(Integer.toString(layProduct().get(index).getId()));
       txt_name.setText(layProduct().get(index).getName());
       txt_price.setText(Float.toString(layProduct().get(index).getPrice()));
       Date addDate=null;
       addDate=new SimpleDateFormat("yyyy-MM-dd").parse((String)layProduct().get(index).getAddDate());
       txt_addDate.setDate(addDate);
       lbImg.setIcon(ResizeImage(null,layProduct().get(index).getPricture()));
               
       
       
       
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        lbImg = new javax.swing.JLabel();
        btnChonImage = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt_addDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Product = new javax.swing.JTable();
        btn_Frist = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Previous = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText(" Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Price :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Add date");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Image");

        lbImg.setBackground(new java.awt.Color(204, 255, 255));
        lbImg.setText("jLabel5");
        lbImg.setOpaque(true);

        btnChonImage.setText("Chon Image");
        btnChonImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonImageActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        jButton3.setText("update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txt_addDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txt_addDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnChonImage, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton3)
                                            .addGap(35, 35, 35)
                                            .addComponent(jButton4))
                                        .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(btn_insert))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_addDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChonImage)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        JTable_Product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "addDate"
            }
        ));
        JTable_Product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Product);

        btn_Frist.setText("Frist");
        btn_Frist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FristActionPerformed(evt);
            }
        });

        btn_Next.setText("Next");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Previous.setText("Previous");
        btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PreviousActionPerformed(evt);
            }
        });

        btn_Last.setText("Last");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_Frist)
                        .addGap(26, 26, 26)
                        .addComponent(btn_Next)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Last))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Frist)
                            .addComponent(btn_Next)
                            .addComponent(btn_Previous)
                            .addComponent(btn_Last))
                        .addGap(0, 91, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonImageActionPerformed
       JFileChooser file =new JFileChooser();
       file.setCurrentDirectory(new File(System.getProperty("user.home")));
       
        FileNameExtensionFilter fileter=new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(fileter);
        int result=file.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
            File selectedFile=file.getSelectedFile();
            String path=selectedFile.getAbsolutePath();
            lbImg.setIcon(ResizeImage(path,null));
            imgPath=path;
        }
        else{
            System.out.println("NO file select");
        }
    }//GEN-LAST:event_btnChonImageActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        
        if(checkInputs()&& imgPath!=null)
        {
            String sql="insert into products(name,price,add_date,image) value(?,?,?,?)";
            Connection con=layKetNoi();
            try {
              PreparedStatement ps=con.prepareStatement(sql);
              ps.setString(1,txt_name.getText());
              ps.setString(2, txt_price.getText());;
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String addDate=dateFormat.format(txt_addDate.getDate());
             ps.setString(3,addDate);
             
                InputStream img=new  FileInputStream(new File(imgPath));
                ps.setBlob(4,img);
                ps.executeUpdate();
                show_product_JTable();
                JOptionPane.showMessageDialog(rootPane,"them thanh cong");
            } catch (FileNotFoundException ex) {
               
            } catch (SQLException ex) {
                Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null," lai nhap file");
        }
//        System.out.println(""+txt_name.getText());
//         System.out.println(""+txt_price.getText());
//          System.out.println(""+imgPath);

         
    }//GEN-LAST:event_btn_insertActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(checkInputs()&&txt_id.getText()!=null)
        {
            String updateQuery=null;
            PreparedStatement ps=null;
            Connection con=layKetNoi();
            // update vs ko image
            if(imgPath==null)
            {
                updateQuery="update products set name=?, price=?,add_date=? where id=?";
                try {
                    ps=con.prepareStatement(updateQuery);
                    ps.setString(1,txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    String addDate=dateFormat.format(txt_addDate.getDate());
                    ps.setString(3, addDate);
                    ps.setInt(4,Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane,"Update thanh cong");
                    show_product_JTable();
                } catch (SQLException ex) {
                    Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                        
                      
            }
            else// update vs image
            {
                try {
                        InputStream img=new FileInputStream(new File(imgPath));
                        updateQuery="update products set name=?, price=?,add_date=? ,image=?where id=?";
                        ps=con.prepareStatement(updateQuery);
                        ps.setString(1,txt_name.getText());
                        ps.setString(2, txt_price.getText());
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        String addDate=dateFormat.format(txt_addDate.getDate());
                        ps.setString(3, addDate);
                        ps.setBlob(4,img);
                        ps.setInt(5,Integer.parseInt(txt_id.getText()));
                           ps.executeUpdate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,ex.getMessage());
                }
                 
                   
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(!txt_id.getText().equals(""))
        {
            Connection con=layKetNoi();
            String sql="delete from products where id=?";
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(sql);
                int id=Integer.parseInt(txt_id.getText());
                ps.setInt(1,id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(rootPane,"xoa thanh cong");
                show_product_JTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane,"xoa that bai"+ex);
            }
            //con.close();
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void JTable_ProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductMouseClicked
        int index=JTable_Product.getSelectedRow();
        try {
            showItem(index);
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JTable_ProductMouseClicked

    private void btn_FristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FristActionPerformed
        // TODO add your handling code here:
        pos=0;
        try {
            showItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_FristActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        try {
            // TODO add your handling code here:
            pos=layProduct().size()-1;
            showItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        // TODO add your handling code here:
        pos++;
        try {
            if(pos>=layProduct().size())
            {
               pos=layProduct().size()-1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            showItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PreviousActionPerformed
        // TODO add your handling code here:
        pos--;
        if(pos<0)
        {
            pos=0;
        }
        try {
            showItem(pos);
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_PreviousActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main_window().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Product;
    private javax.swing.JButton btnChonImage;
    private javax.swing.JButton btn_Frist;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Previous;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImg;
    private com.toedter.calendar.JDateChooser txt_addDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
