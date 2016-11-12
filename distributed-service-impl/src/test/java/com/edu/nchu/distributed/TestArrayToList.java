package com.edu.nchu.distributed;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alen on 2016/10/30.
 */
public class TestArrayToList {


    @Test
    public void array2ListTest(){
        List<String> arrayList = Arrays.asList("hello", "world", "test");
        System.out.println(arrayList);


        List<String> list = new ArrayList<String>(arrayList);
        list.add("jdk Array");

        System.out.println(list);



    }
}
