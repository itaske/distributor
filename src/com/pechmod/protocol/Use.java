package com.pechmod.protocol;

import java.util.stream.IntStream;

public class Use {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 int array[]={1,2,3,4,5,6,7,8,9,0};
		 
		 System.out.println(IntStream.of(array).sum());
		 System.out.println(IntStream.of(array).average().getAsDouble());
		 System.out.println(IntStream.of(array).summaryStatistics());
		 System.out.println(IntStream.range(1, 9).sum());
		 System.out.println(IntStream.rangeClosed(1,9).sum());
		 
	}

}
