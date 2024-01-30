package models;

public class Gate extends BaseModel{

    private int gateNumber;

    private Operator gateOperator;
    private GateType gateType;

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Operator getGateOperator() {
        return gateOperator;
    }

    public void setGateOperator(Operator gateOperator) {
        this.gateOperator = gateOperator;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    private GateStatus gateStatus;
}
