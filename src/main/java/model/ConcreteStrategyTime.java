package model;


public class ConcreteStrategyTime implements Strategy {

    public int addTask(Scheduler scheduler, Task t) {
        int minim = 9999;
        int id = 0;
        for( Server s: scheduler.getServers()) {
            if(s.getWaitingTime().intValue()<minim) {
                minim = s.getWaitingTime().intValue();
                id = scheduler.getServers().indexOf(s);
            }
        }
        for(Server s : scheduler.getServers()) {
            if(scheduler.getServers().indexOf(s) == id) {
                s.addTask(t);
            }
        }
        return id;
    }
}
