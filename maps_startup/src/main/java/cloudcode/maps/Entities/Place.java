
package cloudcode.maps.Entities;


public class Place {
    private final String name;
    private final String address;

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
