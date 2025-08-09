package org.datanucleus.query.inmemory;

public class ArcCosineFunction extends MathFunction {
   protected String getFunctionName() {
      return "acos";
   }

   protected double evaluateMathFunction(double num) {
      return Math.acos(num);
   }
}
