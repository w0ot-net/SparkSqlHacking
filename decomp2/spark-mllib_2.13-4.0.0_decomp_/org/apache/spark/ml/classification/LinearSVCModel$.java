package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LinearSVCModel$ implements MLReadable, Serializable {
   public static final LinearSVCModel$ MODULE$ = new LinearSVCModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new LinearSVCModel.LinearSVCReader();
   }

   public LinearSVCModel load(final String path) {
      return (LinearSVCModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearSVCModel$.class);
   }

   private LinearSVCModel$() {
   }
}
