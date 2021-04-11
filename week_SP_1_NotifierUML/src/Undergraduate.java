/**
 *
 * @author Mark Drozd
 */
public class Undergraduate extends Student
{
    
    public Undergraduate(String name) 
    {
        super(name);
    }


    public Undergraduate(String login, String email) 
    {
        super(login, email);
    }
    
    public Academic getTutor()
    {
        return tutor;
    }
    
    public void setTutor(Academic tutor)
    {
        this.tutor=tutor;
    }
    
    @Override
    public String toString()
    {
        return "Undergraduate "+ super.toString()+" Tutor: "+tutor.toString();
    }
    
    
    private Academic tutor;

}
