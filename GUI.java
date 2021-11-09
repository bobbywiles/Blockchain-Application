import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
//date jspinner thing
public class GUI extends JFrame implements ActionListener, WindowListener {
    private JFrame window, popup;
    private Popup p;
    private JPanel startPanel, addHousePanel, sellPanel, settingPanel,errorMessage, newSellPanel, addArtefact;
    private JTextField IAH_ID, IAH_NAME, IAH_ADDRESS, IAH_BLNCE,
            TFSHN, TFSHID,TFSHA, TFSHB, TFBN,TFBID,TFBA,TFBB,
            TFAID,TFAN,TFAC, TFTP, TFTD,
            artOwnerName, artOwnerID, artOwnerAddress, artOwnerBalance,
            artID, artName, artOrigin;
    private JLabel label1, label, AH_ID, AH_NAME, AH_ADDRESS, AH_BLNCE, sizeMain;
    //sell panel 2
    private JLabel B_ID, B_NAME, B_ADDRESS, B_BLNCE, S_ID, S_NAME, S_ADDRESS, S_BLNCE, PRICE, DATE;
    private JTextField B_IDT, B_NAMET, B_ADDRESST, B_BLNCET, S_IDT, S_NAMET, S_ADDRESST, S_BLNCET,PRICET,DATET;
    private JButton sub, clear;
    //panel 2
    private JButton button1, submit, AH_SUB,AH_DONE, POPUP_DONE, artSubmit;
    private JComboBox dropbox, auctionDropbox, artDropbox;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6;
    private PopupFactory pop;
    private JRadioButton radioButton1, radioButton2;
    private ButtonGroup buttonGroup;


    public GUI(){
        //window
        window = new JFrame("BlockChain App");
        window.setSize(500,400);
        window.setResizable(false);

        //startPanel****************************************************************************************************
        startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
        startPanel.setBackground(Color.LIGHT_GRAY);
        label1 = new JLabel("Welcome to BlockChain App");
        startPanel.add(label1);
        sizeMain = new JLabel("Block Chain Size: " + Main.blockchain.size());
        startPanel.add(sizeMain);
        ImageIcon img = new ImageIcon("iconImage.png");
        JLabel image = new JLabel(img);
        startPanel.add(image);
        startPanel.setVisible(true);


        //addHouse panel************************************************************************************************
        addHousePanel = new JPanel();
        addHousePanel.setLayout(new GridLayout(5,2));
        addHousePanel.setBackground(Color.LIGHT_GRAY);
        //add items to panel*********
        //labels
        AH_ID = new JLabel("Auction House ID");
        AH_NAME = new JLabel("Auction House Name");
        AH_ADDRESS = new JLabel("Auction House Address");
        AH_BLNCE = new JLabel("Auction House Balance");
        //textFields
        IAH_ID = new JFormattedTextField();
        IAH_NAME = new JFormattedTextField();
        IAH_ADDRESS = new JFormattedTextField();
        IAH_BLNCE = new JFormattedTextField();
        //add the text fields and labels
        addHousePanel.add(AH_ID);
        addHousePanel.add(IAH_ID);
        addHousePanel.add(AH_NAME);
        addHousePanel.add(IAH_NAME);
        addHousePanel.add(AH_ADDRESS);
        addHousePanel.add(IAH_ADDRESS);
        addHousePanel.add(AH_BLNCE);
        addHousePanel.add(IAH_BLNCE);
        //Buttons
        AH_SUB = new JButton("Add");
        AH_DONE = new JButton("Done");
        AH_SUB.addActionListener(this);
        AH_DONE.addActionListener(this);
        addHousePanel.add(AH_SUB);
        addHousePanel.add(AH_DONE);
        //make visible
        addHousePanel.setVisible(true);


        //new sell panel************************************************************************************************
        sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(0,2));
        sellPanel.setBackground(Color.LIGHT_GRAY);
        //stakeholder name
        label = new JLabel("Seller Name: ");
        sellPanel.add(label);
        TFSHN = new JTextField("Name Here");
        sellPanel.add(TFSHN);
        //stakeholder id
        label = new JLabel("Seller ID: ");
        sellPanel.add(label);
        TFSHID = new JTextField("ID Here", 1);
        sellPanel.add(TFSHID);
        //stakeholder address
        label = new JLabel("Seller Address: ");
        sellPanel.add(label);
        TFSHA = new JTextField("Address Here", 1);
        sellPanel.add(TFSHA);
        //stakeholder balance
        label = new JLabel("Seller Balance (Integers Only): ");
        sellPanel.add(label);
        TFSHB = new JTextField("Balance Here", 1);
        sellPanel.add(TFSHB);

        //break
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);

        //buyer id
        label = new JLabel("Buyer Name: ");
        sellPanel.add(label);
        TFBN = new JTextField("Buyer Name", 1);
        sellPanel.add(TFBN);
        label = new JLabel("Buyer ID: ");
        sellPanel.add(label);
        TFBID = new JTextField("ID Here", 1);
        sellPanel.add(TFBID);
        label = new JLabel("Buyer Address: ");
        sellPanel.add(label);
        TFBA = new JTextField("Address Here", 1);
        sellPanel.add(TFBA);
        label = new JLabel("Buyer Balance (Integers Only): ");
        sellPanel.add(label);
        TFBB = new JTextField("Balance Here", 1);
        sellPanel.add(TFBB);

        //break
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);

        //artefact
        label = new JLabel("Artifact ID: ");
        sellPanel.add(label);
        TFAID = new JTextField("ID Here", 1);
        sellPanel.add(TFAID);
        label = new JLabel("Artifact name: ");
        sellPanel.add(label);
        TFAN = new JTextField("Name Here", 1);
        sellPanel.add(TFAN);
        label = new JLabel("Artifact Country of Origin:");
        sellPanel.add(label);
        TFAC = new JTextField("Country Here", 1);
        sellPanel.add(TFAC);

        //break
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);

        //transaction
        label = new JLabel("Transaction Price (Integers Only): ");
        sellPanel.add(label);
        TFTP = new JTextField("Price Here", 1);
        sellPanel.add(TFTP);
        label = new JLabel("Date of Transaction (Integers Only):");
        sellPanel.add(label);
        TFTD = new JTextField("Date Here", 1);
        sellPanel.add(TFTD);

        //break
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);
        label = new JLabel("-------------------------------------------------------");
        sellPanel.add(label);

        //addDropBox
        dropbox = new JComboBox();
        for(int i=0;i<Main.auctionHouseList.size();i++){
            dropbox.addItem(Main.auctionHouseList.get(i).getName());
        }
        sellPanel.add(dropbox);

        //submit button
        submit = new JButton("Submit");
        submit.addActionListener(this);
        sellPanel.add(submit);
        //***********************
        sellPanel.setVisible(true);


        //Create setting panel********************************************************************************************
        settingPanel = new JPanel();
        settingPanel.setLayout(new FlowLayout());
        settingPanel.setBackground(Color.LIGHT_GRAY);
        label = new JLabel("Would you like to use treaty's 2001 year requirement? ");
        settingPanel.add(label);
        radioButton1 = new JRadioButton("Yes");
        radioButton1.addActionListener(this);
        radioButton1.setSelected(true);
        radioButton2 = new JRadioButton("No");
        radioButton2.addActionListener(this);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        settingPanel.add(radioButton1);
        settingPanel.add(radioButton2);

        //artefact panel************************************************************************************************
        addArtefact = new JPanel();
        addArtefact.setLayout(new GridLayout(0,2));
        addArtefact.setBackground(Color.LIGHT_GRAY);
        //items
        //owner
        label = new JLabel("Owner Name: ");
        addArtefact.add(label);
        artOwnerName = new JTextField("Name");
        addArtefact.add(artOwnerName);
        label = new JLabel("Owner ID: ");
        addArtefact.add(label);
        artOwnerID = new JTextField("ID");
        addArtefact.add(artOwnerID);
        label = new JLabel("Owner Address: ");
        addArtefact.add(label);
        artOwnerAddress = new JTextField("Address");
        addArtefact.add(artOwnerAddress);
        //auto set balance to 0
        //art
        label = new JLabel("Artefact ID: ");
        addArtefact.add(label);
        artID = new JTextField("ID");
        addArtefact.add(artID);
        label = new JLabel("Artefact Name: ");
        addArtefact.add(label);
        artName = new JTextField("Name");
        addArtefact.add(artName);
        label = new JLabel("Artefact Country of Origin: ");
        addArtefact.add(label);
        artOrigin = new JTextField("Country Name");
        addArtefact.add(artOrigin);
        //submit button
        artSubmit = new JButton("Submit");
        artSubmit.addActionListener(this);
        addArtefact.add(artSubmit);




        //sell Panel 2**************************************************************************************************

        newSellPanel = new JPanel();
        newSellPanel.setLayout(new GridLayout(0,2));
        newSellPanel.setBackground(Color.LIGHT_GRAY);



        //artifact Menu
        artDropbox = new JComboBox();
        for(int i =0; i<Main.artefactsList.size()+1; i++){
            if(i==0){
                artDropbox.addItem("Chose An Artefact");
            } else {
                artDropbox.addItem(Main.artefactsList.get(i-1).getName());
            }

        }
        artDropbox.addActionListener(this);
        newSellPanel.add(artDropbox);
        //auctionHouse menu
        auctionDropbox = new JComboBox();
        for(int i =0;i<Main.auctionHouseList.size();i++){
            auctionDropbox.addItem(Main.auctionHouseList.get(i).getName());
        }
        newSellPanel.add(auctionDropbox);
        //S_ID, S_NAME, S_ADDRESS, S_BLNCE
        S_ID = new JLabel("Seller ID:");
        S_NAME = new JLabel("Seller Name:");
        S_ADDRESS = new JLabel("Seller Address:");
        S_BLNCE = new JLabel("Seller Balance:");
        S_IDT = new JTextField();
        S_NAMET = new JTextField();
        S_ADDRESST = new JTextField();
        S_BLNCET = new JTextField();
        B_ID = new JLabel("Buyer ID:");
        B_NAME = new JLabel("Buyer Name:");
        B_ADDRESS = new JLabel("Buyer Address:");
        B_BLNCE = new JLabel("Buyer Balance:");
        B_IDT = new JTextField();
        B_NAMET = new JTextField();
        B_ADDRESST = new JTextField();
        B_BLNCET = new JTextField();
        PRICE = new JLabel("Transaction Price");
        DATE = new JLabel("Date of Transaction");
        PRICET = new JTextField();
        DATET = new JTextField();

        newSellPanel.add(S_ID);
        newSellPanel.add(S_IDT);
        newSellPanel.add(S_NAME);
        newSellPanel.add(S_NAMET);
        newSellPanel.add(S_ADDRESS);
        newSellPanel.add(S_ADDRESST);
        newSellPanel.add(S_BLNCE);
        newSellPanel.add(S_BLNCET);
        newSellPanel.add(B_ID);
        newSellPanel.add(B_IDT);
        newSellPanel.add(B_NAME);
        newSellPanel.add(B_NAMET);
        newSellPanel.add(B_ADDRESS);
        newSellPanel.add(B_ADDRESST);
        newSellPanel.add(B_BLNCE);
        newSellPanel.add(B_BLNCET);
        newSellPanel.add(PRICE);
        newSellPanel.add(PRICET);
        newSellPanel.add(DATE);
        newSellPanel.add(DATET);

        sub = new JButton("Submit");
        clear = new JButton("Clear");
        newSellPanel.add(clear);
        newSellPanel.add(sub);
        clear.addActionListener(this);
        sub.addActionListener(this);


        //**************************************************************************************************


        //Create Menu Bar
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem1 = new JMenuItem("Home");
        menuItem1.addActionListener(this);
        menuItem2 = new JMenuItem("Add Auction House");
        menuItem2.addActionListener(this);
        menuItem3 = new JMenuItem("Create New Sell OLD");
        menuItem3.addActionListener(this);
        menuItem4 = new JMenuItem("Create New Sell");
        menuItem4.addActionListener(this);
        menuItem5 = new JMenuItem("Settings");
        menuItem5.addActionListener(this);
        menuItem6 = new JMenuItem("Add Artefact");
        menuItem6.addActionListener(this);
        menu.add(menuItem1); //home
        menu.add(menuItem2); //add house
        menu.add(menuItem6); //add art
        //menu.add(menuItem3); //sell 1
        menu.add(menuItem4); //sell 2
        menu.add(menuItem5); //settings
        menuBar.add(menu);
        menuBar.setVisible(true);

        //Add Start panel to window
        window.add(startPanel);
        window.setSize(500,400);
        window.setLocationRelativeTo(null);
        window.addWindowListener(this);
        window.setJMenuBar(menuBar);

        //error popup frame
        popup = new JFrame("Error");
        errorMessage = new JPanel();
        JLabel message = new JLabel("Balance must be an Integer. Please Re-enter");
        errorMessage.add(message);
        pop = new PopupFactory();
        POPUP_DONE = new JButton("OK");
        errorMessage.add(POPUP_DONE);


        p = pop.getPopup(popup,errorMessage,(int)(window.getX()*1.2),(int)(window.getY()*1.4));

        //Icon Image
        Image icon = Toolkit.getDefaultToolkit().getImage("iconImage.png");
        window.setIconImage(icon);

        //Open window to user
        window.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if(action.equals("Home")){
            Main.update();
            sizeMain.setText("Block Chain Size: " + Main.blockchain.size());
            addHousePanel.setVisible(false);
            sellPanel.setVisible(false);
            newSellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addArtefact.setVisible(false);
            startPanel.setVisible(true);
            window.add(startPanel);
        }
        if(action.equals("Add Auction House")){
            Main.update();
            startPanel.setVisible(false);
            sellPanel.setVisible(false);
            newSellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addArtefact.setVisible(false);
            addHousePanel.setVisible(true);
            window.add(addHousePanel);
        }
        if(action.equals("Add Artefact")){
            Main.update();
            startPanel.setVisible(false);
            sellPanel.setVisible(false);
            newSellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addHousePanel.setVisible(false);
            addArtefact.setVisible(true);
            window.add(addArtefact);
        }
        if(action.equals("Create New Sell OLD")){
            Main.update();
            dropbox.removeAllItems();
            for(int i=0;i<Main.auctionHouseList.size();i++){
                dropbox.addItem(Main.auctionHouseList.get(i).getName());
            }

            startPanel.setVisible(false);
            addHousePanel.setVisible(false);
            newSellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addArtefact.setVisible(false);
            sellPanel.setVisible(true);
            window.add(sellPanel);
        }
        if(action.equals("Create New Sell")){
            Main.update();
            auctionDropbox.removeAllItems();
            for(int i=0;i<Main.auctionHouseList.size();i++){
                auctionDropbox.addItem(Main.auctionHouseList.get(i).getName());
            }
            artDropbox.removeAllItems();
            for(int i =0; i<Main.artefactsList.size()+1; i++){
                if(i==0){
                    artDropbox.addItem("Chose An Artefact");
                } else {
                    artDropbox.addItem(Main.artefactsList.get(i-1).getName());
                }

            }
            startPanel.setVisible(false);
            addHousePanel.setVisible(false);
            sellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addArtefact.setVisible(false);
            newSellPanel.setVisible(true);
            window.add(newSellPanel);
        }
        if(action.equals("Settings")){
            Main.update();
            startPanel.setVisible(false);
            addHousePanel.setVisible(false);
            sellPanel.setVisible(false);
            newSellPanel.setVisible(false);
            addArtefact.setVisible(false);
            settingPanel.setVisible(true);
            window.add(settingPanel);
        }
        if(action.equals("Yes")){
            Main.useTreaty = true;
        }
        if(action.equals("No")){
            Main.useTreaty = false;
        }
        //sell 1
        if(event.getSource().equals(submit)){
            //TFSHN, TFSHID,TFSHA, TFSHB, TFBN,TFBID,TFBA,TFBB,TFAID,TFAN,TFAC, TFTP, TFTD
            //Artefact(String id, String name, String countryOrigin, Stakeholder owner)
            //Stakeholder(String id, String name, String address, int balance)
            //Transaction(Artefact artefact, int timeStamp, Stakeholder seller, Stakeholder buyer, Stakeholder auctionHouse, int price)
            ArrayList<String> everything = new ArrayList<>();
            String errorLocation = "";
            POPUP_DONE.addActionListener(this);

            try{
                //test for integer values
                parseInt(TFBB.getText()); //buyer balance
                parseInt(TFSHB.getText()); //seller balance
                parseInt(TFTD.getText()); //transaction price
                parseInt(TFTP.getText()); //transaction date
                everything.add(TFSHID.getText()); //add seller id
                everything.add(TFSHN.getText()); //seller name
                everything.add(TFSHA.getText()); //seller address
                everything.add(TFSHB.getText()); //seller balance
                everything.add(TFBID.getText()); //buyer id
                everything.add(TFBN.getText()); //buyer name
                everything.add(TFBA.getText()); //buyer address
                everything.add(TFBB.getText()); //buyer balance
                everything.add(TFAID.getText()); //artifact id
                everything.add(TFAN.getText()); //artifact name
                everything.add(TFAC.getText()); //artifact country of origin
                everything.add(dropbox.getSelectedItem().toString());
                everything.add((TFTP.getText())); //transaction price
                everything.add(TFTD.getText()); //transaction date
                //send everything through this
                Main.newSellInput(everything);//Call to Main
                TFSHN.setText("");
                TFSHID.setText("");
                TFSHA.setText("");
                TFSHB.setText("");
                TFBN.setText("");
                TFBID.setText("");
                TFBA.setText("");
                TFBB.setText("");
                TFAID.setText("");
                TFAN.setText("");
                TFAC.setText("");
                TFTP.setText("");
                TFTD.setText("");
            } catch(NumberFormatException e){
                p.show();
            }
        }
        //add art submit
        if(event.getSource().equals(artSubmit)){
            ArrayList<String> artItems = new ArrayList<>();
            artItems.add(artOwnerName.getText()); //0
            artItems.add(artOwnerID.getText()); //1
            artItems.add(artOwnerAddress.getText()); //2
            artItems.add("0"); //3
            artItems.add(artID.getText()); //4
            artItems.add(artName.getText()); //5
            artItems.add(artOrigin.getText()); //6

            Main.addArtefact(artItems);

            artOwnerName.setText("");
            artOwnerID.setText("");
            artOwnerAddress.setText("");
            artID.setText("");
            artName.setText("");
            artOrigin.setText("");

        }

        //close popup
        if (event.getSource().equals(POPUP_DONE)){
            p.hide(); //hide popup window
            p = pop.getPopup(popup,errorMessage,(int)(window.getX()*1.2),(int)(window.getY()*1.4)); //create it again
        }
        //AuctionHouse Submit
        if(event.getSource().equals(AH_SUB)){
            POPUP_DONE.addActionListener(this);

            //test for integer balance. Display error if it cannot be converted.
            try{
                parseInt(IAH_BLNCE.getText());
                Main.addHouse(IAH_ID.getText(), IAH_NAME.getText(), IAH_ADDRESS.getText(), IAH_BLNCE.getText());
                IAH_ID.setText("");
                IAH_NAME.setText("");
                IAH_ADDRESS.setText("");
                IAH_BLNCE.setText("");
            } catch (NumberFormatException e){
                p.show();
            }

        }
        if(event.getSource().equals(AH_DONE)){
            Main.update();
            addHousePanel.setVisible(false);
            sellPanel.setVisible(false);
            newSellPanel.setVisible(false);
            settingPanel.setVisible(false);
            addArtefact.setVisible(false);
            startPanel.setVisible(true);
            window.add(startPanel);
        }
        if(event.getSource().equals(artDropbox)){
            //System.out.println(artDropbox.getSelectedItem());
            if(artDropbox.getSelectedItem() == null || artDropbox.getSelectedItem().toString().equals("Chose An Artefact")){
                //do nothing
            } else {
                Stakeholder owner = Main.retrieveInfo(artDropbox.getSelectedItem().toString());
                S_ADDRESST.setText(owner.getAddress());
                S_BLNCET.setText(Integer.toString(owner.getBalance()));
                S_IDT.setText(owner.getId());
                S_NAMET.setText(owner.getName());

                //set text here
                //System.out.println("TESTS: " +owner.getName());


            }
        }


        if(event.getSource().equals(sub)){
            ArrayList<String> everything2 = new ArrayList<>();
            String errorLocation = "";
            POPUP_DONE.addActionListener(this);

            try{
                //test for integer values
                parseInt(S_BLNCET.getText()); //buyer balance
                parseInt(B_BLNCET.getText()); //seller balance
                parseInt(DATET.getText()); //transaction price
                parseInt(PRICET.getText()); //transaction date
                everything2.add(S_IDT.getText()); //add seller id
                everything2.add(S_NAMET.getText()); //seller name
                everything2.add(S_ADDRESST.getText()); //seller address
                everything2.add(S_BLNCET.getText()); //seller balance
                everything2.add(B_IDT.getText()); //buyer id
                everything2.add(B_NAMET.getText()); //buyer name
                everything2.add(B_ADDRESST.getText()); //buyer address
                everything2.add(B_BLNCET.getText()); //buyer balance
                everything2.add(PRICET.getText()); //artifact id
                everything2.add(DATET.getText()); //artifact name
                everything2.add(artDropbox.getSelectedItem().toString());
                everything2.add(auctionDropbox.getSelectedItem().toString());
                //SENDS: sellerid, sellername, selleraddress, sellerblnce, buyerid, buyername, buyeraddress, buyerbalance, price, date, artifact name, auction house name;
                //send everything through this
                Main.newSell2(everything2);//Call to Main
                S_IDT.setText("");
                S_NAMET.setText("");
                S_ADDRESST.setText("");
                S_BLNCET.setText("");
                B_IDT.setText("");
                B_NAMET.setText("");
                B_ADDRESST.setText("");
                B_BLNCET.setText("");
                PRICET.setText("");
                DATET.setText("");
            } catch(NumberFormatException e){
                p.show();
            }

        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window is closing by user.");
        Main.save();
        System.exit(-1);
    }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {

    }
    @Override
    public void windowDeiconified(WindowEvent e) {

    }
    @Override
    public void windowActivated(WindowEvent e) {

    }
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}