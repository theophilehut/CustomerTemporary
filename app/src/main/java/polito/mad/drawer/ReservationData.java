package polito.mad.drawer;

class ReservationData {
    private String customerName;
    private String customerPhoneNumber;
    private String dishes;
    private String time;
    private String notes;

    public ReservationData(String customerName, String customerPhoneNumber, String dishes, String time, String notes){
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.dishes = dishes;
        this.time = time;
        this.notes = notes;
    }

    public String toString(){
        String str;
        int i;
        str = time + "  " + customerName + "\n" + customerPhoneNumber + "\n" + dishes + "\n" + notes;
        return str;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getDishes() {
        return dishes;
    }

    public String getNotes() {
        return notes;
    }

    public String getTime() {
        return time;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
