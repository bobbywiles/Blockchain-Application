public class Artefact {
    private String id;
    private String name;
    private String countryOrigin;
    private Stakeholder Owner;

    //Constructor
    public Artefact(){
    }

    //Constructor w/ Requirements
    public Artefact(String id, String name, String countryOrigin, Stakeholder owner){
        this.id = id;
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.Owner = owner;
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
    public String getCountryOrigin() {
        return countryOrigin;
    }
    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }
    public Stakeholder getOwner() {
        return Owner;
    }
    public void setOwner(Stakeholder owner) {
        Owner = owner;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + countryOrigin + "," + Owner;
    }
}