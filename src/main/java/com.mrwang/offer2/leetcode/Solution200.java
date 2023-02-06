package com.mrwang.offer2.leetcode;
/*
给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

["1","1","1","1","0"],
["1","1","0","1","0"],
["1","1","0","0","0"],
["0","0","0","0","0"]


此外，你可以假设该网格的四条边均被水包围
 */


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Solution200 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("%E5%85%BB%E8%82%9D%E6%8A%A4%E8%82%9D%E8%83%B6%E5%9B%8A%F0%9F%92%8A", "UTF-8"));
    }
    public int numIslands(char[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                if ((i - 1 < 0 || grid[i - 1][j] == '0') && (i + 1 >= grid.length || grid[i + 1][j] == '0') && (j - 1 < 0 || grid[i][j - 1] == '0') && (j + 1 >= grid[0].length || grid[i][j + 1] == '0')) {
                    result++;
                }
            }
        }
        return result;
    }
}
