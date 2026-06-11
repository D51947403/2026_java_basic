import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldDemo2 {
    // main function
    public static void main(String[] args)
    {
        // Create a frame
        Frame f = new Frame("TextField Example");

        // Create a button
        Button b = new Button("Submit");
        // Set the position of the button
        b.setBounds(160, 100, 100, 30);

        // Create a text field
        TextField t = new TextField();

        // Set position of text field
        t.setBounds(160, 50, 200, 20);

        // Create labels
        Label l1 = new Label("Name");
        Label l2 = new Label(" ");

        // Set position of labels
        l1.setBounds(40, 55, 100, 14);
        l2.setBounds(150, 170, 400, 14);

        // Add the components to the frame
        f.add(b);
        f.add(t);
        f.add(l1);
        f.add(l2);

        // Add action listener
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                l2.setText("Name: " + t.getText());
            }
        });

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
    }
}
