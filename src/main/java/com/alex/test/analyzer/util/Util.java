package com.alex.test.analyzer.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private static List<String> excludeWords = exclude();

    // создание списка, состоящего из союзов, предлогов, местоимений
    private static List<String> exclude() {
        List<String> excludeWords = new ArrayList<>();

        try {
            String txt = new String(Files.readAllBytes(
                    Paths.get(ClassLoader.getSystemResource("exclude_words.txt").toURI())));
            excludeWords.addAll(Arrays.stream(txt.split(",")).collect(Collectors.toList()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return excludeWords;
    }

    public static List<String> getExcludeList() {
        return excludeWords;
    }

    public static String readFile(String path) {
        String result = "";

        // считывание файла, если по указанному пути существует файл,
        // то считывает его, иначе считывает из папки ресурсов
        try {
            if (Files.isReadable(Paths.get(path))) {
                result = new String(Files.readAllBytes(Paths.get(path)));
            } else {
                result = new String(Files.readAllBytes(
                        Paths.get(ClassLoader.getSystemResource("test_text.txt").toURI())));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return result.toLowerCase();
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
