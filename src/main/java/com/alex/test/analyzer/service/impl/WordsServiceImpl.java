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

        // поиск слов по заданному регулярному выражению (учитывает переносы)
        Matcher matcher = Pattern.compile("\\b[а-я|а-я\\-а-я]+\\b")
                .matcher(Util.readFile(path));

        while (matcher.find()) {
            wordsList.add(matcher.group());
        }

        // создает список строк, с помощью лямбда выражения удаляет повторяющиеся
        // и исключает союзы, предлоги, местоимения
        List<String> startList = wordsList.stream()
                .distinct()
                .filter(i -> !(Util.getExcludeList().contains(i)))
                .collect(Collectors.toList());

        List<Word> endList = new ArrayList<>();

        // пробегается по списку, добавляет слово в конечный список и ищет сколько раз повторяется слово
        startList.forEach(i -> endList.add(new Word(i, wordsList.stream()
                .filter(i::equals)
                .count())));
        // сортировка в обратном порядке
        endList.sort((elem1, elem2) -> elem2.getCount().compareTo(elem1.getCount()));

        // вывод первых 10 элементов
        return endList.stream().limit(10).collect(Collectors.toList());
    }


    @Override
    public String checkFile(String path) {
        Stack<String> stack = new Stack<>();

        // поиск символов []{}() по заданному регулярному выражению
        Matcher matcher = Pattern.compile("[\\[\\](){}]+?")
                .matcher(Util.readFile(path));

        // работа со стеком, ищет первый символ, если стек пустой,
        // то добавляет в стек, иначе берет последний и проверяет на сходство,
        // если символы равны, то удаляет верхний в стеке
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
