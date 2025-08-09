package org.datanucleus.query.inmemory;

public class ArcTangentFunction extends MathFunction {
   protected String getFunctionName() {
      return "atan";
   }

   protected double evaluateMathFunction(double num) {
      return Math.atan(num);
   }
}
