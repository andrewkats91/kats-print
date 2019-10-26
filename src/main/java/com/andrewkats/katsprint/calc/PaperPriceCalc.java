package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;

public final class PaperPriceCalc
{
    public static int getJobPrice(Paper paper, Boolean isDoubleSided, int totalPagesCount, int colorPagesCount)
    {
        if(paper == null) return Price.PRICE_INVALID;

        int blackPagesCount = totalPagesCount - colorPagesCount;
        if(blackPagesCount < 0) return Price.PRICE_INVALID;
        
        int totalPrice = 0;
        totalPrice += getPrice(paper.BLACK, isDoubleSided) * blackPagesCount;
        totalPrice += getPrice(paper.COLOR, isDoubleSided) * colorPagesCount;
        if(totalPrice <= 0) return Price.PRICE_INVALID;
        return totalPrice;
    }

    private static int getPrice(Paper.Type paperType, boolean isDoubleSided)
    {
        if(paperType == null) return Price.PRICE_INVALID;
        
        if(isDoubleSided) return paperType.DOUBLE.PRICE;
        return paperType.SINGLE.PRICE;
    }
}