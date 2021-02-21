package com.example.testrechner.arithmeticutils;

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
        for (int i = 0; i < infix.length(); i++) {
            String currentInfixString = infixStrings[i];
            if ("+-*/()".contains(currentInfixString)) {
                //Strich Regel
                if (currentInfixString.equals("+") || currentInfixString.equals("-")) {
                    if (stack.peek().equals('*') || stack.peek().equals('/')) {
                        postfix.add(stack.pop().toString());
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('-') || stack.peek().equals('+')) {
                        postfix.add(stack.pop().toString());
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('(')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals(')')) {
                        postfix.add(stack.peek().toString());
                        stack.removeLast();
                    }
                }
                //Punkt Regel
                else if (currentInfixString.equals("*") || currentInfixString.equals("/")) {
                    if (stack.peek().equals('*') || stack.peek().equals('/')) {
                        postfix.add(stack.pop().toString());
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('+') || stack.peek().equals('-')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('(')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals(')')) {
                        postfix.add(stack.peek().toString());
                        stack.pop();
                    }
                }
                //Klammer auf Regel
                else if (currentInfixString.equals("(")) {
                    if (stack.peek().equals('*') || stack.peek().equals('/')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('+') || stack.peek().equals('-')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals('(')) {
                        stack.push(currentInfixString.charAt(0));
                    } else if (stack.peek().equals(')')) {
                        stack.pop();
                    }
                }
                //Klammer zu Regel
                else if (currentInfixString.equals(")")) {
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

        return 0;
    }

    private void clearStack() {
        stack.clear();
    }

    public String getfixExpression() {
        String fixExpression = "";
        for (String s : postfix) {
            fixExpression += s;
        }
        return fixExpression;
    }

    public List<String> getPostfixAsList() {
        return postfix;
    }
}
