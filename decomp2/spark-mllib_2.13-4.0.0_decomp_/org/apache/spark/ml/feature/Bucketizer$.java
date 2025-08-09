package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.SparkException;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Bucketizer$ implements DefaultParamsReadable, Serializable {
   public static final Bucketizer$ MODULE$ = new Bucketizer$();
   private static final String SKIP_INVALID;
   private static final String ERROR_INVALID;
   private static final String KEEP_INVALID;
   private static final String[] supportedHandleInvalids;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      SKIP_INVALID = "skip";
      ERROR_INVALID = "error";
      KEEP_INVALID = "keep";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.SKIP_INVALID(), MODULE$.ERROR_INVALID(), MODULE$.KEEP_INVALID()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String SKIP_INVALID() {
      return SKIP_INVALID;
   }

   public String ERROR_INVALID() {
      return ERROR_INVALID;
   }

   public String KEEP_INVALID() {
      return KEEP_INVALID;
   }

   public String[] supportedHandleInvalids() {
      return supportedHandleInvalids;
   }

   public boolean checkSplits(final double[] splits) {
      if (splits.length < 3) {
         return false;
      } else {
         int i = 0;

         int n;
         for(n = splits.length - 1; i < n; ++i) {
            if (splits[i] >= splits[i + 1] || Double.isNaN(splits[i])) {
               return false;
            }
         }

         return !Double.isNaN(splits[n]);
      }
   }

   public boolean checkSplitsArray(final double[][] splitsArray) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])splitsArray), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$checkSplitsArray$1(x$3)));
   }

   public double binarySearchForBuckets(final double[] splits, final double feature, final boolean keepInvalid) {
      if (Double.isNaN(feature)) {
         if (keepInvalid) {
            return (double)(splits.length - 1);
         } else {
            throw new SparkException("Bucketizer encountered NaN value. To handle or skip NaNs, try setting Bucketizer.handleInvalid.");
         }
      } else if (feature == BoxesRunTime.unboxToDouble(.MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(splits)))) {
         return (double)(splits.length - 2);
      } else {
         int idx = Arrays.binarySearch(splits, feature);
         if (idx >= 0) {
            return (double)idx;
         } else {
            int insertPos = -idx - 1;
            if (insertPos != 0 && insertPos != splits.length) {
               return (double)(insertPos - 1);
            } else {
               throw new SparkException("Feature value " + feature + " out of Bucketizer bounds [" + .MODULE$.head$extension(scala.Predef..MODULE$.doubleArrayOps(splits)) + ", " + .MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(splits)) + "]. Check your features, or loosen the lower/upper bound constraints.");
            }
         }
      }
   }

   public Bucketizer load(final String path) {
      return (Bucketizer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Bucketizer$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkSplitsArray$1(final double[] x$3) {
      return MODULE$.checkSplits(x$3);
   }

   private Bucketizer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
