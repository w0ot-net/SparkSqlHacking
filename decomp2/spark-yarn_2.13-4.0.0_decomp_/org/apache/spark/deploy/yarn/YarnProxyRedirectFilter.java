package org.apache.spark.deploy.yarn;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000514Aa\u0003\u0007\u0001/!)a\u0006\u0001C\u0001_!)!\u0007\u0001C!g!)!\b\u0001C!w!)\u0011\t\u0001C!\u0005\")!\u000b\u0001C\u0005'\u001e1q\f\u0004E\u0001!\u00014aa\u0003\u0007\t\u0002A\t\u0007\"\u0002\u0018\b\t\u0003)\u0007b\u00024\b\u0005\u0004%\ta\u001a\u0005\u0007W\u001e\u0001\u000b\u0011\u00025\u0003/e\u000b'O\u001c)s_bL(+\u001a3je\u0016\u001cGOR5mi\u0016\u0014(BA\u0007\u000f\u0003\u0011I\u0018M\u001d8\u000b\u0005=\u0001\u0012A\u00023fa2|\u0017P\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\r!QA\u0011\u0011DH\u0007\u00025)\u00111\u0004H\u0001\u0005Y\u0006twMC\u0001\u001e\u0003\u0011Q\u0017M^1\n\u0005}Q\"AB(cU\u0016\u001cG\u000f\u0005\u0002\"M5\t!E\u0003\u0002$I\u000591/\u001a:wY\u0016$(\"A\u0013\u0002\u000f)\f7.\u0019:uC&\u0011qE\t\u0002\u0007\r&dG/\u001a:\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-\u0002\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u00055R#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\u0002\"!\r\u0001\u000e\u00031\tq\u0001Z3tiJ|\u0017\u0010F\u00015!\t)\u0004(D\u00017\u0015\u00059\u0014!B:dC2\f\u0017BA\u001d7\u0005\u0011)f.\u001b;\u0002\t%t\u0017\u000e\u001e\u000b\u0003iqBQ!P\u0002A\u0002y\naaY8oM&<\u0007CA\u0011@\u0013\t\u0001%E\u0001\u0007GS2$XM]\"p]\u001aLw-\u0001\u0005e_\u001aKG\u000e^3s)\u0011!4\tS'\t\u000b\u0011#\u0001\u0019A#\u0002\u0007I,\u0017\u000f\u0005\u0002\"\r&\u0011qI\t\u0002\u000f'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0011\u0015IE\u00011\u0001K\u0003\r\u0011Xm\u001d\t\u0003C-K!\u0001\u0014\u0012\u0003\u001fM+'O\u001e7fiJ+7\u000f]8og\u0016DQA\u0014\u0003A\u0002=\u000bQa\u00195bS:\u0004\"!\t)\n\u0005E\u0013#a\u0003$jYR,'o\u00115bS:\f!\u0002Z8SK\u0012L'/Z2u)\r!Dk\u0017\u0005\u0006\t\u0016\u0001\r!\u0016\t\u0003-fk\u0011a\u0016\u0006\u00031\n\nA\u0001\u001b;ua&\u0011!l\u0016\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH\u000fC\u0003J\u000b\u0001\u0007A\f\u0005\u0002W;&\u0011al\u0016\u0002\u0014\u0011R$\boU3sm2,GOU3ta>t7/Z\u0001\u00183\u0006\u0014h\u000e\u0015:pqf\u0014V\rZ5sK\u000e$h)\u001b7uKJ\u0004\"!M\u0004\u0014\u0005\u001d\u0011\u0007CA\u001bd\u0013\t!gG\u0001\u0004B]f\u0014VM\u001a\u000b\u0002A\u0006Y1iT(L\u0013\u0016{f*Q'F+\u0005A\u0007CA\rj\u0013\tQ'D\u0001\u0004TiJLgnZ\u0001\r\u0007>{5*S#`\u001d\u0006kU\t\t"
)
public class YarnProxyRedirectFilter implements Filter, Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String COOKIE_NAME() {
      return YarnProxyRedirectFilter$.MODULE$.COOKIE_NAME();
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

   public void destroy() {
   }

   public void init(final FilterConfig config) {
   }

   public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
      HttpServletRequest hreq = (HttpServletRequest)req;
      Option var6 = .MODULE$.apply(hreq.getCookies()).flatMap((x$1) -> scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$1), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$doFilter$2(x$2))));
      if (var6 instanceof Some) {
         this.doRedirect(hreq, (HttpServletResponse)res);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         chain.doFilter(req, res);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private void doRedirect(final HttpServletRequest req, final HttpServletResponse res) {
      String redirect = req.getRequestURL().toString();
      String content = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |<html xmlns=\"http://www.w3.org/1999/xhtml\">\n      |<head>\n      |  <title>Spark History Server Redirect</title>\n      |  <meta http-equiv=\"refresh\" content=\"0;URL='" + redirect + "'\" />\n      |</head>\n      |<body>\n      |  <p>The requested page can be found at: <a href=\"" + redirect + "\">" + redirect + "</a>.</p>\n      |</body>\n      |</html>\n      "));
      this.logDebug((Function0)(() -> "Redirecting YARN proxy request to " + redirect + "."));
      res.setStatus(200);
      res.setContentType("text/html");
      res.getWriter().write(content);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doFilter$2(final Cookie x$2) {
      boolean var2;
      label23: {
         String var10000 = x$2.getName();
         String var1 = YarnProxyRedirectFilter$.MODULE$.COOKIE_NAME();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public YarnProxyRedirectFilter() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
