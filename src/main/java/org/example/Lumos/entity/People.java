package org.example.Lumos.entity;

import jakarta.persistence.*;

import java.util.List;

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

    public People(){
    }
    public People(String name, List<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
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

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name;
    }
}
