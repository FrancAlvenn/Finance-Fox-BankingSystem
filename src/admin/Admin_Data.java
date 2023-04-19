
package admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


public interface Admin_Data {
    static final String username = "root";
    static final String password = "";
    static final String database = "jdbc:mysql://localhost/finance_fox";   
    static Random rand = new Random();
    static Integer AccountNumber = rand.nextInt(100000000, 999999999);
    
    
    
    ImageIcon foxSVG = new FlatSVGIcon("icon/Brand Logo (NG).svg",430,430);
    ImageIcon menuBarsSVG = new FlatSVGIcon("icon/MenuBars.svg",20,20);
    Image brandIconSVG = new FlatSVGIcon("icon/Brand Logo (NG).svg").getImage();
    ImageIcon logoutSVG = new FlatSVGIcon("icon/Logout.svg",20,20);
    ImageIcon mbSearchSVG = new FlatSVGIcon("icon/Search_Icon.svg",20,20);
    ImageIcon addUserSVG = new FlatSVGIcon("icon/Add_User.svg",25,20);
    ImageIcon updateSVG = new FlatSVGIcon("icon/Update.svg",20,20);
    ImageIcon deleteSVG = new FlatSVGIcon("icon/Delete.svg",18,20);
    ImageIcon searchSVG = new FlatSVGIcon("icon/Search_Icon.svg",20,20);
    ImageIcon iconSVG = new FlatSVGIcon("icon/Brand Logo (NG).svg",70,70);
    ImageIcon copySVG = new FlatSVGIcon("icon/Copy.svg",20,20);
    ImageIcon profileSVG = new FlatSVGIcon("icon/User.svg",70,70);
    ImageIcon homeSVG = new FlatSVGIcon("icon/Home.svg",20,20);
    ImageIcon mbInboxSVG = new FlatSVGIcon("icon/Inbox.svg",20,20);
    ImageIcon settingsSVG = new FlatSVGIcon("icon/Settings.svg",20,20);
    ImageIcon contactSVG = new FlatSVGIcon("icon/Contact Us.svg",20,20);
    ImageIcon infoSVG = new FlatSVGIcon("icon/Info.svg",30,30);
    ImageIcon plusSVG = new FlatSVGIcon("icon/Plus Circle.svg",38,38);
    ImageIcon depositSVG = new FlatSVGIcon("icon/Deposit.svg",30,30);
    ImageIcon withdrawSVG = new FlatSVGIcon("icon/Withdraw.svg",30,30);
    ImageIcon transferFundsSVG = new FlatSVGIcon("icon/Transfer Funds.svg",30,30);
    ImageIcon payBillsSVG = new FlatSVGIcon("icon/PayBills.svg",30,30);
    ImageIcon transactionHistorySVG = new FlatSVGIcon("icon/Transaction History.svg",30,30);
    ImageIcon inboxSVG = new FlatSVGIcon("icon/Inbox.svg",30,30);
    ImageIcon closeSVG = new FlatSVGIcon("icon/Close.svg",30,30);
    
    
    static final String createAdminTable = ("""
                                           CREATE TABLE IF NOT EXISTS `finance_fox`.`admin_user` (
                                            `ID` INT NOT NULL AUTO_INCREMENT,
                                            `Account_Number` VARCHAR(10) NOT NULL,
                                            `Name` VARCHAR(100) NOT NULL,
                                            `Password` VARCHAR(100) NOT NULL,
                                            PRIMARY KEY (`ID`, `Account_Number`));
                                           """);
    
    static final String createAdminUser = ("""
                                               INSERT INTO admin_user(Account_Number,Name,Password) 
                                               SELECT * FROM (SELECT 'admin', 'Franc Alvenn Dela Cruz', 'financefox') AS tmp
                                               WHERE NOT EXISTS (
                                                    SELECT Name FROM admin_user WHERE Name = 'Franc Alvenn Dela Cruz'
                                               ) LIMIT 1;
                                           """);
    
}
