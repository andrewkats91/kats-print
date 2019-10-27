package com.andrewkats.katsprint.parser;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.PrintJob;
import com.andrewkats.katsprint.data.PrintJob.PrintTask;

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

    // Tools for testing.
    private boolean compareJobLists(List<PrintJob> jobListA, List<PrintJob> jobListB)
    {
        int testSize = jobListA.size();
        if(testSize != jobListB.size()) 
        {
            System.out.println("FAILED | compareJobLists: Different sizes detected");
            return false;
        }
        
        for(int i = 0; i < testSize; i++)
        {
            if(!compareJobs(jobListA.get(i), jobListB.get(i)))
            {
                return false;
            }
        }

        return true;
    }
    private boolean compareJobs(PrintJob jobA, PrintJob jobB)
    {
        int testSize = jobA.jobList().size();
        if(testSize != jobB.jobList().size())
        {
            System.out.println("FAILED | compareJobs: Different sizes detected");
            return false;
        }

        for(int i = 0; i < testSize; i++)
        {
            if(!compareTasks(jobA.jobList().get(i), jobB.jobList().get(i)))
            {
                System.out.println("While comparing jobs, task comparison failed at index: " + i);
            }
        }

        System.out.println("Comparing jobs success!");
        return true;
    }

    private boolean compareTasks(PrintTask taskA, PrintTask taskB)
    {
        if(taskA.paperType() != taskB.paperType()) return false;
        if(taskA.isDoubleSided() != taskB.isDoubleSided()) return false;
        if(taskA.pageCount() != taskB.pageCount()) return false;

        return true;
    }

    // Tests
    @Test
    public void test_handleInputNull() 
    {
        List<PrintJob> actual = new ParserCSV().parseData(null);
        Assert.assertNull(actual);
    }

    @Test
    public void test_handleInputNullListElements() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("Total Pages, Color Pages, Double Sided");
        testData.add(null);
        testData.add(null);
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");
        testData.add(null);
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }

    @Test
    public void test_handleInputEmptyList() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("");
        testData.add("Total Pages, Color Pages, Double Sided");
        testData.add("");
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");
        testData.add("");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }

    // Included bad columns should be ignored.
    @Test
    public void test_handleInputBadData() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("Total Pages, Color Pages, Double Sided");
        testData.add("       Test,     Invalid,      3Words1");
        testData.add("   TesTRUEt,   3Wo44rds1,      3Words1");
        testData.add("         20,          10,        false");
        testData.add("          2,           1,         TRUE");
        testData.add(" 2 TesTRUEt,   3Wo44rds1,  3TRUEWords1");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }

    // Regular Input but with extra detected columns
    @Test
    public void test_handleInputEmptyColumns() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("Total Pages, , , Color Pages, Double Sided");
        testData.add("20, , , 10, false, ");
        testData.add(" 2, , ,  1,  TRUE, ");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }


    // Regular Input
    @Test
    public void test_handleInputMissingHeader() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("20, 10, false");
        testData.add(" 2,  1,  TRUE");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertNull(jobList);
    }

    @Test
    public void test_handleInputHeader() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("Total Pages, Color Pages, Double Sided");
        testData.add("         20,          10,        false");
        testData.add("          2,           1,         TRUE");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }

    // Including a header
    @Test
    public void test_handleInputPageSizeHeader() 
    {
        List<String> testData = new ArrayList<String>();
        testData.add("Total Pages, Color Pages, Double Sided, Page Size");
        testData.add("         20,          10,        false,      NONE");
        testData.add("          2,           1,         TRUE,      NONE");
        testData.add("         20,          10,        false,        A4");
        testData.add("          2,           1,         TRUE,        A4");
        testData.add("         20,          10,        false,          ");

        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }

    // Input: Shuffled and Header
    @Test
    public void test_handleInputShuffledHeader() 
    {
        List<String> testData = new ArrayList<>();
        testData.add("Color Pages, Double Sided, Total Pages");
        testData.add("         10,        false,          20");
        testData.add("          1,         TRUE,           2");
        
        List<PrintJob> jobList = new ParserCSV().parseData(testData);
        Assert.assertTrue(compareJobLists(getPrintJobList(), jobList));
    }
}
