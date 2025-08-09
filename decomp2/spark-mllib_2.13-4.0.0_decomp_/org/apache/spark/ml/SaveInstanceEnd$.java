package org.apache.spark.ml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SaveInstanceEnd$ extends AbstractFunction1 implements Serializable {
   public static final SaveInstanceEnd$ MODULE$ = new SaveInstanceEnd$();

   public final String toString() {
      return "SaveInstanceEnd";
   }

   public SaveInstanceEnd apply(final String path) {
      return new SaveInstanceEnd(path);
   }

   public Option unapply(final SaveInstanceEnd x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.path()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SaveInstanceEnd$.class);
   }

   private SaveInstanceEnd$() {
   }
}
