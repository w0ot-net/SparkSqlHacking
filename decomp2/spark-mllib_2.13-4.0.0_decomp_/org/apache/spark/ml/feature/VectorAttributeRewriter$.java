package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VectorAttributeRewriter$ implements MLReadable, Serializable {
   public static final VectorAttributeRewriter$ MODULE$ = new VectorAttributeRewriter$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new VectorAttributeRewriter.VectorAttributeRewriterReader();
   }

   public VectorAttributeRewriter load(final String path) {
      return (VectorAttributeRewriter)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorAttributeRewriter$.class);
   }

   private VectorAttributeRewriter$() {
   }
}
