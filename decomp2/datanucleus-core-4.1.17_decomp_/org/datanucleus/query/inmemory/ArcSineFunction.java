package org.datanucleus.query.inmemory;

public class ArcSineFunction extends MathFunction {
   protected String getFunctionName() {
      return "asin";
   }

   protected double evaluateMathFunction(double num) {
      return Math.asin(num);
   }
}
