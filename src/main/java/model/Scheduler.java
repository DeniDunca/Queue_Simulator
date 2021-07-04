package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private int maxServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private List<Thread> threads;
    private float totalWaiting = 0;

    public Scheduler( int maxServers, int maxTasksPerServer){
        this.strategy = new ConcreteStrategyTime();
        this.maxServers = maxServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = Collections.synchronizedList(new ArrayList<>(maxServers));
        this.threads = Collections.synchronizedList(new ArrayList<>(maxServers));
        for(int i = 0; i < maxServers; i++){
            Server serv = new Server(maxTasksPerServer, i + 1);
            servers.add(serv);
            threads.add(new Thread(servers.get(i)));
            threads.get(i).start();
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    @Override
    public String toString() {
        String res = "";
        for(Server serv : this.getServers()){
            res += "Queue " + serv.getIndex() + ": " + serv.toString() + "\n";
        }
        return res;
    }

    public void dispatchTask(Task task) {
        int id = strategy.addTask(this,task);
        totalWaiting += servers.get(id).queueWaitingTime();
    }

    public float averageWaitingTime()
    {
        return totalWaiting / maxTasksPerServer;
    }
}
