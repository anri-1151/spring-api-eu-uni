package ge.eu.demo.dto;

public class AddAddress {

    private String address;
    private String district;

    public AddAddress() {}
    public AddAddress(String address, String district) {
        this.address = address;
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
