package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private ArrayBlockingQueue<Task> tasks;
    private AtomicInteger waitingTime;
    private boolean stop = false;
    private int idServer;
    private int index;
    private AtomicInteger timpMediu;

    public Server(int idServer, int index) {
        this.index = index;
        this.idServer = idServer;
        this.tasks = new ArrayBlockingQueue<Task>(2000);
        waitingTime = new AtomicInteger(0);
        timpMediu = new AtomicInteger(0);
    }

    public AtomicInteger getWaitingTime()
    {
        return waitingTime;
    }

    public int getIndex()
    {
        return index;
    }

    public AtomicInteger getWaitingClient()
    {
        return new AtomicInteger(tasks.size());
    }

    public int getSize()
    {
        return tasks.size();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingTime.addAndGet(newTask.getProcessingTime());
        timpMediu.addAndGet(waitingTime.intValue());
    }

    public void stop()
    {
        stop = true;
    }

    public float queueWaitingTime(){
        if(tasks.size() <= 0){
            return 0;
        }
        float time = 0;
        int index = 0;
        for(Task t : tasks){
            if(index < tasks.size()){
                time += t.getProcessingTime();
            }
            index++;
        }
        return time / tasks.size();
    }

    @Override
    public String toString() {
        String res = "";
        if (tasks.isEmpty()) {
            return res + "closed." + "\n";
        }
        for(Task task : tasks){
            res = res + task.toString() + "\n";
        }
        return res;
    }

    @Override
    public void run() {
        while(true) {
            if(!tasks.isEmpty()) {
                try{
                    Task t = tasks.peek();//iau primul client din coada
                    int time = t.getProcessingTime();
                    while(time > 0 && !stop) {//facem update la processingtime
                        Thread.sleep(1000);
                        time--;
                        t.setProcessingTime(time);
                    }
                    waitingTime = new AtomicInteger(waitingTime.intValue() - (t.getProcessingTime()));// impartirea pe cozi
                    if(stop) {
                        break;
                    }
                    tasks.remove();
                }
                catch(InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
            else {
                if(stop) {
                    break;
                }
            }
        }
    }
}
