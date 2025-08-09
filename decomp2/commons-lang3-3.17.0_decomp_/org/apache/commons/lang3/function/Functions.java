package org.apache.commons.lang3.function;

import java.util.function.Function;

public final class Functions {
   public static Object apply(Function function, Object object) {
      return function != null ? function.apply(object) : null;
   }

   public static Function function(Function function) {
      return function;
   }

   private Functions() {
   }
}
