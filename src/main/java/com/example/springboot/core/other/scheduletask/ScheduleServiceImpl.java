package com.example.springboot.core.other.scheduletask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl {
    int count1 = 1;
    int count2 = 1;
    int count3 = 1;
    int count4 = 1;

    @Scheduled(fixedRate = 1000)
    @Async
    public void job1()
    {
        System.out.println(Thread.currentThread().getName() + "count1: " + count1);
        count1++;
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void job2()
    {
        System.out.println(Thread.currentThread().getName() + "count2: " + count2);
        count2++;
    }

    @Scheduled(initialDelay = 3000, fixedRate = 1000)
    @Async
    public void job3()
    {
        System.out.println(Thread.currentThread().getName() + "count3: " + count3);
        count3++;
    }

    @Scheduled(cron = "0 * 11 * * ?")
    @Async
    public void job4()
    {
        System.out.println(Thread.currentThread().getName() + "count4: " + count4);
        count4++;
    }
}
