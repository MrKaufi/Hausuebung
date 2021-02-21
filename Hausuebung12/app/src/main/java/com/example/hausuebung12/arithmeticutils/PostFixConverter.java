package com.example.hausuebung12.arithmeticutils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>();

    public PostFixConverter(String expression) {
        infix = expression;
        convertExpression();
    }

    private void convertExpression() {
        String[] infixStrings = infix.split("(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])");
        for (int i = 0; i < infixStrings.length; i++) {
            String currentInfixString = infixStrings[i];
            if ("+-*/()".contains(currentInfixString)) {
                if (stack.peek() == null) {
                    stack.push(currentInfixString.charAt(0));
                } else if (getPrecedence(currentInfixString.charAt(0)) == 3) {
                    stack.push(currentInfixString.charAt(0));
                } else if (getPrecedence(currentInfixString.charAt(0)) > getPrecedence(stack.peek()) && getPrecedence(currentInfixString.charAt(0)) < 4) {
                    if (getPrecedence(currentInfixString.charAt(0)) == 3) {
                    }
                    stack.push(currentInfixString.charAt(0));
                } else if (getPrecedence(currentInfixString.charAt(0)) < getPrecedence(stack.peek())) {
                    if (getPrecedence(stack.peek()) < 3) {
                        postfix.add(stack.pop().toString());
                        stack.push(currentInfixString.charAt(0));
                    } else {
                        stack.pop();
                        stack.push(currentInfixString.charAt(0));
                    }
                } else if (getPrecedence(currentInfixString.charAt(0)) == getPrecedence(stack.peek())) {
                    if (getPrecedence(stack.peek()) < 3) {
                        postfix.add(stack.pop().toString());
                        stack.push(currentInfixString.charAt(0));
                    } else {
                        stack.pop();
                        stack.push(currentInfixString.charAt(0));
                    }
                } else if (getPrecedence(currentInfixString.charAt(0)) == 4) {
                    while (!stack.isEmpty()) {
                        Character temp = stack.pop();

                        if (temp.equals("(")) {
                            break;
                        } else postfix.add(temp.toString());
                    }
                }
            } else postfix.add(currentInfixString);
        }
        clearStack();
    }


    private void inputToStack(char input) {
        stack.addLast(input);
    }

    private int getPrecedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        } else if (op == '*' || op == '/') {
            return 2;
        } else if (op == '(') {
            return 3;
        } else if (op == ')') {
            return 4;
        }
        return 0;
    }

    private void clearStack() {
        while (!stack.isEmpty()) {
            if (!stack.peek().equals("(") ) {
                postfix.add(stack.pop().toString());
            }
        }

    }

    public String getInfixExpression() {
        String infixExpression = "";
        for (String s : postfix) {
            infixExpression += s;
        }
        return infixExpression;
    }

    public List<String> getPostfixAsList() {
        return postfix;
    }
}
