package com.andrewkats.katsprint.calc;

import com.andrewkats.katsprint.data.Price;
import com.andrewkats.katsprint.data.PrintJob;
import com.andrewkats.katsprint.data.PrintJob.PrintTask;

import java.util.List;

public final class PrintJobInfoLog 
{
    public static int logLevel = 0;
    
    public static void setLogLevelByString (String logLevelStr)
    {
        if(logLevelStr == null)
        {
            logLevel = 0;
            return;
        }
        
        if(logLevelStr.contains("0")) logLevel = 0;
        if(logLevelStr.contains("1")) logLevel = 1;
        if(logLevelStr.contains("2")) logLevel = 2;
    }

    public static void logJobListInfo(List<PrintJob> printJobList)
    {
        if(logLevel >= 2) return;

        System.out.println(" ");
        System.out.println("JOB LIST - Jobs: " + printJobList.size());
        System.out.println("========================================");
    }

    public static void logJobListInfoEnd(List<PrintJob> printJobList, int jobListPrice)
    { 
        if(logLevel >= 2)
        {
            System.out.println("-----------");
            System.out.println("Total Price: " + Price.toDollar(jobListPrice));
            return;
        }

        System.out.println("========================================");
        System.out.println("Total Price: " + Price.toDollar(jobListPrice));
    }

    public static void logJobInfo(int jobIndex, PrintJob printJob)
    { 
        if(logLevel >= 2) return;
        if(logLevel >= 1) return;

        System.out.println("JOB " + (jobIndex + 1) + " - Tasks: " + printJob.taskList().size());
        System.out.println("----------------------------------------");
    }

    public static void logJobInfoEnd(int jobIndex, PrintJob printJob, int jobPrice)
    { 
        if(logLevel >= 2) return;
        if(logLevel >= 1) 
        {
            System.out.println("Job " + (jobIndex + 1) + ": " +  Price.toDollar(jobPrice));
            return;
        }

        System.out.println("  - JOB " + (jobIndex + 1) + " COST: " +  Price.toDollar(jobPrice));
    }
    
    public static void logTaskInfo(int taskIndex, PrintTask printTask) 
    { 
        if(logLevel >= 1) return;
        System.out.println("TASK " + (taskIndex + 1) + ": " + logPaperInfo(printTask));
    }

    private static String logPaperInfo(PrintTask printTask)
    {
        String oStr = "";
        int price = 0;
        
        if(printTask.isDoubleSided())
        {
            price = printTask.paperType().DOUBLE.PRICE;
            oStr = oStr + printTask.paperType().DOUBLE.NAME + "-SIDED ";

        } 
        else 
        {
            price = printTask.paperType().SINGLE.PRICE;
            oStr = oStr + printTask.paperType().SINGLE.NAME + "-SIDED ";
        } 

        oStr = oStr + "(" + Price.toDollar(price) + ")";

        oStr = oStr + "  ";
        oStr = oStr + "Count: " + printTask.pageCount();

        oStr = oStr + "  ";
        oStr = oStr + "Cost: " + Price.toDollar(printTask.pageCount() * price);

        return oStr;
    }
}