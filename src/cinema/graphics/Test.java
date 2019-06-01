package cinema.graphics;

import javax.swing.*;
import java.awt.*;

public class Test {
    static final String separator = new String(new char[40]).replace('\0', '=');

    public static void testSwing() {
        JFrame frame = new JFrame("Cinema Management");

        final int frameWidth = 1000;
        final int frameHeight = 600;
        frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.pack();



        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, frameWidth, frameHeight / 2);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, frameWidth, frameHeight / 2);

        JButton button = new JButton("Click me");
        button.setBounds(0, 0, 100, 100);
        button.addActionListener(e -> {
            System.out.println(separator);
            System.out.println(tabs.getSelectedIndex());
            System.out.println(separator);
        });
        panel.add(button);

        tabs.addTab("Admin", null, panel, "The first tab");


        panel = new JPanel();
        panel.setBounds(0, 0, frameWidth, frameHeight / 2);

        button = new JButton("Second button");
        button.setBounds(0, 0, 100, 1000);
        button.addActionListener(e -> {
            System.out.println(separator);
            System.out.println(tabs.getSelectedIndex());
            System.out.println(separator);
        });
        panel.add(button);

        tabs.addTab("Admin", null, panel, "The second tab");


        frame.add(tabs);


        JTextArea area = new JTextArea();
        area.setBounds(frameWidth / 4, frameHeight / 2, frameWidth / 2, frameHeight / 2);
        area.setText("idk");
        area.setEditable(false);
        frame.add(area);


        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        testSwing();
    }
}
