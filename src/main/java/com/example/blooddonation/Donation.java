package com.example.blooddonation;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="donor_id")
    private Donor donor;

    private LocalDate donationDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public LocalDate getDonationDate() { return donationDate; }
    public void setDonationDate(LocalDate donationDate) { this.donationDate = donationDate; }
}
