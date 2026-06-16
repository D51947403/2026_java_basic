package org.demoswing;

import javax.swing.*;

public class ButtonDemo {
    ButtonDemo()
    {
        JFrame f = new JFrame();

        // Button 1 created
        // OK button
        JButton b1 = new JButton("OK");
        b1.setBounds(100, 50, 50, 50);
        f.add(b1);

        // Button 2 created
        // Submit button
        JButton b2 = new JButton("SUBMIT");
        b2.setBounds(100, 101, 50, 50);
        f.add(b2);

        // Button 3 created
        // Cancel button
        JButton b3 = new JButton("CANCEL");
        b3.setBounds(100, 150, 80, 50);
        f.add(b3);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String a[]) { new ButtonDemo(); }
}
