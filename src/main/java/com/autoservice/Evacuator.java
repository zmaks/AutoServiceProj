package com.autoservice;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ������ on 14.12.2015.
 *
 * ����� ����������
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
                //���������� ����� (��������� ����� ���� ��� ��������)
                setNewCar();
                // ����� �� �������� � �������� �����
                Thread.sleep(new Random().nextInt(3000)+5000);
                // �������� ����� �� ��������
                parking.put(car);
                logger.info(name + " brought " + car.getName() + " on parking");
                // ����� �� ����� ����� �����
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
