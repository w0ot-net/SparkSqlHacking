package org.apache.spark.deploy;

import java.io.File;
import java.io.InputStreamReader;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.spark.deploy.master.RecoveryState$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.json4s.Formats;
import org.json4s.JValue;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.StringContext;
import scala.collection.IterableOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001\u0002\f\u0018\t\u0001B\u0001\"\f\u0001\u0003\u0006\u0004%\tA\f\u0005\tu\u0001\u0011\t\u0011)A\u0005_!A1\b\u0001BC\u0002\u0013\u0005A\b\u0003\u0005B\u0001\t\u0005\t\u0015!\u0003>\u0011!\u0011\u0005A!b\u0001\n\u0003\u0019\u0005\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011\u0002#\t\u000b5\u0003A\u0011\u0001(\t\u000fM\u0003!\u0019!C\u0002)\"11\f\u0001Q\u0001\nUC\u0011\u0002\u0018\u0001A\u0002\u0003\u0007I\u0011A/\t\u0013%\u0004\u0001\u0019!a\u0001\n\u0003Q\u0007\"\u00039\u0001\u0001\u0004\u0005\t\u0015)\u0003_\u0011%\t\b\u00011AA\u0002\u0013\u0005!\u000fC\u0005}\u0001\u0001\u0007\t\u0019!C\u0001{\"Iq\u0010\u0001a\u0001\u0002\u0003\u0006Ka\u001d\u0005\n\u0003\u0003\u0001\u0001\u0019!C\u0001\u0003\u0007A\u0011\"a\u0003\u0001\u0001\u0004%\t!!\u0004\t\u0011\u0005E\u0001\u0001)Q\u0005\u0003\u000bAq!a\u0005\u0001\t\u0003\t)\u0002C\u0004\u0002\u0018\u0001!\t!!\u0006\t\u000f\u0005e\u0001\u0001\"\u0011\u0002\u001c\tqA+Z:u\u001b\u0006\u001cH/\u001a:J]\u001a|'B\u0001\r\u001a\u0003\u0019!W\r\u001d7ps*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\ts\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13E\u0001\u0004B]f\u0014VM\u001a\t\u0003Q-j\u0011!\u000b\u0006\u0003Ue\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003Y%\u0012q\u0001T8hO&tw-\u0001\u0002jaV\tq\u0006\u0005\u00021o9\u0011\u0011'\u000e\t\u0003e\rj\u0011a\r\u0006\u0003i}\ta\u0001\u0010:p_Rt\u0014B\u0001\u001c$\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001(\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y\u001a\u0013aA5qA\u0005AAm\\2lKJLE-F\u0001>!\tqt(D\u0001\u0018\u0013\t\u0001uC\u0001\u0005E_\u000e\\WM]%e\u0003%!wnY6fe&#\u0007%A\u0004m_\u001e4\u0015\u000e\\3\u0016\u0003\u0011\u0003\"!\u0012&\u000e\u0003\u0019S!a\u0012%\u0002\u0005%|'\"A%\u0002\t)\fg/Y\u0005\u0003\u0017\u001a\u0013AAR5mK\u0006AAn\\4GS2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\u001fB\u000b&\u000b\u0005\u0002?\u0001!)Qf\u0002a\u0001_!)1h\u0002a\u0001{!)!i\u0002a\u0001\t\u00069am\u001c:nCR\u001cX#A+\u0011\u0005YKV\"A,\u000b\u0005ak\u0012A\u00026t_:$4/\u0003\u0002[/\n9ai\u001c:nCR\u001c\u0018\u0001\u00034pe6\fGo\u001d\u0011\u0002\u000bM$\u0018\r^3\u0016\u0003y\u0003\"aX3\u000f\u0005\u0001\u001cW\"A1\u000b\u0005\t<\u0012AB7bgR,'/\u0003\u0002eC\u0006i!+Z2pm\u0016\u0014\u0018p\u0015;bi\u0016L!AZ4\u0003\u000bY\u000bG.^3\n\u0005!\u001c#aC#ok6,'/\u0019;j_:\f\u0011b\u001d;bi\u0016|F%Z9\u0015\u0005-t\u0007C\u0001\u0012m\u0013\ti7E\u0001\u0003V]&$\bbB8\f\u0003\u0003\u0005\rAX\u0001\u0004q\u0012\n\u0014AB:uCR,\u0007%A\u0007mSZ,wk\u001c:lKJL\u0005k]\u000b\u0002gB\u0019A/_\u0018\u000f\u0005U<hB\u0001\u001aw\u0013\u0005!\u0013B\u0001=$\u0003\u001d\u0001\u0018mY6bO\u0016L!A_>\u0003\t1K7\u000f\u001e\u0006\u0003q\u000e\n\u0011\u0003\\5wK^{'o[3s\u0013B\u001bx\fJ3r)\tYg\u0010C\u0004p\u001d\u0005\u0005\t\u0019A:\u0002\u001d1Lg/Z,pe.,'/\u0013)tA\u0005Ya.^7MSZ,\u0017\t\u001d9t+\t\t)\u0001E\u0002#\u0003\u000fI1!!\u0003$\u0005\rIe\u000e^\u0001\u0010]VlG*\u001b<f\u0003B\u00048o\u0018\u0013fcR\u00191.a\u0004\t\u0011=\f\u0012\u0011!a\u0001\u0003\u000b\tAB\\;n\u0019&4X-\u00119qg\u0002\n\u0011B]3bIN#\u0018\r^3\u0015\u0003-\fAa[5mY\u0006AAo\\*ue&tw\rF\u00010\u0001"
)
public class TestMasterInfo implements Logging {
   private final String ip;
   private final DockerId dockerId;
   private final File logFile;
   private final Formats formats;
   private Enumeration.Value state;
   private List liveWorkerIPs;
   private int numLiveApps;
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

   public String ip() {
      return this.ip;
   }

   public DockerId dockerId() {
      return this.dockerId;
   }

   public File logFile() {
      return this.logFile;
   }

   public Formats formats() {
      return this.formats;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public List liveWorkerIPs() {
      return this.liveWorkerIPs;
   }

   public void liveWorkerIPs_$eq(final List x$1) {
      this.liveWorkerIPs = x$1;
   }

   public int numLiveApps() {
      return this.numLiveApps;
   }

   public void numLiveApps_$eq(final int x$1) {
      this.numLiveApps = x$1;
   }

   public void readState() {
      try {
         InputStreamReader masterStream = new InputStreamReader((new URI(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("http://%s:8080/json"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.ip()})))).toURL().openStream(), StandardCharsets.UTF_8);
         JValue json = org.json4s.jackson.JsonMethods..MODULE$.parse(masterStream, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.readerAsJsonInput());
         JValue workers = org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(json), "workers");
         List liveWorkers = workers.children().filter((w) -> BoxesRunTime.boxToBoolean($anonfun$readState$1(this, w)));
         this.liveWorkerIPs_$eq(liveWorkers.map((w) -> .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(.MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString((String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(w), "webuiaddress")), this.formats(), scala.reflect.ManifestFactory..MODULE$.classType(String.class))), "http://")), ":8081")));
         this.numLiveApps_$eq(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(json), "activeapps").children().size());
         JValue status = org.json4s.MonadicJValue..MODULE$.$bslash$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(json), "status");
         String stateString = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(status), this.formats(), scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         this.state_$eq((Enumeration.Value)((IterableOps)RecoveryState$.MODULE$.values().filter((state) -> BoxesRunTime.boxToBoolean($anonfun$readState$3(stateString, state)))).head());
      } catch (Exception var8) {
         this.logWarning((Function0)(() -> "Exception"), var8);
      }

   }

   public void kill() {
      Docker$.MODULE$.kill(this.dockerId());
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("[ip=%s, id=%s, logFile=%s, state=%s]"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.ip(), this.dockerId().id(), this.logFile().getAbsolutePath(), this.state()}));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readState$1(final TestMasterInfo $this, final JValue w) {
      boolean var3;
      label23: {
         Object var10000 = org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(w), "state")), $this.formats(), scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String var2 = "ALIVE";
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readState$3(final String stateString$1, final Enumeration.Value state) {
      boolean var3;
      label23: {
         String var10000 = state.toString();
         if (var10000 == null) {
            if (stateString$1 == null) {
               break label23;
            }
         } else if (var10000.equals(stateString$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public TestMasterInfo(final String ip, final DockerId dockerId, final File logFile) {
      this.ip = ip;
      this.dockerId = dockerId;
      this.logFile = logFile;
      Logging.$init$(this);
      this.formats = org.json4s.DefaultFormats..MODULE$;
      this.numLiveApps = 0;
      this.logDebug((Function0)(() -> "Created master: " + this));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
