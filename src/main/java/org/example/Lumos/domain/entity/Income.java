package org.example.Lumos.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="date", nullable=false)
    private String date;
    @Column(name="place", nullable=false)
    private String place;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private ShowProgram showProgram;
    @OneToMany(mappedBy = "income",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;

    public Income(){}
    public Income(String date, String place, ShowProgram showProgram) {//, List<Expense> expenses) {
        this.date = date;
        this.place = place;
        this.showProgram = showProgram;
        //this.expenses = expenses;
    }

    public int getId() {
        return id;
    }
    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public ShowProgram getShowProgram() {
        return showProgram;
    }
    public void setShowProgram(ShowProgram showProgram) {
        this.showProgram = showProgram;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                ", showProgram=" + showProgram;
    }
}
