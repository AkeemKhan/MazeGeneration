package testingmazes;

public class List {
    private int noofitems = 0;
    private Node root = null;
    
    List()
    {
    }
    
    public int getNoofitems()
    {
        return noofitems;
    }
    
    public void add(int y, int x, char dir)
    {
        Node tmp=new Node(x, y, dir);
        if(root==null)
        {
            root = tmp;
        }
        else
        {
            tmp.setNext(root);
            root = tmp;
        }
        noofitems++;
    }
    
    public void delete(int index)
    {
        if(noofitems!=1)
        {
            Node temp=new Node();
            if(index == 1)
            {
                temp=root;
                temp=temp.getNext();
                root=temp;
            }
            else
                root.delete(index);
        }
        else
            root=null;
        noofitems--;
    }
    
    public Node getItem(int index)
    {
        Node item=root.getItem(index);
        return new Node(item.getX(),item.getY(),item.getD());
    }
    
    
    
    public void printList()
    {
        if(root!=null)
            root.printList();
        else
            System.out.println("NULL ITEM");
    }
    
    public void updateList(int y, int x, Room[][] maze, int maxY, int maxX, char dir)
    {
        int tempX = x;
        int tempY = y;
        
        if(dir=='N')
        {
            //Check boundaries
            if((y-1)>=0)
            {
                //Check if room is not found
                if(maze[y-1][x].revealRoom()!='X')
                {
                    this.add(y, x, 'N');
                    
                }
            }            
        }
        
        if(dir=='E')
        {
            //Check boundaries
            if((x+1)<maxX)
            {
                //Check if room is not found
                if(maze[y][x+1].revealRoom()!='X')
                {
                    this.add(y, x, 'E');
                }
            }            
        }
        
        if(dir=='S')
        {
            //Check boundaries
            if((y+1)<maxY)
            {
                //Check if room is not found
                if(maze[y+1][x].revealRoom()!='X')
                {
                    this.add(y, x, 'S');
                }
            }            
        }
        
        if(dir=='W')
        {
            //Check boundaries
            if((x-1)>=0)
            {
                //Check if room is not found
                if(maze[y][x-1].revealRoom()!='X')
                {
                    this.add(y, x, 'W');
                }
            }            
        }
        
   
        
    }
    
    public void push(int y, int x, char dir)
    {
        Node tmp=new Node(x, y, dir);
        /*if(root==null)
        {
            root = tmp;
        }
        else*/
        {
            tmp.setNext(root);
            root = tmp;
        }
        noofitems++;
    }
    
    public Node pop()
    {
        if (root==null)
            return null;
        else
        {
            Node temp = root;//new Node(root.getY(),root.getX());
            //Node newRoot = root.getNext();
            root = root.getNext();       
            return temp;
        }
    }
    
    public void deleteList()
    {
        root=null;
    }
    
}
