package com.hotelService.hotelController;

import com.hotelService.entities.Hotel;
import com.hotelService.hotelService.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    public HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable String hotelId){
        return hotelService.getHotelById(hotelId);
    }

    @PostMapping("/")
    public Hotel createNewHotel(@RequestBody Hotel hotel){
        return hotelService.createNewHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void deletHotel(@PathVariable String id){
        hotelService.deletHotel(id);
    }

}
