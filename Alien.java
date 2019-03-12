/*Samuel Snarr
 * 12/02/17
 * CSC 201 - Final Project - Professor  Stange
 * Alien class*/
import java.awt.*;
import java.net.URL;
import javax.imageio.*;

public class Alien {
	
	private boolean visible;  //data fields
	private int x;
	private int y;
	Image mario;
	
	public Alien(int x, int y){  //constructs alien
		this.x = x;
		this.y = y;
		visible = false;
		
		try{     //tries to load mario image from resource folder
			URL imgUrl = getClass().getClassLoader().getResource("mario.png");  
			mario =ImageIO.read(imgUrl);
			
			} catch (Exception e){ //if mario cannot be loaded set it to nothing
				mario = null;
		}
	}
	public int getX(){   //returns the x coordinate
		return x;
	}
	public int getY(){   //returns the y coordinate
		return y;
	}
	public void setX(int x){  //sets the x coordinate
		this.x = x;
	}
	public void setY(int y){  //sets the y coordinate
		this.y = y;
	}
	public boolean getVisible(){  //returns whether it is being drawn or not
		return visible;
	}
	public void setVisible(boolean var){  //sets the object so it can be drawn
		visible = var;
	}
	public void show(Graphics g){  //draws the mario image on the gameboard at x,y coordinates
		
		g.drawImage(mario, x, y, null);
	}
	
}
