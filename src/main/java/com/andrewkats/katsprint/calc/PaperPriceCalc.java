package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;

public final class PaperPriceCalc
{
    public static int getPrice(Paper.Size size, Paper.Type type, Boolean isDoubleSided)
    {
        if(size == null || type == null) return Price.PRICE_INVALID;
        
        switch(size)
        {
            case A4: return getPriceA4(type, isDoubleSided);
            default: return Price.PRICE_INVALID;
        }
    }

    private static int getPriceA4(Paper.Type type, Boolean isDoubleSided)
    {
        switch(type)
        {
            case BLACK: return getPriceSided(Price.A4_BLACK, isDoubleSided);
            case COLOR: return getPriceSided(Price.A4_COLOR, isDoubleSided);
            default: return Price.PRICE_INVALID;
        }
    }
    
    private static int getPriceSided(Price price,  boolean isDoubleSided) 
    {
        if(isDoubleSided) return price.DOUBLE;
        else return price.SINGLE;
    }
}