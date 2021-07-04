package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ModelQ {

    private String file;
    public ModelQ(String file)
    {
        this.file = file;
    }

    public int peak(Scheduler scheduler)
    {
        int maxTasks = 0;
        for(Server serv: scheduler.getServers())
        {
            maxTasks += serv.getSize();
        } return maxTasks;
    }

    public void display(Scheduler scheduler, int timp, List<Task> tasks) throws IOException {
        Path p = Paths.get(file);
        BufferedWriter w = Files.newBufferedWriter(p, StandardOpenOption.APPEND);
        for(Server serv: scheduler.getServers() ) {
            w.write("Queue " + serv.getIndex() + serv.toString() + "\n");
        }
    }

}
