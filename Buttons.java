package Project1;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.*;

public class Buttons extends Panel implements ActionListener {
    //Every JButton,primitive and data structure used for either storage or usage
    double sum,taxable,taxIncluded,totalBilling;
    int orderAmount;
    int beefCount,fishCount,WarhawkCount,smallCount,MediumCount,largeCount,pepsiCount,cokeCount,spriteCount;
    int endBeef,endFish,endWarhawk,endSmall,endMedium,endLarge,endPepsi,endCoke,endSprite;
    int cancelCounter, ProcessedCounter, completedCounter;

    JFrame frame;
    JButton friesbackButton, burgersbackButton,drinksbackButton;
    JButton beefBurger, fishBurger, warhawkBurger;
    JButton small, medium, large;
    JButton pepsi, coke, sprite;
    JButton clear, newOrder, completeOrder,cancelOrder;
    JButton burgers, fries, drinks;
    JButton cash, card;
    JButton exit;
    FileWriter output;
    private JButton[] burger = new JButton[]{beefBurger, fishBurger, warhawkBurger,burgersbackButton};
    private JButton[] fry = new JButton[]{small,medium,large,friesbackButton};
    private JButton[] drink = new JButton[]{pepsi,coke,sprite,drinksbackButton};
    private JButton[] allButtons = new JButton[]{burgers,fries,drinks};
    //linked lists..One holds temporary orders while the other has cummulative order amounts
    LinkedList<Double> order;
    LinkedList<Double> total;
    

    Buttons(){
    //method to initialize all variables and assign vallues
    orderAmount = 1;
    //create new file if it doesnt already exist
    File file = new File("OrderSummary.txt");
    try {
        if(!(file.exists())){
        file.createNewFile();
        }
        
        output = new FileWriter("OrderSummary.txt",true);
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
     

        


        order = new LinkedList<Double>();
        total = new LinkedList<Double>();

        //setting bounds and activating buttons
        textArea = new JTextArea();
        textArea.setBounds(1100, 75, 400, 700);
        textArea.setFont(font);
        textArea.setEditable(false);
        cash = new JButton("Cash");
        card = new JButton("Card");
        activateButton(cash);
        activateButton(card);
        cash.setBounds(90,150,400,200);
        card.setBounds(580,150,400,200);
        cash.setVisible(false);
        card.setVisible(false);

        
        completeOrder = new JButton("Finish");
        clear = new JButton("Clear");
        newOrder= new JButton("New Order");
        activateButton(newOrder);
        newOrder.setBounds(350,150,400,200);
        newOrder.setVisible(false);
        
        cancelOrder = new JButton("Cancel Order");
        activateButton(cancelOrder);
        cancelOrder.setBounds(350,425,400,200);
        cancelOrder.setVisible(false);

        exit = new JButton("Exit");
        activateButton(exit);
        exit.setBounds(450,50,200,50);
        exit.setVisible(true);

        textArea.append("                  Order Number "+orderAmount + "\n");

        completeOrder.addActionListener(this);
        clear.addActionListener(this);
        completeOrder.setFont(font1);
        clear.setFont(font1);
        completeOrder.setFocusable(false);
        clear.setFocusable(false);
        completeOrder.setBackground(Color.WHITE);
        clear.setBackground(Color.WHITE);
        clear.setOpaque(true);
        completeOrder.setOpaque(true);
        completeOrder.setBounds(350, 705, 180, 70);
        clear.setBounds(550,705,180,70);

        //Setting names for indivudal buttons for the switch statement to catch for cases

        friesbackButton = new JButton("BackFries");
        burgersbackButton = new JButton("BackBurgers");
        drinksbackButton = new JButton("BackDrinks");
        
        burgers = new JButton("Burgers");
        fries = new JButton("Fries");
        drinks = new JButton("Drinks");

        beefBurger = new JButton("Beef Burger-12.99");
        fishBurger = new JButton("Fish Burger-11.99");
        warhawkBurger= new JButton("Warhawk Burger-13.99");
        
        small = new JButton("Small-2.99");
        medium = new JButton("Medium-3.99");
        large = new JButton("Large-4.99");

        pepsi = new JButton("Pepsi-2.99");
        coke = new JButton("Coke-2.99");
        sprite = new JButton("Sprite-2.99");


        //setting each button to an element in each array

        burger[0] = beefBurger;
        burger[1] = fishBurger;
        burger[2] = warhawkBurger;
        burger[3] = burgersbackButton;

        fry[0]= small;
        fry[1]= medium;
        fry[2] = large;
        fry[3] = friesbackButton;

        drink[0]=pepsi;
        drink[1] = coke;
        drink[2]= sprite;
        drink[3] = drinksbackButton;

        allButtons[0]=burgers;
        allButtons[1]=fries;
        allButtons[2]=drinks;

        //button activation
        activateButton(burgersbackButton);
        activateButton(friesbackButton);
        activateButton(drinksbackButton);
        

        activateButton(burger);
        activateButton(fry);
        activateButton(drink);
        
        activateButton(allButtons);

        //setting where each button goes on its individual Panel
        setButtonBounds(beefBurger, fishBurger, warhawkBurger,burgersbackButton);
        setButtonBounds(small, medium, large,friesbackButton);
        setButtonBounds(pepsi, coke, sprite,drinksbackButton);
        setButtonBounds(burgers, fries, drinks);
        //adding the Panels to the frame
        addComponent(burgersPanel, beefBurger, fishBurger, warhawkBurger,burgersbackButton);
        addComponent(drinksPanel, pepsi, coke, sprite,drinksbackButton);
        addComponent(friesPanel, small, medium, large,friesbackButton);
        
        //setting each panels visibility to false so each can be set to true when needed
        setVisible(burgersPanel, drinksPanel, friesPanel);

    }

    public void setButtonBounds(JButton b1, JButton b2, JButton b3){
        b1.setBounds(90,150,400,200);
        b2.setBounds(580,150,400,200);
        b3.setBounds(335,400,400,200);
        
    }
    public void setButtonBounds(JButton b1, JButton b2, JButton b3, JButton b4){
        b1.setBounds(90,150,400,200);
        b2.setBounds(580,150,400,200);
        b3.setBounds(335,400,400,200);
        b4.setBounds(30,50,100,50);
    }

    //button activation method seen earlier in program to give each button functionality
    public void activateButton(JButton[] button){
        for(int i = 0; i<button.length;i++){
            button[i].addActionListener(this);
            button[i].setFont(font);
            button[i].setFocusable(false);
            button[i].setVerticalTextPosition(JButton.BOTTOM);
            button[i].setHorizontalTextPosition(JButton.CENTER);
            button[i].setBackground(Color.LIGHT_GRAY);
            button[i].setOpaque(true);
        }
    }
    //method overiding in order to pick an individual button to activate
    public void activateButton(JButton button){
        button.addActionListener(this);
        button.setFont(font);
        button.setFocusable(false);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setBackground(Color.LIGHT_GRAY);
        button.setOpaque(true);
    
    }

    @Override
    //switch statment for all possiblr buttons selected
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Burgers":
            //Main Panel is already visible and each button can be selected in order to choose the individual burgers, fries or drinks
                exit.setVisible(false);
                mainPanel.setVisible(false);
                burgersPanel.setVisible(true);
                break;
                
            case "Drinks":
                exit.setVisible(false);
                mainPanel.setVisible(false);
                drinksPanel.setVisible(true);
                break;

            case "Fries":
                exit.setVisible(false);
                mainPanel.setVisible(false);
                friesPanel.setVisible(true);
                break;

            case "Beef Burger-12.99":
            //add individual foods to count and display price in JtextArea
                beefCount++;
                endBeef++;
                order.add(12.99);
                textArea.append("Beef Burger................................" + Double.toString(order.peekLast()) + "\n");

                break;

            case "Fish Burger-11.99":
                fishCount++;
                endFish++;
                order.add(11.99);
                textArea.append("Fish Burger................................." + Double.toString(order.peekLast()) + "\n");
    
                break;

            case "Warhawk Burger-13.99":
                WarhawkCount++;
                endWarhawk++;
                order.add(13.99);
                textArea.append("Warhawk Burger........................" + Double.toString(order.peekLast()) + "\n");
                
                break;

            case "Small-2.99":
                smallCount++;
                endSmall++;
                order.add(2.99);
                textArea.append("Small Fry......................................" + Double.toString(order.peekLast()) + "\n");
                
                break;

            case "Medium-3.99":
                MediumCount++;
                endMedium++;
                order.add(3.99);
                textArea.append("Medium Fry................................." + Double.toString(order.peekLast()) + "\n");
               
                break;

            case "Large-4.99":
                largeCount++;
                endLarge++;
                order.add(4.99);
                textArea.append("Large Fry....................................." + Double.toString(order.peekLast()) + "\n");
                
                break; 

            case "Pepsi-2.99":
                pepsiCount++;
                endPepsi++;
                order.add(2.99);
                textArea.append("Pepsi............................................." + Double.toString(order.peekLast()) + "\n");
                
                break;
            
            case "Coke-2.99":
                cokeCount++;
                endCoke++;
                order.add(2.99);
                textArea.append("Coke.............................................." + Double.toString(order.peekLast()) + "\n");
                
                break;

            case "Sprite-2.99":
                spriteCount++;
                endSprite++;
                order.add(2.99);
                textArea.append("Sprite............................................" + Double.toString(order.peek()) + "\n");
                
                break;

            case "BackBurgers":
                burgersPanel.setVisible(false);
                mainPanel.setVisible(true);
                break;

            case "BackFries":
                friesPanel.setVisible(false);
                mainPanel.setVisible(true);
                break;
            
            case "BackDrinks":
                drinksPanel.setVisible(false);
                mainPanel.setVisible(true);
                break;

            case "Clear":
            //emptys the linked list and sets the textarea text to be empty
                textArea.setText("");
                resetCounters();
                order.clear();
                break;

            case "New Order":
            //select another order 
                cancelOrder.setVisible(false);
                completeOrder.setVisible(true);
                clear.setVisible(true);
                newOrder.setVisible(false);
                textArea.setText("");
                mainPanel.setVisible(true);
                exit.setVisible(true);
                orderAmount++;
                textArea.append("             Order Number "+orderAmount + "\n");
                textArea.append(" ");
                break;

            case "Finish":
            //complete current order and find the sum while calculating tax
                completedCounter++;
        
                cash.setVisible(true);
                card.setVisible(true);
                setVisible(friesPanel, drinksPanel, burgersPanel,mainPanel);
                sum = 0;
                
                completeOrder.setVisible(false);
                clear.setVisible(false);
                //getting sum of linked list
                for(int i = 0; i < order.size(); i++){
                    sum= sum + order.get(i);
                }
                //calculating taxable and adding to sum
                 taxable =sum *0.02;
                 taxIncluded = taxable+sum;
                 textArea.setText("");
                 textArea.append("Please Select Cash or Card:\n");
                
                 textArea.append(String.format("%d Beef Burgers-12.99/ea\n%d Fish Burger(s)-11.99/ea\n%d Warhawk Burger(s)-13.99/ea\n%d Small Frie(s)-2.99/ea\n%d Medium Frie(s)-3.99/ea\n%d Large Frie(s)-4.99/ea\n%d Coke(s)-2.99/ea\n%d Pepsi(s)-2.99/ea\n%d Sprite(s)-2.99/ea\n",beefCount,fishCount,WarhawkCount,smallCount,MediumCount,largeCount,cokeCount,pepsiCount,spriteCount));
                 
                 textArea.append("Total Before Tax: "+ String.format("$%.2f\n",sum));
                 textArea.append("Tax: "+ String.format("$%.2f\n",taxable));
                 textArea.append("Total Billing: "+ String.format("$%.2f\n",taxIncluded));

                 break;

            case "Card":
            //immediate payment for usage of card
                textArea.append(String.format("Your card has been charged $%.2f\n Thank you for your business!",taxIncluded));
                total.add(taxIncluded);
                //counting processed order
                ProcessedCounter++;
                order.clear();
                resetCounters();
                cash.setVisible(false);
                card.setVisible(false);
                newOrder.setVisible(true);
                break;

            case "Cash":
            //prompts user for cash amount to be entered and calculates change
                Double cashEntered = 0.0;
                 try {
                    //JOptionPane
                    cashEntered=Double.parseDouble(JOptionPane.showInputDialog(null,"Please Enter how much you will be paying: "));
                    
                 } catch (Exception z) {
                    textArea.append("Invalid Entry\n");
                    break;
                 }

                 double change = cashEntered-taxIncluded;

                 if (cashEntered<taxIncluded){
                    //User amount entered is less than given price so option to cancel is shown
                    textArea.append("Invalid Cash Entered Please try again\nOr cancel your order\n");
                    cancelOrder.setVisible(true);
                    break;
                 } 

                 else{
                    //cash amount is sufficiant and order goes through
                    cancelOrder.setVisible(false);
                    textArea.append(String.format("Your change is $%.2f\nThank you for your business!",change));
                    total.add(taxIncluded);
                    ProcessedCounter++;
                    order.clear();
                    resetCounters();
                    cash.setVisible(false);
                    card.setVisible(false);
                    newOrder.setVisible(true);
                 } 
                 break;

            case "Cancel Order":

                 cancelCounter++;
                 cancelOrder.setVisible(false);
                 textArea.setText("Your Order has been Cancelled");
                 cash.setVisible(false);
                 card.setVisible(false);
                 newOrder.setVisible(true);
                 break;

            case "Exit":
            //total amounts of pricing and foods are counted each time they are ordered and displayed in text file and printed to console when user clicks "Exit"
                 totalBilling = 0;
                 for(int i = 0; i <total.size();i++){
                    totalBilling+=total.get(i);
                 }
                 System.out.printf("Total Orders: %d\nOrders Processed: %d\nOrders Cancelled: %d\nTotal Billing: %.2f\n",completedCounter,ProcessedCounter,cancelCounter,totalBilling);
                 System.out.printf("Beef Burger(s): %d\nFish Burger(s): %d\nWarhawk Burger(s): %d\nSmall Fries(s): %d\nMedium Frie(s):%d\nLarge Frie(s): %d\nCoke(s): %d\nPepsi(s): %d\nSprite(s): %d\n",endBeef,endFish,endWarhawk,endSmall,endMedium,endLarge,endCoke,endPepsi,endSprite);
                 try{
                    output.write(String.format("Total Orders: %d\nOrders Processed: %d\nOrders Cancelled: %d\nTotal Billing: %.2f\n",completedCounter,ProcessedCounter,cancelCounter,totalBilling));
                    output.write(String.format("Beef Burger(s): %d\nFish Burger(s): %d\nWarhawk Burger(s): %d\nSmall Fries(s): %d\nMedium Frie(s):%d\nLarge Frie(s): %d\nCoke(s): %d\nPepsi(s): %d\nSprite(s): %d\n",endBeef,endFish,endWarhawk,endSmall,endMedium,endLarge,endCoke,endPepsi,endSprite));
                    output.close();
                 }
                 catch(IOException z){
                    System.out.println("IO EXCEPTION");
                 }
                 textArea.setText("You may now exit the application");
                 
                 break;
                 
            
            default:
                System.out.println("Invalid");    
            
                
        }
        
    }
    public void setVisible(JPanel b1, JPanel b2, JPanel b3, JPanel b4){
            b1.setVisible(false);
            b2.setVisible(false);
            b3.setVisible(false);
            b4.setVisible(false);
           
        
    }
    public void setVisible(JPanel b1, JPanel b2, JPanel b3){
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
    }
    public void resetCounters(){
        beefCount=0;
        fishCount=0;
        WarhawkCount=0;
        smallCount=0;
        MediumCount=0;
        largeCount=0;
        pepsiCount=0;
        cokeCount=0;
        spriteCount=0;
    }
}



