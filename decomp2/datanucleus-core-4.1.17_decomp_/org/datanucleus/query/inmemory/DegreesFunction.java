package org.datanucleus.query.inmemory;

public class DegreesFunction extends MathFunction {
   protected String getFunctionName() {
      return "degrees";
   }

   protected double evaluateMathFunction(double num) {
      return Math.toDegrees(num);
   }
}
