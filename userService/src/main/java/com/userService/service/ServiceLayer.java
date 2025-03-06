package com.userService.service;

import com.userService.entities.Hotel;
import com.userService.entities.Rating;
import com.userService.entities.User;
import com.userService.external.HotelServiceFeign;
import com.userService.external.RatingServiceFeign;
import com.userService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {
    private final UserRepo userRepo;

    @Autowired
    private HotelServiceFeign hotelServiceFeign;

    @Autowired
    private RatingServiceFeign ratingServiceFeign;

    @Autowired
    private RestTemplate restTemplate;

    public ServiceLayer(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public User getUser(String userId) {

        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));

        //Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+user.getUserId(), Rating[].class);

        Rating[] ratingOfUser = ratingServiceFeign.getRating(user.getUserId());
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();


        List<Rating> listRatingList = ratings.stream().map(rating -> {

            Hotel hotel = hotelServiceFeign.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(listRatingList);
        return user;
    }

    public User createNewUser(User user) {
        //  String hotelId = UUID.randomUUID().toString(); // Generate unique ID
        //user.setUserId(hotelId); // Assign ID to the hotel
        return userRepo.save(user);
    }

    public List<User> createNewUsers(List<User> users) {
        // Logic to save all users in the database
        return userRepo.saveAll(users); // Assuming you use JPA's saveAll method
    }


    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }


    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }

    public User updateUser(Map<String, Object> update, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));


        for(Map.Entry<String, Object> entry : update.entrySet()){
            String fieldName = entry.getKey();
            Object values = entry.getValue();

            try {
                Field field = User.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(user, values);

            }
            catch (Exception e){
                System.out.println(" Exception occur");
            }

        }
        return userRepo.save(user);
    }

}