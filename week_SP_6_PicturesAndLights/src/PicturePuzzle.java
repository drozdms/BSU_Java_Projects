
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Mark Drozd
 */
class PicturePuzzle extends JPanel
{
    BufferedImage sourceImage;
    private static final int FIELD_SIZE=3;
    private int pieceHeight;
    private int pieceWidth;
    private int nullCordX;
    private int nullCordY;   
    private JLabel[][] pieces;
    private JPanel field;
    private JPanel example;
    private int[] matrixForCheck;
    Border border=BorderFactory.createLineBorder(Color.BLACK);
        
    public PicturePuzzle()
    {
        super(new BorderLayout());
        try {
            
            sourceImage=ImageIO.read(new File("images\\LOTR.png"));
            matrixForCheck=new int[FIELD_SIZE*FIELD_SIZE];
            nullCordX=0;
            nullCordY=0;
            JPanel panel=new JPanel();
            field=new JPanel();
            example=new JPanel();
            field.setLayout(new GridLayout(FIELD_SIZE,FIELD_SIZE));
            example.setLayout(new BorderLayout());
            panel.add(field, BorderLayout.CENTER);
            
            reset();
           
            field.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyReleased(KeyEvent e)
                {
                    switch (e.getKeyCode())
                    {
                        case KeyEvent.VK_LEFT:  
                        {
                            if (nullCordY+1<FIELD_SIZE)
                            {
                                pieces[nullCordX][nullCordY].setIcon(pieces[nullCordX][nullCordY+1].getIcon());
                                pieces[nullCordX][nullCordY+1].setIcon(null);
                                 swapPlaces(nullCordX, nullCordY, nullCordX, nullCordY+1);
                                nullCordY++;
                               
                                if (isGameOver())
                                {
                                    JOptionPane.showMessageDialog(null, "GAME OVER");
                                    reset();
                                }
                            }
                            break;
                        }
                        case KeyEvent.VK_UP:
                        {
                            if (nullCordX+1<FIELD_SIZE)
                            {
                                pieces[nullCordX][nullCordY].setIcon(pieces[nullCordX+1][nullCordY].getIcon());
                                pieces[nullCordX+1][nullCordY].setIcon(null);
                                swapPlaces(nullCordX, nullCordY, nullCordX+1, nullCordY);
                                nullCordX++;
                                
                                if (isGameOver())
                                {
                                    JOptionPane.showMessageDialog(null, "GAME OVER");
                                    reset();
                                }
                            }
                            break;
                        }
                        case KeyEvent.VK_DOWN:
                        {
                            if (nullCordX>0)
                            {
                                pieces[nullCordX][nullCordY].setIcon(pieces[nullCordX-1][nullCordY].getIcon());
                                pieces[nullCordX-1][nullCordY].setIcon(null);
                                swapPlaces(nullCordX, nullCordY, nullCordX-1, nullCordY);
                                nullCordX--;
                                
                                if (isGameOver())
                                {
                                    JOptionPane.showMessageDialog(null, "GAME OVER");
                                    reset();
                                }
                            }
                            break;
                        }
                        case KeyEvent.VK_RIGHT:
                        {
                            if (nullCordY>0)
                            {
                                pieces[nullCordX][nullCordY].setIcon(pieces[nullCordX][nullCordY-1].getIcon());
                                pieces[nullCordX][nullCordY-1].setIcon(null);
                                 swapPlaces(nullCordX, nullCordY, nullCordX, nullCordY-1);
                                nullCordY--;
                               
                                if (isGameOver())
                                {
                                    JOptionPane.showMessageDialog(null, "GAME OVER");
                                    reset();
                                }
                            }
                            break;
                        }
                    }
                    
                }
                
                
            });
            this.requestFocus();
            
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e)
                {
                    field.requestFocus();
                }
            });
             this.add(example, BorderLayout.EAST);
            this.add(panel, BorderLayout.CENTER);
            
            
        }
        catch (IOException exc) {
            sourceImage=null;
            
        }
    }
    
    public void setImage(BufferedImage img) {
        sourceImage=img;
        reset();
    }
    
    
    
    private JLabel[][] splitImageIntoPieces() {
        pieceWidth=sourceImage.getWidth()/FIELD_SIZE;
        pieceHeight=sourceImage.getHeight()/FIELD_SIZE;
        pieces=new JLabel[FIELD_SIZE][FIELD_SIZE];
        for (int i=0; i<FIELD_SIZE; ++i)
            for (int k=0; k<FIELD_SIZE; ++k)
                pieces[i][k]=getPiece(i,k);
        return pieces;
    }
    
    
    
    private void reset()
    {
        pieces=splitImageIntoPieces();
        pieces[0][0].setIcon(null);
        sshuffleField();
        field.setPreferredSize(new Dimension(sourceImage.getWidth()+2*FIELD_SIZE, sourceImage.getHeight()+2*FIELD_SIZE));
        field.removeAll();
        for (int i=0; i<FIELD_SIZE; ++i)
                for (int j=0; j<FIELD_SIZE; ++j)
                {
                    field.add(pieces[i][j]);
                    pieces[i][j].setBorder(border);
                }
        example.removeAll();
        example.setPreferredSize(new Dimension(200, this.getHeight()));
        JLabel examplePicLabel=new JLabel();
        examplePicLabel.setSize(200, 200*sourceImage.getHeight()/sourceImage.getWidth());
        BufferedImage image= getScaledImage(sourceImage, examplePicLabel.getWidth(), examplePicLabel.getHeight());
        examplePicLabel.setIcon(new ImageIcon(image));
        example.add(examplePicLabel, BorderLayout.WEST);
        examplePicLabel.setBorder(border);
    }
    
    
    private JLabel getPiece(int i, int k){
        JLabel piece=new JLabel();
        piece.setSize(pieceWidth+2, pieceHeight+2);
        BufferedImage icon=new BufferedImage(pieceWidth, pieceHeight, BufferedImage.TYPE_INT_ARGB);
        int[] pixels=new int[pieceWidth*pieceHeight];
        PixelGrabber grabber=new PixelGrabber(sourceImage, k*pieceWidth, i*pieceHeight, pieceWidth, pieceHeight, pixels, 0, pieceWidth);
        try {
            grabber.grabPixels();
        }
        catch(InterruptedException exc) {
            JOptionPane.showMessageDialog(null, "Failing to split an image");
                    return null;
        }
        icon.setRGB(0, 0, pieceWidth, pieceHeight, pixels, 0, pieceWidth);
         piece.setIcon(new ImageIcon(icon));
         matrixForCheck[i*FIELD_SIZE+k]=i*FIELD_SIZE+k;
        return piece;
    }
    
    
    
    private void swapPlaces(int i, int j, int k, int l)
    {
        int tempInt=matrixForCheck[i*FIELD_SIZE+j];
        matrixForCheck[i*FIELD_SIZE+j]=matrixForCheck[k*FIELD_SIZE+l];
        matrixForCheck[k*FIELD_SIZE+l]=tempInt;
    }
    
    private boolean isGameOver()
    {
        for (int i=0; i<FIELD_SIZE; ++i)
            for (int j=0; j<FIELD_SIZE; ++j)
                if (matrixForCheck[i*FIELD_SIZE+j]!=i*FIELD_SIZE+j)
                    return false;
        return true;
    }
    
    
//    void shuffleField  () {
//    Random random = new Random();
//    for (int i = pieces.length - 1; i > 0; i--) {
//        for (int j = pieces[i].length - 1; j > 0; j--) {
//            int k = random.nextInt(i + 1);
//            int l = random.nextInt(j + 1);
//            JLabel temp = pieces[i][j];
//            pieces[i][j] = pieces[k][l];
//            pieces[k][l] = temp;
//            swapPlaces(i,j,k,l);
//            }
//        }
//    
//        for (int i=0; i<FIELD_SIZE;++i)
//            for (int j=0; j<FIELD_SIZE; ++j)
//                if (pieces[i][j].getIcon()==null)
//                {
//                    nullCordX=i;
//                    nullCordY=j;
//                }
//    }
    
    
    void sshuffleField ()  {
        
        Random random = new Random();
        int count=random.nextInt(200)+100;
        while (count>0)
        {
            int sw=random.nextInt(count--);
            switch (sw%4)
            {
                case 0:
                {
                    if (nullCordY+1<FIELD_SIZE)
                    {
                            JLabel temp = pieces[nullCordX][nullCordY+1];
                            pieces[nullCordX][nullCordY+1] = pieces[nullCordX][nullCordY];
                            pieces[nullCordX][nullCordY] = temp;
                            swapPlaces(nullCordX,nullCordY,nullCordX,nullCordY+1);
                            nullCordY++;
                    }
                    break;
                }
                case 1:
                {
                    if (nullCordX>0)
                    {
                            JLabel temp = pieces[nullCordX-1][nullCordY];
                            pieces[nullCordX-1][nullCordY] = pieces[nullCordX][nullCordY];
                            pieces[nullCordX][nullCordY] = temp;
                            swapPlaces(nullCordX,nullCordY,nullCordX-1,nullCordY);
                            nullCordX--;
                    }
                    break;
                }
                case 2:
                {
                    if (nullCordY>0)
                    {
                            JLabel temp = pieces[nullCordX][nullCordY-1];
                            pieces[nullCordX][nullCordY-1] = pieces[nullCordX][nullCordY];
                            pieces[nullCordX][nullCordY] = temp;
                            swapPlaces(nullCordX,nullCordY,nullCordX,nullCordY-1);
                            nullCordY--;
                    }
                    break;
                }
                case 3:
                {
                    if (nullCordX+1<FIELD_SIZE)
                    {
                            JLabel temp = pieces[nullCordX+1][nullCordY];
                            pieces[nullCordX+1][nullCordY] = pieces[nullCordX][nullCordY];
                            pieces[nullCordX][nullCordY] = temp;
                            swapPlaces(nullCordX,nullCordY,nullCordX+1,nullCordY);
                            nullCordX++;
                    }
                    break;
                }
            }
            
            for (int i=0; i<FIELD_SIZE;++i)
            for (int j=0; j<FIELD_SIZE; ++j)
                if (pieces[i][j].getIcon()==null)
                {
                    nullCordX=i;
                    nullCordY=j;
                }
            
            
            
            
        }
        
        
        
        
        
        
    }
    
    
    
    
    public void fireFocusEvent()
    {
        ComponentEvent event=new ComponentEvent(this,ComponentEvent.COMPONENT_SHOWN);
            this.processComponentEvent(event);
    }
    
    private BufferedImage getScaledImage(BufferedImage srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.fillRect(0, 0, w, h);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
    }
    
}
