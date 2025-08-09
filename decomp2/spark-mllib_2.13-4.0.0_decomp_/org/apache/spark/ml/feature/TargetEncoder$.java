package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class TargetEncoder$ implements DefaultParamsReadable, Serializable {
   public static final TargetEncoder$ MODULE$ = new TargetEncoder$();
   private static final String KEEP_INVALID;
   private static final String ERROR_INVALID;
   private static final String[] supportedHandleInvalids;
   private static final String TARGET_BINARY;
   private static final String TARGET_CONTINUOUS;
   private static final String[] supportedTargetTypes;
   private static final double UNSEEN_CATEGORY;
   private static final double NULL_CATEGORY;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      KEEP_INVALID = "keep";
      ERROR_INVALID = "error";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.KEEP_INVALID(), MODULE$.ERROR_INVALID()}));
      TARGET_BINARY = "binary";
      TARGET_CONTINUOUS = "continuous";
      supportedTargetTypes = (String[])((Object[])(new String[]{MODULE$.TARGET_BINARY(), MODULE$.TARGET_CONTINUOUS()}));
      UNSEEN_CATEGORY = (double)Integer.MAX_VALUE;
      NULL_CATEGORY = (double)-1.0F;
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

   public String TARGET_BINARY() {
      return TARGET_BINARY;
   }

   public String TARGET_CONTINUOUS() {
      return TARGET_CONTINUOUS;
   }

   public String[] supportedTargetTypes() {
      return supportedTargetTypes;
   }

   public double UNSEEN_CATEGORY() {
      return UNSEEN_CATEGORY;
   }

   public double NULL_CATEGORY() {
      return NULL_CATEGORY;
   }

   public TargetEncoder load(final String path) {
      return (TargetEncoder)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TargetEncoder$.class);
   }

   private TargetEncoder$() {
   }
}
