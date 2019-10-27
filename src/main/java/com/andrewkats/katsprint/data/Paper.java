package com.andrewkats.katsprint.data;

public enum Paper
{
    // Add additional paper sizes here.
    // Use PRICE_INVALID to set as unavaliable.
    A4(Price.A4_BLACK_SINGLE, Price.A4_BLACK_DOUBLE, Price.A4_COLOR_SINGLE, Price.A4_COLOR_DOUBLE),
    NONE(Price.PRICE_INVALID, Price.PRICE_INVALID, Price.PRICE_INVALID, Price.PRICE_INVALID);

    // Add additional print types here.
    // NOTE: We are using "COLOR" to increase readability.
    public final String NAME;
    public final Type BLACK;
    public final Type COLOR;

    private Paper(int blackSingle, int blackDouble, int colorSingle, int colorDouble) 
    {
        NAME = name();
        this.BLACK = new Type(NAME + " BLACK", blackSingle, blackDouble);
        this.COLOR = new Type(NAME + " COLOR", colorSingle, colorDouble);
    }
    
    public final class Type
    {
        public final String NAME;
        public final Side SINGLE;
        public final Side DOUBLE;

        private Type(String NAME, int priceSingle, int priceDouble)
        {
            this.NAME = NAME;
            this.SINGLE = new Side(NAME + " SINGLE", priceSingle);
            this.DOUBLE = new Side(NAME + " DOUBLE", priceDouble);
        }
    
        public final class Side
        {
            public final String NAME;
            public final int PRICE;
    
            private Side(String NAME, int tPrice)
            {
                if(tPrice <= 0) tPrice = Price.PRICE_INVALID;
                this.NAME = NAME;
                this.PRICE = tPrice;
            }
        }
    }
}
