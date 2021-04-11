/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */


class LOTRCharacter implements Comparable<LOTRCharacter>
{
    private Integer yearOfBirth;
    private String name;
    
    LOTRCharacter(String n, int year)
    {
        name=n;
        yearOfBirth=year;
    }

    
    @Override
    public int compareTo(LOTRCharacter other)
    {
        
        int compare=yearOfBirth.compareTo(other.yearOfBirth);
        if (compare==0 && name.equals(other.name))
            return 0;
        else if (compare==0) 
            return -1;
        else return compare;
        
    }
    
    @Override
    public String toString()
    {
        String string=name+" "+yearOfBirth.toString();
        return string;
    }
    
    @Override
    public boolean equals (Object otherObject)
    {
       if (otherObject instanceof LOTRCharacter)
       {
           LOTRCharacter otherCharacter=(LOTRCharacter)otherObject;
           return name.equals(otherCharacter.name)&&yearOfBirth.equals(otherCharacter.yearOfBirth);
           
       }
       else return false;
    }
    
}