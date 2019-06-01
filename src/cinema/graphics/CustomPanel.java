package cinema.graphics;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private ListAction[] actions;
    private JList opList;
    private static final double OPS_SIZE_PERCENT = 0.4;
    private static final int NUM_INPUTS = 10;
    private static final int FONT_SIZE = 20;
    private JLabel[] labels = new JLabel[NUM_INPUTS];
    private JTextField[] inputs = new JTextField[NUM_INPUTS];


    public ListAction[] getActions() {
        return actions;
    }

    public JList getOpList() {
        return opList;
    }

    public JTextField[] getInputs() {
        return inputs;
    }




    public static int getLeftWidth(int width) {
        return (int) (OPS_SIZE_PERCENT * width);
    }

    public static int getRightWidth(int width) {
        return width - getLeftWidth(width);
    }

    public CustomPanel(int width, int height, ListAction[] actions) {
        super();
        this.actions = actions;

        this.setLayout(null);
        this.setSize(width, height);


        Object[] listItems = new Object[actions.length];
        for (int i = 0; i < listItems.length; ++i) {
            listItems[i] = actions[i].getActionName();
        }

        this.opList = new JList(listItems);
        this.opList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.opList.addListSelectionListener(e -> {
            int idx = this.opList.getSelectedIndex();
            String[] labelNames = CustomPanel.this.actions[idx].getLabelNames();

            for (int i = 0; i < labelNames.length; ++i) {
                CustomPanel.this.labels[i].setText( labelNames[i] );
            }
            for (int i = labelNames.length; i < CustomPanel.this.labels.length; ++i) {
                CustomPanel.this.labels[i].setText( "N/A" );
            }
        });

        JScrollPane listScroll = new JScrollPane(this.opList);
        listScroll.setBounds(0, 0, getLeftWidth(width), height);
        this.add(listScroll);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < this.labels.length; ++i) {
            this.labels[i] = new JLabel("N/A");

//            label.setBounds(0, (2 * i) * INPUT_HEIGHT, width / 2, INPUT_HEIGHT);
//            label.setPreferredSize(new Dimension(width / 2, INPUT_HEIGHT));

            this.labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.labels[i].setFont(new Font(this.labels[i].getFont().getName(), Font.PLAIN, FONT_SIZE));
            inputPanel.add(this.labels[i]);




            this.inputs[i] = new JTextField();

//            textField.setBounds(0, (2 * i + 1) * INPUT_HEIGHT, width / 2, INPUT_HEIGHT);
//            textField.setPreferredSize(new Dimension(width / 4, INPUT_HEIGHT));

            this.inputs[i].setFont(new Font(this.inputs[i].getFont().getName(), Font.PLAIN, FONT_SIZE));
            inputPanel.add(this.inputs[i]);
        }

        JScrollPane inputScroll = new JScrollPane(inputPanel);
        inputScroll.setBounds(getLeftWidth(width), 0, getRightWidth(width), height);
        this.add(inputScroll);
    }
}
