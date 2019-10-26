package com.andrewkats.katsprint.data;

import org.junit.Assert;
import org.junit.Test;

public class PaperTest 
{
    @Test
    public void test_noNullPaperType()
    {
        Paper.Type actual = Paper.NONE.BLACK; 
        Assert.assertNotNull(actual);
    }

    @Test
    public void test_noNullPaperTypePrice()
    {
        Paper.Type.Side actual = Paper.NONE.BLACK.SINGLE; 
        Assert.assertNotNull(actual);
    }

    @Test
    public void test_paperTypePriceInvalid()
    {
        int expected = Price.PRICE_INVALID;
        int actual = Paper.NONE.BLACK.SINGLE.PRICE; 
        Assert.assertEquals(expected, actual);
    }
}
