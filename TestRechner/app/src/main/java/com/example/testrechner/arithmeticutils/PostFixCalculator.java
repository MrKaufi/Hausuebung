package com.example.testrechner.arithmeticutils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixCalculator {
    private List<String> expression = new ArrayList<String>();
    private Deque<Double> stack = new ArrayDeque<Double>();


    public PostFixCalculator(List<String> postfix) {
        String expressionString = "";
        for (String s : postfix) {
            expressionString += s;
        }
        PostFixConverter converter = new PostFixConverter(expressionString);
        expression = converter.getPostfixAsList();
    }

    public BigDecimal getResult() {
        double tempDouble = 0.0;
        for (int i = 0; i < expression.size(); i++) {
            String currentPostFixString = expression.get(i);
            if (currentPostFixString.equals("+") || currentPostFixString.equals("-") || currentPostFixString.equals("*") || currentPostFixString.equals("/") || currentPostFixString.equals("(") || currentPostFixString.equals(")")) {
                if (currentPostFixString.equals("+")) {
                    tempDouble = Integer.parseInt(expression.get(i - 2)) + Integer.parseInt(expression.get(i - 1));
                    stack.removeLast();
                    stack.removeLast();
                    stack.addLast(tempDouble);
                } else if (currentPostFixString.equals("-")) {
                    tempDouble = Double.parseDouble(expression.get(i - 2)) - Double.parseDouble(expression.get(i - 1));
                    stack.removeLast();
                    stack.removeLast();
                    stack.addLast(tempDouble);
                } else if (currentPostFixString.equals("*")) {
                    tempDouble = Double.parseDouble(expression.get(i - 2)) * Double.parseDouble(expression.get(i - 1));
                    stack.removeLast();
                    stack.removeLast();
                    stack.addLast(tempDouble);
                } else if (currentPostFixString.equals("/")) {
                    tempDouble = Double.parseDouble(expression.get(i - 2)) / Double.parseDouble(expression.get(i - 1));
                    stack.removeLast();
                    stack.removeLast();
                    stack.addLast(tempDouble);
                }
            } else stack.addLast(Double.valueOf(currentPostFixString));
        }
        return new BigDecimal(stack.getLast());
    }
}


