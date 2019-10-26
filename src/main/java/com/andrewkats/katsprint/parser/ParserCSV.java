package com.andrewkats.katsprint.parser;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.PrintJob;

import java.util.ArrayList;
import java.util.List;

public class ParserCSV
{
    private List<PrintJob> jobList;

    public List<PrintJob> parseData(List<String> stringData) 
    {
        jobList = new ArrayList<PrintJob>();

        for(int i = 0; i < stringData.size(); i++)
        {
            String line = stringData.get(i);
            if(isValidFormat(line))
            {
                String[] data = line.split(",");
                cleanUpData(data);
                addJob(data);
            }
        }

        return jobList;
    }

    private boolean isValidFormat(String inData)
    {
        if(inData == null) return false;
        return inData.contains(",");
    }

    void cleanUpData(String[] data)
    {
        // For our numeric columns, always remove other characters.
        data[0] = data[0].replaceAll("[^\\d.]", "");
        data[1] = data[1].replaceAll("[^\\d.]", "");

        // For double sided check, remove any extra spaces.
        data[2] = data[2].replaceAll("\\s+","");
    }

    private boolean addJob(String[] data)
    {
        // Ensure the changed data is still valid.
        if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty()) return false;

        // Check if true false or invalid.
        int dSidedCheck = parseDoubleSided(data[2]);
        if(dSidedCheck <= -1) return false;



        // Begin producing valid job data.
        Paper paper = Paper.A4;
        int totalPages = 0; 
        int colorPages = 0; 
        boolean isDoubleSided = dSidedCheck == 1;

        // Try and parse in our ints.
        try
        {
            // Get all required information.
            totalPages = Integer.parseInt(data[0]);
            colorPages = Integer.parseInt(data[1]);

        }
        catch(NumberFormatException e)
        {
            return false;
        }


        // Add individual tasks to build the print job.
        PrintJob nPrintJob = new PrintJob();
        
        // Add a task for black pages.
        int blackPages = totalPages - colorPages;
        nPrintJob.addTask(paper.BLACK, isDoubleSided, blackPages);

        // Add a task for color pages.
        nPrintJob.addTask(paper.COLOR, isDoubleSided, colorPages);

        return true;
    }

    // Values to look for when setting double sided.
    private int parseDoubleSided(String val)
    {
        switch(val.toUpperCase())
        {
            case "TRUE":
            case "YES":
            case "DOUBLE":
                return 1;

            case "FALSE":
            case "NO":
            case "SINGLE":
                return 0;

            default:
                return -1;
        }
    }
}