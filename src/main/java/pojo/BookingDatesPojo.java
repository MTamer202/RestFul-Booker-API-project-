package pojo;

public class BookingDatesPojo
{
    private String checkin;
    private String checkout;

    public BookingDatesPojo(String checkin, String checkout)
    {
        this.setCheckin(checkin);
        this.setCheckout(checkout);
    }


    // Getters and Setters
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
