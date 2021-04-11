import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.UnaryOperator;
/**
 *
 * @author Mark Drozd
 */
public class MainTree {
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        try
        {
        Scanner scannerFirst=new Scanner(new File("tree_1.txt"));
        Tree<Integer> treeFirst=new Tree(scannerFirst.nextInt());
        scanIntTreeFromFile(scannerFirst, treeFirst);
            treeFirst.add(17);
        treeFirst.add(18);
        Scanner scannerSecond=new Scanner(new File("tree_2.txt"));
        Tree<Integer> treeSecond=new Tree(scannerSecond.nextInt());
        scanIntTreeFromFile(scannerSecond, treeSecond);
        
        
        Scanner scannerLOTR=new Scanner(new File("tree_LOTRCharacters.txt"));
        Tree<LOTRCharacter> treeLOTR=new Tree(new LOTRCharacter(scannerLOTR.next(),scannerLOTR.nextInt())); 
        scanLOTRTreeFromFile(scannerLOTR, treeLOTR);
        
        

        LOTRCharacter boromir=new LOTRCharacter("Boromir",2978);
        LOTRCharacter faramir=new LOTRCharacter("Faramir",2983);

        treeFirst.prefixTraverse();
        treeFirst.delete(20);
        treeFirst.delete(10);
        treeFirst.delete(90);
        newLine();
        treeFirst.prefixTraverse();
      //  performTree(treeFirst, Tree<Integer>::prefixTraverse, 20, 8);
       // performTree(treeSecond, Tree<Integer>::prefixTraverse, 20, 18);
       // performTree(treeLOTR, Tree<LOTRCharacter>::infixTraverse, faramir, boromir);
//        System.out.println( treeLOTR.find(faramir));
//        System.out.println( treeLOTR.add(boromir));
//        treeLOTR.infixTraverse();
//        treeLOTR.delete(boromir);
//        System.out.println( treeLOTR.find(boromir));
          
        }
        catch(FileNotFoundException e)
        {
            System.out.println("WARNING: FILE FOR INPUT NOT FOUND ");
        }
        
           
    }
    
    
    static void scanIntTreeFromFile(Scanner scanner, Tree tree)
    {
        while (scanner.hasNextInt())
            tree.add(scanner.nextInt()); 
    }
    
    
    static<T extends Comparable<T>> void performTree(Tree tree, UnaryOperator<Tree> func, T valToFind, T valToDelete)
    {
        func.apply(tree);
        newLine();
        System.out.println(tree.find(valToFind));
        tree.delete(valToDelete);
        func.apply(tree);
        newLine();
    }
    
    
    static void scanLOTRTreeFromFile(Scanner scannerLOTR, Tree treeLOTR)
    {
       while (scannerLOTR.hasNext())
            treeLOTR.add(new LOTRCharacter(scannerLOTR.next(),scannerLOTR.nextInt())); 
    }
      
    static void newLine()
    {
        System.out.println();
    }
}