
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
class Tree <T extends Comparable<T>>
{
    
    private Node root;
    
    
    Tree(T val)
    {
        root=new Node(val);
    }
    
    public boolean add(T val)
    {
        if (root==null)
        {
            root=new Node(val);
            return true;
        }
        else return root.add(val);
    }
    
    public Tree infixTraverse()
    {
        if (root==null)
            return this;
        else return root.infixTraverse();
    }
    
    
    public Tree prefixTraverse()
    {  
        if (root==null)
            return this;
        else return root.prefixTraverse();
    }
    
    public Tree postfixTraverse()
    {  
        if (root==null)
            return this;
        else return root.postfixTraverse();
    }
     
    public boolean find(T val)
    {
        if (root==null)
            return false;
        else return root.find(val);   
    }
    
    
    public boolean delete(T val)
    {
        if (root == null)
            return false;
        root.findToDelete(val);
        return true;
        
    }
        
    
    
    private class Node
    {
        Node left;
        Node right;
        T value;
        
        Node (T val)
        {
            value=val;
            left=null;
            right=null;
        }
        
        boolean add(T val)
        {
            int compare=val.compareTo(value);
            switch (compare)
            {
                case 0:
                    return false;
                case -1:
                    if (left==null)
                    {
                        left=new Node(val);
                        return true;
                    }
                    else return left.add(val);
                case 1:
                    if (right==null)
                    {
                        right=new Node(val);
                        return true;
                    }
                    else return right.add(val);
            }

            return false;
        }
          
        Tree infixTraverse()
        {
            if (left!=null)
                left.infixTraverse();
             System.out.print(value.toString()+" ");
            if (right!=null)
                right.infixTraverse();
            return Tree.this;
        }
        
        Tree prefixTraverse()
        { 
             System.out.print(value.toString()+" ");
            if (left!=null)
                left.prefixTraverse();  
            if (right!=null)
                right.prefixTraverse();
            return Tree.this;
        }
        
        Tree postfixTraverse()
        { 
            if (left!=null)
                left.postfixTraverse();
            if (right!=null)
                right.postfixTraverse();
            System.out.print(value.toString()+" ");
            return Tree.this;
        }
        
        private void traverseOrder(ArrayList<T> list)
        {
            if (left!=null)
                left.traverseOrder(list);
            if (right!=null)
                right.traverseOrder(list);
            list.add(value);
        }
        
        
        
        boolean find(T val)
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
        

        
        T findMax()
        {
            if (right!=null)
               return right.findMax();
            return value;
        }
        
        Node findToDelete(T val)
        {
            Node temp=null;
            if (!find(val))
                return null;
            int compare = val.compareTo(value);
            switch (compare)
            {
                case -1:
                    if (left.value.equals(val) && left.right==null)
                    {
                        left=left.left;
                        break;
                    }
                    if (left.left==null && left.value.equals(val))
                    {
                        left=left.right;
                        break;
                    }
                    else return left.findToDelete(val);
                case 1:
                    if (right.value.equals(val) && right.right==null)
                        {
                        right=right.left;
                        break;
                    }
                    else if (right.left==null && right.value.equals(val))
                        {
                        right=right.right;
                        break;
                    }
                    else return right.findToDelete(val);
                case 0:
                    this.value=left.findMax();
                    if (!left.value.equals(this.value))
                        return left.findToDelete(this.value);
                    else 
                    {
                        left=left.left;
                        break;
                    }
                   
            }
            
            
            return temp;
        }
    }   
}




//        boolean findToDelete(T val)
//        {
//            ArrayList<T> list=new ArrayList();
//            int compare=val.compareTo(value);
//            switch (compare)
//            {
//                case 0:                                     // удаляемая вершина -- главный корень
//                    if (left!=null) 
//                        left.traverseOrder(list);
//                    if (right!=null)
//                        right.traverseOrder(list);
//                    root=null;
//                    for (int i=list.size()-1; i>=0; --i)
//                        Tree.this.add(list.get(i));
//                    return true;
//                case -1:
//                    if (left==null)
//                        return false;
//                    else if (left.value==val)
//                    {
//                        if (left.left!=null) 
//                            left.left.traverseOrder(list);
//                        if (left.right!=null) 
//                            left.right.traverseOrder(list);
//                        left=null;
//                        for (int i=list.size()-1; i>=0; --i)
//                           Tree.this.add(list.get(i));
//                         return true;
//                    }
//                    else return left.findToDelete(val);
//                case 1:
//                    if (right==null)
//                        return false;
//                     else if (right.value==val)
//                    {
//                         if (right.left!=null) 
//                            right.left.traverseOrder(list);
//                        if (right.right!=null) 
//                            right.right.traverseOrder(list);
//                        right=null;
//                        for (int i=list.size()-1; i>=0; --i)
//                           Tree.this.add(list.get(i));
//                        return true;
//                    }
//                    else return right.findToDelete(val);
//            }
//            return false;
//        }