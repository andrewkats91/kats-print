package com.andrewkats.katsprint;

import com.andrewkats.katsprint.calc.PaperPriceCalc;
import com.andrewkats.katsprint.calc.PrintJobInfoLog;
import com.andrewkats.katsprint.data.Price;
import com.andrewkats.katsprint.parser.FileLoader;

public class App 
{
    public static void main(String[] args) 
    {
        // Default price for fail if nothing valid entered.
        int totalPrice = Price.PRICE_INVALID;
        String filePath = null;

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
            filePath = args[0];
        }
        if(args.length >= 2)
        {
            if(args[1].contains("0")) PrintJobInfoLog.logLevel = 0;
            if(args[1].contains("1")) PrintJobInfoLog.logLevel = 1;
            if(args[1].contains("2")) PrintJobInfoLog.logLevel = 2;
        }

        // Load and process the target file.
        System.out.println(" ");
        totalPrice = PaperPriceCalc.getJobListPrice(new FileLoader().readPrintJobs(filePath));
    }
}