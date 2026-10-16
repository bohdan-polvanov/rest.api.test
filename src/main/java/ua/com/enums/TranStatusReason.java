package ua.com.enums;

public enum TranStatusReason {
    TRAN_STATUS_REASON_01("01"),
    TRAN_STATUS_REASON_02("02"),
    TRAN_STATUS_REASON_03("03"),
    TRAN_STATUS_REASON_04("04"),
    TRAN_STATUS_REASON_05("05"),
    TRAN_STATUS_REASON_06("06"),
    TRAN_STATUS_REASON_07("07"),
    TRAN_STATUS_REASON_08("08"),
    TRAN_STATUS_REASON_09("09"),
    TRAN_STATUS_REASON_10("10"),
    TRAN_STATUS_REASON_11("11"),
    TRAN_STATUS_REASON_12("12"),
    TRAN_STATUS_REASON_13("13"),
    TRAN_STATUS_REASON_14("14"),
    TRAN_STATUS_REASON_15("15"),
    TRAN_STATUS_REASON_16("16"),
    TRAN_STATUS_REASON_17("17"),
    TRAN_STATUS_REASON_18("18"),
    TRAN_STATUS_REASON_19("19"),
    TRAN_STATUS_REASON_20("20");

    private final String status;

    TranStatusReason(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
