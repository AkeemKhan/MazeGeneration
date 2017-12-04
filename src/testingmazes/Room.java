package testingmazes;
public class Room {
    private boolean nWall=true;
    private boolean eWall=true;
    private boolean sWall=true;
    private boolean wWall=true;
    private char room='O';
    
    Room()
    {}
    
    //Testing Methods
    public String isNWall()
    {
        if(nWall)
            return "North";
        else
            return "North Broken";
    }
    
    public String isEWall()
    {
        if(eWall)
            return "East";
        else
            return "East Broken";
    }
    
    public String isSWall()
    {
        if(sWall)
            return "South";
        else
            return "South Broken";
    }
    
    public String isWWall()
    {
        if(wWall)
            return "West";
        else
            return "West Broken";
    }
    
    //Checking Methods
    public boolean checkNWall()
    {
        if(nWall)
            return true;
        else
            return false;
    }
    
    public boolean checkEWall()
    {
        if(eWall)
            return true;
        else
            return false;
    }
    
    public boolean checkSWall()
    {
        if(sWall)
            return true;
        else
            return false;
    }
    
    public boolean checkWWall()
    {
        if(wWall)
            return true;
        else
            return false;
    }
    
    public char revealRoom()
    {
        return room;
    }
    
    //Required Methods
    public void breakWall(char dir)
    {
        switch (dir) {
            case 'N':
                nWall=false;                
                break;
            case 'E':
                eWall=false;                
                break;
            case 'S':
                sWall=false;                
                break;
            case 'W':
                wWall=false;                
                break;   
        }
    }
    
    public void setFound()
    {
        room='X';
    }
            
    public void markRoom()
    {
        room='O';
    }
    
    public void setWall(char dir)
    {
        switch (dir) {
            case 'N':
                nWall=true;                
                break;
            case 'E':
                eWall=true;               
                break;
            case 'S':
                sWall=true;               
                break;
            case 'W':
                wWall=true;               
                break;  
        }
    }
    
    
}   
