
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Mark Drozd
 */
public class TreeNode extends DefaultMutableTreeNode
{
    public TreeNode(Object obj)
    {
        super(obj);
    }
    
    public TreeNode(Object obj, boolean b)
    {
        super(obj,b);
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof DefaultMutableTreeNode)
        {
            Object first=this.getUserObject();
            Object second=((DefaultMutableTreeNode)obj).getUserObject();
            if ((first instanceof Student)&(second instanceof Student))
            {
                return ((Student)first).getName().equals(((Student)second).getName());
            }
            else return first.equals(second);
        }
        
        return this.equals(obj);
    }
    
    
    TreeNode searchChild(Object obj)
    {
        TreeNode node=null;
        Enumeration kids=this.children();
        while (kids.hasMoreElements())
        {
            node=(TreeNode)kids.nextElement();
            if (node.getUserObject().toString().equals(obj.toString()))
                return node;
        }
        
        return null;
    }
    
    
    
    TreeNode addStudent(Integer courseNumber, Integer groupNumber, String surname)
    {
        TreeNode course=new TreeNode("Course "+courseNumber.toString());
             
        TreeNode nodeCourse=this.searchChild("Course "+courseNumber.toString());
        if (nodeCourse==null)
             this.add(course);
        else 
            course=nodeCourse;
        TreeNode group=new TreeNode("Group "+groupNumber.toString());
        TreeNode nodeGroup=course.searchChild("Group "+groupNumber.toString());
        if (nodeGroup==null)
            course.add(group);
        else 
            group=nodeGroup;
        Student student=new Student(courseNumber.toString(), groupNumber.toString(), surname);

        TreeNode studentNode=new TreeNode(student, false);
        TreeNode studentPossibleNode=group.searchChild(student);
        if (studentPossibleNode==null)
            group.add(studentNode);
        else
            studentNode=studentPossibleNode;
        
        return studentNode;
        
    }
    
    TreeNode addStudentToGroup(String surname)
    {
        if (this.getLevel()!=2)
            return null;
         Student student=new Student(((TreeNode)this.getParent()).getUserObject().toString(), 
                 this.getUserObject().toString(),
                 surname);
        TreeNode studentNode=new TreeNode(student, false);
        TreeNode studentPossibleNode=this.searchChild(student);
        if (studentPossibleNode==null)
            this.add(studentNode);
        else
            studentNode=studentPossibleNode;
        
        return studentNode;

    }
    
    

}
