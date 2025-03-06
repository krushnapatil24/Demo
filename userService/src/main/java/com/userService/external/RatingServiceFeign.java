package com.userService.external;

import com.userService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RATINGSERVICE")
public interface RatingServiceFeign {
    @GetMapping("/rating/user/{userId}")
    Rating[] getRating(@PathVariable String userId);
}
