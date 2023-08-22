package com.example.trip.controller;

import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.*;
import com.textrazor.classifier.ClassifierManager;
import com.textrazor.classifier.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/text")
public class TextExtractor {

    private final String API_KEY =  "7aaf111a1b01bdf4bc0c34b75903aaa48eb1c9b3fd7d5d50439bd268"; //"96ae44141e745b345d27940eba7245c20833a98af3b799b8fed3b4de";
    private TextRazor client;
    private ClassifierManager manager;

    private String[] natureKeywords = {"nature", "park", "walk", "explore", "track", "nation", "cruise", "adventure", "attraction", "tourism", "animal","view","sea"};
    private String[] artKeywords = {"culture", "art", "urban", "modern"};
    private String[] foodAndDrinkKeywords = {"food", "drink", "restaurant", "meal", "coffee", "brew","gastronomy","diner", "coffee", "pub"};
    private String[] sportKeywords = {"sport","beach", "diving", "surf","adventure", "snorkel","boat", "kayak"};

    public TextExtractor() {
        this.client = new TextRazor(API_KEY);
        client.addExtractor("words");
    }

    public HashSet<String> textAnalysis(String description) throws NetworkException, AnalysisException {
        AnalyzedText response = client.analyze(description);
        HashSet<String> styles = new HashSet<String>(); // Set of matched topics
        for (Word topic : response.getResponse().getWords()) {
            String label = topic.getStem();
            if (Arrays.asList(natureKeywords).contains(label) & !styles.contains("nature")) {
                styles.add("nature");
                // System.out.println("Matched Topics: " + label);
            }
            if (Arrays.asList(sportKeywords).contains(label) & !styles.contains("sport")) {
                styles.add("sport");
            }
            if (Arrays.asList(artKeywords).contains(label) & !styles.contains("art")) {
                styles.add("art");
            }
            if (Arrays.asList(foodAndDrinkKeywords).contains(label) & !styles.contains("food")) {
                styles.add("food");
            }
        }
        return styles;
    }

}
