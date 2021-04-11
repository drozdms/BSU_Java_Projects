/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package week_9_series;
import java.io.File;
import java.text.DecimalFormat;
import java.io.IOException;
import java.io.FileWriter;
/**
 *
 * @author Mark Drozd
 */
abstract class Series 

{
    protected int n;
    protected double first;
    protected double dif;
    
    
    Series() 
    {
        n=0;
        first=0;
        dif=0;
    }
    Series(int n, double first, double dif)
    {
        this.n=n;
        this.first=first;
        this.dif=dif;
    }
    
    abstract double returnElement(int j);
    public double sum()
    {
        double sum=0;
        for (int i=1; i<=n; ++i)
        {
            Double j=returnElement(i);
            if (j.isInfinite())
                 return sum;
            sum+=j;
        }
        
        return sum;
    }
    
    @Override
    public String toString()
    {
       StringBuilder builder=new StringBuilder();
       DecimalFormat format=new DecimalFormat("0.0#");
       for (int i=1; i<=n; ++i)
       {
           Double j=returnElement(i);
           if (j.isInfinite())
               return builder.toString();
           builder.append(format.format(j)).append(" ");
       }
        
       return builder.toString();
    }
    
    
    public void toFile(File file) throws IOException
    {
        if (!file.canWrite())
            return;
        FileWriter writer=new FileWriter(file, true);
        writer.write(toString());
        writer.write(System.getProperty("line.separator"));
        writer.close();
    }
    
    
    public void setNumberofElements(int n)
    {
        this.n=n;
    }
    
    public void setFirstElement(double first)
    {
        this.first=first;
    }
    
    public void setDif(double dif)
    {
        this.dif=dif;
    }
    
    

}
