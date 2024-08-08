package cloudcode.maps.Interface_adapter;


public class Place {
    private String name;
    private String address;

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Place{name='" + name + "', address='" + address + "'}";
    }
}

