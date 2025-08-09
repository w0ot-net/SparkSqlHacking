package org.apache.spark.api.python;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CollectionAccumulator;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005a4Q\u0001E\t\u0001+mA\u0001\"\r\u0001\u0003\u0006\u0004%Ia\r\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005i!AA\t\u0001BC\u0002\u0013%Q\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003G\u0011!Q\u0005A!b\u0001\n\u0013\u0019\u0004\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u000b1\u0003A\u0011A'\t\u000fM\u0003!\u0019!C\u0001\u000b\"1A\u000b\u0001Q\u0001\n\u0019C\u0011\"\u0016\u0001A\u0002\u0003\u0007I\u0011\u0002,\t\u0013}\u0003\u0001\u0019!a\u0001\n\u0013\u0001\u0007\"\u00034\u0001\u0001\u0004\u0005\t\u0015)\u0003X\u0011\u0015A\u0007\u0001\"\u0003j\u0011\u0015Q\u0007\u0001\"\u0011l\u0011\u0015a\u0007\u0001\"\u0011n\u0005M\u0001\u0016\u0010\u001e5p]\u0006\u001b7-^7vY\u0006$xN\u001d,3\u0015\t\u00112#\u0001\u0004qsRDwN\u001c\u0006\u0003)U\t1!\u00199j\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<7c\u0001\u0001\u001dWA\u0019Q\u0004\t\u0012\u000e\u0003yQ!aH\u000b\u0002\tU$\u0018\u000e\\\u0005\u0003Cy\u0011QcQ8mY\u0016\u001cG/[8o\u0003\u000e\u001cW/\\;mCR|'\u000fE\u0002$M!j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003G%J!A\u000b\u0013\u0003\t\tKH/\u001a\t\u0003Y=j\u0011!\f\u0006\u0003]U\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003a5\u0012q\u0001T8hO&tw-\u0001\u0006tKJ4XM\u001d%pgR\u001c\u0001!F\u00015!\t)DH\u0004\u00027uA\u0011q\u0007J\u0007\u0002q)\u0011\u0011HM\u0001\u0007yI|w\u000e\u001e \n\u0005m\"\u0013A\u0002)sK\u0012,g-\u0003\u0002>}\t11\u000b\u001e:j]\u001eT!a\u000f\u0013\u0002\u0017M,'O^3s\u0011>\u001cH\u000f\t\u0015\u0003\u0005\u0005\u0003\"a\t\"\n\u0005\r##!\u0003;sC:\u001c\u0018.\u001a8u\u0003)\u0019XM\u001d<feB{'\u000f^\u000b\u0002\rB\u00111eR\u0005\u0003\u0011\u0012\u00121!\u00138u\u0003-\u0019XM\u001d<feB{'\u000f\u001e\u0011\u0002\u0017M,7M]3u)>\\WM\\\u0001\rg\u0016\u001c'/\u001a;U_.,g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t9\u0003\u0016K\u0015\t\u0003\u001f\u0002i\u0011!\u0005\u0005\u0006c\u001d\u0001\r\u0001\u000e\u0005\u0006\t\u001e\u0001\rA\u0012\u0005\u0006\u0015\u001e\u0001\r\u0001N\u0001\u000bEV4g-\u001a:TSj,\u0017a\u00032vM\u001a,'oU5{K\u0002\naa]8dW\u0016$X#A,\u0011\u0005akV\"A-\u000b\u0005i[\u0016a\u00018fi*\tA,\u0001\u0003kCZ\f\u0017B\u00010Z\u0005\u0019\u0019vnY6fi\u0006Q1o\\2lKR|F%Z9\u0015\u0005\u0005$\u0007CA\u0012c\u0013\t\u0019GE\u0001\u0003V]&$\bbB3\f\u0003\u0003\u0005\raV\u0001\u0004q\u0012\n\u0014aB:pG.,G\u000f\t\u0015\u0003\u0019\u0005\u000b!b\u001c9f]N{7m[3u)\u00059\u0016\u0001D2paf\fe\u000e\u001a*fg\u0016$H#\u0001(\u0002\u000b5,'oZ3\u0015\u0005\u0005t\u0007\"B8\u0010\u0001\u0004\u0001\u0018!B8uQ\u0016\u0014\b\u0003B\u000frEML!A\u001d\u0010\u0003\u001b\u0005\u001b7-^7vY\u0006$xN\u001d,3!\r!hOI\u0007\u0002k*\u0011qdW\u0005\u0003oV\u0014A\u0001T5ti\u0002"
)
public class PythonAccumulatorV2 extends CollectionAccumulator implements Logging {
   private final transient String serverHost;
   private final int serverPort;
   private final String secretToken;
   private final int bufferSize;
   private transient Socket socket;
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

   private String serverHost() {
      return this.serverHost;
   }

   private int serverPort() {
      return this.serverPort;
   }

   private String secretToken() {
      return this.secretToken;
   }

   public int bufferSize() {
      return this.bufferSize;
   }

   private Socket socket() {
      return this.socket;
   }

   private void socket_$eq(final Socket x$1) {
      this.socket = x$1;
   }

   private synchronized Socket openSocket() {
      if (this.socket() == null || this.socket().isClosed()) {
         this.socket_$eq(new Socket(this.serverHost(), this.serverPort()));
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connected to AccumulatorServer at host: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.serverHost())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" port: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.serverPort()))}))))));
         this.socket().getOutputStream().write(this.secretToken().getBytes(StandardCharsets.UTF_8));
      }

      return this.socket();
   }

   public PythonAccumulatorV2 copyAndReset() {
      return new PythonAccumulatorV2(this.serverHost(), this.serverPort(), this.secretToken());
   }

   public synchronized void merge(final AccumulatorV2 other) {
      PythonAccumulatorV2 otherPythonAccumulator = (PythonAccumulatorV2)other;
      if (this.serverHost() == null) {
         super.merge(otherPythonAccumulator);
      } else {
         Socket socket = this.openSocket();
         InputStream in = socket.getInputStream();
         DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), this.bufferSize()));
         List values = (List)other.value();
         out.writeInt(values.size());
         scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(values).asScala().foreach((array) -> {
            $anonfun$merge$1(out, array);
            return BoxedUnit.UNIT;
         });
         out.flush();
         int byteRead = in.read();
         if (byteRead == -1) {
            throw new SparkException("EOF reached before Python server acknowledged");
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$merge$1(final DataOutputStream out$2, final byte[] array) {
      out$2.writeInt(array.length);
      out$2.write(array);
   }

   public PythonAccumulatorV2(final String serverHost, final int serverPort, final String secretToken) {
      this.serverHost = serverHost;
      this.serverPort = serverPort;
      this.secretToken = secretToken;
      Logging.$init$(this);
      Utils$.MODULE$.checkHost(serverHost);
      this.bufferSize = BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.BUFFER_SIZE()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
