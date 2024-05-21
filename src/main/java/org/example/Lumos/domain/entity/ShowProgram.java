package org.example.Lumos.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "show_programs")
public class ShowProgram {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="title", nullable=false)
    private String title;
    @Column(name="price", nullable=false)
    private int price;
    @Column(name="artists_cnt", nullable=false)
    private int artistsCnt;
    @Column(name="technicians_cnt", nullable=false)
    private int techniciansCnt;
    @Column(name="transfer_cnt", nullable=false)
    private int transferCnt;
    @Column(name="artist_salary", nullable=false)
    private int artistSalary;
    @Column(name="technician_salary", nullable=false)
    private int technicianSalary;
    @Column(name="transfer_cost", nullable=false)
    private int transferCost;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_show",
            joinColumns = { @JoinColumn(name = "id_show") },
            inverseJoinColumns = { @JoinColumn(name = "id_artist") })
    private List<People> artists;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "technician_show",
            joinColumns = { @JoinColumn(name = "id_show") },
            inverseJoinColumns = { @JoinColumn(name = "id_technician") })
    private List<People> technicians;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "transfer_show",
            joinColumns = { @JoinColumn(name = "id_show") },
            inverseJoinColumns = { @JoinColumn(name = "id_transfer") })
    private List<People> transfers;
    @OneToMany(mappedBy = "showProgram",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes;

    public ShowProgram(){

    }
    public ShowProgram(String title, int price, int artistsCnt, int techniciansCnt, int transferCnt, int artistSalary, int technicianSalary, int transferCost, List<People> artists, List<People> technicians, List<People> transfers, List<Income> incomes) {
        this.title = title;
        this.price = price;
        this.artistsCnt = artistsCnt;
        this.techniciansCnt = techniciansCnt;
        this.transferCnt = transferCnt;
        this.artistSalary = artistSalary;
        this.technicianSalary = technicianSalary;
        this.transferCost = transferCost;
        this.artists = artists;
        this.technicians = technicians;
        this.transfers = transfers;
        this.incomes = incomes;
    }

    public int getId() {
        return id;
    }
    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getArtistsCnt() {
        return artistsCnt;
    }
    public void setArtistsCnt(int artistsCnt) {
        this.artistsCnt = artistsCnt;
    }

    public int getTechniciansCnt() {
        return techniciansCnt;
    }
    public void setTechniciansCnt(int techniciansCnt) {
        this.techniciansCnt = techniciansCnt;
    }

    public int getTransferCnt() {
        return transferCnt;
    }
    public void setTransferCnt(int transferCnt) {
        this.transferCnt = transferCnt;
    }

    public int getArtistSalary() {
        return artistSalary;
    }
    public void setArtistSalary(int artistSalary) {
        this.artistSalary = artistSalary;
    }

    public int getTechnicianSalary() {
        return technicianSalary;
    }
    public void setTechnicianSalary(int technicianSalary) {
        this.technicianSalary = technicianSalary;
    }

    public int getTransferCost() {
        return transferCost;
    }
    public void setTransferCost(int transferCost) {
        this.transferCost = transferCost;
    }

    public List<People> getArtists() {
        return artists;
    }
    public void setArtists(List<People> artists) {
        this.artists = artists;
    }

    public List<People> getTechnicians() {
        return technicians;
    }
    public void setTechnicians(List<People> technicians) {
        this.technicians = technicians;
    }

    public List<People> getTransfers() {
        return transfers;
    }
    public void setTransfers(List<People> transfers) {
        this.transfers = transfers;
    }

    public List<Income> getIncomes() {
        return incomes;
    }
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public void addArtist(People people){
        artists.add(people);
    }
    public void removeArtist(People people){
        artists.remove(people);
    }
    public void removeArtist(int id){
        artists.remove(id);
    }

    public void addTechnician(People people){
        technicians.add(people);
    }
    public void removeTechnician(People people){
        technicians.remove(people);
    }
    public void removeTechnician(int id){
        technicians.remove(id);
    }

    public void addTransfer(People people){
        transfers.add(people);
    }
    public void removeTransfer(int id){
        transfers.remove(id);
    }

    public void addIncome(Income income){
        incomes.add(income);
    }
    public void removeIncome(Income income){
        incomes.remove(income);
    }

    @Override
    public String toString() {
        return "ShowProgram{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", artistsCnt=" + artistsCnt +
                ", techniciansCnt=" + techniciansCnt +
                ", transferCnt=" + transferCnt +
                ", artistSalary=" + artistSalary +
                ", technicianSalary=" + technicianSalary +
                ", transferCost=" + transferCost; //+
                //", artists=" + artists +
                //", technicians=" + technicians +
               // ", transfers=" + transfers;
    }
}
