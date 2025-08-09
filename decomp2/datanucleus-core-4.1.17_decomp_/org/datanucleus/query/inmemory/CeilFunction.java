package org.datanucleus.query.inmemory;

public class CeilFunction extends MathFunction {
   protected String getFunctionName() {
      return "ceil";
   }

   protected double evaluateMathFunction(double num) {
      return Math.ceil(num);
   }
}
