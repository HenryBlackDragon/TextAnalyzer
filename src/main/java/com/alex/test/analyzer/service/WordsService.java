package com.alex.test.analyzer.service;

import com.alex.test.analyzer.model.Word;

import java.util.List;

public interface WordsService {

    List<Word> getListWords(String path);

    String checkFile(String path);
}
