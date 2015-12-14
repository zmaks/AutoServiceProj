package com.autoservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ������ on 14.12.2015.
 *
 * ����� ��������
 *
 */
public class Parking {
    private int parkingSpaceCount;
    // ���������������� ������� � ������������ ����������� ��������� (parkingSpaceCount)
    // ��� ������ ������ .take() (�������� master) ������� ������� �� �������, ���� ��� �� �����
    // ���� ������, �����, ������������ � �������, ����, ���� � ��� �������� �������
    // ��� ������ ������ .put() (�������� evacuator) ����������� ������� � �������, ���� �� ������ �� ���������� parkingSpaceCount
    // ���� ���������, �����, ������������ � �������, ����, ���� � ��� ����������� �����
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
