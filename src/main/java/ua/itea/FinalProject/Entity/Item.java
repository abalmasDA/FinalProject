package ua.itea.FinalProject.Entity;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_details")
    private ItemDetails details;

    public Item() {
    }

    public Item(Customer customer, ItemDetails details) {
        this.customer = customer;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ItemDetails getDetails() {
        return details;
    }

    public void setDetails(ItemDetails details) {
        this.details = details;
    }

    public String getStatus() {
        return this.details.getStatus();
    }

    public String getAdditionalInfo() {
        return details.getAdditionalInfo();
    }

    public Date getDate() {
        return details.getDate();
    }


}
