import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.StringTokenizer;

public class Design extends JFrame implements ActionListener{
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu menuFile,menuEdit,menuHelp;
    JMenuItem Open,Save,SaveAs,Exit;
    JFileChooser fileChooser;
    File fp;
    ImageIcon icon;
    BufferedWriter bufferedWriter = null;
    Design(){
        setTitle("NotePad++");
        setVisible(true);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        addComponents();
        addlistener();
    }

    private void initComponents(){
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuHelp = new JMenu("Help");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        SaveAs = new JMenuItem("Save as");
        Exit = new JMenuItem("Exit");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text file only","txt"));
        fileChooser.setDialogTitle("Choose a text file");
        icon = new ImageIcon(getClass().getResource("note.png"));
        this.setIconImage(icon.getImage());


        //Add shortCut to the menu item
        Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));



        //Add menu item in in menu

        menuFile.add(Open);
        menuFile.add(Save);
        menuFile.add(SaveAs);
        menuFile.add(Exit);

        //Add menu in the menu bar

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);

    }

    private void addComponents(){
        setJMenuBar(menuBar);
        getContentPane().add(textArea);
        getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
    }
    private void addlistener(){
        Open.addActionListener(this);
        Save.addActionListener(this);
        SaveAs.addActionListener(this);
        Exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

            if(e.getSource() == Exit){
                System.exit(1);
            }else if(e.getSource() == Save){

                save();

            }else if(e.getSource()==Open){
                String line,total="";
                int returnVal = fileChooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    fp = fileChooser.getSelectedFile();
                    setTitle(fp.getName());
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(fp));
                        while ((line =bufferedReader.readLine()) != null){

                            total+=line+"\n";
                        }

                    }catch (Exception err){

                    }finally {
                        textArea.setText(total);
                    }
                }

            }else if(e.getSource()==SaveAs){
                saveAs();
            }

    }

    private void save(){
        if(fp==null){
            saveAs();
        }else{

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(fp));
                String s = textArea.getText();
                StringTokenizer st = new StringTokenizer(s,System.getProperty("line.separator"));
                while (st.hasMoreElements()){
                    bufferedWriter.write(st.nextToken());
                    bufferedWriter.newLine();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(this,"Your file is saved");

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }


        }
    }
    private void saveAs(){
        fileChooser.setApproveButtonText("Choose file");
        int returnVal = fileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            fp = fileChooser.getSelectedFile();
            save();
        }
    }
}





