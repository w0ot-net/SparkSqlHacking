package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RFormulaModel$ implements MLReadable, Serializable {
   public static final RFormulaModel$ MODULE$ = new RFormulaModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new RFormulaModel.RFormulaModelReader();
   }

   public RFormulaModel load(final String path) {
      return (RFormulaModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RFormulaModel$.class);
   }

   private RFormulaModel$() {
   }
}
