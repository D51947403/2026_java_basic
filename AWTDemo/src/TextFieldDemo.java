import java.awt.*;

public class TextFieldDemo {
    // main function
    public static void main(String[] args){

        // Create a frame
        Frame f = new Frame("TextField Example");

        // Create a text field
        TextField t1 = new TextField();

        // Set position
        t1.setBounds(50, 50, 200, 30);

        TextField t2 = new TextField();
        t2.setBounds(50, 100, 200, 30);

        // Add text fields to the frame
        f.add(t1);
        f.add(t2);

        // Set frame size
        f.setSize(300, 200);

        // Set frame layout
        f.setLayout(null);

        // Make the frame visible
        f.setVisible(true);
    }
}
