package org.apache.spark.rpc;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.RpcUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.concurrent.Future;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}bA\u0002\u0007\u000e\u0003\u0003yQ\u0003\u0003\u00050\u0001\t\u0005\t\u0015!\u00031\u0011\u0015!\u0004\u0001\"\u00016\u0011\u0019I\u0004\u0001)A\u0005u!)Q\b\u0001D\u0001}!)!\t\u0001D\u0001\u0007\")A\n\u0001D\u0001\u001b\")a\u000b\u0001C\u0001/\")\u0001\u000f\u0001D\u0001c\"1\u0001\u000f\u0001C\u0001\u0003\u0007Aq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002\u0018\u0001!\t!a\u000b\u0003\u001dI\u00038-\u00128ea>Lg\u000e\u001e*fM*\u0011abD\u0001\u0004eB\u001c'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\t\u00011B$\u000b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005u1cB\u0001\u0010%\u001d\ty2%D\u0001!\u0015\t\t#%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005I\u0012BA\u0013\u0019\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0015B\u0002C\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0010\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0018,\u0005\u001daunZ4j]\u001e\fAaY8oMB\u0011\u0011GM\u0007\u0002\u001f%\u00111g\u0004\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDC\u0001\u001c9!\t9\u0004!D\u0001\u000e\u0011\u0015y#\u00011\u00011\u0003E!WMZ1vYR\f5o\u001b+j[\u0016|W\u000f\u001e\t\u0003omJ!\u0001P\u0007\u0003\u0015I\u00038\rV5nK>,H/A\u0004bI\u0012\u0014Xm]:\u0016\u0003}\u0002\"a\u000e!\n\u0005\u0005k!A\u0003*qG\u0006#GM]3tg\u0006!a.Y7f+\u0005!\u0005CA#J\u001d\t1u\t\u0005\u0002 1%\u0011\u0001\nG\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002I1\u0005!1/\u001a8e)\tq\u0015\u000b\u0005\u0002\u0018\u001f&\u0011\u0001\u000b\u0007\u0002\u0005+:LG\u000fC\u0003S\r\u0001\u00071+A\u0004nKN\u001c\u0018mZ3\u0011\u0005]!\u0016BA+\u0019\u0005\r\te._\u0001\rCN\\\u0017IY8si\u0006\u0014G.Z\u000b\u00031~#2!W7o)\tQV\rE\u000287vK!\u0001X\u0007\u0003%\u0005\u0013wN\u001d;bE2,'\u000b]2GkR,(/\u001a\t\u0003=~c\u0001\u0001B\u0003a\u000f\t\u0007\u0011MA\u0001U#\t\u00117\u000b\u0005\u0002\u0018G&\u0011A\r\u0007\u0002\b\u001d>$\b.\u001b8h\u0011\u001d1w!!AA\u0004\u001d\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rA7.X\u0007\u0002S*\u0011!\u000eG\u0001\be\u00164G.Z2u\u0013\ta\u0017N\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0015\u0011v\u00011\u0001T\u0011\u0015yw\u00011\u0001;\u0003\u001d!\u0018.\\3pkR\f1!Y:l+\t\u00118\u0010\u0006\u0003t\u007f\u0006\u0005AC\u0001;}!\r)\bP_\u0007\u0002m*\u0011q\u000fG\u0001\u000bG>t7-\u001e:sK:$\u0018BA=w\u0005\u00191U\u000f^;sKB\u0011al\u001f\u0003\u0006A\"\u0011\r!\u0019\u0005\b{\"\t\t\u0011q\u0001\u007f\u0003))g/\u001b3f]\u000e,GE\r\t\u0004Q.T\b\"\u0002*\t\u0001\u0004\u0019\u0006\"B8\t\u0001\u0004QT\u0003BA\u0003\u0003\u001b!B!a\u0002\u0002\u0016Q!\u0011\u0011BA\b!\u0011)\b0a\u0003\u0011\u0007y\u000bi\u0001B\u0003a\u0013\t\u0007\u0011\rC\u0005\u0002\u0012%\t\t\u0011q\u0001\u0002\u0014\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\t!\\\u00171\u0002\u0005\u0006%&\u0001\raU\u0001\bCN\\7+\u001f8d+\u0011\tY\"!\t\u0015\t\u0005u\u0011\u0011\u0006\u000b\u0005\u0003?\t\u0019\u0003E\u0002_\u0003C!Q\u0001\u0019\u0006C\u0002\u0005D\u0011\"!\n\u000b\u0003\u0003\u0005\u001d!a\n\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003iW\u0006}\u0001\"\u0002*\u000b\u0001\u0004\u0019V\u0003BA\u0017\u0003g!b!a\f\u0002<\u0005uB\u0003BA\u0019\u0003k\u00012AXA\u001a\t\u0015\u00017B1\u0001b\u0011%\t9dCA\u0001\u0002\b\tI$\u0001\u0006fm&$WM\\2fIU\u0002B\u0001[6\u00022!)!k\u0003a\u0001'\")qn\u0003a\u0001u\u0001"
)
public abstract class RpcEndpointRef implements Serializable, Logging {
   private final RpcTimeout defaultAskTimeout;
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

   public abstract RpcAddress address();

   public abstract String name();

   public abstract void send(final Object message);

   public AbortableRpcFuture askAbortable(final Object message, final RpcTimeout timeout, final ClassTag evidence$1) {
      throw new UnsupportedOperationException();
   }

   public abstract Future ask(final Object message, final RpcTimeout timeout, final ClassTag evidence$2);

   public Future ask(final Object message, final ClassTag evidence$3) {
      return this.ask(message, this.defaultAskTimeout, evidence$3);
   }

   public Object askSync(final Object message, final ClassTag evidence$4) {
      return this.askSync(message, this.defaultAskTimeout, evidence$4);
   }

   public Object askSync(final Object message, final RpcTimeout timeout, final ClassTag evidence$5) {
      Future future = this.ask(message, timeout, evidence$5);
      return timeout.awaitResult(future);
   }

   public RpcEndpointRef(final SparkConf conf) {
      Logging.$init$(this);
      this.defaultAskTimeout = RpcUtils$.MODULE$.askRpcTimeout(conf);
   }
}
