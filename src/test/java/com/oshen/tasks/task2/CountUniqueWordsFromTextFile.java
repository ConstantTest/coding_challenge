package com.oshen.tasks.task2;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class CountUniqueWordsFromTextFile {

    @Test
    public void countUniqueWordsFromTextFileTest() throws IOException, URISyntaxException {
        URI fileUri = getClass().getClassLoader().getResource("input.txt").toURI();
        Path filePath = Paths.get(fileUri);
        Map<String, Integer> uniqueWords = StreamMain.findUniqueWords(filePath);

        assertTrue(!uniqueWords.isEmpty());
        assertFalse(uniqueWords.containsKey(" "));
        assertFalse(uniqueWords.containsKey(""));
        assertFalse(uniqueWords.containsKey("?"));
        assertFalse(uniqueWords.containsKey(","));
        assertFalse(uniqueWords.containsKey("1"));

        assertEquals(2, uniqueWords.get("friend").intValue());
        assertEquals(2, uniqueWords.get("the").intValue());
    }
}
