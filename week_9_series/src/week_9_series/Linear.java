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
class Linear extends Series 
{
    
    public Linear()
    {
        super();
        dif=0;
        first=0;
    }
    
    public Linear(double first, double dif, int n)
    {
        super(n, first, dif);
    }
    
    @Override
    double returnElement(int j)
    {
        return first+(j-1)*dif;
    }

}
