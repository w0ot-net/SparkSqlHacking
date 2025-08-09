package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ColumnPruner$ implements MLReadable, Serializable {
   public static final ColumnPruner$ MODULE$ = new ColumnPruner$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new ColumnPruner.ColumnPrunerReader();
   }

   public ColumnPruner load(final String path) {
      return (ColumnPruner)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ColumnPruner$.class);
   }

   private ColumnPruner$() {
   }
}
