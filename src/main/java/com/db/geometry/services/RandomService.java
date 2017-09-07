package com.db.geometry.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {

    private final Random random = new Random();

    public int getInt(int bound) {
        return random.nextInt(bound);
    }

    public int getInRange(int from, int to) {
        return from + getInt(to - from);
    }

}
