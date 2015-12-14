package com.autoservice;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ������ on 14.12.2015.
 *
 * ����� ������� �����������
 */
public class Master implements Runnable {
    private static final Logger logger = Logger.getLogger(Master.class.getName());

    private Car car;
    private BlockingQueue<Car> parking;
    private String name;

    public Master(BlockingQueue<Car> parking, String name) {
        this.parking = parking;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // ���� ����� � ��������
                takeNewCar();
                logger.info(name + " start fix " + car.getName());
                // ����� �� ������� ���� (HandlingTime - ����������� �����)
                Thread.sleep(car.getHandlingTime()+new Random().nextInt(3000));
                logger.info(car.getName() + " fixed by " + name);
                // ������ ���������� � ���������� �����
                DataWriter.writeData(car);
                // ����� �� �����
                Thread.sleep(new Random().nextInt(5000)+2000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void takeNewCar() throws InterruptedException {
        this.car = parking.take();
    }
}
