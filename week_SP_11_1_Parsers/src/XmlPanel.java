import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class XmlPanel extends JPanel {

    private JMenuBar menuBar;
    private JTable table;
    private DefaultTableModel data;
    private int count = 0;

    public XmlPanel() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openDomMenuItem = new JMenuItem("Open XML");
        openDomMenuItem.addActionListener((ActionEvent actionEvent) -> {
            openXML();
        });
        fileMenu.add(openDomMenuItem);
        
        JMenuItem openBinaryFileItem = new JMenuItem("Open binary...");
        openBinaryFileItem.addActionListener((ActionEvent actionEvent) -> {
            openBinary();
        });
        fileMenu.add(openBinaryFileItem);

        JMenuItem saveMenuItem = new JMenuItem("Save as XML");
        saveMenuItem.addActionListener((ActionEvent actionEvent) -> {
            saveToXml();
        });
        fileMenu.add(saveMenuItem);
        
        JMenuItem saveBinaryFileItem = new JMenuItem("Save binary...");
        saveBinaryFileItem.addActionListener((ActionEvent actionEvent) -> {
            saveBinary();
        });
        fileMenu.add(saveBinaryFileItem);

        JMenu tableMenu = new JMenu("Table");

        JMenuItem addRowMenuItem = new JMenuItem("Add Row");
        addRowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addRow();
            }
        });
        tableMenu.add(addRowMenuItem);

        JMenuItem deleteRowMenuItem = new JMenuItem("Delete Row");
        deleteRowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteRow();
            }
        });
        tableMenu.add(deleteRowMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(tableMenu);


        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        data = new DefaultTableModel(new String[]{"ID", "Name", "Faculty", "Year", "Average"}, 0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Integer.class;
                } else if (columnIndex == 1) {
                    return String.class;
                } else if (columnIndex == 2) {
                    return String.class;
                } else if (columnIndex == 3) {
                    return Integer.class;
                } else if (columnIndex == 4) {
                    return Integer.class;
                } else {
                    return Boolean.class;
                }
            }

            @Override
            public void rowsRemoved(TableModelEvent event) {
                super.rowsRemoved(event);
            }

            @Override
            public int getRowCount() {
                return super.dataVector.size();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public void addRow(Object[] rowData) {
                if (rowData!=null)
                    super.addRow(rowData);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                return super.getValueAt(row, column);
            }
        };

        table.setModel(data);

    }

    private void openXML() {
       
            JFileChooser jFileChooser = new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_11_1_Parsers");
            int r = jFileChooser.showDialog(null, "Open XML");
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                ArrayList<Student> students = readXML(file);
                if (students!=null)
                {
                    removeDataFromTableModel();
                    addStudentsToTable(students);
                }
            }
        
    }

    public void addStudentsToTable(ArrayList<Student> list){
        for (Student student : list) {
            count++;
            data.addRow(new Object[]{
                    student.getId(), student.getName(), student.getFaculty(), student.getYear(), student.getMeanGrade()} );
        }
    }

    public static ArrayList<Student> readXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = (Document) builder.parse(file);
            ArrayList<Student> list = new ArrayList<>();
            NodeList nodeList = document.getElementsByTagName("student");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                int id = Integer.parseInt(element.getAttribute("id"));
                String name = element.getAttribute("name");
                String faculty = element.getElementsByTagName("faculty").item(0).getTextContent();
                int year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
                double meanGrade = Double.parseDouble(element.getElementsByTagName("grade").item(0).getTextContent());
                list.add(new Student(id, name, faculty, year, meanGrade));
                
                if (id<100000 || id>999999)
                     throw new ParserConfigurationException();
                if (meanGrade<0 || meanGrade > 10)
                     throw new ParserConfigurationException();
            }
            return list;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            JOptionPane.showMessageDialog(null,"WRONG XML!");
            return null;
        }
    }

    private void saveToXml() {
        try {
            JFileChooser jFileChooser = new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_11_1_Parsers");
            int r = jFileChooser.showDialog(null, "Save file");
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                writeToXML(file);
            }
        } catch (XmlSaveException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void writeToXML(File file) throws XmlSaveException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("bsu");
            document.appendChild(rootElement);

            for (int i = 0; i < data.getRowCount(); i++) {
                Element elementStudent = document.createElement("student");
                
                
                
                Attr id = document.createAttribute("id");
                id.setValue(Integer.toString(((Integer) data.getValueAt(i, 0))));

                Attr name = document.createAttribute("name");
                name.setValue((String) data.getValueAt(i, 1));



                elementStudent.setAttributeNode(id);
                elementStudent.setAttributeNode(name);

                Element elementFaculty = document.createElement("faculty");
                elementFaculty.appendChild(document.createTextNode((String) data.getValueAt(i, 2)));
                elementStudent.appendChild(elementFaculty);

                Element elementYear = document.createElement("year");
                elementYear.appendChild(document.createTextNode(Integer.toString(((Integer) data.getValueAt(i, 3)))));
                elementStudent.appendChild(elementYear);

                Element elementGrade = document.createElement("grade");
                elementGrade.appendChild(document.createTextNode(Double.toString(((Double) data.getValueAt(i, 4)))));
                elementStudent.appendChild((elementGrade));
                
                rootElement.appendChild(elementStudent);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new XmlSaveException(e);
        }
    }
    
    
    
    private void openBinary() {
        try {
            JFileChooser jFileChooser = new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_11_1_Parsers");
            int r = jFileChooser.showDialog(null, "Open binary file");
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<Student> list = new ArrayList<Student>();
                Object obj = null;

                try{
                    while(true){
                        obj = objectInputStream.readObject();
                        list.add((Student)obj);
                        System.out.println(obj);
                    }
                } catch(EOFException e){

                }

               objectInputStream.close();
                removeDataFromTableModel();
                addStudentsToTable(list);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
}
    
    
     private void saveBinary() {
        try {
            JFileChooser jFileChooser = new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_11_1_Parsers");
            int r = jFileChooser.showDialog(null, "Save binary file");
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ArrayList<Student> list = makeStudentList();
                ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
                for(int i = 0; i < list.size(); i++){
                    objectOutputStream.writeObject(list.get(i));
                    System.out.println(list.get(i));
                }
                //objectOutputStream.flush();
                objectOutputStream.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

     
     private ArrayList<Student> makeStudentList(){
        ArrayList<Student> list = new ArrayList<>();
        Vector<Vector> vec = data.getDataVector();
        for(int i = 0; i < vec.size(); i++){
            int id = (int) vec.elementAt(i).elementAt(0);
            String name = (String) vec.elementAt(i).elementAt(1);
            String faculty = (String) vec.elementAt(i).elementAt(2);
            int year = (int) vec.elementAt(i).elementAt(3);
            double grade = (double) vec.elementAt(i).elementAt(4);
            list.add(new Student(id, name, faculty, year, grade));
        }

        return list;
}

    private void addRow() {
        Dialog d = new Dialog();
        d.setVisible(true);
        if(d.isOK()){
            data.addRow(d.getData());
        }

    }

    private void deleteRow() {
        int row = table.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null,"Choose a student.");
        }
        else {
            data.removeRow(row);
        }
    }

    private void removeDataFromTableModel(){
        int rowCount = data.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            data.removeRow(i);
        }
    }

    public static void main(String[] args) {
        XmlPanel xmlPanel = new XmlPanel();

        JFrame frame = new JFrame("XML");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(xmlPanel, BorderLayout.CENTER);
        frame.setJMenuBar(xmlPanel.menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}