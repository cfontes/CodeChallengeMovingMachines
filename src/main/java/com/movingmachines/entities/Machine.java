package com.movingmachines.entities;

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
