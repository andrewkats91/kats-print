package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;

public final class PaperPriceCalc
{
    public static int getJobPrice(Paper paper, Boolean isDoubleSided, int totalPagesCount, int colorPagesCount)
    {
        if(paper == null) return Price.PRICE_INVALID;

        int blackPagesCount = totalPagesCount - colorPagesCount;
        
        int totalPrice = 0;
        totalPrice += getPrice(paper.BLACK, isDoubleSided) * blackPagesCount;
        totalPrice += getPrice(paper.COLOR, isDoubleSided) * colorPagesCount;
        return totalPrice;
    }

    private static int getPrice(Price priceGroup, boolean isDoubleSided)
    {
        if(priceGroup == null) return Price.PRICE_INVALID;
        
        if(isDoubleSided) return priceGroup.DOUBLE;
        else return priceGroup.SINGLE;
    }
}