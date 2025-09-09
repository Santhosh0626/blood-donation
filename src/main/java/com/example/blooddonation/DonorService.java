package com.example.blooddonation;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DonorService {
    private final DonorRepository donorRepo;
    private final DonationRepository donationRepo;

    public DonorService(DonorRepository donorRepo, DonationRepository donationRepo) {
        this.donorRepo = donorRepo;
        this.donationRepo = donationRepo;
    }

    public Donor saveDonor(Donor donor) { return donorRepo.save(donor); }

    public List<Donor> getAll() { return donorRepo.findAll(); }

    public List<Donor> search(String bloodGroup, String location) {
        if ((bloodGroup == null || bloodGroup.isBlank()) && (location == null || location.isBlank())) {
            return donorRepo.findAll();
        }
        return donorRepo.search(bloodGroup.isBlank() ? null : bloodGroup, location.isBlank() ? null : location);
    }

    public void recordDonation(Long donorId, LocalDate date) {
        Donor donor = donorRepo.findById(donorId).orElseThrow();
        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setDonationDate(date != null ? date : LocalDate.now());
        donationRepo.save(donation);
    }
}
