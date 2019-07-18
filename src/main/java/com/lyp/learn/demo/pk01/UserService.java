package com.lyp.learn.demo.pk01;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(){
        System.out.println("UserService constructor ......");
    }

//
//    public int binarySearch(int [] arry,int num){
//        int length = arry.length;
//        int result = -1;
//        if(length == 0){
//            return result;
//        }
//        int start = 0;
//        int end = length - 1;
//        while(start <= end){
//            int mid = (start + end)/2;
//            if(arry[mid] > num){
//                end = mid - 1;
//            }else if(arry[mid] < num){
//                start = mid + 1;
//            }else{
//                result = mid;
//                break;
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        int [] ar = {3};
//        int index = new UserService().binarySearch(ar,4);
//        System.out.println(index);
//    }
}
