package com.autoservice;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Максим on 14.12.2015.
 *
 * Класс эвакуатора
 */
public class Evacuator implements Runnable {
    private static final Logger logger = Logger.getLogger(Evacuator.class.getName());

    private Car car;
    private String name;
    private CarCreator carCreator;
    private BlockingQueue<Car> parking;
    public Evacuator(BlockingQueue<Car> parking, String name){
        carCreator = new CarCreator();
        setNewCar();
        this.parking = parking;
        this.name = name;
    }
    public void run() {
        while (true){
            try {
                //добавление тачки (эвакуатор нашел авто для погрузки)
                setNewCar();
                // время на загрузку и доставку тачки
                Thread.sleep(new Random().nextInt(3000)+5000);
                // доставка тачки на парковку
                parking.put(car);
                logger.info(name + " brought " + car.getName() + " on parking");
                // время на поиск новой тачки
                Thread.sleep(new Random().nextInt(1000)+2000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void setNewCar(){
        car = carCreator.getNewCar();
    }
}
