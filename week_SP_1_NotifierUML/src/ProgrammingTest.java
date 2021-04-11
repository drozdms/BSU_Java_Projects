import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Mark Drozd
 */
public class ProgrammingTest 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Set<Student> students=new HashSet();
        
        try (Scanner scanner = new Scanner(new File("input.txt"))) 
        {
            while (scanner.hasNext())
            {
                String type =scanner.next();
                String name=scanner.next();
                String login=scanner.next();
                String email=scanner.next();
                String academic=scanner.next();
                switch (type)
                {
                    case "Undergraduate":
                        Undergraduate undergraduate=new Undergraduate(login, email);
                        undergraduate.setName(name);
                        undergraduate.setTutor(new Academic(academic));
                        students.add(undergraduate);
                        break;
                        
                    case "Postgraduate":
                        Postgraduate postgraduate=new Postgraduate(login, email);
                        postgraduate.setName(name);
                        postgraduate.setSupervisor(new Academic(academic));
                        students.add(postgraduate);
                        break;
                        
                    default:
                        throw new InvalidStudentException();
                }
            }
            
            
            Course course=new Course("Programming", students);
            Notifier notifier=new Notifier(course.getPostGraduates("Thorne"));
            notifier.doNotifyAll("Your supervisor has won the Nobel Prize in Physics 2017!");
        }
        
        catch (FileNotFoundException | NoSuchElementException | InvalidStudentException e)
        {
            System.out.println(e.getMessage());
        }
        
   }
        
        
}






