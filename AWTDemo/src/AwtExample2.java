import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtExample2 extends Frame implements ActionListener {
    // Frame Label and Button Initialised
    private Frame frame;
    private Label label;
    private Button button;
    public int count = 0;


    // Constructor
    public AwtExample2()
    {
        // Memory Allocated to frame
        frame = new Frame("Example of AWT Label");

        // Memory Allocated to label
        label = new Label("Check if a Number is Even or Odd");

        // Memory Allocated to button
        button = new Button("Calculate");

        // Register ActionListener
        button.addActionListener(this);

        // Add components to the frame
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        // Dimensions of Frame
        frame.setSize(300, 300);
        frame.setVisible(true);

        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
    // Action Listener checks when
    // Button is Pressed
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button) {
            String str;
            if (count % 2 == 0)
                str = count + " is Even";
            else
                str = count + " is odd";

            label.setText(str);
            count++;
        }
    }

    public static void main(String[] args) {
        new AwtExample2();
    }
}
