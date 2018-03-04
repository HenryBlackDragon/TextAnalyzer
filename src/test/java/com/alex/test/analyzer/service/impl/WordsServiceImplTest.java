package com.alex.test.analyzer.service.impl;

import com.alex.test.analyzer.model.Word;
import com.alex.test.analyzer.service.WordsService;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class WordsServiceImplTest {

    private WordsService wordsService;

    @Before
    public void setUp() throws Exception {
        wordsService = new WordsServiceImpl();
    }

    @Test
    public void getListWordsTest() throws URISyntaxException {
        List<Word> result = wordsService.getListWords(String.valueOf(
                Paths.get(ClassLoader.getSystemResource("test_text.txt").toURI())));
        assertNotNull(result);
        assertEquals("это", result.get(0).getName());
        assertEquals("1", result.get(0).getCount().toString());
        assertEquals("тестовый", result.get(1).getName());
        assertEquals("1", result.get(1).getCount().toString());
        assertEquals("файл", result.get(2).getName());
        assertEquals("1", result.get(2).getCount().toString());
    }

    @Test
    public void checkFileTest() throws URISyntaxException {
        String result = wordsService.checkFile(String.valueOf(
                Paths.get(ClassLoader.getSystemResource("test_text.txt").toURI())));
        assertNotNull(result);
        assertEquals("Correct", result);
    }
}