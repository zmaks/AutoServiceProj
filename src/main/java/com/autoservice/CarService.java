package com.autoservice;

/**
 * Created by Максим on 14.12.2015.
 *
 * класс автомастерской
 */
public class CarService {
    private int parkingSpaceCount;
    private int evacuatorsCount;
    private int mastersCount;
    private Parking parking;

    public CarService(int parkingSpaceCount, int evacuatorsCount, int mastersCount){
        this.parkingSpaceCount = parkingSpaceCount;
        this.evacuatorsCount = evacuatorsCount;
        this.mastersCount = mastersCount;
        parking = new Parking(parkingSpaceCount);
    }

    /**
     * запуск работы автомастерской
     */
    public void startWorking(){
        for(int i = 0; i<evacuatorsCount;i++)
            new Thread(new Evacuator(parking.getParking(),"Evacuator "+(i+1))).start();
        for(int i = 0; i<mastersCount;i++)
            new Thread(new Master(parking.getParking(),"Master "+(i+1))).start();
    }
}

