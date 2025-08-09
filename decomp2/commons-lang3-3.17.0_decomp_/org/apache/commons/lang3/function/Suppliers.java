package org.apache.commons.lang3.function;

import java.util.function.Supplier;

public class Suppliers {
   private static Supplier NUL = () -> null;

   public static Object get(Supplier supplier) {
      return supplier == null ? null : supplier.get();
   }

   public static Supplier nul() {
      return NUL;
   }
}
