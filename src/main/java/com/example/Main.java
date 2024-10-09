package com.example;

import com.example.riddleanswerers.MinimumMissingNumberAnswerer;

import java.util.*;

public class Main {
    private static final NavigableSet<Integer> ARRAY = new TreeSet<>(Arrays.asList(3, 4, -1, 1));

    public static void main(String[] args) {
        var answerer = new MinimumMissingNumberAnswerer(ARRAY);

        int answer = answerer.getAnswer();

        System.out.println("Original array: " + ARRAY);
        System.out.println("Minimum positive missing number: " + answer);
    }
}
