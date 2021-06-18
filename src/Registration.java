import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;

public class Registration  extends JFrame implements ActionListener {
    JLabel label1, firstNameLabel, lastNamelabel, studentNumberLabel;
    JTextField firstNameText, lastnameText, studentNumberText;
    JButton btn1, btn2, btn3;

    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    Panel pnl = new Panel();
    Panel pnl2 = new Panel();

    public Registration()
    {
        model.addColumn("نام");
        model.addColumn("نام خانوادگی");
        model.addColumn("شماره دانشجویی");

        setSize(900, 700);
        setLayout(null);
        pnl.setLayout(null);
        pnl2.setLayout(null);

        pnl.setSize(400,700);
        pnl2.setSize(500,700);
        pnl.setBounds(550,0,400,700);
        pnl2.setBounds(0,0,500,700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("فرم ثبت نام");
        label1 = new JLabel("ثبت نام");
        label1.setForeground(Color.blue);
        firstNameLabel = new JLabel("نام: ");
        lastNamelabel = new JLabel("نام خانوادگی: ");
        studentNumberLabel = new JLabel("شماره دانشجویی: ");
        firstNameText = new JTextField();
        lastnameText = new JTextField();
        studentNumberText = new JTextField();
        btn1 = new JButton("ثبت");
        btn2 = new JButton("پاک کردن");
        btn3 = new JButton("فراخوانی");
        btn1.addActionListener(this);
        btn1.setBackground(Color.blue);
        btn1.setForeground(Color.WHITE);
        btn2.addActionListener(this);
        btn2.setForeground(Color.WHITE);
        btn2.setBackground(Color.RED);
        btn3.addActionListener(this);
        btn3.setBackground(Color.PINK);
        btn3.setForeground(Color.WHITE);

        label1.setBounds(200, 30, 300, 30);
        firstNameLabel.setBounds(200, 70, 200, 30);
        firstNameText.setBounds(0, 70, 200, 30);
        lastNamelabel.setBounds(200, 110, 200, 30);
        lastnameText.setBounds(0, 110, 200, 30);
        studentNumberLabel.setBounds(200, 150, 200, 30);
        studentNumberText.setBounds(0, 150, 200, 30);
        btn1.setBounds(200, 250, 100, 30);
        btn2.setBounds(100, 250, 100, 30);
        btn3.setBounds(0, 250, 100, 30);
        pnl.add(label1);
        pnl.add(firstNameLabel);
        pnl.add(firstNameText);
        pnl.add(lastNamelabel);
        pnl.add(lastnameText);
        pnl.add(studentNumberLabel);
        pnl.add(studentNumberText);

        pnl.add(btn1);
        pnl.add(btn2);
        pnl.add(btn3);
        add(pnl);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setBounds(0,0,500,300);
        table.setFillsViewportHeight(true);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        pnl2.setLayout(new BorderLayout());
        pnl2.add(scrollPane);
        add(pnl2);

        setVisible(true);
    }
        public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn1)
        {
            int x = 0;
            String firstName = firstNameText.getText();
            String lastName = lastnameText.getText();
            String studentNumber = studentNumberText.getText();
            if(!firstName.equals("") && !lastName.equals("") && !studentNumber.equals(""))
            {
                try
                {
                    BufferedWriter bw = new BufferedWriter( new FileWriter("test.txt",true) );
                    bw.write(firstName+","+lastName+","+studentNumber);
                    bw.flush();
                    bw.newLine();
                    bw.close();
                    JOptionPane.showMessageDialog(btn1, "اطلاعات ذخیره شد");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(btn1, "اطلاعات کامل نیست!");
            }
        }
        else if (e.getSource() == btn2)
        {
            firstNameText.setText("");
            lastnameText.setText("");
            studentNumberText.setText("");
        }else {
            BufferedReader br = null;
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            try {
                br = new BufferedReader( new FileReader("test.txt") );
                String record;
                while( ( record = br.readLine() ) != null ) {
                    StringTokenizer st = new StringTokenizer(record,",");
                    model.addRow(new String[] {st.nextToken(), st.nextToken(),st.nextToken()});
                }
                br.close();
                table.setModel(model);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
                JOptionPane.showMessageDialog(btn1, "کاربری ثبت نشده است");

            }
        }
    }
}
