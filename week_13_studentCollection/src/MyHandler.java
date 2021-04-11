import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 *
 * @author Mark Drozd
 */
public class MyHandler extends DefaultHandler 
{
    private StudentCollection studentCollection;
    private Student currentStudent;
    private Integer curCourse;
    private String curSubject;
    private Integer curMark;
    private boolean isInvalid;
    static final String STUDENT_TAG="student";
    static final String MARKS_TAG="marks";
    static final String SUBJECT_TAG="subject";
    
    
    
    public MyHandler()
    {
        super();
        studentCollection=new StudentCollection();
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
            case STUDENT_TAG:
            {
                String surname=attributes.getValue("surname");
                try 
                {
                    Integer number=Integer.parseInt(attributes.getValue("number"));
                    currentStudent=new Student(number, surname);
                }
                catch(NumberFormatException exc)
                {
                    // do nothing -- this student is invalid
                    isInvalid=true;
                    currentStudent=null;
                }    
            }    
            break;
            
            case MARKS_TAG:
            {
                try
                {
                    curCourse=Integer.parseInt(attributes.getValue("course"));
                }
                catch (NumberFormatException exc)
                {
                    isInvalid=true;
                    curCourse=null;
                }
            }
            break;
            
            case SUBJECT_TAG:
            {
               curSubject=attributes.getValue("subject");
               try
               {
                   curMark=Integer.parseInt(attributes.getValue("mark"));
               }
               catch (NumberFormatException exc)
               {
                   isInvalid=true;
                   curMark=null;
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
            case SUBJECT_TAG:
            {
                if (curCourse!=null & curMark != null & currentStudent != null)
                    currentStudent.addMark(curCourse, curSubject, curMark);
            }
            break;
            
            case STUDENT_TAG:
            {
                if (currentStudent != null)
                    studentCollection.add(currentStudent);
            }
            break;
        }
    }
   
    
    
    public StudentCollection getCollection()
    {
        return studentCollection;
    }
    
    public boolean isInvalid()
    {
        return isInvalid;
    }
   
}