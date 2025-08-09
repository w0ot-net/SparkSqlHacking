package org.datanucleus.query.inmemory;

public class CosineFunction extends MathFunction {
   protected String getFunctionName() {
      return "cos";
   }

   protected double evaluateMathFunction(double num) {
      return Math.cos(num);
   }
}
