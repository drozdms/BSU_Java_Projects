import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.NoSuchElementException;


/**
 *
 * @author Mark Drozd
 */
public class DemoApp implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(null, new DemoApp(), "", 128 * 1024 * 1024).start();
    }
    
    
    
    static void scanIntTreeFromFile(Scanner scanner, Tree tree)
    {
       
        while (scanner.hasNextLong())
        {
            Long a=scanner.nextLong();
            tree.add(a);
        }
            
    }

    @Override
    public void run() {
        try
        {
        Scanner scannerFirst=new Scanner(new File("input.txt"));
        Tree tree=new Tree();
        
        Long key=scannerFirst.nextLong();
       
        scanIntTreeFromFile(scannerFirst, tree);
        
        tree.root=tree.delete(tree.root, key);
        FileWriter writer=new FileWriter(new File("output.txt"),false);
        tree.prefixTraverse(writer);
        writer.close();
        }
        catch(IOException | NoSuchElementException e)
        {
            System.out.println("Exception thrown ");
        }
    }

}


class Tree
{
    
    public Node root;
    
    
    
    Tree()
    {
        root=null;
    }
    
    Tree(Long val)
    {
        root=new Node(val);
    }
    
    
    
    public Tree prefixTraverse(FileWriter writer) throws IOException
    {  
        if (root==null)
            return this;
        else return root.prefixTraverse(writer);
    }
    
    public Node delete(Node root, Long val)
    {
       
        if (root == null)
            return null;
        int compare = val.compareTo(root.value);
        switch (compare)
        {
            case -1:
              root.left=delete(root.left, val);
              return root;
            case 1:
              root.right=delete(root.right, val);
              return root;
            case 0:
              if (root.right==null)
                  return root.left;
              else if (root.left==null)
                  return root.right;
              else
              {
                  root.value=root.right.findMin();
                  root.right=delete(root.right, root.value);
                  return root;
              }
                   
        }    
        return root;
    }
    
    public boolean add(Long val)
    {
        if (root==null)
        {
            root=new Node(val);
            return true;
        }
        else return root.add(val);
    }
    
    
    
   
    
    
    private class Node
    {
        Node left;
        Node right;
        Long value;
        
        Node (Long val)
        {
            value=val;
            left=null;
            right=null;
        }
        
        Tree prefixTraverse(FileWriter writer) throws IOException
        { 
            writer.write(value.toString()+"\n");
            
            if (left!=null)
                left.prefixTraverse(writer);  
            if (right!=null)
                right.prefixTraverse(writer);
            return Tree.this;
        }
        
        boolean find(Long val)
        {
            int compare=val.compareTo(value);
            switch (compare)
            {
                case 0:
                    return true;
                case -1:
                    if (left==null)
                        return false;
                    else return left.find(val);
                case 1:
                    if (right==null)
                        return false;
                    else return right.find(val);
            } 
            return false;
        }
        

        
        Long findMin()
        {
            if (left!=null)
               return left.findMin();
            return value;
        }
        
        
        
        boolean add(Long val)
        {
            if (Objects.equals(val, value))
                    return false;
            else if (val<value)
            {
                    if (left==null)
                    {
                        left=new Node(val);
                        return true;
                    }
                    else return left.add(val);
            }
            else
            {
                    if (right==null)
                    {
                        right=new Node(val);
                        return true;
                    }
                    else return right.add(val);
            }

        }
          
       
    }   
}