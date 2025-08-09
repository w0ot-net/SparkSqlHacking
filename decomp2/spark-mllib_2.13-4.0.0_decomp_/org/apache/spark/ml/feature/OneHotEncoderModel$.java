package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class OneHotEncoderModel$ implements MLReadable, Serializable {
   public static final OneHotEncoderModel$ MODULE$ = new OneHotEncoderModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new OneHotEncoderModel.OneHotEncoderModelReader();
   }

   public OneHotEncoderModel load(final String path) {
      return (OneHotEncoderModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneHotEncoderModel$.class);
   }

   private OneHotEncoderModel$() {
   }
}
