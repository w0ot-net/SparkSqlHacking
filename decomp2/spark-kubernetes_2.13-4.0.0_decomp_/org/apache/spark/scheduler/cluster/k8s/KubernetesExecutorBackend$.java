package org.apache.spark.scheduler.cluster.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.deploy.worker.WorkerWatcher;
import org.apache.spark.executor.CoarseGrainedExecutorBackend;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function5;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class KubernetesExecutorBackend$ implements Logging {
   public static final KubernetesExecutorBackend$ MODULE$ = new KubernetesExecutorBackend$();
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
      Function5 createFn = (x0$1, x1$1, x2$1, x3$1, x4$1) -> {
         Tuple5 var6 = new Tuple5(x0$1, x1$1, x2$1, x3$1, x4$1);
         if (var6 != null) {
            RpcEnv rpcEnv = (RpcEnv)var6._1();
            KubernetesExecutorBackend.Arguments arguments = (KubernetesExecutorBackend.Arguments)var6._2();
            SparkEnv env = (SparkEnv)var6._3();
            ResourceProfile resourceProfile = (ResourceProfile)var6._4();
            String execId = (String)var6._5();
            return new CoarseGrainedExecutorBackend(rpcEnv, arguments.driverUrl(), execId, arguments.bindAddress(), arguments.hostname(), arguments.cores(), env, arguments.resourcesFileOpt(), resourceProfile);
         } else {
            throw new MatchError(var6);
         }
      };
      this.run(this.parseArguments(args, .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getCanonicalName()), "$")), createFn);
      System.exit(0);
   }

   public void run(final KubernetesExecutorBackend.Arguments arguments, final Function5 backendCreateFn) {
      org.apache.spark.util.Utils..MODULE$.resetStructuredLogging();
      org.apache.spark.util.Utils..MODULE$.initDaemon(this.log());
      org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().runAsSparkUser((JFunction0.mcV.sp)() -> {
         RpcEnv fetcher;
         CoarseGrainedClusterMessages.SparkAppConfig cfg;
         Seq props;
         String var10000;
         label36: {
            ObjectRef driver;
            label35: {
               scala.Predef..MODULE$.assert(arguments.hostname() != null && (arguments.hostname().indexOf(58) == -1 || arguments.hostname().split(":").length > 2));
               SparkConf executorConf = new SparkConf();
               fetcher = org.apache.spark.rpc.RpcEnv..MODULE$.create("driverPropsFetcher", arguments.bindAddress(), arguments.hostname(), -1, executorConf, new SecurityManager(executorConf, org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$2(), org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$3()), 0, true);
               driver = ObjectRef.create((Object)null);
               int nTries = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)scala.sys.package..MODULE$.env().getOrElse("EXECUTOR_DRIVER_PROPS_FETCHER_MAX_ATTEMPTS", () -> "3")));
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nTries).withFilter((JFunction1.mcZI.sp)(i) -> (RpcEndpointRef)driver.elem == null).foreach((JFunction1.mcVI.sp)(i) -> {
                  try {
                     driver.elem = fetcher.setupEndpointRefByURI(arguments.driverUrl());
                  } catch (Throwable var6) {
                     if (i == nTries - 1) {
                        throw var6;
                     }
                  }

               });
               cfg = (CoarseGrainedClusterMessages.SparkAppConfig)((RpcEndpointRef)driver.elem).askSync(new CoarseGrainedClusterMessages.RetrieveSparkAppConfig(arguments.resourceProfileId()), scala.reflect.ClassTag..MODULE$.apply(CoarseGrainedClusterMessages.SparkAppConfig.class));
               props = (Seq)cfg.sparkProperties().$plus$plus(new scala.collection.immutable..colon.colon(new Tuple2("spark.app.id", arguments.appId()), scala.collection.immutable.Nil..MODULE$));
               String var10 = arguments.executorId();
               switch (var10 == null ? 0 : var10.hashCode()) {
                  case 0:
                     if (var10 == null || "".equals(var10)) {
                        break label35;
                     }
                     break;
                  case 2058803564:
                     if ("EXECID".equals(var10)) {
                        break label35;
                     }
               }

               var10000 = var10;
               break label36;
            }

            var10000 = (String)((RpcEndpointRef)driver.elem).askSync(new GenerateExecID(arguments.podName()), scala.reflect.ClassTag..MODULE$.apply(String.class));
         }

         String execId = var10000;
         fetcher.shutdown();
         SparkConf driverConf = new SparkConf();
         props.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$run$5(check$ifrefutable$1))).foreach((x$1) -> {
            if (x$1 != null) {
               String key = (String)x$1._1();
               String value = (String)x$1._2();
               return org.apache.spark.SparkConf..MODULE$.isExecutorStartupConf(key) ? driverConf.setIfMissing(key, value) : driverConf.set(key, value);
            } else {
               throw new MatchError(x$1);
            }
         });
         org.apache.spark.util.Utils..MODULE$.resetStructuredLogging(driverConf);
         org.apache.spark.internal.Logging..MODULE$.uninitialize();
         cfg.hadoopDelegationCreds().foreach((tokens) -> {
            $anonfun$run$7(driverConf, tokens);
            return BoxedUnit.UNIT;
         });
         driverConf.set(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_ID(), execId);
         SparkEnv env = org.apache.spark.SparkEnv..MODULE$.createExecutorEnv(driverConf, execId, arguments.bindAddress(), arguments.hostname(), arguments.cores(), cfg.ioEncryptionKey(), false);
         CoarseGrainedExecutorBackend backend = (CoarseGrainedExecutorBackend)backendCreateFn.apply(env.rpcEnv(), arguments, env, cfg.resourceProfile(), execId);
         env.rpcEnv().setupEndpoint("Executor", backend);
         arguments.workerUrl().foreach((url) -> {
            RpcEnv x$1;
            RpcEnv var10000 = x$1 = env.rpcEnv();
            AtomicBoolean x$3 = backend.stopping();
            boolean x$4 = org.apache.spark.deploy.worker.WorkerWatcher..MODULE$.$lessinit$greater$default$3();
            return var10000.setupEndpoint("WorkerWatcher", new WorkerWatcher(x$1, url, x$4, x$3));
         });
         env.rpcEnv().awaitTermination();
      });
   }

   public KubernetesExecutorBackend.Arguments parseArguments(final String[] args, final String classNameForEntry) {
      String driverUrl = null;
      String executorId = null;
      String bindAddress = null;
      String hostname = null;
      int cores = 0;
      Option resourcesFileOpt = scala.None..MODULE$;
      String appId = null;
      Option workerUrl = scala.None..MODULE$;
      int resourceProfileId = org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
      String podName = null;
      List argv = scala.Predef..MODULE$.wrapRefArray((Object[])args).toList();

      while(!argv.isEmpty()) {
         boolean var15 = false;
         scala.collection.immutable..colon.colon var16 = null;
         if (argv instanceof scala.collection.immutable..colon.colon) {
            var15 = true;
            var16 = (scala.collection.immutable..colon.colon)argv;
            String var18 = (String)var16.head();
            List var19 = var16.next$access$1();
            if ("--driver-url".equals(var18) && var19 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var20 = (scala.collection.immutable..colon.colon)var19;
               String value = (String)var20.head();
               List tail = var20.next$access$1();
               driverUrl = value;
               argv = tail;
               BoxedUnit var78 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var23 = (String)var16.head();
            List var24 = var16.next$access$1();
            if ("--executor-id".equals(var23) && var24 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var25 = (scala.collection.immutable..colon.colon)var24;
               String value = (String)var25.head();
               List tail = var25.next$access$1();
               executorId = value;
               argv = tail;
               BoxedUnit var77 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var28 = (String)var16.head();
            List var29 = var16.next$access$1();
            if ("--bind-address".equals(var28) && var29 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var30 = (scala.collection.immutable..colon.colon)var29;
               String value = (String)var30.head();
               List tail = var30.next$access$1();
               bindAddress = value;
               argv = tail;
               BoxedUnit var76 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var33 = (String)var16.head();
            List var34 = var16.next$access$1();
            if ("--hostname".equals(var33) && var34 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var35 = (scala.collection.immutable..colon.colon)var34;
               String value = (String)var35.head();
               List tail = var35.next$access$1();
               hostname = org.apache.spark.util.Utils..MODULE$.addBracketsIfNeeded(value);
               argv = tail;
               BoxedUnit var75 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var38 = (String)var16.head();
            List var39 = var16.next$access$1();
            if ("--cores".equals(var38) && var39 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var40 = (scala.collection.immutable..colon.colon)var39;
               String value = (String)var40.head();
               List tail = var40.next$access$1();
               cores = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(value));
               argv = tail;
               BoxedUnit var74 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var43 = (String)var16.head();
            List var44 = var16.next$access$1();
            if ("--resourcesFile".equals(var43) && var44 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var45 = (scala.collection.immutable..colon.colon)var44;
               String value = (String)var45.head();
               List tail = var45.next$access$1();
               resourcesFileOpt = new Some(value);
               argv = tail;
               BoxedUnit var73 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var48 = (String)var16.head();
            List var49 = var16.next$access$1();
            if ("--app-id".equals(var48) && var49 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var50 = (scala.collection.immutable..colon.colon)var49;
               String value = (String)var50.head();
               List tail = var50.next$access$1();
               appId = value;
               argv = tail;
               BoxedUnit var72 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var53 = (String)var16.head();
            List var54 = var16.next$access$1();
            if ("--worker-url".equals(var53) && var54 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var55 = (scala.collection.immutable..colon.colon)var54;
               String value = (String)var55.head();
               List tail = var55.next$access$1();
               workerUrl = new Some(value);
               argv = tail;
               BoxedUnit var71 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var58 = (String)var16.head();
            List var59 = var16.next$access$1();
            if ("--resourceProfileId".equals(var58) && var59 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var60 = (scala.collection.immutable..colon.colon)var59;
               String value = (String)var60.head();
               List tail = var60.next$access$1();
               resourceProfileId = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(value));
               argv = tail;
               BoxedUnit var70 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var15) {
            String var63 = (String)var16.head();
            List var64 = var16.next$access$1();
            if ("--podName".equals(var63) && var64 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var65 = (scala.collection.immutable..colon.colon)var64;
               String value = (String)var65.head();
               List tail = var65.next$access$1();
               podName = value;
               argv = tail;
               BoxedUnit var69 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (scala.collection.immutable.Nil..MODULE$.equals(argv)) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            System.err.println("Unrecognized options: " + argv.mkString(" "));
            this.printUsageAndExit(classNameForEntry);
            BoxedUnit var68 = BoxedUnit.UNIT;
         }
      }

      if (hostname == null) {
         hostname = org.apache.spark.util.Utils..MODULE$.localHostName();
         this.log().info("Executor hostname is not provided, will use '" + hostname + "' to advertise itself");
      }

      if (driverUrl == null || executorId == null || cores <= 0 || appId == null) {
         this.printUsageAndExit(classNameForEntry);
      }

      if (bindAddress == null) {
         bindAddress = hostname;
      }

      return new KubernetesExecutorBackend.Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId, podName);
   }

   private void printUsageAndExit(final String classNameForEntry) {
      System.err.println(.MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |Usage: " + classNameForEntry + " [options]\n      |\n      | Options are:\n      |   --driver-url <driverUrl>\n      |   --executor-id <executorId>\n      |   --bind-address <bindAddress>\n      |   --hostname <hostname>\n      |   --cores <cores>\n      |   --resourcesFile <fileWithJSONResourceInformation>\n      |   --app-id <appid>\n      |   --worker-url <workerUrl>\n      |   --resourceProfileId <id>\n      |   --podName <podName>\n      |")));
      System.exit(1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$5(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$run$7(final SparkConf driverConf$1, final byte[] tokens) {
      org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().addDelegationTokens(tokens, driverConf$1);
   }

   private KubernetesExecutorBackend$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
