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
    public List<PrintJob> readPrintJobs(String filePath)
    {
        // Required data types.
        BufferedReader fileReader = null;
        List<PrintJob> nPrintQueue = null;
        List<String> stringData = null;

        // Try and read the target file.
        try 
        {
			fileReader = new BufferedReader(new FileReader(filePath));
        } 
        catch (FileNotFoundException e) 
        {
            return null;
        }

        // If the file seems valid, try and retrieve all file data.
        if(fileReader != null)
        {
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
        }

        // Finish up and close the reader.
        try 
        {
			fileReader.close();
        } 
        catch (IOException e) 
        {
			e.printStackTrace();
        }
        
        // If data exists, create a new parser and process the data.
        if(stringData != null)
        {
            ParserCSV parser = new ParserCSV();
            nPrintQueue = parser.parseData(stringData);
        }
    
        return nPrintQueue;
    }
}