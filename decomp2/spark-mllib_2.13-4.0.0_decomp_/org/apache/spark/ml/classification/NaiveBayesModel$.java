package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class NaiveBayesModel$ implements MLReadable, Serializable {
   public static final NaiveBayesModel$ MODULE$ = new NaiveBayesModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new NaiveBayesModel.NaiveBayesModelReader();
   }

   public NaiveBayesModel load(final String path) {
      return (NaiveBayesModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayesModel$.class);
   }

   private NaiveBayesModel$() {
   }
}
