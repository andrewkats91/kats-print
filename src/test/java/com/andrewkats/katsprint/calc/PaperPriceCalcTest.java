package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;
import com.andrewkats.katsprint.data.PrintJob;

import org.junit.Assert;
import org.junit.Test;

public class PaperPriceCalcTest 
{
    @Test
    public void getJobPrice_standardJobFormat()
    {
        int expected = Paper.A4.BLACK.SINGLE.PRICE + Paper.A4.COLOR.SINGLE.PRICE;
        
        PrintJob testJob = new PrintJob();
        testJob.addTask(Paper.A4.BLACK, false, 1);
        testJob.addTask(Paper.A4.COLOR, false, 1);
        
        int actual = PaperPriceCalc.getJobPrice(testJob);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_nullCheck()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getJobPrice(null);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_invalidPageCount()
    {
        int expected = Price.PRICE_INVALID;
        
        PrintJob testJob = new PrintJob();
        testJob.addTask(Paper.A4.BLACK, false, -1);

        int actual = PaperPriceCalc.getJobPrice(testJob);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTaskPrice_invalidPrice()
    {
        PrintJob testJob = new PrintJob();
        testJob.addTask(Paper.NONE.BLACK, false, 1);
        testJob.addTask(Paper.A4.COLOR, false, 1);
        
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getTaskPrice(testJob.jobList().get(0));
        Assert.assertEquals(expected, actual);
    }
}
