package com.alex.test.analyzer.controller;

import com.alex.test.analyzer.model.Word;
import com.alex.test.analyzer.service.WordsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerControllerTest {

    @Mock
    private WordsService mock;

    @InjectMocks
    private AnalyzerController analyzerController;

    @Before
    public void setUp() throws Exception {
        reset(mock);
    }

    @Test
    public void getWordsTest() {
        String path = "test.txt";

        when(mock.getListWords(path)).thenReturn(Collections.singletonList(new Word("test", 1L)));

        List<Word> result = analyzerController.getWords(path);

        verify(mock).getListWords(path);

        assertNotNull(result);
        assertEquals("test", result.get(0).getName());
        assertEquals("1", result.get(0).getCount().toString());
    }

    @Test
    public void getWordsEmptyListTest() {
        String path = "test.txt";

        when(mock.getListWords(path)).thenReturn(new ArrayList<>());

        List<Word> result = analyzerController.getWords(path);

        verify(mock).getListWords(path);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCheckFileCorrectTest() {
        String path = "test.txt";

        when(mock.checkFile(path)).thenReturn("Correct");

        String result = analyzerController.getCheckFile(path);

        verify(mock).checkFile(path);

        assertNotNull(result);
        assertEquals("Correct", result);
    }

    @Test
    public void getCheckFileIncorrectTest() {
        String path = "test.txt";

        when(mock.checkFile(path)).thenReturn("Incorrect");

        String result = analyzerController.getCheckFile(path);

        verify(mock).checkFile(path);

        assertNotNull(result);
        assertEquals("Incorrect", result);
    }
}