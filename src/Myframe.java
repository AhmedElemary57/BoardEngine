import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Myframe extends JFrame implements ActionListener {
    JButton jButton=new JButton("Submit");
     JTextField jTextField = new JTextField();
    Myframe(){
        JLabel jLabel=new JLabel();
        jLabel.setText("alooooooooooooooooooo");

        ImageIcon imageIcon = new ImageIcon(".//utils//logo.png");
        jLabel.setIcon(imageIcon);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        jLabel.setVerticalTextPosition(JLabel.TOP);
        jLabel.setForeground(Color.GREEN);
        jLabel.setFont(new Font("MV Boli",Font.PLAIN,28));
        jLabel.setBackground(Color.blue);
        jLabel.setOpaque(true);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setBounds(0,0,500,500);


        jButton.addActionListener(this);

        jTextField.setPreferredSize(new Dimension(250,40));
        jTextField.setHorizontalAlignment(JLabel.BOTTOM);
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        frame.add(jTextField);
        frame.add(jButton);

        // ImageIcon imageIcon= new ImageIcon("\\utils\\logo.jpg");
        frame.setIconImage(imageIcon.getImage());
        frame.add(jLabel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton){
            System.out.println( jTextField.getText());

        }

    }
}
