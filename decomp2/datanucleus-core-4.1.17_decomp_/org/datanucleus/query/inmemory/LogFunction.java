package org.datanucleus.query.inmemory;

public class LogFunction extends MathFunction {
   protected String getFunctionName() {
      return "log";
   }

   protected double evaluateMathFunction(double num) {
      return Math.log(num);
   }
}
