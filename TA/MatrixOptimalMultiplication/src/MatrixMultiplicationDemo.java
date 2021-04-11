
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Mark Drozd
 */
public class MatrixMultiplicationDemo 
{

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner scanner=new Scanner(new File("input.txt"));
        ArrayList<Matrix> list=new ArrayList<>();
        
        int N=scanner.nextInt();
        long[][] table=new long[N][N];
        while (scanner.hasNext())
        {
            long n=scanner.nextLong();
            long m=scanner.nextLong();
            list.add(new Matrix(n,m));
        }
        
        
        int k=0;
        
        
        while (k<N)
        {
            
            for (int i=0, j=k; i<N-k && j<N; ++i, ++j)
            {
                if (i==j)
                    table[i][j]=0;
                else if (j==i+1)
                    table[i][j]=list.get(i).n*list.get(i).m*list.get(j).m;
                else 
                {
                    long min=table[i][i]+table[i+1][j]+list.get(i).n*list.get(i).m*list.get(j).m; 
                    
                    for (int l=i+1; l<j; ++l)
                    {
                        long value=table[i][l]+table[l+1][j]+list.get(i).n*list.get(l).m*list.get(j).m;
                        if (value<min)
                            min=value;
                    }
                    
                    table[i][j]=min;
                }
            }
            
            
            k++;
        }
        
        
       FileWriter writer=new FileWriter(new File("output.txt"),false);
        writer.write(((Long)table[0][N-1]).toString());
        writer.write(System.getProperty( "line.separator" ));
        writer.close();
        
        
    }

}

class Matrix
{
    long n;
    long m;
    public Matrix(long n, long m)
    {
        this.n=n;
        this.m=m;
    }
}
