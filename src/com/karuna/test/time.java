package com.karuna.test;
import java.util.*;
import java.lang.*;

public class time {
	public static void main(String[] args){

	long stime=System.currentTimeMillis();
	for(int i=0;i<10;i++){
	for(int j=0;j<10;j++){

		System.out.println(j+i);
	}
	}
	long etime=System.currentTimeMillis();
	
	System.out.println((stime-etime)/1000);
	}

}
