package org.apache.spark.scheduler.cluster;

import java.io.InterruptedIOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.yarn.Client;
import org.apache.spark.deploy.yarn.ClientArguments;
import org.apache.spark.deploy.yarn.YarnAppReport;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package.;
import org.apache.spark.launcher.SparkAppHandle.State;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da!\u0002\f\u0018\u0001m\t\u0003\u0002\u0003\u000e\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011E\u0002!\u0011!Q\u0001\nIBQA\u000e\u0001\u0005\u0002]Bqa\u000f\u0001A\u0002\u0013%A\bC\u0004F\u0001\u0001\u0007I\u0011\u0002$\t\r=\u0003\u0001\u0015)\u0003>\u0011\u001d\u0001\u0006\u00011A\u0005\nECqa\u001b\u0001A\u0002\u0013%A\u000e\u0003\u0004o\u0001\u0001\u0006KA\u0015\u0005\u0006_\u0002!\t%\u001b\u0005\u0006a\u0002!I!\u001b\u0004\u0005)\u0002!Q\u000bC\u00037\u0019\u0011\u0005a\fC\u0004`\u0019\u0001\u0007I\u0011\u00021\t\u000f\u0011d\u0001\u0019!C\u0005K\"1q\r\u0004Q!\n\u0005DQ\u0001\u001b\u0007\u0005B%DQA\u001b\u0007\u0005\u0002%DQ!\u001d\u0001\u0005\nyCQA\u001d\u0001\u0005BMDQ!\u001f\u0001\u0005Ri\u0014!$W1s]\u000ec\u0017.\u001a8u'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012T!\u0001G\r\u0002\u000f\rdWo\u001d;fe*\u0011!dG\u0001\ng\u000eDW\rZ;mKJT!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\n\u0004\u0001\t2\u0003CA\u0012%\u001b\u00059\u0012BA\u0013\u0018\u0005QI\u0016M\u001d8TG\",G-\u001e7fe\n\u000b7m[3oIB\u0011qEK\u0007\u0002Q)\u0011\u0011fG\u0001\tS:$XM\u001d8bY&\u00111\u0006\u000b\u0002\b\u0019><w-\u001b8h\u0007\u0001\u0001\"AL\u0018\u000e\u0003eI!\u0001M\r\u0003#Q\u000b7o[*dQ\u0016$W\u000f\\3s\u00136\u0004H.\u0001\u0002tGB\u00111\u0007N\u0007\u00027%\u0011Qg\u0007\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007aJ$\b\u0005\u0002$\u0001!)!d\u0001a\u0001[!)\u0011g\u0001a\u0001e\u000511\r\\5f]R,\u0012!\u0010\t\u0003}\rk\u0011a\u0010\u0006\u0003\u0001\u0006\u000bA!_1s]*\u0011!iG\u0001\u0007I\u0016\u0004Hn\\=\n\u0005\u0011{$AB\"mS\u0016tG/\u0001\u0006dY&,g\u000e^0%KF$\"aR'\u0011\u0005![U\"A%\u000b\u0003)\u000bQa]2bY\u0006L!\u0001T%\u0003\tUs\u0017\u000e\u001e\u0005\b\u001d\u0016\t\t\u00111\u0001>\u0003\rAH%M\u0001\bG2LWM\u001c;!\u00035iwN\\5u_J$\u0006N]3bIV\t!\u000b\u0005\u0002T\u00195\t\u0001AA\u0007N_:LGo\u001c:UQJ,\u0017\rZ\n\u0003\u0019Y\u0003\"a\u0016/\u000e\u0003aS!!\u0017.\u0002\t1\fgn\u001a\u0006\u00027\u0006!!.\u0019<b\u0013\ti\u0006L\u0001\u0004UQJ,\u0017\r\u001a\u000b\u0002%\u0006q\u0011\r\u001c7po&sG/\u001a:skB$X#A1\u0011\u0005!\u0013\u0017BA2J\u0005\u001d\u0011un\u001c7fC:\f!#\u00197m_^Le\u000e^3seV\u0004Ho\u0018\u0013fcR\u0011qI\u001a\u0005\b\u001d>\t\t\u00111\u0001b\u0003=\tG\u000e\\8x\u0013:$XM\u001d:vaR\u0004\u0013a\u0001:v]R\tq)A\u0006ti>\u0004Xj\u001c8ji>\u0014\u0018!E7p]&$xN\u001d+ie\u0016\fGm\u0018\u0013fcR\u0011q)\u001c\u0005\b\u001d\"\t\t\u00111\u0001S\u00039iwN\\5u_J$\u0006N]3bI\u0002\nQa\u001d;beR\f!c^1ji\u001a{'/\u00119qY&\u001c\u0017\r^5p]\u00069\u0012m]=oG6{g.\u001b;pe\u0006\u0003\b\u000f\\5dCRLwN\\\u0001\u0005gR|\u0007\u000f\u0006\u0002Hi\")Q\u000f\u0006a\u0001m\u0006AQ\r_5u\u0007>$W\r\u0005\u0002Io&\u0011\u00010\u0013\u0002\u0004\u0013:$\u0018AF;qI\u0006$X\rR3mK\u001e\fG/[8o)>\\WM\\:\u0015\u0005\u001d[\b\"\u0002?\u0016\u0001\u0004i\u0018A\u0002;pW\u0016t7\u000f\u0005\u0003I}\u0006\u0005\u0011BA@J\u0005\u0015\t%O]1z!\rA\u00151A\u0005\u0004\u0003\u000bI%\u0001\u0002\"zi\u0016\u0004"
)
public class YarnClientSchedulerBackend extends YarnSchedulerBackend {
   public final SparkContext org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$sc;
   private Client org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client;
   private MonitorThread monitorThread;

   public Client org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client() {
      return this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client;
   }

   private void client_$eq(final Client x$1) {
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client = x$1;
   }

   private MonitorThread monitorThread() {
      return this.monitorThread;
   }

   private void monitorThread_$eq(final MonitorThread x$1) {
      this.monitorThread = x$1;
   }

   public void start() {
      super.start();
      String driverHost = (String)this.conf().get(.MODULE$.DRIVER_HOST_ADDRESS());
      int driverPort = BoxesRunTime.unboxToInt(this.conf().get(.MODULE$.DRIVER_PORT()));
      String hostport = driverHost + ":" + driverPort;
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$sc.ui().foreach((ui) -> this.conf().set(package$.MODULE$.DRIVER_APP_UI_ADDRESS(), ui.webUrl()));
      ArrayBuffer argsArrayBuf = new ArrayBuffer();
      argsArrayBuf.$plus$eq("--arg").$plus$eq(hostport);
      this.logDebug(() -> "ClientArguments called with: " + argsArrayBuf.mkString(" "));
      ClientArguments args = new ClientArguments((String[])argsArrayBuf.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
      this.totalExpectedExecutors_$eq(org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber(this.conf(), org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber$default$2()));
      this.client_$eq(new Client(args, this.conf(), this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$sc.env().rpcEnv()));
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client().submitApplication();
      this.bindToYarn(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client().getApplicationId(), scala.None..MODULE$);
      this.waitForApplication();
      this.monitorThread_$eq(this.asyncMonitorApplication());
      this.monitorThread().start();
      this.startBindings();
   }

   private void waitForApplication() {
      long monitorInterval = BoxesRunTime.unboxToLong(this.conf().get(package$.MODULE$.CLIENT_LAUNCH_MONITOR_INTERVAL()));
      scala.Predef..MODULE$.assert(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client() != null && this.appId().isDefined(), () -> "Application has not been submitted yet!");
      Client qual$1 = this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client();
      boolean x$1 = true;
      boolean x$3 = qual$1.monitorApplication$default$2();
      YarnAppReport var6 = qual$1.monitorApplication(true, x$3, monitorInterval);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Option diags;
         label72: {
            YarnApplicationState state = var6.appState();
            Option diags = var6.diagnostics();
            Tuple2 var5 = new Tuple2(state, diags);
            YarnApplicationState state = (YarnApplicationState)var5._1();
            diags = (Option)var5._2();
            YarnApplicationState var16 = YarnApplicationState.FINISHED;
            if (state == null) {
               if (var16 == null) {
                  break label72;
               }
            } else if (state.equals(var16)) {
               break label72;
            }

            YarnApplicationState var17 = YarnApplicationState.FAILED;
            if (state == null) {
               if (var17 == null) {
                  break label72;
               }
            } else if (state.equals(var17)) {
               break label72;
            }

            YarnApplicationState var18 = YarnApplicationState.KILLED;
            if (state == null) {
               if (var18 == null) {
                  break label72;
               }
            } else if (state.equals(var18)) {
               break label72;
            }

            label48: {
               YarnApplicationState var24 = YarnApplicationState.RUNNING;
               if (state == null) {
                  if (var24 == null) {
                     break label48;
                  }
               } else if (state.equals(var24)) {
                  break label48;
               }

               return;
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " has started running."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId().get())})))));
            return;
         }

         String genericMessage = "The YARN application has already ended! It might have been killed or the Application Master may have failed to start. Check the YARN application logs for more details.";
         String var10000;
         if (diags instanceof Some) {
            Some var22 = (Some)diags;
            String msg = (String)var22.value();
            this.logError(() -> genericMessage);
            var10000 = msg;
         } else {
            if (!scala.None..MODULE$.equals(diags)) {
               throw new MatchError(diags);
            }

            var10000 = genericMessage;
         }

         String exceptionMsg = var10000;
         throw new SparkException(exceptionMsg);
      }
   }

   private MonitorThread asyncMonitorApplication() {
      scala.Predef..MODULE$.assert(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client() != null && this.appId().isDefined(), () -> "Application has not been submitted yet!");
      MonitorThread t = new MonitorThread();
      t.setName("YARN application state monitor");
      t.setDaemon(true);
      return t;
   }

   public void stop(final int exitCode) {
      scala.Predef..MODULE$.assert(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client() != null, () -> "Attempted to stop this scheduler before starting it!");
      this.yarnSchedulerEndpoint().signalDriverStop(exitCode);
      if (this.monitorThread() != null) {
         this.monitorThread().stopMonitor();
      }

      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client().reportLauncherState(State.FINISHED);
      super.stop();
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client().stop();
      this.logInfo(() -> "YARN client scheduler backend Stopped");
   }

   public void updateDelegationTokens(final byte[] tokens) {
      super.updateDelegationTokens(tokens);
      this.amEndpoint().foreach((x$3) -> {
         $anonfun$updateDelegationTokens$1(tokens, x$3);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$updateDelegationTokens$1(final byte[] tokens$1, final RpcEndpointRef x$3) {
      x$3.send(new CoarseGrainedClusterMessages.UpdateDelegationTokens(tokens$1));
   }

   public YarnClientSchedulerBackend(final TaskSchedulerImpl scheduler, final SparkContext sc) {
      super(scheduler, sc);
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$sc = sc;
      this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client = null;
      this.monitorThread = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class MonitorThread extends Thread {
      private boolean allowInterrupt;
      // $FF: synthetic field
      public final YarnClientSchedulerBackend $outer;

      private boolean allowInterrupt() {
         return this.allowInterrupt;
      }

      private void allowInterrupt_$eq(final boolean x$1) {
         this.allowInterrupt = x$1;
      }

      public void run() {
         try {
            Client qual$1 = this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$client();
            boolean x$1 = false;
            boolean x$2 = qual$1.monitorApplication$default$1();
            long x$3 = qual$1.monitorApplication$default$3();
            YarnAppReport var7 = qual$1.monitorApplication(x$2, false, x$3);
            if (var7 == null) {
               throw new MatchError(var7);
            }

            FinalApplicationStatus state = var7.finalState();
            Option diags = var7.diagnostics();
            Tuple2 var6 = new Tuple2(state, diags);
            FinalApplicationStatus statex = (FinalApplicationStatus)var6._1();
            Option diagsx = (Option)var6._2();
            this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"YARN application has exited unexpectedly with state "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "! Check the YARN application logs for more details."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, statex)}))))));
            diagsx.foreach((err) -> {
               $anonfun$run$2(this, err);
               return BoxedUnit.UNIT;
            });
            this.allowInterrupt_$eq(false);
            this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$$sc.stop();
            if ((FinalApplicationStatus.FAILED.equals(statex) ? true : FinalApplicationStatus.KILLED.equals(statex)) && BoxesRunTime.unboxToBoolean(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().conf().get(package$.MODULE$.AM_CLIENT_MODE_EXIT_ON_ERROR()))) {
               this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ApplicationMaster finished with status ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, statex)}))).$plus(this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SparkContext should exit with code 1."})))).log(scala.collection.immutable.Nil..MODULE$))));
               System.exit(1);
               BoxedUnit var22 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var21 = BoxedUnit.UNIT;
            }
         } catch (Throwable var20) {
            if (!(var20 instanceof InterruptedException ? true : var20 instanceof InterruptedIOException)) {
               throw var20;
            }

            this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().logInfo(() -> "Interrupting monitor thread");
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      }

      public void stopMonitor() {
         if (this.allowInterrupt()) {
            this.interrupt();
         }
      }

      // $FF: synthetic method
      public YarnClientSchedulerBackend org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$2(final MonitorThread $this, final String err) {
         $this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$scheduler$cluster$YarnClientSchedulerBackend$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Diagnostics message: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, err)})))));
      }

      public MonitorThread() {
         if (YarnClientSchedulerBackend.this == null) {
            throw null;
         } else {
            this.$outer = YarnClientSchedulerBackend.this;
            super();
            this.allowInterrupt = true;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
