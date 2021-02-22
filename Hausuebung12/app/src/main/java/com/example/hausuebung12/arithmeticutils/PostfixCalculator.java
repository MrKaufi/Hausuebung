package com.example.hausuebung12.arithmeticutils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class PostfixCalculator {
    private List<String> expression;
    private Deque<Double> stack = new ArrayDeque<Double>();

    public PostfixCalculator(List<String> postfix) {
        expression = postfix;
    }

    public BigDecimal getResult() {

        for (int i = 0; i < expression.size(); i++) {
            String currentPostFixString = expression.get(i);

            if ("+-*/".contains(currentPostFixString)) {
                double x = stack.pop();
                double y = stack.pop();
                if (currentPostFixString.equals("+")) {
                    stack.push(y+x);
                } else if (currentPostFixString.equals("-")) {
                    stack.push(y-x);
                } else if (currentPostFixString.equals("*")) {
                    stack.push(y*x);
                } else if (currentPostFixString.equals("/")) {
                    stack.push(y/x);
                }
            } else stack.push(Double.valueOf(currentPostFixString));
        }
        return new BigDecimal(stack.pop());
    }
}
