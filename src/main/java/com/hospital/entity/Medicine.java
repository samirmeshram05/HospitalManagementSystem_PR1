package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long medicineId;

    @NotBlank(message = "Medicine Name is required")
    private String medicineName;

    @NotBlank(message = "Medicine Type is required")
    private String medicineType;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    public Medicine() {
    }

    public Medicine(Long medicineId, String medicineName,
            String medicineType, String dosage,
            Integer quantity, String manufacturer,
            Prescription prescription) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.dosage = dosage;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.prescription = prescription;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}