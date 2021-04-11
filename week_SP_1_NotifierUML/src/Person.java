/**
 *
 * @author Mark Drozd
 */
public class Person 
{
    Person(String name)
    {
        this.name=name;
    }
    
    String getName()
    {
        return name;
    }
    
    void setName(String name)
    {
        this.name=name;
    }
    
    @Override
    public String toString()
    {
        return "Person "+name;
    }
    
    
    
    protected String name;

}
