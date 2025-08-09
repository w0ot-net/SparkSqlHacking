package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.json4s.Diff;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005E4a\u0001C\u0005\u0002\u0002%\u0019\u0002\"\u0002\u0013\u0001\t\u00031\u0003\"B\u0015\u0001\t#Q\u0003\"B\u001e\u0001\t#a\u0004\"\u0002*\u0001\t#\u0019\u0006\"B0\u0001\t#\u0001\u0007\"\u00024\u0001\t#9\u0007\"B7\u0001\t\u0013q'a\u0003*fgR\u001cVM\u001d<mKRT!AC\u0006\u0002\tI,7\u000f\u001e\u0006\u0003\u00195\ta\u0001Z3qY>L(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!b\u0004\u0005\u0002\u001695\taC\u0003\u0002\u00181\u0005!\u0001\u000e\u001e;q\u0015\tI\"$A\u0004tKJ4H.\u001a;\u000b\u0003m\tqA[1lCJ$\u0018-\u0003\u0002\u001e-\tY\u0001\n\u001e;q'\u0016\u0014h\u000f\\3u!\ty\"%D\u0001!\u0015\t\tS\"\u0001\u0005j]R,'O\\1m\u0013\t\u0019\u0003EA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012a\n\t\u0003Q\u0001i\u0011!C\u0001\rg\u0016tGMU3ta>t7/\u001a\u000b\u0004WE2\u0004C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#\u0001B+oSRDQA\r\u0002A\u0002M\nqB]3ta>t7/Z'fgN\fw-\u001a\t\u0003QQJ!!N\u0005\u00035M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\u001c*fgB|gn]3\t\u000b]\u0012\u0001\u0019\u0001\u001d\u0002\u001fI,7\u000f]8og\u0016\u001cVM\u001d<mKR\u0004\"!F\u001d\n\u0005i2\"a\u0005%uiB\u001cVM\u001d<mKR\u0014Vm\u001d9p]N,\u0017!\u00054j]\u0012,fn\u001b8po:4\u0015.\u001a7egR\u0019QhS'\u0011\u00071r\u0004)\u0003\u0002@[\t)\u0011I\u001d:bsB\u0011\u0011\t\u0013\b\u0003\u0005\u001a\u0003\"aQ\u0017\u000e\u0003\u0011S!!R\u0013\u0002\rq\u0012xn\u001c;?\u0013\t9U&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0013*\u0013aa\u0015;sS:<'BA$.\u0011\u0015a5\u00011\u0001A\u0003-\u0011X-];fgRT5o\u001c8\t\u000b9\u001b\u0001\u0019A(\u0002\u001dI,\u0017/^3ti6+7o]1hKB\u0011\u0001\u0006U\u0005\u0003#&\u0011\u0011dU;c[&$(+Z:u!J|Go\\2pY6+7o]1hK\u0006yam\u001c:nCR,\u0005pY3qi&|g\u000e\u0006\u0002A)\")Q\u000b\u0002a\u0001-\u0006\tQ\r\u0005\u0002X9:\u0011\u0001L\u0017\b\u0003\u0007fK\u0011AL\u0005\u000376\nq\u0001]1dW\u0006<W-\u0003\u0002^=\nIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u000376\n1\u0002[1oI2,WI\u001d:peR\u0011\u0011\r\u001a\t\u0003Q\tL!aY\u0005\u0003\u001b\u0015\u0013(o\u001c:SKN\u0004xN\\:f\u0011\u0015)W\u00011\u0001A\u0003\u001diWm]:bO\u0016\f\u0011\u0003]1sg\u0016\u001cVOY7jgNLwN\\%e)\tA7\u000eE\u0002-S\u0002K!A[\u0017\u0003\r=\u0003H/[8o\u0011\u0015ag\u00011\u0001A\u0003\u0011\u0001\u0018\r\u001e5\u0002!Y\fG.\u001b3bi\u0016\u0014Vm\u001d9p]N,GcA\u001apa\")!g\u0002a\u0001g!)qg\u0002a\u0001q\u0001"
)
public abstract class RestServlet extends HttpServlet implements Logging {
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

   public void sendResponse(final SubmitRestProtocolResponse responseMessage, final HttpServletResponse responseServlet) {
      SubmitRestProtocolResponse message = this.validateResponse(responseMessage, responseServlet);
      responseServlet.setContentType("application/json");
      responseServlet.setCharacterEncoding("utf-8");
      responseServlet.getWriter().write(message.toJson());
   }

   public String[] findUnknownFields(final String requestJson, final SubmitRestProtocolMessage requestMessage) {
      JValue clientSideJson = .MODULE$.parse(requestJson, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      JValue serverSideJson = .MODULE$.parse(requestMessage.toJson(), .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      Diff var8 = clientSideJson.diff(serverSideJson);
      if (var8 != null) {
         JValue unknown = var8.deleted();
         if (unknown instanceof JObject) {
            JObject var11 = (JObject)unknown;
            return (String[])var11.obj().map((x0$1) -> {
               if (x0$1 != null) {
                  String k = (String)x0$1._1();
                  return k;
               } else {
                  throw new MatchError(x0$1);
               }
            }).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
         } else {
            return (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
         }
      } else {
         throw new MatchError(var8);
      }
   }

   public String formatException(final Throwable e) {
      String stackTraceString = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])e.getStackTrace()), (x$3) -> "\t" + x$3, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("\n");
      return e + "\n" + stackTraceString;
   }

   public ErrorResponse handleError(final String message) {
      ErrorResponse e = new ErrorResponse();
      e.serverSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      e.message_$eq(message);
      return e;
   }

   public Option parseSubmissionId(final String path) {
      return (Option)(path != null && !path.isEmpty() ? scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(path), "/").split("/"))).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$parseSubmissionId$1(x$4))) : scala.None..MODULE$);
   }

   private SubmitRestProtocolResponse validateResponse(final SubmitRestProtocolResponse responseMessage, final HttpServletResponse responseServlet) {
      Object var10000;
      try {
         responseMessage.validate();
         var10000 = responseMessage;
      } catch (Exception var4) {
         responseServlet.setStatus(500);
         var10000 = this.handleError("Internal server error: " + this.formatException(var4));
      }

      return (SubmitRestProtocolResponse)var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseSubmissionId$1(final String x$4) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   public RestServlet() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
