package com.service;

import com.model.Device;
import com.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository repo;

    public DeviceService(DeviceRepository repo) {
        this.repo = repo;
    }

    public Device saveDevice(Device d) {
        return repo.save(d);
    }

    public List<Device> getAllDevices() {
        return repo.findAll();
    }
}

