package com.db.geometry.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Getter
public class RandomService {

    private final Random random = new Random();

}
