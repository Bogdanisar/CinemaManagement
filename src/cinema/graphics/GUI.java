package cinema.graphics;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.stream.Collectors;

public class GUI {
    static final String separator = new String(new char[40]).replace('\0', '=');

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final double TEXT_AREA_PERCENT = 0.25;
    private static final double BUTTON_AREA_PERCENT = 0.15;
    private static final int TEXT_AREA_MARGIN = 10;
    private static final int BUTTON_HORIZONTAL_GAP = 10;
    private static final double BUTTON_HEIGHT_PERCENT = 0.9;
    private static final String DEFAULT_ACTION_OUTPUT_MESSAGE = "This will have output / error data";

    private static int getTextAreaHeight() {
        return (int) (TEXT_AREA_PERCENT * FRAME_HEIGHT);
    }

    private static int getButtonAreaHeight() {
        return (int) (BUTTON_AREA_PERCENT * FRAME_HEIGHT);
    }

    private static int getTabsHeight() {
        return FRAME_HEIGHT - getTextAreaHeight() - getButtonAreaHeight();
    }

    private static int getButtonWidth() {
        double val = (FRAME_WIDTH - 2 * BUTTON_HORIZONTAL_GAP - 30) / 3;
//        val *= 0.5;
        return (int) val;
    }

    private static int getButtonHeight() {
        return (int) (BUTTON_HEIGHT_PERCENT * getButtonAreaHeight());
    }

    private static int getButtonVerticalGap() {
        return (getButtonAreaHeight() - getButtonHeight()) / 2;
    }


    private JFrame frame;
    private JTabbedPane tabbedPane;
    private CustomPanel[] panels = new CustomPanel[3];
    private volatile JTextArea textArea;
    private volatile List<String> outputMessages = Arrays.stream(new String[] {DEFAULT_ACTION_OUTPUT_MESSAGE}).collect(Collectors.toList());
    private volatile int messageIndex = 0;

    public GUI() {
        this.frame = new JFrame("Cinema Management");
        this.frame.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.frame.pack();


        this.setupTabs();
        this.setupTextArea();
        this.setupButtons();


        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
    }

    private void setupTabs() {

        ListAction a1 = new ListAction() {
            @Override
            public String getActionName() {
                return "First Action";
            }

            @Override
            public String[] getLabelNames() {
                return new String[] { "First Label", "Second Label" };
            }

            @Override
            public String run(String[] textFields) throws Exception {
                return "The first operation was executed";
            }
        };
        ListAction a2 = new ListAction() {
            @Override
            public String getActionName() {
                return "User Actiksajdksaljdlksadjlksadjlksajdlksajdlkasndfkljsdhbkjgjasp';dfjbajfklaesvn;hjhsbdkxnjlc;jvbhxdsfladvxjbksdflxc;vnbkzjfxlkvljzfgkjlon";
            }

            @Override
            public String[] getLabelNames() {
                return new String[] { "Ceva", "Mai", "Funky" };
            }

            @Override
            public String run(String[] textFields) throws Exception {
                return "LEL, YOU EXPECT SOME ACTUAL STRING?";
            }
        };

        ListAction[] actions1 = new ListAction[] {a1, a2};
        ListAction[] actions2 = new ListAction[] {a2, a1};
        this.panels[0] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight(), actions1);
        this.panels[1] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight(), actions2);
        this.panels[2] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight(), actions2);


        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(0, 0, FRAME_WIDTH, GUI.getTabsHeight());

        this.tabbedPane.addTab("Admin", null, this.panels[0], "The first tab");
        this.tabbedPane.addTab("User", null, this.panels[1], "The second tab");
        this.tabbedPane.addTab("Info", null, this.panels[2], "The third tab");

        this.frame.add(this.tabbedPane);
    }

    private void setupTextArea() {
        this.textArea = new JTextArea(DEFAULT_ACTION_OUTPUT_MESSAGE);
        this.textArea.setBounds(0, GUI.getTabsHeight(), FRAME_WIDTH, GUI.getTextAreaHeight());
        this.textArea.setMargin(new Insets(TEXT_AREA_MARGIN, TEXT_AREA_MARGIN, TEXT_AREA_MARGIN, TEXT_AREA_MARGIN));
        this.textArea.setEditable(false);

        JScrollPane textAreaScroll = new JScrollPane(this.textArea);
        textAreaScroll.setBounds(0, GUI.getTabsHeight(), FRAME_WIDTH, GUI.getTextAreaHeight());

        this.frame.add(textAreaScroll);
    }

    private void setupButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, BUTTON_HORIZONTAL_GAP, getButtonVerticalGap()));
        buttonsPanel.setBounds(0, GUI.getTabsHeight() + GUI.getTextAreaHeight(), FRAME_WIDTH, GUI.getButtonAreaHeight());

        this.setupFirstButton(buttonsPanel);
        this.setupSecondButton(buttonsPanel);
        this.setupThirdButton(buttonsPanel);

        this.frame.add(buttonsPanel);
    }

    private void setupFirstButton(JPanel buttonsPanel) {
        JButton button = new JButton("Previous");
        button.setPreferredSize( new Dimension(GUI.getButtonWidth(), GUI.getButtonHeight()) );

        button.addActionListener(e -> {
            if (this.messageIndex > 0) {
                this.messageIndex -= 1;
                this.textArea.setText(this.outputMessages.get(this.messageIndex));
            }
        });

        buttonsPanel.add(button);
    }

    private void setupSecondButton(JPanel buttonsPanel) {

        JButton button = new JButton("Submit");
        button.setPreferredSize( new Dimension(GUI.getButtonWidth(), GUI.getButtonHeight()) );

        button.addActionListener(e -> {
            int tabIndex = this.tabbedPane.getSelectedIndex();
            CustomPanel tab = this.panels[tabIndex];

            JList opList = tab.getOpList();
            if (opList.getSelectedIndex() < 0) {
                return;
            }

            ListAction[] actionList = tab.getActions();
            ListAction currentAction = actionList[opList.getSelectedIndex()];

            JTextField[] textFields = tab.getInputs();
            String[] inputs = new String[textFields.length];
            for (int i = 0; i < textFields.length; ++i) {
                inputs[i] = textFields[i].getText();
            }


            new Thread(() -> {
                String operationOutput;
                try {
                    operationOutput = currentAction.run(inputs);
                }
                catch (Exception except) {
                    operationOutput = "The operation failed with this error message:\n\n";
                    operationOutput += except.getMessage();

                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    except.printStackTrace(pw);
                    String sStackTrace = sw.toString();

                    operationOutput += "\n\nThe stack trace:\n\n";
                    operationOutput += sStackTrace;
                }

                this.outputMessages.add(operationOutput);
                this.messageIndex = this.outputMessages.size() - 1;
                this.textArea.setText(operationOutput);
            }).start();
        });

        buttonsPanel.add(button);
    }

    private void setupThirdButton(JPanel buttonsPanel) {
        JButton button = new JButton("Next");
        button.setPreferredSize( new Dimension(GUI.getButtonWidth(), GUI.getButtonHeight()) );

        button.addActionListener(e -> {
            if (this.messageIndex < this.outputMessages.size() - 1) {
                this.messageIndex += 1;
                this.textArea.setText(this.outputMessages.get(this.messageIndex));
            }
        });

        buttonsPanel.add(button);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
