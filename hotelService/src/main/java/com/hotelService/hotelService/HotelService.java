package com.hotelService.hotelService;

import com.hotelService.entities.Hotel;
import com.hotelService.hotelRepo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);
    private final HotelRepo hotelRepo;

    public HotelService(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public List<Hotel> getAllHotels() {
        logger.info("Fetching all hotels");
        return hotelRepo.findAll();
    }

    public Hotel createNewHotel(Hotel hotel) {
//        String hotelId = UUID.randomUUID().toString();
//        hotel.setHotelId(hotelId);
//        logger.info("Creating new hotel with ID: {}", hotelId);
        return hotelRepo.save(hotel);
    }

    public Hotel getHotelById(String hotelId) {
        logger.info("Fetching hotel with ID: {}", hotelId);
        return hotelRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelId));
    }

    public void deletHotel(String id) {
        hotelRepo.deleteById(id);

    }
}
