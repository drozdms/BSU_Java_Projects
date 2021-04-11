/**
 *
 * @author Mark Drozd
 */
public class Tour 
{
    private String country;
    private String description;
    private double price;
    
    
    public Tour(String country, String description, double price)
    {
        this.country=country;
        this.description=description;
        this.price=price;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void setCountry(String country)
    {
        this.country=country;
    }
    
    public void setDescription(String description)
    {
        this.description=description;
    }
    
    public void setPrice(double price)
    {
        this.price=price;
    }
    
    
    
    

}
