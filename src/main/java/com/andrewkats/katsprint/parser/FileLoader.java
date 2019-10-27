package com.andrewkats.katsprint.parser;

import com.andrewkats.katsprint.calc.PrintJobInfoLog;
import com.andrewkats.katsprint.data.PrintJob;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader
{
    private BufferedReader fileReader;

    public List<PrintJob> readPrintJobs(String filePath)
    {
        boolean validPath = true;
        if(filePath == null) validPath = false;
        else if(filePath.isEmpty()) validPath = false;
        
        // If no valid path entered, request input from user.
        if(!validPath)
        {
            filePath = JobConsole.readLine("Please enter the path to the target CSV file: ");

            if(filePath == null) validPath = false;
            else if(filePath.isEmpty()) validPath = false;
            else validPath = true;

            if(!validPath)
            {
                System.out.println("No file path entered. Exiting application..");
                return null;
            }
            else
            {
                String logLev = JobConsole.readLine("Please enter the desired output detail (0 - 2 | Default is 0): ");
                PrintJobInfoLog.setLogLevelByString(logLev);
            }
        }

        
        // Required data types.
        List<PrintJob> printJobList = null;
        List<String> stringData = null;

        // Try and read the target file.
        tryReadTargetFile(filePath);

        // If file reading failed, try a different way. 
        if(fileReader == null) tryReadTargetFile(filePath + ".csv");

        // If the file seems valid, try and retrieve all file data.
        if(fileReader == null)
        {
            System.out.println("Could not read input file. Please ensure target path is correct.");
            return null;
        }

        try
        {
            stringData = new ArrayList<>();
            String line;
            while ((line = fileReader.readLine()) != null) 
            {
                stringData.add(line);
            }
        }
        catch (IOException e)
        {
            return null;
        }

        // Finish up and close the reader.
        try 
        {
			fileReader.close();
        } 
        catch (IOException e) 
        {
            System.out.println("An error occured while closing the input file.");
        }
        
        // If data exists, create a new parser and process the data.
        if(stringData != null)
        {
            printJobList = new ParserCSV().parseData(stringData);
        }
    
        return printJobList;
    }

    private void tryReadTargetFile(String filePath)
    {
        try 
        {
			fileReader = new BufferedReader(new FileReader(filePath));
        } 
        catch (FileNotFoundException e) 
        {
            fileReader = null;
        }
    }
}