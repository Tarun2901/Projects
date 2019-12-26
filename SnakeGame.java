package basilisk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable,KeyListener{
	// Setup double buffering 
	Graphics gfx;
	Image img;
	Thread thread;
	Snake snake;
	boolean gameOver;
	Token token;
	
	
public void init()
{
	this.resize(600,600);
	gameOver=false;
	img= createImage(600,600);
	gfx= img.getGraphics();
	this.addKeyListener(this);
	snake= new Snake();
	token= new Token(snake);
	thread= new Thread(this);
	thread.start();
}

public void paint(Graphics g)
{
	gfx.setColor(Color.black);
	gfx.fillRect(0, 0, 600, 600);
	if(!gameOver)
	 {snake.draw(gfx);
	 token.draw(gfx);
	 }
	else
	{	gfx.setColor(Color.red);
	gfx.drawString("GAME OVER", 280, 250);
	gfx.drawString("SCORE:"+token.getScore(), 280, 270);
	}
	    
	g.drawImage(img,0,0,null);
}

public void update(Graphics g)

{
	paint(g);
}

public void repaint(Graphics g)
{
	paint(g);
}
public void run() {
	//Infinite for loop driving the game
	
	for(;;)
	{   if(!gameOver)
	{	snake.move();
		this.checkGameOver();
		token.snakeCollision();
	}
		this.repaint();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public void checkGameOver()
{   //boundary
	if(snake.getX()<0||snake.getX()>596)
	{
		gameOver=true;
	}
	
	if(snake.getY()<0||snake.getY()>596)
	{
		gameOver=true;
	}
	
//collision
	
	if(snake.snakeCollision())
	{
		gameOver= true;
	}
	
}





public void keyTyped(KeyEvent e) {

	
}

public void keyPressed(KeyEvent e) {
	if(!snake.isMoving()) {
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			
	snake.setIsMoving(true);	
	
			
	}
	}
	if(e.getKeyCode()==KeyEvent.VK_UP)
	{
		if(snake.getyDir()!=1)
		{
		  snake.setyDir(-1);
		  snake.setxDir(0);
		}
	}
	if(e.getKeyCode()==KeyEvent.VK_DOWN)
	{
		if(snake.getyDir()!=-1)
		{
		  snake.setyDir(1);
		  snake.setxDir(0);
	}
	}
	if(e.getKeyCode()==KeyEvent.VK_LEFT)
	{
		if(snake.getxDir()!=1)
		{
		  snake.setxDir(-1);
		  snake.setyDir(0);
	}
	}
	
	if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	{
		if(snake.getxDir()!=-1)
		{
		  snake.setxDir(1);
		  snake.setyDir(0);
	}
	}
}


public void keyReleased(KeyEvent e) {

	
}



}
