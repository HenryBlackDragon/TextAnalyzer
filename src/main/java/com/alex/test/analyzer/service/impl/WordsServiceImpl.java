package com.alex.test.analyzer.service.impl;

import com.alex.test.analyzer.model.Word;
import com.alex.test.analyzer.service.WordsService;
import com.alex.test.analyzer.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WordsServiceImpl implements WordsService {

    @Override
    public List<Word> getListWords(String path) {
        List<String> wordsList = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\b[а-я|а-я\\-а-я]+\\b")
                .matcher(Util.readFile(path));

        while (matcher.find()) {
            wordsList.add(matcher.group());
        }

        List<String> startList = wordsList.stream()
                .distinct()
                .filter(i -> !(Util.getExcludeList().contains(i)))
                .collect(Collectors.toList());

        List<Word> endList = new ArrayList<>();

        startList.forEach(i -> endList.add(new Word(i, wordsList.stream()
                .filter(i::equals)
                .count())));
        endList.sort((elem1, elem2) -> elem2.getCount().compareTo(elem1.getCount()));

        return endList.stream().limit(10).collect(Collectors.toList());
    }


    @Override
    public String checkFile(String path) {
        Stack<String> stack = new Stack<>();

        Matcher matcher = Pattern.compile("[\\[\\](){}]+?")
                .matcher(Util.readFile(path));

        while (matcher.find()) {
            if (!stack.isEmpty() && stack.peek().equals(Util.getInverseSign(matcher.group()))) {
                stack.pop();
            } else stack.push(matcher.group());
        }

        if (stack.isEmpty()) {
            return "Correct";
        } else return "Incorrect";
    }
}
