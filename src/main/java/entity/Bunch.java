package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 06.02.2018.
 */
public class Bunch {

    private List<Flower> flowers;
    private int id;

    public Bunch() {
        flowers = new ArrayList<Flower>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
