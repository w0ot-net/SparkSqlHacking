package org.apache.spark.streaming.dstream;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!\u0002\n\u0014\u0001Ui\u0002\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u001dC\u0001B\u0013\u0001\u0003\u0002\u0003\u0006Ia\u0013\u0005\n?\u0002\u0011\t\u0011)A\u0005A\u001aD\u0001b\u001a\u0001\u0003\u0004\u0003\u0006Y\u0001\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\nq\u0002\u0001\r\u00111A\u0005\neD1\"!\u0001\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u0004!Q\u0011q\u0002\u0001A\u0002\u0003\u0005\u000b\u0015\u0002>\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011Q\u0003\u0001\u0005\u0002\u0005M\u0001bBA\f\u0001\u0011\u0005\u00111C\u0004\t\u00033\u0019\u0002\u0012A\u000b\u0002\u001c\u00199!c\u0005E\u0001+\u0005u\u0001B\u00028\u000f\t\u0003\tY\u0003C\u0004\u0002.9!\t!a\f\t\u0013\u0005]b\"!A\u0005\n\u0005e\"AD*pG.,GOU3dK&4XM\u001d\u0006\u0003)U\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u0017/\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sOV\u0011adJ\n\u0004\u0001}!\u0004c\u0001\u0011$K5\t\u0011E\u0003\u0002#+\u0005A!/Z2fSZ,'/\u0003\u0002%C\tA!+Z2fSZ,'\u000f\u0005\u0002'O1\u0001A!\u0002\u0015\u0001\u0005\u0004Q#!\u0001+\u0004\u0001E\u00111&\r\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\b\u001d>$\b.\u001b8h!\ta#'\u0003\u00024[\t\u0019\u0011I\\=\u0011\u0005UBT\"\u0001\u001c\u000b\u0005]:\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005e2$a\u0002'pO\u001eLgnZ\u0001\u0005Q>\u001cH\u000f\u0005\u0002=\u0007:\u0011Q(\u0011\t\u0003}5j\u0011a\u0010\u0006\u0003\u0001&\na\u0001\u0010:p_Rt\u0014B\u0001\".\u0003\u0019\u0001&/\u001a3fM&\u0011A)\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\tk\u0013\u0001\u00029peR\u0004\"\u0001\f%\n\u0005%k#aA%oi\u0006q!-\u001f;fgR{wJ\u00196fGR\u001c\b\u0003\u0002\u0017M\u001dZK!!T\u0017\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA(U\u001b\u0005\u0001&BA)S\u0003\tIwNC\u0001T\u0003\u0011Q\u0017M^1\n\u0005U\u0003&aC%oaV$8\u000b\u001e:fC6\u00042a\u0016/&\u001d\tA&L\u0004\u0002?3&\ta&\u0003\u0002\\[\u00059\u0001/Y2lC\u001e,\u0017BA/_\u0005!IE/\u001a:bi>\u0014(BA..\u00031\u0019Ho\u001c:bO\u0016dUM^3m!\t\tG-D\u0001c\u0015\t\u0019w#A\u0004ti>\u0014\u0018mZ3\n\u0005\u0015\u0014'\u0001D*u_J\fw-\u001a'fm\u0016d\u0017BA0$\u0003))g/\u001b3f]\u000e,GE\r\t\u0004S2,S\"\u00016\u000b\u0005-l\u0013a\u0002:fM2,7\r^\u0005\u0003[*\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bA$XO^<\u0015\u0005E\u001c\bc\u0001:\u0001K5\t1\u0003C\u0003h\r\u0001\u000f\u0001\u000eC\u0003;\r\u0001\u00071\bC\u0003G\r\u0001\u0007q\tC\u0003K\r\u0001\u00071\nC\u0003`\r\u0001\u0007\u0001-\u0001\u0004t_\u000e\\W\r^\u000b\u0002uB\u00111P`\u0007\u0002y*\u0011QPU\u0001\u0004]\u0016$\u0018BA@}\u0005\u0019\u0019vnY6fi\u0006Q1o\\2lKR|F%Z9\u0015\t\u0005\u0015\u00111\u0002\t\u0004Y\u0005\u001d\u0011bAA\u0005[\t!QK\\5u\u0011!\ti\u0001CA\u0001\u0002\u0004Q\u0018a\u0001=%c\u000591o\\2lKR\u0004\u0013aB8o'R\f'\u000f\u001e\u000b\u0003\u0003\u000b\taa\u001c8Ti>\u0004\u0018a\u0002:fG\u0016Lg/Z\u0001\u000f'>\u001c7.\u001a;SK\u000e,\u0017N^3s!\t\u0011hbE\u0003\u000f\u0003?\t)\u0003E\u0002-\u0003CI1!a\t.\u0005\u0019\te.\u001f*fMB\u0019q*a\n\n\u0007\u0005%\u0002K\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002\u001c\u0005a!-\u001f;fgR{G*\u001b8fgR!\u0011\u0011GA\u001a!\r9Fl\u000f\u0005\u0007\u0003k\u0001\u0002\u0019\u0001(\u0002\u0017%t\u0007/\u001e;TiJ,\u0017-\\\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003w\u0001B!!\u0010\u0002D5\u0011\u0011q\b\u0006\u0004\u0003\u0003\u0012\u0016\u0001\u00027b]\u001eLA!!\u0012\u0002@\t1qJ\u00196fGR\u0004"
)
public class SocketReceiver extends Receiver implements Logging {
   private final String host;
   private final int port;
   private final Function1 bytesToObjects;
   private Socket socket;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Iterator bytesToLines(final InputStream inputStream) {
      return SocketReceiver$.MODULE$.bytesToLines(inputStream);
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

   private Socket socket() {
      return this.socket;
   }

   private void socket_$eq(final Socket x$1) {
      this.socket = x$1;
   }

   public void onStart() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port))})))));

      try {
         this.socket_$eq(new Socket(this.host, this.port));
      } catch (ConnectException var2) {
         this.restart("Error connecting to " + this.host + ":" + this.port, var2);
         return;
      }

      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connected to ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port))})))));
      (new Thread() {
         // $FF: synthetic field
         private final SocketReceiver $outer;

         public void run() {
            this.$outer.receive();
         }

         public {
            if (SocketReceiver.this == null) {
               throw null;
            } else {
               this.$outer = SocketReceiver.this;
               this.setDaemon(true);
            }
         }
      }).start();
   }

   public synchronized void onStop() {
      if (this.socket() != null) {
         this.socket().close();
         this.socket_$eq((Socket)null);
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Closed socket to ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port))})))));
      }
   }

   public void receive() {
      try {
         Iterator iterator = (Iterator)this.bytesToObjects.apply(this.socket().getInputStream());

         while(!this.isStopped() && iterator.hasNext()) {
            this.store(iterator.next());
         }

         if (!this.isStopped()) {
            this.restart("Socket data stream had no more data");
         } else {
            this.logInfo((Function0)(() -> "Stopped receiving"));
         }
      } catch (Throwable var9) {
         if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
            throw var9;
         }

         this.logWarning((Function0)(() -> "Error receiving data"), var9);
         this.restart("Error receiving data", var9);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } finally {
         this.onStop();
      }

   }

   public SocketReceiver(final String host, final int port, final Function1 bytesToObjects, final StorageLevel storageLevel, final ClassTag evidence$2) {
      super(storageLevel);
      this.host = host;
      this.port = port;
      this.bytesToObjects = bytesToObjects;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
