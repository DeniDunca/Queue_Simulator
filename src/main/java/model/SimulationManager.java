package model;

import view.ViewQ;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable{

    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime ;
    private int numberOfServers;
    private int numberOfClients ;
    private ModelQ m_model;
    private ViewQ m_view ;

    //pool of tasks (client shopping in the store)
    private ArrayList<Task> generatedTask;

    public SimulationManager(int timeLimit, int numberOfClients, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, ModelQ m_model, ViewQ m_view) {
        generatedTask = new ArrayList<>();
        this.m_model = m_model;
        this.m_view = m_view;
        this.timeLimit = timeLimit;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        generatedNRandomTasks(numberOfClients, maxProcessingTime,minProcessingTime);
    }

    private void generatedNRandomTasks(int numberOfClients, int maxProcessingTime, int minProcessingTime) {
        for(int i = 1; i <= numberOfClients; i++) {
            int arrivalTime = minArrivalTime + (int)(Math.random() * (maxArrivalTime - minArrivalTime));
            int proccTime = minProcessingTime + (int)(Math.random() * (maxProcessingTime - minProcessingTime));
            Task t = new Task(i,arrivalTime,proccTime);
            generatedTask.add(t);
        }
        Collections.sort(generatedTask);
    }

    private float serving() {
        float t = 0;
        for(Task task : generatedTask) {
            t = t + task.getProcessingTime();
        }
        t = t / numberOfClients;
        return t;
    }

    @Override
    public void run() {
        int peak = 0;
        int maxTasks = 0;
        int currentTime = 0;
        Scheduler scheduler = new Scheduler(numberOfServers,numberOfClients);
        float serving = serving();

        while(currentTime < timeLimit) {
            if(!generatedTask.isEmpty()) {
                for(int i = 0; i < numberOfServers && !generatedTask.isEmpty(); i++) {
                    if(currentTime >= generatedTask.get(0).getArrivalTime()) {
                        scheduler.dispatchTask(generatedTask.get(0));
                        generatedTask.remove(0);
                    }
                }
            }
            afis(currentTime, scheduler);
            if(maxTasks < m_model.peak(scheduler))//pentru peak
            {
                maxTasks = m_model.peak(scheduler);
                peak = currentTime;
            }
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                System.out.println("Interrupted");
            }
            if(generatedTask.isEmpty()) {//daca nu mai sunt clienti in waiting
                int cnt = 0;
                for(Server serv: scheduler.getServers()) {
                    if(serv.getSize() == 0) {
                        cnt++;
                    }
                }
                if(cnt == numberOfServers) {
                    currentTime = timeLimit;
                }
            }
            try {
                m_model.display(scheduler,currentTime,generatedTask);//afisare in aplicatie
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTime++;
        }
        afis(currentTime, scheduler);
        for(Server serv: scheduler.getServers()) {
            serv.stop();
        }
        System.out.println("Final servers: ");
        for(Server s : scheduler.getServers()) {
            System.out.println("Queue:" + s.getIndex());
            System.out.println(s.toString());
        }
        System.out.println("Average time: " + scheduler.averageWaitingTime());
        System.out.println("Peak hour: " + peak);
        System.out.println("Serving time: " + serving);
        m_view.setT_avgTime(String.valueOf(scheduler.averageWaitingTime()));
        m_view.setT_peak(String.valueOf(peak));
        m_view.setT_serving(String.valueOf(serving));
    }

    private void afis(int currentTime, Scheduler scheduler) {
        String str = "";
        int index = -1;
        str ="Time: " + currentTime + "\n";
        str += "Waiting clients: " + "\n";
        for(Task t : generatedTask) {
            index++;
            if(index % 5 == 0) {
                str += "\n";
            }
            str += t.toString() + "; ";
        }
        str += "\n";
        System.out.println("\nTime: " + currentTime);

        for(Server s : scheduler.getServers()) {
            str += "Queue:" + s.getIndex() + "\n" + s.toString();
            System.out.println("Queue:" + s.getIndex());
            System.out.println(s.toString());
        }
        m_view.setA_text(str);
    }
}
