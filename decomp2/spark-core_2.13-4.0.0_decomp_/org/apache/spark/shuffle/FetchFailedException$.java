package org.apache.spark.shuffle;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FetchFailedException$ implements Serializable {
   public static final FetchFailedException$ MODULE$ = new FetchFailedException$();

   public Throwable $lessinit$greater$default$7() {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FetchFailedException$.class);
   }

   private FetchFailedException$() {
   }
}
