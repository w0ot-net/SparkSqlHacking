package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class IndexToString$ implements DefaultParamsReadable, Serializable {
   public static final IndexToString$ MODULE$ = new IndexToString$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public IndexToString load(final String path) {
      return (IndexToString)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexToString$.class);
   }

   private IndexToString$() {
   }
}
