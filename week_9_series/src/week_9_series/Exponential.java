/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package week_9_series;

/**
 *
 * @author Mark Drozd
 */
public class Exponential extends Series 
{
   
    Exponential()
    {
        super();
    }
    Exponential(double first, double factor, int n)
    {
        super(n, first, factor);
    }
    
    @Override
    double returnElement(int j)
    {
        return first*Math.pow(dif,j-1);
    }
    

}
