package org.apache.spark.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Try;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\r\u001b\u0001q\u0011\u0003\u0002C\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u000bq\u0002A\u0011A\u001f\t\u000f\u0005\u0003!\u0019!C\u0005\u0005\"1!\n\u0001Q\u0001\n\rCqa\u0013\u0001C\u0002\u0013%A\n\u0003\u0004Q\u0001\u0001\u0006I!\u0014\u0005\b#\u0002\u0011\r\u0011\"\u0003S\u0011\u00191\u0006\u0001)A\u0005'\"9q\u000b\u0001a\u0001\n\u0013a\u0005b\u0002-\u0001\u0001\u0004%I!\u0017\u0005\u0007?\u0002\u0001\u000b\u0015B'\t\u000f\u0001\u0004!\u0019!C\u0005C\"1Q\r\u0001Q\u0001\n\tD\u0001B\u001a\u0001\t\u0006\u0004%I\u0001\u0014\u0005\u0006O\u0002!I\u0001\u001b\u0005\u0007S\u0002!\tA\u00076\t\rI\u0004A\u0011\u0001\u000et\u0011\u00199\b\u0001\"\u0001\u001dq\u001e1\u0011P\u0007E\u00019i4a!\u0007\u000e\t\u0002qY\b\"\u0002\u001f\u0015\t\u0003a\bbB?\u0015\u0005\u0004%)A \u0005\u0007\u007fR\u0001\u000bQ\u0002 \t\u0013\u0005\u0005A#%A\u0005\u0002\u0005\r!a\u0005)s_\u000e47/T3ue&\u001c7oR3ui\u0016\u0014(BA\u000e\u001d\u0003!)\u00070Z2vi>\u0014(BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0014\u0007\u0001\u0019\u0013\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U5j\u0011a\u000b\u0006\u0003Yq\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003]-\u0012q\u0001T8hO&tw-A\u0005qe>\u001cgm\u001d#je\u000e\u0001\u0001C\u0001\u001a:\u001d\t\u0019t\u0007\u0005\u00025K5\tQG\u0003\u00027a\u00051AH]8pizJ!\u0001O\u0013\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003q\u0015\na\u0001P5oSRtDC\u0001 A!\ty\u0004!D\u0001\u001b\u0011\u001dy#\u0001%AA\u0002E\na\u0002\u001d:pG\u001a\u001c8\u000b^1u\r&dW-F\u0001D!\t!\u0015*D\u0001F\u0015\t1u)\u0001\u0003mC:<'\"\u0001%\u0002\t)\fg/Y\u0005\u0003u\u0015\u000bq\u0002\u001d:pG\u001a\u001c8\u000b^1u\r&dW\rI\u0001\bi\u0016\u001cH/\u001b8h+\u0005i\u0005C\u0001\u0013O\u0013\tyUEA\u0004C_>dW-\u00198\u0002\u0011Q,7\u000f^5oO\u0002\n\u0001\u0002]1hKNK'0Z\u000b\u0002'B\u0011A\u0005V\u0005\u0003+\u0016\u0012A\u0001T8oO\u0006I\u0001/Y4f'&TX\rI\u0001\fSN\fe/Y5mC\ndW-A\bjg\u00063\u0018-\u001b7bE2,w\fJ3r)\tQV\f\u0005\u0002%7&\u0011A,\n\u0002\u0005+:LG\u000fC\u0004_\u0015\u0005\u0005\t\u0019A'\u0002\u0007a$\u0013'\u0001\u0007jg\u00063\u0018-\u001b7bE2,\u0007%\u0001\u000bdkJ\u0014XM\u001c;Qe>\u001cWm]:IC:$G.Z\u000b\u0002EB\u0011AiY\u0005\u0003I\u0016\u0013Q\u0002\u0015:pG\u0016\u001c8\u000fS1oI2,\u0017!F2veJ,g\u000e\u001e)s_\u000e,7o\u001d%b]\u0012dW\rI\u0001\u0012SN\u0004&o\\2gg\u00063\u0018-\u001b7bE2,\u0017aD2p[B,H/\u001a)bO\u0016\u001c\u0016N_3\u0015\u0003M\u000ba$\u00193e!J|7MZ:NKR\u0014\u0018nY:Ge>lwJ\\3Qe>\u001cWm]:\u0015\u0007-t\u0007\u000f\u0005\u0002@Y&\u0011QN\u0007\u0002\u000e!J|7MZ:NKR\u0014\u0018nY:\t\u000b=\u0004\u0002\u0019A6\u0002\u0015\u0005dG.T3ue&\u001c7\u000fC\u0003r!\u0001\u00071+A\u0002qS\u0012\f!cY8naV$X\r\u0015:pG\u0016\u001c8\u000f\u0016:fKR\tA\u000fE\u00023kNK!A^\u001e\u0003\u0007M+G/A\td_6\u0004X\u000f^3BY2lU\r\u001e:jGN$\u0012a[\u0001\u0014!J|7MZ:NKR\u0014\u0018nY:HKR$XM\u001d\t\u0003\u007fQ\u0019\"\u0001F\u0012\u0015\u0003i\f\u0011\u0002\u001d+sK\u0016LeNZ8\u0016\u0003y\n!\u0002\u001d+sK\u0016LeNZ8!\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\u0001\u0016\u0004c\u0005\u001d1FAA\u0005!\u0011\tY!!\u0006\u000e\u0005\u00055!\u0002BA\b\u0003#\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005MQ%\u0001\u0006b]:|G/\u0019;j_:LA!a\u0006\u0002\u000e\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class ProcfsMetricsGetter implements Logging {
   private boolean isProcfsAvailable;
   private final String procfsDir;
   private final String procfsStatFile;
   private final boolean testing;
   private final long pageSize;
   private boolean isAvailable;
   private final ProcessHandle currentProcessHandle;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static String $lessinit$greater$default$1() {
      return ProcfsMetricsGetter$.MODULE$.$lessinit$greater$default$1();
   }

   public static ProcfsMetricsGetter pTreeInfo() {
      return ProcfsMetricsGetter$.MODULE$.pTreeInfo();
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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private String procfsStatFile() {
      return this.procfsStatFile;
   }

   private boolean testing() {
      return this.testing;
   }

   private long pageSize() {
      return this.pageSize;
   }

   private boolean isAvailable() {
      return this.isAvailable;
   }

   private void isAvailable_$eq(final boolean x$1) {
      this.isAvailable = x$1;
   }

   private ProcessHandle currentProcessHandle() {
      return this.currentProcessHandle;
   }

   private boolean isProcfsAvailable$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            boolean var10001;
            if (this.testing()) {
               var10001 = true;
            } else {
               Try procDirExists = .MODULE$.apply((JFunction0.mcZ.sp)() -> Files.exists(Paths.get(this.procfsDir), new LinkOption[0])).recover(new Serializable() {
                  private static final long serialVersionUID = 0L;
                  // $FF: synthetic field
                  private final ProcfsMetricsGetter $outer;

                  public final Object applyOrElse(final Throwable x1, final Function1 default) {
                     if (x1 instanceof IOException var5) {
                        this.$outer.logWarning((Function0)(() -> "Exception checking for procfs dir"), var5);
                        return BoxesRunTime.boxToBoolean(false);
                     } else {
                        return default.apply(x1);
                     }
                  }

                  public final boolean isDefinedAt(final Throwable x1) {
                     return x1 instanceof IOException;
                  }

                  public {
                     if (ProcfsMetricsGetter.this == null) {
                        throw null;
                     } else {
                        this.$outer = ProcfsMetricsGetter.this;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               });
               boolean shouldPollProcessTreeMetrics = BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_PROCESS_TREE_METRICS_ENABLED()));
               var10001 = BoxesRunTime.unboxToBoolean(procDirExists.get()) && shouldPollProcessTreeMetrics;
            }

            this.isProcfsAvailable = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.isProcfsAvailable;
   }

   private boolean isProcfsAvailable() {
      return !this.bitmap$0 ? this.isProcfsAvailable$lzycompute() : this.isProcfsAvailable;
   }

   private long computePageSize() {
      if (this.testing()) {
         return 4096L;
      } else {
         long var10000;
         try {
            String[] cmd = (String[])((Object[])(new String[]{"getconf", "PAGESIZE"}));
            String out = Utils$.MODULE$.executeAndGetOutput(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(cmd).toImmutableArraySeq(), Utils$.MODULE$.executeAndGetOutput$default$2(), Utils$.MODULE$.executeAndGetOutput$default$3(), Utils$.MODULE$.executeAndGetOutput$default$4());
            var10000 = (long)Integer.parseInt(out.split("\n")[0]);
         } catch (Exception var4) {
            this.logDebug((Function0)(() -> "Exception when trying to compute pagesize, as a result reporting of ProcessTree metrics is stopped"));
            this.isAvailable_$eq(false);
            var10000 = 0L;
         }

         return var10000;
      }
   }

   public ProcfsMetrics addProcfsMetricsFromOneProcess(final ProcfsMetrics allMetrics, final long pid) {
      try {
         File pidDir = new File(this.procfsDir, Long.toString(pid));
         return (ProcfsMetrics)Utils$.MODULE$.tryWithResource(() -> this.openReader$1(pidDir), (in) -> {
            String procInfo = in.readLine();
            String[] procInfoSplit = procInfo.split(" ");
            long vmem = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(procInfoSplit[22]));
            long rssMem = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(procInfoSplit[23])) * this.pageSize();
            if (procInfoSplit[1].toLowerCase(Locale.US).contains("java")) {
               return allMetrics.copy(allMetrics.jvmVmemTotal() + vmem, allMetrics.jvmRSSTotal() + rssMem, allMetrics.copy$default$3(), allMetrics.copy$default$4(), allMetrics.copy$default$5(), allMetrics.copy$default$6());
            } else if (procInfoSplit[1].toLowerCase(Locale.US).contains("python")) {
               long x$1 = allMetrics.pythonVmemTotal() + vmem;
               long x$2 = allMetrics.pythonRSSTotal() + rssMem;
               long x$3 = allMetrics.copy$default$1();
               long x$4 = allMetrics.copy$default$2();
               long x$5 = allMetrics.copy$default$5();
               long x$6 = allMetrics.copy$default$6();
               return allMetrics.copy(x$3, x$4, x$1, x$2, x$5, x$6);
            } else {
               long x$7 = allMetrics.otherVmemTotal() + vmem;
               long x$8 = allMetrics.otherRSSTotal() + rssMem;
               long x$9 = allMetrics.copy$default$1();
               long x$10 = allMetrics.copy$default$2();
               long x$11 = allMetrics.copy$default$3();
               long x$12 = allMetrics.copy$default$4();
               return allMetrics.copy(x$9, x$10, x$11, x$12, x$7, x$8);
            }
         });
      } catch (IOException var6) {
         this.logDebug((Function0)(() -> "There was a problem with reading the stat file of the process. "), var6);
         throw var6;
      }
   }

   public Set computeProcessTree() {
      if (!this.isAvailable()) {
         return scala.Predef..MODULE$.Set().empty();
      } else {
         Set children = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.currentProcessHandle().descendants().map((x$1) -> BoxesRunTime.boxToLong($anonfun$computeProcessTree$1(x$1))).toList()).asScala().toSet();
         return (Set)children.$plus(BoxesRunTime.boxToLong(this.currentProcessHandle().pid()));
      }
   }

   public ProcfsMetrics computeAllMetrics() {
      Object var1 = new Object();

      ProcfsMetrics var10000;
      try {
         if (!this.isAvailable()) {
            return new ProcfsMetrics(0L, 0L, 0L, 0L, 0L, 0L);
         }

         Set pids = this.computeProcessTree();
         ObjectRef allMetrics = ObjectRef.create(new ProcfsMetrics(0L, 0L, 0L, 0L, 0L, 0L));
         pids.foreach((JFunction1.mcVJ.sp)(p) -> {
            try {
               allMetrics.elem = this.addProcfsMetricsFromOneProcess((ProcfsMetrics)allMetrics.elem, p);
               if (!this.isAvailable()) {
                  throw new NonLocalReturnControl(var1, new ProcfsMetrics(0L, 0L, 0L, 0L, 0L, 0L));
               }
            } catch (IOException var5) {
               throw new NonLocalReturnControl(var1, new ProcfsMetrics(0L, 0L, 0L, 0L, 0L, 0L));
            }
         });
         var10000 = (ProcfsMetrics)allMetrics.elem;
      } catch (NonLocalReturnControl var5) {
         if (var5.key() != var1) {
            throw var5;
         }

         var10000 = (ProcfsMetrics)var5.value();
      }

      return var10000;
   }

   private final BufferedReader openReader$1(final File pidDir$1) {
      File f = new File(pidDir$1, this.procfsStatFile());
      return new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
   }

   // $FF: synthetic method
   public static final long $anonfun$computeProcessTree$1(final ProcessHandle x$1) {
      return x$1.pid();
   }

   public ProcfsMetricsGetter(final String procfsDir) {
      this.procfsDir = procfsDir;
      Logging.$init$(this);
      this.procfsStatFile = "stat";
      this.testing = Utils$.MODULE$.isTesting();
      this.pageSize = this.computePageSize();
      this.isAvailable = this.isProcfsAvailable();
      this.currentProcessHandle = ProcessHandle.current();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
