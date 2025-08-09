package org.apache.spark.ml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SaveInstanceStart$ extends AbstractFunction1 implements Serializable {
   public static final SaveInstanceStart$ MODULE$ = new SaveInstanceStart$();

   public final String toString() {
      return "SaveInstanceStart";
   }

   public SaveInstanceStart apply(final String path) {
      return new SaveInstanceStart(path);
   }

   public Option unapply(final SaveInstanceStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.path()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SaveInstanceStart$.class);
   }

   private SaveInstanceStart$() {
   }
}
