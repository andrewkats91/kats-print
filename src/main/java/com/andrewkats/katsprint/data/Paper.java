package com.andrewkats.katsprint.data;

public enum Paper
{
    // Support for other paper sizes.
    A4(Price.A4_BLACK, Price.A4_COLOR),
    NONE(Price.NONE, Price.NONE),
    NULL(null, null);

    // Support for special ink types.
    // NOTE: We are using "COLOR" to increase readability.
    public final Price BLACK;
    public final Price COLOR;

    Paper(Price black, Price color) 
    {
        this.BLACK = black;
        this.COLOR = color;
    }
}
