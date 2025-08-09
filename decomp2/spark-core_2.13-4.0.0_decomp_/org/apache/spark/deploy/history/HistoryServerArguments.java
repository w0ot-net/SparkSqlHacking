package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.History$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.StringOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u000554QAC\u0006\u0001\u0017UA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tQ\u0001\u0011\t\u0011)A\u0005S!)q\u0007\u0001C\u0001q!9Q\b\u0001a\u0001\n\u0013q\u0004bB \u0001\u0001\u0004%I\u0001\u0011\u0005\u0007\r\u0002\u0001\u000b\u0015\u0002\u0017\t\u000b\u001d\u0003A\u0011\u0002%\t\u000bi\u0003A\u0011B.\t\u000f\r\u0004\u0011\u0013!C\u0005I\n1\u0002*[:u_JL8+\u001a:wKJ\f%oZ;nK:$8O\u0003\u0002\r\u001b\u00059\u0001.[:u_JL(B\u0001\b\u0010\u0003\u0019!W\r\u001d7ps*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xmE\u0002\u0001-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f!\u001b\u0005q\"BA\u0010\u0010\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0011\u001f\u0005\u001daunZ4j]\u001e\fAaY8oM\u000e\u0001\u0001CA\u0013'\u001b\u0005y\u0011BA\u0014\u0010\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0003be\u001e\u001c\bcA\f+Y%\u00111\u0006\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003[Qr!A\f\u001a\u0011\u0005=BR\"\u0001\u0019\u000b\u0005E\u001a\u0013A\u0002\u001fs_>$h(\u0003\u000241\u00051\u0001K]3eK\u001aL!!\u000e\u001c\u0003\rM#(/\u001b8h\u0015\t\u0019\u0004$\u0001\u0004=S:LGO\u0010\u000b\u0004smb\u0004C\u0001\u001e\u0001\u001b\u0005Y\u0001\"\u0002\u0012\u0004\u0001\u0004!\u0003\"\u0002\u0015\u0004\u0001\u0004I\u0013A\u00049s_B,'\u000f^5fg\u001aKG.Z\u000b\u0002Y\u0005\u0011\u0002O]8qKJ$\u0018.Z:GS2,w\fJ3r)\t\tE\t\u0005\u0002\u0018\u0005&\u00111\t\u0007\u0002\u0005+:LG\u000fC\u0004F\u000b\u0005\u0005\t\u0019\u0001\u0017\u0002\u0007a$\u0013'A\bqe>\u0004XM\u001d;jKN4\u0015\u000e\\3!\u0003\u0015\u0001\u0018M]:f)\t\t\u0015\nC\u0003)\u000f\u0001\u0007!\nE\u0002L!2r!\u0001\u0014(\u000f\u0005=j\u0015\"A\r\n\u0005=C\u0012a\u00029bG.\fw-Z\u0005\u0003#J\u0013A\u0001T5ti*\u0011q\n\u0007\u0015\u0003\u000fQ\u0003\"!\u0016-\u000e\u0003YS!a\u0016\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Z-\n9A/Y5me\u0016\u001c\u0017!\u00059sS:$Xk]1hK\u0006sG-\u0012=jiR\u0019\u0011\tX1\t\u000buC\u0001\u0019\u00010\u0002\u0011\u0015D\u0018\u000e^\"pI\u0016\u0004\"aF0\n\u0005\u0001D\"aA%oi\"9!\r\u0003I\u0001\u0002\u0004a\u0013!B3se>\u0014\u0018a\u00079sS:$Xk]1hK\u0006sG-\u0012=ji\u0012\"WMZ1vYR$#'F\u0001fU\tacmK\u0001h!\tA7.D\u0001j\u0015\tQg+A\u0005v]\u000eDWmY6fI&\u0011A.\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class HistoryServerArguments implements Logging {
   private String propertiesFile;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private String propertiesFile() {
      return this.propertiesFile;
   }

   private void propertiesFile_$eq(final String x$1) {
      this.propertiesFile = x$1;
   }

   private void parse(final List args) {
      while(true) {
         boolean var5 = false;
         .colon.colon var6 = null;
         if (args instanceof .colon.colon) {
            var5 = true;
            var6 = (.colon.colon)args;
            String var8 = (String)var6.head();
            if ("--help".equals(var8) ? true : "-h".equals(var8)) {
               this.printUsageAndExit(0, this.printUsageAndExit$default$2());
               BoxedUnit var16 = BoxedUnit.UNIT;
               break;
            }
         }

         if (var5) {
            String var9 = (String)var6.head();
            List var10 = var6.next$access$1();
            if ("--properties-file".equals(var9) && var10 instanceof .colon.colon) {
               .colon.colon var11 = (.colon.colon)var10;
               String value = (String)var11.head();
               List tail = var11.next$access$1();
               this.propertiesFile_$eq(value);
               args = tail;
               continue;
            }
         }

         if (scala.collection.immutable.Nil..MODULE$.equals(args)) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            String errorMsg = "Unrecognized options: " + args.mkString(" ") + "\n";
            this.printUsageAndExit(1, errorMsg);
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
         break;
      }

      BoxedUnit var17 = BoxedUnit.UNIT;
   }

   private void printUsageAndExit(final int exitCode, final String error) {
      ConfigEntry[] configs = (ConfigEntry[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])History$.MODULE$.getClass().getDeclaredFields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$printUsageAndExit$1(f)))), (f) -> {
         f.setAccessible(true);
         return (ConfigEntry)f.get(History$.MODULE$);
      }, scala.reflect.ClassTag..MODULE$.apply(ConfigEntry.class));
      int maxConfigLength = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])configs), (x$1) -> BoxesRunTime.boxToInteger($anonfun$printUsageAndExit$3(x$1)), scala.reflect.ClassTag..MODULE$.Int())).max(scala.math.Ordering.Int..MODULE$));
      StringBuilder sb = new StringBuilder(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |" + error + "Usage: HistoryServer [options]\n         |\n         |Options:\n         |  " + scala.collection.StringOps..MODULE$.padTo$extension(scala.Predef..MODULE$.augmentString("--properties-file FILE"), maxConfigLength, ' ') + " Path to a custom Spark properties file.\n         |  " + scala.collection.StringOps..MODULE$.padTo$extension(scala.Predef..MODULE$.augmentString(""), maxConfigLength, ' ') + " Default is conf/spark-defaults.conf.\n         |\n         |Configuration options can be set by setting the corresponding JVM system property.\n         |History Server options are always available; additional options depend on the provider.\n         |\n         |")));
      Tuple2 var8 = scala.collection.ArrayOps..MODULE$.partition$extension(scala.Predef..MODULE$.refArrayOps((Object[])configs), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$printUsageAndExit$7(x$3)));
      if (var8 != null) {
         ConfigEntry[] common = (ConfigEntry[])var8._1();
         ConfigEntry[] fs = (ConfigEntry[])var8._2();
         Tuple2 var7 = new Tuple2(common, fs);
         ConfigEntry[] common = (ConfigEntry[])var7._1();
         ConfigEntry[] fs = (ConfigEntry[])var7._2();
         sb.append("History Server options:\n");
         printConfigs$1(common, sb, maxConfigLength);
         sb.append("FsHistoryProvider options:\n");
         printConfigs$1(fs, sb, maxConfigLength);
         System.err.println(sb.toString());
         System.exit(exitCode);
      } else {
         throw new MatchError(var8);
      }
   }

   private String printUsageAndExit$default$2() {
      return "";
   }

   // $FF: synthetic method
   public static final boolean $anonfun$printUsageAndExit$1(final Field f) {
      return ConfigEntry.class.isAssignableFrom(f.getType());
   }

   // $FF: synthetic method
   public static final int $anonfun$printUsageAndExit$3(final ConfigEntry x$1) {
      return x$1.key().length();
   }

   // $FF: synthetic method
   public static final void $anonfun$printUsageAndExit$6(final IntRef currentDocLen$1, final StringBuilder sb$1, final String intention$1, final String word) {
      if (currentDocLen$1.elem + word.length() > 60) {
         sb$1.append(intention$1).append(" ").append(word);
         currentDocLen$1.elem = word.length() + 1;
      } else {
         sb$1.append(" ").append(word);
         currentDocLen$1.elem += word.length() + 1;
      }
   }

   private static final void printConfigs$1(final ConfigEntry[] configs, final StringBuilder sb$1, final int maxConfigLength$1) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])configs), (x$2) -> x$2.key(), scala.math.Ordering.String..MODULE$)), (conf) -> {
         sb$1.append("  ").append(scala.collection.StringOps..MODULE$.padTo$extension(scala.Predef..MODULE$.augmentString(conf.key()), maxConfigLength$1, ' '));
         IntRef currentDocLen = IntRef.create(0);
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         String intention = "\n" + var10000.$times$extension(scala.Predef..MODULE$.augmentString(" "), maxConfigLength$1 + 2);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.doc().split("\\s+")), (word) -> {
            $anonfun$printUsageAndExit$6(currentDocLen, sb$1, intention, word);
            return BoxedUnit.UNIT;
         });
         return sb$1.append(intention).append(" (Default: ").append(conf.defaultValueString()).append(")\n");
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$printUsageAndExit$7(final ConfigEntry x$3) {
      return !x$3.key().startsWith("spark.history.fs.");
   }

   public HistoryServerArguments(final SparkConf conf, final String[] args) {
      Logging.$init$(this);
      this.propertiesFile = null;
      this.parse(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
      Utils$.MODULE$.loadDefaultSparkProperties(conf, this.propertiesFile());
      Utils$.MODULE$.resetStructuredLogging(conf);
      org.apache.spark.internal.Logging..MODULE$.uninitialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
