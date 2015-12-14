package com.autoservice;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ћаксим on 14.12.2015.
 *
 * генераци€ тачки с рандомными параметрами
 */
public class CarCreator {
    private static ArrayList<String> ids = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> owners = new ArrayList<String>();

    public CarCreator(){
        for(int i = 0; i<3;i++){
            ids.add("BMW");
            ids.add("Fiat");
            ids.add("Toyota");
        }
    }

    public static Car getNewCar(){
        Random r = new Random();
        String id = ids.get(r.nextInt(3));
        String name = "CarName"+r.nextInt(100);
        String owner = "Owner"+r.nextInt(100);
        long handlingTime = r.nextInt(4000)+1000;
        DataIndicator dataIndicator = DataIndicator.getRandomDataIndicator();

        return new Car(id,name,owner,handlingTime,dataIndicator);
    }
}
