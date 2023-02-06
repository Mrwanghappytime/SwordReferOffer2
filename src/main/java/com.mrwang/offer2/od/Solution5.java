package com.mrwang.offer2.od;

import java.util.HashMap;

public class Solution5 {
    public static void main(String[] args) {
        System.out.println(new Solution5().getMaxThroughColorOfCarInSecond(new int[]{0, 1, 2, 1}, 3));
    }

    public int getMaxThroughColorOfCarInSecond(int[] cars, int second) {
        HashMap<Integer, Integer> carCountMap = new HashMap<>();
        int result = initMapAndGetBeginResult(carCountMap, cars, second);
        for (int i = 1; i <= cars.length - second; i++) {
            Integer integer = carCountMap.get(cars[i - 1]);
            carCountMap.put(cars[i - 1], integer - 1);
            int count = carCountMap.getOrDefault(cars[i + second - 1], 0) + 1;
            carCountMap.put(cars[i + second - 1], count);
            result = Math.max(result, count);
        }
        return result;
    }

    private int initMapAndGetBeginResult(HashMap<Integer, Integer> carCountMap, int[] cars, int second) {
        int result = 0;
        for (int i = 0; i < second; i++) {
            int count = carCountMap.getOrDefault(cars[i], 0) + 1;
            carCountMap.put(cars[i], count);
            result = Math.max(result, count);
        }
        return result;
    }
}
