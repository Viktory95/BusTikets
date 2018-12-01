package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Entity
@Table(name = "races", schema = "bus_tickets", catalog = "postgres")
public class RacesEntity {
    private String raceName;
    private String busNum;
    private String emptyPlaces;
    private BigInteger raceId;
    private Timestamp raceDate;
    private BigInteger price;

    @Basic
    @Column(name = "race_name", nullable = true, length = -1)
    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @Basic
    @Column(name = "bus_num", nullable = true, length = -1)
    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    @Basic
    @Column(name = "empty_places", nullable = true, length = -1)
    public String getEmptyPlaces() {
        return emptyPlaces;
    }

    public void setEmptyPlaces(String emptyPlaces) {
        this.emptyPlaces = emptyPlaces;
    }

    @Id
    @Column(name = "race_id", nullable = true, precision = 0)
    public BigInteger getRaceId() {
        return raceId;
    }

    public void setRaceId(BigInteger raceId) {
        this.raceId = raceId;
    }

    @Basic
    @Column(name = "race_date", nullable = true, length = -1)
    public Timestamp getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Timestamp raceDate) {
        this.raceDate = raceDate;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacesEntity that = (RacesEntity) o;
        return Objects.equals(raceName, that.raceName) &&
                Objects.equals(busNum, that.busNum) &&
                Objects.equals(emptyPlaces, that.emptyPlaces) &&
                Objects.equals(raceId, that.raceId) &&
                Objects.equals(raceDate, that.raceDate) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(raceName, busNum, emptyPlaces, raceId, raceDate, price);
    }

    @Override
    public String toString(){
        Date date = new Date(raceDate.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String formattedDate = sdf.format(date);
        return raceName + " " + formattedDate;
    }
}
