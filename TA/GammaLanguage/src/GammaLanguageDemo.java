
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Mark Drozd
 */
public class GammaLanguageDemo 
{
    /*
    
    
     static int[] A;
    static int[] B;
    
    int L(int i, int j)
    {
        if (i==0 || j==0)
            return 0;
        
        int t=L(i-1, j);
        int tl=L(i-1,j-1);
        int l=L(i,j-1);
        
        
        if (t==tl & l==tl)
        {
            if (A[i-1]==B[j-1])
                return tl+1;
            else return tl;
        }
        else if ((t==tl)&(l!=tl))
            return l;
        else return t;
    }
    
    */

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Scanner scanner=new Scanner(new File("input.txt"));
        
        int N=scanner.nextInt();
        int M=scanner.nextInt();
        
        
        boolean b=N<M;
        
        
        int[] A=new int[N];
        int[] B=new int[M];
        
        for (int i=0; i<N; ++i)
            A[i]=scanner.nextInt();
        for (int i=0; i<M; ++i)
            B[i]=scanner.nextInt();
        
        int[][] matrix=new int[N+1][M+1];
        
        
        for (int i=0; i<N; ++i)
        {
            for (int j=0; j<M; ++j)
            {
                int t=matrix[i][j+1];
                int tl=matrix[i][j];
                int l=matrix[i+1][j];
                
                if (t==tl & l==tl)
                {
                    matrix[i+1][j+1]=matrix[i][j];
                    if (A[i]==B[j])
                        matrix[i+1][j+1]+=1;
                }
                else if ((t==tl)&(l!=tl))
                    matrix[i+1][j+1]=matrix[i+1][j];
                else 
                    matrix[i+1][j+1]=matrix[i][j+1];
            }
        }
        
        FileWriter writer=new FileWriter(new File("output.txt"),false);
        writer.write(((Integer)matrix[N][M]).toString());
        writer.write(System.getProperty( "line.separator" ));
        writer.close();
        
    }

}
