package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ChiSqSelectorModel$ implements MLReadable, Serializable {
   public static final ChiSqSelectorModel$ MODULE$ = new ChiSqSelectorModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new ChiSqSelectorModel.ChiSqSelectorModelReader();
   }

   public ChiSqSelectorModel load(final String path) {
      return (ChiSqSelectorModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChiSqSelectorModel$.class);
   }

   private ChiSqSelectorModel$() {
   }
}
