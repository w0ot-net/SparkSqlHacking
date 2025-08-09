package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.logging.DriverLogger$;
import org.apache.spark.util.logging.RollingFileAppender$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple4;
import scala.Option.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\u0007\u000f\u000191\u0002\u0002C\u0011\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\t\u0011\u0019\u0002!\u0011!Q\u0001\n\u001dBQa\u000b\u0001\u0005\u00021Bq\u0001\r\u0001C\u0002\u0013%\u0011\u0007\u0003\u0004E\u0001\u0001\u0006IA\r\u0005\b\u000b\u0002\u0011\r\u0011\"\u0003G\u0011\u0019Y\u0005\u0001)A\u0005\u000f\"9A\n\u0001b\u0001\n\u0013i\u0005B\u0002-\u0001A\u0003%a\nC\u0003Z\u0001\u0011\u0005!\fC\u0003w\u0001\u0011\u0005q\u000fC\u0003z\u0001\u0011%!PA\u0007Ee&4XM\u001d'pOB\u000bw-\u001a\u0006\u0003\u001fA\t!!^5\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f\u001c!\tA\u0012$D\u0001\u000f\u0013\tQbBA\u0005XK\n,\u0016\nU1hKB\u0011AdH\u0007\u0002;)\u0011a\u0004E\u0001\tS:$XM\u001d8bY&\u0011\u0001%\b\u0002\b\u0019><w-\u001b8h\u0003\u0019\u0001\u0018M]3oi\u000e\u0001\u0001C\u0001\r%\u0013\t)cB\u0001\u0007Ee&4XM\u001d'pOR\u000b'-\u0001\u0003d_:4\u0007C\u0001\u0015*\u001b\u0005\u0001\u0012B\u0001\u0016\u0011\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0004[9z\u0003C\u0001\r\u0001\u0011\u0015\t3\u00011\u0001$\u0011\u001513\u00011\u0001(\u0003E\u0019X\u000f\u001d9peR,G\rT8h)f\u0004Xm]\u000b\u0002eA\u00191G\u000f\u001f\u000e\u0003QR!!\u000e\u001c\u0002\u0013%lW.\u001e;bE2,'BA\u001c9\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002s\u0005)1oY1mC&\u00111\b\u000e\u0002\u0004'\u0016$\bCA\u001fC\u001b\u0005q$BA A\u0003\u0011a\u0017M\\4\u000b\u0003\u0005\u000bAA[1wC&\u00111I\u0010\u0002\u0007'R\u0014\u0018N\\4\u0002%M,\b\u000f]8si\u0016$Gj\\4UsB,7\u000fI\u0001\rI\u00164\u0017-\u001e7u\u0005f$Xm]\u000b\u0002\u000fB\u0011\u0001*S\u0007\u0002q%\u0011!\n\u000f\u0002\u0004\u0013:$\u0018!\u00043fM\u0006,H\u000e\u001e\"zi\u0016\u001c\b%\u0001\u0004m_\u001e$\u0015N]\u000b\u0002\u001dB\u0011qJ\u0016\b\u0003!R\u0003\"!\u0015\u001d\u000e\u0003IS!a\u0015\u0012\u0002\rq\u0012xn\u001c;?\u0013\t)\u0006(\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0007^S!!\u0016\u001d\u0002\u000f1|w\rR5sA\u00051!/\u001a8eKJ$\"a\u00176\u0011\u0007q\u000bGM\u0004\u0002^?:\u0011\u0011KX\u0005\u0002s%\u0011\u0001\rO\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00117MA\u0002TKFT!\u0001\u0019\u001d\u0011\u0005\u0015DW\"\u00014\u000b\u0005\u001dD\u0014a\u0001=nY&\u0011\u0011N\u001a\u0002\u0005\u001d>$W\rC\u0003l\u0015\u0001\u0007A.A\u0004sKF,Xm\u001d;\u0011\u00055$X\"\u00018\u000b\u0005=\u0004\u0018\u0001\u00025uiBT!!\u001d:\u0002\u000fM,'O\u001e7fi*\t1/A\u0004kC.\f'\u000f^1\n\u0005Ut'A\u0005%uiB\u001cVM\u001d<mKR\u0014V-];fgR\f\u0011B]3oI\u0016\u0014Hj\\4\u0015\u00059C\b\"B6\f\u0001\u0004a\u0017AB4fi2{w\rF\u0005|\u0003\u0007\t9!a\u0003\u0002\u0016A1\u0001\n (\u007f}zL!! \u001d\u0003\rQ+\b\u000f\\35!\tAu0C\u0002\u0002\u0002a\u0012A\u0001T8oO\"1\u0011Q\u0001\u0007A\u00029\u000bA\u0002\\8h\t&\u0014Xm\u0019;pefDa!!\u0003\r\u0001\u0004q\u0015a\u00027pORK\b/\u001a\u0005\b\u0003\u001ba\u0001\u0019AA\b\u00031ygMZ:fi>\u0003H/[8o!\u0011A\u0015\u0011\u0003@\n\u0007\u0005M\u0001H\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003/a\u0001\u0019A$\u0002\u0015\tLH/\u001a'f]\u001e$\b\u000e"
)
public class DriverLogPage extends WebUIPage implements Logging {
   private final DriverLogTab parent;
   private final SparkConf conf;
   private final Set supportedLogTypes;
   private final int defaultBytes;
   private final String logDir;
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

   private Set supportedLogTypes() {
      return this.supportedLogTypes;
   }

   private int defaultBytes() {
      return this.defaultBytes;
   }

   private String logDir() {
      return this.logDir;
   }

   public Seq render(final HttpServletRequest request) {
      String logType = (String).MODULE$.apply(request.getParameter("logType")).getOrElse(() -> DriverLogger$.MODULE$.DRIVER_LOG_FILE());
      Option offset = .MODULE$.apply(request.getParameter("offset")).map((x$1) -> BoxesRunTime.boxToLong($anonfun$render$2(x$1)));
      int byteLength = BoxesRunTime.unboxToInt(.MODULE$.apply(request.getParameter("byteLength")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$render$3(x$2))).getOrElse((JFunction0.mcI.sp)() -> this.defaultBytes()));
      Tuple4 var7 = this.getLog(this.logDir(), logType, offset, byteLength);
      if (var7 != null) {
         String logText = (String)var7._1();
         long startByte = BoxesRunTime.unboxToLong(var7._2());
         long endByte = BoxesRunTime.unboxToLong(var7._3());
         long logLength = BoxesRunTime.unboxToLong(var7._4());
         Tuple4 var6 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
         String logText = (String)var6._1();
         long startByte = BoxesRunTime.unboxToLong(var6._2());
         long endByte = BoxesRunTime.unboxToLong(var6._3());
         long logLength = BoxesRunTime.unboxToLong(var6._4());
         long curLogLength = endByte - startByte;
         Null $md = scala.xml.Null..MODULE$;
         MetaData var47 = new UnprefixedAttribute("id", new Text("log-data"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Showing "));
         $buf.$amp$plus(BoxesRunTime.boxToLong(curLogLength));
         $buf.$amp$plus(new Text(" Bytes: "));
         $buf.$amp$plus(Long.toString(startByte));
         $buf.$amp$plus(new Text(" - "));
         $buf.$amp$plus(Long.toString(endByte));
         $buf.$amp$plus(new Text(" of "));
         $buf.$amp$plus(BoxesRunTime.boxToLong(logLength));
         $buf.$amp$plus(new Text("\n      "));
         Elem range = new Elem((String)null, "span", var47, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var48 = new UnprefixedAttribute("class", new Text("log-more-btn btn btn-secondary"), $md);
         var48 = new UnprefixedAttribute("onclick", "loadMore()", var48);
         var48 = new UnprefixedAttribute("type", new Text("button"), var48);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Load More\n      "));
         Elem moreButton = new Elem((String)null, "button", var48, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var51 = new UnprefixedAttribute("class", new Text("log-new-btn btn btn-secondary"), $md);
         var51 = new UnprefixedAttribute("onclick", "loadNew()", var51);
         var51 = new UnprefixedAttribute("type", new Text("button"), var51);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Load New\n      "));
         Elem newButton = new Elem((String)null, "button", var51, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var54 = new UnprefixedAttribute("style", new Text("display: none;"), $md);
         var54 = new UnprefixedAttribute("class", new Text("no-new-alert alert alert-info"), var54);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        End of Log\n      "));
         Elem alert = new Elem((String)null, "div", var54, var10005, false, var10007.seqToNodeSeq($buf));
         String logParams = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("/?logType=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{logType}));
         String jsOnload = "window.onload = initLogPage('" + logParams + "', " + curLogLength + ", " + startByte + ", " + endByte + ", " + logLength + ", " + byteLength + ");";
         Null $md = scala.xml.Null..MODULE$;
         MetaData var56 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         var56 = new UnprefixedAttribute("type", new Text("module"), var56);
         Elem var10000 = new Elem((String)null, "script", var56, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
         Null var63 = scala.xml.Null..MODULE$;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Logs at "));
         $buf.$amp$plus(this.logDir());
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(range);
         $buf.$amp$plus(new Text("\n        "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var58 = new UnprefixedAttribute("style", new Text("height:80vh; overflow:auto; padding:5px;"), $md);
         var58 = new UnprefixedAttribute("class", new Text("log-content"), var58);
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10023 = scala.xml.Null..MODULE$;
         TopScope var10024 = scala.xml.TopScope..MODULE$;
         NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(moreButton);
         $buf.$amp$plus(new Elem((String)null, "div", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10023 = scala.xml.Null..MODULE$;
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(logText);
         $buf.$amp$plus(new Elem((String)null, "pre", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(alert);
         $buf.$amp$plus(new Text("\n          "));
         var10023 = scala.xml.Null..MODULE$;
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(newButton);
         $buf.$amp$plus(new Elem((String)null, "div", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var58, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Null var10014 = scala.xml.Null..MODULE$;
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(jsOnload));
         $buf.$amp$plus(new Elem((String)null, "script", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         NodeSeq content = var10000.$plus$plus(new Elem((String)null, "div", var63, var10006, false, var10008.seqToNodeSeq($buf)));
         return UIUtils$.MODULE$.headerSparkPage(request, "Logs", () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
      } else {
         throw new MatchError(var7);
      }
   }

   public String renderLog(final HttpServletRequest request) {
      String logType = (String).MODULE$.apply(request.getParameter("logType")).getOrElse(() -> DriverLogger$.MODULE$.DRIVER_LOG_FILE());
      Option offset = .MODULE$.apply(request.getParameter("offset")).map((x$4) -> BoxesRunTime.boxToLong($anonfun$renderLog$2(x$4)));
      int byteLength = BoxesRunTime.unboxToInt(.MODULE$.apply(request.getParameter("byteLength")).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$renderLog$3(x$5))).getOrElse((JFunction0.mcI.sp)() -> this.defaultBytes()));
      Tuple4 var7 = this.getLog(this.logDir(), logType, offset, byteLength);
      if (var7 != null) {
         String logText = (String)var7._1();
         long startByte = BoxesRunTime.unboxToLong(var7._2());
         long endByte = BoxesRunTime.unboxToLong(var7._3());
         long logLength = BoxesRunTime.unboxToLong(var7._4());
         Tuple4 var6 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
         String logText = (String)var6._1();
         long startByte = BoxesRunTime.unboxToLong(var6._2());
         long endByte = BoxesRunTime.unboxToLong(var6._3());
         long logLength = BoxesRunTime.unboxToLong(var6._4());
         String pre = "==== Bytes " + startByte + "-" + endByte + " of " + logLength + " of " + this.logDir() + logType + " ====\n";
         return pre + logText;
      } else {
         throw new MatchError(var7);
      }
   }

   private Tuple4 getLog(final String logDirectory, final String logType, final Option offsetOption, final int byteLength) {
      if (!this.supportedLogTypes().contains(logType)) {
         return new Tuple4("Error: Log type must be one of " + this.supportedLogTypes().mkString(", "), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
      } else {
         Tuple4 var10000;
         try {
            Seq files = RollingFileAppender$.MODULE$.getSortedRolledOverFiles(logDirectory, logType);
            this.logDebug((Function0)(() -> "Sorted log files of type " + logType + " in " + logDirectory + ":\n" + files.mkString("\n")));
            Seq fileLengths = (Seq)files.map((x$7) -> BoxesRunTime.boxToLong($anonfun$getLog$2(this, x$7)));
            long totalLength = BoxesRunTime.unboxToLong(fileLengths.sum(scala.math.Numeric.LongIsIntegral..MODULE$));
            long offset = BoxesRunTime.unboxToLong(offsetOption.getOrElse((JFunction0.mcJ.sp)() -> totalLength - (long)byteLength));
            long startIndex = offset < 0L ? 0L : (offset > totalLength ? totalLength : offset);
            long endIndex = scala.math.package..MODULE$.min(startIndex + (long)byteLength, totalLength);
            this.logDebug((Function0)(() -> "Getting log from " + startIndex + " to " + endIndex));
            String logText = Utils$.MODULE$.offsetBytes(files, fileLengths, startIndex, endIndex);
            this.logDebug((Function0)(() -> "Got log of length " + logText.length() + " bytes"));
            var10000 = new Tuple4(logText, BoxesRunTime.boxToLong(startIndex), BoxesRunTime.boxToLong(endIndex), BoxesRunTime.boxToLong(totalLength));
         } catch (Exception var17) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error getting ", " logs from directory "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOG_TYPE..MODULE$, logType)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logDirectory)}))))), var17);
            var10000 = new Tuple4("Error getting logs due to exception: " + var17.getMessage(), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$render$2(final String x$1) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$3(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final long $anonfun$renderLog$2(final String x$4) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   public static final int $anonfun$renderLog$3(final String x$5) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$5));
   }

   // $FF: synthetic method
   public static final long $anonfun$getLog$2(final DriverLogPage $this, final File x$7) {
      return Utils$.MODULE$.getFileLength(x$7, $this.conf);
   }

   public DriverLogPage(final DriverLogTab parent, final SparkConf conf) {
      super("");
      this.parent = parent;
      this.conf = conf;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(((Option)conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LOCAL_DIR())).nonEmpty(), () -> "Please specify " + package$.MODULE$.DRIVER_LOG_LOCAL_DIR().key() + ".");
      this.supportedLogTypes = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{DriverLogger$.MODULE$.DRIVER_LOG_FILE(), "stderr", "stdout"})));
      this.defaultBytes = 102400;
      this.logDir = (String)((Option)conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LOCAL_DIR())).get();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
