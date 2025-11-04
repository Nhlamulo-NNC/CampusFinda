package com.controller;

import com.model.LocationHistory;
import com.model.LocationUpdateRequest;
import com.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "*")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    
    @PostMapping("/update")
    public ResponseEntity<?> saveLocation(@RequestBody LocationUpdateRequest request) {
        try {
            
            String location = request.getLatitude() + "," + request.getLongitude();

            LocationHistory savedLocation = locationService.saveUserLocation(request.getUserId(), location);
            return ResponseEntity.ok(Map.of(
                    "message", "Location saved successfully",
                    "data", savedLocation
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }

    
    @GetMapping("/latest/{userId}")
    public ResponseEntity<?> getLatestLocation(@PathVariable Long userId) {
        LocationHistory latest = locationService.getLatestLocation(userId);
        if (latest == null) {
            return ResponseEntity.ok(Map.of("message", "No location data found"));
        }
        return ResponseEntity.ok(latest);
    }

   
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LocationHistory>> getLocationHistory(@PathVariable Long userId) {
        List<LocationHistory> history = locationService.getLocationHistory(userId);
        return ResponseEntity.ok(history);
    }
}
