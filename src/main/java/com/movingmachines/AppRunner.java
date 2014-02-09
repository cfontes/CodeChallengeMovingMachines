package com.movingmachines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import com.movingmachines.core.GridEngine;
import com.movingmachines.entities.Direction;
import com.movingmachines.entities.Machine;

/**
 * Main application file
 * 
 * @author Cristiano
 * 
 */
public class AppRunner {
	/**
	 * 
	 * Start the application and fetch the command file.
	 * 
	 * @param args
	 * @author Cristiano
	 */
	public static void main(String[] args) {
		BufferedReader bufferReader = null;
		GridEngine gridEngine = null;

		try {
 
			String line;
 
			bufferReader = new BufferedReader(new FileReader("input.txt"));
			Machine machine = null;

			while ((line = bufferReader.readLine()) != null) {
				String[] commands = line.split("\\s");
				if (gridEngine == null) {
					gridEngine = new GridEngine(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]));
				} else if (commands.length > 1) {
					machine = gridEngine.addMachine(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]),
							Direction.toDirection(commands[2]));
				} else if (machine != null) {
					machine.execute(commands[0], gridEngine);
				}
			}
			gridEngine.generateOutputFile();
 
		} catch (IOException e) {
			System.out.println("Error writing to file - " + e.getMessage());
			//TODO - expand error handling
		} catch (NumberFormatException | ParseException e) {
			System.out.println("Error reading commands - " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error - " + e.getMessage());
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
