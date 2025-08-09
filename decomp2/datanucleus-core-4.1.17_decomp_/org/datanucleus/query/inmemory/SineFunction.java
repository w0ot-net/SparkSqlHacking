package org.datanucleus.query.inmemory;

public class SineFunction extends MathFunction {
   protected String getFunctionName() {
      return "sin";
   }

   protected double evaluateMathFunction(double num) {
      return Math.sin(num);
   }
}
