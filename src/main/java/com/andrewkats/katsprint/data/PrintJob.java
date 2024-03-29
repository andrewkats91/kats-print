package com.andrewkats.katsprint.data;

import java.util.List;
import java.util.ArrayList;

public class PrintJob
{
    private List<PrintTask> taskList = new ArrayList<>();
     
    public boolean addTask(Paper.Type paperType, boolean isDoubleSided, int pageCount)
    {
        if(taskList == null) return false;
        if(paperType == null) return false;
        if(pageCount <= 0) return false;

        return taskList.add(new PrintTask(paperType, isDoubleSided, pageCount));
    }

    public List<PrintTask> taskList() { return taskList; }
    
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
            this.isDoubleSided = isDoubleSided;
            this.pageCount = pageCount;
        }

        public Paper.Type paperType() { return paperType; }
        public boolean isDoubleSided() { return isDoubleSided; }
        public int pageCount() { return pageCount; }

        public String[] toStringArray()
        {
            return new String[]
            {
                paperType.toString(),
                String.valueOf(isDoubleSided),
                String.valueOf(pageCount)
            };
        }
    }
}