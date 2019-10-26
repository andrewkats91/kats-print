package com.andrewkats.katsprint.parser;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class FileLoaderTest
{
    @Test
    public void test_handleEmptyFilePath() 
    {
        FileLoader fileLoader = new FileLoader();
        assertNull(fileLoader.readPrintJobs(""));
    }
}
