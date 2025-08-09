package org.datanucleus.query.inmemory;

public class TangentFunction extends MathFunction {
   protected String getFunctionName() {
      return "tan";
   }

   protected double evaluateMathFunction(double num) {
      return Math.tan(num);
   }
}
