package com.andrewkats.katsprint.data;

public final class Paper
{
    // Support for other paper sizes.
    public static enum Size
    {
        A4
    };

    // Support for special ink types.
    // NOTE: We are using "COLOR" to increase readability.
    public static enum Type
    {
        BLACK,
        COLOR
    };
}
