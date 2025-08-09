package org.apache.spark;

import java.io.File;
import java.lang.invoke.SerializedLambda;

public final class SparkFiles$ {
   public static final SparkFiles$ MODULE$ = new SparkFiles$();

   public String get(final String filename) {
      String var10000;
      label17: {
         label16: {
            String jobArtifactUUID = (String)JobArtifactSet$.MODULE$.getCurrentJobArtifactState().map((x$1) -> x$1.uuid()).getOrElse(() -> "default");
            String var4 = "default";
            if (jobArtifactUUID == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (jobArtifactUUID.equals(var4)) {
               break label16;
            }

            var10000 = jobArtifactUUID + "/" + filename;
            break label17;
         }

         var10000 = filename;
      }

      String withUuid = var10000;
      return (new File(this.getRootDirectory(), withUuid)).getAbsolutePath();
   }

   public String getRootDirectory() {
      return (String)SparkEnv$.MODULE$.get().driverTmpDir().getOrElse(() -> ".");
   }

   private SparkFiles$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
