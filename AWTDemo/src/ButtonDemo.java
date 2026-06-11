import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonDemo  implements ActionListener {
    // Frame Label and Button Initialised
    private Frame frame;
    private Label label1;
    private Label label2;
    private Label label3;
    private Button button1;
    private Button button2;
    private Button button3;

    public ButtonDemo() {
        // Create a frame
        frame = new Frame("AWT Button Example");
        // Button 1
        button1 = new Button("Click Me!");
        // Set the background color
        button1.setBackground(Color.BLUE);
        // Set the foreground color
        button1.setForeground(Color.WHITE);
        // Set the button font size
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        // Set the button position
        button1.setBounds(150, 50, 100, 50);
        // Button 2 with different properties
        button2 = new Button("Press Me!");
        button2.setBackground(Color.GREEN);
        button2.setForeground(Color.BLACK);

        button2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        button2.setBounds(150, 140, 80, 30);

        // Button 3 with different properties
        button3 = new Button("Tap Me!");
        button3.setBackground(Color.RED);
        button3.setForeground(Color.WHITE);

        button3.setFont(new Font("Verdana", Font.ITALIC, 16));
        button3.setBounds(130, 220, 70, 40);

        // Register ActionListener
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        // Memory Allocated to label
        label1 = new Label("lable1");
        label2 = new Label("label2");
        label3 = new Label("label3");

        label1.setBounds(300, 50, 100, 50);
        label2.setBounds(300, 140, 100, 50);
        label3.setBounds(300, 220, 100, 50);

        // Add the buttons to the frame
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);

        // Set the frame background color
        frame.setBackground(Color.WHITE);

        // Set the frame size and make it visible
        frame.setSize(500, 500);
       // frame.setLayout(null);

        // Set the frame visibility to true
        frame.setVisible(true);

        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Calling WindowEvent to close !");
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button1) {
            System.out.println("Button 1 clicked");
            label1.setText("Click Me!");
        } else if (actionEvent.getSource() == button2) {
            System.out.println("Button 2 clicked");
            label2.setText("Press Me!");
        } else if (actionEvent.getSource() == button3) {
            System.out.println("Button 3 clicked");
            label3.setText("Tap Me!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Calling button demo !");
        new ButtonDemo();
    }
}
