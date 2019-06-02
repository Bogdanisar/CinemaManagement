package cinema.graphics;

import cinema.service.LoggerService;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GUI {
    static final String separator = new String(new char[40]).replace('\0', '=');

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final int TAB_ITEM_HEIGHT = 30;
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


    private Connection conn;
    private Logger logger;
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private CustomPanel[] panels = new CustomPanel[3];
    private volatile JTextArea textArea;
    private volatile List<String> outputMessages = Arrays.stream(new String[] {DEFAULT_ACTION_OUTPUT_MESSAGE}).collect(Collectors.toList());
    private volatile int messageIndex = 0;

    public GUI() throws SQLException, IOException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_project","root","root");
        this.logger = LoggerService.getInstance();

        this.frame = new JFrame("Cinema Management");
        this.frame.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.frame.pack();


        this.setupTabs();
        this.setupTextArea();
        this.setupButtons();


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (GUI.this.conn != null) {
                        GUI.this.conn.close();
                    }
                }
                catch (Exception except) {

                }

                super.windowClosing(e);
            }
        });


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
            public String[] run(String[] textFields, Connection conn) throws Exception {
                return new String[] {"The first operation was executed", "OK"};
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
            public String[] run(String[] textFields, Connection conn) throws Exception {
                return new String[] {"LEL, YOU EXPECT SOME ACTUAL STRING?", "OK"};
            }
        };

        ListAction[] actions1 = new ListAction[] {
                new ListAction.AddCategory(),
                new ListAction.AddFood(),
                new ListAction.AddMovie(),
                new ListAction.AddEmployee(),
                new ListAction.AddAuditorium(),
                new ListAction.AddScreeningToAuditorium(),
                new ListAction.AddUsherToScreening(),
                new ListAction.AddClient(),
                new ListAction.AddFundsToClient(),
                new ListAction.PurchaseTicketForClient(),
                new ListAction.PurchaseFoodForClient(),
                new ListAction.GetPersonsAtScreening(),
                new ListAction.GetScreeningsForEmployee()

        };
        ListAction[] actions2 = new ListAction[] {a1, a2};
        ListAction[] actions3 = new ListAction[] {a2, a1};
        this.panels[0] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight() - TAB_ITEM_HEIGHT, actions1);
        this.panels[1] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight() - TAB_ITEM_HEIGHT, actions2);
        this.panels[2] = new CustomPanel(FRAME_WIDTH, GUI.getTabsHeight() - TAB_ITEM_HEIGHT, actions2);


        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(0, 0, FRAME_WIDTH, GUI.getTabsHeight());

        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
             @Override
             protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                 return GUI.TAB_ITEM_HEIGHT - 10;
             }
        });

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
                inputs[i] = textFields[i].getText().toLowerCase();
            }


            new Thread(() -> {
                String[] operationStrings;
                try {
                    operationStrings = currentAction.run(inputs, GUI.this.conn);
                }
                catch (Exception except) {
                    operationStrings = new String[2];
                    operationStrings[0] = "Error";

                    operationStrings[1] = "The operation failed with this error message:\n\n";
                    operationStrings[1] += except.getMessage();

                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    except.printStackTrace(pw);
                    String sStackTrace = sw.toString();

                    operationStrings[1] += "\n\nThe stack trace:\n\n";
                    operationStrings[1] += sStackTrace;
                }

                String operationDescription = GUI.indentMessage(operationStrings[0]);
                String operationResult = GUI.indentMessage(operationStrings[1]);

                String message = "Description:\n";
                message += operationDescription + "\n\n";
                message += "Result: \n";
                message += operationResult;

                this.outputMessages.add(message);
                this.messageIndex = this.outputMessages.size() - 1;
                this.textArea.setText(message);

                this.logger.info(operationDescription);
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


    public static String INDENTATION = new String(new char[6]).replace('\0', ' ');

    public static String indentMessage(String message) {
        StringBuilder ret = new StringBuilder();
        ret.append(INDENTATION);
        for (char c : message.toCharArray()) {
            ret.append(c);

            if (c == '\n') {
                ret.append(INDENTATION);
            }
        }

        return ret.toString();
    }






    public static void main(String[] args) throws IOException, SQLException {
        new GUI();
    }

}
