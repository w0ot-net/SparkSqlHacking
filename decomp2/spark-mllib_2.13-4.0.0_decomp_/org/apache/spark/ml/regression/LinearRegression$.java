package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.optim.WeightedLeastSquares$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LinearRegression$ implements DefaultParamsReadable, Serializable {
   public static final LinearRegression$ MODULE$ = new LinearRegression$();
   private static final int MAX_FEATURES_FOR_NORMAL_SOLVER;
   private static final String Auto;
   private static final String Normal;
   private static final String LBFGS;
   private static final String[] supportedSolvers;
   private static final String SquaredError;
   private static final String Huber;
   private static final String[] supportedLosses;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      MAX_FEATURES_FOR_NORMAL_SOLVER = WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES();
      Auto = "auto";
      Normal = "normal";
      LBFGS = "l-bfgs";
      supportedSolvers = (String[])((Object[])(new String[]{MODULE$.Auto(), MODULE$.Normal(), MODULE$.LBFGS()}));
      SquaredError = "squaredError";
      Huber = "huber";
      supportedLosses = (String[])((Object[])(new String[]{MODULE$.SquaredError(), MODULE$.Huber()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public LinearRegression load(final String path) {
      return (LinearRegression)MLReadable.load$(this, path);
   }

   public int MAX_FEATURES_FOR_NORMAL_SOLVER() {
      return MAX_FEATURES_FOR_NORMAL_SOLVER;
   }

   public String Auto() {
      return Auto;
   }

   public String Normal() {
      return Normal;
   }

   public String LBFGS() {
      return LBFGS;
   }

   public String[] supportedSolvers() {
      return supportedSolvers;
   }

   public String SquaredError() {
      return SquaredError;
   }

   public String Huber() {
      return Huber;
   }

   public String[] supportedLosses() {
      return supportedLosses;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearRegression$.class);
   }

   private LinearRegression$() {
   }
}
