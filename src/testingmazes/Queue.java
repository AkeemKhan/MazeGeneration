
package testingmazes;

public class Queue {
    Node firstItem=null;
    
    public Queue()
    {}
    
    public void push(int y, int x)
    {
        if(firstItem==null)
        {
            firstItem = new Node(y, x);
        }
        else
        {
            firstItem.push(y,x);
        }
       
    }
    
    public Node getFirstItem()
    {
        Node temp = new Node(firstItem.getY(), firstItem.getX());
        return temp;
    }
    
    public Node pop()
    {
        if(firstItem!=null)
        {
            Node returnNode = new Node(firstItem.getY(), firstItem.getX());
            Node temp = new Node();
            temp = firstItem.getNext();
            firstItem = temp;
            return returnNode;
        }
        else return null;
    }
    
    public void printQueue()
    {
        if(firstItem!=null)
            firstItem.printQueue();
        else
            System.out.println("NULL ITEM");
    }
    
    public void addPossibleRooms(Room maze[][], char[][] checkBoard, int curY, int curX, int maxY, int maxX)
    {
        //Check North Room
        if((maze[curY][curX].checkNWall()==false)&&(checkBoard[curY-1][curX]!='X'))
            push(curY-1,curX);
        
        //Check East Room
        if((maze[curY][curX].checkEWall()==false)&&(checkBoard[curY][curX+1] != 'X'))
            push(curY,curX+1);

        //Check South Room
        if((maze[curY][curX].checkSWall()==false)&&(checkBoard[curY+1][curX] != 'X'))
            push(curY+1,curX);
  
        //Check West Room
        if((maze[curY][curX].checkWWall()==false)&&(checkBoard[curY][curX-1]!='X'))
            push(curY,curX-1);

    }
}
