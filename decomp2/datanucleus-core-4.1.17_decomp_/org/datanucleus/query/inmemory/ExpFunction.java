package org.datanucleus.query.inmemory;

public class ExpFunction extends MathFunction {
   protected String getFunctionName() {
      return "exp";
   }

   protected double evaluateMathFunction(double num) {
      return Math.exp(num);
   }
}
