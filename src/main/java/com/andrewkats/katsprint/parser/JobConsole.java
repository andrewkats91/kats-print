package com.andrewkats.katsprint.parser;

import java.io.Console;

public class JobConsole 
{
    public static String readLine(String question) 
    {      
        if(question == null) return null;
        
        Console cnsl = null;
        String oString = null;
        
        try 
        {
            cnsl = System.console();
            if (cnsl != null) 
            {
                oString = cnsl.readLine(question);
            }     
        } 
        catch(Exception ex) 
        {
            ex.printStackTrace();      
        }

        return oString;
    }
}