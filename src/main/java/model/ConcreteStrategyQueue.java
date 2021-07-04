package model;


public class ConcreteStrategyQueue implements Strategy {

    @Override
    public int addTask(Scheduler scheduler, Task task) {
        int minim = 9999;
        int id = 0;
        for( Server s: scheduler.getServers()) {
            if(s.getWaitingClient().intValue()<minim) {
                minim = s.getWaitingClient().intValue();
                id = scheduler.getServers().indexOf(s);
            }
        }
        for(Server s : scheduler.getServers()) {
            if(scheduler.getServers().indexOf(s) == id) {
                s.addTask(task);
            }
        }
        return id;
    }
}
