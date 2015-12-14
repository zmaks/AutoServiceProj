package com.autoservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Максим on 14.12.2015.
 *
 * класс парковки
 *
 */
public class Parking {
    private int parkingSpaceCount;
    // потокобезопасная очередь с ограниченным количеством элементов (parkingSpaceCount)
    // при вызове метода .take() (вызывает master) берется элемент из очереди, если она не пуста
    // если пустая, поток, обращающийся к очереди, ждет, пока в ней появится элемент
    // при вызове метода .put() (вызывает evacuator) добавляется элемент в очередь, если ее размер не превышаает parkingSpaceCount
    // если привышает, поток, обращающийся к очереди, ждет, пока в ней освободится место
    private BlockingQueue<Car> parking;
    public Parking(int parkingSpaceCount)
    {
        this.parkingSpaceCount = parkingSpaceCount;
        parking = new ArrayBlockingQueue<Car>(parkingSpaceCount);
    }


    public BlockingQueue<Car> getParking() {
        return parking;
    }
}
