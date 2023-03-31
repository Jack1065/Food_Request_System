package Project1;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;

public class Panel {
//All panels used in the frame and the fonts used for the lettering in each JButton

JTextArea textArea;
JPanel mainPanel;
JPanel burgersPanel;
JPanel drinksPanel;
JPanel friesPanel;

//Color of every font
Font font = new Font("Serif", Font.BOLD, 25);
Font font1 = new Font("Serif", Font.BOLD, 30);
Color color = new Color(250, 234, 203);
Panel(){
    

    
    mainPanel = createPanel(mainPanel, color);
    burgersPanel = createPanel(burgersPanel,color);
    drinksPanel = createPanel(drinksPanel, color);
    friesPanel = createPanel(friesPanel, color);
}

    public void addComponent(JPanel panel, JButton b1, JButton b2, JButton b3){
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
    }
    public void addComponent(JPanel panel, JButton b1, JButton b2, JButton b3, JButton b4){
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
    }

    public void removeComponent(JPanel panel, JButton b1, JButton b2, JButton b3){
        panel.remove(b1);
        panel.remove(b2);
        panel.remove(b3);
    }
//creates a panel with desired color
public JPanel createPanel(JPanel mainPanel, Color color){
    mainPanel= new JPanel();
    mainPanel.setVisible(true);
    mainPanel.setBounds(0, 0, 1000, 600);
    mainPanel.setBackground(color);
    mainPanel.setLayout(null);
    return mainPanel;
    }

}
