package org.datanucleus.query.inmemory;

public class SqrtFunction extends MathFunction {
   protected String getFunctionName() {
      return "sqrt";
   }

   protected double evaluateMathFunction(double num) {
      return Math.sqrt(num);
   }
}
