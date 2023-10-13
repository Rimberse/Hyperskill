package carsharing.model;

public class Car {
    private long id;
    private String name;
    private long companyId;

    public Car(long id, String name, long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
