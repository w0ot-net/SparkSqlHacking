package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class StringIndexerModel$ implements MLReadable, Serializable {
   public static final StringIndexerModel$ MODULE$ = new StringIndexerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new StringIndexerModel.StringIndexerModelReader();
   }

   public StringIndexerModel load(final String path) {
      return (StringIndexerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringIndexerModel$.class);
   }

   private StringIndexerModel$() {
   }
}
