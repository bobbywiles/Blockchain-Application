import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    private String previousHash;
    private Transaction data;
    private int timeStamp;
    private int nonce;
    private String currentHash;

    //Constructor Blank
    public Block(){
    }

    //Constructor for load
    public Block(Transaction data, String previousHash, int timeStamp, int nonce, String currentHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
        this.currentHash = currentHash;
    }

    //Constructor With Requirements
    public Block(Transaction data, String previousHash, int timeStamp){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.currentHash = calculateBlockHash(); //Creates a hash but is not a valid four 0's hash
    }

    //Calculate Block Hash Method
    public String calculateBlockHash(){
        String dataToHash = previousHash + Integer.toString(timeStamp) + Integer.toString(nonce) + data.toString();
        MessageDigest digest = null;
        byte[] bytes = null;
        try{
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex){
            System.out.println("The encoding is not supported");
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes){
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }




    //mineBlock method
    public void mineBlock(int prefix, String prefixString){


        if(TreatySC(this.data)){
            //Loops until correct hash is mined
            while(!currentHash.substring(0, prefix).equals(prefixString)){
                nonce++;
                currentHash = calculateBlockHash();
            }
        } else {
            //Abort since block cant happen
            System.out.println("Block Not Mined Due to Treaty Failure.");
        }
    }

    //TreatySC method
    public boolean TreatySC(Transaction data){
        if(Main.useTreaty){
            //must have 2 transactions (blocks) after timestamp of 2001
            if(retrieveProvenance(data.getArtefact().getId())){
                //continue and check the other requirements

                if(data.getBuyer().getBalance() >= data.getPrice()){
                    //continue and check the other requirements

                    int auctionHouseCut = 0;
                    int sellerCut = 0;
                    int countryOrigin = 0;

                    auctionHouseCut = (int) (data.getPrice()*0.1);
                    sellerCut = (int) (data.getPrice()*0.7);
                    countryOrigin = (int) (data.getPrice()*0.2);


                    data.getAuctionHouse().setBalance(data.getAuctionHouse().getBalance()+auctionHouseCut);
                    data.getSeller().setBalance(data.getSeller().getBalance()+sellerCut);
                    data.getBuyer().setBalance(data.getBuyer().getBalance()-data.getPrice());

                    return true;

                } else {
                    System.out.println("Error: Buyer Does Not Have Enough Money.");
                    return false;
                }

            } else {
                //end and stop transaction
                System.out.println("Error: Not enough transactions after 2001.");
                return false;
            }
        } else {
            //will run if treaty setting is off
            if(data.getBuyer().getBalance() >= data.getPrice()){
                int auctionHouseCut = 0;
                int sellerCut = 0;
                int countryOrigin = 0;

                auctionHouseCut = (int) (data.getPrice()*0.1);
                sellerCut = (int) (data.getPrice()*0.7);
                countryOrigin = (int) (data.getPrice()*0.2);


                data.getAuctionHouse().setBalance(data.getAuctionHouse().getBalance()+auctionHouseCut);
                data.getSeller().setBalance(data.getSeller().getBalance()+sellerCut);
                data.getBuyer().setBalance(data.getBuyer().getBalance()-data.getPrice());

                return true;
            } else {
                System.out.println("Error: Buyer Does Not Have Enough Money.");
                return false;
            }
        }
    }


    //retrieve methods
    //retrieveProvenance method
    public static boolean retrieveProvenance(String id){
        int counter = 0;

        for(int i = 0; i < Main.blockchain.size(); i++){
            if((Main.blockchain.get(i).getData().getArtefact().getId().equals(id)) && (Main.blockchain.get(i).getTimeStamp() > 2001)){
                counter++;
            }
        }

        if(counter >= 2){
            return true;
        } else {
            return false;
        }
    }

    //retrieveProvenance method 2
    public static boolean retrieveProvenance(String id, int timeStamp){
        int counter = 0;

        for(int i = 0; i<Main.blockchain.size(); i++){
            if((Main.blockchain.get(i).getData().getArtefact().getId().equals(id)) && (Main.blockchain.get(i).getTimeStamp() > timeStamp)){
                counter++;
            }
        }

        if(counter >= 2){
            return true;
        } else {
            return false;
        }

    }

    //Verify the block chain arraylist
    public boolean verify_Blockchain(ArrayList<Block> blockChain){

        //TMP Storage
        Block currentBlock;
        Block previousBlock;

        //Goes through the list and checks each hash to the block class
        for(int i = 1; i<blockChain.size(); i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);

            //System.out.println("check loop # "+i);
            //System.out.println("normal HASH: " + currentBlock.currentHash + ", cal HASH: " + currentBlock.currentHash);
            //Check current block hash to see if they are valid
            if(!currentBlock.currentHash.equals(currentBlock.calculateBlockHash())){
                //System.out.println("Curr Hash: " + currentBlock.currentHash);
                //System.out.println("Cal Hash: " + currentBlock.calculateBlockHash());
                System.out.println("Verify_Block_Error_1: Block Not Verified");
                return false;
            }

            //Check previous block hash to see if they are valid
            if(!previousBlock.currentHash.equals(currentBlock.previousHash)){
                System.out.println("Verify_Block_Error_2: Block Not Verified");
                return false;
            }
        }

        //If it gets here the arraylist is the valid
        return true;
    }


    //Getters And Setters
    public String getPreviousHash() {
        return previousHash;
    }
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public Transaction getData() {
        return data;
    }
    public void setData(Transaction data) {
        this.data = data;
    }
    public int getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
    public int getNonce() {
        return nonce;
    }
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
    public String getCurrentHash() {
        return currentHash;
    }
    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    @Override
    public String toString() {
        return previousHash + "," + data + "," + timeStamp + "," + nonce + "," + currentHash;
    }
}
