
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mark Drozd
 */
class TabbedPaneFrame extends JFrame {
 private JTabbedPane tabbedPane;
    private PicturePuzzle picturePuzzle;
    private ExtrudedText extrudedText;
    
    
     private JMenuBar menuBar;
    private JMenu dataMenu;
    private JMenuItem open;
    
    
    
    
    
    public TabbedPaneFrame()
    {
        super("Game and Text");
        setBounds(100,100,1000,600);
        tabbedPane=new JTabbedPane();
        picturePuzzle=new PicturePuzzle();
        extrudedText=new ExtrudedText();
        tabbedPane.addTab("Picture Puzzle", picturePuzzle);
        tabbedPane.addTab("3D Text", extrudedText);
        add(tabbedPane);
        
        menuBar=new JMenuBar();
        dataMenu=new JMenu("File");
        open=new JMenuItem("Open Picture...");
        open.addActionListener((e)->{
            FileFilter imageFilter = new FileNameExtensionFilter(
                             "Image files", ImageIO.getReaderFileSuffixes());
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_6_PicturesAndLights\\images");
            jfc.setFileFilter(imageFilter);
            int rVal=jfc.showOpenDialog(this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                BufferedImage img=null;
                try
                {
                    img=ImageIO.read(jfc.getSelectedFile());
                }
                catch (IOException ex) {
                     ex=null;
                        return;
                }
                
                picturePuzzle.setImage(img);
            }
        
        });
        
        
        dataMenu.add(open);
        menuBar.add(dataMenu);
        this.setJMenuBar(menuBar);
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    
    public void fireFocusGameEvent()
    {
            
            picturePuzzle.fireFocusEvent();
    }
}
