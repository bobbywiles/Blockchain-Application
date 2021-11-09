public class Stakeholder {
    private String id;
    private String name;
    private String address;
    private int balance;

    //Constructor
    public Stakeholder(){
    }

    //Constructor w/ Requirements
    public Stakeholder(String id, String name, String address, int balance){
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    //Getters And Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + address + "," + balance;
    }
}
