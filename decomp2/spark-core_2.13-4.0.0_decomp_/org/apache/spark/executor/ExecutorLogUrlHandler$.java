package org.apache.spark.executor;

import scala.collection.StringOps.;
import scala.util.matching.Regex;

public final class ExecutorLogUrlHandler$ {
   public static final ExecutorLogUrlHandler$ MODULE$ = new ExecutorLogUrlHandler$();
   private static final Regex CUSTOM_URL_PATTERN_REGEX;

   static {
      CUSTOM_URL_PATTERN_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\{\\{([A-Za-z0-9_\\-]+)\\}\\}"));
   }

   public Regex CUSTOM_URL_PATTERN_REGEX() {
      return CUSTOM_URL_PATTERN_REGEX;
   }

   private ExecutorLogUrlHandler$() {
   }
}
