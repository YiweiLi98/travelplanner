package com.example.trip;


import com.example.trip.controller.TextExtractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.Matchers.hasItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextExtractorTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    private TextExtractor textExtractor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void textAnalysisTest() throws Exception {

        textExtractor = new TextExtractor();
        HashSet<String> result = textExtractor.textAnalysis("Today the Lord Nelson Brewery Hotel is Australia''s oldest pub brewery, " +
                "famous throughout the World for award-winning natural ales. " +
                "Brewed with only malt, hops, yeast and water (no added sugars, preservatives or additives), " +
                "ensuring 100 per cent natural beer.");
        Assert.assertThat(result,hasItem("food"));

    }

}
