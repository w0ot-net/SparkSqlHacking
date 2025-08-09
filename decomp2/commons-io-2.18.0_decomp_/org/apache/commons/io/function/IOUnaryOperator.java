package org.apache.commons.io.function;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface IOUnaryOperator extends IOFunction {
   static IOUnaryOperator identity() {
      return (t) -> t;
   }

   default UnaryOperator asUnaryOperator() {
      return (t) -> Uncheck.apply(this, t);
   }
}
