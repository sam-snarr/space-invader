/*Samuel Snarr
 * 12/02/17
 * CSC 201 - Final Project - Professor Stange
 * CannonBlaster class*/

import java.awt.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class CannonBlaster extends DrawableAdapter{
	//counts are for the three timers
	static int count=0; 
	static int count2=0;
	static int count3=30;
	
	//code to create the gameBoard
	static CannonBlaster ga = new CannonBlaster();
	static GameBoard gb = new GameBoard(ga, "This is a Cannon Blaster game");
	
	//creating cannon to be used as well as arrays for objects in the game
	static Cannon C1 = new Cannon();
	static Bullet[] bulletArray = new Bullet[20];
	static Alien[] aliensArray = new Alien[100];
	static Stars[] starArray = new Stars[1000];
	
	//variables to keep track of bullets
	static int bulletNum = 0;
	static int bulletCount = bulletArray.length;
	
	//to keep track of the number of aliens as well as the number that have been killed
	static int alienCount = 0;
	static int alienKilled = 0;
	
	//strings for output
	static String bulletString = "Bullets: " + bulletCount;
	static String s;
	
	// variables to make movement in display more realistic
	static int momentum = 0;
	static int gunHeight = 0;
	
	//random value generator
	static Random rand = new Random();

	public static void main(String[] args) {   //main method
		
			JOptionPane.showMessageDialog(null, //outputs rules
					"                                            "
					+ "***CANNON BLASTER***\n"
					+ " You have 30 seconds to shoot as many alien marios "
					+ " as you can using your cannon blaster.\n"
					+ " Use the left and right arrows to move side to side"
					+ " and use the up arrow to shoot(you can also use 'W' 'A' and 'D'). Press 'B' to reload.\n The solar winds may make"
					+ " your bullets bounce side to side, use this to your advantage. "
					+ "\n Press 'Q' to quit at anytime.");
		
			gb.setTimerInterval(1, 20); //sets timers
			gb.setTimerInterval(2, 40);
			gb.setTimerInterval(3,  1000);
			
			createOtherStars();      //creates an array of 1000 stars of different color and size
			
			for(int i=0; i<bulletArray.length; i++){ //creates array of bullets
				Bullet B = new Bullet(C1);
				bulletArray[i] = B;
			}
			for(int i=0; i<aliensArray.length; i++){ //creates array of aliens
				int x = rand.nextInt(400);
				aliensArray[i] = new Alien(x, -20);
			}
			gb.setBackground(Color.BLACK);
			showGameBoard(gb);				//displays the gameBoard
	}

	public void draw(Graphics g){ //callback method that draws on screen
		for(int i=0; i<1000; i++){ //shows stars
			starArray[i].show(g);
		}
		for(int i=0; i<bulletArray.length; i++){ //shows bullets
			if(bulletArray[i].getVisible()){
				bulletArray[i].show(g);
			}
		}
		for(int i=0; i<aliensArray.length; i++){ //shows aliens
			if(aliensArray[i].getVisible()){
				aliensArray[i].show(g);
			}
		}
		C1.show(g); //shows cannon
		
		g.setColor(Color.WHITE); //displays time, bullets left, when to reload, and aliens killed
		g.setFont(new Font("Arial", Font.BOLD, 20));
		if(count3 == 30){
			g.drawString("PRESS START TO BEGIN", 150, 250);
		}
		g.drawString("Bullets: " + bulletCount, 200, 60);
		g.drawString("Time: " + count3, 400, 60);
		g.drawString("Aliens killed: " + alienKilled, 15, 60);
		if(bulletCount == 0){    //reload if bullet array is empty
			g.drawString("Press 'B' To Reload Cannon!", 150, 250);
		}
		
		if(count3==-1){  //if the count for game time is -1 then the game is over
			gb.stopTimer(1);
			gb.stopTimer(2);
			gb.stopTimer(3);
			JOptionPane.showMessageDialog(null, "You killed a total of "+ alienKilled +" aliens. ");  //displays the number of aliens killed
			for(Alien a: aliensArray){ //clears the screen
				a.setVisible(false);
			}
			s = JOptionPane.showInputDialog(null, "do you want to play again?");
			
			if(s.charAt(0)=='y' || s.charAt(0)=='Y'){ //if person attempts to type 'yes'--they can spell it wrong
				for(int i=0; i<aliensArray.length; i++){ //creates new array of aliens
					int x = rand.nextInt(400);
					aliensArray[i] = new Alien(x, 0);
				}
				for(int i=0; i<bulletArray.length; i++){ //creates new array of bullets
					Bullet B = new Bullet(C1);
					bulletArray[i] = B;
				}
				count3 = 30; //resets the original static variables
				alienKilled = 0;
				
				gb.startTimer(1); //starts game again by resetting timers
				gb.startTimer(2);
				gb.startTimer(3);
				showGameBoard(gb);
			}
			else{
				System.exit(0); //if they do not type yes, game exits
			}
		}
	}
	public void mouseClicked(int x, int y, int buttonClicked){ //added method so they can click to shoot also
		bulletArray[bulletNum].setVisible(true);
		bulletNum++;
		bulletCount--;
	}
	public void keyStruck(char key){ //keyboard listener
		if(key =='U' || key =='W'){ //press up button to shoot
			if(bulletCount !=0){//recoil for cannon
				int y = C1.getY();
				C1.setY(y+10);
			}
			try{
				bulletArray[bulletNum].setVisible(true); //tries to set bullet visible
			}
			catch(Exception e){  //if it is at the end of the array
				bulletCount = 1;
			}
			bulletNum++;
			bulletCount--;
		}
		if(key =='L' || key =='A'){  //press left button to move cannon to the left
			C1.moveLeft();
			for(int i=0; i<1000; i++){ //moves stars right one pixel
				starArray[i].setX(starArray[i].getX()+1);
			}
			momentum = -7;
		}
		if(key =='R' || key =='D'){ //press right button to move cannon to the right
			C1.moveRight();
			for(int i=0; i<1000; i++){  //moves stars left one pixel
				starArray[i].setX(starArray[i].getX()-1);
			}
			momentum =7;
		}
		if(key =='B'){              //reloads the cannon if 'B' is pressed
			for(int i =0; i<bulletArray.length; i++){
				bulletArray[i] = new Bullet(C1);
			}
			bulletCount = bulletArray.length;
			bulletNum = 0;
		}
		if(key == 'H'){           //pauses game then displays a help window
			gb.stopTimer(1);
			gb.stopTimer(2);
			gb.stopTimer(3);
			String message = "                              "
					+ "***Help***\nTo play this game all you "
					+ "have to do is shoot as many alien marios as you can,"
					+ " if one of them gets passed you then you lose."
					+ "\nWhen you are done, exit out of this help window and then "
					+ "press 'S' to resume the game.";
			JOptionPane.showMessageDialog(null, message);
		}
		if(key == 'Q'){  //if key is 'Q' then the game is terminated
			System.exit(0);
		}
		if(key == 'S'){  //if key is 'S' then the game is resumed
			gb.startTimer(1);
			gb.startTimer(2);
			gb.startTimer(3);
		}
	}
	public void timer1(){
		if(count2%70==0){  //releases an alien every 100 cycles of count2
			aliensArray[alienCount].setVisible(true);
			alienCount++;
		}
		count2++;
		for(Alien a: aliensArray){ //moves alien down one pixel for each time timer is run
			if(a.getVisible()){
				int y = a.getY();
				a.setY(y+1);
			}
		}
		int bx, by, ax, ay;
		for(Bullet b: bulletArray){ //looks for bullet that is visible
			if(b.getVisible()){    
				bx = b.getX();
				by = b.getY();
				
				for(Alien a: aliensArray){  //looks for alien that is visible
					if(a.getVisible()){
						ax = a.getX();
						ay = a.getY();
						//compares their coordinates to see if they are touching, if they are, then alien has been shot
						//bullet and alien are set to not visible
						if(bx<ax+15 && bx>=ax && by<ay+25 && by>=ay && b.getVisible() && a.getVisible()){
							b.setVisible(false);
							a.setVisible(false);
							alienKilled++;	
						}
					}
				}
			}
		}
		if(C1.getY() != Cannon.height){ //moves the cannon back toward where it originally was to make cannon shot realistic
			gunHeight = C1.getY();
			C1.setY(gunHeight -1);
		}
		if(C1.getY() > Cannon.height + 5){ //keeps cannon from going to far down the screen
			C1.setY(Cannon.height + 5);
		}
		for(int i =0; i<bulletArray.length; i++){ //uses random numbers to make bullets bounce back and forth
			int num = rand.nextInt(5)-2;
			int x = bulletArray[i].getX();
			
			if(bulletArray[i].getVisible()){  //moves only the bullets that are visible
				int temp2 = bulletArray[i].getY();
				if(temp2 <50){                   //if bullet is off the screen
					bulletArray[i].setVisible(false);
				}
				bulletArray[i].setY(temp2-2);
				bulletArray[i].setX(x+num);
			}
			else{                       //keeps bullets within the cannon ready to fire
				int temp = C1.getX();
				bulletArray[i].setX(temp+5);
			}
		}
		int y;
		if(count2%2 ==0){ //moves the stars down slower than the aliens
			for(int i=0; i< 1000; i++){  //moves the stars down one pixel
				y = starArray[i].getY();
				if(y>500){
					starArray[i].setY(0);
				}
				else{
					starArray[i].setY(y+1);
				}
			}
		}
	}
	public void timer2(){  // used to make the game smoother looking
		count++;
		if(momentum != 0 && momentum >0){
			C1.setX(C1.getX()+ momentum);  //makes the cannon slide after taking finger off of key to move it
			momentum -=1;
		}
		if(momentum != 0 && momentum <0){  //makes the cannon slide the other way if it is moving the other way
			C1.setX(C1.getX()+ momentum);
			momentum +=1;
		}
	}
	public void timer3(){  //timer for game clock
		count3--;
	}
	public static void createOtherStars(){  //creates 1000 star objects that will be displayed on the background of gameboard
		Random r1 = new Random();  //they are randomly located
		int x, y;
		for(int i=0; i<1000; i++){
			x = r1.nextInt(900)-200;
			y = r1.nextInt(500);
			if(i%3==0){  //one third are yellow
				Stars star = new Stars(x, y, 3, Color.YELLOW);
				starArray[i] = star;
			}
			else if(i%3==1){  //one third are red
				Stars star = new Stars(x, y, 3, Color.RED);
				starArray[i] = star;
			}
			else{      //one third are white
				Stars star = new Stars(x, y, 3, Color.WHITE);
				starArray[i] = star;
			}
		}
	}
}

