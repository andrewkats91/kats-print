package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;

import org.junit.Assert;
import org.junit.Test;

public class PaperPriceCalcTest 
{
    @Test
    public void getJobPrice_standardJobFormat()
    {
        int expected = Paper.A4.BLACK.SINGLE.PRICE + Paper.A4.COLOR.SINGLE.PRICE;
        
        Paper tPaper = Paper.A4;
        boolean isDoubleSided = false;
        int totalPages = 2;
        int colorPages = 1;
        int actual = PaperPriceCalc.getJobPrice(tPaper, isDoubleSided, totalPages, colorPages);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_nullCheck()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getJobPrice(null, false, 2, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_invalidPageCount()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getJobPrice(Paper.A4, false, 3, 5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_negativePageCount()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getJobPrice(Paper.A4, false, -2, -5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getJobPrice_invalidPrice()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getJobPrice(Paper.NONE, false, 2, 1);
        Assert.assertEquals(expected, actual);
    }
}
