package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ElementwiseProduct$ implements DefaultParamsReadable, Serializable {
   public static final ElementwiseProduct$ MODULE$ = new ElementwiseProduct$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public ElementwiseProduct load(final String path) {
      return (ElementwiseProduct)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementwiseProduct$.class);
   }

   private ElementwiseProduct$() {
   }
}
