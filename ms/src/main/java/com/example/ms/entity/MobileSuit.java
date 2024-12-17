package com.example.ms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MobileSuit {

    @Id
    private String msId;

    @Column(nullable = false)
    private String msName;

    private String msType;
    private String msStatus;
    private double msCost;

    private LocalDate msCreateStartDate;
    private LocalDate msCreateEndDate;
    private boolean msNoCreate;

    @Lob
    private byte[] msImage;

    @ManyToOne
    @JoinColumn(name = "factionId")
    private Faction faction;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "MobileSuitMaterial",
            joinColumns = @JoinColumn(name = "msId"),
            inverseJoinColumns = @JoinColumn(name = "materialId")
    )
    private List<Meterial> materials = new ArrayList<>();

    public MobileSuit() {

    }

    public MobileSuit(byte[] msImage, String msId, String msName, String msType, String msStatus, double msCost, LocalDate msCreateStartDate, LocalDate msCreateEndDate, boolean msNoCreate, Faction faction, Company company, List<Meterial> materials) {
        this.msImage = msImage;
        this.msId = msId;
        this.msName = msName;
        this.msType = msType;
        this.msStatus = msStatus;
        this.msCost = msCost;
        this.msCreateStartDate = msCreateStartDate;
        this.msCreateEndDate = msCreateEndDate;
        this.msNoCreate = msNoCreate;
        this.faction = faction;
        this.company = company;
        this.materials = materials;
    }

    public List<Meterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Meterial> materials) {
        this.materials = materials;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public byte[] getMsImage() {
        return msImage;
    }

    public void setMsImage(byte[] msImage) {
        this.msImage = msImage;
    }

    public boolean isMsNoCreate() {
        return msNoCreate;
    }

    public void setMsNoCreate(boolean msNoCreate) {
        this.msNoCreate = msNoCreate;
    }

    public LocalDate getMsCreateEndDate() {
        return msCreateEndDate;
    }

    public void setMsCreateEndDate(LocalDate msCreateEndDate) {
        this.msCreateEndDate = msCreateEndDate;
    }

    public LocalDate getMsCreateStartDate() {
        return msCreateStartDate;
    }

    public void setMsCreateStartDate(LocalDate msCreateStartDate) {
        this.msCreateStartDate = msCreateStartDate;
    }

    public double getMsCost() {
        return msCost;
    }

    public void setMsCost(double msCost) {
        this.msCost = msCost;
    }

    public String getMsStatus() {
        return msStatus;
    }

    public void setMsStatus(String msStatus) {
        this.msStatus = msStatus;
    }

    public String getMsType() {
        return msType;
    }

    public void setMsType(String msType) {
        this.msType = msType;
    }

    public String getMsName() {
        return msName;
    }

    public void setMsName(String msName) {
        this.msName = msName;
    }

    public String getMsId() {
        return msId;
    }

    public void setMsId(String msId) {
        this.msId = msId;
    }
}
