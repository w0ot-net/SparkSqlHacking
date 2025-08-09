package org.apache.spark.api.r;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaSparkContext$;
import org.apache.spark.internal.config.package$;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class RUtils$ {
   public static final RUtils$ MODULE$ = new RUtils$();
   private static Option rPackages;

   static {
      rPackages = .MODULE$;
   }

   public Option rPackages() {
      return rPackages;
   }

   public void rPackages_$eq(final Option x$1) {
      rPackages = x$1;
   }

   public Option localSparkRPackagePath() {
      Option sparkHome = scala.sys.package..MODULE$.env().get("SPARK_HOME").orElse(() -> scala.sys.package..MODULE$.props().get("spark.test.home"));
      return sparkHome.map((x$1) -> (new scala.collection.immutable..colon.colon(x$1, new scala.collection.immutable..colon.colon("R", new scala.collection.immutable..colon.colon("lib", scala.collection.immutable.Nil..MODULE$)))).mkString(File.separator));
   }

   public boolean isSparkRInstalled() {
      return this.localSparkRPackagePath().exists((pkgDir) -> BoxesRunTime.boxToBoolean($anonfun$isSparkRInstalled$1(pkgDir)));
   }

   public Seq sparkRPackagePath(final boolean isDriver) {
      Tuple2 var10000;
      if (isDriver) {
         var10000 = new Tuple2(scala.sys.package..MODULE$.props().apply("spark.master"), scala.sys.package..MODULE$.props().apply(package$.MODULE$.SUBMIT_DEPLOY_MODE().key()));
      } else {
         SparkConf sparkConf = SparkEnv$.MODULE$.get().conf();
         var10000 = new Tuple2(sparkConf.get("spark.master"), sparkConf.get(package$.MODULE$.SUBMIT_DEPLOY_MODE()));
      }

      Tuple2 var4 = var10000;
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         String master;
         String deployMode;
         label69: {
            label68: {
               String master = (String)var4._1();
               String deployMode = (String)var4._2();
               Tuple2 var3 = new Tuple2(master, deployMode);
               master = (String)var3._1();
               deployMode = (String)var3._2();
               if (master != null && master.contains("yarn")) {
                  String var11 = "cluster";
                  if (deployMode == null) {
                     if (var11 == null) {
                        break label68;
                     }
                  } else if (deployMode.equals(var11)) {
                     break label68;
                  }
               }

               var17 = false;
               break label69;
            }

            var17 = true;
         }

         boolean isYarnCluster;
         label59: {
            label58: {
               isYarnCluster = var17;
               if (master != null && master.contains("yarn")) {
                  String var13 = "client";
                  if (deployMode == null) {
                     if (var13 == null) {
                        break label58;
                     }
                  } else if (deployMode.equals(var13)) {
                     break label58;
                  }
               }

               var18 = false;
               break label59;
            }

            var18 = true;
         }

         boolean isYarnClient = var18;
         if (isYarnCluster || isYarnClient && !isDriver) {
            String sparkRPkgPath = (new File("sparkr")).getAbsolutePath();
            File rPkgPath = new File("rpkg");
            return rPkgPath.exists() ? new scala.collection.immutable..colon.colon(sparkRPkgPath, new scala.collection.immutable..colon.colon(rPkgPath.getAbsolutePath(), scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon(sparkRPkgPath, scala.collection.immutable.Nil..MODULE$);
         } else {
            String sparkRPkgPath = (String)this.localSparkRPackagePath().getOrElse(() -> {
               throw new SparkException("SPARK_HOME not set. Can't locate SparkR package.");
            });
            return !this.rPackages().isEmpty() ? new scala.collection.immutable..colon.colon(sparkRPkgPath, new scala.collection.immutable..colon.colon((String)this.rPackages().get(), scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon(sparkRPkgPath, scala.collection.immutable.Nil..MODULE$);
         }
      }
   }

   public boolean isRInstalled() {
      boolean var10000;
      try {
         ProcessBuilder builder = new ProcessBuilder(Arrays.asList((Object[])(new String[]{"R", "--version"})));
         var10000 = builder.start().waitFor() == 0;
      } catch (Exception var3) {
         var10000 = false;
      }

      return var10000;
   }

   public boolean isEncryptionEnabled(final JavaSparkContext sc) {
      return BoxesRunTime.unboxToBoolean(JavaSparkContext$.MODULE$.toSparkContext(sc).conf().get(package$.MODULE$.IO_ENCRYPTION_ENABLED()));
   }

   public String[] getJobTags(final JavaSparkContext sc) {
      return (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(sc.getJobTags().toArray()), (x$3) -> (String)x$3, scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSparkRInstalled$1(final String pkgDir) {
      return (new File((new scala.collection.immutable..colon.colon(pkgDir, new scala.collection.immutable..colon.colon("SparkR", scala.collection.immutable.Nil..MODULE$))).mkString(File.separator))).exists();
   }

   private RUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
