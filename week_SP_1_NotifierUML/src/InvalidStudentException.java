
import java.io.IOException;

public class InvalidStudentException extends IOException
{
    InvalidStudentException()
    {
        super("Invalid input");
    }
    
}