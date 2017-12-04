package testingmazes;

public class Node {
    private Node next;      //Points to next node in list
    private int x;          //X Coordinate of a room
    private int y;          //Y Coordinate of a room
    private char direction; //Direction of wall

    public Node()
    {}

    public Node(int x, int y, char d)
    {
        this.x=x;
        this.y=y;
        direction=d;
    }
    
    public Node(int y, int x)
    {
        this.x=x;
        this.y=y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public char getD()
    {
        return direction;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setD(char d)
    {
        this.direction = d;
    }
    
    public void set(int y, int x, char d)
    {
        this.x = x;
        this.y = y;
        this.direction = d;
    }
    
    public void setCoord(int y, int x)
    {
        this.x=x;
        this.y=y;
        
    }

    public Node getNext()
    {
        return next;
    }
    
    public void setNext(Node item)
    {
        next = item;
    }

    public void delete(int index)
    {
        if(index == 2)
        {
            this.next = next.next;
        }
        else
        {
            next.delete( index-1);
        }
    }
    
    public Node getItem(int index)
    {
        if(index != 1)
        {
            return next.getItem(index-1);
        }
        return new Node(this.x,this.y,this.direction);
    }
    
    public void printList()
    {
        Node temp = this;
        while(temp.next!=null)
        {
            System.out.println("Y: "+temp.getY()+" X: "+temp.getX()+" CHAR: "+temp.getD());
            temp=temp.next;
        }
        System.out.println("Y: "+temp.getY()+" X: "+temp.getX()+" CHAR: "+temp.getD());
            
    }
    
    public void prepareNextRoom()
    {
        //Get the Opposite Wall
        
        switch (direction) {
            case 'N':
                direction = 'S';
                y--;
                break;
            case 'S':
                direction = 'N';
                y++;
                break;
            case 'E':
                direction = 'W';
                x++;
                break;
            case 'W':
                direction = 'E';
                x--;
                break;    
        }
    }
    
    public void push(int y, int x)
    {
        if(next==null)
            next=new Node(y,x);
        else
            next.push(y,x);
    }
    
    public void printQueue()
    {
        Node temp = this;
        while(temp.next!=null)
        {
            System.out.println("Y: "+temp.getY()+" X: "+temp.getX());
            temp=temp.next;
        }
        System.out.println("Y: "+temp.getY()+" X: "+temp.getX());
            
    }
    
    
    
}
