package me.efekos.simpler;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Utils {

    public static <T> ArrayList<T> fromStreamToArrayList(Stream<T> stream){
        ArrayList<T> list = new ArrayList<>();
        stream.forEach(list::add);
        return list;
    }
}
