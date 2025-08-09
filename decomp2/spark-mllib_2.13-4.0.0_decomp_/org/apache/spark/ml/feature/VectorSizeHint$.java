package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VectorSizeHint$ implements DefaultParamsReadable, Serializable {
   public static final VectorSizeHint$ MODULE$ = new VectorSizeHint$();
   private static final String OPTIMISTIC_INVALID;
   private static final String ERROR_INVALID;
   private static final String SKIP_INVALID;
   private static final String[] supportedHandleInvalids;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      OPTIMISTIC_INVALID = "optimistic";
      ERROR_INVALID = "error";
      SKIP_INVALID = "skip";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.OPTIMISTIC_INVALID(), MODULE$.ERROR_INVALID(), MODULE$.SKIP_INVALID()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String OPTIMISTIC_INVALID() {
      return OPTIMISTIC_INVALID;
   }

   public String ERROR_INVALID() {
      return ERROR_INVALID;
   }

   public String SKIP_INVALID() {
      return SKIP_INVALID;
   }

   public String[] supportedHandleInvalids() {
      return supportedHandleInvalids;
   }

   public VectorSizeHint load(final String path) {
      return (VectorSizeHint)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorSizeHint$.class);
   }

   private VectorSizeHint$() {
   }
}
