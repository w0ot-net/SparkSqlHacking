package org.apache.spark.metrics;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.LinearSeqOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a!B\n\u0015\u0001Ya\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u000b=\u0002A\u0011\u0001\u0019\t\u000fQ\u0002!\u0019!C\u0005k!1a\b\u0001Q\u0001\nYBqa\u0010\u0001C\u0002\u0013%\u0001\t\u0003\u0004J\u0001\u0001\u0006I!\u0011\u0005\b\u0015\u0002\u0011\r\u0011\"\u00036\u0011\u0019Y\u0005\u0001)A\u0005m!AA\n\u0001b\u0001\n\u0003!R\n\u0003\u0004T\u0001\u0001\u0006IA\u0014\u0005\t)\u0002\u0001\r\u0011\"\u0001\u0015+\"A\u0001\u000e\u0001a\u0001\n\u0003!\u0012\u000e\u0003\u0004p\u0001\u0001\u0006KA\u0016\u0005\u0006a\u0002!I!\u001d\u0005\u0006i\u0002!\t!\u001e\u0005\u0006m\u0002!\ta\u001e\u0005\u0006w\u0002!\t\u0001 \u0005\b\u007f\u0002\u0001K\u0011BA\u0001\u00055iU\r\u001e:jGN\u001cuN\u001c4jO*\u0011QCF\u0001\b[\u0016$(/[2t\u0015\t9\u0002$A\u0003ta\u0006\u00148N\u0003\u0002\u001a5\u00051\u0011\r]1dQ\u0016T\u0011aG\u0001\u0004_J<7c\u0001\u0001\u001eGA\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\u0004\"\u0001J\u0014\u000e\u0003\u0015R!A\n\f\u0002\u0011%tG/\u001a:oC2L!\u0001K\u0013\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"\u0001L\u0017\u000e\u0003YI!A\f\f\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u00022gA\u0011!\u0007A\u0007\u0002)!)\u0011F\u0001a\u0001W\u0005qA)\u0012$B+2#v\f\u0015*F\r&CV#\u0001\u001c\u0011\u0005]bT\"\u0001\u001d\u000b\u0005eR\u0014\u0001\u00027b]\u001eT\u0011aO\u0001\u0005U\u00064\u0018-\u0003\u0002>q\t11\u000b\u001e:j]\u001e\fq\u0002R#G\u0003VcEk\u0018)S\u000b\u001aK\u0005\fI\u0001\u000f\u0013:\u001bF+\u0011(D\u000b~\u0013ViR#Y+\u0005\t\u0005C\u0001\"H\u001b\u0005\u0019%B\u0001#F\u0003!i\u0017\r^2iS:<'B\u0001$ \u0003\u0011)H/\u001b7\n\u0005!\u001b%!\u0002*fO\u0016D\u0018aD%O'R\u000bejQ#`%\u0016;U\t\u0017\u0011\u0002;\u0011+e)Q+M)~kU\t\u0016*J\u0007N{6i\u0014(G?\u001aKE*\u0012(B\u001b\u0016\u000ba\u0004R#G\u0003VcEkX'F)JK5iU0D\u001f:3uLR%M\u000b:\u000bU*\u0012\u0011\u0002\u0015A\u0014x\u000e]3si&,7/F\u0001O!\ty\u0015+D\u0001Q\u0015\t1%(\u0003\u0002S!\nQ\u0001K]8qKJ$\u0018.Z:\u0002\u0017A\u0014x\u000e]3si&,7\u000fI\u0001\u0019a\u0016\u0014\u0018J\\:uC:\u001cWmU;c!J|\u0007/\u001a:uS\u0016\u001cX#\u0001,\u0011\t]cfLT\u0007\u00021*\u0011\u0011LW\u0001\b[V$\u0018M\u00197f\u0015\tYv$\u0001\u0006d_2dWm\u0019;j_:L!!\u0018-\u0003\u000f!\u000b7\u000f['baB\u0011qL\u001a\b\u0003A\u0012\u0004\"!Y\u0010\u000e\u0003\tT!a\u0019\u0016\u0002\rq\u0012xn\u001c;?\u0013\t)w$\u0001\u0004Qe\u0016$WMZ\u0005\u0003{\u001dT!!Z\u0010\u00029A,'/\u00138ti\u0006t7-Z*vEB\u0013x\u000e]3si&,7o\u0018\u0013fcR\u0011!.\u001c\t\u0003=-L!\u0001\\\u0010\u0003\tUs\u0017\u000e\u001e\u0005\b]2\t\t\u00111\u0001W\u0003\rAH%M\u0001\u001aa\u0016\u0014\u0018J\\:uC:\u001cWmU;c!J|\u0007/\u001a:uS\u0016\u001c\b%\u0001\u000btKR$UMZ1vYR\u0004&o\u001c9feRLWm\u001d\u000b\u0003UJDQa\u001d\bA\u00029\u000bA\u0001\u001d:pa\u0006Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0003)\fQb];c!J|\u0007/\u001a:uS\u0016\u001cHc\u0001,ys\")1\u000f\u0005a\u0001\u001d\")!\u0010\u0005a\u0001\u0003\u0006)!/Z4fq\u0006Yq-\u001a;J]N$\u0018M\\2f)\tqU\u0010C\u0003\u007f#\u0001\u0007a,\u0001\u0003j]N$\u0018A\u00067pC\u0012\u0004&o\u001c9feRLWm\u001d$s_64\u0015\u000e\\3\u0015\u0007)\f\u0019\u0001C\u0004\u0002\u0006I\u0001\r!a\u0002\u0002\tA\fG\u000f\u001b\t\u0005=\u0005%a,C\u0002\u0002\f}\u0011aa\u00149uS>t\u0007"
)
public class MetricsConfig implements Logging {
   private final SparkConf conf;
   private final String DEFAULT_PREFIX;
   private final Regex INSTANCE_REGEX;
   private final String DEFAULT_METRICS_CONF_FILENAME;
   private final Properties properties;
   private HashMap perInstanceSubProperties;
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

   private String DEFAULT_PREFIX() {
      return this.DEFAULT_PREFIX;
   }

   private Regex INSTANCE_REGEX() {
      return this.INSTANCE_REGEX;
   }

   private String DEFAULT_METRICS_CONF_FILENAME() {
      return this.DEFAULT_METRICS_CONF_FILENAME;
   }

   public Properties properties() {
      return this.properties;
   }

   public HashMap perInstanceSubProperties() {
      return this.perInstanceSubProperties;
   }

   public void perInstanceSubProperties_$eq(final HashMap x$1) {
      this.perInstanceSubProperties = x$1;
   }

   private void setDefaultProperties(final Properties prop) {
      prop.setProperty("*.sink.servlet.class", "org.apache.spark.metrics.sink.MetricsServlet");
      prop.setProperty("*.sink.servlet.path", "/metrics/json");
      prop.setProperty("master.sink.servlet.path", "/metrics/master/json");
      prop.setProperty("applications.sink.servlet.path", "/metrics/applications/json");
   }

   public void initialize() {
      this.setDefaultProperties(this.properties());
      this.loadPropertiesFromFile((Option)this.conf.get((ConfigEntry)package$.MODULE$.METRICS_CONF()));
      String prefix = "spark.metrics.conf.";
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.conf.getAll()), (x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            if (k.startsWith(prefix)) {
               return this.properties().setProperty(k.substring(prefix.length()), v);
            }
         }

         return BoxedUnit.UNIT;
      });
      this.perInstanceSubProperties_$eq(this.subProperties(this.properties(), this.INSTANCE_REGEX()));
      if (this.perInstanceSubProperties().contains(this.DEFAULT_PREFIX())) {
         scala.collection.mutable.Map defaultSubProperties = scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala((Properties)this.perInstanceSubProperties().apply(this.DEFAULT_PREFIX())).asScala();
         this.perInstanceSubProperties().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$initialize$2(check$ifrefutable$1))).withFilter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$initialize$3(this, x$1))).foreach((x$4) -> {
            $anonfun$initialize$4(defaultSubProperties, x$4);
            return BoxedUnit.UNIT;
         });
      }
   }

   public HashMap subProperties(final Properties prop, final Regex regex) {
      HashMap subProperties = new HashMap();
      scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(prop).asScala().foreach((kv) -> {
         if (regex.findPrefixOf((CharSequence)kv._1()).isDefined()) {
            String var5 = (String)kv._1();
            if (var5 != null) {
               Option var6 = regex.unapplySeq(var5);
               if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(2) == 0) {
                  String prefix = (String)((LinearSeqOps)var6.get()).apply(0);
                  String suffix = (String)((LinearSeqOps)var6.get()).apply(1);
                  Tuple2 var4 = new Tuple2(prefix, suffix);
                  String prefixx = (String)var4._1();
                  String suffixx = (String)var4._2();
                  return ((Properties)subProperties.getOrElseUpdate(prefixx, () -> new Properties())).setProperty(suffixx, (String)kv._2());
               }
            }

            throw new MatchError(var5);
         } else {
            return BoxedUnit.UNIT;
         }
      });
      return subProperties;
   }

   public Properties getInstance(final String inst) {
      Option var3 = this.perInstanceSubProperties().get(inst);
      if (var3 instanceof Some var4) {
         Properties s = (Properties)var4.value();
         return s;
      } else if (scala.None..MODULE$.equals(var3)) {
         return (Properties)this.perInstanceSubProperties().getOrElse(this.DEFAULT_PREFIX(), () -> new Properties());
      } else {
         throw new MatchError(var3);
      }
   }

   private void loadPropertiesFromFile(final Option path) {
      InputStream is = null;

      try {
         Object var10000;
         if (path instanceof Some var5) {
            String f = (String)var5.value();
            var10000 = new FileInputStream(f);
         } else {
            if (!scala.None..MODULE$.equals(path)) {
               throw new MatchError(path);
            }

            var10000 = Utils$.MODULE$.getSparkClassLoader().getResourceAsStream(this.DEFAULT_METRICS_CONF_FILENAME());
         }

         is = (InputStream)var10000;
         if (is != null) {
            this.properties().load(is);
         }
      } catch (Exception var12) {
         String file = (String)path.getOrElse(() -> this.DEFAULT_METRICS_CONF_FILENAME());
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error loading configuration file ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), var12);
      } finally {
         if (is != null) {
            is.close();
         }

      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$3(final MetricsConfig $this, final Tuple2 x$1) {
      if (x$1 == null) {
         throw new MatchError(x$1);
      } else {
         boolean var10000;
         label30: {
            String instance = (String)x$1._1();
            String var5 = $this.DEFAULT_PREFIX();
            if (instance == null) {
               if (var5 != null) {
                  break label30;
               }
            } else if (!instance.equals(var5)) {
               break label30;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$5(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$6(final Properties prop$1, final Tuple2 x$2) {
      if (x$2 != null) {
         String k = (String)x$2._1();
         return prop$1.get(k) == null;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$4(final scala.collection.mutable.Map defaultSubProperties$1, final Tuple2 x$4) {
      if (x$4 != null) {
         Properties prop = (Properties)x$4._2();
         defaultSubProperties$1.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$initialize$5(check$ifrefutable$2))).withFilter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$initialize$6(prop, x$2))).foreach((x$3) -> {
            if (x$3 != null) {
               String k = (String)x$3._1();
               String v = (String)x$3._2();
               return prop.put(k, v);
            } else {
               throw new MatchError(x$3);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$4);
      }
   }

   public MetricsConfig(final SparkConf conf) {
      this.conf = conf;
      Logging.$init$(this);
      this.DEFAULT_PREFIX = "*";
      this.INSTANCE_REGEX = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(\\*|[a-zA-Z]+)\\.(.+)"));
      this.DEFAULT_METRICS_CONF_FILENAME = "metrics.properties";
      this.properties = new Properties();
      this.perInstanceSubProperties = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
