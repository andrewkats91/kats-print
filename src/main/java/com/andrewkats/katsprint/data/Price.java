package com.andrewkats.katsprint.data;

// Change internal pricing here.
// Use PRICE_INVALID to set a price as unavaliable.
public enum Price
{
    A4_BLACK (15, 10),
    A4_COLOR (25, 20);

    // Conversion ratio for a dollar.
    // Changing this can allow for more precision if smaller values are required.
    // Please use caution when changing this value. 
    public final static int CENT_VALUE = 1;

    // The maximum valid price.
    public final static int MAX_PRICE = 1000000;

    // The value of an invalid price.
    public final static int PRICE_INVALID = -1;
    

    // Price Internal
    private final int priceSingle;
    private final int priceDouble;

    private Price(int priceSingle, int priceDouble) 
    {
        this.priceSingle = priceSingle;
        this.priceDouble = priceDouble;
    }
    
    public int getPrice(Paper.Sided sided) 
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