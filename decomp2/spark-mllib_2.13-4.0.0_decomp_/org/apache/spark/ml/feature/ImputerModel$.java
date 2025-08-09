package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ImputerModel$ implements MLReadable, Serializable {
   public static final ImputerModel$ MODULE$ = new ImputerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new ImputerModel.ImputerReader();
   }

   public ImputerModel load(final String path) {
      return (ImputerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ImputerModel$.class);
   }

   private ImputerModel$() {
   }
}
