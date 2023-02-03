package com.mrwng.jvm;

public class ToStringClass {
    private ToStringClass toStringClass;
    private String str;

    @Override
    public String toString() {
        return "ToStringClass{" +
//                "toStringClass=" + toStringClass +
                ", str='" + str + '\'' +
                '}';
    }
}
