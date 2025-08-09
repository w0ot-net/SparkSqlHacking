package org.apache.spark.executor;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkConf$;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.deploy.worker.WorkerWatcher;
import org.apache.spark.deploy.worker.WorkerWatcher$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function4;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class CoarseGrainedExecutorBackend$ implements Logging {
   public static final CoarseGrainedExecutorBackend$ MODULE$ = new CoarseGrainedExecutorBackend$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void main(final String[] args) {
      Function4 createFn = (x0$1, x1$1, x2$1, x3$1) -> {
         Tuple4 var5 = new Tuple4(x0$1, x1$1, x2$1, x3$1);
         if (var5 != null) {
            RpcEnv rpcEnv = (RpcEnv)var5._1();
            CoarseGrainedExecutorBackend.Arguments arguments = (CoarseGrainedExecutorBackend.Arguments)var5._2();
            SparkEnv env = (SparkEnv)var5._3();
            ResourceProfile resourceProfile = (ResourceProfile)var5._4();
            return new CoarseGrainedExecutorBackend(rpcEnv, arguments.driverUrl(), arguments.executorId(), arguments.bindAddress(), arguments.hostname(), arguments.cores(), env, arguments.resourcesFileOpt(), resourceProfile);
         } else {
            throw new MatchError(var5);
         }
      };
      this.run(this.parseArguments(args, .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getCanonicalName()), "$")), createFn);
      System.exit(0);
   }

   public void run(final CoarseGrainedExecutorBackend.Arguments arguments, final Function4 backendCreateFn) {
      Utils$.MODULE$.resetStructuredLogging();
      Utils$.MODULE$.initDaemon(this.log());
      SparkHadoopUtil$.MODULE$.get().runAsSparkUser((JFunction0.mcV.sp)() -> {
         Utils$.MODULE$.checkHost(arguments.hostname());
         SparkConf executorConf = new SparkConf();
         RpcEnv fetcher = RpcEnv$.MODULE$.create("driverPropsFetcher", arguments.bindAddress(), arguments.hostname(), -1, executorConf, new SecurityManager(executorConf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3()), 0, true);
         ObjectRef driver = ObjectRef.create((Object)null);
         int nTries = 3;
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nTries).withFilter((JFunction1.mcZI.sp)(i) -> (RpcEndpointRef)driver.elem == null).foreach((JFunction1.mcVI.sp)(i) -> {
            try {
               driver.elem = fetcher.setupEndpointRefByURI(arguments.driverUrl());
            } catch (Throwable var6) {
               if (i == nTries - 1) {
                  throw var6;
               }
            }

         });
         CoarseGrainedClusterMessages.SparkAppConfig cfg = (CoarseGrainedClusterMessages.SparkAppConfig)((RpcEndpointRef)driver.elem).askSync(new CoarseGrainedClusterMessages.RetrieveSparkAppConfig(arguments.resourceProfileId()), scala.reflect.ClassTag..MODULE$.apply(CoarseGrainedClusterMessages.SparkAppConfig.class));
         Seq props = (Seq)cfg.sparkProperties().$plus$plus(new scala.collection.immutable..colon.colon(new Tuple2("spark.app.id", arguments.appId()), scala.collection.immutable.Nil..MODULE$));
         fetcher.shutdown();
         SparkConf driverConf = new SparkConf();
         props.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$run$10(check$ifrefutable$1))).foreach((x$4) -> {
            if (x$4 != null) {
               String key = (String)x$4._1();
               String value = (String)x$4._2();
               return SparkConf$.MODULE$.isExecutorStartupConf(key) ? driverConf.setIfMissing(key, value) : driverConf.set(key, value);
            } else {
               throw new MatchError(x$4);
            }
         });
         Utils$.MODULE$.resetStructuredLogging(driverConf);
         org.apache.spark.internal.Logging..MODULE$.uninitialize();
         cfg.hadoopDelegationCreds().foreach((tokens) -> {
            $anonfun$run$12(driverConf, tokens);
            return BoxedUnit.UNIT;
         });
         driverConf.set((OptionalConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ID(), (Object)arguments.executorId());
         cfg.logLevel().foreach((logLevel) -> {
            $anonfun$run$13(logLevel);
            return BoxedUnit.UNIT;
         });
         if (cfg.resourceProfile().id() != ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()) {
            cfg.resourceProfile().executorResources().foreach((x0$1) -> {
               $anonfun$run$14(driverConf, x0$1);
               return BoxedUnit.UNIT;
            });
         }

         SparkEnv env = SparkEnv$.MODULE$.createExecutorEnv(driverConf, arguments.executorId(), arguments.bindAddress(), arguments.hostname(), arguments.cores(), cfg.ioEncryptionKey(), false);
         Option appAttemptId = (Option)env.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.APP_ATTEMPT_ID());
         appAttemptId.foreach((attemptId) -> {
            $anonfun$run$19(env, attemptId);
            return BoxedUnit.UNIT;
         });
         CoarseGrainedExecutorBackend backend = (CoarseGrainedExecutorBackend)backendCreateFn.apply(env.rpcEnv(), arguments, env, cfg.resourceProfile());
         env.rpcEnv().setupEndpoint("Executor", backend);
         arguments.workerUrl().foreach((url) -> {
            RpcEnv x$1;
            RpcEnv var10000 = x$1 = env.rpcEnv();
            AtomicBoolean x$3 = backend.stopping();
            boolean x$4 = WorkerWatcher$.MODULE$.$lessinit$greater$default$3();
            return var10000.setupEndpoint("WorkerWatcher", new WorkerWatcher(x$1, url, x$4, x$3));
         });
         env.rpcEnv().awaitTermination();
      });
   }

   public CoarseGrainedExecutorBackend.Arguments parseArguments(final String[] args, final String classNameForEntry) {
      String driverUrl = null;
      String executorId = null;
      String bindAddress = null;
      String hostname = null;
      int cores = 0;
      Option resourcesFileOpt = scala.None..MODULE$;
      String appId = null;
      Option workerUrl = scala.None..MODULE$;
      int resourceProfileId = ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
      List argv = scala.Predef..MODULE$.wrapRefArray((Object[])args).toList();

      while(!argv.isEmpty()) {
         boolean var14 = false;
         scala.collection.immutable..colon.colon var15 = null;
         if (argv instanceof scala.collection.immutable..colon.colon) {
            var14 = true;
            var15 = (scala.collection.immutable..colon.colon)argv;
            String var17 = (String)var15.head();
            List var18 = var15.next$access$1();
            if ("--driver-url".equals(var17) && var18 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var19 = (scala.collection.immutable..colon.colon)var18;
               String value = (String)var19.head();
               List tail = var19.next$access$1();
               driverUrl = value;
               argv = tail;
               BoxedUnit var71 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var22 = (String)var15.head();
            List var23 = var15.next$access$1();
            if ("--executor-id".equals(var22) && var23 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var24 = (scala.collection.immutable..colon.colon)var23;
               String value = (String)var24.head();
               List tail = var24.next$access$1();
               executorId = value;
               argv = tail;
               BoxedUnit var70 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var27 = (String)var15.head();
            List var28 = var15.next$access$1();
            if ("--bind-address".equals(var27) && var28 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var29 = (scala.collection.immutable..colon.colon)var28;
               String value = (String)var29.head();
               List tail = var29.next$access$1();
               bindAddress = value;
               argv = tail;
               BoxedUnit var69 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var32 = (String)var15.head();
            List var33 = var15.next$access$1();
            if ("--hostname".equals(var32) && var33 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var34 = (scala.collection.immutable..colon.colon)var33;
               String value = (String)var34.head();
               List tail = var34.next$access$1();
               hostname = value;
               argv = tail;
               BoxedUnit var68 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var37 = (String)var15.head();
            List var38 = var15.next$access$1();
            if ("--cores".equals(var37) && var38 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var39 = (scala.collection.immutable..colon.colon)var38;
               String value = (String)var39.head();
               List tail = var39.next$access$1();
               cores = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(value));
               argv = tail;
               BoxedUnit var67 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var42 = (String)var15.head();
            List var43 = var15.next$access$1();
            if ("--resourcesFile".equals(var42) && var43 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var44 = (scala.collection.immutable..colon.colon)var43;
               String value = (String)var44.head();
               List tail = var44.next$access$1();
               resourcesFileOpt = new Some(value);
               argv = tail;
               BoxedUnit var66 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var47 = (String)var15.head();
            List var48 = var15.next$access$1();
            if ("--app-id".equals(var47) && var48 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var49 = (scala.collection.immutable..colon.colon)var48;
               String value = (String)var49.head();
               List tail = var49.next$access$1();
               appId = value;
               argv = tail;
               BoxedUnit var65 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var52 = (String)var15.head();
            List var53 = var15.next$access$1();
            if ("--worker-url".equals(var52) && var53 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var54 = (scala.collection.immutable..colon.colon)var53;
               String value = (String)var54.head();
               List tail = var54.next$access$1();
               workerUrl = new Some(value);
               argv = tail;
               BoxedUnit var64 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var14) {
            String var57 = (String)var15.head();
            List var58 = var15.next$access$1();
            if ("--resourceProfileId".equals(var57) && var58 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var59 = (scala.collection.immutable..colon.colon)var58;
               String value = (String)var59.head();
               List tail = var59.next$access$1();
               resourceProfileId = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(value));
               argv = tail;
               BoxedUnit var63 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (scala.collection.immutable.Nil..MODULE$.equals(argv)) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            System.err.println("Unrecognized options: " + argv.mkString(" "));
            this.printUsageAndExit(classNameForEntry);
            BoxedUnit var62 = BoxedUnit.UNIT;
         }
      }

      if (hostname == null) {
         hostname = Utils$.MODULE$.localHostName();
         this.log().info("Executor hostname is not provided, will use '" + hostname + "' to advertise itself");
      }

      if (driverUrl == null || executorId == null || cores <= 0 || appId == null) {
         this.printUsageAndExit(classNameForEntry);
      }

      if (bindAddress == null) {
         bindAddress = hostname;
      }

      return new CoarseGrainedExecutorBackend.Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId);
   }

   private void printUsageAndExit(final String classNameForEntry) {
      System.err.println(.MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |Usage: " + classNameForEntry + " [options]\n      |\n      | Options are:\n      |   --driver-url <driverUrl>\n      |   --executor-id <executorId>\n      |   --bind-address <bindAddress>\n      |   --hostname <hostname>\n      |   --cores <cores>\n      |   --resourcesFile <fileWithJSONResourceInformation>\n      |   --app-id <appid>\n      |   --worker-url <workerUrl>\n      |   --resourceProfileId <id>\n      |")));
      System.exit(1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$10(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$run$12(final SparkConf driverConf$1, final byte[] tokens) {
      SparkHadoopUtil$.MODULE$.get().addDelegationTokens(tokens, driverConf$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$run$13(final String logLevel) {
      Utils$.MODULE$.setLogLevelIfNeeded(logLevel);
   }

   // $FF: synthetic method
   public static final void $anonfun$run$14(final SparkConf driverConf$1, final Tuple2 x0$1) {
      ExecutorResourceRequest request;
      label68: {
         if (x0$1 != null) {
            String var4 = (String)x0$1._1();
            request = (ExecutorResourceRequest)x0$1._2();
            String var10000 = ResourceProfile$.MODULE$.OFFHEAP_MEM();
            if (var10000 == null) {
               if (var4 == null) {
                  break label68;
               }
            } else if (var10000.equals(var4)) {
               break label68;
            }
         }

         ExecutorResourceRequest request;
         label69: {
            if (x0$1 != null) {
               String var7 = (String)x0$1._1();
               request = (ExecutorResourceRequest)x0$1._2();
               String var16 = ResourceProfile$.MODULE$.MEMORY();
               if (var16 == null) {
                  if (var7 == null) {
                     break label69;
                  }
               } else if (var16.equals(var7)) {
                  break label69;
               }
            }

            ExecutorResourceRequest request;
            label70: {
               if (x0$1 != null) {
                  String var10 = (String)x0$1._1();
                  request = (ExecutorResourceRequest)x0$1._2();
                  String var17 = ResourceProfile$.MODULE$.OVERHEAD_MEM();
                  if (var17 == null) {
                     if (var10 == null) {
                        break label70;
                     }
                  } else if (var17.equals(var10)) {
                     break label70;
                  }
               }

               ExecutorResourceRequest request;
               label43: {
                  if (x0$1 != null) {
                     String var13 = (String)x0$1._1();
                     request = (ExecutorResourceRequest)x0$1._2();
                     String var18 = ResourceProfile$.MODULE$.CORES();
                     if (var18 == null) {
                        if (var13 == null) {
                           break label43;
                        }
                     } else if (var18.equals(var13)) {
                        break label43;
                     }
                  }

                  BoxedUnit var19 = BoxedUnit.UNIT;
                  return;
               }

               driverConf$1.set(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES().key(), Long.toString(request.amount()));
               MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Set executor cores to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_CORES..MODULE$, request)})))));
               BoxedUnit var20 = BoxedUnit.UNIT;
               return;
            }

            driverConf$1.set(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY_OVERHEAD().key(), Long.toString(request.amount()) + "m");
            MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Set executor memory_overhead to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_MEMORY_OVERHEAD_SIZE..MODULE$, request)}))))));
            BoxedUnit var21 = BoxedUnit.UNIT;
            return;
         }

         driverConf$1.set(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY().key(), Long.toString(request.amount()) + "m");
         MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Set executor memory to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_MEMORY_SIZE..MODULE$, request)})))));
         BoxedUnit var22 = BoxedUnit.UNIT;
         return;
      }

      driverConf$1.set(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_SIZE().key(), Long.toString(request.amount()) + "m");
      MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Set executor off-heap memory to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_MEMORY_OFFHEAP..MODULE$, request)}))))));
      BoxedUnit var23 = BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final void $anonfun$run$19(final SparkEnv env$1, final String attemptId) {
      env$1.blockManager().blockStoreClient().setAppAttemptId(attemptId);
   }

   private CoarseGrainedExecutorBackend$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
