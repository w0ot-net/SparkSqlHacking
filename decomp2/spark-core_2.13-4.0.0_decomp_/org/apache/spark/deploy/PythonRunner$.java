package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.api.python.Py4JServer;
import org.apache.spark.api.python.PythonUtils$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.RedirectThread;
import org.apache.spark.util.RedirectThread$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.ArrayOps.;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class PythonRunner$ {
   public static final PythonRunner$ MODULE$ = new PythonRunner$();

   public void main(final String[] args) {
      String[] otherArgs;
      SparkConf sparkConf;
      String pythonExec;
      String formattedPythonFile;
      String[] formattedPyFiles;
      String apiMode;
      boolean var10000;
      label141: {
         label140: {
            String pythonFile = args[0];
            String pyFiles = args[1];
            otherArgs = (String[]).MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), 2, args.length);
            sparkConf = new SparkConf();
            pythonExec = (String)((Option)sparkConf.get((ConfigEntry)package$.MODULE$.PYSPARK_DRIVER_PYTHON())).orElse(() -> (Option)sparkConf.get((ConfigEntry)package$.MODULE$.PYSPARK_PYTHON())).orElse(() -> scala.sys.package..MODULE$.env().get("PYSPARK_DRIVER_PYTHON")).orElse(() -> scala.sys.package..MODULE$.env().get("PYSPARK_PYTHON")).getOrElse(() -> "python3");
            formattedPythonFile = this.formatPath(pythonFile, this.formatPath$default$2());
            formattedPyFiles = this.resolvePyFiles(this.formatPaths(pyFiles, this.formatPaths$default$2()));
            apiMode = ((String)sparkConf.get(package$.MODULE$.SPARK_API_MODE())).toLowerCase(Locale.ROOT);
            String var11 = "classic";
            if (apiMode == null) {
               if (var11 == null) {
                  break label140;
               }
            } else if (apiMode.equals(var11)) {
               break label140;
            }

            var10000 = false;
            break label141;
         }

         var10000 = true;
      }

      boolean isAPIModeClassic;
      label133: {
         label132: {
            isAPIModeClassic = var10000;
            String var13 = "connect";
            if (apiMode == null) {
               if (var13 == null) {
                  break label132;
               }
            } else if (apiMode.equals(var13)) {
               break label132;
            }

            var10000 = false;
            break label133;
         }

         var10000 = true;
      }

      boolean isAPIModeConnect = var10000;
      ObjectRef gatewayServer = ObjectRef.create(scala.None..MODULE$);
      if (sparkConf.getOption("spark.remote").isEmpty() || isAPIModeClassic) {
         gatewayServer.elem = new Some(new Py4JServer(sparkConf));
         Thread thread = new Thread(() -> org.apache.spark.util.Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> ((Py4JServer)((Option)gatewayServer.elem).get()).start()));
         thread.setName("py4j-gateway-init");
         thread.setDaemon(true);
         thread.start();
         thread.join();
      }

      ArrayBuffer pathElements = new ArrayBuffer();
      pathElements.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])formattedPyFiles));
      pathElements.$plus$eq(PythonUtils$.MODULE$.sparkPythonPath());
      pathElements.$plus$eq(scala.sys.package..MODULE$.env().getOrElse("PYTHONPATH", () -> ""));
      String pythonPath = PythonUtils$.MODULE$.mergePythonPaths(pathElements.toSeq());
      ProcessBuilder builder = new ProcessBuilder(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)(new scala.collection.immutable..colon.colon(pythonExec, new scala.collection.immutable..colon.colon(formattedPythonFile, scala.collection.immutable.Nil..MODULE$))).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])otherArgs))).asJava());
      Map env = builder.environment();
      if (sparkConf.getOption("spark.remote").nonEmpty() || isAPIModeConnect) {
         scala.collection.immutable.Seq grouped = scala.Predef..MODULE$.wrapRefArray((Object[])sparkConf.getAll()).toMap(scala..less.colon.less..MODULE$.refl()).grouped(10).toSeq();
         env.put("PYSPARK_REMOTE_INIT_CONF_LEN", Integer.toString(grouped.length()));
         ((IterableOnceOps)grouped.zipWithIndex()).foreach((x0$1) -> {
            if (x0$1 != null) {
               scala.collection.immutable.Map group = (scala.collection.immutable.Map)x0$1._1();
               int idx = x0$1._2$mcI$sp();
               return (String)env.put("PYSPARK_REMOTE_INIT_CONF_" + idx, org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.map2jvalue(group, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      if (isAPIModeClassic) {
         sparkConf.getOption("spark.master").foreach((url) -> (String)env.put("MASTER", url));
      } else {
         sparkConf.getOption("spark.remote").foreach((url) -> (String)env.put("SPARK_REMOTE", url));
      }

      env.put("PYTHONPATH", pythonPath);
      env.put("PYTHONUNBUFFERED", "YES");
      ((Option)gatewayServer.elem).foreach((s) -> (String)env.put("PYSPARK_GATEWAY_PORT", Integer.toString(s.getListeningPort())));
      ((Option)gatewayServer.elem).foreach((s) -> (String)env.put("PYSPARK_GATEWAY_SECRET", s.secret()));
      ((Option)sparkConf.get((ConfigEntry)package$.MODULE$.PYSPARK_PYTHON())).foreach((x$1) -> (String)env.put("PYSPARK_PYTHON", x$1));
      scala.sys.package..MODULE$.env().get("PYTHONHASHSEED").foreach((x$2) -> (String)env.put("PYTHONHASHSEED", x$2));
      if (sparkConf.getOption("spark.yarn.appMasterEnv.OMP_NUM_THREADS").isEmpty() && sparkConf.getOption("spark.kubernetes.driverEnv.OMP_NUM_THREADS").isEmpty()) {
         sparkConf.getOption("spark.driver.cores").foreach((x$3) -> (String)env.put("OMP_NUM_THREADS", x$3));
      }

      builder.redirectErrorStream(true);

      try {
         Process process = builder.start();
         (new RedirectThread(process.getInputStream(), System.out, "redirect output", RedirectThread$.MODULE$.$lessinit$greater$default$4())).start();
         int exitCode = process.waitFor();
         if (exitCode != 0) {
            throw new SparkUserAppException(exitCode);
         }
      } finally {
         ((Option)gatewayServer.elem).foreach((x$4) -> {
            $anonfun$main$17(x$4);
            return BoxedUnit.UNIT;
         });
      }

   }

   public String formatPath(final String path, final boolean testWindows) {
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])org.apache.spark.util.Utils$.MODULE$.nonLocalPaths(path, testWindows)))) {
         throw new IllegalArgumentException("Launching Python applications through spark-submit is currently only supported for local files: " + path);
      } else {
         String var10000;
         label43: {
            URI uri;
            label42: {
               uri = (URI)scala.util.Try..MODULE$.apply(() -> new URI(path)).getOrElse(() -> (new File(path)).toURI());
               String var6 = uri.getScheme();
               switch (var6 == null ? 0 : var6.hashCode()) {
                  case 0:
                     if (var6 == null) {
                        var10000 = path;
                        break label43;
                     }
                     break;
                  case 3143036:
                     if ("file".equals(var6)) {
                        break label42;
                     }
                     break;
                  case 103145323:
                     if ("local".equals(var6)) {
                        break label42;
                     }
               }

               var10000 = null;
               break label43;
            }

            var10000 = uri.getPath();
         }

         String formattedPath = var10000;
         if (formattedPath == null) {
            throw new IllegalArgumentException("Python file path is malformed: " + path);
         } else {
            if (org.apache.spark.util.Utils$.MODULE$.isWindows() && formattedPath.matches("/[a-zA-Z]:/.*")) {
               formattedPath = scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(formattedPath), "/");
            }

            return formattedPath;
         }
      }
   }

   public boolean formatPath$default$2() {
      return false;
   }

   public String[] formatPaths(final String paths, final boolean testWindows) {
      return (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String)scala.Option..MODULE$.apply(paths).getOrElse(() -> "")).split(",")), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$formatPaths$2(x$5)))), (p) -> MODULE$.formatPath(p, testWindows), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public boolean formatPaths$default$2() {
      return false;
   }

   private String[] resolvePyFiles(final String[] pyFiles) {
      LazyRef dest$lzy = new LazyRef();
      return (String[]).MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])pyFiles), (pyFile) -> {
         if (pyFile.endsWith(".py")) {
            File source = new File(pyFile);
            if (source.exists() && source.isFile() && source.canRead()) {
               Files.copy(source.toPath(), (new File(dest$1(dest$lzy), source.getName())).toPath());
               return new Some(dest$1(dest$lzy).getAbsolutePath());
            } else {
               return scala.None..MODULE$;
            }
         } else {
            return new Some(pyFile);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))));
   }

   // $FF: synthetic method
   public static final void $anonfun$main$17(final Py4JServer x$4) {
      x$4.shutdown();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$formatPaths$2(final String x$5) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$5));
   }

   // $FF: synthetic method
   private static final File dest$lzycompute$1(final LazyRef dest$lzy$1) {
      synchronized(dest$lzy$1){}

      File var2;
      try {
         File var10000;
         if (dest$lzy$1.initialized()) {
            var10000 = (File)dest$lzy$1.value();
         } else {
            String x$1 = "localPyFiles";
            String x$2 = org.apache.spark.util.Utils$.MODULE$.createTempDir$default$1();
            var10000 = (File)dest$lzy$1.initialize(org.apache.spark.util.Utils$.MODULE$.createTempDir(x$2, "localPyFiles"));
         }

         var2 = var10000;
      } catch (Throwable var6) {
         throw var6;
      }

      return var2;
   }

   private static final File dest$1(final LazyRef dest$lzy$1) {
      return dest$lzy$1.initialized() ? (File)dest$lzy$1.value() : dest$lzycompute$1(dest$lzy$1);
   }

   private PythonRunner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
