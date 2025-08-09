package org.apache.spark.ml;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class TransformStart$ extends AbstractFunction0 implements Serializable {
   public static final TransformStart$ MODULE$ = new TransformStart$();

   public final String toString() {
      return "TransformStart";
   }

   public TransformStart apply() {
      return new TransformStart();
   }

   public boolean unapply(final TransformStart x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TransformStart$.class);
   }

   private TransformStart$() {
   }
}
