package io.github.lap1597;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {
    public static int[][] loadMap() {
        try (BufferedReader br = new BufferedReader(new FileReader("MapResource/Background.csv"))) {
            return br.lines()
                .map(line -> line.split(","))
                .map(strings -> {
                    int[] row = new int[strings.length];
                    for (int i = 0; i < strings.length; i++) {
                        row[i] = Integer.parseInt(strings[i].trim());
                    }
                    return row;
                })
                .toArray(int[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0][0];
        }
    }
}
