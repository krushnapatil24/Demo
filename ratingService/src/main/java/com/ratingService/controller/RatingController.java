package com.ratingService.controller;

import com.ratingService.entities.Rating;
import com.ratingService.service.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @PostMapping("/")
    public Rating createRating(@RequestBody Rating rating){
        return ratingService.createRating(rating);
    }

    @GetMapping
    public List<Rating> getAll(){
        return ratingService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getByUserId(@PathVariable String userId){
        return ratingService.getByUserId(userId);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Rating> getByHotelId(@PathVariable String hotelId){
        return ratingService.getByHotelId(hotelId);
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable String ratingId){
        ratingService.deleteRating(ratingId);
    }
}
