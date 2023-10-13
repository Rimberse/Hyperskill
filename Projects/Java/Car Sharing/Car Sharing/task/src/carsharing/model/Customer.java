package carsharing.model;

public class Customer {
    private long id;
    private String name;
    private long rentedCarId;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
        this.rentedCarId = -1;
    }

    public Customer(long id, String name, long rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
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

    public long getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(long rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rentedCarId=" + rentedCarId +
                '}';
    }
}
