package org.apache.spark.streaming.dstream;

import java.io.EOFException;
import java.lang.invoke.SerializedLambda;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4Qa\u0003\u0007\u0001\u001dYA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tm\u0001\u0011\t\u0011)A\u0005o!I!\b\u0001B\u0001B\u0003%1(\u0011\u0005\u0006\u0005\u0002!\ta\u0011\u0005\b\u0013\u0002\u0001\r\u0011\"\u0001K\u0011\u001d\u0019\u0006\u00011A\u0005\u0002QCaA\u0017\u0001!B\u0013Y\u0005\"B.\u0001\t\u0003a\u0006\"B/\u0001\t\u0003a\u0006\"\u00020\u0001\t\u0013y&A\u0005*bo:+Go^8sWJ+7-Z5wKJT!!\u0004\b\u0002\u000f\u0011\u001cHO]3b[*\u0011q\u0002E\u0001\ngR\u0014X-Y7j]\u001eT!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\n\u0004\u0001]\u0019\u0003c\u0001\r\u001c;5\t\u0011D\u0003\u0002\u001b\u001d\u0005A!/Z2fSZ,'/\u0003\u0002\u001d3\tA!+Z2fSZ,'\u000f\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sDA\u0002B]f\u0004\"\u0001J\u0014\u000e\u0003\u0015R!A\n\t\u0002\u0011%tG/\u001a:oC2L!\u0001K\u0013\u0003\u000f1{wmZ5oO\u0006!\u0001n\\:u\u0007\u0001\u0001\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018 \u001b\u0005y#B\u0001\u0019+\u0003\u0019a$o\\8u}%\u0011!gH\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023?\u0005!\u0001o\u001c:u!\tq\u0002(\u0003\u0002:?\t\u0019\u0011J\u001c;\u0002\u0019M$xN]1hK2+g/\u001a7\u0011\u0005qzT\"A\u001f\u000b\u0005y\u0002\u0012aB:u_J\fw-Z\u0005\u0003\u0001v\u0012Ab\u0015;pe\u0006<W\rT3wK2L!AO\u000e\u0002\rqJg.\u001b;?)\u0011!ei\u0012%\u0011\u0005\u0015\u0003Q\"\u0001\u0007\t\u000b%\"\u0001\u0019A\u0016\t\u000bY\"\u0001\u0019A\u001c\t\u000bi\"\u0001\u0019A\u001e\u0002%\tdwnY6QkND\u0017N\\4UQJ,\u0017\rZ\u000b\u0002\u0017B\u0011A*U\u0007\u0002\u001b*\u0011ajT\u0001\u0005Y\u0006twMC\u0001Q\u0003\u0011Q\u0017M^1\n\u0005Ik%A\u0002+ie\u0016\fG-\u0001\fcY>\u001c7\u000eU;tQ&tw\r\u00165sK\u0006$w\fJ3r)\t)\u0006\f\u0005\u0002\u001f-&\u0011qk\b\u0002\u0005+:LG\u000fC\u0004Z\r\u0005\u0005\t\u0019A&\u0002\u0007a$\u0013'A\ncY>\u001c7\u000eU;tQ&tw\r\u00165sK\u0006$\u0007%A\u0004p]N#\u0018M\u001d;\u0015\u0003U\u000baa\u001c8Ti>\u0004\u0018!\u0003:fC\u00124U\u000f\u001c7z)\r)\u0006M\u001b\u0005\u0006C*\u0001\rAY\u0001\bG\"\fgN\\3m!\t\u0019\u0007.D\u0001e\u0015\t)g-\u0001\u0005dQ\u0006tg.\u001a7t\u0015\t9w*A\u0002oS>L!!\u001b3\u0003'I+\u0017\rZ1cY\u0016\u0014\u0015\u0010^3DQ\u0006tg.\u001a7\t\u000b-T\u0001\u0019\u00017\u0002\t\u0011,7\u000f\u001e\t\u0003[:l\u0011AZ\u0005\u0003_\u001a\u0014!BQ=uK\n+hMZ3s\u0001"
)
public class RawNetworkReceiver extends Receiver implements Logging {
   private final String host;
   private final int port;
   private Thread blockPushingThread;
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

   public Thread blockPushingThread() {
      return this.blockPushingThread;
   }

   public void blockPushingThread_$eq(final Thread x$1) {
      this.blockPushingThread = x$1;
   }

   public void onStart() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port))})))));
      SocketChannel channel = SocketChannel.open();
      channel.configureBlocking(true);
      channel.connect(new InetSocketAddress(this.host, this.port));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connected to ", ":", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port))})))));
      ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
      this.blockPushingThread_$eq(new Thread(queue) {
         // $FF: synthetic field
         private final RawNetworkReceiver $outer;
         private final ArrayBlockingQueue queue$1;

         public void run() {
            int nextBlockNumber = 0;

            while(true) {
               ByteBuffer buffer = (ByteBuffer)this.queue$1.take();
               ++nextBlockNumber;
               this.$outer.store(buffer);
            }
         }

         public {
            if (RawNetworkReceiver.this == null) {
               throw null;
            } else {
               this.$outer = RawNetworkReceiver.this;
               this.queue$1 = queue$1;
               this.setDaemon(true);
            }
         }
      });
      this.blockPushingThread().start();
      ByteBuffer lengthBuffer = ByteBuffer.allocate(4);

      while(true) {
         lengthBuffer.clear();
         this.readFully(channel, lengthBuffer);
         lengthBuffer.flip();
         int length = lengthBuffer.getInt();
         ByteBuffer dataBuffer = ByteBuffer.allocate(length);
         this.readFully(channel, dataBuffer);
         dataBuffer.flip();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Read a block with ", " bytes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BYTE_SIZE..MODULE$, BoxesRunTime.boxToInteger(length))})))));
         queue.put(dataBuffer);
      }
   }

   public void onStop() {
      if (this.blockPushingThread() != null) {
         this.blockPushingThread().interrupt();
      }
   }

   private void readFully(final ReadableByteChannel channel, final ByteBuffer dest) {
      while(true) {
         if (dest.position() < dest.limit()) {
            if (channel.read(dest) != -1) {
               continue;
            }

            throw new EOFException("End of channel");
         }

         return;
      }
   }

   public RawNetworkReceiver(final String host, final int port, final StorageLevel storageLevel) {
      super(storageLevel);
      this.host = host;
      this.port = port;
      Logging.$init$(this);
      this.blockPushingThread = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
