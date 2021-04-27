package com.pechmod.utils;

import java.io.File;
import java.util.Locale;
import java.util.Properties;

public class UserTest {

	public static void main(String[] args) {
		User user =new User();
		
		Properties properties=user.getProperties();
		
		
			properties.list(System.out);
			
			user.saveFile("Patrick", System.getProperty("java.home"), new File(System.getProperty("user.home","Distributor")),Locale.US);

			System.out.println("\n After modification\n");
			properties.list(System.out);
		
		
		
	}

}
