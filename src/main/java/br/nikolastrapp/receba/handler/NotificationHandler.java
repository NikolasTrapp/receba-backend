package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificationHandler {

    private final NotificationService notificationService;
}
