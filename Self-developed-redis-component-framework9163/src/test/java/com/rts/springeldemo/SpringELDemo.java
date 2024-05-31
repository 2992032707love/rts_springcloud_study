package com.rts.springeldemo;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpringELDemo {
    public static void main(String[] args) {
        String var = "#userid";
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(var);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("userid","xxbb0818");
        String s = expression.getValue(context).toString();
        System.out.println(s);
    }
}
