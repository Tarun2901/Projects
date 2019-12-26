package basilisk;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake{
List<Point> SnakePoints;
int xDir,yDir;
// We use isMoving to check whether snake is moving or not 
boolean isMoving,elongate;
final int STARTSIZE=20,STARTX=150,STARTY=150;

public Snake()
{
	SnakePoints= new ArrayList<Point>();
	 // Since the left hand corner of the applet viewer is 0,0
	//if xdir==-1, snake heading to the left
	        // if xdir==1 then snake is heading to the the right
	        // ydir==-1 then snake is going upwards
	        // ydir== 1 then snake is going downwards
	xDir=0;
	yDir=0;
	isMoving=false;
	elongate=false;
	SnakePoints.add(new Point(STARTX,STARTY));
	for(int i=1;i<STARTSIZE;i++)
	{
		SnakePoints.add(new Point(STARTX-i*4,STARTY));
	}
	
}

public void draw(Graphics g)
{
	g.setColor(Color.white);
	for(Point p:SnakePoints)
	{
		g.fillRect(p.getx(),p.gety(), 4, 4);
	}
}
// Move gets called within the infinite loop present in the class SnakeGame and snake will move one unit and briefly pause
// We perceive it as moving continuously
public void move() {
	// Find current position of snake
	if(isMoving) {
	Point temp= SnakePoints.get(0);
	Point last= SnakePoints.get(SnakePoints.size()-1);
	// New starting point
	Point newStart= new Point(temp.getx()+xDir*4,temp.gety()+yDir*4);
    //We now want to update every other point
	for(int i=SnakePoints.size()-1;i>=1;i--)
	{
		// Every SnakePoint becomes the SnakePoint that was before it
		SnakePoints.set(i, SnakePoints.get(i-1));
		if(elongate)
		{
			SnakePoints.add(last);
			elongate=false;
		}
	}
	// Update original head of the snake
	SnakePoints.set(0, newStart);

}
}

public boolean snakeCollision() {
	
	//We track head of the snake and check if it is same x and y position as any other point of the snake
	int x= this.getX();
	int y= this.getY();
	for(int i=1;i<SnakePoints.size();i++)
	{ if(SnakePoints.get(i).getx()==x&&SnakePoints.get(i).gety()==y)
		{
			return true;
		}
	}
	return false;
}


public boolean isMoving()
{
	return isMoving;
	
}

public void setIsMoving(boolean b)
{
	isMoving=b;
}



public int getxDir()
{
	return xDir;
}

public int getyDir()
{
	return yDir;
}

public void setxDir(int x)
{
	xDir=x;
}

public void setyDir(int y)
{
	yDir=y;
}


//X position of the head of the snake
public int getX()
{
	
	return SnakePoints.get(0).getx();
}

public int getY()
{
	return SnakePoints.get(0).gety();


}

public void setElongate(boolean b)
{
	elongate=b;
}


}

