/*Samuel Snarr
 * 12/02/17
 * CSC 201 - Final Project - Professor  Stange
 * Cannon class*/
import java.awt.*;

public class Cannon{
	static Color cannonColor = Color.GRAY;   //data fields 
	static int length = 75;
	static int width = 15;
	static int ballWidth = 10;
	static final int height = 400;
	static final int MOVE = 3;
	
	private int xCoord = 250;
	private int yCoord = 400;
	
	public Cannon(){  //constructs the cannon
		xCoord = 250;
		yCoord = 400;
	}
	
	public void show(Graphics g){ //draws the cannon
		g.setColor(cannonColor);
		g.fillRect(xCoord, yCoord, width, length);
		g.fillOval(xCoord - 23, yCoord + 70, 60, 60);
	}
	public void setX(int xCoord){  //sets the x coordinate
		this.xCoord = xCoord;
	}
	public int getX(){     //returns the x coordinate
		return xCoord;
	}
	public int getY(){     //returns the y coordinate
		return yCoord;
	}
	public void setY(int yCoord){ //sets the y coordinate
		this.yCoord = yCoord;
	}
	//shifts cannon left or right
	public void moveLeft(){  
		this.xCoord -= MOVE;
	}
	public void moveRight(){
		this.xCoord += MOVE;
	}
}
