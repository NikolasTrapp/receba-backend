package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.repositories.NotificationRepository;
import br.nikolastrapp.receba.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
}