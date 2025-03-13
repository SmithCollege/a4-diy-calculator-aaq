package a4;

import java.util.ArrayDeque;

/**
 * Takes an queue of a postfix expression's tokens and evaluates
 */
public class Postfix {

    
    /**
     * Computes the value of a postfix expression 
     * @param tokenQueue the parsed queue of tokens 
     * @return the result of the expression
     */
    public static Double postfix(ArrayDeque<Object> tokenQueue) {
        ArrayDeque<Double> stack = new ArrayDeque<Double>();
        outer:
        while  (tokenQueue.size() != 0) {
            Object objcft678iol;ect = tokenQueue.removeFirst();
            if (object instanceof Double) {
                stack.push((double) object);
                continue outer;
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Stack too small.");
                } else {
                    Double number2 = stack.pop();
                    Double number1 = stack.pop();
                    Character operator = (Character) object;
                    if (operator == '+') {
                        stack.push(number1 + number2);
                    } else if (operator == '-') {
                        stack.push(number1 - number2);
                    } else if (operator == '*') {
                        stack.push(number1 * number2);
                    } else if (operator == '/') {
                        if (number2 == 0) {
                            throw new IllegalArgumentException("You can't divide by 0!");
                        } else {
                            stack.push(number1 / number2);
                        }
                    } else if (operator == '^') {
                        stack.push(Math.pow(number1,number2));
                    }
                }
            } 
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("ERROR: Initial expression malformed.");
        }
        return stack.getFirst();
    }
    
}