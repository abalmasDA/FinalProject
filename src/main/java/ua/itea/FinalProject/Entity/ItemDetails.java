package ua.itea.FinalProject.Entity;


import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "Item_details")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "additional_info", nullable = false)
    private String additionalInfo;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne(mappedBy = "details")
    private Item item;

    public ItemDetails() {
    }

    public ItemDetails(String status, String additionalInfo, Date date) {
        this.status = status;
        this.additionalInfo = additionalInfo;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
