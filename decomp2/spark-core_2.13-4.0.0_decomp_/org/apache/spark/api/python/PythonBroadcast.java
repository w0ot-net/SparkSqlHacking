package org.apache.spark.api.python;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.Socket;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.security.SocketAuthServer;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.BroadcastBlockId;
import org.apache.spark.storage.DiskBlockManager;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\u000b\u0017\u0001i\u0001\u0003\u0002C\u001b\u0001\u0005\u0003\u0007I\u0011A\u001c\t\u0011\r\u0003!\u00111A\u0005\u0002\u0011C\u0001B\u0013\u0001\u0003\u0002\u0003\u0006K\u0001\u000f\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\n)\u0002\u0001\r\u00111A\u0005\nUC\u0011\"\u0017\u0001A\u0002\u0003\u0007I\u0011\u0002.\t\u0013q\u0003\u0001\u0019!A!B\u00131\u0006bB/\u0001\u0001\u0004%IA\u0018\u0005\bK\u0002\u0001\r\u0011\"\u0003g\u0011\u0019A\u0007\u0001)Q\u0005?\"9\u0011\u000e\u0001a\u0001\n\u0013q\u0006b\u00026\u0001\u0001\u0004%Ia\u001b\u0005\u0007[\u0002\u0001\u000b\u0015B0\t\u000b9\u0004A\u0011B8\t\u000bU\u0004A\u0011\u0002<\t\u000bq\u0004A\u0011A?\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005\r\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u0003/\u0001A\u0011AA\u000b\u0005=\u0001\u0016\u0010\u001e5p]\n\u0013x.\u00193dCN$(BA\f\u0019\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u0011\u0011DG\u0001\u0004CBL'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0014\t\u0001\tse\f\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!jS\"A\u0015\u000b\u0005)Z\u0013AA5p\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u0011\u0005A\u001aT\"A\u0019\u000b\u0005IR\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005Q\n$a\u0002'pO\u001eLgnZ\u0001\u0005a\u0006$\bn\u0001\u0001\u0016\u0003a\u0002\"!\u000f!\u000f\u0005ir\u0004CA\u001e$\u001b\u0005a$BA\u001f7\u0003\u0019a$o\\8u}%\u0011qhI\u0001\u0007!J,G-\u001a4\n\u0005\u0005\u0013%AB*ue&twM\u0003\u0002@G\u0005A\u0001/\u0019;i?\u0012*\u0017\u000f\u0006\u0002F\u0011B\u0011!ER\u0005\u0003\u000f\u000e\u0012A!\u00168ji\"9\u0011JAA\u0001\u0002\u0004A\u0014a\u0001=%c\u0005)\u0001/\u0019;iA!\u00121\u0001\u0014\t\u0003E5K!AT\u0012\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018A\u0002\u001fj]&$h\b\u0006\u0002R'B\u0011!\u000bA\u0007\u0002-!)Q\u0007\u0002a\u0001q\u0005Y!M]8bI\u000e\f7\u000f^%e+\u00051\u0006C\u0001\u0012X\u0013\tA6E\u0001\u0003M_:<\u0017a\u00042s_\u0006$7-Y:u\u0013\u0012|F%Z9\u0015\u0005\u0015[\u0006bB%\u0007\u0003\u0003\u0005\rAV\u0001\rEJ|\u0017\rZ2bgRLE\rI\u0001\u0011K:\u001c'/\u001f9uS>t7+\u001a:wKJ,\u0012a\u0018\t\u0004A\u000e,U\"A1\u000b\u0005\tT\u0012\u0001C:fGV\u0014\u0018\u000e^=\n\u0005\u0011\f'\u0001E*pG.,G/Q;uQN+'O^3s\u0003Q)gn\u0019:zaRLwN\\*feZ,'o\u0018\u0013fcR\u0011Qi\u001a\u0005\b\u0013&\t\t\u00111\u0001`\u0003E)gn\u0019:zaRLwN\\*feZ,'\u000fI\u0001\u0011I\u0016\u001c'/\u001f9uS>t7+\u001a:wKJ\fA\u0003Z3def\u0004H/[8o'\u0016\u0014h/\u001a:`I\u0015\fHCA#m\u0011\u001dIE\"!AA\u0002}\u000b\u0011\u0003Z3def\u0004H/[8o'\u0016\u0014h/\u001a:!\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u0005\u0015\u0003\b\"B9\u000f\u0001\u0004\u0011\u0018aA8viB\u0011\u0001f]\u0005\u0003i&\u0012!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\u0005\u0015;\b\"\u0002=\u0010\u0001\u0004I\u0018AA5o!\tA#0\u0003\u0002|S\t\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7\u0002\u001dM,GO\u0011:pC\u0012\u001c\u0017m\u001d;JIR\u0011QI \u0005\u0006\u007fB\u0001\rAV\u0001\u0004E&$\u0017!F:fiV\u0004XI\\2ssB$\u0018n\u001c8TKJ4XM\u001d\u000b\u0003\u0003\u000b\u0001RAIA\u0004\u0003\u0017I1!!\u0003$\u0005\u0015\t%O]1z!\r\u0011\u0013QB\u0005\u0004\u0003\u001f\u0019#aA!os\u0006)2/\u001a;va\u0012+7M]=qi&|gnU3sm\u0016\u0014\u0018!G<bSR$\u0016\u000e\u001c7Ce>\fGmY1ti\u0012\u000bG/Y*f]R$\u0012!R\u0001\u0015o\u0006LG\u000fV5mY\u0012\u000bG/\u0019*fG\u0016Lg/\u001a3"
)
public class PythonBroadcast implements Serializable, Logging {
   private transient String path;
   private long broadcastId;
   private SocketAuthServer encryptionServer;
   private SocketAuthServer decryptionServer;
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

   public String path() {
      return this.path;
   }

   public void path_$eq(final String x$1) {
      this.path = x$1;
   }

   private long broadcastId() {
      return this.broadcastId;
   }

   private void broadcastId_$eq(final long x$1) {
      this.broadcastId = x$1;
   }

   private SocketAuthServer encryptionServer() {
      return this.encryptionServer;
   }

   private void encryptionServer_$eq(final SocketAuthServer x$1) {
      this.encryptionServer = x$1;
   }

   private SocketAuthServer decryptionServer() {
      return this.decryptionServer;
   }

   private void decryptionServer_$eq(final SocketAuthServer x$1) {
      this.decryptionServer = x$1;
   }

   private void writeObject(final ObjectOutputStream out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcJ.sp)() -> {
         out.writeLong(this.broadcastId());
         FileInputStream in = new FileInputStream(new File(this.path()));

         long var10000;
         try {
            var10000 = Utils$.MODULE$.copyStream(in, out, Utils$.MODULE$.copyStream$default$3(), Utils$.MODULE$.copyStream$default$4());
         } finally {
            in.close();
         }

         return var10000;
      });
   }

   private void readObject(final ObjectInputStream in) {
      this.broadcastId_$eq(in.readLong());
      BroadcastBlockId blockId = new BroadcastBlockId(this.broadcastId(), "python");
      BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
      DiskBlockManager diskBlockManager = blockManager.diskBlockManager();
      if (!diskBlockManager.containsBlock(blockId)) {
         Utils$.MODULE$.tryOrIOException((JFunction0.mcZ.sp)() -> {
            File dir = new File(Utils$.MODULE$.getLocalDir(SparkEnv$.MODULE$.get().conf()));
            File file = File.createTempFile("broadcast", "", dir);
            FileOutputStream out = new FileOutputStream(file);
            return BoxesRunTime.unboxToBoolean(Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcZ.sp)() -> {
               long size = Utils$.MODULE$.copyStream(in, out, Utils$.MODULE$.copyStream$default$3(), Utils$.MODULE$.copyStream$default$4());
               ClassTag ct = (ClassTag).MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.Object());
               BlockManager.TempFileBasedBlockStoreUpdater blockStoreUpdater = blockManager.new TempFileBasedBlockStoreUpdater(blockId, org.apache.spark.storage.StorageLevel..MODULE$.DISK_ONLY(), ct, file, size, blockManager.TempFileBasedBlockStoreUpdater().apply$default$6(), blockManager.TempFileBasedBlockStoreUpdater().apply$default$7());
               return blockStoreUpdater.save();
            }, (JFunction0.mcV.sp)() -> out.close()));
         });
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.path_$eq(diskBlockManager.getFile((BlockId)blockId).getAbsolutePath());
   }

   public void setBroadcastId(final long bid) {
      this.broadcastId_$eq(bid);
   }

   public Object[] setupEncryptionServer() {
      this.encryptionServer_$eq(new SocketAuthServer() {
         // $FF: synthetic field
         private final PythonBroadcast $outer;

         public void handleConnection(final Socket sock) {
            SparkEnv env = SparkEnv$.MODULE$.get();
            InputStream in = sock.getInputStream();
            String abspath = (new File(this.$outer.path())).getAbsolutePath();
            OutputStream out = env.serializerManager().wrapForEncryption((OutputStream)(new FileOutputStream(abspath)));
            DechunkedInputStream$.MODULE$.dechunkAndCopyToOutput(in, out);
         }

         public {
            if (PythonBroadcast.this == null) {
               throw null;
            } else {
               this.$outer = PythonBroadcast.this;
            }
         }
      });
      return new Object[]{BoxesRunTime.boxToInteger(this.encryptionServer().port()), this.encryptionServer().secret()};
   }

   public Object[] setupDecryptionServer() {
      this.decryptionServer_$eq(new SocketAuthServer() {
         // $FF: synthetic field
         private final PythonBroadcast $outer;

         public void handleConnection(final Socket sock) {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
               InputStream in = SparkEnv$.MODULE$.get().serializerManager().wrapForEncryption((InputStream)(new FileInputStream(this.$outer.path())));
               Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcJ.sp)() -> Utils$.MODULE$.copyStream(in, out, false, Utils$.MODULE$.copyStream$default$4()), (JFunction0.mcV.sp)() -> in.close());
               out.flush();
            }, (JFunction0.mcV.sp)() -> JavaUtils.closeQuietly(out));
         }

         public {
            if (PythonBroadcast.this == null) {
               throw null;
            } else {
               this.$outer = PythonBroadcast.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      return new Object[]{BoxesRunTime.boxToInteger(this.decryptionServer().port()), this.decryptionServer().secret()};
   }

   public void waitTillBroadcastDataSent() {
      this.decryptionServer().getResult();
   }

   public void waitTillDataReceived() {
      this.encryptionServer().getResult();
   }

   public PythonBroadcast(final String path) {
      this.path = path;
      super();
      Logging.$init$(this);
      this.encryptionServer = null;
      this.decryptionServer = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
