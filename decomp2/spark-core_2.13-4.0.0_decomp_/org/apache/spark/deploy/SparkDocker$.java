package org.apache.spark.deploy;

import java.io.File;
import java.io.FileWriter;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.ThreadUtils$;
import scala.MatchError;
import scala.Tuple3;
import scala.concurrent.Awaitable;
import scala.concurrent.Promise;
import scala.concurrent.Promise.;
import scala.concurrent.duration.package;
import scala.runtime.BoxedUnit;
import scala.sys.process.ProcessBuilder;

public final class SparkDocker$ {
   public static final SparkDocker$ MODULE$ = new SparkDocker$();

   public TestMasterInfo startMaster(final String mountDir) {
      String x$1 = "spark-test-master";
      String x$3 = Docker$.MODULE$.makeRunCmd$default$2();
      ProcessBuilder cmd = Docker$.MODULE$.makeRunCmd("spark-test-master", x$3, mountDir);
      Tuple3 var8 = this.startNode(cmd);
      if (var8 != null) {
         String ip = (String)var8._1();
         DockerId id = (DockerId)var8._2();
         File outFile = (File)var8._3();
         Tuple3 var7 = new Tuple3(ip, id, outFile);
         String ip = (String)var7._1();
         DockerId id = (DockerId)var7._2();
         File outFile = (File)var7._3();
         return new TestMasterInfo(ip, id, outFile);
      } else {
         throw new MatchError(var8);
      }
   }

   public TestWorkerInfo startWorker(final String mountDir, final String masters) {
      ProcessBuilder cmd = Docker$.MODULE$.makeRunCmd("spark-test-worker", masters, mountDir);
      Tuple3 var6 = this.startNode(cmd);
      if (var6 != null) {
         String ip = (String)var6._1();
         DockerId id = (DockerId)var6._2();
         File outFile = (File)var6._3();
         Tuple3 var5 = new Tuple3(ip, id, outFile);
         String ip = (String)var5._1();
         DockerId id = (DockerId)var5._2();
         File outFile = (File)var5._3();
         return new TestWorkerInfo(ip, id, outFile);
      } else {
         throw new MatchError(var6);
      }
   }

   private Tuple3 startNode(final ProcessBuilder dockerCmd) {
      Promise ipPromise = .MODULE$.apply();
      File outFile = File.createTempFile("fault-tolerance-test", "", org.apache.spark.util.Utils$.MODULE$.createTempDir());
      FileWriter outStream = new FileWriter(outFile);
      dockerCmd.run(scala.sys.process.ProcessLogger..MODULE$.apply((line) -> {
         $anonfun$startNode$1(ipPromise, outStream, line);
         return BoxedUnit.UNIT;
      }));
      String ip = (String)ThreadUtils$.MODULE$.awaitResult((Awaitable)ipPromise.future(), (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
      DockerId dockerId = Docker$.MODULE$.getLastProcessId();
      return new Tuple3(ip, dockerId, outFile);
   }

   private static final void findIpAndLog$1(final String line, final Promise ipPromise$1, final FileWriter outStream$1) {
      if (line.startsWith("CONTAINER_IP=")) {
         String ip = line.split("=")[1];
         ipPromise$1.success(ip);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      outStream$1.write(line + "\n");
      outStream$1.flush();
   }

   // $FF: synthetic method
   public static final void $anonfun$startNode$1(final Promise ipPromise$1, final FileWriter outStream$1, final String line) {
      findIpAndLog$1(line, ipPromise$1, outStream$1);
   }

   private SparkDocker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
