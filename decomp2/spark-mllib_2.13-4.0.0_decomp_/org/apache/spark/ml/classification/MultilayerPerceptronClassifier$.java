package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MultilayerPerceptronClassifier$ implements DefaultParamsReadable, Serializable {
   public static final MultilayerPerceptronClassifier$ MODULE$ = new MultilayerPerceptronClassifier$();
   private static final String LBFGS;
   private static final String GD;
   private static final String[] supportedSolvers;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      LBFGS = "l-bfgs";
      GD = "gd";
      supportedSolvers = (String[])((Object[])(new String[]{MODULE$.LBFGS(), MODULE$.GD()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String LBFGS() {
      return LBFGS;
   }

   public String GD() {
      return GD;
   }

   public String[] supportedSolvers() {
      return supportedSolvers;
   }

   public MultilayerPerceptronClassifier load(final String path) {
      return (MultilayerPerceptronClassifier)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultilayerPerceptronClassifier$.class);
   }

   private MultilayerPerceptronClassifier$() {
   }
}
