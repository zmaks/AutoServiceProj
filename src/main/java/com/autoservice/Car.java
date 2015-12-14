package com.autoservice;

/**
 * Created by Максим on 14.12.2015.
 *
 * класс тачки
 */
public class Car {
    private String id;
    private String name;
    private String owner;
    private long handlingTime;
    private DataIndicator dataIndicator;

    public Car(String id, String name, String owner, long handlingTime, DataIndicator dataIndicator){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.handlingTime = handlingTime;
        this.dataIndicator = dataIndicator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public long getHandlingTime() {
        return handlingTime;
    }

    public DataIndicator getDataIndicator() {
        return dataIndicator;
    }
}
