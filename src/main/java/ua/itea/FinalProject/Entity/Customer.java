package ua.itea.FinalProject.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters long")
    @Column(name = "fullName")
    private String fullName;
    @NotEmpty(message = "phone should not be empty")
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Item> items;


    public Customer() {
    }

    public Customer(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Item> getItems() {
        return items;
    }
}
