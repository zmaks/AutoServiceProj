package com.autoservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Максим on 14.12.2015.
 *
 * перечислимый тип, хронящий способы записи/вывода информации о тачке
 */
public enum DataIndicator {
    CONSOLE,
    XML,
    DATABASE;

    private static final ArrayList<DataIndicator> VALUES =  new ArrayList<DataIndicator>(Arrays.asList(values()));

    /**
     *  возращает рандомный тип
     */
    public static DataIndicator getRandomDataIndicator()  {
        return VALUES.get(new Random().nextInt(VALUES.size()));
    }
}
