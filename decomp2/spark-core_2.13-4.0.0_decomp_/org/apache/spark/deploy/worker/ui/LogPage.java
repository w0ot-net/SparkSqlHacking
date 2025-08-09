package org.apache.spark.deploy.worker.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.spark.deploy.worker.Worker;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.logging.RollingFileAppender$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
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
   bytes = "\u0006\u0005\u0005Mb!\u0002\b\u0010\u0001=Y\u0002\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u000b5\u0002A\u0011\u0001\u0018\t\u000fI\u0001!\u0019!C\u0005c!1a\u0007\u0001Q\u0001\nIBqa\u000e\u0001C\u0002\u0013%\u0001\b\u0003\u0004B\u0001\u0001\u0006I!\u000f\u0005\b\u0005\u0002\u0011\r\u0011\"\u0003D\u0011\u0019!\u0006\u0001)A\u0005\t\"9Q\u000b\u0001b\u0001\n\u00131\u0006BB.\u0001A\u0003%q\u000bC\u0003]\u0001\u0011\u0005Q\fC\u0003u\u0001\u0011\u0005Q\u000fC\u0004\u0002\u000e\u0001!I!a\u0004\u0003\u000f1{w\rU1hK*\u0011\u0001#E\u0001\u0003k&T!AE\n\u0002\r]|'o[3s\u0015\t!R#\u0001\u0004eKBdw.\u001f\u0006\u0003-]\tQa\u001d9be.T!\u0001G\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0012aA8sON\u0019\u0001\u0001H\u0011\u0011\u0005uyR\"\u0001\u0010\u000b\u0005A)\u0012B\u0001\u0011\u001f\u0005%9VMY+J!\u0006<W\r\u0005\u0002#K5\t1E\u0003\u0002%+\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002'G\t9Aj\\4hS:<\u0017A\u00029be\u0016tGo\u0001\u0001\u0011\u0005)ZS\"A\b\n\u00051z!aC,pe.,'oV3c+&\u000ba\u0001P5oSRtDCA\u00181!\tQ\u0003\u0001C\u0003(\u0005\u0001\u0007\u0011&F\u00013!\t\u0019D'D\u0001\u0012\u0013\t)\u0014C\u0001\u0004X_J\\WM]\u0001\bo>\u00148.\u001a:!\u0003\u001d9xN]6ESJ,\u0012!\u000f\t\u0003u}j\u0011a\u000f\u0006\u0003yu\n!![8\u000b\u0003y\nAA[1wC&\u0011\u0001i\u000f\u0002\u0005\r&dW-\u0001\u0005x_J\\G)\u001b:!\u0003E\u0019X\u000f\u001d9peR,G\rT8h)f\u0004Xm]\u000b\u0002\tB\u0019Q\t\u0014(\u000e\u0003\u0019S!a\u0012%\u0002\u0013%lW.\u001e;bE2,'BA%K\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0017\u0006)1oY1mC&\u0011QJ\u0012\u0002\u0004'\u0016$\bCA(S\u001b\u0005\u0001&BA)>\u0003\u0011a\u0017M\\4\n\u0005M\u0003&AB*ue&tw-\u0001\ntkB\u0004xN\u001d;fI2{w\rV=qKN\u0004\u0013\u0001\u00043fM\u0006,H\u000e\u001e\"zi\u0016\u001cX#A,\u0011\u0005aKV\"\u0001&\n\u0005iS%aA%oi\u0006iA-\u001a4bk2$()\u001f;fg\u0002\n\u0011B]3oI\u0016\u0014Hj\\4\u0015\u0005yC\u0007CA0g\u001d\t\u0001G\r\u0005\u0002b\u00156\t!M\u0003\u0002dQ\u00051AH]8pizJ!!\u001a&\u0002\rA\u0013X\rZ3g\u0013\t\u0019vM\u0003\u0002f\u0015\")\u0011n\u0003a\u0001U\u00069!/Z9vKN$\bCA6s\u001b\u0005a'BA7o\u0003\u0011AG\u000f\u001e9\u000b\u0005=\u0004\u0018aB:feZdW\r\u001e\u0006\u0002c\u00069!.Y6beR\f\u0017BA:m\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;\u0002\rI,g\u000eZ3s)\r1\u00181\u0002\t\u0004or|hB\u0001={\u001d\t\t\u00170C\u0001L\u0013\tY(*A\u0004qC\u000e\\\u0017mZ3\n\u0005ut(aA*fc*\u00111P\u0013\t\u0005\u0003\u0003\t9!\u0004\u0002\u0002\u0004)\u0019\u0011Q\u0001&\u0002\u0007alG.\u0003\u0003\u0002\n\u0005\r!\u0001\u0002(pI\u0016DQ!\u001b\u0007A\u0002)\faaZ3u\u0019><GCCA\t\u0003;\t\t#!\n\u00020AQ\u0001,a\u0005_\u0003/\t9\"a\u0006\n\u0007\u0005U!J\u0001\u0004UkBdW\r\u000e\t\u00041\u0006e\u0011bAA\u000e\u0015\n!Aj\u001c8h\u0011\u0019\ty\"\u0004a\u0001=\u0006aAn\\4ESJ,7\r^8ss\"1\u00111E\u0007A\u0002y\u000bq\u0001\\8h)f\u0004X\rC\u0004\u0002(5\u0001\r!!\u000b\u0002\u0019=4gm]3u\u001fB$\u0018n\u001c8\u0011\u000ba\u000bY#a\u0006\n\u0007\u00055\"J\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003ci\u0001\u0019A,\u0002\u0015\tLH/\u001a'f]\u001e$\b\u000e"
)
public class LogPage extends WebUIPage implements Logging {
   private final Worker worker;
   private final File workDir;
   private final Set supportedLogTypes;
   private final int defaultBytes;
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

   private Worker worker() {
      return this.worker;
   }

   private File workDir() {
      return this.workDir;
   }

   private Set supportedLogTypes() {
      return this.supportedLogTypes;
   }

   private int defaultBytes() {
      return this.defaultBytes;
   }

   public String renderLog(final HttpServletRequest request) {
      String logType;
      Option offset;
      int byteLength;
      String var10000;
      label65: {
         Option appId = .MODULE$.apply(request.getParameter("appId"));
         Option executorId = .MODULE$.apply(request.getParameter("executorId"));
         Option driverId = .MODULE$.apply(request.getParameter("driverId"));
         Option self = .MODULE$.apply(request.getParameter("self"));
         logType = request.getParameter("logType");
         offset = .MODULE$.apply(request.getParameter("offset")).map((x$1) -> BoxesRunTime.boxToLong($anonfun$renderLog$1(x$1)));
         byteLength = BoxesRunTime.unboxToInt(.MODULE$.apply(request.getParameter("byteLength")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$renderLog$2(x$2))).getOrElse((JFunction0.mcI.sp)() -> this.defaultBytes()));
         Tuple4 var12 = new Tuple4(appId, executorId, driverId, self);
         if (var12 != null) {
            Option var13 = (Option)var12._1();
            Option var14 = (Option)var12._2();
            Option var15 = (Option)var12._3();
            Option var16 = (Option)var12._4();
            if (var13 instanceof Some) {
               Some var17 = (Some)var13;
               String a = (String)var17.value();
               if (var14 instanceof Some) {
                  Some var19 = (Some)var14;
                  String e = (String)var19.value();
                  if (scala.None..MODULE$.equals(var15) && scala.None..MODULE$.equals(var16)) {
                     var10000 = this.workDir().getPath() + "/" + a + "/" + e + "/";
                     break label65;
                  }
               }
            }
         }

         if (var12 != null) {
            Option var21 = (Option)var12._1();
            Option var22 = (Option)var12._2();
            Option var23 = (Option)var12._3();
            Option var24 = (Option)var12._4();
            if (scala.None..MODULE$.equals(var21) && scala.None..MODULE$.equals(var22) && var23 instanceof Some) {
               Some var25 = (Some)var23;
               String d = (String)var25.value();
               if (scala.None..MODULE$.equals(var24)) {
                  var10000 = this.workDir().getPath() + "/" + d + "/";
                  break label65;
               }
            }
         }

         if (var12 == null) {
            throw new Exception("Request must specify either application or driver identifiers");
         }

         Option var27 = (Option)var12._1();
         Option var28 = (Option)var12._2();
         Option var29 = (Option)var12._3();
         Option var30 = (Option)var12._4();
         if (!scala.None..MODULE$.equals(var27) || !scala.None..MODULE$.equals(var28) || !scala.None..MODULE$.equals(var29) || !(var30 instanceof Some)) {
            throw new Exception("Request must specify either application or driver identifiers");
         }

         var10000 = scala.sys.package..MODULE$.env().getOrElse("SPARK_LOG_DIR", () -> this.workDir().getPath()) + "/";
      }

      String logDir = var10000;
      Tuple4 var32 = this.getLog(logDir, logType, offset, byteLength);
      if (var32 != null) {
         String logText = (String)var32._1();
         long startByte = BoxesRunTime.unboxToLong(var32._2());
         long endByte = BoxesRunTime.unboxToLong(var32._3());
         long logLength = BoxesRunTime.unboxToLong(var32._4());
         Tuple4 var31 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
         String logText = (String)var31._1();
         long startByte = BoxesRunTime.unboxToLong(var31._2());
         long endByte = BoxesRunTime.unboxToLong(var31._3());
         long logLength = BoxesRunTime.unboxToLong(var31._4());
         String pre = "==== Bytes " + startByte + "-" + endByte + " of " + logLength + " of " + logDir + logType + " ====\n";
         return pre + logText;
      } else {
         throw new MatchError(var32);
      }
   }

   public Seq render(final HttpServletRequest request) {
      String logType;
      Option offset;
      int byteLength;
      Tuple3 var10000;
      label71: {
         Option appId = .MODULE$.apply(request.getParameter("appId"));
         Option executorId = .MODULE$.apply(request.getParameter("executorId"));
         Option driverId = .MODULE$.apply(request.getParameter("driverId"));
         Option self = .MODULE$.apply(request.getParameter("self"));
         logType = request.getParameter("logType");
         offset = .MODULE$.apply(request.getParameter("offset")).map((x$4) -> BoxesRunTime.boxToLong($anonfun$render$1(x$4)));
         byteLength = BoxesRunTime.unboxToInt(.MODULE$.apply(request.getParameter("byteLength")).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$render$2(x$5))).getOrElse((JFunction0.mcI.sp)() -> this.defaultBytes()));
         Tuple4 var14 = new Tuple4(appId, executorId, driverId, self);
         if (var14 != null) {
            Option var15 = (Option)var14._1();
            Option var16 = (Option)var14._2();
            Option var17 = (Option)var14._3();
            Option var18 = (Option)var14._4();
            if (var15 instanceof Some) {
               Some var19 = (Some)var15;
               String a = (String)var19.value();
               if (var16 instanceof Some) {
                  Some var21 = (Some)var16;
                  String e = (String)var21.value();
                  if (scala.None..MODULE$.equals(var17) && scala.None..MODULE$.equals(var18)) {
                     var10000 = new Tuple3(this.workDir().getPath() + "/" + a + "/" + e + "/", "appId=" + a + "&executorId=" + e, a + "/" + e);
                     break label71;
                  }
               }
            }
         }

         if (var14 != null) {
            Option var23 = (Option)var14._1();
            Option var24 = (Option)var14._2();
            Option var25 = (Option)var14._3();
            Option var26 = (Option)var14._4();
            if (scala.None..MODULE$.equals(var23) && scala.None..MODULE$.equals(var24) && var25 instanceof Some) {
               Some var27 = (Some)var25;
               String d = (String)var27.value();
               if (scala.None..MODULE$.equals(var26)) {
                  var10000 = new Tuple3(this.workDir().getPath() + "/" + d + "/", "driverId=" + d, d);
                  break label71;
               }
            }
         }

         if (var14 == null) {
            throw new Exception("Request must specify either application or driver identifiers");
         }

         Option var29 = (Option)var14._1();
         Option var30 = (Option)var14._2();
         Option var31 = (Option)var14._3();
         Option var32 = (Option)var14._4();
         if (!scala.None..MODULE$.equals(var29) || !scala.None..MODULE$.equals(var30) || !scala.None..MODULE$.equals(var31) || !(var32 instanceof Some)) {
            throw new Exception("Request must specify either application or driver identifiers");
         }

         var10000 = new Tuple3(scala.sys.package..MODULE$.env().getOrElse("SPARK_LOG_DIR", () -> this.workDir().getPath()) + "/", "self", "worker");
      }

      Tuple3 var13 = var10000;
      if (var13 != null) {
         String logDir = (String)var13._1();
         String params = (String)var13._2();
         String pageName = (String)var13._3();
         Tuple3 var12 = new Tuple3(logDir, params, pageName);
         String logDir = (String)var12._1();
         String params = (String)var12._2();
         String pageName = (String)var12._3();
         Tuple4 var40 = this.getLog(logDir, logType, offset, byteLength);
         if (var40 != null) {
            String logText = (String)var40._1();
            long startByte = BoxesRunTime.unboxToLong(var40._2());
            long endByte = BoxesRunTime.unboxToLong(var40._3());
            long logLength = BoxesRunTime.unboxToLong(var40._4());
            Tuple4 var39 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
            String logText = (String)var39._1();
            long startByte = BoxesRunTime.unboxToLong(var39._2());
            long endByte = BoxesRunTime.unboxToLong(var39._3());
            long logLength = BoxesRunTime.unboxToLong(var39._4());
            Null var10004 = scala.xml.Null..MODULE$;
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null $md = scala.xml.Null..MODULE$;
            MetaData var84 = new UnprefixedAttribute("href", this.worker().activeMasterWebUiUrl(), $md);
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Back to Master"));
            $buf.$amp$plus(new Elem((String)null, "a", var84, var10014, false, var10016.seqToNodeSeq($buf)));
            Elem linkToMaster = new Elem((String)null, "p", var10004, var10005, false, var10007.seqToNodeSeq($buf));
            long curLogLength = endByte - startByte;
            Null $md = scala.xml.Null..MODULE$;
            MetaData var85 = new UnprefixedAttribute("id", new Text("log-data"), $md);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
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
            Elem range = new Elem((String)null, "span", var85, var10005, false, var10007.seqToNodeSeq($buf));
            Null $md = scala.xml.Null..MODULE$;
            MetaData var86 = new UnprefixedAttribute("class", new Text("log-more-btn btn btn-secondary"), $md);
            var86 = new UnprefixedAttribute("onclick", "loadMore()", var86);
            var86 = new UnprefixedAttribute("type", new Text("button"), var86);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n        Load More\n      "));
            Elem moreButton = new Elem((String)null, "button", var86, var10005, false, var10007.seqToNodeSeq($buf));
            Null $md = scala.xml.Null..MODULE$;
            MetaData var89 = new UnprefixedAttribute("class", new Text("log-new-btn btn btn-secondary"), $md);
            var89 = new UnprefixedAttribute("onclick", "loadNew()", var89);
            var89 = new UnprefixedAttribute("type", new Text("button"), var89);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n        Load New\n      "));
            Elem newButton = new Elem((String)null, "button", var89, var10005, false, var10007.seqToNodeSeq($buf));
            Null $md = scala.xml.Null..MODULE$;
            MetaData var92 = new UnprefixedAttribute("style", new Text("display: none;"), $md);
            var92 = new UnprefixedAttribute("class", new Text("no-new-alert alert alert-info"), var92);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n        End of Log\n      "));
            Elem alert = new Elem((String)null, "div", var92, var10005, false, var10007.seqToNodeSeq($buf));
            String logParams = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("?%s&logType=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{params, logType}));
            String jsOnload = "window.onload = initLogPage('" + logParams + "', " + curLogLength + ", " + startByte + ", " + endByte + ", " + logLength + ", " + byteLength + ");";
            Null $md = scala.xml.Null..MODULE$;
            MetaData var94 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
            var94 = new UnprefixedAttribute("type", new Text("module"), var94);
            Elem var98 = new Elem((String)null, "script", var94, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
            Null var103 = scala.xml.Null..MODULE$;
            TopScope var10006 = scala.xml.TopScope..MODULE$;
            NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n        "));
            $buf.$amp$plus(linkToMaster);
            $buf.$amp$plus(new Text("\n        "));
            $buf.$amp$plus(range);
            $buf.$amp$plus(new Text("\n        "));
            Null $md = scala.xml.Null..MODULE$;
            MetaData var96 = new UnprefixedAttribute("style", new Text("height:80vh; overflow:auto; padding:5px;"), $md);
            var96 = new UnprefixedAttribute("class", new Text("log-content"), var96);
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
            $buf.$amp$plus(new Elem((String)null, "div", var96, var10015, false, var10017.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Null var108 = scala.xml.Null..MODULE$;
            var10015 = scala.xml.TopScope..MODULE$;
            var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(jsOnload));
            $buf.$amp$plus(new Elem((String)null, "script", var108, var10015, false, var10017.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n      "));
            NodeSeq content = var98.$plus$plus(new Elem((String)null, "div", var103, var10006, false, var10008.seqToNodeSeq($buf)));
            return UIUtils$.MODULE$.basicSparkPage(request, () -> content, logType + " log page for " + pageName, UIUtils$.MODULE$.basicSparkPage$default$4());
         } else {
            throw new MatchError(var40);
         }
      } else {
         throw new MatchError(var13);
      }
   }

   private Tuple4 getLog(final String logDirectory, final String logType, final Option offsetOption, final int byteLength) {
      if (!this.supportedLogTypes().contains(logType)) {
         return new Tuple4("Error: Log type must be one of " + this.supportedLogTypes().mkString(", "), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
      } else {
         URI normalizedUri = (new File(logDirectory)).toURI().normalize();
         File normalizedLogDir = new File(normalizedUri.getPath());
         if (!Utils$.MODULE$.isInDirectory(this.workDir(), normalizedLogDir)) {
            return new Tuple4("Error: invalid log directory " + logDirectory, BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
         } else {
            Tuple4 var10000;
            try {
               String fileName = logType.equals("out") ? (String)scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])normalizedLogDir.listFiles()), (x$8) -> x$8.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$getLog$2(x$9))))).getOrElse(() -> logType) : logType;
               Seq files = RollingFileAppender$.MODULE$.getSortedRolledOverFiles(logDirectory, fileName);
               this.logDebug((Function0)(() -> "Sorted log files of type " + logType + " in " + logDirectory + ":\n" + files.mkString("\n")));
               Seq fileLengths = (Seq)files.map((x$10) -> BoxesRunTime.boxToLong($anonfun$getLog$5(this, x$10)));
               long totalLength = BoxesRunTime.unboxToLong(fileLengths.sum(scala.math.Numeric.LongIsIntegral..MODULE$));
               long offset = BoxesRunTime.unboxToLong(offsetOption.getOrElse((JFunction0.mcJ.sp)() -> totalLength - (long)byteLength));
               long startIndex = offset < 0L ? 0L : (offset > totalLength ? totalLength : offset);
               long endIndex = scala.math.package..MODULE$.min(startIndex + (long)byteLength, totalLength);
               this.logDebug((Function0)(() -> "Getting log from " + startIndex + " to " + endIndex));
               String logText = Utils$.MODULE$.offsetBytes(files, fileLengths, startIndex, endIndex);
               this.logDebug((Function0)(() -> "Got log of length " + logText.length() + " bytes"));
               var10000 = new Tuple4(logText, BoxesRunTime.boxToLong(startIndex), BoxesRunTime.boxToLong(endIndex), BoxesRunTime.boxToLong(totalLength));
            } catch (Exception var20) {
               this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error getting ", " logs from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOG_TYPE..MODULE$, logType)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logDirectory)}))))), var20);
               var10000 = new Tuple4("Error getting logs due to exception: " + var20.getMessage(), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
            }

            return var10000;
         }
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$renderLog$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$renderLog$2(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final long $anonfun$render$1(final String x$4) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$2(final String x$5) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$5));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLog$2(final String x$9) {
      return x$9.endsWith(".out");
   }

   // $FF: synthetic method
   public static final long $anonfun$getLog$5(final LogPage $this, final File x$10) {
      return Utils$.MODULE$.getFileLength(x$10, $this.worker().conf());
   }

   public LogPage(final WorkerWebUI parent) {
      super("logPage");
      Logging.$init$(this);
      this.worker = parent.worker();
      this.workDir = new File(parent.workDir().toURI().normalize().getPath());
      this.supportedLogTypes = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"stderr", "stdout", "out"})));
      this.defaultBytes = 102400;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
