/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
public abstract class Scholar implements Comparable<Scholar>
{
    
    
     @Override
    public int compareTo(Scholar o) 
    {
        
        
        if (this.school.equals(o.school))
            return this.surname.compareTo(o.surname);
        
        return this.school.compareTo(o.school);
        
   
    }
    
    
     @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Scholar)
            return this.surname.equals(((Scholar) obj).surname) &
                this.school.equals(((Scholar) obj).school) &
                this.average.equals(((Scholar) obj).average);
        
        return false;
        
    }
    
    
     @Override
    public abstract String toString();
    
    
    
        protected String surname;
        protected String school;
        protected Double average;
        
}


class Student extends Scholar
{
    
    public Student()
    {
        surname="Unknown";
        school="Unknown";
        average=-1.;
        year=0;
    }
    
    
    public Student(String surname, String school, double average, int year)
    {
        this.surname=surname;
        this.school=school;
        this.average=average;
        this.year=year;
    }
   
    
    @Override
    public String toString()
    {
        StringBuilder str=new StringBuilder();
        
        str.append(this.surname);
        str.append(", ");
        str.append(this.school);
        str.append(", ");
        str.append(this.year.toString());
        str.append(", ");
        str.append(this.average.toString());
        
        
        return str.toString();
    }
    
    
    @Override
    public boolean equals(Object o)
    {
        
        if (o instanceof Student)
            return (super.equals(o) &
                this.year.equals(((Student) o).year));
        
        
        return false;
    }
    
    
    
    
    private Integer year;

   
}


class Pupil extends Scholar
{
    
   public Pupil()
    {
        surname="Unknown";
        school="Unknown";
        average=-1.;
        form=0;
        behavior=Behavior.BAD;
    }
    
    
    public Pupil(String surname, String school,  int form, double average, String behavior)
    {
        this.surname=surname;
        this.school=school;
        this.average=average;
        this.form=form;
       switch (behavior) {
           case "bad":
               this.behavior=Behavior.BAD;
               break;
           case "good":
               this.behavior=Behavior.GOOD;
               break;
           case "almost":
               this.behavior=Behavior.ALMOST;
               break;
           default:
               break;
       }
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder str=new StringBuilder();
        
        str.append(this.surname);
        str.append(", ");
        str.append(this.school);
        str.append(", ");
        str.append(this.form.toString());
        str.append(", ");
        str.append(this.average.toString());
        str.append(", ");
        str.append(this.behavior.toString());
        
        
        return str.toString();
    }
    
    
     public boolean equals(Pupil o)
    {
        return (this.surname.equals(o.surname) &
                this.school.equals(o.school) &
                this.average.equals(o.average) &
                this.form.equals(o.form) &
                this.behavior.equals(o.behavior));
    }
    
     
     @Override
    public boolean equals(Object o)
    {
        
        if (o instanceof Pupil)
            return (super.equals(o) &
                this.form.equals(((Pupil) o).form) &
                     this.behavior.equals(((Pupil) o).behavior));
        
        
        return false;
    }
    
     
    
    
    private Integer form;
    public enum Behavior
            {
                GOOD,
                ALMOST, 
                BAD
            };
    
    
    private Behavior behavior;
}
