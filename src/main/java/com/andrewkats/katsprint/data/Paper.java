package com.andrewkats.katsprint.data;

public final class Paper
{
    public final static int PRICE_INVALID = -1;


    // Support for other paper sizes.
    public static enum Size
    {
        A4,
        A3
    };

    // This is an enum for readability.
    public static enum Sided
    {
        SINGLE,
        DOUBLE
    };

    // Support for special ink types.
    public static enum Type
    {
        BLACKWHITE,
        COLOR
    };

    // Use PRICE_INVALID to set as unavaliable.
    public static enum Price
    {
        A4_BLACK (15, 10),
        A4_COLOR (25, 20);

        private final int priceSingle;
        private final int priceDouble;
    
        private Price(int priceSingle, int priceDouble) 
        {
            this.priceSingle = priceSingle;
            this.priceDouble = priceDouble;
        }
        
        public int getPrice(Sided sided) 
        {
            if(sided == null) return PRICE_INVALID;

            switch(sided)
            {
                case SINGLE: return priceSingle;
                case DOUBLE: return priceDouble;
                default: return PRICE_INVALID;
            }
        }
    };
}
