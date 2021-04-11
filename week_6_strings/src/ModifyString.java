/**
 *
 * @author Mark Drozd
 */
public class ModifyString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String i : args)
        {
            builder.append(i);
            builder.append(' ');
            
        }
        
        System.out.println(modifyString(builder).toString());
        
        
    }
    
   
    
    
    static StringBuilder modifyString(StringBuilder builder)
    {
        for (int i=0; i<builder.length(); ++i)
        {
            int k=i;
            if (builder.charAt(i)=='0')
            {
               if (i!=0) 
               {
                if (Character.isDigit(builder.charAt(i-1)) | builder.charAt(i-1)=='.')
                    continue;
                else k=findZeroSequenceFinalIndex(builder,i);
               }
               else k=findZeroSequenceFinalIndex(builder,i);
            
               builder.delete(i, k);
            }
        }
        
        return builder;
    }
    
    
    static int findZeroSequenceFinalIndex(StringBuilder builder, int i)
    {
        int k;
        for (k=i; k<builder.length(); ++k)
        {
            if (builder.charAt(k)!='0')
                break;
        }    
        return k;
    }
}
