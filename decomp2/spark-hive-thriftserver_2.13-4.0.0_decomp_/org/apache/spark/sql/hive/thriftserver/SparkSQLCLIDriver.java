package org.apache.spark.sql.hive.thriftserver;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.cli.CliDriver;
import org.apache.hadoop.hive.common.HiveInterruptUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.processors.AddResourceProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorFactory;
import org.apache.hadoop.hive.ql.processors.DeleteResourceProcessor;
import org.apache.hadoop.hive.ql.processors.ListResourceProcessor;
import org.apache.hadoop.hive.ql.processors.ResetProcessor;
import org.apache.hadoop.hive.ql.processors.SetProcessor;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.util.StringUtils;
import org.apache.spark.SparkThrowable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.AnalysisException;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps;
import scala.collection.mutable.Buffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}uAB\u0010!\u0011\u0003\u0011CF\u0002\u0004/A!\u0005!e\f\u0005\u0006y\u0005!\tA\u0010\u0005\b\u007f\u0005\u0011\r\u0011\"\u0003A\u0011\u0019I\u0015\u0001)A\u0005\u0003\"9!*\u0001b\u0001\n\u0013Y\u0005B\u0002,\u0002A\u0003%A\nC\u0004X\u0003\t\u0007IQ\u0002-\t\rq\u000b\u0001\u0015!\u0004Z\u0011\u001di\u0016\u00011A\u0005\nyCqAY\u0001A\u0002\u0013%1\r\u0003\u0004j\u0003\u0001\u0006Ka\u0018\u0005\u0006U\u0006!\ta\u001b\u0005\u0006Y\u0006!\t!\u001c\u0005\u0006a\u0006!\t!\u001d\u0005\u0006o\u0006!\ta\u001b\u0005\u0006q\u0006!I!\u001f\u0005\b\u0003\u001f\tA\u0011BA\t\r\u0019q\u0003\u0005\u0001\u0012\u0002*!1AH\u0005C\u0001\u0003oA\u0011\"a\u000f\u0013\u0005\u0004%I!!\u0010\t\u000f\u0005}\"\u0003)A\u0005y\"I\u0011q\u0004\nC\u0002\u0013%\u0011\u0011\t\u0005\t\u0003#\u0012\u0002\u0015!\u0003\u0002D!I\u00111\u000b\nC\u0002\u0013%\u0011Q\u000b\u0005\t\u0003C\u0012\u0002\u0015!\u0003\u0002X!9\u00111\r\n\u0005B\u0005\u0015\u0004BBA<%\u0011\u00051\u000eC\u0004\u0002zI!\t%a\u001f\t\u000f\u0005\u0005%\u0003\"\u0011\u0002\u0004\"A\u00111\u0013\n\u0005\u0002\t\n)*A\tTa\u0006\u00148nU)M\u00072KEI]5wKJT!!\t\u0012\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005\r\"\u0013\u0001\u00025jm\u0016T!!\n\u0014\u0002\u0007M\fHN\u0003\u0002(Q\u0005)1\u000f]1sW*\u0011\u0011FK\u0001\u0007CB\f7\r[3\u000b\u0003-\n1a\u001c:h!\ti\u0013!D\u0001!\u0005E\u0019\u0006/\u0019:l'Fc5\tT%Ee&4XM]\n\u0004\u0003A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$AB!osJ+g\r\u0005\u00028u5\t\u0001H\u0003\u0002:M\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002<q\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u00031\na\u0001\u001d:p[B$X#A!\u0011\u0005\t;U\"A\"\u000b\u0005\u0011+\u0015\u0001\u00027b]\u001eT\u0011AR\u0001\u0005U\u00064\u0018-\u0003\u0002I\u0007\n11\u000b\u001e:j]\u001e\fq\u0001\u001d:p[B$\b%A\bd_:$\u0018N\\;fIB\u0013x.\u001c9u+\u0005a\u0005CA'U\u001d\tq%\u000b\u0005\u0002Pe5\t\u0001K\u0003\u0002R{\u00051AH]8pizJ!a\u0015\u001a\u0002\rA\u0013X\rZ3g\u0013\tAUK\u0003\u0002Te\u0005\u00012m\u001c8uS:,X\r\u001a)s_6\u0004H\u000fI\u0001\u0019'B\u000b%kS0I\u0003\u0012{u\nU0Q%>\u0003v\f\u0015*F\r&CV#A-\u0010\u0003i\u000b\u0013aW\u0001\u000egB\f'o\u001b\u0018iC\u0012|w\u000e\u001d\u0018\u00023M\u0003\u0016IU&`\u0011\u0006#uj\u0014)`!J{\u0005k\u0018)S\u000b\u001aK\u0005\fI\u0001\tKbLGoQ8eKV\tq\f\u0005\u00022A&\u0011\u0011M\r\u0002\u0004\u0013:$\u0018\u0001D3ySR\u001cu\u000eZ3`I\u0015\fHC\u00013h!\t\tT-\u0003\u0002ge\t!QK\\5u\u0011\u001dA'\"!AA\u0002}\u000b1\u0001\u001f\u00132\u0003%)\u00070\u001b;D_\u0012,\u0007%\u0001\u000bj]N$\u0018\r\u001c7TS\u001et\u0017\r\u001c%b]\u0012dWM\u001d\u000b\u0002I\u0006!Q\r_5u)\t!g\u000eC\u0003p\u001b\u0001\u0007q,\u0001\u0003d_\u0012,\u0017\u0001B7bS:$\"\u0001\u001a:\t\u000bMt\u0001\u0019\u0001;\u0002\t\u0005\u0014xm\u001d\t\u0004cUd\u0015B\u0001<3\u0005\u0015\t%O]1z\u0003)\u0001(/\u001b8u+N\fw-Z\u0001\u001fG2|7/\u001a%jm\u0016\u001cVm]:j_:\u001cF/\u0019;f\u0013\u001a\u001cF/\u0019:uK\u0012$\"\u0001\u001a>\t\u000bm\u0004\u0002\u0019\u0001?\u0002\u000bM$\u0018\r^3\u0011\u0007u\fY!D\u0001\u007f\u0015\ry\u0018\u0011A\u0001\bg\u0016\u001c8/[8o\u0015\u0011\t\u0019!!\u0002\u0002\u0005Ed'bA\u0012\u0002\b)\u0019\u0011\u0011\u0002\u0015\u0002\r!\fGm\\8q\u0013\r\tiA \u0002\r'\u0016\u001c8/[8o'R\fG/Z\u0001\u0014O\u0016$8i\\7nC:$7i\\7qY\u0016$XM\u001d\u000b\u0003\u0003'\u0001B!M;\u0002\u0016A!\u0011qCA\u0013\u001b\t\tIB\u0003\u0003\u0002\u001c\u0005u\u0011!C2p[BdW\r^3s\u0015\u0011\ty\"!\t\u0002\u000f\r|gn]8mK*\u0011\u00111E\u0001\u0006U2Lg.Z\u0005\u0005\u0003O\tIBA\u0005D_6\u0004H.\u001a;feN!!#a\u000b7!\u0011\ti#a\r\u000e\u0005\u0005=\"\u0002BA\u0019\u0003\u000b\t1a\u00197j\u0013\u0011\t)$a\f\u0003\u0013\rc\u0017\u000e\u0012:jm\u0016\u0014HCAA\u001d!\ti##\u0001\u0007tKN\u001c\u0018n\u001c8Ti\u0006$X-F\u0001}\u00035\u0019Xm]:j_:\u001cF/\u0019;fAU\u0011\u00111\t\t\u0005\u0003\u000b\nYED\u0002~\u0003\u000fJ1!!\u0013\u007f\u00031\u0019Vm]:j_:\u001cF/\u0019;f\u0013\u0011\ti%a\u0014\u0003\u00131{w\rS3ma\u0016\u0014(bAA%}\u0006A1m\u001c8t_2,\u0007%\u0001\u0003d_:4WCAA,!\u0011\tI&!\u0018\u000e\u0005\u0005m#\u0002BA*\u0003\u000fIA!a\u0018\u0002\\\ti1i\u001c8gS\u001e,(/\u0019;j_:\fQaY8oM\u0002\n\u0001c]3u\u0011&4XMV1sS\u0006\u0014G.Z:\u0015\u0007\u0011\f9\u0007C\u0004\u0002ji\u0001\r!a\u001b\u0002\u001b!Lg/\u001a,be&\f'\r\\3t!\u0019\ti'a\u001dM\u00196\u0011\u0011q\u000e\u0006\u0004\u0003c*\u0015\u0001B;uS2LA!!\u001e\u0002p\t\u0019Q*\u00199\u0002'A\u0014\u0018N\u001c;NCN$XM]!oI\u0006\u0003\b/\u00133\u0002\u0015A\u0014xnY3tg\u000ekG\rF\u0002`\u0003{Ba!a \u001d\u0001\u0004a\u0015aA2nI\u0006Y\u0001O]8dKN\u001cH*\u001b8f)\u0015y\u0016QQAE\u0011\u0019\t9)\ba\u0001\u0019\u0006!A.\u001b8f\u0011\u001d\tY)\ba\u0001\u0003\u001b\u000b\u0011#\u00197m_^Le\u000e^3seV\u0004H/\u001b8h!\r\t\u0014qR\u0005\u0004\u0003#\u0013$a\u0002\"p_2,\u0017M\\\u0001\u000fgBd\u0017\u000e^*f[&\u001cu\u000e\\8o)\u0011\t9*!(\u0011\u000b\u00055\u0014\u0011\u0014'\n\t\u0005m\u0015q\u000e\u0002\u0005\u0019&\u001cH\u000f\u0003\u0004\u0002\bz\u0001\r\u0001\u0014"
)
public class SparkSQLCLIDriver extends CliDriver implements Logging {
   private final SessionState sessionState;
   private final SessionState.LogHelper org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console;
   private final Configuration conf;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void printUsage() {
      SparkSQLCLIDriver$.MODULE$.printUsage();
   }

   public static void main(final String[] args) {
      SparkSQLCLIDriver$.MODULE$.main(args);
   }

   public static void exit(final int code) {
      SparkSQLCLIDriver$.MODULE$.exit(code);
   }

   public static void installSignalHandler() {
      SparkSQLCLIDriver$.MODULE$.installSignalHandler();
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

   private SessionState sessionState() {
      return this.sessionState;
   }

   public SessionState.LogHelper org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console() {
      return this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console;
   }

   private Configuration conf() {
      return this.conf;
   }

   public void setHiveVariables(final Map hiveVariables) {
      .MODULE$.MapHasAsScala(hiveVariables).asScala().foreach((kv) -> {
         $anonfun$setHiveVariables$1(kv);
         return BoxedUnit.UNIT;
      });
   }

   public void printMasterAndAppId() {
      String master = SparkSQLEnv$.MODULE$.sparkContext().master();
      String appId = SparkSQLEnv$.MODULE$.sparkContext().applicationId();
      SparkSQLEnv$.MODULE$.sparkContext().uiWebUrl().foreach((webUrl) -> {
         $anonfun$printMasterAndAppId$1(this, webUrl);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Spark master: " + master + ", Application Id: " + appId);
   }

   public int processCmd(final String cmd) {
      String cmd_trimmed = cmd.trim();
      String cmd_lower = cmd_trimmed.toLowerCase(Locale.ROOT);
      String[] tokens = cmd_trimmed.split("\\s+");
      String cmd_1 = cmd_trimmed.substring(tokens[0].length()).trim();
      if (cmd_lower.equals("quit") || cmd_lower.equals("exit")) {
         SparkSQLCLIDriver$.MODULE$.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(this.sessionState());
         SparkSQLCLIDriver$.MODULE$.exit(org.apache.spark.util.SparkExitCode..MODULE$.EXIT_SUCCESS());
      }

      if (!tokens[0].toLowerCase(Locale.ROOT).equals("source") && !cmd_trimmed.startsWith("!")) {
         int ret = 0;
         HiveConf hconf = (HiveConf)this.conf();
         CommandProcessor proc = CommandProcessorFactory.get(tokens, hconf);
         if (proc != null) {
            if (!(proc instanceof Driver) && !(proc instanceof SetProcessor) && !(proc instanceof AddResourceProcessor) && !(proc instanceof ListResourceProcessor) && !(proc instanceof DeleteResourceProcessor) && !(proc instanceof ResetProcessor)) {
               if (this.sessionState().getIsVerbose()) {
                  this.sessionState().out.println(tokens[0] + " " + cmd_1);
               }

               ret = proc.run(cmd_1).getResponseCode();
            } else {
               SparkSQLDriver driver = new SparkSQLDriver(SparkSQLDriver$.MODULE$.$lessinit$greater$default$1());
               driver.init();
               PrintStream out = this.sessionState().out;
               PrintStream err = this.sessionState().err;
               long startTimeNs = System.nanoTime();
               if (this.sessionState().getIsVerbose()) {
                  out.println(cmd);
               }

               try {
                  driver.run(cmd);
               } catch (Throwable var36) {
                  label70: {
                     ret = 1;
                     Enumeration.Value format = SparkSQLEnv$.MODULE$.sparkSession().sessionState().conf().errorMessageFormat();
                     String msg = var36 instanceof SparkThrowable ? org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(var36, format) : var36.getMessage();
                     err.println(msg);
                     Enumeration.Value var26 = org.apache.spark.ErrorMessageFormat..MODULE$.PRETTY();
                     if (format == null) {
                        if (var26 != null) {
                           break label70;
                        }
                     } else if (!format.equals(var26)) {
                        break label70;
                     }

                     if (!this.sessionState().getIsSilent() && (!(var36 instanceof AnalysisException) || var36.getCause() != null)) {
                        var36.printStackTrace(err);
                     }
                  }

                  driver.close();
                  return ret;
               }

               long endTimeNs = System.nanoTime();
               double timeTaken = (double)TimeUnit.NANOSECONDS.toMillis(endTimeNs - startTimeNs) / (double)1000.0F;
               ArrayList res = new ArrayList();
               if (HiveConf.getBoolVar(this.conf(), ConfVars.HIVE_CLI_PRINT_HEADER) || SparkSQLEnv$.MODULE$.sparkSession().sessionState().conf().cliPrintHeader()) {
                  scala.Option..MODULE$.apply(driver.getSchema().getFieldSchemas()).foreach((fields) -> {
                     $anonfun$processCmd$1(out, fields);
                     return BoxedUnit.UNIT;
                  });
               }

               IntRef counter = IntRef.create(0);

               try {
                  while(!out.checkError() && driver.getResults(res)) {
                     .MODULE$.ListHasAsScala(res).asScala().foreach((l) -> {
                        $anonfun$processCmd$3(counter, out, l);
                        return BoxedUnit.UNIT;
                     });
                     res.clear();
                  }
               } catch (IOException var37) {
                  SessionState.LogHelper var10000 = this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console();
                  StringOps var10001 = scala.collection.StringOps..MODULE$;
                  Predef var10002 = scala.Predef..MODULE$;
                  String var10003 = var37.getClass().getName();
                  var10000.printError(var10001.stripMargin$extension(var10002.augmentString("Failed with exception " + var10003 + ": " + var37.getMessage() + "\n                   |" + StringUtils.stringifyException(var37) + "\n                 ")));
                  ret = 1;
               }

               int cret = driver.close();
               if (ret == 0) {
                  ret = cret;
               }

               String responseMsg = "Time taken: " + timeTaken + " seconds";
               if (counter.elem != 0) {
                  responseMsg = responseMsg + ", Fetched " + counter.elem + " row(s)";
               }

               this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo(responseMsg, (String)null);
               driver.destroy();
            }
         }

         return ret;
      } else {
         long startTimeNs = System.nanoTime();
         super.processCmd(cmd);
         long endTimeNs = System.nanoTime();
         double timeTaken = (double)TimeUnit.NANOSECONDS.toMillis(endTimeNs - startTimeNs) / (double)1000.0F;
         this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Time taken: " + timeTaken + " seconds");
         return 0;
      }
   }

   public int processLine(final String line, final boolean allowInterrupting) {
      Object var3 = new Object();

      int var10000;
      try {
         SignalHandler oldSignal = null;
         Signal interruptSignal = null;
         if (allowInterrupting) {
            interruptSignal = new Signal("INT");
            oldSignal = Signal.handle(interruptSignal, new SignalHandler() {
               private boolean interruptRequested;
               // $FF: synthetic field
               private final SparkSQLCLIDriver $outer;

               private boolean interruptRequested() {
                  return this.interruptRequested;
               }

               private void interruptRequested_$eq(final boolean x$1) {
                  this.interruptRequested = x$1;
               }

               public void handle(final Signal signal) {
                  boolean initialRequest = !this.interruptRequested();
                  this.interruptRequested_$eq(true);
                  if (!initialRequest) {
                     this.$outer.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Exiting the JVM");
                     SparkSQLCLIDriver$.MODULE$.exit(org.apache.spark.util.SparkExitCode..MODULE$.ERROR_COMMAND_NOT_FOUND());
                  }

                  this.$outer.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Interrupting... Be patient, this might take some time.");
                  this.$outer.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Press Ctrl+C again to kill JVM");
                  HiveInterruptUtils.interrupt();
               }

               public {
                  if (SparkSQLCLIDriver.this == null) {
                     throw null;
                  } else {
                     this.$outer = SparkSQLCLIDriver.this;
                     this.interruptRequested = false;
                  }
               }
            });
         }

         try {
            IntRef lastRet = IntRef.create(0);
            Buffer commands = .MODULE$.ListHasAsScala(this.splitSemiColon(line)).asScala();
            ObjectRef command = ObjectRef.create("");
            commands.foreach((oneCmd) -> {
               $anonfun$processLine$1(this, command, lastRet, var3, oneCmd);
               return BoxedUnit.UNIT;
            });
            CommandProcessorFactory.clean((HiveConf)this.conf());
            var10000 = lastRet.elem;
         } finally {
            if (oldSignal != null && interruptSignal != null) {
               Signal.handle(interruptSignal, oldSignal);
            }

         }
      } catch (NonLocalReturnControl var14) {
         if (var14.key() != var3) {
            throw var14;
         }

         var10000 = var14.value$mcI$sp();
      }

      return var10000;
   }

   public List splitSemiColon(final String line) {
      BooleanRef insideSingleQuote = BooleanRef.create(false);
      BooleanRef insideDoubleQuote = BooleanRef.create(false);
      BooleanRef insideSimpleComment = BooleanRef.create(false);
      IntRef bracketedCommentLevel = IntRef.create(0);
      BooleanRef escape = BooleanRef.create(false);
      IntRef beginIndex = IntRef.create(0);
      BooleanRef leavingBracketedComment = BooleanRef.create(false);
      BooleanRef isStatement = BooleanRef.create(false);
      ArrayList ret = new ArrayList();
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), line.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(index) -> {
         if (leavingBracketedComment.elem) {
            --bracketedCommentLevel.elem;
            leavingBracketedComment.elem = false;
         }

         if (line.charAt(index) == '\'' && !insideComment$1(insideSimpleComment, bracketedCommentLevel)) {
            if (!escape.elem && !insideDoubleQuote.elem) {
               insideSingleQuote.elem = !insideSingleQuote.elem;
            }
         } else if (line.charAt(index) == '"' && !insideComment$1(insideSimpleComment, bracketedCommentLevel)) {
            if (!escape.elem && !insideSingleQuote.elem) {
               insideDoubleQuote.elem = !insideDoubleQuote.elem;
            }
         } else if (line.charAt(index) == '-') {
            boolean hasNext = index + 1 < line.length();
            if (!insideDoubleQuote.elem && !insideSingleQuote.elem && !insideComment$1(insideSimpleComment, bracketedCommentLevel) && hasNext && line.charAt(index + 1) == '-') {
               insideSimpleComment.elem = true;
            }
         } else if (line.charAt(index) == ';') {
            if (!insideSingleQuote.elem && !insideDoubleQuote.elem && !insideComment$1(insideSimpleComment, bracketedCommentLevel)) {
               if (isStatement.elem) {
                  BoxesRunTime.boxToBoolean(ret.add(line.substring(beginIndex.elem, index)));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               beginIndex.elem = index + 1;
               isStatement.elem = false;
            }
         } else if (line.charAt(index) == '\n') {
            if (!escape.elem) {
               insideSimpleComment.elem = false;
            }
         } else if (line.charAt(index) == '/' && !insideSimpleComment.elem) {
            boolean hasNext = index + 1 < line.length();
            if (!insideSingleQuote.elem && !insideDoubleQuote.elem) {
               if (insideBracketedComment$1(bracketedCommentLevel) && line.charAt(index - 1) == '*') {
                  leavingBracketedComment.elem = true;
               } else if (hasNext && line.charAt(index + 1) == '*') {
                  ++bracketedCommentLevel.elem;
               }
            }
         }

         if (escape.elem) {
            escape.elem = false;
         } else if (line.charAt(index) == '\\') {
            escape.elem = true;
         }

         isStatement.elem = statementInProgress$1(index, isStatement, beginIndex, line, insideSimpleComment, bracketedCommentLevel);
      });
      boolean endOfBracketedComment = leavingBracketedComment.elem && bracketedCommentLevel.elem == 1;
      if (endOfBracketedComment || !isStatement.elem && !insideBracketedComment$1(bracketedCommentLevel)) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxesRunTime.boxToBoolean(ret.add(line.substring(beginIndex.elem)));
      }

      return ret;
   }

   // $FF: synthetic method
   public static final void $anonfun$setHiveVariables$1(final Tuple2 kv) {
      SparkSQLEnv$.MODULE$.sparkSession().sessionState().conf().setConfString((String)kv._1(), (String)kv._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$printMasterAndAppId$1(final SparkSQLCLIDriver $this, final String webUrl) {
      $this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console().printInfo("Spark Web UI available at " + webUrl);
   }

   // $FF: synthetic method
   public static final void $anonfun$processCmd$1(final PrintStream out$1, final List fields) {
      out$1.println(((IterableOnceOps).MODULE$.ListHasAsScala(fields).asScala().map((x$6) -> x$6.getName())).mkString("\t"));
   }

   // $FF: synthetic method
   public static final void $anonfun$processCmd$3(final IntRef counter$1, final PrintStream out$1, final String l) {
      ++counter$1.elem;
      out$1.println(l);
   }

   // $FF: synthetic method
   public static final void $anonfun$processLine$1(final SparkSQLCLIDriver $this, final ObjectRef command$1, final IntRef lastRet$1, final Object nonLocalReturnKey1$1, final String oneCmd) {
      if (org.apache.commons.lang3.StringUtils.endsWith(oneCmd, "\\")) {
         String var7 = (String)command$1.elem;
         command$1.elem = var7 + org.apache.commons.lang3.StringUtils.chop(oneCmd) + ";";
      } else {
         String var10001 = (String)command$1.elem;
         command$1.elem = var10001 + oneCmd;
         if (!org.apache.commons.lang3.StringUtils.isBlank((String)command$1.elem)) {
            int ret = $this.processCmd((String)command$1.elem);
            command$1.elem = "";
            lastRet$1.elem = ret;
            boolean ignoreErrors = HiveConf.getBoolVar($this.conf(), HiveConf.getConfVars("hive.cli.errors.ignore"));
            if (ret != 0 && !ignoreErrors) {
               CommandProcessorFactory.clean((HiveConf)$this.conf());
               throw new NonLocalReturnControl.mcI.sp(nonLocalReturnKey1$1, ret);
            }
         }
      }
   }

   private static final boolean insideBracketedComment$1(final IntRef bracketedCommentLevel$1) {
      return bracketedCommentLevel$1.elem > 0;
   }

   private static final boolean insideComment$1(final BooleanRef insideSimpleComment$1, final IntRef bracketedCommentLevel$1) {
      return insideSimpleComment$1.elem || insideBracketedComment$1(bracketedCommentLevel$1);
   }

   private static final boolean statementInProgress$1(final int index, final BooleanRef isStatement$1, final IntRef beginIndex$1, final String line$1, final BooleanRef insideSimpleComment$1, final IntRef bracketedCommentLevel$1) {
      return isStatement$1.elem || !insideComment$1(insideSimpleComment$1, bracketedCommentLevel$1) && index > beginIndex$1.elem && !String.valueOf(BoxesRunTime.boxToCharacter(line$1.charAt(index))).trim().isEmpty();
   }

   public SparkSQLCLIDriver() {
      Logging.$init$(this);
      this.sessionState = SessionState.get();
      this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$console = new SessionState.LogHelper(this.log());
      this.conf = this.sessionState().getConf();
      SparkSQLEnv$.MODULE$.init();
      if (this.sessionState().getIsSilent()) {
         SparkSQLEnv$.MODULE$.sparkContext().setLogLevel("warn");
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
