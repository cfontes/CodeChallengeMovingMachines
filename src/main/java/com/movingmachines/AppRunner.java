package com.movingmachines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.movingmachines.core.Engine;

public class AppRunner {

	/**
	 * Start the application and fetch the command file.
	 * 
	 * @param args
	 * @author Cristiano
	 */
	public static void main(String[] args) {
		BufferedReader bufferReader = null;
		Engine engine;
		String[] commands;

		try {
 
			String line;
 
			bufferReader = new BufferedReader(new FileReader("input.txt"));

			while ((line = bufferReader.readLine()) != null) {
				commands = line.split("\\s");

				for (String command : commands) {
					System.out.println(command);
				}
				System.out.println();
			}

			if (engine == null) {
				engine = new Engine(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]));
			} else {
				engine.addMachine(x, y);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
