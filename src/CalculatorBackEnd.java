// class StackNode {
//     private char operator;
//     private StackNode next;

//     StackNode(char value) {
//         this.operator = value;
//         this.next = null;
//     }

//     public char getOperator() {
//         return this.operator;
//     }

//     public void setOperator(char newOperator) {
//         this.operator = newOperator;
//     }

//     public StackNode getNext() {
//         return this.next;
//     }

//     public void setNext(StackNode newNext) {
//         this.next = newNext;
//     }
// }

// class Stack {
//     StackNode top;

//     public Stack() {
//         top = null;
//     }

//     public void push(char data) {
//         if (isEmpty()) {
//             top = new StackNode(data);
//             return;
//         }
//         StackNode newNode = new StackNode(data);
//         newNode.setNext(top);
//         top = newNode;
//     }

//     public boolean isEmpty() {
//         return (top == null);
//     }

//     public void pop() {
//         try{
//             top = top.getNext();
//         }
//         catch (NullPointerException e) {
//             System.out.println("Stack Underflow");
//         }
//     }
    
//     public char top() {
//         try {
//             if (top != null) {
//                 return top.getOperator();
//             } else {
//                 // You can choose to return a default value or throw a specific exception.
//                 // For simplicity, let's throw an IllegalStateException in this case.
//                 throw new IllegalStateException("Stack is empty");
//             }
//         } catch (NullPointerException e) {
//             // It's generally not a good practice to catch NullPointerException explicitly
//             // unless you're doing something specific with it. In this case, it's better
//             // to check for null explicitly in the code above.
//             throw e;
//         }
//     }
// }

import java.util.Stack;

public class CalculatorBackEnd {
    private String expression;
    private double result;

    public CalculatorBackEnd() {
        expression = "";
    }

    public String getExpresssion() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() throws Exception {
        try{
            result = evaluatePostfixExpression(convertToPostfixExpression(expression));
            return result;
        }
        catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static String convertToPostfixExpression(String infixExpression){
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfixExpression = new StringBuilder();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); ++i) {
            char currentChar = infixExpression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                number.append(currentChar);
            } 
            else {
                if (number.length() > 0) {
                    postfixExpression.append(number).append(", ");
                    number.setLength(0); // Reset the number StringBuilder
                }

                while (!operatorStack.isEmpty() && precedence(currentChar) <= precedence(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop()).append(", ");
                }

                operatorStack.push(currentChar);
            }
        }

        // Append any remaining number
        if (number.length() > 0) {
            postfixExpression.append(number).append(", ");
        }

        // Pop remaining operators from the stack
        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop()).append(", ");
        }

        // Remove the trailing comma and space
        if (postfixExpression.length() > 0) {
            postfixExpression.setLength(postfixExpression.length() - 2);
        }
        return postfixExpression.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
    
    public static double evaluatePostfixExpression(String postfixExpression) {
        Stack<Double> operandStack = new Stack<>();

        String[] tokens = postfixExpression.split(",\\s+");

        for (String token : tokens) {
            if (isNumeric(token)) {
                operandStack.push(Double.parseDouble(token));
            } else {
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                double result = performOperation(operand1, operand2, token);
                operandStack.push(result);
            }
        }
       
        if (operandStack.size() != 1 || postfixExpression.length() == 0) {
            throw new IllegalArgumentException("Invalid Expression");
        }
        return operandStack.pop();
    }
    
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    // public static void main(String[] args) {
    //     String postfixExpression = "3, 4, 2, *, 1, 5, -, 2, 3, ^, ^, /, +";
    //     double result = evaluatePostfixExpression(postfixExpression);
    //     System.out.println("Postfix Expression: " + postfixExpression);
    //     System.out.println("Result: " + result);
    // }
}
