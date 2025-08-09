package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class OneHotEncoder$ implements DefaultParamsReadable, Serializable {
   public static final OneHotEncoder$ MODULE$ = new OneHotEncoder$();
   private static final String KEEP_INVALID;
   private static final String ERROR_INVALID;
   private static final String[] supportedHandleInvalids;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      KEEP_INVALID = "keep";
      ERROR_INVALID = "error";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.KEEP_INVALID(), MODULE$.ERROR_INVALID()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String KEEP_INVALID() {
      return KEEP_INVALID;
   }

   public String ERROR_INVALID() {
      return ERROR_INVALID;
   }

   public String[] supportedHandleInvalids() {
      return supportedHandleInvalids;
   }

   public OneHotEncoder load(final String path) {
      return (OneHotEncoder)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneHotEncoder$.class);
   }

   private OneHotEncoder$() {
   }
}
