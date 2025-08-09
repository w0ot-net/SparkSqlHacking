package org.apache.spark.scheduler.cluster.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkContext;
import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.PluginContext;
import org.apache.spark.deploy.k8s.Config;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Array.;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%f\u0001B\t\u0013\u0001}AQA\u000e\u0001\u0005\u0002]B\u0001B\u000f\u0001\t\u0006\u0004%\ta\u000f\u0005\n\u0005\u0002\u0001\r\u00111A\u0005\n\rC\u0011\u0002\u0013\u0001A\u0002\u0003\u0007I\u0011B%\t\u0013I\u0003\u0001\u0019!A!B\u0013!\u0005bB*\u0001\u0005\u0004%I\u0001\u0016\u0005\u0007;\u0002\u0001\u000b\u0011B+\t\u0011y\u0003\u0001\u0019!C\u0001%}C\u0001b\u0019\u0001A\u0002\u0013\u0005!\u0003\u001a\u0005\u0007M\u0002\u0001\u000b\u0015\u00021\t\u000b\u001d\u0004A\u0011\t5\t\r}\u0004A\u0011IA\u0001\u0011\u001d\t\u0019\u0001\u0001C\u0005\u0003\u000bAq!a\n\u0001\t\u0013\tI\u0003C\u0004\u0002~\u0001!I!a \t\u000f\u0005M\u0005\u0001\"\u0003\u0002\u0016\nAR\t_3dkR|'OU8mY\u0012\u0013\u0018N^3s!2,x-\u001b8\u000b\u0005M!\u0012aA69g*\u0011QCF\u0001\bG2,8\u000f^3s\u0015\t9\u0002$A\u0005tG\",G-\u001e7fe*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\u0001\u0003\u0006\r\t\u0003C\u0019j\u0011A\t\u0006\u0003G\u0011\nA\u0001\\1oO*\tQ%\u0001\u0003kCZ\f\u0017BA\u0014#\u0005\u0019y%M[3diB\u0011\u0011FL\u0007\u0002U)\u00111\u0006L\u0001\u0007a2,x-\u001b8\u000b\u00055B\u0012aA1qS&\u0011qF\u000b\u0002\r\tJLg/\u001a:QYV<\u0017N\u001c\t\u0003cQj\u0011A\r\u0006\u0003ga\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003kI\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGO\u0010\u000b\u0002qA\u0011\u0011\bA\u0007\u0002%\u0005iQ)\u0014)U3~kU\t\u0016*J\u0007N+\u0012\u0001\u0010\t\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fa\t\u0001\"\u001a=fGV$xN]\u0005\u0003\u0003z\u0012q\"\u0012=fGV$xN]'fiJL7m]\u0001\rgB\f'o[\"p]R,\u0007\u0010^\u000b\u0002\tB\u0011QIR\u0007\u00021%\u0011q\t\u0007\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0011gB\f'o[\"p]R,\u0007\u0010^0%KF$\"A\u0013)\u0011\u0005-sU\"\u0001'\u000b\u00035\u000bQa]2bY\u0006L!a\u0014'\u0003\tUs\u0017\u000e\u001e\u0005\b#\u0012\t\t\u00111\u0001E\u0003\rAH%M\u0001\u000egB\f'o[\"p]R,\u0007\u0010\u001e\u0011\u0002\u001fA,'/[8eS\u000e\u001cVM\u001d<jG\u0016,\u0012!\u0016\t\u0003-nk\u0011a\u0016\u0006\u00031f\u000b!bY8oGV\u0014(/\u001a8u\u0015\tQF%\u0001\u0003vi&d\u0017B\u0001/X\u0005a\u00196\r[3ek2,G-\u0012=fGV$xN]*feZL7-Z\u0001\u0011a\u0016\u0014\u0018n\u001c3jGN+'O^5dK\u0002\n\u0001\"\\5o)\u0006\u001c8n]\u000b\u0002AB\u00111*Y\u0005\u0003E2\u00131!\u00138u\u00031i\u0017N\u001c+bg.\u001cx\fJ3r)\tQU\rC\u0004R\u0013\u0005\u0005\t\u0019\u00011\u0002\u00135Lg\u000eV1tWN\u0004\u0013\u0001B5oSR$2!\u001b={!\u0011Q7.\\7\u000e\u0003eK!\u0001\\-\u0003\u00075\u000b\u0007\u000f\u0005\u0002ok:\u0011qn\u001d\t\u0003a2k\u0011!\u001d\u0006\u0003ez\ta\u0001\u0010:p_Rt\u0014B\u0001;M\u0003\u0019\u0001&/\u001a3fM&\u0011ao\u001e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Qd\u0005\"B=\f\u0001\u0004!\u0015AA:d\u0011\u0015Y8\u00021\u0001}\u0003\r\u0019G\u000f\u001f\t\u0003SuL!A \u0016\u0003\u001bAcWoZ5o\u0007>tG/\u001a=u\u0003!\u0019\b.\u001e;e_^tG#\u0001&\u0002\u001d\u001d,G\u000fU3bW6+GO]5dgR1\u0011qAA\u0007\u0003G\u00012aSA\u0005\u0013\r\tY\u0001\u0014\u0002\u0005\u0019>tw\rC\u0004\u0002\u00105\u0001\r!!\u0005\u0002\u000fM,X.\\1ssB!\u00111CA\u0010\u001b\t\t)B\u0003\u0003\u0002\u0018\u0005e\u0011A\u0001<2\u0015\ri\u00131\u0004\u0006\u0004\u0003;A\u0012AB:uCR,8/\u0003\u0003\u0002\"\u0005U!aD#yK\u000e,Ho\u001c:Tk6l\u0017M]=\t\r\u0005\u0015R\u00021\u0001n\u0003\u0011q\u0017-\\3\u0002\r\rDwn\\:f)\u0019\tY#!\r\u0002HA!1*!\fn\u0013\r\ty\u0003\u0014\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005Mb\u00021\u0001\u00026\u0005!A.[:u!\u0019\t9$!\u0011\u0002\u00129!\u0011\u0011HA\u001f\u001d\r\u0001\u00181H\u0005\u0002\u001b&\u0019\u0011q\b'\u0002\u000fA\f7m[1hK&!\u00111IA#\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u007fa\u0005bBA%\u001d\u0001\u0007\u00111J\u0001\u0007a>d\u0017nY=\u0011\t\u00055\u0013Q\u000f\b\u0005\u0003\u001f\nyG\u0004\u0003\u0002R\u0005%d\u0002BA*\u0003KrA!!\u0016\u0002b9!\u0011qKA0\u001d\u0011\tI&!\u0018\u000f\u0007A\fY&C\u0001\u001e\u0013\tYB$\u0003\u0002\u001a5%\u0019\u00111\r\r\u0002\r\u0011,\u0007\u000f\\8z\u0013\r\u0019\u0012q\r\u0006\u0004\u0003GB\u0012\u0002BA6\u0003[\naaQ8oM&<'bA\n\u0002h%!\u0011\u0011OA:\u0003I)\u00050Z2vi>\u0014(k\u001c7m!>d\u0017nY=\u000b\t\u0005-\u0014QN\u0005\u0005\u0003o\nIHA\u0003WC2,X-C\u0002\u0002|1\u00131\"\u00128v[\u0016\u0014\u0018\r^5p]\u0006qr.\u001e;mS\u0016\u00148O\u0012:p[6+H\u000e^5qY\u0016$\u0015.\\3og&|gn\u001d\u000b\u0005\u0003\u0003\u000by\t\u0005\u0004\u0002\u0004\u00065\u0015\u0011C\u0007\u0003\u0003\u000bSA!a\"\u0002\n\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0004\u0003\u0017c\u0015AC2pY2,7\r^5p]&!\u00111IAC\u0011\u001d\t\tj\u0004a\u0001\u0003k\t\u0011\u0003\\5ti^KG\u000f[8vi\u0012\u0013\u0018N^3s\u0003!yW\u000f\u001e7jKJ\u001cHCBA\u001b\u0003/\u000bI\nC\u0004\u00024A\u0001\r!!\u000e\t\u000f\u0005m\u0005\u00031\u0001\u0002\u001e\u0006\u0019q-\u001a;\u0011\u000f-\u000by*!\u0005\u0002$&\u0019\u0011\u0011\u0015'\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA&\u0002&&\u0019\u0011q\u0015'\u0003\u000b\u0019cw.\u0019;"
)
public class ExecutorRollDriverPlugin implements DriverPlugin, Logging {
   private ExecutorMetrics EMPTY_METRICS;
   private SparkContext sparkContext;
   private final ScheduledExecutorService periodicService;
   private int minTasks;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   public void registerMetrics(final String x$1, final PluginContext x$2) {
      super.registerMetrics(x$1, x$2);
   }

   public Object receive(final Object x$1) throws Exception {
      return super.receive(x$1);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ExecutorMetrics EMPTY_METRICS$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.EMPTY_METRICS = new ExecutorMetrics(.MODULE$.emptyLongArray());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.EMPTY_METRICS;
   }

   public ExecutorMetrics EMPTY_METRICS() {
      return !this.bitmap$0 ? this.EMPTY_METRICS$lzycompute() : this.EMPTY_METRICS;
   }

   private SparkContext sparkContext() {
      return this.sparkContext;
   }

   private void sparkContext_$eq(final SparkContext x$1) {
      this.sparkContext = x$1;
   }

   private ScheduledExecutorService periodicService() {
      return this.periodicService;
   }

   public int minTasks() {
      return this.minTasks;
   }

   public void minTasks_$eq(final int x$1) {
      this.minTasks = x$1;
   }

   public Map init(final SparkContext sc, final PluginContext ctx) {
      long interval = BoxesRunTime.unboxToLong(sc.conf().get(Config$.MODULE$.EXECUTOR_ROLL_INTERVAL()));
      if (interval <= 0L) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Disabled due to invalid interval value, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"'", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INTERVAL..MODULE$, BoxesRunTime.boxToLong(interval * 1000L))}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (!BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package..MODULE$.DECOMMISSION_ENABLED()))) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Disabled because ", " is false."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package..MODULE$.DECOMMISSION_ENABLED().key())})))));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         this.minTasks_$eq(BoxesRunTime.unboxToInt(sc.conf().get(Config$.MODULE$.MINIMUM_TASKS_PER_EXECUTOR_BEFORE_ROLLING())));
         this.sparkContext_$eq(sc);
         Enumeration.Value policy = Config.ExecutorRollPolicy$.MODULE$.withName((String)sc.conf().get(Config$.MODULE$.EXECUTOR_ROLL_POLICY()));
         this.periodicService().scheduleAtFixedRate(() -> {
            try {
               SchedulerBackend var4 = this.sparkContext().schedulerBackend();
               if (var4 instanceof KubernetesClusterSchedulerBackend var5) {
                  Seq executorSummaryList = this.sparkContext().statusStore().executorList(true);
                  Option var7 = this.choose(executorSummaryList, policy);
                  if (var7 instanceof Some var8) {
                     String id = (String)var8.value();
                     this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ask to decommission executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, id)})))));
                     long now = System.currentTimeMillis();
                     var5.decommissionExecutor(id, new ExecutorDecommissionInfo("Rolling via " + policy + " at " + now, org.apache.spark.scheduler.ExecutorDecommissionInfo..MODULE$.apply$default$2()), false, var5.decommissionExecutor$default$4());
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     this.logInfo((Function0)(() -> "There is nothing to roll."));
                     BoxedUnit var14 = BoxedUnit.UNIT;
                  }

                  BoxedUnit var15 = BoxedUnit.UNIT;
               } else {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This plugin expects "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, KubernetesClusterSchedulerBackend.class.getSimpleName())}))))));
                  BoxedUnit var16 = BoxedUnit.UNIT;
               }
            } catch (Throwable var13) {
               this.logError((Function0)(() -> "Error in rolling thread"), var13);
            }

         }, interval, interval, TimeUnit.SECONDS);
      }

      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava();
   }

   public void shutdown() {
      this.periodicService().shutdown();
   }

   private long getPeakMetrics(final ExecutorSummary summary, final String name) {
      return ((ExecutorMetrics)summary.peakMemoryMetrics().getOrElse(() -> this.EMPTY_METRICS())).getMetricValue(name);
   }

   private Option choose(final Seq list, final Enumeration.Value policy) {
      Seq var30;
      label159: {
         Seq listWithoutDriver;
         label162: {
            listWithoutDriver = (Seq)((IterableOps)list.filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$choose$1(x$2)))).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$choose$2(this, x$3)));
            Enumeration.Value var10000 = Config.ExecutorRollPolicy$.MODULE$.ID();
            if (var10000 == null) {
               if (policy == null) {
                  break label162;
               }
            } else if (var10000.equals(policy)) {
               break label162;
            }

            label163: {
               var10000 = Config.ExecutorRollPolicy$.MODULE$.ADD_TIME();
               if (var10000 == null) {
                  if (policy == null) {
                     break label163;
                  }
               } else if (var10000.equals(policy)) {
                  break label163;
               }

               label164: {
                  var10000 = Config.ExecutorRollPolicy$.MODULE$.TOTAL_GC_TIME();
                  if (var10000 == null) {
                     if (policy == null) {
                        break label164;
                     }
                  } else if (var10000.equals(policy)) {
                     break label164;
                  }

                  label165: {
                     var10000 = Config.ExecutorRollPolicy$.MODULE$.TOTAL_DURATION();
                     if (var10000 == null) {
                        if (policy == null) {
                           break label165;
                        }
                     } else if (var10000.equals(policy)) {
                        break label165;
                     }

                     label166: {
                        var10000 = Config.ExecutorRollPolicy$.MODULE$.AVERAGE_DURATION();
                        if (var10000 == null) {
                           if (policy == null) {
                              break label166;
                           }
                        } else if (var10000.equals(policy)) {
                           break label166;
                        }

                        label167: {
                           var10000 = Config.ExecutorRollPolicy$.MODULE$.FAILED_TASKS();
                           if (var10000 == null) {
                              if (policy == null) {
                                 break label167;
                              }
                           } else if (var10000.equals(policy)) {
                              break label167;
                           }

                           label168: {
                              var10000 = Config.ExecutorRollPolicy$.MODULE$.PEAK_JVM_ONHEAP_MEMORY();
                              if (var10000 == null) {
                                 if (policy == null) {
                                    break label168;
                                 }
                              } else if (var10000.equals(policy)) {
                                 break label168;
                              }

                              label169: {
                                 var10000 = Config.ExecutorRollPolicy$.MODULE$.PEAK_JVM_OFFHEAP_MEMORY();
                                 if (var10000 == null) {
                                    if (policy == null) {
                                       break label169;
                                    }
                                 } else if (var10000.equals(policy)) {
                                    break label169;
                                 }

                                 label170: {
                                    var10000 = Config.ExecutorRollPolicy$.MODULE$.TOTAL_SHUFFLE_WRITE();
                                    if (var10000 == null) {
                                       if (policy == null) {
                                          break label170;
                                       }
                                    } else if (var10000.equals(policy)) {
                                       break label170;
                                    }

                                    label171: {
                                       var10000 = Config.ExecutorRollPolicy$.MODULE$.DISK_USED();
                                       if (var10000 == null) {
                                          if (policy == null) {
                                             break label171;
                                          }
                                       } else if (var10000.equals(policy)) {
                                          break label171;
                                       }

                                       label172: {
                                          var10000 = Config.ExecutorRollPolicy$.MODULE$.OUTLIER();
                                          if (var10000 == null) {
                                             if (policy == null) {
                                                break label172;
                                             }
                                          } else if (var10000.equals(policy)) {
                                             break label172;
                                          }

                                          var10000 = Config.ExecutorRollPolicy$.MODULE$.OUTLIER_NO_FALLBACK();
                                          if (var10000 == null) {
                                             if (policy != null) {
                                                throw new MatchError(policy);
                                             }
                                          } else if (!var10000.equals(policy)) {
                                             throw new MatchError(policy);
                                          }

                                          var30 = this.outliersFromMultipleDimensions(listWithoutDriver);
                                          break label159;
                                       }

                                       var30 = (Seq)this.outliersFromMultipleDimensions(listWithoutDriver).$plus$plus((IterableOnce)((SeqOps)listWithoutDriver.sortBy((x$13) -> BoxesRunTime.boxToLong($anonfun$choose$13(x$13)), scala.math.Ordering.Long..MODULE$)).reverse());
                                       break label159;
                                    }

                                    var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$12) -> BoxesRunTime.boxToLong($anonfun$choose$12(x$12)), scala.math.Ordering.Long..MODULE$)).reverse();
                                    break label159;
                                 }

                                 var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$11) -> BoxesRunTime.boxToLong($anonfun$choose$11(x$11)), scala.math.Ordering.Long..MODULE$)).reverse();
                                 break label159;
                              }

                              var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$10) -> BoxesRunTime.boxToLong($anonfun$choose$10(this, x$10)), scala.math.Ordering.Long..MODULE$)).reverse();
                              break label159;
                           }

                           var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$9) -> BoxesRunTime.boxToLong($anonfun$choose$9(this, x$9)), scala.math.Ordering.Long..MODULE$)).reverse();
                           break label159;
                        }

                        var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$8) -> BoxesRunTime.boxToInteger($anonfun$choose$8(x$8)), scala.math.Ordering.Int..MODULE$)).reverse();
                        break label159;
                     }

                     var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((e) -> BoxesRunTime.boxToFloat($anonfun$choose$7(e)), scala.math.Ordering.DeprecatedFloatOrdering..MODULE$)).reverse();
                     break label159;
                  }

                  var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$7) -> BoxesRunTime.boxToLong($anonfun$choose$6(x$7)), scala.math.Ordering.Long..MODULE$)).reverse();
                  break label159;
               }

               var30 = (Seq)((SeqOps)listWithoutDriver.sortBy((x$6) -> BoxesRunTime.boxToLong($anonfun$choose$5(x$6)), scala.math.Ordering.Long..MODULE$)).reverse();
               break label159;
            }

            var30 = (Seq)listWithoutDriver.sortBy((x$5) -> x$5.addTime(), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()));
            break label159;
         }

         var30 = (Seq)listWithoutDriver.sortBy((x$4) -> BoxesRunTime.boxToInteger($anonfun$choose$3(x$4)), scala.math.Ordering.Int..MODULE$);
      }

      Seq sortedList = var30;
      return sortedList.headOption().map((x$14) -> x$14.id());
   }

   private Seq outliersFromMultipleDimensions(final Seq listWithoutDriver) {
      return (Seq)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)this.outliers((Seq)listWithoutDriver.filter((x$15) -> BoxesRunTime.boxToBoolean($anonfun$outliersFromMultipleDimensions$1(x$15))), (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$2(e))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$3(e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$4(e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$5(e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$6(this, e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$7(this, e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$8(e))))).$plus$plus(this.outliers(listWithoutDriver, (e) -> BoxesRunTime.boxToFloat($anonfun$outliersFromMultipleDimensions$9(e))));
   }

   private Seq outliers(final Seq list, final Function1 get) {
      if (list.isEmpty()) {
         return list;
      } else {
         int size = list.size();
         Seq values = (Seq)list.map(get);
         float mean = BoxesRunTime.unboxToFloat(values.sum(scala.math.Numeric.FloatIsFractional..MODULE$)) / (float)size;
         double sd = Math.sqrt((double)(BoxesRunTime.unboxToFloat(((IterableOnceOps)values.map((JFunction1.mcFF.sp)(v) -> (v - mean) * (v - mean))).sum(scala.math.Numeric.FloatIsFractional..MODULE$)) / (float)size));
         return (Seq)((SeqOps)((SeqOps)list.filter((e) -> BoxesRunTime.boxToBoolean($anonfun$outliers$2(get, mean, sd, e)))).sortBy((e) -> BoxesRunTime.boxToFloat($anonfun$outliers$3(get, e)), scala.math.Ordering.DeprecatedFloatOrdering..MODULE$)).reverse();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$choose$1(final ExecutorSummary x$2) {
      return x$2.id().equals(org.apache.spark.SparkContext..MODULE$.DRIVER_IDENTIFIER());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$choose$2(final ExecutorRollDriverPlugin $this, final ExecutorSummary x$3) {
      return x$3.totalTasks() >= $this.minTasks();
   }

   // $FF: synthetic method
   public static final int $anonfun$choose$3(final ExecutorSummary x$4) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$4.id()));
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$5(final ExecutorSummary x$6) {
      return x$6.totalGCTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$6(final ExecutorSummary x$7) {
      return x$7.totalDuration();
   }

   // $FF: synthetic method
   public static final float $anonfun$choose$7(final ExecutorSummary e) {
      return (float)e.totalDuration() / (float)Math.max(1, e.totalTasks());
   }

   // $FF: synthetic method
   public static final int $anonfun$choose$8(final ExecutorSummary x$8) {
      return x$8.failedTasks();
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$9(final ExecutorRollDriverPlugin $this, final ExecutorSummary x$9) {
      return $this.getPeakMetrics(x$9, "JVMHeapMemory");
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$10(final ExecutorRollDriverPlugin $this, final ExecutorSummary x$10) {
      return $this.getPeakMetrics(x$10, "JVMOffHeapMemory");
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$11(final ExecutorSummary x$11) {
      return x$11.totalShuffleWrite();
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$12(final ExecutorSummary x$12) {
      return x$12.diskUsed();
   }

   // $FF: synthetic method
   public static final long $anonfun$choose$13(final ExecutorSummary x$13) {
      return x$13.totalDuration();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$outliersFromMultipleDimensions$1(final ExecutorSummary x$15) {
      return x$15.totalTasks() > 0;
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$2(final ExecutorSummary e) {
      return (float)(e.totalDuration() / (long)e.totalTasks());
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$3(final ExecutorSummary e) {
      return (float)e.totalDuration();
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$4(final ExecutorSummary e) {
      return (float)e.totalGCTime();
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$5(final ExecutorSummary e) {
      return (float)e.failedTasks();
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$6(final ExecutorRollDriverPlugin $this, final ExecutorSummary e) {
      return (float)$this.getPeakMetrics(e, "JVMHeapMemory");
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$7(final ExecutorRollDriverPlugin $this, final ExecutorSummary e) {
      return (float)$this.getPeakMetrics(e, "JVMOffHeapMemory");
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$8(final ExecutorSummary e) {
      return (float)e.totalShuffleWrite();
   }

   // $FF: synthetic method
   public static final float $anonfun$outliersFromMultipleDimensions$9(final ExecutorSummary e) {
      return (float)e.diskUsed();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$outliers$2(final Function1 get$1, final float mean$1, final double sd$1, final ExecutorSummary e) {
      return (double)(BoxesRunTime.unboxToFloat(get$1.apply(e)) - mean$1) > (double)2 * sd$1;
   }

   // $FF: synthetic method
   public static final float $anonfun$outliers$3(final Function1 get$1, final ExecutorSummary e) {
      return BoxesRunTime.unboxToFloat(get$1.apply(e));
   }

   public ExecutorRollDriverPlugin() {
      Logging.$init$(this);
      this.periodicService = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonSingleThreadScheduledExecutor("executor-roller");
      this.minTasks = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
