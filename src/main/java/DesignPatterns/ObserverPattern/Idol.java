package DesignPatterns.ObserverPattern;

public interface Idol {
    public void addFan(Fan fan);
    public void deFan(Fan fan);
    public void notify(String messgae);

}
