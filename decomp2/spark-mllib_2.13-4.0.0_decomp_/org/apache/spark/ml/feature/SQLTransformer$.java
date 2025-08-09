package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class SQLTransformer$ implements DefaultParamsReadable, Serializable {
   public static final SQLTransformer$ MODULE$ = new SQLTransformer$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public SQLTransformer load(final String path) {
      return (SQLTransformer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SQLTransformer$.class);
   }

   private SQLTransformer$() {
   }
}
