package org.apache.spark.mllib.feature;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import scala.runtime.ModuleSerializationProxy;

public final class ChiSqSelectorModel$ implements Loader, Serializable {
   public static final ChiSqSelectorModel$ MODULE$ = new ChiSqSelectorModel$();

   public ChiSqSelectorModel load(final SparkContext sc, final String path) {
      return ChiSqSelectorModel.SaveLoadV1_0$.MODULE$.load(sc, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChiSqSelectorModel$.class);
   }

   private ChiSqSelectorModel$() {
   }
}
