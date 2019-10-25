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
        int actual = PaperPriceCalc.getPrice(null, null, null);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPriceTargeted()
    {
        int expected = Price.A4_COLOR.getPrice(Paper.Sided.SINGLE);
        int actual = PaperPriceCalc.getPrice(Paper.Size.A4, Paper.Type.COLOR, Paper.Sided.SINGLE);
        Assert.assertEquals(expected, actual);
    }
}
