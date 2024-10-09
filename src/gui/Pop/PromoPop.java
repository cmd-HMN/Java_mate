package src.gui.Pop;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;

import java.awt.Font;

import java.awt.Color;
import java.awt.GridLayout;

public class PromoPop extends JFrame {
    JFrame f;
    PopupFactory pf;
    JPanel p;
    Popup poop;
    public PromoPop(){
        f = new JFrame("Fuck me");
        pf = new PopupFactory();
        JLabel l = new JLabel("This  is a popup menu");
        JButton b19 = new JButton("OK");
        try {
            // set windows look and feel
            UIManager.setLookAndFeel(UIManager.
                  getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
 
        // create a panel
        p = new JPanel();
 
        p.setBackground(Color.BLUE);
        Font fo = new Font("BOLD", 1, 14);
 
        l.setFont(fo);
 
        // add contents to panel
        p.add(l);
        p.add(b19);
 
        p.setLayout(new GridLayout(2, 1));
 
        // create a popup
        poop = pf.getPopup(f, p, 180, 100);
 
        // create a button
        JButton b = new JButton("click");
 

 
        // create a panel
        JPanel p1 = new JPanel();
 
        p1.add(b);
        f.add(p1);
        f.show();
    }

    public String get_promoting_string(){
        return "harder";
    }
}