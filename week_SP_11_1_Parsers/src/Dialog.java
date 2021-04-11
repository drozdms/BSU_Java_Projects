
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField facultyField;
    private JTextField yearField;
    private JTextField gradeField;
    private boolean ok;

    public Dialog() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        
        idField= new JTextField();
        nameField=new JTextField();
        facultyField = new JTextField();
        yearField = new JTextField();
        gradeField = new JTextField();
        
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        idField.setPreferredSize(new Dimension(20,10));
        nameField.setPreferredSize(new Dimension(60, 10));
        facultyField.setPreferredSize(new Dimension(60, 10));
        yearField.setPreferredSize(new Dimension(20, 10));
        gradeField.setPreferredSize(new Dimension(30, 10));
        
        
        
        
        setMinimumSize(new Dimension(500, 300));
        
        buttonOK=new JButton("OK");

        buttonOK.addActionListener((ActionEvent e) -> {
            try {
                onOK();
            } catch (BadInputException e1) {
                e1.printStackTrace();
            }
        });

        
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener((ActionEvent e) -> {
            onCancel();
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction((ActionEvent e) -> {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        
        JPanel idPanel=new JPanel(new BorderLayout());
        idPanel.add(new JLabel("id"), BorderLayout.WEST);
        idPanel.add(idField, BorderLayout.CENTER);
        
        JPanel namePanel=new JPanel(new BorderLayout());
        namePanel.add(new JLabel("name"), BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        
        JPanel facultyPanel=new JPanel(new BorderLayout());
        facultyPanel.add(new JLabel("faculty"), BorderLayout.WEST);
        facultyPanel.add(facultyField, BorderLayout.CENTER);
        
        JPanel yearPanel=new JPanel(new BorderLayout());
        yearPanel.add(new JLabel("year of study"), BorderLayout.WEST);
        yearPanel.add(yearField, BorderLayout.CENTER);
        
        JPanel gradePanel=new JPanel(new BorderLayout());
        gradePanel.add(new JLabel("average"), BorderLayout.WEST);
        gradePanel.add(gradeField, BorderLayout.CENTER);
        
        
        panel.add(new JLabel("Info"));
        panel.add(idPanel);
        panel.add(namePanel);
        panel.add(facultyPanel);
         panel.add(yearPanel);
          panel.add(gradePanel);
          
          contentPane.add(panel, BorderLayout.WEST);
          contentPane.add(buttonOK, BorderLayout.CENTER);
          contentPane.add(buttonCancel, BorderLayout.EAST);
    }

    private void onOK() throws BadInputException {
        // add your code here
        if (nameField.equals("") || idField.equals("") || facultyField.equals("")||
                yearField.equals("") || gradeField.equals("")){
            throw new BadInputException("Fill all the fields.");
        }
        try {
            Student student = new Student(Integer.parseInt(idField.getText()), nameField.getText(),
                facultyField.getText(), Integer.parseInt(yearField.getText()),
                Double.parseDouble(gradeField.getText()));
            int id=Integer.parseInt(idField.getText());
            if (id<100000 || id>999999)
                throw new NumberFormatException();
            double av=Double.parseDouble(gradeField.getText());
            if (av<0 || av > 10)
                throw new NumberFormatException();
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"WRONG INPUT!");
        }
        
        ok = true;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        ok = false;
        dispose();
    }

    public boolean isOK(){
        return ok;
    }


    public Object[] getData() {
        Object[] ar;
    
        try {
                ar= new Object[]{Integer.parseInt(idField.getText()), nameField.getText(),
                facultyField.getText(), Integer.parseInt(yearField.getText()),
                Double.parseDouble(gradeField.getText())};
        }
        catch (NumberFormatException e)
            {
                    return null;
            }
        
        
        return ar;
    }

}