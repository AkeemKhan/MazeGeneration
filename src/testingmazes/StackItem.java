
package testingmazes;

public class StackItem {
    public int y;
    public int x;
    public StackItem next;
    
    StackItem()
    {}
    
    StackItem(int y, int x)
    {
        this.x=x;
        this.y=y;
    }
    
    public StackItem getItem(int index)
    {
        if(index!=0)
        {
            return next.getItem(index-1);
        }
        else
        {
            return new StackItem(this.y,this.x);
        }
    }
    
    public int getNoOfItems()
    {
        if(next==null)
            return 1;
        else
            return 1+ next.getNoOfItems();
    }
    
}