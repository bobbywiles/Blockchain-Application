import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static FileInputStream recordFile;
    public static Scanner fileReader;
    public static Scanner commandInput;
    public static boolean isFileBlank;
    public static ArrayList<Stakeholder> auctionHouseList = new ArrayList<>();
    public static ArrayList<Artefact> artefactsList = new ArrayList<>();
    public static Boolean useTreaty;

    public static void main(String[] args) throws IOException {
        useTreaty = true;//sets the treaty use to true

        //Creates and Opens the Record File
        isFileBlank = false;
        try{
            recordFile = new FileInputStream("record.csv");
        } catch (FileNotFoundException e1){
            System.out.println("File Not Found - Creating New Record File.");
            isFileBlank = true;
            FileOutputStream newRecordFile = null;
            try{
                newRecordFile = new FileOutputStream("record.csv");
                newRecordFile.close();
            } catch (FileNotFoundException e2){
                System.out.println("Cannot Create New Record File.");
                System.exit(-1);
            } catch (IOException e2){
                System.out.println("Cannot Close New Record File.");
                System.exit(-1);
            }
            try {
                recordFile = new FileInputStream("record.csv");
            } catch (FileNotFoundException e3) {
                System.exit(-1);
            }
        }

        //Readers
        commandInput = new Scanner(System.in);//Command Input
        fileReader = new Scanner(recordFile);//File Input

        //load data from record file
        //Important: MAKE SURE TO DO THIS BEFORE USER INPUTS ANYTHING ELSE!!!
        load();
        update();

        fileReader.close(); //Close fileReader
        recordFile.close(); //First Close reader to write

        GUI gui = new GUI(); //GUI Creation

    }

    public static void update(){

        //will update the houseList to be the most recent house used
        for(int i = 0; i<auctionHouseList.size(); i++){
            for(int j = blockchain.size()-1; j>=0; j--){
                if(auctionHouseList.get(i).getName().equals(blockchain.get(j).getData().getAuctionHouse().getName())){
                    auctionHouseList.set(i, blockchain.get(j).getData().getAuctionHouse());
                    break;
                }
            }
        }
        //find most recent artefact
        for(int i = 0; i<artefactsList.size();i++){
            for(int j = blockchain.size()-1; j>=0; j--){
                if(artefactsList.get(i).getId().equals(blockchain.get(j).getData().getArtefact().getId())){
                    artefactsList.set(i, blockchain.get(j).getData().getArtefact());
                    break;
                }
            }
        }

    }


    public static void save(){
        System.out.println("Saving Records...");
        //records file writer/save
        PrintWriter fileWriter = null;
        try{
            fileWriter = new PrintWriter("record.csv"); //Print Writer Creation
        } catch(FileNotFoundException e1){
            System.out.println("Saving File Error - File Not Found.");
            System.exit(-1);
        }
        for(int i = 0; i<blockchain.size(); i++){
            fileWriter.println(blockchain.get(i));
        }
        fileWriter.close();

        //create new receipt writer/save
        File receipt = new File("receipt.txt");
        PrintWriter receiptWriter = null;
        try {
            receiptWriter = new PrintWriter("receipt.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Receipt File Error.");
            System.exit(-1);
        }
        receiptWriter.println("*****Block Chain*****\n");
        for(int i = 0; i < blockchain.size(); i++){
            receiptWriter.println("*Block " + (i+1) + " Information*\nItem: " + blockchain.get(i).getData().getArtefact().getName() +
                    "\nOwner: " + blockchain.get(i).getData().getArtefact().getOwner().getName() +
                    "\nBuyer: " + blockchain.get(i).getData().getBuyer().getName() +
                    "\nPrice: " + blockchain.get(i).getData().getPrice() +
                    "\nAuction House: " + blockchain.get(i).getData().getAuctionHouse().getName() +
                    "\n" + blockchain.get(i).getData().getAuctionHouse().getName() + " Balance: $" + blockchain.get(i).getData().getAuctionHouse().getBalance() +
                    "\nYear: " + blockchain.get(i).getData().getTimeStamp() +
                    "\n\n*****************************************\n");
        }
        receiptWriter.close();


    }

    public static void load(){
        isFileBlank = !fileReader.hasNextLine();

        if(isFileBlank){
            //need to add auction houses
            //the auction house will be added in main below load call
        } else {
            //Load in from csv file if there is any thing
            while(fileReader.hasNextLine()){
                Block block;
                Artefact artefact;
                Stakeholder seller, buyer, auctionHouse;
                String prevHash, transactionTimeStamp, blockTimeStamp, blockNonce, blockCurrHash, artID, artName, artOrigin, stakeholderID, stakeholderName, stakeholderAddress, stakeholderBalance;
                int price;
                int comma;

                String currentLine = fileReader.nextLine();

                //get block prevHash
                comma = currentLine.indexOf(',');
                prevHash = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                //Load Artefact Object//
                //art id
                comma = currentLine.indexOf(',');
                artID = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //art Name
                comma = currentLine.indexOf(',');
                artName = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //art countryOrigin
                comma = currentLine.indexOf(',');
                artOrigin = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                //Load Seller/Owner for art
                //id
                comma = currentLine.indexOf(',');
                stakeholderID = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //name
                comma = currentLine.indexOf(',');
                stakeholderName = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //address
                comma = currentLine.indexOf(',');
                stakeholderAddress = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //balance
                comma = currentLine.indexOf(',');
                stakeholderBalance = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                seller = new Stakeholder(stakeholderID, stakeholderName, stakeholderAddress, Integer.parseInt(stakeholderBalance));
                //art
                artefact = new Artefact(artID, artName, artOrigin, seller);
                //need to filter the art to list
                if(artefactsList.size() == 0){
                    artefactsList.add(artefact);
                } else {//auction house duplication here
                    boolean isFound = false;
                    for(int i = 0; i < artefactsList.size(); i++){
                        if(artefact.getId().equals(artefactsList.get(i).getId())){
                            isFound = true; //then do not add to list
                            break;
                        }
                    }
                    if(!isFound){
                        artefactsList.add(artefact);
                    }
                }


                //load data timeStamp
                comma = currentLine.indexOf(',');
                transactionTimeStamp = currentLine.substring(0, comma);
                currentLine = currentLine.substring(comma+1);

                //seller load
                //id
                comma = currentLine.indexOf(',');
                currentLine = currentLine.substring(comma+1);
                //name
                comma = currentLine.indexOf(',');
                currentLine = currentLine.substring(comma+1);
                //address
                comma = currentLine.indexOf(',');
                currentLine = currentLine.substring(comma+1);
                //balance
                comma = currentLine.indexOf(',');
                currentLine = currentLine.substring(comma+1);

                //buyer load
                //id
                comma = currentLine.indexOf(',');
                stakeholderID = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //name
                comma = currentLine.indexOf(',');
                stakeholderName = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //address
                comma = currentLine.indexOf(',');
                stakeholderAddress = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //balance
                comma = currentLine.indexOf(',');
                stakeholderBalance = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                buyer = new Stakeholder(stakeholderID, stakeholderName, stakeholderAddress, Integer.parseInt(stakeholderBalance));

                //load auction house
                //id
                comma = currentLine.indexOf(',');
                stakeholderID = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //name
                comma = currentLine.indexOf(',');
                stakeholderName = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //address
                comma = currentLine.indexOf(',');
                stakeholderAddress = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);
                //balance
                comma = currentLine.indexOf(',');
                stakeholderBalance = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                auctionHouse = new Stakeholder(stakeholderID, stakeholderName, stakeholderAddress, Integer.parseInt(stakeholderBalance));
                //need to filter the auction houses to list
                if(auctionHouseList.size() == 0){
                    auctionHouseList.add(auctionHouse);
                } else {//auction house duplication here
                    boolean isFound = false;
                    for(int i = 0; i < auctionHouseList.size(); i++){
                        if(auctionHouse.getId().equals(auctionHouseList.get(i).getId())){
                            isFound = true; //then do not add to list
                            break;
                        }
                    }
                    if(!isFound){
                        auctionHouseList.add(auctionHouse);
                    }
                }


                //load price
                comma = currentLine.indexOf(',');
                price = Integer.parseInt(currentLine.substring(0,comma));
                currentLine = currentLine.substring(comma+1);

                //load blockTimestamp
                comma = currentLine.indexOf(',');
                blockTimeStamp = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                //load nonce
                comma = currentLine.indexOf(',');
                blockNonce = currentLine.substring(0,comma);
                currentLine = currentLine.substring(comma+1);

                //load currHash
                blockCurrHash = currentLine;

                //push everything loaded into correct arrays
                Transaction data = new Transaction(artefact, Integer.parseInt(transactionTimeStamp), seller, buyer, auctionHouse, price);
                block = new Block(data, prevHash, Integer.parseInt(blockTimeStamp), Integer.parseInt(blockNonce), blockCurrHash);
                blockchain.add(block);
            }
        }
    }

    public static void addHouse(String ID, String Name, String Address, String stringBalance){

        int Balance = 0;
        try{
            Balance = Integer.parseInt(stringBalance);
        } catch (NumberFormatException e1){
            System.out.println("Balance Parse Error.");
            System.exit(-1);
        }

        Stakeholder auctionHouse = new Stakeholder(ID, Name, Address, Balance);

        //Check the list for any matches
        boolean isFound = false;
        for(int i = 0; i < auctionHouseList.size(); i++){
            if(auctionHouse.getId().equals(auctionHouseList.get(i).getId())){
                isFound = true;
                break;
            }
        }
        if(!isFound){
            auctionHouseList.add(auctionHouse);

            System.out.println("House Added Successfully.");
        } else {
            System.out.println("House Not Added Due to House already existing.");
        }
    }

    public static void addArtefact(ArrayList<String> artItems){
        update();

        //make owner
        Stakeholder owner = new Stakeholder(artItems.get(1), artItems.get(0), artItems.get(2), 0);

        Artefact artefact = new Artefact(artItems.get(4), artItems.get(5), artItems.get(6), owner);

        boolean isFound = false;
        for(int i = 0; i<artefactsList.size();i++){
            if(artefact.getId().equals(artefactsList.get(i).getId())){
                isFound = true;
                break;
            }
        }
        if(isFound){
            //do nothing
            System.out.println("Artefact Not Added Already Exists.");
        } else {
            System.out.println("Artefact Added to List.");
            artefactsList.add(artefact);
        }
        update();
    }

    public static void newSellInput(ArrayList<String> list){
        update();

        Stakeholder seller;
        Stakeholder buyer;
        Artefact artefact;
        Stakeholder auctionHouse;
        String auctionHouseName;
        int choice = 0;
        int timeStamp = 0;
        int price = 0;

        //seller
        String ID = list.get(0);
        String Name = list.get(1);
        String Address = list.get(2);
        int balance = 0;
        try{
            balance = Integer.parseInt(list.get(3));
        } catch (NumberFormatException e1){
            System.out.println("number input error for seller.");
            System.exit(-1);
        }
        seller = new Stakeholder(ID, Name, Address, balance);

        //buyer
        ID = list.get(4);
        Name = list.get(5);
        Address = list.get(6);
        balance = 0;
        try{
            balance = Integer.parseInt(list.get(7));
        } catch (NumberFormatException e1){
            System.out.println("number input error for buyer.");
            System.exit(-1);
        }
        buyer = new Stakeholder(ID, Name, Address, balance);

        //artefact
        ID = list.get(8);
        Name = list.get(9);
        String countryOrigin = list.get(10);
        artefact = new Artefact(ID, Name, countryOrigin, seller);

        //add art to list, Check the list for any matches
        boolean isFound = false;
        for(int i = 0; i < artefactsList.size(); i++){
            if(artefact.getId().equals(artefactsList.get(i).getId())){
                isFound = true;
                break;
            }
        }
        if(!isFound){
            artefactsList.add(artefact); //add to list if no match
        }


        //search for a_house
        auctionHouseName = list.get(11);
        for(int i = 0; i < auctionHouseList.size(); i++){
            if(auctionHouseName.equals(auctionHouseList.get(i).getName())){
                choice = i;
                break;
            }
        }
        auctionHouse = new Stakeholder(auctionHouseList.get(choice).getId(), auctionHouseList.get(choice).getName(),
                auctionHouseList.get(choice).getAddress(), auctionHouseList.get(choice).getBalance());


        //transaction price
        try{
            price = Integer.parseInt(list.get(12));
        } catch (NumberFormatException e1){
            System.out.println("Price was not parsed correctly.");
            System.exit(-1);
        }

        //transaction timeStamp/date
        try{
            timeStamp = Integer.parseInt(list.get(13));
        } catch (NumberFormatException e1){
            System.out.println("Timestamp was not parsed correctly.");
            System.exit(-1);
        }



        //Create the Transaction object
        //Transaction transaction = new Transaction(artefact, timeStamp, seller, buyer, auctionHouseList.get(choice), price); //This one is bad!
        Transaction transaction = new Transaction(artefact, timeStamp, seller, buyer, auctionHouse, price); //This one is good!

        //Sends transaction data to addBlock where it will create the block and add it to the chain
        addBlock(transaction);
    }

    //get owner/seller info
    public static Stakeholder retrieveInfo(String artName){
        update();
        int num =0;
        for(int i = 0; i< artefactsList.size();i++){
            if(artName.equals(artefactsList.get(i).getName())){
                num = i;
                break;
            }
        }
        return artefactsList.get(num).getOwner();
    }

    //sell page 2
    public static void newSell2(ArrayList<String> input){
        update();

        Stakeholder seller, buyer, auctionHouse;
        Artefact artefact;
        String auctionHouseName, artefactName;
        int price = 0;
        int timestamp = 0;
        int choice = 0;

        //seller
        String ID = input.get(0);
        String Name = input.get(1);
        String Address = input.get(2);
        int balance = 0;
        try{
            balance = Integer.parseInt(input.get(3));
        } catch (NumberFormatException e1){
            System.out.println("number input error for seller.");
            System.exit(-1);
        }
        seller = new Stakeholder(ID, Name, Address, balance);

        //buyer
        ID = input.get(4);
        Name = input.get(5);
        Address = input.get(6);
        balance = 0;
        try{
            balance = Integer.parseInt(input.get(7));
        } catch (NumberFormatException e1){
            System.out.println("number input error for buyer.");
            System.exit(-1);
        }
        buyer = new Stakeholder(ID, Name, Address, balance);

        //price
        try {
            price = Integer.parseInt(input.get(8));
        } catch (NumberFormatException e1){
            System.out.println("number input error for price.");
            System.exit(-1);
        }
        //date
        try {
            timestamp = Integer.parseInt(input.get(9));
        } catch (NumberFormatException e1){
            System.out.println("number input error for date.");
            System.exit(-1);
        }

        //art
        artefactName = input.get(10);
        for(int i =0; i<artefactsList.size(); i++){
            if(artefactName.equals((artefactsList.get(i).getName()))){
                choice = i;
                break;
            }
        }
        artefact = new Artefact(artefactsList.get(choice).getId(), artefactsList.get(choice).getName(),
                artefactsList.get(choice).getCountryOrigin(), seller);

        //auctionHouse
        auctionHouseName = input.get(11);
        for(int i = 0; i < auctionHouseList.size(); i++){
            if(auctionHouseName.equals(auctionHouseList.get(i).getName())){
                choice = i;
                break;
            }
        }
        auctionHouse = new Stakeholder(auctionHouseList.get(choice).getId(), auctionHouseList.get(choice).getName(),
                auctionHouseList.get(choice).getAddress(), auctionHouseList.get(choice).getBalance());

        //transaction
        Transaction transaction = new Transaction(artefact, timestamp, seller, buyer, auctionHouse, price);

        //addBlock
        addBlock(transaction);


    }



    public static void addBlock(Transaction data){
        int prefix = 4; //We want our hash to start with four zeroes, can be changed here
        String prefixString = new String(new char[prefix]).replace('\0','0'); //This is what is checked against to see if there is four 0's in the hash mined

        int spot = blockchain.size();

        Block block;
        if(blockchain.size()==0){
            //Only for first block in chain
            block = new Block(data, "0", data.getTimeStamp());
            System.out.println("\nBlock " + (spot+1) + " Random TMP Hash: " + block.getCurrentHash());
            //MineBlock
            block.mineBlock(prefix, prefixString);
            if(block.getCurrentHash().substring(0,prefix).equals(prefixString) && block.verify_Blockchain(blockchain)){
                blockchain.add(block);
                System.out.println("Block " + (spot+1) + " Added.");
            }
        } else {
            block = new Block(data, blockchain.get(spot-1).getCurrentHash(), data.getTimeStamp());
            System.out.println("\nBlock " + (spot+1) + " Random TMP Hash: " + block.getCurrentHash());
            //MineBlock
            block.mineBlock(prefix, prefixString);
            if(block.getCurrentHash().substring(0, prefix).equals(prefixString) && block.verify_Blockchain(blockchain)){
                blockchain.add(block);
                System.out.println("Block " + (spot+1) + " Added.");
            }
        }
        update();
    }
}
