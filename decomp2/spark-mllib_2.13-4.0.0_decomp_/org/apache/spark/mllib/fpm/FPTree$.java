package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FPTree$ implements Serializable {
   public static final FPTree$ MODULE$ = new FPTree$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(FPTree$.class);
   }

   private FPTree$() {
   }
}
