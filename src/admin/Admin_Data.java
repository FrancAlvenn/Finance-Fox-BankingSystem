
package admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javaswingdev.drawer.DrawerController;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.SQLException;


public interface Admin_Data {
    static final String username = "root";
    static final String password = "";
    static final String database = "jdbc:mysql://localhost/finance_fox";
    static Double balance = 0.0, currentValue = 0.0;
    static String[] info = {"0","1","2","3","4","5","6"};

    static Connection sqlConn = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    static DefaultTableModel RecordTable = null;
    
    
    public final DrawerController drawer = null;
    ImageIcon menuBarsSVG = new FlatSVGIcon("icon/MenuBars.svg",20,20);
    Image brandIconSVG = new FlatSVGIcon("icon/Brand Logo (NG).svg").getImage();
    ImageIcon logoutSVG = new FlatSVGIcon("icon/Logout.svg",20,20);
    ImageIcon mbSearchSVG = new FlatSVGIcon("icon/Search_Icon.svg",20,20);
    ImageIcon addUserSVG = new FlatSVGIcon("icon/Add_User.svg",25,20);
    ImageIcon updateSVG = new FlatSVGIcon("icon/Update.svg",20,20);
    ImageIcon deleteSVG = new FlatSVGIcon("icon/Delete.svg",18,20);
    ImageIcon searchSVG = new FlatSVGIcon("icon/Search_Icon.svg",20,20);
    
    
    public static Connection getConnection(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(database, username, password);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    return con;
    }
}
