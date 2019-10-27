package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.Price;
import com.andrewkats.katsprint.data.PrintJob;
import com.andrewkats.katsprint.data.PrintJob.PrintTask;

import java.util.List;

public final class PaperPriceCalc
{
    public static int getJobListPrice(List<PrintJob> printJobList) 
    {
        if(printJobList == null) return Price.PRICE_INVALID;

        int jobListSize = printJobList.size();
        if(jobListSize <= 0) return Price.PRICE_INVALID;

        int totalPrice = 0;
        for(int i = 0; i < jobListSize; i++)
        {
            PrintJob tPrintJob = printJobList.get(i);
            int jobPrice = getJobPrice(tPrintJob);
            totalPrice += jobPrice;

            // Perform a validation check to ensure correct data.
            if(jobPrice <= 0 || jobPrice == Price.PRICE_INVALID) return Price.PRICE_INVALID;
        }
        return totalPrice; 
    }

    protected static int getJobPrice(PrintJob printJob) 
    {
        if(printJob == null) return Price.PRICE_INVALID;

        int jobSize = printJob.taskList().size();
        if(jobSize <= 0) return Price.PRICE_INVALID;

        int totalPrice = 0;
        for(int i = 0; i < jobSize; i++)
        {
            PrintTask tPrintTask = printJob.taskList().get(i);
            int paperPrice = getTaskPrice(tPrintTask);
            totalPrice += paperPrice;

            // Perform a validation check to ensure correct data.
            if(paperPrice <= 0 || paperPrice == Price.PRICE_INVALID) return Price.PRICE_INVALID;
        }
        return totalPrice;
    }
    
    protected static int getTaskPrice(PrintTask printTask) 
    { 
        if(printTask == null) return Price.PRICE_INVALID;
        if(printTask.pageCount() <= 0) return Price.PRICE_INVALID;

        int paperPrice = getPrice(printTask.paperType(), printTask.isDoubleSided());
        if(paperPrice <= 0 || paperPrice == Price.PRICE_INVALID) return Price.PRICE_INVALID;
        
        return paperPrice * printTask.pageCount();
    }

    private static int getPrice(Paper.Type paperType, boolean isDoubleSided)
    {
        if(paperType == null) return Price.PRICE_INVALID;
        
        if(isDoubleSided) return paperType.DOUBLE.PRICE;
        return paperType.SINGLE.PRICE;
    }
}