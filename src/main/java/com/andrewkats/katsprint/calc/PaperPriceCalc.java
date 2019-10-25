package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;

public final class PaperPriceCalc
{
    public static int getPrice(Paper.Size size, Paper.Type type, Paper.Sided sided)
    {
        if(size == null ||type == null || sided == null) return Paper.PRICE_INVALID;
        
        switch(size)
        {
            case A4: return getPriceA4(type, sided);
            default: return Paper.PRICE_INVALID;
        }
    }

    private static int getPriceA4(Paper.Type type, Paper.Sided sided)
    {
        switch(type)
        {
            case BLACKWHITE: return Paper.Price.A4_BLACK.getPrice(sided);
            case COLOR: return Paper.Price.A4_COLOR.getPrice(sided);
            default: return Paper.PRICE_INVALID;
        }
    }
}