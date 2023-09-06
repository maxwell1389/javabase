package com.github.maxwell.base.expression;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MainTest {

    @Test
    public void test1() {
        System.out.println("start");
        Expression expression = AviatorEvaluator.compile("2.555 * (3 + 5)");
        Double r = (Double) expression.execute();
        System.out.println("r" + r);
        System.out.println("end");
    }

}
