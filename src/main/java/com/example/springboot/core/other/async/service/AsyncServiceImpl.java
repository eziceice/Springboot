package com.example.springboot.core.other.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateReport() {
        System.out.println(Thread.currentThread().getName());
    }
}
