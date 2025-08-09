package org.datanucleus.query.inmemory;

public class AbsFunction extends MathFunction {
   protected String getFunctionName() {
      return "abs";
   }

   protected double evaluateMathFunction(double num) {
      return Math.abs(num);
   }
}
