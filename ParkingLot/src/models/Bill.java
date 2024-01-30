package models;

import java.util.Date;
import java.util.List;

public class Bill extends BaseModel{

    private Date exitTime;

    private Token token;

    private Gate generateAt;

    private Operator generatedBy;

    private List<Payment> paymentList;

    private int totalAmount;

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Gate getGenerateAt() {
        return generateAt;
    }

    public void setGenerateAt(Gate generateAt) {
        this.generateAt = generateAt;
    }

    public Operator getGetGenerateBy() {
        return generatedBy;
    }

    public void setGetGenerateBy(Operator generatedBy) {
        this.generatedBy = generatedBy;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    private BillStatus billStatus;

}
