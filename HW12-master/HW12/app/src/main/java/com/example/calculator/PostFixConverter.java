package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostFixConverter {
    private String infix;
    //private Deque<String> stack = new ArrayDeque<>();
    private Stack<String> stack = new Stack<>();
    private List<String> postFix = new ArrayList<>();

    public PostFixConverter(String expr) {
        this.infix = expr;
        convertExpression();
    }

    public PostFixConverter(CharSequence text) {
        this(text.toString());
    }

    private void convertExpression() {
        String[] strings = infix.split("(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])");

        for (String s : strings) {
            if (isNumber(s)) {
                postFix.add(s);
            } else if ("+-*/".contains(s)) {
                if (stack.isEmpty()) {
                    stack.push(s);
                } else {
                    if (getPrecedence(stack.peek()) == 2 || getPrecedence(stack.peek()) < getPrecedence(s)) {
                        stack.push(s);
                    } else if (getPrecedence(stack.peek()) >= getPrecedence(s)) {
                        postFix.add(stack.pop());
                        stack.push(s);
                    }
                }
            } else if (getPrecedence(s) == 2) {
                stack.push(s);
            } else if (getPrecedence(s) == 3) {
                while (!stack.isEmpty()) {
                    String pop = stack.pop();

                    if (!"(".equals(pop))
                        postFix.add(pop);
                    else
                        break;
                }
            }
        }

        while (!stack.isEmpty())
            postFix.add(stack.pop());
    }

    public static int getPrecedence(char op) {
        return getPrecedence("" + op);
    }

    public static int getPrecedence(String op) {
        if (null == op)
            return -1;

        if ("+-".contains(op))
            return 0;

        if ("*/".contains(op))
            return 1;

        if ("(".equals(op))
            return 2;

        if (")".equals(op))
            return 3;

        return -1;
    }

    public static boolean isNumber(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getPostFixExpression() {
        StringBuilder s = new StringBuilder();

        for (String temp : postFix)
            s.append(temp);

        return s.toString();
    }

    public List<String> getPostFixAsList() {
        return postFix;
    }
}
