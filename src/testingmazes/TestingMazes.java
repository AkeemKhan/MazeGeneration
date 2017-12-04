package testingmazes;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class TestingMazes {

    public static void main(String[] args) {
        int maxX;
        int maxY;
        int option;
        Room[][] maze;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please enter the maximum height: ");
        maxY = scan.nextInt();
        
        System.out.println("Please enter the maximum width: ");
        maxX = scan.nextInt();
        
        System.out.println("How would you like to generate maze? ");
        System.out.println("Press 0 for Prims: ");
        System.out.println("Press 1 for Depth first generation: ");
        
        option = scan.nextInt();
        
        while((option!=0)&&(option!=1))
        {
            System.out.println("Value must be 0 or 1.");
            option = scan.nextInt();
            
        }
        
        //Calls Prims Generation
        if(option==0)
        {
            maze = primsMaze(maxY, maxX);
            traverseMaze(maze,maxY,maxX);
        }
        
        //Calls Depth First Generation
        if(option==1)
        {
            maze = depthFirst(maxY, maxX);
            traverseMaze(maze,maxY,maxX);
        }
        
    }
    
    public static Room[][] primsMaze(int maxY, int maxX)
    {
        Random rn = new Random();        //Random Number Generator
        //Int declarations
        int rand;    
        int tempX, tempY;
        int maxRooms = maxX*maxY;
        
        //Maze and List Declaration
        Node item = new Node();
        List mainList = new List();
        Room[][] maze = new Room[maxY][maxX];
        
        //Initialisation for Maze
        for(int i=0;i<maxY;i++)
        {
            for(int j=0;j<maxX;j++)
            {
                maze[i][j] = new Room();
            }
        }
        
        //Generate random coordinates for first room
        tempY = rn.nextInt(maxY);
        tempX = rn.nextInt(maxX);
        
        //Set coordinates in a temporary item
        item.set(tempY, tempX, 'N');

        //Find First Room and update list
        maze[item.getY()][item.getX()].setFound();
        mainList.updateList(tempY, tempX, maze, maxY, maxX, 'N');
        mainList.updateList(tempY, tempX, maze, maxY, maxX, 'E');
        mainList.updateList(tempY, tempX, maze, maxY, maxX, 'S');
        mainList.updateList(tempY, tempX, maze, maxY, maxX, 'W');

        
        while(mainList.getNoofitems()>0)
        {
            //Selecting/Copying and Deleting randomly
            rand = rn.nextInt(mainList.getNoofitems());
               
            item = mainList.getItem(rand+1);
            mainList.delete(rand+1);
            item.prepareNextRoom();
            
            if(maze[item.getY()][item.getX()].revealRoom()!='X') 
            {
                //Breaks wall and Finds room
                maze[item.getY()][item.getX()].breakWall(item.getD());
                maze[item.getY()][item.getX()].setFound();
                item.prepareNextRoom();
                maze[item.getY()][item.getX()].breakWall(item.getD());
                item.prepareNextRoom();
                
                //Set temporary variables
                tempY=item.getY();
                tempX=item.getX();
                
                //Updating List
                mainList.updateList(tempY, tempX, maze, maxY, maxX, 'N');
                mainList.updateList(tempY, tempX, maze, maxY, maxX, 'E');
                mainList.updateList(tempY, tempX, maze, maxY, maxX, 'S');
                mainList.updateList(tempY, tempX, maze, maxY, maxX, 'W');
                
            }
        }

        return maze;

    }
    
    public static void displayMaze(Room[][] maze, int height, int width)
    {
        char[][] display = new char[height*3][width*3];
        
        for (int j=0; j<height; j++)
        {
            int offy=3*j;
            for (int i=0; i<width; i++)
            {
              // each display cell will have '+' characters to make the edges of the room
              int offx=3*i;
              display[offy][offx]='+';
              display[offy][offx+2]='+';
              display[offy+2][offx]='+';
              display[offy+2][offx+2]='+';

              // do we need a top wall?
              display[offy][offx+1]=(maze[j][i].checkNWall()?'-':' ');

              // do we need a bottom wall?
              display[offy+2][offx+1]=(maze[j][i].checkSWall()?'-':' ');

              // do we need a left wall?
              display[offy+1][offx]=(maze[j][i].checkWWall()?'|':' ');

              // do we need a left right?
              display[offy+1][offx+2]=(maze[j][i].checkEWall()?'|':' ');

              // set the centre cell to be blank
              display[offy+1][offx+1]=(maze[j][i].revealRoom()=='O'?'*':' ');
            }
        }
        
        Display(display);
        
    }
    
    public static void traverseMaze(Room [][] maze, int height, int width)
    {
        Random rn = new Random();        //Random Number Generator
        Queue queue = new Queue();
        Node room = new Node();
        char[][] checkBoard = new char[height][width];
        boolean foundExit=false;
        
        int curY;
        int curX;

        //Initialise checkboard
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                checkBoard[i][j] = '.';
            }
        }
        
        //Set Starting and Finishing room
        curY=rn.nextInt(height);
        curX=rn.nextInt(width);
        setStartAndFinish(checkBoard, maze, height, width, curY, curX);
        //Add neighbouring rooms 
        queue.addPossibleRooms(maze, checkBoard, curY, curX, curY, curX);
        //Program
        TestingMazes.displayMaze(maze, height, width);
        while(!foundExit)
        {
            //Get item from queue
            //Set temporary variables coordinates
            room=queue.pop();
            curY=room.getY();
            curX=room.getX();
            
            //Check if found finishing cell
            //Else find a new room
            if(checkBoard[curY][curX]=='F')
            {
                foundExit=true;
                checkBoard[curY][curX]='X';
                maze[curY][curX].markRoom();
            }
            else
            {
                if(checkBoard[curY][curX]=='.')
                {
                    checkBoard[curY][curX]='X';
                    maze[curY][curX].markRoom();
                }
            }
                        
            //Add neighouring rooms
            if(!foundExit)
                queue.addPossibleRooms(maze, checkBoard, curY, curX, curY, curX);
            TestingMazes.displayMaze(maze, height, width);
            
            //Get user input
            try
            {
                System.in.read();
            }
            catch (IOException exception)
            {
                System.out.println("ERROR");
            }
            
        }
        System.out.println("Exit found!");
    }
    
    public static void Display(char display[][])
    {
      
      System.out.println("Maze state:");

      for (int j=0; j<display.length; j+=3)
      {
        // draw the top cells
        for (int i=0; i<display[j].length; i+=3)
        {
          System.out.print(display[j][i]);
          System.out.print(display[j][i+1]);
        }
        // draw the right-most item cell
        System.out.println(display[j][display[j].length-1]);
        
        // draw the middle cells
        int index=j+1;
        for (int i=0; i<display[j].length; i+=3)
        {
          System.out.print(display[index][i]);
          System.out.print(display[index][i+1]);
        }
        System.out.println(display[index][display[index].length-1]);
      }
      // draw the bottom of the maze
      int index=display.length-1;
      for (int i=0; i<display[index].length; i+=3)
      {
        System.out.print(display[index][i]);
        System.out.print(display[index][i+1]);
      }
      System.out.println(display[index][display[index].length-1]);

      System.out.println("");
    }
    
    public static void setStartAndFinish(char checkBoard[][], Room maze[][], int height, int width, int curY, int curX)
    {
        Random rn = new Random();
        int outerWall=rn.nextInt(4);
        
        int position;
        //North
        if(outerWall==0)
        {
            position=rn.nextInt(width);
            checkBoard[0][position]='F';
            maze[0][position].breakWall('N');
        }
        
        //South
        if(outerWall==1)
        {
            position=rn.nextInt(width);
            checkBoard[height-1][position]='F';
            maze[height-1][position].breakWall('S');
        }
        
        //East
        if(outerWall==2)
        {
            position=rn.nextInt(height);
            checkBoard[position][width-1]='F';
            maze[position][width-1].breakWall('E');
        }
        
        //West
        if(outerWall==3)
        {
            position=rn.nextInt(height);
            checkBoard[position][0]='F';
            maze[position][0].breakWall('W');
        }
        
        
        while(checkBoard[curY][curX]=='F')
        {
            curY=rn.nextInt(height);
            curX=rn.nextInt(width);
        }
        checkBoard[curY][curX] = 'X';
        maze[curY][curX].markRoom();
        
    }
    
    public static Room[][] depthFirst(int maxY, int maxX)
    {
        Random rn = new Random();        //Random Number Generator
        //Declarations
        Stack stack = new Stack(); //Stack to store coordinates
        Stack neighbours = new Stack(); //List to store neighbouring rooms
        
        //Integers that store random numbers
        int rand;
        int tempX, tempY;

        Room[][] maze = new Room[maxY][maxX]; //Maze
        
        //Temporary nodes that store coordinates
        StackItem currentPos = new StackItem(); 
        StackItem prevPos = new StackItem();
        
        //Randomisation
        tempY = rn.nextInt(maxY);
        tempX = rn.nextInt(maxX);
        
        //Sets cuurent = prev
        currentPos.y=tempY;
        currentPos.x=tempX;
        prevPos.y=tempY;
        prevPos.x=tempX;
        
        //Initialisation for Maze
        for(int i=0;i<maxY;i++)
        {
            for(int j=0;j<maxX;j++)
            {
                maze[i][j] = new Room();
            }
        }
        
        //Initialise starting position
        maze[currentPos.y][currentPos.x].setFound();
        
        do
        {
            //Adds neighbours to a seperate list
            neighbours.addNeighbours(currentPos.y,currentPos.x,maze, maxY, maxX);
            
            //If neighbours exists
            if(neighbours.getNoOfItems()!=0)
            {
                prevPos.y=currentPos.y;
                prevPos.x=currentPos.x;
                //Push previous position onto stack
                stack.push(currentPos.y, currentPos.x);
                //Selects random room
                rand = rn.nextInt(neighbours.getNoOfItems());
                currentPos = neighbours.getItem(rand);
                //Finds new room
                maze[currentPos.y][currentPos.x].setFound();
                //Breaks walls of necessary rooms
                breakWalls(currentPos, prevPos, maze);  
            }
            else
            {
                //If no rooms exists pop previous value from stack
                prevPos.y=currentPos.y;
                prevPos.x=currentPos.x;
                currentPos = stack.pop();
            }
            //Resets the neighbour list to null
            neighbours.deleteList();       
        } while(stack.getNoOfItems()!=0); //Repeat until all rooms found

        setOuterWalls(maze,maxY,maxX);
        return maze;
    }
   
    public static void breakWalls(StackItem current, StackItem prev, Room maze[][])
    {
        //For DF generation
        //Checks the coordinates of current and previous rooms and decides which
        //walls to break
        
        //North or South
        if(current.y<prev.y)
        {
            maze[prev.y][prev.x].breakWall('N');
            maze[current.y][current.x].breakWall('S');
        }
        else
        {
            maze[prev.y][prev.x].breakWall('S');
            maze[current.y][current.x].breakWall('N');
        }

        //West or East
        if(current.x<prev.x)
        {
            maze[prev.y][prev.x].breakWall('W');
            maze[current.y][current.x].breakWall('E');
        }
        else
        {
            maze[prev.y][prev.x].breakWall('E');
            maze[current.y][current.x].breakWall('W');
        }

    }
    
    public static void setOuterWalls(Room maze[][], int height, int width)
    {
        
        
        //North walls
        for(int i=0;i<width;i++)
        {
            maze[0][i].setWall('N');
        }
        
        //South walls
        for(int i=0;i<width;i++)
        {
            maze[height-1][i].setWall('S');
        }
        
        //West walls
        for(int i=0;i<height;i++)
        {
            maze[i][0].setWall('W');
        }
        
        //East walls
        for(int i=0;i<height;i++)
        {
            maze[i][width-1].setWall('E');
        }
        
    }
    
}

