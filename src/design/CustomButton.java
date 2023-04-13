
package design;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class CustomButton extends JButton{

    
    public static boolean MousePress;

    
    public CustomButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(7,5,7,5));
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    MousePress=true;

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    MousePress=false;

                }
            }
            
            
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(MousePress){
            g2.setColor(getBackground().darker());
        }else{
            g2.setColor(getBackground());
        }
        g2.fill(new RoundRectangle2D.Double(0, 0 , getWidth(), getHeight(), getHeight(), getHeight()));
        g2.dispose();
        super.paintComponent(g); 
    }
    
    
    
    
}
