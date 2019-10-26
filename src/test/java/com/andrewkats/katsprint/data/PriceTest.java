package com.andrewkats.katsprint.data;

import org.junit.Test;

public class PriceTest 
{
    @Test
    public void printCalculatedValues()
    {
        System.out.println("Internal MAX_PRICE: " + Price.MAX_PRICE);
        float maxPriceCents = Price.MAX_PRICE / Price.CENT_VALUE;
        float maxPriceDollars = maxPriceCents / 100f;

        System.out.println("Max price in dollars: $" + maxPriceDollars);
    }
}
