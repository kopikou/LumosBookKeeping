package org.example.Lumos.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="salary", nullable=false)
    private int salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private People person;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id")
    private Income income;

    public Expense(){}
    public Expense(int salary, People person, Income income) {
        this.salary = salary;
        this.person = person;
        this.income = income;
    }

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", salary=" + salary +
                ", person=" + person +
                ", income=" + income +
                '}';
    }
}
