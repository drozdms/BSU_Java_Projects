
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Mark Drozd
 */
public class PaneStudentTree extends JPanel implements ActionListener
{
    private JTree tree;
    private TreeNode root;
    private JButton addStudent;
    private JButton removeStudent;
    private JButton editStudent;
    private DefaultTreeModel treeModel;
    private int newNodeSuffix=1;
    
    private static final String ADD_COMMAND = "add";
    private static final String REMOVE_COMMAND = "remove";
    private static final String EDIT_COMMAND="edit";
    public PaneStudentTree()
    {
        super(new BorderLayout());
       
        
        root=new TreeNode("University");
        treeModel=new DefaultTreeModel(root);
        try 
        {
            Scanner scanner=new Scanner(new File("student_input.txt"));
            
            while (scanner.hasNext())
            {
                Integer courseNumber=scanner.nextInt();
                Integer groupNumber=scanner.nextInt();
                String surname=scanner.next();
                
                TreeNode node=root.addStudent(courseNumber, groupNumber, surname);
                if (node!=null)
                    treeModel.insertNodeInto(node, (MutableTreeNode) node.getParent(), node.getParent().getChildCount()-1);
            }
        } 
        
        catch (FileNotFoundException | NoSuchElementException ex) 
        {
            Logger.getLogger(PaneStudentTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
         treeModel=new DefaultTreeModel(root);
         tree=new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                
         addStudent=new JButton("add student");
         removeStudent=new JButton("remove student");
         editStudent=new JButton("edit student");
         addStudent.setActionCommand(ADD_COMMAND);
         addStudent.addActionListener(this);
         removeStudent.setActionCommand(REMOVE_COMMAND);
         removeStudent.addActionListener(this);
         editStudent.setActionCommand(EDIT_COMMAND);
         editStudent.addActionListener(this);
         JPanel panel=new JPanel(new FlowLayout());
         
         panel.add(addStudent);
         panel.add(removeStudent);
         panel.add(editStudent);
         add(panel, BorderLayout.NORTH);
         add(tree, BorderLayout.CENTER);
         
         
         
         
         tree.setCellRenderer(new MyRenderer(new ImageIcon("university.png"),
         new ImageIcon("course.gif"), new ImageIcon("group.jpg"), new ImageIcon("person.png")));
    }
    
    
     public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (ADD_COMMAND.equals(command)) 
        {
            addClick(tree, "New Student " + newNodeSuffix++);
        }
        else if (REMOVE_COMMAND.equals(command))
        {
            removeClick(tree);
        }
        else if (EDIT_COMMAND.equals(command))
        {
            TreeNode selectedNode=((TreeNode)tree.getSelectionPath().getLastPathComponent());
            if (selectedNode==null)
                return;
            if (selectedNode.getLevel()!=3)
                return;
            JOptionPane pane=new JOptionPane(null,
            JOptionPane.PLAIN_MESSAGE, 
                    JOptionPane.OK_CANCEL_OPTION);  
            
            Student selectedStudent=(Student)selectedNode.getUserObject();
            JDialog diag =pane.createDialog(this, "edit this student");
            diag.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel jpanel=new JPanel(new BorderLayout());
            JTextField area=new JTextField(18);
            area.setText(selectedStudent.getName());
            jpanel.add(new Label("Change surname:"), BorderLayout.NORTH);
            jpanel.add(area);
            JFormattedTextField fieldCourse=new JFormattedTextField(Integer.parseInt(selectedStudent.getCourse()));
            JFormattedTextField fieldGroup=new JFormattedTextField(Integer.parseInt(selectedStudent.getGroup()));
            fieldCourse.setColumns(2);
            fieldGroup.setColumns(2);
            diag.add(jpanel);
            diag.add(fieldCourse);
            diag.add(fieldGroup);
            diag.add(pane);
            diag.pack();
            diag.setVisible(true);
                    
                    

            Object value=pane.getValue();
            int c=-1;
            if (value==null)
                c=JOptionPane.CLOSED_OPTION;
            else c=Integer.parseInt(value.toString());
            if (c==JOptionPane.YES_OPTION)
            {
                String name=area.getText();
                String course=fieldCourse.getValue().toString();
                String group=fieldGroup.getValue().toString();
                TreeNode node=root.addStudent(Integer.parseInt(course), Integer.parseInt(group), name);
                
                
                TreeNode parent=(TreeNode) selectedNode.getParent();
                parent.remove(selectedNode);
                treeModel.reload();
                if (node!=null)
                     treeModel.insertNodeInto(node, (MutableTreeNode) node.getParent(), node.getParent().getChildCount()-1);
                
               
                tree.expandPath(new TreePath(node.getParent()));
            }
         }
        
     }
     
    TreeNode addClick(JTree tree, String surname)
    {       
        TreeNode selectedNode=((TreeNode)tree.getSelectionPath().getLastPathComponent());
        if (selectedNode==null)
            return null;
        TreeNode node=selectedNode.addStudentToGroup(surname);
        if (node==null)
            return null;
        treeModel.insertNodeInto(node, (MutableTreeNode) node.getParent(), node.getParent().getChildCount()-1);
        tree.makeVisible(new TreePath(node.getPath()));
        return node;
    }
    
    boolean removeClick(JTree tree)
    {       
        TreeNode selectedNode=((TreeNode)tree.getSelectionPath().getLastPathComponent());
        if (selectedNode==null)
            return false;
        if (selectedNode.getLevel()!=3)
            return false;
        
        TreeNode parent=(TreeNode) selectedNode.getParent();
        parent.remove(selectedNode);
        treeModel.reload();
         tree.expandPath(new TreePath(parent.getPath()));
        return true;
        
    }
}
