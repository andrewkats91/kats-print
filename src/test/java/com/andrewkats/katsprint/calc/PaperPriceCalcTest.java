package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;

import org.junit.Assert;
import org.junit.Test;

public class PaperPriceCalcTest 
{
    // Test focus: getPrice
    @Test
    public void testGetPriceNullCheck()
    {
        int expected = Price.PRICE_INVALID;
        int actual = PaperPriceCalc.getPrice(null, false);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPriceTargeted()
    {
        int expected = Price.A4_COLOR.SINGLE;
        int actual = PaperPriceCalc.getPrice(Paper.A4.COLOR, false);
        Assert.assertEquals(expected, actual);
    }
}
