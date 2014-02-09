package com.movingmachines.entities;

import java.text.ParseException;

import com.movingmachines.core.GridEngine;
import com.movingmachines.entities.exceptions.positionNotAvaliableException;

/**
 * 
 * Machine base class
 * 
 * @author Cristiano
 * 
 */
public class Machine {

	private int xPosition;
	private int yPosition;
	private Direction direction;

	public Machine(int x, int y, Direction direction) {
		this.xPosition = x;
		this.yPosition = y;
		this.direction = direction;
	}

	/**
	 * Validates and execute commands sent to the machine
	 * 
	 * @param commandString
	 *            A string with commands without spaces
	 * @throws ParseException
	 *             Problems parsing this commands into valid actions will throw this exception
	 * @throws positionNotAvaliableException
	 *             Happens when we try to move to a position that is occupied or out of bounds
	 * @author Cristiano
	 */
	public void execute(String commandString, GridEngine grid) throws ParseException, positionNotAvaliableException {
		System.out.println("Executing commands " + commandString);

		char[] commands = commandString.toCharArray();
		for (char command : commands) {
			switch (command) {
			case 'L':
				this.setDirection(this.turnLeft());
				System.out.println("Turning Left. Direction now is " + this.direction);
				break;
			case 'R':
				this.setDirection(this.turnRIght());
				System.out.println("Turning Right. Direction now is " + this.direction);
				break;
			case 'M':
				grid.removeMachine(this);
				System.out.println("Moving");
				this.move();
				grid.set(this);
				break;
			default:
				throw new ParseException("Error parsing command to machine" + command + "is not a valid command", 0);
			}
		}
	}

	/**
	 * Moves the machine one position in the current direction
	 * 
	 * @author Cristiano
	 */
	public void move() {
		System.out.println("Current position is now " + this.xPosition + ", " + this.yPosition);
		this.setxPosition(this.getxPosition() + this.getDirection().getXCorrection());
		this.setyPosition(this.getyPosition() + this.getDirection().getYCorrection());
		System.out.println("Current position is now " + this.xPosition + ", " + this.yPosition);
	}

	/**
	 * Verify which is the direction left to the current one
	 * 
	 * @return Direction that is left to the actual one
	 * @throws ParseException
	 *             in case of a invalid direction
	 * @author Cristiano
	 */
	public Direction turnLeft() throws ParseException {
		if (this.getDirection() == Direction.N) {
			return Direction.E;
		} else if (this.getDirection() == Direction.E) {
			return Direction.S;
		} else if (this.getDirection() == Direction.S) {
			return Direction.W;
		} else if (this.getDirection() == Direction.W) {
			return Direction.N;
		}
		throw new ParseException("Invalid direction", 0);
	}

	/**
	 * Verify which is the direction right to the current one
	 * 
	 * @return Direction that is right to the actual one
	 * @throws ParseException
	 *             in case of a invalid direction
	 * @author Cristiano
	 */
	public Direction turnRIght() throws ParseException {
		if (this.getDirection() == Direction.N) {
			return Direction.W;
		} else if (this.getDirection() == Direction.W) {
			return Direction.S;
		} else if (this.getDirection() == Direction.S) {
			return Direction.E;
		} else if (this.getDirection() == Direction.E) {
			return Direction.N;
		}
		throw new ParseException("Invalid direction", 0);
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

}
