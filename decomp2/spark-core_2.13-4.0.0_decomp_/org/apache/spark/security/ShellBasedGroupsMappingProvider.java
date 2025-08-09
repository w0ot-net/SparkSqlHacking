package org.apache.spark.security;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Predef;
import scala.StringContext;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q!\u0002\u0004\u0001\u00119AQa\b\u0001\u0005\u0002\u0005B\u0001b\t\u0001\t\u0006\u0004%I\u0001\n\u0005\u0006a\u0001!\t%\r\u0005\u0006o\u0001!I\u0001\u000f\u0002 '\",G\u000e\u001c\"bg\u0016$wI]8vaNl\u0015\r\u001d9j]\u001e\u0004&o\u001c<jI\u0016\u0014(BA\u0004\t\u0003!\u0019XmY;sSRL(BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0014\t\u0001yQ#\u0007\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y9R\"\u0001\u0004\n\u0005a1!aG$s_V\u0004X*\u00199qS:<7+\u001a:wS\u000e,\u0007K]8wS\u0012,'\u000f\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d\u0011\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u001f7\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\t\u0002\"A\u0006\u0001\u0002\r%$\u0007+\u0019;i+\u0005)\u0003C\u0001\u0014.\u001d\t93\u0006\u0005\u0002)#5\t\u0011F\u0003\u0002+A\u00051AH]8pizJ!\u0001L\t\u0002\rA\u0013X\rZ3g\u0013\tqsF\u0001\u0004TiJLgn\u001a\u0006\u0003YE\t\u0011bZ3u\u000fJ|W\u000f]:\u0015\u0005I*\u0004c\u0001\u00144K%\u0011Ag\f\u0002\u0004'\u0016$\b\"\u0002\u001c\u0004\u0001\u0004)\u0013\u0001C;tKJt\u0017-\\3\u0002\u001b\u001d,G/\u00168jq\u001e\u0013x.\u001e9t)\t\u0011\u0014\bC\u00037\t\u0001\u0007Q\u0005"
)
public class ShellBasedGroupsMappingProvider implements GroupMappingServiceProvider, Logging {
   private String idPath;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   private String idPath$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.idPath = .MODULE$.stripLineEnd$extension(scala.Predef..MODULE$.augmentString(Utils$.MODULE$.executeAndGetOutput(scala.collection.immutable.Nil..MODULE$.$colon$colon("id").$colon$colon("which"), Utils$.MODULE$.executeAndGetOutput$default$2(), Utils$.MODULE$.executeAndGetOutput$default$3(), Utils$.MODULE$.executeAndGetOutput$default$4())));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.idPath;
   }

   private String idPath() {
      return !this.bitmap$0 ? this.idPath$lzycompute() : this.idPath;
   }

   public Set getGroups(final String username) {
      Set userGroups = this.getUnixGroups(username);
      this.logDebug((Function0)(() -> "User: " + username + " Groups: " + userGroups.mkString(",")));
      return userGroups;
   }

   private Set getUnixGroups(final String username) {
      Predef var10000 = scala.Predef..MODULE$;
      StringOps var10001 = .MODULE$;
      Predef var10002 = scala.Predef..MODULE$;
      Utils$ var10003 = Utils$.MODULE$;
      String var2 = this.idPath();
      return var10000.wrapRefArray((Object[])var10001.stripLineEnd$extension(var10002.augmentString(var10003.executeAndGetOutput(scala.collection.immutable.Nil..MODULE$.$colon$colon(username).$colon$colon("-Gn").$colon$colon(var2), Utils$.MODULE$.executeAndGetOutput$default$2(), Utils$.MODULE$.executeAndGetOutput$default$3(), Utils$.MODULE$.executeAndGetOutput$default$4()))).split(" ")).toSet();
   }

   public ShellBasedGroupsMappingProvider() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
