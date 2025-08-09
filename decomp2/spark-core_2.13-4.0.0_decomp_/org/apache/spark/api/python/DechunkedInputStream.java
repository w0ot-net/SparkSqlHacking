package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Qa\u0004\t\u0001)iA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\u0006W\u0001!\t\u0001\f\u0005\ba\u0001\u0011\r\u0011\"\u00032\u0011\u0019)\u0004\u0001)A\u0005e!9a\u0007\u0001a\u0001\n\u00139\u0004b\u0002 \u0001\u0001\u0004%Ia\u0010\u0005\u0007\u000b\u0002\u0001\u000b\u0015\u0002\u001d\t\u000b\u0019\u0003A\u0011I$\t\u000b\u0019\u0003A\u0011\t%\t\u000bU\u0003A\u0011\t,\b\r]\u0003\u0002\u0012\u0001\u000bY\r\u0019y\u0001\u0003#\u0001\u00153\")1\u0006\u0004C\u0001;\")a\f\u0004C\u0001?\n!B)Z2ik:\\W\rZ%oaV$8\u000b\u001e:fC6T!!\u0005\n\u0002\rALH\u000f[8o\u0015\t\u0019B#A\u0002ba&T!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\n\u0004\u0001m\u0019\u0003C\u0001\u000f\"\u001b\u0005i\"B\u0001\u0010 \u0003\tIwNC\u0001!\u0003\u0011Q\u0017M^1\n\u0005\tj\"aC%oaV$8\u000b\u001e:fC6\u0004\"\u0001J\u0014\u000e\u0003\u0015R!A\n\u000b\u0002\u0011%tG/\u001a:oC2L!\u0001K\u0013\u0003\u000f1{wmZ5oO\u00069qO]1qa\u0016$7\u0001A\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00055z\u0003C\u0001\u0018\u0001\u001b\u0005\u0001\u0002\"B\u0015\u0003\u0001\u0004Y\u0012a\u00013j]V\t!\u0007\u0005\u0002\u001dg%\u0011A'\b\u0002\u0010\t\u0006$\u0018-\u00138qkR\u001cFO]3b[\u0006!A-\u001b8!\u0003A\u0011X-\\1j]&tw-\u00138DQVt7.F\u00019!\tID(D\u0001;\u0015\u0005Y\u0014!B:dC2\f\u0017BA\u001f;\u0005\rIe\u000e^\u0001\u0015e\u0016l\u0017-\u001b8j]\u001eLen\u00115v].|F%Z9\u0015\u0005\u0001\u001b\u0005CA\u001dB\u0013\t\u0011%H\u0001\u0003V]&$\bb\u0002#\u0007\u0003\u0003\u0005\r\u0001O\u0001\u0004q\u0012\n\u0014!\u0005:f[\u0006Lg.\u001b8h\u0013:\u001c\u0005.\u001e8lA\u0005!!/Z1e)\u0005AD\u0003\u0002\u001dJ#NCQAS\u0005A\u0002-\u000bA\u0001Z3tiB\u0019\u0011\b\u0014(\n\u00055S$!B!se\u0006L\bCA\u001dP\u0013\t\u0001&H\u0001\u0003CsR,\u0007\"\u0002*\n\u0001\u0004A\u0014aA8gM\")A+\u0003a\u0001q\u0005\u0019A.\u001a8\u0002\u000b\rdwn]3\u0015\u0003\u0001\u000bA\u0003R3dQVt7.\u001a3J]B,Ho\u0015;sK\u0006l\u0007C\u0001\u0018\r'\ta!\f\u0005\u0002:7&\u0011AL\u000f\u0002\u0007\u0003:L(+\u001a4\u0015\u0003a\u000ba\u0003Z3dQVt7.\u00118e\u0007>\u0004\u0018\u0010V8PkR\u0004X\u000f\u001e\u000b\u0004\u0001\u0002\u0014\u0007\"B1\u000f\u0001\u0004Y\u0012aB2ik:\\W\r\u001a\u0005\u0006G:\u0001\r\u0001Z\u0001\u0004_V$\bC\u0001\u000ff\u0013\t1WD\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000e"
)
public class DechunkedInputStream extends InputStream implements Logging {
   private final InputStream wrapped;
   private final DataInputStream din;
   private int remainingInChunk;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void dechunkAndCopyToOutput(final InputStream chunked, final OutputStream out) {
      DechunkedInputStream$.MODULE$.dechunkAndCopyToOutput(chunked, out);
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

   private DataInputStream din() {
      return this.din;
   }

   private int remainingInChunk() {
      return this.remainingInChunk;
   }

   private void remainingInChunk_$eq(final int x$1) {
      this.remainingInChunk = x$1;
   }

   public int read() {
      byte[] into = new byte[1];
      int n = this.read(into, 0, 1);
      if (n == -1) {
         return -1;
      } else {
         byte b = into[0];
         return b < 0 ? 256 + b : b;
      }
   }

   public int read(final byte[] dest, final int off, final int len) {
      if (this.remainingInChunk() == -1) {
         return -1;
      } else {
         int destSpace = len;
         int destPos = off;

         while(destSpace > 0 && this.remainingInChunk() != -1) {
            int toCopy = .MODULE$.min(this.remainingInChunk(), destSpace);
            int read = this.din().read(dest, destPos, toCopy);
            destPos += read;
            destSpace -= read;
            this.remainingInChunk_$eq(this.remainingInChunk() - read);
            if (this.remainingInChunk() == 0) {
               this.remainingInChunk_$eq(this.din().readInt());
            }
         }

         scala.Predef..MODULE$.assert(destSpace == 0 || this.remainingInChunk() == -1);
         return destPos - off;
      }
   }

   public void close() {
      this.wrapped.close();
   }

   public DechunkedInputStream(final InputStream wrapped) {
      this.wrapped = wrapped;
      Logging.$init$(this);
      this.din = new DataInputStream(wrapped);
      this.remainingInChunk = this.din().readInt();
   }
}
