package org.apache.avro.util.internal;

import java.util.function.Supplier;

public class ThreadLocalWithInitial {
   public static ThreadLocal of(Supplier supplier) {
      return ThreadLocal.withInitial(supplier);
   }
}
