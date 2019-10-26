package com.andrewkats.katsprint.data;

import java.util.List;
import java.util.ArrayList;

public class PrintJob
{
    private List<PrintTask> jobList = new ArrayList<>();
     
    public boolean addTask(Paper.Type paperType, boolean isDoubleSided, int pageCount)
    {
        if(jobList == null) return false;
        if(paperType == null) return false;
        if(pageCount <= 0) return false;

        return jobList.add(new PrintTask(paperType, isDoubleSided, pageCount));
    }

    // Contains the information for a single print task.
    // A task supports a single page size, type and side setting.
    public class PrintTask
    {
        private Paper.Type paperType;
        private boolean isDoubleSided;
        private int pageCount;
        
        private PrintTask(Paper.Type paperType, boolean isDoubleSided, int pageCount)
        {
            this.paperType = paperType;
            this.pageCount = pageCount;
        }
    }
}