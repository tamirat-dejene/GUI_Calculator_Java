import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorBackEnd theModel;
    private CalculatorUI theView;

    public CalculatorController(CalculatorBackEnd theModel, CalculatorUI theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.theView.buttonListener(new EquationListener());
        this.theView.equalButtonListener(new EqualListener());
    }

    public CalculatorUI getTheView() {
        return theView;
    }

    public CalculatorBackEnd getTheModel() {
        return theModel;
    }

    private class EquationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            switch (actionCommand) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                case "+":
                case "-":
                case "*":
                case "":
                case "/":
                    theView.setExpression(actionCommand);
                    break;
                case "X":
                    theView.deleteLastButtonInput();
                    break;
                case "C":
                    theView.resetExpression();
                    break;
                default:
                    theView.displayErrorMessage("System Error");
            }
        }
    }

    private class EqualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String infixExpress = theView.getExpression().getText();

                theModel.setExpression(infixExpress);
                double result = theModel.getResult();
                theView.setResult(Double.toString(result));
            }
            catch (Exception f) {
                if (f.getMessage() != "Division by zero")
                    theView.displayErrorMessage("Invalid Expression");
                else
                    theView.displayErrorMessage(f.getMessage());
            }
        }
    }
}
