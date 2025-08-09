package org.apache.spark.util.random;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AcceptanceResult$ implements Serializable {
   public static final AcceptanceResult$ MODULE$ = new AcceptanceResult$();

   public long $lessinit$greater$default$1() {
      return 0L;
   }

   public long $lessinit$greater$default$2() {
      return 0L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AcceptanceResult$.class);
   }

   private AcceptanceResult$() {
   }
}
