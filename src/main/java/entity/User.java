package entity;

import java.util.ArrayList;
import java.util.List;


public class User {

    private int id;
    private String name;
    private String password;
    private List<Bunch> bunch;
    private boolean isAdmin;

    public User() {
        bunch = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bunch> getBunch() {
        return bunch;
    }

    public void setBunch(List<Bunch> bunch) {
        this.bunch = bunch;
    }

    //проверка на админа
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    //будем сравнивать по имении
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            User user = (User) o;
            return user.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + ((name != null) ? name.hashCode() : 0);
        return result;
    }
}
