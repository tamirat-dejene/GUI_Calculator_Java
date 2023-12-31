public class Calculator {
    public static void main(String[] args) {
        CalculatorBackEnd theModel = new CalculatorBackEnd();
        CalculatorUI theView = new CalculatorUI();

        CalculatorController theController = new CalculatorController(theModel, theView);
        theController.getTheView().setVisible(true);
        theController.getTheView().setResizable(false);
    }
}
