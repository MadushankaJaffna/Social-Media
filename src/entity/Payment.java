package entity;

import java.sql.Date;

public class Payment implements SuperEntity{
    private String userId;
    private String name;
    private Long cardno;
    private Date date;
    private Double price;

    public Payment() {
    }

    public Payment(String userId, String name, Long cardno, Date date, Double price) {
        this.userId = userId;
        this.name = name;
        this.cardno = cardno;
        this.date = date;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCardno() {
        return cardno;
    }

    public void setCardno(Long cardno) {
        this.cardno = cardno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", cardno=" + cardno +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
