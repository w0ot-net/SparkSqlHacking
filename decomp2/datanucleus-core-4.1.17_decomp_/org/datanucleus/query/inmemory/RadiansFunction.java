package org.datanucleus.query.inmemory;

public class RadiansFunction extends MathFunction {
   protected String getFunctionName() {
      return "radians";
   }

   protected double evaluateMathFunction(double num) {
      return Math.toRadians(num);
   }
}
