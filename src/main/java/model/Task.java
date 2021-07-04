package model;

public class Task implements Comparable<Task>{
    private int nr;
    private int arrivalTime;
    private int processingTime;

    public Task( int nr, int arrivalTime, int processingTime) {
        this.nr = nr;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getNr() {
        return nr;
    }

    @Override
    public String toString() {
        return "(" + getNr() + ", "+ getArrivalTime() + ", " + getProcessingTime() + ")";
    }

    @Override
    public int compareTo(Task c) {
        if(this.arrivalTime < c.arrivalTime){
            return  -1;
        }
        if(this.arrivalTime > c.arrivalTime){
            return 1;
        }
        return 0;
    }
}
