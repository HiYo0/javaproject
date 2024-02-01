package model.Dto;

public class Reservation_dateDto {
    private int resrvation_date_pk;
    private String reservation_date;
    private int house_pk;
    private int day_price;

    public Reservation_dateDto(){}
    public Reservation_dateDto(int resrvation_date_pk, String reservation_date, int house_pk, int day_price) {
        this.resrvation_date_pk = resrvation_date_pk;
        this.reservation_date = reservation_date;
        this.house_pk = house_pk;
        this.day_price = day_price;
    }

    public int getResrvation_date_pk() {
        return resrvation_date_pk;
    }

    public void setResrvation_date_pk(int resrvation_date_pk) {
        this.resrvation_date_pk = resrvation_date_pk;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getHouse_pk() {
        return house_pk;
    }

    public void setHouse_pk(int house_pk) {
        this.house_pk = house_pk;
    }

    public int getDay_price() {
        return day_price;
    }

    public void setDay_price(int day_price) {
        this.day_price = day_price;
    }

    @Override
    public String toString() {
        return "Reservation_date{" +
                "resrvation_date_pk=" + resrvation_date_pk +
                ", reservation_date='" + reservation_date + '\'' +
                ", house_pk=" + house_pk +
                ", day_price=" + day_price +
                '}';
    }
}
