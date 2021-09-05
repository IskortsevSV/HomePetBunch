package entity;


public class Flower {

    private int id;
    private String name;
    private double price;
    private int lengthSteack;
    private int iceLevel;

    private String imagePath;

    public Flower() {

    }

    public Flower(String name, double price, int lengthSteack, int iceLevel) {
        this.name = name;
        this.price = price;
        this.lengthSteack = lengthSteack;
        this.iceLevel = iceLevel;
        imagePath = name + ".jpg";
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
        imagePath = name + ".jpg";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLengthSteack() {
        return lengthSteack;
    }

    public void setLengthSteack(int lengthSteack) {
        this.lengthSteack = lengthSteack;
    }

    public int getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(int iceLevel) {
        this.iceLevel = iceLevel;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Flower flower = (Flower) obj;
            return flower.name.equals(name) && flower.price == price
                    && flower.iceLevel == iceLevel && flower.lengthSteack == lengthSteack;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 37 * result + ((name != null) ? name.hashCode() : 0);
        result = 37 * result + new Double(price).hashCode();
        result = 37 * result + lengthSteack;
        result = 37 * result + iceLevel;
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                '}';
    }
}
