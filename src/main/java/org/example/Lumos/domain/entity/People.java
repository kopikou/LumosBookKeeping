package org.example.Lumos.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="people")
public class People {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable=false)
    private String name;
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;
    @ManyToMany(mappedBy = "artists")
    private List<ShowProgram> artistForShow;
    @ManyToMany(mappedBy = "technicians")
    private List<ShowProgram> technicianForShow;
    @ManyToMany(mappedBy = "transfers")
    private List<ShowProgram> transferForShow;

    public People(){
    }
    public People(String name, List<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
    }
    public People(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<ShowProgram> getArtistForShow() {
        return artistForShow;
    }
    public void setArtistForShow(List<ShowProgram> artistForShow) {
        this.artistForShow = artistForShow;
    }

    public List<ShowProgram> getTechnicianForShow() {
        return technicianForShow;
    }
    public void setTechnicianForShow(List<ShowProgram> technicianForShow) {
        this.technicianForShow = technicianForShow;
    }

    public List<ShowProgram> getTransferForShow() {
        return transferForShow;
    }
    public void setTransferForShow(List<ShowProgram> transferForShow) {
        this.transferForShow = transferForShow;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expenses=" + expenses +
                ", artistForShow=" + artistForShow +
                ", technicianForShow=" + technicianForShow +
                ", transferForShow=" + transferForShow +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People)) return false;
        People people = (People) o;
        return getId() == people.getId() && Objects.equals(getName(), people.getName()) && Objects.equals(getExpenses(), people.getExpenses()) && Objects.equals(getArtistForShow(), people.getArtistForShow()) && Objects.equals(getTechnicianForShow(), people.getTechnicianForShow()) && Objects.equals(getTransferForShow(), people.getTransferForShow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getExpenses(), getArtistForShow(), getTechnicianForShow(), getTransferForShow());
    }
}
