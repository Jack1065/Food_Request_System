package Project1;
import javax.swing.*;




public class Frame extends Buttons{

//Creation of the frame which is then executed in the main.java file
      Frame(){

        System.out.println("Order Number 1\n");
        frame = new JFrame();

        frame.setLayout(null);
        frame.setTitle("Jacks Fast Food");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        




        addComponent(mainPanel,burgers,fries,drinks);
        
        frame.add(exit);
        frame.add(cancelOrder);
        frame.add(newOrder);
        frame.add(burgersPanel);
        frame.add(drinksPanel);
        frame.add(friesPanel);
        frame.add(cash);
        frame.add(card);
        frame.add(completeOrder);
        frame.add(clear);
        frame.add(mainPanel);
        frame.add(textArea);
        frame.getContentPane().setBackground(color);
        frame.setVisible(true);

        
      }

}
