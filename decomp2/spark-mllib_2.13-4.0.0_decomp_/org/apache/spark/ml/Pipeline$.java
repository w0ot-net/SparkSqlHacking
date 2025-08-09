package org.apache.spark.ml;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class Pipeline$ implements MLReadable, Serializable {
   public static final Pipeline$ MODULE$ = new Pipeline$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new Pipeline.PipelineReader();
   }

   public Pipeline load(final String path) {
      return (Pipeline)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Pipeline$.class);
   }

   private Pipeline$() {
   }
}
