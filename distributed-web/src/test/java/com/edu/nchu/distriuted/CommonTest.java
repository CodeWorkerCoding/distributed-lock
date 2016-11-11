package com.edu.nchu.distriuted;

import org.junit.Test;

/**
 * 项目中一些自定义测试，验证知识点
 * Created by fujianjian on 2016/11/11.
 */
public class CommonTest {


    @Test
    public void testDoubleCompared(){
        Double targetDouble = 0.0d;
        Double targetDouble2 = 12.0d;
        Double anOtherDouble = 10.0d;
        Double anOtherDouble2 = 10.000d;

        System.out.println(targetDouble.compareTo(Double.NaN));
        System.out.println(targetDouble.compareTo(targetDouble2));
        System.out.println(targetDouble2.compareTo(anOtherDouble));
        System.out.println(anOtherDouble.compareTo(anOtherDouble2));
        System.out.println(anOtherDouble.compareTo(anOtherDouble));

        /***
         * test Result
         * -1
         * -1
         * 1
         * 0
         * 0
         */
    }
}
