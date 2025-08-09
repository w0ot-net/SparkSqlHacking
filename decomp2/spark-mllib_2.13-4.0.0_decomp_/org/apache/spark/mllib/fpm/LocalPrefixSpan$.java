package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LocalPrefixSpan$ implements Serializable {
   public static final LocalPrefixSpan$ MODULE$ = new LocalPrefixSpan$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(LocalPrefixSpan$.class);
   }

   private LocalPrefixSpan$() {
   }
}
