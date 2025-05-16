package a4;

import java.util.ArrayDeque;

/**
 * Infix class
 */
public class Infix {
    
    /**
     * Checks to see if a character is one of the recognized operators
     * @param ch character checked
     * @return true / false
     * 
     * Just to avoid cluttering in the infixToPostfix method, we have an 
     * external method to check if a character is an operator!
     * (Here, an operator is defined as +, -, *, /, ^. Parentheses are 
     * parsed separately)
     */
    public static Boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^'; 
    }

    /**
     * Assigns a precedence value to a character
     * @param ch the character
     * @return the precedence value (higher int = higher precedence)
     * 
     */
    public static int assignPrecedence(char ch) {
        if (!isOperator(ch)) {
            return -1;
        }
        else if ((char)ch == '+' || (char)ch == '-') {
            return 0;
        }
        else if ((char)ch == '*' || (char)ch == '/') {
            return 1;
        } else {
            return 2; 
        }
    }
    /**
     * Converts an equation into postfix notation
     * @param tokenQueue the parsed string equation in queue form (ArrayDeque)
     * @return calculated expression
     */
    public static Double infixToPostfix(ArrayDeque<Object> tokenQueue) {
        ArrayDeque<Object> outputQueue = new ArrayDeque<Object>(); 
        ArrayDeque<Character> operatorStack = new ArrayDeque<Character>();
        while (tokenQueue.size() != 0) {
            Object object = tokenQueue.removeFirst();
            if (object instanceof Double) {
                outputQueue.addLast((Double) object);
            } else {
                Character thisChar = '.';
                if (object instanceof Character) {
                    thisChar = (Character) object; 
                } else {
                    throw new IllegalArgumentException("Character not recognized");
                }
                if (thisChar == '(') {
                    operatorStack.addFirst(thisChar);
                }
                if (thisChar == ')') {
                    while ((char)operatorStack.peekFirst() != '(') {
                        Object operator = operatorStack.removeFirst();
                        outputQueue.addLast(operator);
                        if (operatorStack.isEmpty()) {
                            throw new IllegalArgumentException("Mismatched parentheses.");
                        }
                    }
                    operatorStack.removeFirst();
                }
                if (isOperator(thisChar)) {
                    Character queueOperator = thisChar; 
                    while (!operatorStack.isEmpty() && (assignPrecedence(operatorStack.peek()) >= assignPrecedence(queueOperator)) && (char)queueOperator != '^') {
                        Character stackOperator = operatorStack.removeFirst();
                        outputQueue.addLast(stackOperator);
                    }
                    operatorStack.addFirst(queueOperator);

                }
            }
        }
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peekFirst() == '(' || operatorStack.peekFirst() == ')') {
                throw new IllegalArgumentException("Mismatched parentheses");
            } else {
                Character object = operatorStack.removeFirst();
                outputQueue.addLast(object);
            }
        }
        return Postfix.postfix(outputQueue);
    }
}

