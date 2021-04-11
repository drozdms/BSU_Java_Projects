import java.awt.Point;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 *
 * @author Mark Drozd
 */
public class MyHandler extends DefaultHandler 
{
   
    
    ArrayList<Rectangle> list;
    
    
    Integer curXFirst;
    Integer curYFirst;
    Integer curXSecond;
    Integer curYSecond;
    
    boolean isInvalid;
    
    final String RECTANGLE_TAG="rectangle";
    
    public MyHandler()
    {
        super();
        list =new ArrayList();
        isInvalid=false;
    }
    
    @Override
    public void startElement(String uri,
                         String localName,
                         String qName,
                         Attributes attributes)
                  throws SAXException
    {
        
        switch (qName)
        {
            case RECTANGLE_TAG:
            {
                
                try 
                {
                    curXFirst=Integer.parseInt(attributes.getValue("xFirst"));
                     curYFirst=Integer.parseInt(attributes.getValue("yFirst"));
                    
                    curXSecond=Integer.parseInt(attributes.getValue("xSecond"));
                    curYSecond=Integer.parseInt(attributes.getValue("ySecond"));
                }
                catch(NumberFormatException exc)
                {
                    // do nothing -- this student is invalid
                    isInvalid=true;
                    curXFirst=null;
                    curYFirst=null;
                    curXSecond=null;
                    curYSecond=null;
                }    
            }    
            break;
            
        }
    }
    
    @Override
    public void endElement(String uri,
                       String localName,
                       String qName)
                throws SAXException
    {
        switch (qName)
        {
            case RECTANGLE_TAG:
            {
                if (curXFirst!=null & curYFirst != null & curXSecond != null & curYSecond != null)
                {
                    Rectangle rect=new Rectangle (new Point(curXFirst, curYFirst),new Point(curXSecond, curYSecond));
                   list.add(rect);
                }
            }
            break;
            
           
        }
    }
   
    
    
    public ArrayList<Rectangle> getCollection()
    {
        return list;
    }
    
    public boolean isInvalid()
    {
        return isInvalid;
    }
   
}