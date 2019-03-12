/*Samuel Snarr
 * 12/02/17
 * CSC 201 - Final Project - Professor Stange
 * Bullet class*/
import java.awt.*;

public class Bullet {
	
	static int width = 10;   //data fields for class
	static int height = 15;
	
	static Color color = Color.RED;
	private int xCoord=100;
	private int yCoord=100;
	private boolean visible;
	
	public Bullet(Cannon c){  //constructor
		xCoord = c.getX()+5;
		yCoord = c.getY()-5;
		visible = false;
	}
	
	public void show(Graphics g){  //draws bullet
		g.setColor(color);
		g.fillOval(xCoord, yCoord, width, height);
	}
	public void setX(int xCoord){ //sets the x coordinate
		this.xCoord = xCoord;
	}
	public void setY(int yCoord){  //sets the y coordinate
		this.yCoord = yCoord;
	}
	public int getX(){     //returns the x coordinate
		return xCoord;
	}
	public int getY(){     //returns the y coordinate
		return yCoord;
	}
	public boolean getVisible(){ //returns whether it is being drawn or not
		return visible;
	}
	public void setVisible(boolean var){  //sets the object so it can be drawn
		visible = var;
	}
}
