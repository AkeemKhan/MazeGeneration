
package testingmazes;

public class Stack {
    private StackItem root=null;
    
    Stack(){}
    
    public void push(int y, int x)
    {
        StackItem temp = new StackItem(y,x);
        temp.next = root;
        root = temp;
    }
    
    public StackItem pop()
    {   
        StackItem temp = root;
        root = root.next;
        return temp;
    }
    
    public void addNeighbours(int y, int x, Room maze[][], int maxY, int maxX)
    {
        //North
        if(y>0)
        {
            if(maze[y-1][x].revealRoom()!='X')
            {
                push(y-1, x);

            }
        }
        
        //South
        if(y+1<maxY)
        {
            if(maze[y+1][x].revealRoom()!='X')
            {
                push(y+1, x);

            }
        }
        
        //East
        if(x+1<maxX)
        {
            if(maze[y][x+1].revealRoom()!='X')
            {
                push(y, x+1);

            }
        }
        
        //West
        if(x>0)
        {
            if(maze[y][x-1].revealRoom()!='X')
            {
                push(y, x-1);

            }
        }
        
    }
    
    public StackItem getItem(int index)
    {
        StackItem temp = root.getItem(index);
        return new StackItem(temp.y,temp.x);
    }
    
    public int getNoOfItems()
    {
        if(root==null)
            return 0;
        else
            return root.getNoOfItems();
    }
    
    public void deleteList()
    {
        root=null;
    }
    
}
