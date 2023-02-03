package com.mrwang.offer2.leetcode;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int[][] ints = new Solution().floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);

    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int line = image.length;
        int column = image[0].length;

        boolean needFill[][] = new boolean[line][column];

        List<int[]> needScan = new ArrayList<>();

        needScan.add(new int[]{sr, sc});

        while (needScan.size() != 0) {
            List<int[]> temp = new ArrayList<>();
            for (int[] a : needScan) {
                needFill[a[0]][a[1]] = true;
                if (needScan(a, a[0] -1, a[1], image, needFill)) {
                    temp.add(new int[]{a[0] - 1, a[1]});
                }

                if (needScan(a, a[0], a[1] + 1, image, needFill)) {
                    temp.add(new int[]{a[0], a[1] + 1});
                }

                if (needScan(a, a[0] + 1, a[1], image, needFill)) {
                    temp.add(new int[]{a[0] + 1, a[1]});
                }

                if (needScan(a, a[0], a[1] - 1, image, needFill)) {
                    temp.add(new int[]{a[0], a[1] - 1});
                }
            }
            needScan.clear();
            needScan.addAll(temp);
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (needFill[i][j])
                    image[i][j] = color;
            }
        }
        return image;
    }

    public boolean needScan(int[] a, int line, int column, int[][] image, boolean[][] needFill) {
        if (line < image.length && line >= 0 && column < image[0].length && column >= 0 && !needFill[line][column] && image[line][column] == image[a[0]][a[1]]) {
            return true;
        }
        return false;
    }
}