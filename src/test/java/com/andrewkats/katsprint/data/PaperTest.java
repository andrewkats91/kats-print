package com.andrewkats.katsprint.data;

import org.junit.Assert;
import org.junit.Test;

public class PaperTest 
{
    @Test
    public void test_noPaperPrice()
    {
        Assert.assertEquals(Price.NONE, Paper.NONE.BLACK);
    }

    @Test
    public void test_nullPaper()
    {
        Assert.assertNull(Paper.NULL.BLACK);
    }
}
