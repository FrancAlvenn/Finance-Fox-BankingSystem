
package javaui_bankingsystem;


import com.formdev.flatlaf.extras.FlatSVGIcon;
import design.GlassPanePopup;
import design.TableCustom;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import static javaui_bankingsystem.Message.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class MainFrame extends javax.swing.JFrame {

    private final DrawerController drawer;
    static final String username = "root";
    static final String password = "";
    static final String database = "jdbc:mysql://localhost/finance_fox";
    static int q, i;
    static String value, userNameDis = "";
    static Double balance = 0.0, currentValue = 0.0;
    static String[] info = {"0","1","2","3","4","5","6"};
    
    
    static Connection sqlConn = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    static DefaultTableModel RecordTable;
    
    //image loader
    Image brandIconSVG = new FlatSVGIcon("icon/Brand Logo (NG).svg").getImage();
    ImageIcon menuBarsSVG = new FlatSVGIcon("icon/MenuBars.svg",20,20);
    ImageIcon profileSVG = new FlatSVGIcon("icon/User.svg",70,70);
    //side bar menu icons
    ImageIcon homeSVG = new FlatSVGIcon("icon/Home.svg",20,20);
    ImageIcon mbInboxSVG = new FlatSVGIcon("icon/Inbox.svg",20,20);
    ImageIcon settingsSVG = new FlatSVGIcon("icon/Settings.svg",20,20);
    ImageIcon contactSVG = new FlatSVGIcon("icon/Contact Us.svg",20,20);
    ImageIcon logoutSVG = new FlatSVGIcon("icon/Logout.svg",20,20);
    ImageIcon mbSearchSVG = new FlatSVGIcon("icon/Search_Icon.svg",20,20);
   
    ImageIcon copySVG = new FlatSVGIcon("icon/Copy.svg",20,20);
    ImageIcon infoSVG = new FlatSVGIcon("icon/Info.svg",30,30);
    //bank functionality
    ImageIcon plusSVG = new FlatSVGIcon("icon/Plus Circle.svg",38,38);
    ImageIcon depositSVG = new FlatSVGIcon("icon/Deposit.svg",30,30);
    ImageIcon withdrawSVG = new FlatSVGIcon("icon/Withdraw.svg",30,30);
    ImageIcon transferFundsSVG = new FlatSVGIcon("icon/Transfer Funds.svg",30,30);
    ImageIcon payBillsSVG = new FlatSVGIcon("icon/PayBills.svg",30,30);
    ImageIcon searchSVG = new FlatSVGIcon("icon/Search_Icon.svg",30,30);
    ImageIcon transactionHistorySVG = new FlatSVGIcon("icon/Transaction History.svg",30,30);
    ImageIcon inboxSVG = new FlatSVGIcon("icon/Inbox.svg",30,30);
    
 
    public MainFrame() {
        
        initComponents();
        GlassPanePopup.install(this);
        disAccountNumber.setText(value);
        disBalance.setText(Double.toString(currentValue));
        disUserName.setText(userNameDis);
        phoneNumber.setText(info[0]);
        gender.setText(info[1]);
        address.setText(info[2]);
        citizenship.setText(info[3]);
        maritalStatus.setText(info[4]);
        occupation.setText(info[5]);
        email.setText(info[6]);
        
        //icon setter
        this.setIconImage(brandIconSVG);
        menuBar.setIcon(menuBarsSVG);
        home.setIcon(homeSVG);
        search.setIcon(mbSearchSVG);
        inbox.setIcon(mbInboxSVG);
        contact.setIcon(contactSVG);
        settings.setIcon(settingsSVG);
        logout.setIcon(logoutSVG);
        profile.setIcon(profileSVG);
        copy.setIcon(copySVG);
        btnplus.setIcon(plusSVG);
        
        //bank features button
        btnDeposit.setIcon(depositSVG);
        btnWithdraw.setIcon(withdrawSVG);
        btnTransferFunds.setIcon(transferFundsSVG);
        btnPayBills.setIcon(payBillsSVG);
        btnSearch.setIcon(searchSVG);
        btnTransactionHistory.setIcon(transactionHistorySVG);
        btnInbox.setIcon(inboxSVG);
        btnAbout.setIcon(infoSVG);
        
        
        //drawer menu and items 
        drawer = Drawer.newDrawer(this)
                .header(new Header())
                .separator(2, new Color(178,184,189))
                .space(15)
                .background(new Color(55,105,241))
                .addChild(new DrawerItem("HOME").icon(homeSVG).build())
                .addChild(new DrawerItem("SEARCH").icon(mbSearchSVG).build())
                .addChild(new DrawerItem("INBOX").icon(mbInboxSVG).build())
                .addChild(new DrawerItem("CONTACT").icon(contactSVG).build())
                .addChild(new DrawerItem("SETTINGS").icon(settingsSVG).build())
                .addFooter(new DrawerItem("LOGOUT").icon(logoutSVG).build())
                
        //event listener for menu items
                .event(new EventDrawer() {
            @Override
            public void selected(int index, DrawerItem item) {
                System.out.println(index);
                switch (index) {
                    case 0:
                        MainFrame mf = new MainFrame();
                        mf.show();
                        dispose();
                        break;
                    case 1:
                        Search s = new Search();
                        s.show();
                        dispose();
                        break;
                    case 2:
                        Inbox inbox = new Inbox();
                        inbox.show();
                        dispose();
                        break;
                    case 3:
                        Contact contact = new Contact();
                        contact.show();
                        dispose();
                        break;
                    case 4:
                        Settings settings = new Settings();
                        settings.show();
                        dispose();
                        break;
                    case 5:
                        int _temp = JOptionPane.showConfirmDialog(null, "Confirm Logout ...", "LOGOUT", JOptionPane.YES_NO_OPTION);
                        if( _temp == 0){
                            Login log = new Login();
                            log.show();  
                            dispose();
                        }   break;
                    default:
                        break;
                }
            }
                })
                .build();
        
        
        //Table Customization
        TableCustom.apply(jScrollPane1,TableCustom.TableType.DEFAULT);
    }

    
    //-------------------------------------------------------------------------------------------//
    public static void updateDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(database,username,password);
            pst = sqlConn.prepareStatement("SELECT * FROM finance_fox." + value);
            //pst.setString(1, value);
            rs = pst.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            RecordTable = (DefaultTableModel)dataTable.getModel();
            RecordTable.setRowCount(0);
            while(rs.next()){
                   Vector columnData = new Vector();
                  
                   for(i = 1;i <= q;i++){
                       columnData.add(rs.getString("ID"));
                       columnData.add(rs.getString("DATE"));
                       columnData.add(rs.getString("DESCRIPTION"));
                       columnData.add(rs.getDouble("AMOUNT"));
                       columnData.add(rs.getDouble("BALANCE"));
                   }
                   RecordTable.addRow(columnData); 
               }
             
               
        }catch(ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
            //JOptionPane.showMessageDialog(null, ex);
    }
    }
    
    //access balance from database
        public static void getBalance(){
            try{
            Connection connection = DriverManager.getConnection(database, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT Balance FROM bank_user WHERE Account_Number = ?");
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            currentValue = resultSet.getDouble("Balance");
            
            }catch(SQLException ex){
                
            } 
        }
    //-------------------------------------------------------------------------------------------//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        disUserName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        disAccountNumber = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        citizenship = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        maritalStatus = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        occupation = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        copy = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        disBalance = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnplus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnDeposit = new javax.swing.JLabel();
        btnWithdraw = new javax.swing.JLabel();
        btnTransferFunds = new javax.swing.JLabel();
        btnPayBills = new javax.swing.JLabel();
        btnSearch = new javax.swing.JLabel();
        btnTransactionHistory = new javax.swing.JLabel();
        btnInbox = new javax.swing.JLabel();
        btnAbout = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        tableScrollButton1 = new design.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        DrawerPanel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        menuBar = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        inbox = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        search = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finance Fox");
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(new java.awt.Color(102, 102, 102));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        MainPanel.setBackground(new java.awt.Color(236, 237, 239));

        jPanel1.setBackground(new java.awt.Color(212, 214, 218));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        disUserName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        disUserName.setForeground(new java.awt.Color(51, 51, 51));
        disUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disUserName.setText("USER NAME");

        jLabel1.setBackground(new java.awt.Color(55, 105, 241));
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("ACCOUNT NUMBER");

        disAccountNumber.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        disAccountNumber.setForeground(new java.awt.Color(51, 51, 51));
        disAccountNumber.setText("000000000000");

        jButton1.setBorder(null);

        jLabel12.setBackground(new java.awt.Color(55, 105, 241));
        jLabel12.setForeground(new java.awt.Color(0, 153, 255));
        jLabel12.setOpaque(true);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("PROFILE");

        jLabel23.setBackground(new java.awt.Color(153, 153, 153));
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Phone Number");

        jLabel24.setBackground(new java.awt.Color(153, 153, 153));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));

        phoneNumber.setBackground(new java.awt.Color(153, 153, 153));
        phoneNumber.setForeground(new java.awt.Color(102, 102, 102));
        phoneNumber.setText("no");

        jLabel26.setBackground(new java.awt.Color(153, 153, 153));
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Gender");

        gender.setBackground(new java.awt.Color(153, 153, 153));
        gender.setForeground(new java.awt.Color(102, 102, 102));
        gender.setText("no");

        jLabel28.setBackground(new java.awt.Color(153, 153, 153));
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Address");

        address.setBackground(new java.awt.Color(153, 153, 153));
        address.setForeground(new java.awt.Color(102, 102, 102));
        address.setText("no");

        jLabel30.setBackground(new java.awt.Color(153, 153, 153));
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Citizenship");

        citizenship.setBackground(new java.awt.Color(153, 153, 153));
        citizenship.setForeground(new java.awt.Color(102, 102, 102));
        citizenship.setText("no");

        jLabel32.setBackground(new java.awt.Color(153, 153, 153));
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Marital Status");

        maritalStatus.setBackground(new java.awt.Color(153, 153, 153));
        maritalStatus.setForeground(new java.awt.Color(102, 102, 102));
        maritalStatus.setText("no");

        jLabel34.setBackground(new java.awt.Color(153, 153, 153));
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Occupation");

        occupation.setBackground(new java.awt.Color(153, 153, 153));
        occupation.setForeground(new java.awt.Color(102, 102, 102));
        occupation.setText("no");

        jLabel36.setBackground(new java.awt.Color(153, 153, 153));
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Email");

        email.setBackground(new java.awt.Color(153, 153, 153));
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("no");

        copy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                copyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(14, 14, 14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(maritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(citizenship, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel24))
                            .addComponent(jLabel22)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(occupation, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(disUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(disAccountNumber)
                        .addGap(39, 39, 39)
                        .addComponent(copy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(disUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(disAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(copy, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel26)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel28)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel30)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(phoneNumber)
                        .addGap(25, 25, 25)
                        .addComponent(gender)
                        .addGap(25, 25, 25)
                        .addComponent(address)
                        .addGap(25, 25, 25)
                        .addComponent(citizenship)
                        .addGap(25, 25, 25)
                        .addComponent(maritalStatus)))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(occupation)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email)
                            .addComponent(jLabel36))))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("AVAILABLE BALANCE");

        disBalance.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        disBalance.setText("0.00");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel11.setText("P");

        btnplus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnplusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnplus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnplus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnDeposit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeposit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDepositMouseClicked(evt);
            }
        });

        btnWithdraw.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnWithdraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnWithdrawMouseClicked(evt);
            }
        });

        btnTransferFunds.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTransferFunds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransferFundsMouseClicked(evt);
            }
        });

        btnPayBills.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPayBills.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPayBillsMouseClicked(evt);
            }
        });

        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        btnTransactionHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTransactionHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransactionHistoryMouseClicked(evt);
            }
        });

        btnInbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInboxMouseClicked(evt);
            }
        });

        btnAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAboutMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Transaction History");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Withdraw");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Transfer Funds");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("PayBills");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Deposit");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Search");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Inbox");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("About");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeposit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnWithdraw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTransactionHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(btnTransferFunds, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel16))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addGap(89, 89, 89)
                                    .addComponent(btnInbox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel14)))
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPayBills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTransferFunds, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPayBills, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTransactionHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnInbox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DATE", "DESCRIPTION", "AMOUNT", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);
        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            dataTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("TRANSACTION HISTORY");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        DrawerPanel.setBackground(new java.awt.Color(255, 255, 255));
        DrawerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrawerPanelMouseClicked(evt);
            }
        });

        menuBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        inbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inboxMouseClicked(evt);
            }
        });

        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactMouseClicked(evt);
            }
        });

        settings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });

        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DrawerPanelLayout = new javax.swing.GroupLayout(DrawerPanel);
        DrawerPanel.setLayout(DrawerPanelLayout);
        DrawerPanelLayout.setHorizontalGroup(
            DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DrawerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DrawerPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DrawerPanelLayout.createSequentialGroup()
                        .addGroup(DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(settings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inbox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DrawerPanelLayout.setVerticalGroup(
            DrawerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DrawerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(menuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inbox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(DrawerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DrawerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DrawerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrawerPanelMouseClicked
        if (drawer.isShow()) {
            drawer.hide();
        } else {
            drawer.show();
        }
    }//GEN-LAST:event_DrawerPanelMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         updateDB();
    }//GEN-LAST:event_formWindowOpened

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        int _temp = JOptionPane.showConfirmDialog(null, "Confirm Logout ...", "LOGOUT", JOptionPane.YES_NO_OPTION);
        if( _temp == 0){
            Login log = new Login();
            log.show();
            dispose();
        }
        
    }//GEN-LAST:event_logoutMouseClicked

    private void btnDepositMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositMouseClicked
        Deposit deposit = new Deposit();
        deposit.show();
        dispose();
    }//GEN-LAST:event_btnDepositMouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        Deposit deposit = new Deposit();
        deposit.show();
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void btnplusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnplusMouseClicked
        Deposit deposit = new Deposit();
        deposit.show();
        dispose();
    }//GEN-LAST:event_btnplusMouseClicked

    private void btnWithdrawMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWithdrawMouseClicked
       Withdraw withdraw = new Withdraw();
       withdraw.show();
       dispose();
    }//GEN-LAST:event_btnWithdrawMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
       Withdraw withdraw = new Withdraw();
       withdraw.show();
       dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btnTransferFundsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferFundsMouseClicked
       TransferFunds transferfunds = new TransferFunds();
       transferfunds.show();
       dispose();
    }//GEN-LAST:event_btnTransferFundsMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
       TransferFunds transferfunds = new TransferFunds();
       transferfunds.show();
       dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void btnPayBillsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayBillsMouseClicked
       PayBills pb = new PayBills();
       pb.show();
       dispose();
    }//GEN-LAST:event_btnPayBillsMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
       PayBills pb = new PayBills();
       pb.show();
       dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        Search s = new Search();
        s.show();
        dispose();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        Search s = new Search();
        s.show();
        dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void btnTransactionHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransactionHistoryMouseClicked
        Transaction_History th = new Transaction_History();
        th.show();
        dispose();
    }//GEN-LAST:event_btnTransactionHistoryMouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Transaction_History th = new Transaction_History();
        th.show();
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void btnInboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInboxMouseClicked
        Inbox inbox = new Inbox();
        inbox.show();
        dispose();
    }//GEN-LAST:event_btnInboxMouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        Inbox inbox = new Inbox();
        inbox.show();
        dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void inboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inboxMouseClicked
        Inbox inbox = new Inbox();
        inbox.show();
        dispose();
    }//GEN-LAST:event_inboxMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        Search s = new Search();
        s.show();
        dispose();
    }//GEN-LAST:event_searchMouseClicked

    private void contactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactMouseClicked
        Contact contact = new Contact();
        contact.show();
        dispose();
    }//GEN-LAST:event_contactMouseClicked

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
        Settings settings = new Settings();
        settings.show();
        dispose();
    }//GEN-LAST:event_settingsMouseClicked

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        About about = new About();
        about.show();
        dispose();
    }//GEN-LAST:event_btnAboutMouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        About about = new About();
        about.show();
        dispose();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void copyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_copyMouseClicked
       StringSelection stringSelection = new StringSelection(disAccountNumber.getText());
       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
       clipboard.setContents(stringSelection, null);
       
       
       JOptionPane.showMessageDialog(null, "Account Number copied to clipboard");
//        msgtitle = "NOTICE!";
//        msgbody = ("Account Number copied to clipboard!");
//        GlassPanePopup.showPopup(new Message());
    }//GEN-LAST:event_copyMouseClicked

    
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new SplashScreen(null,true).setVisible(true);
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DrawerPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel address;
    private javax.swing.JLabel btnAbout;
    private javax.swing.JLabel btnDeposit;
    private javax.swing.JLabel btnInbox;
    private javax.swing.JLabel btnPayBills;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnTransactionHistory;
    private javax.swing.JLabel btnTransferFunds;
    private javax.swing.JLabel btnWithdraw;
    private javax.swing.JLabel btnplus;
    private javax.swing.JLabel citizenship;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel copy;
    public static javax.swing.JTable dataTable;
    public javax.swing.JLabel disAccountNumber;
    public static javax.swing.JLabel disBalance;
    private javax.swing.JLabel disUserName;
    private javax.swing.JLabel email;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel home;
    private javax.swing.JLabel inbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel maritalStatus;
    private javax.swing.JLabel menuBar;
    private javax.swing.JLabel occupation;
    private javax.swing.JLabel phoneNumber;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel search;
    private javax.swing.JLabel settings;
    private design.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
