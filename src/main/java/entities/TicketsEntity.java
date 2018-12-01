package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "tickets", schema = "bus_tickets", catalog = "postgres")
public class TicketsEntity {
    private BigInteger ticketId;
    private String name;
    private String surname;
    private String surname2;
    private String birthday;
    private String birthdayNum;
    private BigInteger passNum;
    private BigInteger passSeria;
    private String place;
    private String raceNum;
    private double price;
    private BigInteger raceId;

    @Id
    @Column(name = "ticket_id", nullable = false, precision = 0)
    public BigInteger getTicketId() {
        return ticketId;
    }

    public void setTicketId(BigInteger ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "surname2", nullable = true, length = -1)
    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    @Basic
    @Column(name = "birthday", nullable = true, length = -1)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "birthday_num", nullable = true, length = -1)
    public String getBirthdayNum() {
        return birthdayNum;
    }

    public void setBirthdayNum(String birthdayNum) {
        this.birthdayNum = birthdayNum;
    }

    @Basic
    @Column(name = "pass_num", nullable = true, precision = 0)
    public BigInteger getPassNum() {
        return passNum;
    }

    public void setPassNum(BigInteger passNum) {
        this.passNum = passNum;
    }

    @Basic
    @Column(name = "pass_seria", nullable = true, precision = 0)
    public BigInteger getPassSeria() {
        return passSeria;
    }

    public void setPassSeria(BigInteger passSeria) {
        this.passSeria = passSeria;
    }

    @Basic
    @Column(name = "place", nullable = true, length = -1)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "race_num", nullable = true, length = -1)
    public String getRaceNum() {
        return raceNum;
    }

    public void setRaceNum(String raceNum) {
        this.raceNum = raceNum;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double prace) {
        this.price = price;
    }

    @Basic
    @Column(name = "race_id", nullable = true, precision = 0)
    public BigInteger getRaceId() {
        return raceId;
    }

    public void setRaceId(BigInteger raceId) {
        this.raceId = raceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketsEntity that = (TicketsEntity) o;
        return Objects.equals(ticketId, that.ticketId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(surname2, that.surname2) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(birthdayNum, that.birthdayNum) &&
                Objects.equals(passNum, that.passNum) &&
                Objects.equals(passSeria, that.passSeria) &&
                Objects.equals(place, that.place) &&
                Objects.equals(raceNum, that.raceNum) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ticketId, name, surname, surname2, birthday, birthdayNum, passNum, passSeria, place, raceNum, price);
    }

    @Override
    public String toString(){
        return "Номер билета: " + ticketId
                + "\nРейс: " + raceNum
                + "\nМесто: " + place
                + "\nИмя: " + name
                + "\nФамилия: " + surname
                + "\nОтчество: " + surname2
                + "\nДата рождения: " + birthday
                + "\nНомер свед. о рождении: " + birthdayNum
                + "\nСерия и номер паспорта: " + passSeria + " " + passNum
                + "\nЦена с НДС: " + price
                + "\nНДС: 1.18";
    }

    public static TicketEntityBuilder newTicketEntityBuilder() {
        return new TicketsEntity().new TicketEntityBuilder();
    }

    public class TicketEntityBuilder{

        public TicketEntityBuilder setTicketId(BigInteger ticketId){
            TicketsEntity.this.ticketId = ticketId;
            return this;
        }

        public TicketEntityBuilder setName(String name){
            TicketsEntity.this.name = name;
            return this;
        }

        public TicketEntityBuilder setSurname(String surname){
            TicketsEntity.this.surname = surname;
            return this;
        }

        public TicketEntityBuilder setSurname2(String surname2){
            TicketsEntity.this.surname2 = surname2;
            return this;
        }

        public TicketEntityBuilder setBirthday(String birthday){
            TicketsEntity.this.birthday = birthday;
            return this;
        }

        public TicketEntityBuilder setBirthdayNum(String birthdayNum){
            TicketsEntity.this.birthdayNum = birthdayNum;
            return this;
        }

        public TicketEntityBuilder setPassNum(BigInteger passNum){
            TicketsEntity.this.passNum = passNum;
            return this;
        }

        public TicketEntityBuilder setPassSeria(BigInteger passSeria){
            TicketsEntity.this.passSeria = passSeria;
            return this;
        }

        public TicketEntityBuilder setPlace(String place){
            TicketsEntity.this.place = place;
            return this;
        }

        public TicketEntityBuilder setRaceNum(String raceNum){
            TicketsEntity.this.raceNum = raceNum;
            return this;
        }

        public TicketEntityBuilder setPrice(double price){
            TicketsEntity.this.price = price;
            return this;
        }

        public TicketEntityBuilder setRaceId(BigInteger raceId){
            TicketsEntity.this.raceId = raceId;
            return this;
        }

        public TicketsEntity build() {
            return TicketsEntity.this;
        }
    }
}
