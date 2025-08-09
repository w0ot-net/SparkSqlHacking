package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.tree.GBTRegressorParams$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class GBTRegressor$ implements DefaultParamsReadable, Serializable {
   public static final GBTRegressor$ MODULE$ = new GBTRegressor$();
   private static final String[] supportedLossTypes;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedLossTypes = GBTRegressorParams$.MODULE$.supportedLossTypes();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public final String[] supportedLossTypes() {
      return supportedLossTypes;
   }

   public GBTRegressor load(final String path) {
      return (GBTRegressor)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GBTRegressor$.class);
   }

   private GBTRegressor$() {
   }
}
