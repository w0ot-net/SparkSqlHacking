package org.apache.spark.deploy.history;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.Utils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple4;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.sys.package.;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q\u0001B\u0003\u0001\u000b=A\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006E\u0001!\ta\t\u0005\u0006O\u0001!\t\u0001\u000b\u0002\b\u0019><\u0007+Y4f\u0015\t1q!A\u0004iSN$xN]=\u000b\u0005!I\u0011A\u00023fa2|\u0017P\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0003'%\t!!^5\n\u0005U\u0011\"!C,fEVK\u0005+Y4f!\t9\"$D\u0001\u0019\u0015\tI\u0012\"\u0001\u0005j]R,'O\\1m\u0013\tY\u0002DA\u0004M_\u001e<\u0017N\\4\u0002\t\r|gNZ\u0002\u0001!\ty\u0002%D\u0001\n\u0013\t\t\u0013BA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"\"\u0001\n\u0014\u0011\u0005\u0015\u0002Q\"A\u0003\t\u000bq\u0011\u0001\u0019\u0001\u0010\u0002\rI,g\u000eZ3s)\tIS\bE\u0002+i]r!aK\u0019\u000f\u00051zS\"A\u0017\u000b\u00059j\u0012A\u0002\u001fs_>$h(C\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u0003AJ!!\u000e\u001c\u0003\u0007M+\u0017O\u0003\u00023gA\u0011\u0001hO\u0007\u0002s)\u0011!hM\u0001\u0004q6d\u0017B\u0001\u001f:\u0005\u0011qu\u000eZ3\t\u000by\u001a\u0001\u0019A \u0002\u000fI,\u0017/^3tiB\u0011\u0001iR\u0007\u0002\u0003*\u0011!iQ\u0001\u0005QR$\bO\u0003\u0002E\u000b\u000691/\u001a:wY\u0016$(\"\u0001$\u0002\u000f)\f7.\u0019:uC&\u0011\u0001*\u0011\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH\u000f"
)
public class LogPage extends WebUIPage implements Logging {
   private final SparkConf conf;
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

   public Seq render(final HttpServletRequest request) {
      String logDir = (String).MODULE$.env().getOrElse("SPARK_LOG_DIR", () -> "logs/");
      String logType = request.getParameter("logType");
      Option offset = scala.Option..MODULE$.apply(request.getParameter("offset")).map((x$1) -> BoxesRunTime.boxToLong($anonfun$render$2(x$1)));
      int byteLength = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter("byteLength")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$render$3(x$2))).getOrElse((JFunction0.mcI.sp)() -> Utils$.MODULE$.DEFAULT_BYTES()));
      Tuple4 var8 = Utils$.MODULE$.getLog(this.conf, logDir, logType, offset, byteLength);
      if (var8 != null) {
         String logText = (String)var8._1();
         long startByte = BoxesRunTime.unboxToLong(var8._2());
         long endByte = BoxesRunTime.unboxToLong(var8._3());
         long logLength = BoxesRunTime.unboxToLong(var8._4());
         Tuple4 var7 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
         String logText = (String)var7._1();
         long startByte = BoxesRunTime.unboxToLong(var7._2());
         long endByte = BoxesRunTime.unboxToLong(var7._3());
         long logLength = BoxesRunTime.unboxToLong(var7._4());
         long curLogLength = endByte - startByte;
         Null $md = scala.xml.Null..MODULE$;
         MetaData var51 = new UnprefixedAttribute("id", new Text("log-data"), $md);
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
         Elem range = new Elem((String)null, "span", var51, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var52 = new UnprefixedAttribute("class", new Text("log-more-btn btn btn-secondary"), $md);
         var52 = new UnprefixedAttribute("onclick", "loadMore()", var52);
         var52 = new UnprefixedAttribute("type", new Text("button"), var52);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Load More\n      "));
         Elem moreButton = new Elem((String)null, "button", var52, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var55 = new UnprefixedAttribute("class", new Text("log-new-btn btn btn-secondary"), $md);
         var55 = new UnprefixedAttribute("onclick", "loadNew()", var55);
         var55 = new UnprefixedAttribute("type", new Text("button"), var55);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Load New\n      "));
         Elem newButton = new Elem((String)null, "button", var55, var10005, false, var10007.seqToNodeSeq($buf));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var58 = new UnprefixedAttribute("style", new Text("display: none;"), $md);
         var58 = new UnprefixedAttribute("class", new Text("no-new-alert alert alert-info"), var58);
         var10005 = scala.xml.TopScope..MODULE$;
         var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        End of Log\n      "));
         Elem alert = new Elem((String)null, "div", var58, var10005, false, var10007.seqToNodeSeq($buf));
         String logParams = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("?self&logType=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{logType}));
         String jsOnload = "window.onload = initLogPage('" + logParams + "', " + curLogLength + ", " + startByte + ", " + endByte + ", " + logLength + ", " + byteLength + ");";
         Null $md = scala.xml.Null..MODULE$;
         MetaData var60 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         var60 = new UnprefixedAttribute("type", new Text("module"), var60);
         Elem var10000 = new Elem((String)null, "script", var60, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
         Null var68 = scala.xml.Null..MODULE$;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Null var10014 = scala.xml.Null..MODULE$;
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null $md = scala.xml.Null..MODULE$;
         MetaData var62 = new UnprefixedAttribute("href", new Text("/"), $md);
         TopScope var10024 = scala.xml.TopScope..MODULE$;
         NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Back to Main page"));
         $buf.$amp$plus(new Elem((String)null, "a", var62, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "p", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(range);
         $buf.$amp$plus(new Text("\n        "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var63 = new UnprefixedAttribute("style", new Text("height:80vh; overflow:auto; padding:5px;"), $md);
         var63 = new UnprefixedAttribute("class", new Text("log-content"), var63);
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10023 = scala.xml.Null..MODULE$;
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
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
         $buf.$amp$plus(new Elem((String)null, "div", var63, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10014 = scala.xml.Null..MODULE$;
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(jsOnload));
         $buf.$amp$plus(new Elem((String)null, "script", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         NodeSeq content = var10000.$plus$plus(new Elem((String)null, "div", var68, var10006, false, var10008.seqToNodeSeq($buf)));
         return UIUtils$.MODULE$.basicSparkPage(request, () -> content, logType + " log page for history server", UIUtils$.MODULE$.basicSparkPage$default$4());
      } else {
         throw new MatchError(var8);
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

   public LogPage(final SparkConf conf) {
      super("logPage");
      this.conf = conf;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
