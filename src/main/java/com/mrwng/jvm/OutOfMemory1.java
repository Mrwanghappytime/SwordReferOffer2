package com.mrwng.jvm;

import java.util.ArrayList;

public class OutOfMemory1 {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        while (true) {
            objects.add(new Object());
        }
    }
}
