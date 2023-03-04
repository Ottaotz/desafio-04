package br.com.cwi.api.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Service
public class NowService {

    public LocalDateTime getDate() {
        return now();
    }
}
