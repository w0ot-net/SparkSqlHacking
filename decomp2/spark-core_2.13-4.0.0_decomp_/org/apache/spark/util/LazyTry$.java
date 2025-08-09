package org.apache.spark.util;

import java.io.Serializable;
import scala.Function0;
import scala.runtime.ModuleSerializationProxy;

public final class LazyTry$ implements Serializable {
   public static final LazyTry$ MODULE$ = new LazyTry$();

   public LazyTry apply(final Function0 initialize) {
      return new LazyTry(initialize);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LazyTry$.class);
   }

   private LazyTry$() {
   }
}
