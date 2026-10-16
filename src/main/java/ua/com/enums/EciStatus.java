package ua.com.enums;

public enum EciStatus {

    ECI_N2("N2"),
    ECI_N0("N0"),
    ECI_02("02"),
    ECI_01("01"),
    ECI_00("00"),
    ECI_05("05"),
    ECI_06("06"),
    ECI_07("07"),
    ECI_P0("P0"),
    ECI_P2("P2"),
    ECI_P3("P3");

    private final String status;

    EciStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }

}
