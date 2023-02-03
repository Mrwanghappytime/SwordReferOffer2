package com.mrwang.concurrent.map;

import com.mrwang.offer2.util.ArrayUtil;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        int[] randomArray = ArrayUtil.getAscArray(10);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        TreeMap<Integer, Integer> treeMap1 = new TreeMap<>();
        for (int i : randomArray) {
            treeMap.put(i, 1);
            hashMap.put(i, 1);
        }

        int temp = randomArray[1];
        randomArray[1] = randomArray[9];
        randomArray[9] = temp;
        for (int i : randomArray) {
            treeMap1.put(i, 1);
            hashMap1.put(i, 1);
        }
        System.out.println(treeMap.toString().equals(treeMap1.toString()));
        System.out.println(hashMap.toString().equals(hashMap1.toString()));
    }
}
