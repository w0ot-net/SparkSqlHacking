package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class TargetEncoderModel$ implements MLReadable, Serializable {
   public static final TargetEncoderModel$ MODULE$ = new TargetEncoderModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new TargetEncoderModel.TargetEncoderModelReader();
   }

   public TargetEncoderModel load(final String path) {
      return (TargetEncoderModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TargetEncoderModel$.class);
   }

   private TargetEncoderModel$() {
   }
}
