package com.autoservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by ������ on 14.12.2015.
 *
 * ������������ ���, �������� ������� ������/������ ���������� � �����
 */
public enum DataIndicator {
    CONSOLE,
    XML,
    DATABASE;

    private static final ArrayList<DataIndicator> VALUES =  new ArrayList<DataIndicator>(Arrays.asList(values()));

    /**
     *  ��������� ��������� ���
     */
    public static DataIndicator getRandomDataIndicator()  {
        return VALUES.get(new Random().nextInt(VALUES.size()));
    }
}
