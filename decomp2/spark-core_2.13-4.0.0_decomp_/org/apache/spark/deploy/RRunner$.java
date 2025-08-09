package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.api.r.RAuthHelper;
import org.apache.spark.api.r.RBackend;
import org.apache.spark.api.r.RUtils$;
import org.apache.spark.internal.config.R$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.RedirectThread;
import org.apache.spark.util.RedirectThread$;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.VolatileIntRef;
import scala.runtime.VolatileObjectRef;

public final class RRunner$ {
   public static final RRunner$ MODULE$ = new RRunner$();

   public void main(final String[] args) {
      String rFile;
      String[] otherArgs;
      int backendTimeout;
      ObjectRef cmd;
      label66: {
         rFile = PythonRunner$.MODULE$.formatPath(args[0], PythonRunner$.MODULE$.formatPath$default$2());
         otherArgs = (String[]).MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), 1, args.length);
         backendTimeout = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)scala.sys.package..MODULE$.env().getOrElse("SPARKR_BACKEND_TIMEOUT", () -> "120")));
         cmd = ObjectRef.create((String)scala.sys.package..MODULE$.props().getOrElse(R$.MODULE$.SPARKR_COMMAND().key(), () -> (String)R$.MODULE$.SPARKR_COMMAND().defaultValue().get()));
         cmd.elem = (String)scala.sys.package..MODULE$.props().getOrElse(R$.MODULE$.R_COMMAND().key(), () -> (String)cmd.elem);
         Object var10000 = scala.sys.package..MODULE$.props().getOrElse(package$.MODULE$.SUBMIT_DEPLOY_MODE().key(), () -> "client");
         String var7 = "client";
         if (var10000 == null) {
            if (var7 != null) {
               break label66;
            }
         } else if (!var10000.equals(var7)) {
            break label66;
         }

         cmd.elem = (String)scala.sys.package..MODULE$.props().getOrElse("spark.r.driver.command", () -> (String)cmd.elem);
      }

      String rCommand = (String)cmd.elem;
      String backendConnectionTimeout = (String)scala.sys.package..MODULE$.props().getOrElse(R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT().key(), () -> R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT().defaultValue().get().toString());
      File rF = new File(rFile);
      String rFileNormalized = !rF.exists() ? (new Path(rFile)).getName() : rFile;
      RBackend sparkRBackend = new RBackend();
      VolatileIntRef sparkRBackendPort = VolatileIntRef.create(0);
      VolatileObjectRef sparkRBackendSecret = VolatileObjectRef.create((Object)null);
      Semaphore initialized = new Semaphore(0);
      Thread sparkRBackendThread = new Thread(sparkRBackend, sparkRBackendPort, sparkRBackendSecret, initialized) {
         private final RBackend sparkRBackend$1;
         private final VolatileIntRef sparkRBackendPort$1;
         private final VolatileObjectRef sparkRBackendSecret$1;
         private final Semaphore initialized$1;

         public void run() {
            Tuple2 var3 = this.sparkRBackend$1.init();
            if (var3 != null) {
               int port = var3._1$mcI$sp();
               RAuthHelper authHelper = (RAuthHelper)var3._2();
               Tuple2 var2 = new Tuple2(BoxesRunTime.boxToInteger(port), authHelper);
               int port = var2._1$mcI$sp();
               RAuthHelper authHelper = (RAuthHelper)var2._2();
               this.sparkRBackendPort$1.elem = port;
               this.sparkRBackendSecret$1.elem = authHelper.secret();
               this.initialized$1.release();
               this.sparkRBackend$1.run();
            } else {
               throw new MatchError(var3);
            }
         }

         public {
            this.sparkRBackend$1 = sparkRBackend$1;
            this.sparkRBackendPort$1 = sparkRBackendPort$1;
            this.sparkRBackendSecret$1 = sparkRBackendSecret$1;
            this.initialized$1 = initialized$1;
         }
      };
      sparkRBackendThread.start();
      if (initialized.tryAcquire((long)backendTimeout, TimeUnit.SECONDS)) {
         int var25;
         try {
            ProcessBuilder builder = new ProcessBuilder(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)(new scala.collection.immutable..colon.colon(rCommand, new scala.collection.immutable..colon.colon(rFileNormalized, scala.collection.immutable.Nil..MODULE$))).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])otherArgs))).asJava());
            Map env = builder.environment();
            env.put("EXISTING_SPARKR_BACKEND_PORT", Integer.toString(sparkRBackendPort.elem));
            env.put("SPARKR_BACKEND_CONNECTION_TIMEOUT", backendConnectionTimeout);
            scala.collection.immutable.Seq rPackageDir = RUtils$.MODULE$.sparkRPackagePath(true);
            env.put("SPARKR_PACKAGE_DIR", rPackageDir.mkString(","));
            env.put("R_PROFILE_USER", (new scala.collection.immutable..colon.colon((String)rPackageDir.apply(0), new scala.collection.immutable..colon.colon("SparkR", new scala.collection.immutable..colon.colon("profile", new scala.collection.immutable..colon.colon("general.R", scala.collection.immutable.Nil..MODULE$))))).mkString(File.separator));
            env.put("SPARKR_BACKEND_AUTH_SECRET", (String)sparkRBackendSecret.elem);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            (new RedirectThread(process.getInputStream(), System.out, "redirect R output", RedirectThread$.MODULE$.$lessinit$greater$default$4())).start();
            var25 = process.waitFor();
         } finally {
            sparkRBackend.close();
         }

         int returnCode = var25;
         if (returnCode != 0) {
            throw new SparkUserAppException(returnCode);
         }
      } else {
         String errorMessage = "SparkR backend did not initialize in " + backendTimeout + " seconds";
         System.err.println(errorMessage);
         throw new SparkException(errorMessage);
      }
   }

   private RRunner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
