package com.zxj.lrulibrary;

import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args) {
        LRUBaseLinkedList linkedList = new LRUBaseLinkedList(0);
        Scanner sr = new Scanner(System.in);
        while (true){
            linkedList.add(sr.nextInt());
            linkedList.printAll();
        }
    }
}