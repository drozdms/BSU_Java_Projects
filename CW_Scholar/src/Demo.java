import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 *
 * @author Mark Drozd
 */
public class Demo

{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    
    {
        
        ArrayOfScholars<Student> list_1=new ArrayOfScholars();
       ArrayOfScholars<Pupil> list_2=new ArrayOfScholars();
        Scanner scanner = null;
        
        
        try 
        {
            scanner=new Scanner(new File("input_Student.txt"));
            scanStudentListFromFile(scanner, list_1);
            
            
            list_1.show();
            Student mom=new Student("Mom", "Oxford", 9.0, 4);
            System.out.println(list_1.count(mom));
             System.out.println(list_1.binarySearch(mom));
             System.out.println(list_1.min().toString());
             
             
             
             scanner=new Scanner(new File("input_Pupil.txt"));
            scanPupilListFromFile(scanner, list_2);
            
            
            list_2.show();
            Pupil ivan=new Pupil("Ivan", "BSU", 2, 9., "good");
            System.out.println(list_2.count(ivan));
             System.out.println(list_2.binarySearch(ivan));
             System.out.println(list_2.min().toString());
            
          
            
            
        }
        catch (FileNotFoundException exc)
        {
            if (scanner!=null)
                scanner.close();
            scanner=null;
        }
        catch (EmptyListException exc) 
        {
            exc.out();
        }
        
      
        
        finally 
        {
            if (scanner==null)
            {
                System.out.println("Input File Not Found");
                System.exit(0);
            }
            
            
            else 
            {
                System.out.println("Closing scanner");
                scanner.close();
            }
        }   
        
    }
    
    
    
    static void scanStudentListFromFile(Scanner scanner, ArrayOfScholars<Student> list)
    {
        while (scanner.hasNext())
        {
            try
            {
                
                String surname=scanner.next();
                String school=scanner.next();
                
                Double average=scanner.nextDouble();
                Integer year=scanner.nextInt();
                list.add(new Student(surname, school, average, year));
                
            }
            
            catch(InputMismatchException e)
            {
                
                scanner.next();   // skip this token
                
                // continue to scan
            }
            
            
            catch (NoSuchElementException exc)
            {
                // scanner.hasNext()=false
            }
            
        }
    }
    
    
    static void scanPupilListFromFile(Scanner scanner, ArrayOfScholars<Pupil> list)
    {
        while (scanner.hasNext())
        {
            try
            {
                String surname=scanner.next();
                String school=scanner.next();
                Integer form =scanner.nextInt();
                Double average=scanner.nextDouble();
                String behavior=scanner.next();
                list.add(new Pupil(surname, school, form, average, behavior));
                
            }
            
            catch(InputMismatchException exc)
            {
                // continue to scan
            }
            
            catch (NoSuchElementException exc)
            {
                // scanner.hasNext()=false
            }
                
            
        }
    }

}
