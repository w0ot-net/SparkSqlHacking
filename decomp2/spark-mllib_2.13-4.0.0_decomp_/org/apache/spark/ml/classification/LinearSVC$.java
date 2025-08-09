package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LinearSVC$ implements DefaultParamsReadable, Serializable {
   public static final LinearSVC$ MODULE$ = new LinearSVC$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public LinearSVC load(final String path) {
      return (LinearSVC)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearSVC$.class);
   }

   private LinearSVC$() {
   }
}
