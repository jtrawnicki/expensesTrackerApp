package pl.jtrawnicki.model;

public class Expense {

    private String name;

    private Category category;

    private String price;

    public Expense() {
    }

    public Expense(String name, Category category, String price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price='" + price + '\'' +
                '}';
    }
}
