/**
 *
 * @author Mark Drozd
 */
public class Postgraduate extends Student 
{
    

    public Postgraduate(String login, String email) 
    {
        super(login, email);
    }
    
     public Postgraduate(String name)
    {
        super(name);
    }
     
     
     public void setSupervisor(Academic supervisor)
    {
        this.supervisor=supervisor;
    }
    
    public Academic getSupervisor()
    {
        return supervisor;
    }
    
    @Override
    public String toString()
    {
        return "Postgraduate "+ super.toString()+" Supervisor: "+supervisor.toString();
    }

     private Academic supervisor;
}
