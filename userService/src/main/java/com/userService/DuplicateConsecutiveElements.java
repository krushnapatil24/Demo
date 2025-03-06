package com.userService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DuplicateConsecutiveElements {
    public static void main(String[] args) {
        int[] arr = {1,2,2,2,3,4,4,5,2,2};

       // Method method =


        ArrayList<Integer> output = new ArrayList<>();
            
        for (int i = 0; i < arr.length; i++) {
            if(i < arr.length-1 && arr[i] == arr[i+1]  ){
                continue;
            }
            output.add(arr[i]);
        }
        System.out.println(output);


    }
}
