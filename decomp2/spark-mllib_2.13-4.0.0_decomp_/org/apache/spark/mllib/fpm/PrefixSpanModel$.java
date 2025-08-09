package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import scala.runtime.ModuleSerializationProxy;

public final class PrefixSpanModel$ implements Loader, Serializable {
   public static final PrefixSpanModel$ MODULE$ = new PrefixSpanModel$();

   public PrefixSpanModel load(final SparkContext sc, final String path) {
      return PrefixSpanModel.SaveLoadV1_0$.MODULE$.load(sc, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PrefixSpanModel$.class);
   }

   private PrefixSpanModel$() {
   }
}
