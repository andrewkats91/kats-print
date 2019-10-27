package com.andrewkats.katsprint.parser;

import com.andrewkats.katsprint.data.Paper;
import com.andrewkats.katsprint.data.PrintJob;

import java.util.ArrayList;
import java.util.List;

public class ParserCSV
{
    private List<PrintJob> jobList;
    private int columnTotalIndex;
    private int columnColorIndex;
    private int columnSidedIndex;
    private int columnSizesIndex;


    public List<PrintJob> parseData(List<String> stringData) 
    {
        if(stringData == null) return null;

        // Ensure data is reset.
        resetParser();

        // Ensure CSV input data is split correctly.
        List<String[]> dataSet = splitCSVLines(stringData);

        // Try and allocate columns.
        String[] line = dataSet.get(0);
        columnSearchHeader(line);

        // If we failed to find the 3 key columns, return null.
        if(columnTotalIndex == -1 || columnColorIndex == -1 || columnSidedIndex == -1)
        {
            return null;
        }

        // If we couldnt find the size column, then prompt the user about using the default size.
        if(columnSizesIndex == -1)
        {
            String oStr = JobConsole.readLine("Warning: No paper size detected. Will fall back to default: " + Paper.DEFAULT_SIZE.NAME + ". Type N to cancel: ");
            
            if(oStr != null)
            {
                if(oStr.toUpperCase().contains("N")) 
                {
                    System.out.println("Job processing cancelled.");
                    return null;
                }
            } 
        }

        // Attempt to add a job for each valid entry detected. 
        for(int i = 0; i < dataSet.size(); i++)
        {
            line = dataSet.get(i);
            if(line != null)
            {
                cleanUpData(line);
                addJob(line);
            }
        }
            
        return jobList;
    }

    private List<String[]> splitCSVLines(List<String> stringData) 
    {
        List<String[]> dataSet = new ArrayList<String[]>();

        for(int i = 0; i < stringData.size(); i++)
        {
            String line = stringData.get(i);
            if(isValidFormat(line))
            {
                String[] data = line.split(",");
                dataSet.add(data);
            }
        }

        return dataSet;
    }

    private void resetParser()
    {
        jobList = new ArrayList<PrintJob>();
        columnColorIndex = -1;
        columnTotalIndex = -1;
        columnSidedIndex = -1;
        columnSizesIndex = -1;
    }

    // Basic Search - Check if there is a header with valid columns.
    protected void columnSearchHeader(String[] data)
    {
        for(int i = 0; i < data.length; i++)
        {
            if(data[i].toUpperCase().contains("TOTAL PAGES")) columnTotalIndex = i;
            else if(data[i].toUpperCase().contains("COLOR PAGES")) columnColorIndex = i;
            else if(data[i].toUpperCase().contains("DOUBLE SIDED")) columnSidedIndex = i;
            else if(data[i].toUpperCase().contains("PAGE SIZE")) columnSizesIndex = i;
        }
    }

    protected void cleanUpData(String[] data)
    {
        // For our numeric columns, always remove other characters.
        data[columnTotalIndex] = data[columnTotalIndex].replaceAll("[^\\d.]", "");
        data[columnColorIndex] = data[columnColorIndex].replaceAll("[^\\d.]", "");

        // For double sided check, remove any extra spaces.
        data[columnSidedIndex] = data[columnSidedIndex].replaceAll("\\s+","");
        
        // Clean up paper size data if in use.
        if(columnSizesIndex != -1) 
        {
            data[columnSizesIndex] = data[columnSizesIndex].replaceAll("\\s+","");
        }
        
    }

    private boolean addJob(String[] data)
    {
        // Ensure the changed data is still valid.
        if(data[columnTotalIndex].isEmpty() || data[columnColorIndex].isEmpty() || data[columnSidedIndex].isEmpty()) return false;

        // Check if true false or invalid.
        int dSidedCheck = parseDoubleSided(data[columnSidedIndex]);
        if(dSidedCheck <= -1) return false;


        // Begin producing job data.
        Paper paper = Paper.DEFAULT_SIZE;
        boolean isDoubleSided = dSidedCheck == 1;
        int totalPages = 0; 
        int colorPages = 0; 

        // If columnSizesIndex is set, override the type of paper being used.
        if(columnSizesIndex != -1) 
        {
            paper = parsePaper(data[columnSizesIndex]);
        }

        // If we are using an invalid paper type, cancel this job addition.
        if(paper == Paper.NONE)
        {
            return false;
        }
        
        // Try and parse in our ints.
        try
        {
            totalPages = Integer.parseInt(data[columnTotalIndex]);
            colorPages = Integer.parseInt(data[columnColorIndex]);
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
        
        return jobList.add(nPrintJob);
    }

    // Values to look for when using paper size.
    private Paper parsePaper(String val)
    {
        switch(val.toUpperCase())
        {
            case "A4":
                return Paper.A4;
            default:
                break;
        }

        if(val == Paper.A4.NAME) return Paper.A4;
        return Paper.NONE;
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

    private boolean isValidFormat(String inData)
    {
        if(inData == null) return false;
        if(inData.isEmpty()) return false;
        return inData.contains(",");
    }
}