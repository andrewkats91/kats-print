package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Price;

public final class PaperPriceCalc
{
    public static int getPrice(Price priceGroup, boolean isDoubleSided)
    {
        if(priceGroup == null) return Price.PRICE_INVALID;
        
        if(isDoubleSided) return priceGroup.DOUBLE;
        else return priceGroup.SINGLE;
    }
}