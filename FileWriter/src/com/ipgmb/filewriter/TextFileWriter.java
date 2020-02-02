package com.ipgmb.filewriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Text File Writer program is used to write a random string into the text file
 * within the range that can be from 1 to 2 power 30-1.
 * 
 * @author Dillibabu
 * @version 1.0
 */
public class TextFileWriter {

	/**
	 * This main method is used to get the input from the user which can be used to
	 * write into the file as no.of lines.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int number;
		do {
			System.out.println("Please enter a positive number!");
			while (!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next();
			}
			number = sc.nextInt();
		} while (number <= 0);
		Double d = Math.pow(2, 30);
		int maxValue = d.intValue() - 1;
		if (number <= maxValue) {
			writeToFile(number);
		} else {
			System.out.println("Out of range");
		}
	}

	/**
	 * This is method is used to create the file and write the random string into
	 * the file until it reaches the maximum limit.
	 * 
	 * @param lines This is the no of lines
	 * @exception IOException On input error.
	 */
	private static void writeToFile(int noOfLines) {
		try {
			System.out.println("Date and Time Before Start==>" + new Date());
			FileWriter writer = new FileWriter("C://FileWriter//fileWriter.txt");
			int leftLimit = 97; 
			int rightLimit = 122; 
			int targetStringLength = 100;
			Random random = new Random();
			int number = 0;
			do {
				long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long totalMemoryUsed = afterUsedMem / (1024 * 1024);
				System.out.println("Total Memory Used ==" + totalMemoryUsed + "MB");

				if (totalMemoryUsed >= 10) {
					Thread t = new Thread() {
						public void run() {
							Runtime.getRuntime().gc();
						}
					};
					t.start();
					long afterUsedMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					long totalMemoryUsed1 = afterUsedMem1 / (1024 * 1024);
					System.out.println("Total Memory Used After GC==" + totalMemoryUsed1 + "MB");
				}
				String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

				writer.write(generatedString);
				writer.write(System.getProperty("line.separator"));
				generatedString = null;
			} while (number++ < noOfLines);
			writer.close();
			System.out.println("Date and Time After Completion==>" + new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
