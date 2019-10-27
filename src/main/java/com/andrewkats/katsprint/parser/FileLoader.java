package com.andrewkats.katsprint.parser;

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
        if(filePath == null) return null;
        if(filePath.isEmpty()) return null;

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