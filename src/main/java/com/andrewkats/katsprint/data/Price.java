package com.andrewkats.katsprint.data;

public final class Price
{
    // Conversion ratio for a dollar.
    // Changing this can allow for more precision if smaller values are required.
    public final static int CENT_VALUE = 1;

    // The maximum valid price.
    public final static int MAX_PRICE = 1000000;

    // The value of an invalid price.
    public final static int PRICE_INVALID = -1;

    // Change internal paper pricing here.
    // Use PRICE_INVALID to set a price as unavaliable.
    public final static Price A4_BLACK = new Price(15, 10);
    public final static Price A4_COLOR = new Price(25, 20);
    public final static Price NONE = new Price(PRICE_INVALID, PRICE_INVALID);
    

    // Price Internal
    public final int SINGLE;
    public final int DOUBLE;

    private Price(int SINGLE, int DOUBLE) 
    {
        this.SINGLE = SINGLE;
        this.DOUBLE = DOUBLE;
    }
};