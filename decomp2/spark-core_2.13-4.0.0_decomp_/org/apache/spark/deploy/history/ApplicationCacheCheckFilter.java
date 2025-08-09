package org.apache.spark.deploy.history;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3QAB\u0004\u0001\u000fEA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!A!\u0007\u0001B\u0001B\u0003%1\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003=\u0001\u0011\u0005SHA\u000eBaBd\u0017nY1uS>t7)Y2iK\u000eCWmY6GS2$XM\u001d\u0006\u0003\u0011%\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\u000b\u0017\u00051A-\u001a9m_fT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0005\u0001IQ\"\u0005\u0005\u0002\u001415\tAC\u0003\u0002\u0016-\u0005!A.\u00198h\u0015\u00059\u0012\u0001\u00026bm\u0006L!!\u0007\u000b\u0003\r=\u0013'.Z2u!\tY\u0002%D\u0001\u001d\u0015\tib$A\u0004tKJ4H.\u001a;\u000b\u0003}\tqA[1lCJ$\u0018-\u0003\u0002\"9\t1a)\u001b7uKJ\u0004\"a\t\u0014\u000e\u0003\u0011R!!J\u0006\u0002\u0011%tG/\u001a:oC2L!a\n\u0013\u0003\u000f1{wmZ5oO\u0006\u00191.Z=\u0004\u0001A\u00111\u0006L\u0007\u0002\u000f%\u0011Qf\u0002\u0002\t\u0007\u0006\u001c\u0007.Z&fs\u0006AAn\\1eK\u0012,\u0016\n\u0005\u0002,a%\u0011\u0011g\u0002\u0002\f\u0019>\fG-\u001a3BaB,\u0016*A\u0003dC\u000eDW\r\u0005\u0002,i%\u0011Qg\u0002\u0002\u0011\u0003B\u0004H.[2bi&|gnQ1dQ\u0016\fa\u0001P5oSRtD\u0003\u0002\u001d:um\u0002\"a\u000b\u0001\t\u000b!\"\u0001\u0019\u0001\u0016\t\u000b9\"\u0001\u0019A\u0018\t\u000bI\"\u0001\u0019A\u001a\u0002\u0011\u0011|g)\u001b7uKJ$BA\u0010#J\u001dB\u0011qHQ\u0007\u0002\u0001*\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\n!QK\\5u\u0011\u0015)U\u00011\u0001G\u0003\u001d\u0011X-];fgR\u0004\"aG$\n\u0005!c\"AD*feZdW\r\u001e*fcV,7\u000f\u001e\u0005\u0006\u0015\u0016\u0001\raS\u0001\te\u0016\u001c\bo\u001c8tKB\u00111\u0004T\u0005\u0003\u001br\u0011qbU3sm2,GOU3ta>t7/\u001a\u0005\u0006\u001f\u0016\u0001\r\u0001U\u0001\u0006G\"\f\u0017N\u001c\t\u00037EK!A\u0015\u000f\u0003\u0017\u0019KG\u000e^3s\u0007\"\f\u0017N\u001c"
)
public class ApplicationCacheCheckFilter implements Filter, Logging {
   private final CacheKey key;
   private final LoadedAppUI loadedUI;
   private final ApplicationCache cache;
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

   public void init(final FilterConfig x$1) throws ServletException {
      super.init(x$1);
   }

   public void destroy() {
      super.destroy();
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) {
      if (!(request instanceof HttpServletRequest httpRequest)) {
         throw new ServletException("This filter only works for HTTP/HTTPS");
      } else {
         HttpServletResponse httpResponse = (HttpServletResponse)response;
         String requestURI = httpRequest.getRequestURI();
         this.loadedUI.lock().readLock().lock();
         if (this.loadedUI.valid()) {
            try {
               chain.doFilter(request, response);
            } finally {
               this.loadedUI.lock().readLock().unlock();
            }

         } else {
            this.loadedUI.lock().readLock().unlock();
            this.cache.invalidate(this.key);
            String queryStr = (String).MODULE$.apply(httpRequest.getQueryString()).map((x$2) -> "?" + x$2).getOrElse(() -> "");
            String redirectUrl = httpResponse.encodeRedirectURL(requestURI + queryStr);
            httpResponse.sendRedirect(redirectUrl);
         }
      }
   }

   public ApplicationCacheCheckFilter(final CacheKey key, final LoadedAppUI loadedUI, final ApplicationCache cache) {
      this.key = key;
      this.loadedUI = loadedUI;
      this.cache = cache;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
