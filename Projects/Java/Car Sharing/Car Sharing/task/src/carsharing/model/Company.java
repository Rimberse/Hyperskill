package carsharing.model;

public class Company {
    private long id;
    private String name;

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company {" + "id = " + id + ", name = '" + name + '\'' + '}';
    }
}
