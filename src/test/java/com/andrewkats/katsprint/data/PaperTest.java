package com.andrewkats.katsprint.data;

import org.junit.Assert;
import org.junit.Test;

public class PaperTest 
{
    @Test
    public void testGetPriceInvalid()
    {
        int expected = Paper.PRICE_INVALID;
        int actual = Paper.Price.A4_BLACK.getPrice(null);
        Assert.assertEquals(expected, actual);
    }
}
