package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.hadoop.fs.Path;
import scala.Option;
import scala.collection.StringOps.;

public final class SingleEventLogFileWriter$ {
   public static final SingleEventLogFileWriter$ MODULE$ = new SingleEventLogFileWriter$();

   public String getLogPath(final URI logBaseDir, final String appId, final Option appAttemptId, final Option compressionCodecName) {
      String codec = (String)compressionCodecName.map((x$6) -> "." + x$6).getOrElse(() -> "");
      String var10000 = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString((new Path(logBaseDir)).toString()), "/");
      return var10000 + "/" + EventLogFileWriter$.MODULE$.nameForAppAndAttempt(appId, appAttemptId) + codec;
   }

   public Option getLogPath$default$4() {
      return scala.None..MODULE$;
   }

   private SingleEventLogFileWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
