package com.alex.test.analyzer.controller;

import com.alex.test.analyzer.model.Word;
import com.alex.test.analyzer.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import java.util.List;

@Controller
@RequestMapping("/analyzer")
public class AnalyzerController {

    @Autowired
    private WordsService wordsService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Word> getWords(@QueryParam("path") String path) {
        return wordsService.getListWords(path);
    }

    @RequestMapping(path = "/check", method = RequestMethod.GET)
    @ResponseBody
    public String getCheckFile(@QueryParam("path") String path) {
        return wordsService.checkFile(path);
    }
}
