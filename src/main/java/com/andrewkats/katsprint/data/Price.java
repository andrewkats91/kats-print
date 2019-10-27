package com.andrewkats.katsprint.data;

public final class Price
{
    // Change internal paper pricing here.
    // When adding new fields, You will need to assign them to the target paper within the paper class.
    public final static int A4_BLACK_SINGLE = 15;
    public final static int A4_BLACK_DOUBLE = 10;
    public final static int A4_COLOR_SINGLE = 25;
    public final static int A4_COLOR_DOUBLE = 20;

    // Internal value per cent. Setting above 1 allow for values to be less than a cent.
    public final static int CENT_VALUE = 1;

    // The maximum valid price using internal value.
    public final static int MAX_PRICE = 1000000;

    // The value of an invalid price.
    public final static int PRICE_INVALID = -1;



    // Price Utility returns value in dollar format.
    public final static String toDollar(float price)
    {
        int formatVal = 1 + CENT_VALUE;
        if(formatVal > 4) formatVal = 4;

        float priceCents = price / Price.CENT_VALUE;
        float priceDollars = priceCents / 100f;
        return "$" + String.format("%." + formatVal + "f", priceDollars);
    }

    // Internal
    private Price()
    {
        throw new AssertionError();
    }
};