package com.service;

import com.model.LocationHistory;
import com.model.User;
import com.repository.LocationHistoryRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationService {

    private final LocationHistoryRepository locationHistoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public LocationService(LocationHistoryRepository locationHistoryRepository, UserRepository userRepository) {
        this.locationHistoryRepository = locationHistoryRepository;
        this.userRepository = userRepository;
    }

    public LocationHistory saveUserLocation(Long userId, String location) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found."));

        LocationHistory history = new LocationHistory(user, location, LocalDateTime.now());
        return locationHistoryRepository.save(history);
    }

    public LocationHistory getLatestLocation(Long userId) {
        return locationHistoryRepository.findTopByUserIdOrderByTimestampDesc(userId).orElse(null);
    }

    public List<LocationHistory> getLocationHistory(Long userId) {
        return locationHistoryRepository.findByUserIdOrderByTimestampDesc(userId);
    }
}
