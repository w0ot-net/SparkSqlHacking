package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.master.RecoveryState$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Deploy$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.App;
import scala.Enumeration;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.concurrent.Awaitable;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.package;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.sys.package.;

public final class FaultToleranceTest$ implements App, Logging {
   public static final FaultToleranceTest$ MODULE$ = new FaultToleranceTest$();
   private static SparkConf conf;
   private static String zkDir;
   private static ListBuffer masters;
   private static ListBuffer workers;
   private static SparkContext sc;
   private static CuratorFramework zk;
   private static int numPassed;
   private static int numFailed;
   private static String sparkHome;
   private static String containerSparkHome;
   private static String dockerMountDir;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static long executionStart;
   private static String[] scala$App$$_args;
   private static ListBuffer scala$App$$initCode;

   static {
      App.$init$(MODULE$);
      Logging.$init$(MODULE$);
      MODULE$.delayedInit(new FaultToleranceTest$delayedInit$body(MODULE$));
      Statics.releaseFence();
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

   public final String[] args() {
      return App.args$(this);
   }

   /** @deprecated */
   public void delayedInit(final Function0 body) {
      App.delayedInit$(this, body);
   }

   public final void main(final String[] args) {
      App.main$(this, args);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public final long executionStart() {
      return executionStart;
   }

   public String[] scala$App$$_args() {
      return scala$App$$_args;
   }

   public void scala$App$$_args_$eq(final String[] x$1) {
      scala$App$$_args = x$1;
   }

   public ListBuffer scala$App$$initCode() {
      return scala$App$$initCode;
   }

   public final void scala$App$_setter_$executionStart_$eq(final long x$1) {
      executionStart = x$1;
   }

   public final void scala$App$_setter_$scala$App$$initCode_$eq(final ListBuffer x$1) {
      scala$App$$initCode = x$1;
   }

   private SparkConf conf() {
      return conf;
   }

   private String zkDir() {
      return zkDir;
   }

   private ListBuffer masters() {
      return masters;
   }

   private ListBuffer workers() {
      return workers;
   }

   private SparkContext sc() {
      return sc;
   }

   private void sc_$eq(final SparkContext x$1) {
      sc = x$1;
   }

   private CuratorFramework zk() {
      return zk;
   }

   private int numPassed() {
      return numPassed;
   }

   private void numPassed_$eq(final int x$1) {
      numPassed = x$1;
   }

   private int numFailed() {
      return numFailed;
   }

   private void numFailed_$eq(final int x$1) {
      numFailed = x$1;
   }

   private String sparkHome() {
      return sparkHome;
   }

   private String containerSparkHome() {
      return containerSparkHome;
   }

   private String dockerMountDir() {
      return dockerMountDir;
   }

   private void afterEach() {
      if (this.sc() != null) {
         this.sc().stop();
         this.sc_$eq((SparkContext)null);
      }

      this.terminateCluster();
      SparkCuratorUtil$.MODULE$.deleteRecursive(this.zk(), this.zkDir() + "/spark_leader");
      SparkCuratorUtil$.MODULE$.deleteRecursive(this.zk(), this.zkDir() + "/master_status");
   }

   private void test(final String name, final Function0 fn) {
      try {
         fn.apply$mcV$sp();
         this.numPassed_$eq(this.numPassed() + 1);
         this.logInfo((Function0)(() -> "=============================================="));
         this.logInfo((Function0)(() -> "Passed: " + name));
         this.logInfo((Function0)(() -> "=============================================="));
      } catch (Exception var4) {
         this.numFailed_$eq(this.numFailed() + 1);
         this.logInfo((Function0)(() -> "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
         this.logError((Function0)(() -> "FAILED: " + name), var4);
         this.logInfo((Function0)(() -> "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
         throw .MODULE$.exit(1);
      }

      this.afterEach();
   }

   private void addMasters(final int num) {
      this.logInfo((Function0)(() -> ">>>>> ADD MASTERS " + num + " <<<<<"));
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), num).foreach((x$4) -> $anonfun$addMasters$2(BoxesRunTime.unboxToInt(x$4)));
   }

   private void addWorkers(final int num) {
      this.logInfo((Function0)(() -> ">>>>> ADD WORKERS " + num + " <<<<<"));
      String masterUrls = this.getMasterUrls(this.masters().toSeq());
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), num).foreach((x$5) -> $anonfun$addWorkers$2(masterUrls, BoxesRunTime.unboxToInt(x$5)));
   }

   private void createClient() {
      this.logInfo((Function0)(() -> ">>>>> CREATE CLIENT <<<<<"));
      if (this.sc() != null) {
         this.sc().stop();
      }

      System.setProperty(package$.MODULE$.DRIVER_PORT().key(), "0");
      this.sc_$eq(new SparkContext(this.getMasterUrls(this.masters().toSeq()), "fault-tolerance", this.containerSparkHome()));
   }

   private String getMasterUrls(final Seq masters) {
      IterableOnceOps var10000 = (IterableOnceOps)masters.map((master) -> master.ip() + ":7077");
      return "spark://" + var10000.mkString(",");
   }

   private TestMasterInfo getLeader() {
      ListBuffer leaders = (ListBuffer)this.masters().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$getLeader$1(x$6)));
      this.assertTrue(leaders.size() == 1, this.assertTrue$default$2());
      return (TestMasterInfo)leaders.apply(0);
   }

   private void killLeader() {
      this.logInfo((Function0)(() -> ">>>>> KILL LEADER <<<<<"));
      this.masters().foreach((x$7) -> {
         $anonfun$killLeader$2(x$7);
         return BoxedUnit.UNIT;
      });
      TestMasterInfo leader = this.getLeader();
      this.masters().$minus$eq(leader);
      leader.kill();
   }

   private void delay(final Duration secs) {
      Thread.sleep(secs.toMillis());
   }

   private Duration delay$default$1() {
      return (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(5))).seconds();
   }

   private void terminateCluster() {
      this.logInfo((Function0)(() -> ">>>>> TERMINATE CLUSTER <<<<<"));
      this.masters().foreach((x$8) -> {
         $anonfun$terminateCluster$2(x$8);
         return BoxedUnit.UNIT;
      });
      this.workers().foreach((x$9) -> {
         $anonfun$terminateCluster$3(x$9);
         return BoxedUnit.UNIT;
      });
      this.masters().clear();
      this.workers().clear();
   }

   private void assertUsable() {
      Future f = scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> {
         boolean var10000;
         try {
            boolean var8;
            label22: {
               label21: {
                  SparkContext qual$1 = MODULE$.sc();
                  Range x$1 = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), 10);
                  int x$2 = qual$1.parallelize$default$2();
                  int[] res = (int[])qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.Int()).collect();
                  var7 = MODULE$;
                  List var10001 = scala.Predef..MODULE$.wrapIntArray(res).toList();
                  List var4 = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), 10).toList();
                  if (var10001 == null) {
                     if (var4 == null) {
                        break label21;
                     }
                  } else if (var10001.equals(var4)) {
                     break label21;
                  }

                  var8 = false;
                  break label22;
               }

               var8 = true;
            }

            var7.assertTrue(var8, MODULE$.assertTrue$default$2());
            var10000 = true;
         } catch (Exception var6) {
            MODULE$.logError((Function0)(() -> "assertUsable() had exception"), var6);
            var6.printStackTrace();
            var10000 = false;
         }

         return var10000;
      }, scala.concurrent.ExecutionContext.Implicits..MODULE$.global());
      this.assertTrue(BoxesRunTime.unboxToBoolean(ThreadUtils$.MODULE$.awaitResult((Awaitable)f, (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(2))).minutes())), this.assertTrue$default$2());
   }

   private void assertValidClusterState() {
      this.logInfo((Function0)(() -> ">>>>> ASSERT VALID CLUSTER STATE <<<<<"));
      this.assertUsable();
      IntRef numAlive = IntRef.create(0);
      IntRef numStandby = IntRef.create(0);
      IntRef numLiveApps = IntRef.create(0);
      ObjectRef liveWorkerIPs = ObjectRef.create(scala.collection.immutable.Nil..MODULE$);
      Future f = scala.concurrent.Future..MODULE$.apply((JFunction0.mcZ.sp)() -> {
         boolean var10000;
         try {
            while(true) {
               if (!this.stateValid$1(liveWorkerIPs, numAlive, numStandby, numLiveApps)) {
                  Thread.sleep(1000L);
                  numAlive.elem = 0;
                  numStandby.elem = 0;
                  numLiveApps.elem = 0;
                  MODULE$.masters().foreach((x$11) -> {
                     $anonfun$assertValidClusterState$5(x$11);
                     return BoxedUnit.UNIT;
                  });
                  MODULE$.masters().foreach((master) -> {
                     $anonfun$assertValidClusterState$6(numAlive, liveWorkerIPs, numStandby, numLiveApps, master);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  var10000 = true;
                  break;
               }
            }
         } catch (Exception var6) {
            MODULE$.logError((Function0)(() -> "assertValidClusterState() had exception"), var6);
            var10000 = false;
         }

         return var10000;
      }, scala.concurrent.ExecutionContext.Implicits..MODULE$.global());

      try {
         this.assertTrue(BoxesRunTime.unboxToBoolean(ThreadUtils$.MODULE$.awaitResult((Awaitable)f, (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(2))).minutes())), this.assertTrue$default$2());
      } catch (TimeoutException var7) {
         this.logError((Function0)(() -> {
            ListBuffer var10000 = MODULE$.masters();
            return "Master states: " + var10000.map((x$12) -> x$12.state());
         }));
         this.logError((Function0)(() -> "Num apps: " + numLiveApps.elem));
         this.logError((Function0)(() -> {
            Object var10000 = MODULE$.workers().map((x$13) -> x$13.ip());
            return "IPs expected: " + var10000 + " / found: " + (Seq)liveWorkerIPs.elem;
         }));
         throw new RuntimeException("Failed to get into acceptable cluster state after 2 min.", var7);
      }
   }

   private void assertTrue(final boolean bool, final String message) {
      if (!bool) {
         throw new IllegalStateException("Assertion failed: " + message);
      }
   }

   private String assertTrue$default$2() {
      return "";
   }

   // $FF: synthetic method
   public static final void $anonfun$new$7(final TestWorkerInfo x$2) {
      x$2.kill();
   }

   // $FF: synthetic method
   public static final ListBuffer $anonfun$addMasters$2(final int x$4) {
      return (ListBuffer)MODULE$.masters().$plus$eq(SparkDocker$.MODULE$.startMaster(MODULE$.dockerMountDir()));
   }

   // $FF: synthetic method
   public static final ListBuffer $anonfun$addWorkers$2(final String masterUrls$1, final int x$5) {
      return (ListBuffer)MODULE$.workers().$plus$eq(SparkDocker$.MODULE$.startWorker(MODULE$.dockerMountDir(), masterUrls$1));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLeader$1(final TestMasterInfo x$6) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$6.state();
         Enumeration.Value var1 = RecoveryState$.MODULE$.ALIVE();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$killLeader$2(final TestMasterInfo x$7) {
      x$7.readState();
   }

   // $FF: synthetic method
   public static final void $anonfun$terminateCluster$2(final TestMasterInfo x$8) {
      x$8.kill();
   }

   // $FF: synthetic method
   public static final void $anonfun$terminateCluster$3(final TestWorkerInfo x$9) {
      x$9.kill();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertValidClusterState$3(final Seq eta$0$1$1, final Object elem) {
      return eta$0$1$1.contains(elem);
   }

   private final boolean stateValid$1(final ObjectRef liveWorkerIPs$1, final IntRef numAlive$1, final IntRef numStandby$1, final IntRef numLiveApps$1) {
      IterableOnceOps var10000 = (IterableOnceOps)this.workers().map((x$10) -> x$10.ip());
      Seq var5 = (Seq)liveWorkerIPs$1.elem;
      return var10000.forall((elem) -> BoxesRunTime.boxToBoolean($anonfun$assertValidClusterState$3(var5, elem))) && numAlive$1.elem == 1 && numStandby$1.elem == this.masters().size() - 1 && numLiveApps$1.elem >= 1;
   }

   // $FF: synthetic method
   public static final void $anonfun$assertValidClusterState$5(final TestMasterInfo x$11) {
      x$11.readState();
   }

   // $FF: synthetic method
   public static final void $anonfun$assertValidClusterState$6(final IntRef numAlive$1, final ObjectRef liveWorkerIPs$1, final IntRef numStandby$1, final IntRef numLiveApps$1, final TestMasterInfo master) {
      label31: {
         label33: {
            Enumeration.Value var6 = master.state();
            Enumeration.Value var10000 = RecoveryState$.MODULE$.ALIVE();
            if (var10000 == null) {
               if (var6 == null) {
                  break label33;
               }
            } else if (var10000.equals(var6)) {
               break label33;
            }

            label23: {
               var10000 = RecoveryState$.MODULE$.STANDBY();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label23;
                  }
               } else if (var10000.equals(var6)) {
                  break label23;
               }

               BoxedUnit var10 = BoxedUnit.UNIT;
               break label31;
            }

            ++numStandby$1.elem;
            BoxedUnit var11 = BoxedUnit.UNIT;
            break label31;
         }

         ++numAlive$1.elem;
         liveWorkerIPs$1.elem = master.liveWorkerIPs();
         BoxedUnit var12 = BoxedUnit.UNIT;
      }

      numLiveApps$1.elem += master.numLiveApps();
   }

   public final void delayedEndpoint$org$apache$spark$deploy$FaultToleranceTest$1() {
      conf = new SparkConf();
      zkDir = (String)((Option)this.conf().get((ConfigEntry)Deploy$.MODULE$.ZOOKEEPER_DIRECTORY())).getOrElse(() -> "/spark");
      masters = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      workers = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      zk = SparkCuratorUtil$.MODULE$.newClient(this.conf(), SparkCuratorUtil$.MODULE$.newClient$default$2());
      numPassed = 0;
      numFailed = 0;
      sparkHome = System.getenv("SPARK_HOME");
      this.assertTrue(this.sparkHome() != null, "Run with a valid SPARK_HOME");
      containerSparkHome = "/opt/spark";
      dockerMountDir = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s:%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.sparkHome(), this.containerSparkHome()}));
      System.setProperty(package$.MODULE$.DRIVER_HOST_ADDRESS().key(), "172.17.42.1");
      this.test("sanity-basic", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(1);
         MODULE$.addWorkers(1);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
      });
      this.test("sanity-many-masters", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(3);
         MODULE$.addWorkers(3);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
      });
      this.test("single-master-halt", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(3);
         MODULE$.addWorkers(2);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
         MODULE$.killLeader();
         MODULE$.delay((new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
         MODULE$.assertValidClusterState();
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
      });
      this.test("single-master-restart", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(1);
         MODULE$.addWorkers(2);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
         MODULE$.killLeader();
         MODULE$.addMasters(1);
         MODULE$.delay((new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
         MODULE$.assertValidClusterState();
         MODULE$.killLeader();
         MODULE$.addMasters(1);
         MODULE$.delay((new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
         MODULE$.assertValidClusterState();
      });
      this.test("cluster-failure", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(2);
         MODULE$.addWorkers(2);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
         MODULE$.terminateCluster();
         MODULE$.addMasters(2);
         MODULE$.addWorkers(2);
         MODULE$.assertValidClusterState();
      });
      this.test("all-but-standby-failure", (JFunction0.mcV.sp)() -> {
         MODULE$.addMasters(2);
         MODULE$.addWorkers(2);
         MODULE$.createClient();
         MODULE$.assertValidClusterState();
         MODULE$.killLeader();
         MODULE$.workers().foreach((x$2) -> {
            $anonfun$new$7(x$2);
            return BoxedUnit.UNIT;
         });
         MODULE$.workers().clear();
         MODULE$.delay((new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
         MODULE$.addWorkers(2);
         MODULE$.assertValidClusterState();
      });
      this.test("rolling-outage", (JFunction0.mcV.sp)() -> {
         boolean var1;
         FaultToleranceTest$ var10000;
         label17: {
            label16: {
               MODULE$.addMasters(1);
               MODULE$.delay(MODULE$.delay$default$1());
               MODULE$.addMasters(1);
               MODULE$.delay(MODULE$.delay$default$1());
               MODULE$.addMasters(1);
               MODULE$.addWorkers(2);
               MODULE$.createClient();
               MODULE$.assertValidClusterState();
               var10000 = MODULE$;
               TestMasterInfo var10001 = MODULE$.getLeader();
               Object var0 = MODULE$.masters().head();
               if (var10001 == null) {
                  if (var0 == null) {
                     break label16;
                  }
               } else if (var10001.equals(var0)) {
                  break label16;
               }

               var1 = false;
               break label17;
            }

            var1 = true;
         }

         var10000.assertTrue(var1, MODULE$.assertTrue$default$2());
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), 3).foreach$mVc$sp((JFunction1.mcVI.sp)(x$3) -> {
            boolean var2;
            FaultToleranceTest$ var10000;
            label17: {
               label16: {
                  MODULE$.killLeader();
                  MODULE$.delay((new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(30))).seconds());
                  MODULE$.assertValidClusterState();
                  var10000 = MODULE$;
                  TestMasterInfo var10001 = MODULE$.getLeader();
                  Object var1 = MODULE$.masters().head();
                  if (var10001 == null) {
                     if (var1 == null) {
                        break label16;
                     }
                  } else if (var10001.equals(var1)) {
                     break label16;
                  }

                  var2 = false;
                  break label17;
               }

               var2 = true;
            }

            var10000.assertTrue(var2, MODULE$.assertTrue$default$2());
            MODULE$.addMasters(1);
         });
      });
      this.logInfo((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Ran %s tests, %s passed and %s failed"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(MODULE$.numPassed() + MODULE$.numFailed()), BoxesRunTime.boxToInteger(MODULE$.numPassed()), BoxesRunTime.boxToInteger(MODULE$.numFailed())}))));
   }

   private FaultToleranceTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
