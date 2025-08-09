package org.apache.spark.util.collection;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AppendOnlyMap$ implements Serializable {
   public static final AppendOnlyMap$ MODULE$ = new AppendOnlyMap$();
   private static final int MAXIMUM_CAPACITY = 536870912;

   public int $lessinit$greater$default$1() {
      return 64;
   }

   public int MAXIMUM_CAPACITY() {
      return MAXIMUM_CAPACITY;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AppendOnlyMap$.class);
   }

   private AppendOnlyMap$() {
   }
}
