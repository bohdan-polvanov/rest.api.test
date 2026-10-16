package ua.com.enums;

public enum GeneralStatus {
    messageType("ARes"),
    messageVersion("2.2.0"),
    acsOperatorID("10039427"),
    authenticationType_01("01"),
    authenticationType_02("02"),
    authenticationType_03("03"),
    authenticationType_04("04"),
    dsReferenceNumber("GP_TEST_LAB_MC_001"),
    acsReferenceNumber("3DS_LOA_ACS_GPPL_020200_00763");

    private final String status;

    GeneralStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
