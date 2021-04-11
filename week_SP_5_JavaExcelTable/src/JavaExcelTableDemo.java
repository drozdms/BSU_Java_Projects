
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 *
 * @author Mark Drozd
 */
public class JavaExcelTableDemo extends JFrame
{
    
    String[][] array={{"22.06.1998", "14.05.23"}, {"23.09.2013"}};
    String[] columns={"A", "B", "C", "D", "E", "F", "G", "H"};
    
    public JavaExcelTableDemo()
    {
        Model model=new Model(columns, Model.ROW_COUNT);
        View view=new View(model);
        Controller controller=new Controller(model, view);
        view.setDefaultModel(model);
        this.getContentPane().add(new JScrollPane(view));
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       JavaExcelTableDemo frame=new JavaExcelTableDemo();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Excel Table");
       frame.setSize(1000,700);
       frame.setVisible(true);
    }

}
