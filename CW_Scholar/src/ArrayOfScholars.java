/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */

import java.io.IOException;

import java.util.ArrayList; 
import java.util.Collections;



public class ArrayOfScholars<T extends Scholar> extends ArrayList<T>
{
    
    
    
    public void show() throws EmptyListException
    {
         if (this.isEmpty())
            throw new EmptyListException();
        this.forEach((i) -> {
            System.out.println(i.toString());
        });
        
    }
    
    
    public int count(T scholar) throws EmptyListException
    {
      
         if (this.isEmpty())
            throw new EmptyListException();
        return Collections.frequency(this, scholar);
        
    }
    
    
    int binarySearch(T scholar) throws EmptyListException
    {
         if (this.isEmpty())
            throw new EmptyListException();
        Collections.sort(this);
        return Collections.binarySearch(this, scholar);
    }
    
    
    T min() throws EmptyListException
    {
         if (this.isEmpty())
            throw new EmptyListException();
        return Collections.min(this);
    }
        

}


class EmptyListException extends IOException
{
    public EmptyListException()
    {
        super("This list is empty");
    }
    
    public void out()
    {
        System.out.println(this.getMessage());
    }
}


