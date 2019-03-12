/*Samuel Snarr
 * 12/02/17
 * CSC 201 - Final Project - Professor Stange
 * HeavenlyBodies class*/
import java.awt.*;

public class Stars {
	private int xCoord;  //data fields for class
	private int yCoord;
	private int diameter;
	private Color sunColor;
	
	public Stars(int xCoord, int yCoord, int diameter, Color sunColor) { //constructor
		this.xCoord = xCoord;  
		this.yCoord = yCoord;
		this.diameter = diameter;
		this.sunColor = sunColor;
	}
	public void show(Graphics g) {  //draws the HeavenlyBodies object
		g.setColor(sunColor);
		g.fillOval(xCoord, yCoord, diameter, diameter);
	}
	public void setX(int xCoord){  //sets x coordinate
		this.xCoord = xCoord;
	}
	public void setY(int yCoord){  //sets y coordinate
		this.yCoord = yCoord;
	}
	public void setDiameter(int diameter){  //sets diameter
		this.diameter = diameter;
	}
	public void setColor(Color sunColor){ //sets color
		this.sunColor = sunColor;
	}
	public int getX(){  //gets the x coordinate
		return xCoord;
	}
	public int getY(){  //gets the y coordinate
		return yCoord;
	}
	public int getDiameter(){ //gets the diameter
		return diameter;
	}
	public Color getColor(){ //gets the color
		return sunColor;
	}
	public String toString(){ //modified toString
		String s = "X coordinate: " + xCoord + "\nY coordinate: " + yCoord + "\nDiameter: " 
	+ diameter +"\nColor: " + sunColor;
		return s;
	}
}
