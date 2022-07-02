package lk.ijse.supermarket.view.tm;

public class CustomerTM implements Comparable<CustomerTM> {
     private String Id;
     private String name;
    private String address;
private String city;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, String city) {
        Id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    @Override
    public int compareTo(CustomerTM o) {
        return 0;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
