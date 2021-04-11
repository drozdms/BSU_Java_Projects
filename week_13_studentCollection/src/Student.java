import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mark Drozd
 */
public class Student 

{
    
    public Student()
    {
        this.number=-1;
        this.lastName="N/D";
        marks = new HashMap(4, 1);
        for (int i=1; i<=4; ++i)
        {
            HashMap<String, Integer> marksCurrentMap=new HashMap(7,1);
            marks.put(i, marksCurrentMap);
        }
    }
    
    
    public Student(Integer number, String lastName)
    {
        
        this.number=number;
        this.lastName=lastName;
        marks = new HashMap(4, 1);
        for (int i=1; i<=4; ++i)
        {
            HashMap<String, Integer> marksCurrentMap=new HashMap(7,1);
            marks.put(i, marksCurrentMap);
        }
    }
    
    
    public Integer addMark(Integer term, String subject, Integer mark)
    {
        marks.get(term).put(subject, mark);
        return mark;
    }
    
    @Override
    public boolean equals (Object obj)
    {
        if (super.equals(obj))
            return true;
        if (obj instanceof Student)
            if (this.number.equals(((Student) obj).number))
                return true;
        return false;
    }
    
    
    
    public String getName()
    {
        return lastName;
    }
    
    public Integer getSubjectMark(Integer term, String subject)
    {
        return marks.get(term).get(subject);
    }
    
    
    public void updateInfo(Student o)
    {
        for (Map.Entry<Integer, HashMap<String, Integer>> entry: marks.entrySet())
        {
            HashMap<String, Integer> exams=entry.getValue();
            HashMap<String, Integer> examsToCopy=o.marks.get(entry.getKey());
            for (Map.Entry<String, Integer> i: examsToCopy.entrySet())
            {
                exams.put(i.getKey(), i.getValue());
            }
        }
        
    }
    
    @Override
    public String toString()
    {
        StringBuilder s=new StringBuilder(lastName);
        s.append(" ").append(number.toString());
        return s.toString();
    }
    
    
    
    public ArrayList<String> toXML()
    {
        ArrayList<String> text=new ArrayList();
        StringBuilder str=new StringBuilder();
        str.append("<student surname=\"");
        str.append(lastName);
        str.append("\" number=\"");
        str.append(number.toString());
        str.append("\">");
        text.add(str.toString());
        
        for (Map.Entry<Integer, HashMap<String, Integer>> entry: marks.entrySet())
        {
            if (entry.getValue().isEmpty())
                continue;
            str=new StringBuilder();
            str.append("<marks course=\"");
            str.append(entry.getKey().toString());
            str.append("\">");
            text.add(str.toString());
            HashMap <String, Integer> exams=entry.getValue();
            for (Map.Entry<String, Integer> subjectMark: exams.entrySet())
            {
                str=new StringBuilder();
                str.append("<subject subject=\"");
                str.append(subjectMark.getKey());
                str.append("\" mark=\"");
                str.append(subjectMark.getValue().toString());
                str.append("\" />");
                text.add(str.toString());
            } 
            
            text.add("</marks>");
        }
        
        text.add("</student>");
        return text;
    }
    
    
    
    
    
    
    private Integer number;
    private String lastName;
    private HashMap<Integer, HashMap<String, Integer>> marks;

}
