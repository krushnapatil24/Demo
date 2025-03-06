package com.ratingService.service;

import com.ratingService.entities.Rating;
import com.ratingService.repo.RatingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepo ratingRepo;

    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Rating createRating(Rating rating){
    return ratingRepo.save(rating);
    }

    public List<Rating> getAll() {
        return ratingRepo.findAll();
    }

    public List<Rating> getByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    public List<Rating> getByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }

    public void deleteRating(String ratingId) {
        ratingRepo.deleteById(ratingId);
    }
}
