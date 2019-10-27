package com.andrewkats.katsprint;

import com.andrewkats.katsprint.calc.PaperPriceCalc;
import com.andrewkats.katsprint.data.Price;
import com.andrewkats.katsprint.parser.FileLoader;

public class App 
{
    public static void main(String[] args) 
    {
        // Default price for fail if nothing valid entered.
        int totalPrice = Price.PRICE_INVALID;
        

        // Argument handling
        if(args == null)
        {
            System.out.println("No input file entered. Please enter the target file as a parameter when executing the application.");
            return;
        }
        if(args.length == 0)
        {
            System.out.println("No input file entered. Please enter the target file as a parameter when executing the application.");
            return;
        }
        if(args.length >= 1)
        {
            totalPrice = PaperPriceCalc.getJobListPrice(new FileLoader().readPrintJobs(args[0]));
        }
        
        // Handle invalid outcome.
        if(totalPrice <= Price.PRICE_INVALID)
        {
            System.out.println("\nAn error has occured while processing. Please try again.");
            return;
        }
        
        // Write the total price of the job to console.
        float priceCents = totalPrice / Price.CENT_VALUE;
        float priceDollars = priceCents / 100f;
        String dollarsString = String.format("%.2f", priceDollars);
        System.out.println("Total Price: $" + dollarsString);
    }
}