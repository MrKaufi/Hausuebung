package com.example.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import static com.example.calculator.PostFixConverter.*;

public class PostFixCalculator {
    private List<String> expression;
    //private Deque<Double> stack = new ArrayDeque<>();
    private Stack<Double> stack = new Stack<>();

    public PostFixCalculator(List<String> postFix) {
        this.expression = postFix;
    }

    public BigDecimal getResult() {
        for (String s : expression) {
            if (isNumber(s)) {
                stack.push(Double.parseDouble(s));
            } else if ("+-*/".contains(s)) {
                double d1 = stack.pop();
                double d2 = stack.pop();

                switch (s) {
                    case "+":
                        stack.push(d2 + d1);
                        break;
                    case "-":
                        stack.push(d2 - d1);
                        break;
                    case "*":
                        stack.push(d2 * d1);
                        break;
                    case "/":
                        stack.push(d2 / d1);
                        break;
                    default:
                        break;
                }
            }
        }

        return BigDecimal.valueOf(stack.peek());
    }
}
