package com.andrewkats.katsprint.parser;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.PrintJob;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ParserCSVTest 
{
    // Example Data for tests.
    private List<PrintJob> getPrintJobList() 
    {
        List<PrintJob> expected = new ArrayList<>();
        
        PrintJob jobA = new PrintJob();
        jobA.addTask(Paper.A4.BLACK, false, 10);
        jobA.addTask(Paper.A4.COLOR, false, 10);
        expected.add(jobA);

        PrintJob jobB = new PrintJob();
        jobB.addTask(Paper.A4.BLACK, true, 1);
        jobB.addTask(Paper.A4.COLOR, true, 1);
        expected.add(jobB);

        return expected;
    }

    @Test
    public void test_handleInputNull() 
    {
        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(null);
        Assert.assertNull(actual);
    }

    @Test
    public void test_handleInputNullListElements() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add(null);
        testData.add(null);
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");
        testData.add(null);

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_handleInputEmptyList() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("");
        testData.add("");
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");
        testData.add("");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Included bad columns should be ignored.
    @Test
    public void test_handleInputBadData() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("      Test,   Invalid,     3Words1");
        testData.add("  TesTRUEt, 3Wo44rds1,     3Words1");
        testData.add("        20,        10,       false");
        testData.add("         2,         1,        TRUE");
        testData.add("2 TesTRUEt, 3Wo44rds1, 3TRUEWords1");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Regular Input but with extra detected columns
    @Test
    public void test_handleInputEmptyColumns() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("20, , , 10, false, ");
        testData.add(" 2, , ,  1,  TRUE, ");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }


    // Regular Input
    @Test
    public void test_handleInput() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_handleInputHeader() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("Total Pages, Color Pages, Double Sided");
        testData.add("         20,          10,        false");
        testData.add("          2,           1,         TRUE");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Input: Added page size column. NONE and empty columns should be ignored.
    // But only ignore empty when the size column is detected.
    @Test
    public void test_handleInputPageSize() 
    {
        List<PrintJob> expected = getPrintJobList();
        
        List<String> testData = new ArrayList<String>();
        testData.add("20, 10, false, NONE");
        testData.add(" 2,  1,  TRUE, NONE");
        testData.add("20, 10, false,   A4");
        testData.add(" 2,  1,  TRUE,   A4");
        testData.add("20, 10, false,     ");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Including a header
    @Test
    public void test_handleInputPageSizeHeader() 
    {
        List<PrintJob> expected = getPrintJobList();
        
        List<String> testData = new ArrayList<String>();
        testData.add("Total Pages, Color Pages, Double Sided, Page Size");
        testData.add("         20,          10,        false,      NONE");
        testData.add("          2,           1,         TRUE,      NONE");
        testData.add("         20,          10,        false,        A4");
        testData.add("          2,           1,         TRUE,        A4");
        testData.add("         20,          10,        false,          ");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Input: Shuffled Columns
    @Test
    public void test_handleInputShuffled() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("10, false, 20");
        testData.add(" 1,  TRUE,  2");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Input: Shuffled and Header
    @Test
    public void test_handleInputShuffledHeader() 
    {
        List<PrintJob> expected = getPrintJobList();

        List<String> testData = new ArrayList<>();
        testData.add("Color Pages, Double Sided, Total Pages");
        testData.add("         10,        false,          20");
        testData.add("          1,         TRUE,           2");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Input: Shuffled and Page Size
    @Test
    public void test_handleInputShuffledPageSize() 
    {
        List<PrintJob> expected = getPrintJobList();
        
        List<String> testData = new ArrayList<String>();
        testData.add("NONE, false,    20, 10");
        testData.add("NONE,  TRUE,     2,  1");
        testData.add("  A4, false,    20, 10");
        testData.add("  A4,  TRUE,     2,  1");
        testData.add("    , false,    20, 10");

        ParserCSV parserCSV = new ParserCSV();
        List<PrintJob> actual = parserCSV.parseData(testData);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    // Clean up data function test
    @Test
    public void test_cleanUpData() 
    {
        String[] expected = {"1", "3441", "3Word1s"};

        String testData =  "  TesTRUEt1, 3Wo44rds1,     3Wo rd1s";
        String[] actual = testData.split(",");
        ParserCSV parserCSV = new ParserCSV();
        parserCSV.cleanUpData(actual);
        
        Assert.assertArrayEquals(expected, actual);
    }
}
