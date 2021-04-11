import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Mark Drozd
 */
public class SystemOfLinearEquations {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws SolutionException
     */
    public static void main(String[] args) throws FileNotFoundException, SolutionException
    {
        Scanner scanner=new Scanner(new File("matrix.txt"));
        String str;
        List<String> list=new ArrayList();
        while (true)
        {
            if (scanner.hasNext())
            {
               if (!(str=scanner.nextLine()).isEmpty())
                   list.add(str);                   // достаем непустые строки
  
            }
            else break;
        }
        String[] stringArray=list.toArray(new String[0]);
        int n=stringArray.length;
        double[][] matrix = new double[n][n+1];
        double[] solutions=new double[n];
        
        initializeMatrix(matrix, stringArray);
        matrixToTriangularForm(matrix);
        findSolutions(matrix, solutions);
       
        
        for (double i : solutions)
            System.out.println(i);
    }
    
    
    private static boolean swapRows(double[][] matrix, int k, int i)
    {
       
        
        int length=matrix.length;
        
         if (k==i || k>length || i>length)
            return false;
        
         for (int j=0; j<length+1;++j)
         {
             double temp=matrix[k][j];
             matrix[k][j]=matrix[i][j];
             matrix[i][j]=temp;
         }
        
        return true;
    }
    
    
    private static boolean divideRow(double[][] matrix, int i)
    {
       
        
        int length=matrix.length;
        
         if (i>length)
            return false;
        double a = matrix[i][i];
         for (int j=0; j<length+1;++j)
             matrix[i][j]/=a;
        
        return true;
    }
    
    private static boolean transformRows(double[][] matrix, int i)
    {
       
        
        int length=matrix.length;
         if (i>length)
            return false;
        
         for (int k=i+1; k<length; ++k)
         {
            double a=matrix[k][i];
            for (int j=i; j<length+1; ++j)
                matrix[k][j]-=matrix[i][j]*a;
         }
         
         
        return true;
    }
    
    
    private static boolean initializeMatrix(double[][] matrix, String[] stringArray) throws SolutionException
    {
       
        int n=matrix.length;
        for (int i=0; i<n; ++i)
        {
            String str=stringArray[i];
            StringTokenizer tokenizer=new StringTokenizer(str);
            int countTokens=tokenizer.countTokens();
            if (countTokens>(n+1))
                throw new SolutionException("Matrix is not square");
            for (int j=0; j<n+1; ++j)
            {
                try 
                {
                if (tokenizer.hasMoreTokens())
                    matrix[i][j]=Double.parseDouble(tokenizer.nextToken());
                else matrix[i][j] = 0;
                }
                catch (NumberFormatException exc)
                {
                   SolutionException ex= new SolutionException("Not all numbers are actually numbers in your file. Be careful");
                   throw ex;
                }
            }
        }
            
        
        
         return true;
    }
        
       
    
    
    
    private static boolean matrixToTriangularForm(double[][] matrix) throws SolutionException
    {
        int n=matrix.length;
         for (int j=0; j<n; ++j)         // приведение матрицы к треугольному виду
        {
                boolean strIsFound=false;
                int k;
                for (k=j; k<n;++k)            // поиск строки с ненулевым j-m элементом
                {
                    if (matrix[k][j]!=0)
                    {
                        strIsFound=true;
                        break;
                    }
                }
                
                if (strIsFound)
                    swapRows(matrix, k, j);
                else throw new SolutionException("System is either inconsistent or has an infinite number of solutions");     // на главной диагонали треугольной матрицы найдется 0
                
                divideRow(matrix, j);
                transformRows(matrix, j);
        }
        
        return true;
    }
    
    
     private static boolean findSolutions(double[][] matrix, double[] solutions) throws SolutionException
    {
        int n=matrix.length;
        for (int i=0; i<n; ++i)
        {
            double num=matrix[n-i-1][n];
            for (int j=0; j<i; ++j)
            {
                num-=solutions[n-1-j]*matrix[n-i-1][n-j-1];
            }
            solutions[n-1-i]=num;
            
        }
        
        return true;
    }
    
}




class SolutionException extends Exception
{
	public SolutionException() {}
	public SolutionException(String s) 
	{
		super(s);
	}
}

