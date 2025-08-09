package org.apache.spark.launcher;

import scala.Predef.;

public final class YarnCommandBuilderUtils$ {
   public static final YarnCommandBuilderUtils$ MODULE$ = new YarnCommandBuilderUtils$();

   public String quoteForBatchScript(final String arg) {
      return CommandBuilderUtils.quoteForBatchScript(arg);
   }

   public String findJarsDir(final String sparkHome) {
      String scalaVer = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.refArrayOps((Object[])scala.util.Properties..MODULE$.versionNumberString().split("\\.")), 2)).mkString(".");
      return CommandBuilderUtils.findJarsDir(sparkHome, scalaVer, true);
   }

   private YarnCommandBuilderUtils$() {
   }
}
