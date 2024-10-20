package org.example;

import org.example.entity.Alert;
import org.example.entity.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }
}