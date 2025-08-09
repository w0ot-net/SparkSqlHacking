package org.apache.spark.util.random;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BernoulliCellSampler$ implements Serializable {
   public static final BernoulliCellSampler$ MODULE$ = new BernoulliCellSampler$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BernoulliCellSampler$.class);
   }

   private BernoulliCellSampler$() {
   }
}
