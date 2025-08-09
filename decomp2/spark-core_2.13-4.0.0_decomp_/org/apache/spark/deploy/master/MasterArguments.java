package org.apache.spark.deploy.master;

import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.IntParam$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e4QAE\n\u0001'uA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\tu\u0001\u0011\t\u0011)A\u0005w!)q\b\u0001C\u0001\u0001\"9Q\t\u0001a\u0001\n\u00031\u0005bB$\u0001\u0001\u0004%\t\u0001\u0013\u0005\u0007\u001d\u0002\u0001\u000b\u0015B\u0018\t\u000f=\u0003\u0001\u0019!C\u0001!\"9A\u000b\u0001a\u0001\n\u0003)\u0006BB,\u0001A\u0003&\u0011\u000bC\u0004Y\u0001\u0001\u0007I\u0011\u0001)\t\u000fe\u0003\u0001\u0019!C\u00015\"1A\f\u0001Q!\nECq!\u0018\u0001A\u0002\u0013\u0005a\tC\u0004_\u0001\u0001\u0007I\u0011A0\t\r\u0005\u0004\u0001\u0015)\u00030\u0011\u0015\u0011\u0007\u0001\"\u0003d\u0011\u0015)\b\u0001\"\u0003w\u0005=i\u0015m\u001d;fe\u0006\u0013x-^7f]R\u001c(B\u0001\u000b\u0016\u0003\u0019i\u0017m\u001d;fe*\u0011acF\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c2\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011Q\u0005K\u0007\u0002M)\u0011qeF\u0001\tS:$XM\u001d8bY&\u0011\u0011F\n\u0002\b\u0019><w-\u001b8h\u0003\u0011\t'oZ:\u0004\u0001A\u0019q$L\u0018\n\u00059\u0002#!B!se\u0006L\bC\u0001\u00198\u001d\t\tT\u0007\u0005\u00023A5\t1G\u0003\u00025W\u00051AH]8pizJ!A\u000e\u0011\u0002\rA\u0013X\rZ3g\u0013\tA\u0014H\u0001\u0004TiJLgn\u001a\u0006\u0003m\u0001\nAaY8oMB\u0011A(P\u0007\u0002/%\u0011ah\u0006\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDcA!D\tB\u0011!\tA\u0007\u0002'!)!f\u0001a\u0001Y!)!h\u0001a\u0001w\u0005!\u0001n\\:u+\u0005y\u0013\u0001\u00035pgR|F%Z9\u0015\u0005%c\u0005CA\u0010K\u0013\tY\u0005E\u0001\u0003V]&$\bbB'\u0006\u0003\u0003\u0005\raL\u0001\u0004q\u0012\n\u0014!\u00025pgR\u0004\u0013\u0001\u00029peR,\u0012!\u0015\t\u0003?IK!a\u0015\u0011\u0003\u0007%sG/\u0001\u0005q_J$x\fJ3r)\tIe\u000bC\u0004N\u0011\u0005\u0005\t\u0019A)\u0002\u000bA|'\u000f\u001e\u0011\u0002\u0013],'-V5Q_J$\u0018!D<fEVK\u0007k\u001c:u?\u0012*\u0017\u000f\u0006\u0002J7\"9QjCA\u0001\u0002\u0004\t\u0016AC<fEVK\u0007k\u001c:uA\u0005q\u0001O]8qKJ$\u0018.Z:GS2,\u0017A\u00059s_B,'\u000f^5fg\u001aKG.Z0%KF$\"!\u00131\t\u000f5s\u0011\u0011!a\u0001_\u0005y\u0001O]8qKJ$\u0018.Z:GS2,\u0007%A\u0003qCJ\u001cX\r\u0006\u0002JI\")!\u0006\u0005a\u0001KB\u0019am[\u0018\u000f\u0005\u001dLgB\u0001\u001ai\u0013\u0005\t\u0013B\u00016!\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\t1K7\u000f\u001e\u0006\u0003U\u0002B#\u0001E8\u0011\u0005A\u001cX\"A9\u000b\u0005I\u0004\u0013AC1o]>$\u0018\r^5p]&\u0011A/\u001d\u0002\bi\u0006LGN]3d\u0003E\u0001(/\u001b8u+N\fw-Z!oI\u0016C\u0018\u000e\u001e\u000b\u0003\u0013^DQ\u0001_\tA\u0002E\u000b\u0001\"\u001a=ji\u000e{G-\u001a"
)
public class MasterArguments implements Logging {
   private String host;
   private int port;
   private int webUiPort;
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

   public String host() {
      return this.host;
   }

   public void host_$eq(final String x$1) {
      this.host = x$1;
   }

   public int port() {
      return this.port;
   }

   public void port_$eq(final int x$1) {
      this.port = x$1;
   }

   public int webUiPort() {
      return this.webUiPort;
   }

   public void webUiPort_$eq(final int x$1) {
      this.webUiPort = x$1;
   }

   public String propertiesFile() {
      return this.propertiesFile;
   }

   public void propertiesFile_$eq(final String x$1) {
      this.propertiesFile = x$1;
   }

   private void parse(final List args) {
      while(true) {
         boolean var6 = false;
         .colon.colon var7 = null;
         if (args instanceof .colon.colon) {
            var6 = true;
            var7 = (.colon.colon)args;
            String var9 = (String)var7.head();
            List var10 = var7.next$access$1();
            if (("--host".equals(var9) ? true : "-h".equals(var9)) && var10 instanceof .colon.colon) {
               .colon.colon var11 = (.colon.colon)var10;
               String value = (String)var11.head();
               List tail = var11.next$access$1();
               Utils$.MODULE$.checkHost(value);
               this.host_$eq(value);
               args = tail;
               continue;
            }
         }

         if (var6) {
            String var14 = (String)var7.head();
            List var15 = var7.next$access$1();
            if (("--port".equals(var14) ? true : "-p".equals(var14)) && var15 instanceof .colon.colon) {
               .colon.colon var16 = (.colon.colon)var15;
               String var17 = (String)var16.head();
               List tail = var16.next$access$1();
               if (var17 != null) {
                  Option var19 = IntParam$.MODULE$.unapply(var17);
                  if (!var19.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var19.get());
                     this.port_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var6) {
            String var21 = (String)var7.head();
            List var22 = var7.next$access$1();
            if ("--webui-port".equals(var21) && var22 instanceof .colon.colon) {
               .colon.colon var23 = (.colon.colon)var22;
               String var24 = (String)var23.head();
               List tail = var23.next$access$1();
               if (var24 != null) {
                  Option var26 = IntParam$.MODULE$.unapply(var24);
                  if (!var26.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var26.get());
                     this.webUiPort_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var6) {
            String var28 = (String)var7.head();
            List var29 = var7.next$access$1();
            if ("--properties-file".equals(var28) && var29 instanceof .colon.colon) {
               .colon.colon var30 = (.colon.colon)var29;
               String value = (String)var30.head();
               List tail = var30.next$access$1();
               this.propertiesFile_$eq(value);
               args = tail;
               continue;
            }
         }

         label84: {
            if (var6) {
               String var33 = (String)var7.head();
               if ("--help".equals(var33)) {
                  this.printUsageAndExit(0);
                  BoxedUnit var35 = BoxedUnit.UNIT;
                  break label84;
               }
            }

            if (scala.collection.immutable.Nil..MODULE$.equals(args)) {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               this.printUsageAndExit(1);
               BoxedUnit var34 = BoxedUnit.UNIT;
            }
         }

         BoxedUnit var36 = BoxedUnit.UNIT;
         return;
      }
   }

   private void printUsageAndExit(final int exitCode) {
      System.err.println("Usage: Master [options]\n\nOptions:\n  -h HOST, --host HOST   Hostname to listen on\n  -p PORT, --port PORT   Port to listen on (default: 7077)\n  --webui-port PORT      Port for web UI (default: 8080)\n  --properties-file FILE Path to a custom Spark properties file.\n                         Default is conf/spark-defaults.conf.");
      System.exit(exitCode);
   }

   public MasterArguments(final String[] args, final SparkConf conf) {
      Logging.$init$(this);
      this.host = Utils$.MODULE$.localHostName();
      this.port = 7077;
      this.webUiPort = 8080;
      this.propertiesFile = null;
      if (System.getenv("SPARK_MASTER_HOST") != null) {
         this.host_$eq(System.getenv("SPARK_MASTER_HOST"));
      }

      if (System.getenv("SPARK_MASTER_PORT") != null) {
         this.port_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getenv("SPARK_MASTER_PORT"))));
      }

      if (System.getenv("SPARK_MASTER_WEBUI_PORT") != null) {
         this.webUiPort_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getenv("SPARK_MASTER_WEBUI_PORT"))));
      }

      this.parse(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
      this.propertiesFile_$eq(Utils$.MODULE$.loadDefaultSparkProperties(conf, this.propertiesFile()));
      Utils$.MODULE$.resetStructuredLogging(conf);
      org.apache.spark.internal.Logging..MODULE$.uninitialize();
      if (conf.contains(package$.MODULE$.MASTER_UI_PORT().key())) {
         this.webUiPort_$eq(BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.MASTER_UI_PORT())));
      }

   }
}
