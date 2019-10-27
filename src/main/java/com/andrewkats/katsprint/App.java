package com.andrewkats.katsprint;

import com.andrewkats.katsprint.calc.PaperPriceCalc;
import com.andrewkats.katsprint.calc.PrintJobInfoLog;
import com.andrewkats.katsprint.parser.FileLoader;

public class App 
{
    public static void main(String[] args) 
    {
        String filePath = null;

        // Argument handling
        if(args != null)
        {
            if(args.length >= 1) filePath = args[0];
            if(args.length >= 2) PrintJobInfoLog.setLogLevelByString(args[1]);
        }
        
        // Load and process the target file.
        PaperPriceCalc.getJobListPrice(new FileLoader().readPrintJobs(filePath));
    }
}