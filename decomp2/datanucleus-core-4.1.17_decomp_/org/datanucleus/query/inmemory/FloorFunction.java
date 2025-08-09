package org.datanucleus.query.inmemory;

public class FloorFunction extends MathFunction {
   protected String getFunctionName() {
      return "floor";
   }

   protected double evaluateMathFunction(double num) {
      return Math.floor(num);
   }
}
