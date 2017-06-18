package tegoods.model;

/**
 *
 * @author igor
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String addDate;
    private byte[] picture;

    public Product() {
    }

    public Product(String name, double price, String addDate, byte[] picture) {
        this.name = name;
        this.price = price;
        this.addDate = addDate;
        this.picture = picture;
    }

    
    public Product(int id, String name, double price, String addDate, byte[] picture) {
        this(name, price, addDate, picture);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
    
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
