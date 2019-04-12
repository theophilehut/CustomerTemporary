package polito.mad.drawer;

class TodayMenuData {
    private String dishesName;
    private String dishesPrice;
    private String description;
    private String notes;

    public TodayMenuData(String dishesName, String dishesPrice, String description, String notes){
        this.dishesName = dishesName;
        this.dishesPrice = dishesPrice;
        this.description = description;
        this.notes = notes;
    }

    public String toString(){
        String str;
        int i;
        str = dishesName + "\n" + dishesPrice + "\n" + description + "\n" + notes + "\n";
        return str;
    }

    public String getDishesName() {
        return dishesName;
    }

    public String getDishesPrice() {
        return dishesPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public void setDishesPrice(String dishesPrice) {
        this.dishesPrice = dishesPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
