package com.sofyun.user.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LambdaTest
 * @Description TODO
 * @Author gm
 * @Date 2019/3/21 9:49
 **/
public class LambdaTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(30);
        list.add(20);
        list.add(20);


        List<Integer> list2 = new ArrayList<>();
        list.forEach( x -> {
            if (x == 20){
                list2.add(x);
            }
        });

        System.out.println(list2.get(1));

    }

}
