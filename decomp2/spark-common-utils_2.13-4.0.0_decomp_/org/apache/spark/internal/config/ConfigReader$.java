package org.apache.spark.internal.config;

import scala.collection.StringOps.;
import scala.util.matching.Regex;

public final class ConfigReader$ {
   public static final ConfigReader$ MODULE$ = new ConfigReader$();
   private static final Regex org$apache$spark$internal$config$ConfigReader$$REF_RE;

   static {
      org$apache$spark$internal$config$ConfigReader$$REF_RE = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\$\\{(?:(\\w+?):)?(\\S+?)\\}"));
   }

   public Regex org$apache$spark$internal$config$ConfigReader$$REF_RE() {
      return org$apache$spark$internal$config$ConfigReader$$REF_RE;
   }

   private ConfigReader$() {
   }
}
