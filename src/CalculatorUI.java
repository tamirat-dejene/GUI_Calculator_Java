import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorUI extends JFrame {
    public static final int WIDTH = 295;
    public static final int HEIGHT = 515;

    private JTextArea expression;
    private JTextArea result;
    
    private JButton zero = new JButton("0");
    
    private JButton one = new JButton("1");
    private JButton two = new JButton("2");
    private JButton three = new JButton("3");
    private JButton four = new JButton("4");
    private JButton five = new JButton("5");
    private JButton six = new JButton("6");
    private JButton seven = new JButton("7");
    private JButton eight = new JButton("8");
    private JButton nine = new JButton("9");

    private JButton multiplication = new JButton("*");
    private JButton addition = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton division = new JButton("/");
    private JButton equal = new JButton("=");
    private JButton point = new JButton(".");

    private JButton clear = new JButton("C");
    private JButton delete = new JButton("X");

    public CalculatorUI() {
        super("Calculator");
        setBounds(100, 100, WIDTH, HEIGHT);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("../resources/calc.png");
        setIconImage(img.getImage());

        expression = createTextArea();
        // expression.setBackground(Color.ORANGE);
        addComponent(expression, 0, 0, 1, 1);

        // JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        // add(splitPane);

        result = createTextArea();
        addComponent(result, 1, 0, 1, 1);

        JPanel clearOptionPanel = new JPanel(new FlowLayout());

        clear.setBackground(Color.LIGHT_GRAY);
        clear.setFont(new Font("Arial", Font.PLAIN, 20));
        clearOptionPanel.add(clear);
        delete.setBackground(Color.PINK);
        delete.setFont(new Font("Arial", Font.PLAIN, 20));
        clearOptionPanel.add(delete);

        addComponent(clearOptionPanel, 2, 0, 1, 1);

        JPanel keyPads = addKeyPadPanels(new JPanel(new GridLayout(4, 4)));
        addComponent(keyPads, 3, 0, 1, 1);
    }
    
    // Helper methods
    
    private void addComponent(Component component, int gridy, int gridx, int gridheight, int gridwidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridheight = gridheight;
        gbc.gridwidth = gridwidth;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = (gridy == 2) ? 0.01 : 1.0;

        add(component, gbc);
    }
    
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(1, 5);
        textArea.setFont(new Font("Arial", Font.BOLD, 20));
        textArea.setBackground(Color.WHITE); 
        textArea.setEditable(false);
        return textArea;
    }
    
    JPanel addKeyPadPanels(JPanel keyPads) {
        seven.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(seven);
        eight.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(eight);
        nine.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(nine);
        multiplication.setFont(new Font("Arial", Font.BOLD, 24));    keyPads.add(multiplication);
        four.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(four);
        five.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(five);
        six.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(six);
        subtraction.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(subtraction);
        one.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(one);
        two.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(two);
        three.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(three);
        addition.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(addition);
        zero.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(zero);
        point.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(point);
        division.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(division);
        equal.setFont(new Font("Arial", Font.BOLD, 20));    keyPads.add(equal);
        return keyPads;
    }


    public JTextArea getExpression() {
        return this.expression;
    }

    public void setExpression(String exp) {
        expression.setText(expression.getText() + exp);
    }

    public void resetExpression() {
        expression.setText("");
        result.setText("");
    }

    public void deleteLastButtonInput() {
        String s = expression.getText();
        if(s.length() >= 2)
            expression.setText(s.substring(0, s.length() - 1));
        else
            resetExpression();
    }

    public double getResult() {
        return Double.parseDouble(this.result.getText());
    }

    public void setResult(String output) {
        result.setText(output);
    }

    // Numbers and Operands except = Button Listener Methods
    public void buttonListener(ActionListener action) {
        zero.addActionListener(action);
        one.addActionListener(action);
        two.addActionListener(action);
        three.addActionListener(action);
        four.addActionListener(action);
        five.addActionListener(action);
        six.addActionListener(action);
        seven.addActionListener(action);
        eight.addActionListener(action);
        nine.addActionListener(action);
        point.addActionListener(action);
        division.addActionListener(action);
        multiplication.addActionListener(action);
        addition.addActionListener(action);
        subtraction.addActionListener(action);
        delete.addActionListener(action);
        clear.addActionListener(action);
    }

    public void equalButtonListener(ActionListener action) {
        equal.addActionListener(action);
    }


    // Error Message Displayer
    public void displayErrorMessage(String theMessage) {
        JOptionPane.showMessageDialog(this, theMessage, "Ooops!", JOptionPane.ERROR_MESSAGE);
    } 
}
