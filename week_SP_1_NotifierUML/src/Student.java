/**
 *
 * @author Mark Drozd
 */
public class Student extends Person implements Notifiable
{

    public Student(String name) 
    {
        super(name);
    }
    
    public Student(String login, String email)
    {
        super("N/D");
        this.login=login;
        this.email=email;
    }
    
    public String getLogin()
    {
        return login;
    }
    
    public void setLogin(String login)
    {
        this.login=login;
    }
    
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }
    
    @Override
    public void notify(String message) 
    {
        // todo stuff
        System.out.println("Message ''" +message +"'' was sent to " + this.toString());
    }
    
    @Override
    public String toString()
    {
        return "Student "+name+" "+login+" "+email;
    }
    
    
    protected String login;
    protected String email;
   
    
}
