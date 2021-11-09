import java.time.LocalDateTime;

public class Transaction {
    private Artefact artefact;
    private int timeStamp;
    private Stakeholder seller;
    private Stakeholder buyer;
    private Stakeholder auctionHouse;
    private int price;

    //Constructor
    public Transaction(){
    }

    //Constructor w/ Requirements
    public Transaction(Artefact artefact, int timeStamp, Stakeholder seller, Stakeholder buyer, Stakeholder auctionHouse, int price){
        this.artefact = artefact;
        this.timeStamp = timeStamp;
        this.seller = seller;
        this.buyer = buyer;
        this.auctionHouse = auctionHouse;
        this.price = price;
    }

    //Getters And Setters
    public Artefact getArtefact() {
        return artefact;
    }
    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }
    public int getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
    public Stakeholder getSeller() {
        return seller;
    }
    public void setSeller(Stakeholder seller) {
        this.seller = seller;
    }
    public Stakeholder getBuyer() {
        return buyer;
    }
    public void setBuyer(Stakeholder buyer) {
        this.buyer = buyer;
    }
    public Stakeholder getAuctionHouse() {
        return auctionHouse;
    }
    public void setAuctionHouse(Stakeholder auctionHouse) {
        this.auctionHouse = auctionHouse;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return artefact + "," + timeStamp + "," + seller + "," + buyer + "," + auctionHouse + "," + price;
    }
}
