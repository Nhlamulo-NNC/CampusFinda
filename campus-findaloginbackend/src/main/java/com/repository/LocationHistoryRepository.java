package com.repository;

import com.model.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {
    List<LocationHistory> findByUserIdOrderByTimestampDesc(Long userId);
    Optional<LocationHistory> findTopByUserIdOrderByTimestampDesc(Long userId);
}
