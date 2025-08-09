package org.apache.spark.ml;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class TransformEnd$ extends AbstractFunction0 implements Serializable {
   public static final TransformEnd$ MODULE$ = new TransformEnd$();

   public final String toString() {
      return "TransformEnd";
   }

   public TransformEnd apply() {
      return new TransformEnd();
   }

   public boolean unapply(final TransformEnd x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TransformEnd$.class);
   }

   private TransformEnd$() {
   }
}
