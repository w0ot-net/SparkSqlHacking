package org.apache.spark.ml;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class PipelineModel$ implements MLReadable, Serializable {
   public static final PipelineModel$ MODULE$ = new PipelineModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new PipelineModel.PipelineModelReader();
   }

   public PipelineModel load(final String path) {
      return (PipelineModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PipelineModel$.class);
   }

   private PipelineModel$() {
   }
}
