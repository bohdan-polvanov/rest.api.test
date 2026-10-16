package ua.com.enums;

public enum TranStatus {
    TRAN_STATUS_A("A"),
    TRAN_STATUS_N("N"),
    TRAN_STATUS_R("R"),
    TRAN_STATUS_C("C"),
    TRAN_STATUS_U("U"),
    TRAN_STATUS_Y("Y"),
    TRAN_STATUS_D("D"),
    TRAN_STATUS_I("I");

    private final String status;

    TranStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
