public class Customer {
    private int idCard;
    private String firstName;
    private String lastName;

    public Customer(int idCard, String firstName, String lastName){
        this.idCard = idCard;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getIdCard() {
        return idCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
