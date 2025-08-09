package org.apache.spark.ml.tuning;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class TrainValidationSplit$ implements MLReadable, Serializable {
   public static final TrainValidationSplit$ MODULE$ = new TrainValidationSplit$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new TrainValidationSplit.TrainValidationSplitReader();
   }

   public TrainValidationSplit load(final String path) {
      return (TrainValidationSplit)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TrainValidationSplit$.class);
   }

   private TrainValidationSplit$() {
   }
}
