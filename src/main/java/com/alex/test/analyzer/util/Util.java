package com.alex.test.analyzer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private static List<String> excludeWords = exclude();

    private static List<String> exclude() {
        List<String> excludeWords = new ArrayList<>();
        File file = new File(Util.class.getResource("/exclude_words.txt").getFile());

        try (BufferedReader bfdReader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = bfdReader.readLine()) != null) {
                excludeWords.addAll(Arrays.stream(str.split(",")).collect(Collectors.toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excludeWords;
    }

    public static List<String> getExcludeList() {
        return excludeWords;
    }

    public static String readFile() {
        return null;
    }

    public static String getInverseSign(String sign) {
        if ("]".equals(sign)) {
            return "[";
        } else if ("}".equals(sign)) {
            return "{";
        } else if (")".equals(sign)) {
            return "(";
        } else return "";
    }
}
