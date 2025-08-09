package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class KMeans$ implements DefaultParamsReadable, Serializable {
   public static final KMeans$ MODULE$ = new KMeans$();
   private static final String RANDOM;
   private static final String K_MEANS_PARALLEL;
   private static final String[] supportedInitModes;
   private static final String EUCLIDEAN;
   private static final String COSINE;
   private static final String ROW;
   private static final String BLOCK;
   private static final String AUTO;
   private static final String[] supportedSolvers;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      RANDOM = "random";
      K_MEANS_PARALLEL = "k-means||";
      supportedInitModes = (String[])((Object[])(new String[]{MODULE$.RANDOM(), MODULE$.K_MEANS_PARALLEL()}));
      EUCLIDEAN = "euclidean";
      COSINE = "cosine";
      ROW = "row";
      BLOCK = "block";
      AUTO = "auto";
      supportedSolvers = (String[])((Object[])(new String[]{MODULE$.ROW(), MODULE$.BLOCK(), MODULE$.AUTO()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public KMeans load(final String path) {
      return (KMeans)MLReadable.load$(this, path);
   }

   public String RANDOM() {
      return RANDOM;
   }

   public String K_MEANS_PARALLEL() {
      return K_MEANS_PARALLEL;
   }

   public String[] supportedInitModes() {
      return supportedInitModes;
   }

   public String EUCLIDEAN() {
      return EUCLIDEAN;
   }

   public String COSINE() {
      return COSINE;
   }

   public String ROW() {
      return ROW;
   }

   public String BLOCK() {
      return BLOCK;
   }

   public String AUTO() {
      return AUTO;
   }

   public String[] supportedSolvers() {
      return supportedSolvers;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KMeans$.class);
   }

   private KMeans$() {
   }
}
