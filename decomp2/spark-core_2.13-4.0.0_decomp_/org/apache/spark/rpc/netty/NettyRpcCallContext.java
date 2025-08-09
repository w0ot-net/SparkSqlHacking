package org.apache.spark.rpc.netty;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553aa\u0002\u0005\u0002\u0002!\u0011\u0002\u0002C\u0012\u0001\u0005\u000b\u0007I\u0011I\u0013\t\u0011%\u0002!\u0011!Q\u0001\n\u0019BQA\u000b\u0001\u0005\u0002-BQa\f\u0001\u0007\u0012ABQ!\u000f\u0001\u0005BiBQ!\u0010\u0001\u0005By\u00121CT3uif\u0014\u0006oY\"bY2\u001cuN\u001c;fqRT!!\u0003\u0006\u0002\u000b9,G\u000f^=\u000b\u0005-a\u0011a\u0001:qG*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xm\u0005\u0003\u0001'ei\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b75\t!\"\u0003\u0002\u001d\u0015\tq!\u000b]2DC2d7i\u001c8uKb$\bC\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\r\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0012 \u0005\u001daunZ4j]\u001e\fQb]3oI\u0016\u0014\u0018\t\u001a3sKN\u001c8\u0001A\u000b\u0002MA\u0011!dJ\u0005\u0003Q)\u0011!B\u00159d\u0003\u0012$'/Z:t\u00039\u0019XM\u001c3fe\u0006#GM]3tg\u0002\na\u0001P5oSRtDC\u0001\u0017/!\ti\u0003!D\u0001\t\u0011\u0015\u00193\u00011\u0001'\u0003\u0011\u0019XM\u001c3\u0015\u0005E\"\u0004C\u0001\u000b3\u0013\t\u0019TC\u0001\u0003V]&$\b\"B\u001b\u0005\u0001\u00041\u0014aB7fgN\fw-\u001a\t\u0003)]J!\u0001O\u000b\u0003\u0007\u0005s\u00170A\u0003sKBd\u0017\u0010\u0006\u00022w!)A(\u0002a\u0001m\u0005A!/Z:q_:\u001cX-A\u0006tK:$g)Y5mkJ,GCA\u0019@\u0011\u0015\u0001e\u00011\u0001B\u0003\u0005)\u0007C\u0001\"K\u001d\t\u0019\u0005J\u0004\u0002E\u000f6\tQI\u0003\u0002GI\u00051AH]8pizJ\u0011AF\u0005\u0003\u0013V\tq\u0001]1dW\u0006<W-\u0003\u0002L\u0019\nIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0003\u0013V\u0001"
)
public abstract class NettyRpcCallContext implements RpcCallContext, Logging {
   private final RpcAddress senderAddress;
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

   public RpcAddress senderAddress() {
      return this.senderAddress;
   }

   public abstract void send(final Object message);

   public void reply(final Object response) {
      this.send(response);
   }

   public void sendFailure(final Throwable e) {
      this.send(new RpcFailure(e));
   }

   public NettyRpcCallContext(final RpcAddress senderAddress) {
      this.senderAddress = senderAddress;
      Logging.$init$(this);
   }
}
