package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VectorIndexer$ implements DefaultParamsReadable, Serializable {
   public static final VectorIndexer$ MODULE$ = new VectorIndexer$();
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

   public VectorIndexer load(final String path) {
      return (VectorIndexer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorIndexer$.class);
   }

   private VectorIndexer$() {
   }
}
