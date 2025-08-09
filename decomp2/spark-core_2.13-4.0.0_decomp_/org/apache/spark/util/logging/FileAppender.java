package org.apache.spark.util.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!B\u000e\u001d\u0001\u00012\u0003\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011u\u0002!\u0011!Q\u0001\nyB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006IA\u0011\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\r\")\u0011\n\u0001C\u0001\u0015\"9\u0011\u000b\u0001a\u0001\n\u0013\u0011\u0006b\u0002,\u0001\u0001\u0004%Ia\u0016\u0005\u0007;\u0002\u0001\u000b\u0015B*\t\u000f\t\u0004\u0001\u0019!C\u0005G\"9A\r\u0001a\u0001\n\u0013)\u0007BB4\u0001A\u0003&a\tC\u0004j\u0001\t\u0007I\u0011\u00026\t\rE\u0004\u0001\u0015!\u0003l\u0011\u0015\u0011\b\u0001\"\u0001t\u0011\u0015!\b\u0001\"\u0001t\u0011\u0015)\b\u0001\"\u0005t\u0011\u00151\b\u0001\"\u0005x\u0011\u0019\t)\u0001\u0001C\tg\"1\u0011q\u0001\u0001\u0005\u0012M<\u0001\"!\u0003\u001d\u0011\u0003\u0001\u00131\u0002\u0004\b7qA\t\u0001IA\u0007\u0011\u0019IU\u0003\"\u0001\u0002\u0010!9\u0011\u0011C\u000b\u0005\u0002\u0005M\u0001\"CA\u0014+E\u0005I\u0011AA\u0015\u0011%\ty$FI\u0001\n\u0003\t\t\u0005C\u0005\u0002FU\t\n\u0011\"\u0001\u0002*\taa)\u001b7f\u0003B\u0004XM\u001c3fe*\u0011QDH\u0001\bY><w-\u001b8h\u0015\ty\u0002%\u0001\u0003vi&d'BA\u0011#\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019C%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002K\u0005\u0019qN]4\u0014\u0007\u00019S\u0006\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0003]Ej\u0011a\f\u0006\u0003a\u0001\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003e=\u0012q\u0001T8hO&tw-A\u0006j]B,Ho\u0015;sK\u0006l7\u0001\u0001\t\u0003mmj\u0011a\u000e\u0006\u0003qe\n!![8\u000b\u0003i\nAA[1wC&\u0011Ah\u000e\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW.\u0001\u0003gS2,\u0007C\u0001\u001c@\u0013\t\u0001uG\u0001\u0003GS2,\u0017A\u00032vM\u001a,'oU5{KB\u0011\u0001fQ\u0005\u0003\t&\u00121!\u00138u\u00031\u0019Gn\\:f'R\u0014X-Y7t!\tAs)\u0003\u0002IS\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\bF\u0003L\u001b:{\u0005\u000b\u0005\u0002M\u00015\tA\u0004C\u00034\u000b\u0001\u0007Q\u0007C\u0003>\u000b\u0001\u0007a\bC\u0004B\u000bA\u0005\t\u0019\u0001\"\t\u000f\u0015+\u0001\u0013!a\u0001\r\u0006aq.\u001e;qkR\u001cFO]3b[V\t1\u000b\u0005\u00027)&\u0011Qk\u000e\u0002\u0011\r&dWmT;uaV$8\u000b\u001e:fC6\f\u0001c\\;uaV$8\u000b\u001e:fC6|F%Z9\u0015\u0005a[\u0006C\u0001\u0015Z\u0013\tQ\u0016F\u0001\u0003V]&$\bb\u0002/\b\u0003\u0003\u0005\raU\u0001\u0004q\u0012\n\u0014!D8viB,Ho\u0015;sK\u0006l\u0007\u0005\u000b\u0002\t?B\u0011\u0001\u0006Y\u0005\u0003C&\u0012\u0001B^8mCRLG.Z\u0001\u000e[\u0006\u00148.\u001a3G_J\u001cFo\u001c9\u0016\u0003\u0019\u000b\u0011#\\1sW\u0016$gi\u001c:Ti>\u0004x\fJ3r)\tAf\rC\u0004]\u0015\u0005\u0005\t\u0019\u0001$\u0002\u001d5\f'o[3e\r>\u00148\u000b^8qA!\u00121bX\u0001\u000eoJLG/\u001b8h)\"\u0014X-\u00193\u0016\u0003-\u0004\"\u0001\\8\u000e\u00035T!A\\\u001d\u0002\t1\fgnZ\u0005\u0003a6\u0014a\u0001\u00165sK\u0006$\u0017AD<sSRLgn\u001a+ie\u0016\fG\rI\u0001\u0011C^\f\u0017\u000e\u001e+fe6Lg.\u0019;j_:$\u0012\u0001W\u0001\u0005gR|\u0007/\u0001\nbaB,g\u000eZ*ue\u0016\fW\u000eV8GS2,\u0017\u0001D1qa\u0016tG\rV8GS2,G\u0003\u0002-y\u0003\u0003AQ!_\tA\u0002i\fQAY=uKN\u00042\u0001K>~\u0013\ta\u0018FA\u0003BeJ\f\u0017\u0010\u0005\u0002)}&\u0011q0\u000b\u0002\u0005\u0005f$X\r\u0003\u0004\u0002\u0004E\u0001\rAQ\u0001\u0004Y\u0016t\u0017\u0001C8qK:4\u0015\u000e\\3\u0002\u0013\rdwn]3GS2,\u0017\u0001\u0004$jY\u0016\f\u0005\u000f]3oI\u0016\u0014\bC\u0001'\u0016'\r)r%\f\u000b\u0003\u0003\u0017\tQ!\u00199qYf$\u0012bSA\u000b\u0003/\tI\"!\n\t\u000bM:\u0002\u0019A\u001b\t\u000bu:\u0002\u0019\u0001 \t\u000f\u0005mq\u00031\u0001\u0002\u001e\u0005!1m\u001c8g!\u0011\ty\"!\t\u000e\u0003\u0001J1!a\t!\u0005%\u0019\u0006/\u0019:l\u0007>tg\rC\u0004F/A\u0005\t\u0019\u0001$\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIQ*\"!a\u000b+\u0007\u0019\u000bic\u000b\u0002\u00020A!\u0011\u0011GA\u001e\u001b\t\t\u0019D\u0003\u0003\u00026\u0005]\u0012!C;oG\",7m[3e\u0015\r\tI$K\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u001f\u0003g\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u00111\t\u0016\u0004\u0005\u00065\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C\u0007"
)
public class FileAppender implements Logging {
   private final InputStream inputStream;
   public final File org$apache$spark$util$logging$FileAppender$$file;
   private final int bufferSize;
   private final boolean closeStreams;
   private volatile FileOutputStream outputStream;
   private volatile boolean markedForStop;
   private final Thread writingThread;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$4() {
      return FileAppender$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return FileAppender$.MODULE$.$lessinit$greater$default$3();
   }

   public static boolean apply$default$4() {
      return FileAppender$.MODULE$.apply$default$4();
   }

   public static FileAppender apply(final InputStream inputStream, final File file, final SparkConf conf, final boolean closeStreams) {
      return FileAppender$.MODULE$.apply(inputStream, file, conf, closeStreams);
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

   private FileOutputStream outputStream() {
      return this.outputStream;
   }

   private void outputStream_$eq(final FileOutputStream x$1) {
      this.outputStream = x$1;
   }

   private boolean markedForStop() {
      return this.markedForStop;
   }

   private void markedForStop_$eq(final boolean x$1) {
      this.markedForStop = x$1;
   }

   private Thread writingThread() {
      return this.writingThread;
   }

   public void awaitTermination() {
      this.writingThread().join();
   }

   public void stop() {
      this.markedForStop_$eq(true);
   }

   public void appendStreamToFile() {
      try {
         this.logDebug((Function0)(() -> "Started appending thread"));
         Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
            this.openFile();
            byte[] buf = new byte[this.bufferSize];
            int n = 0;

            while(!this.markedForStop() && n != -1) {
               try {
                  n = this.inputStream.read(buf);
               } catch (Throwable var6) {
                  if (!(var6 instanceof IOException) || !this.markedForStop()) {
                     throw var6;
                  }

                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               if (n > 0) {
                  this.appendToFile(buf, n);
               }
            }

         }, (JFunction0.mcV.sp)() -> {
            try {
               if (this.closeStreams) {
                  this.inputStream.close();
               }
            } finally {
               this.closeFile();
            }

         });
      } catch (Exception var2) {
         this.logError((LogEntry).MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error writing stream to file ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$util$logging$FileAppender$$file)})))), var2);
      }

   }

   public void appendToFile(final byte[] bytes, final int len) {
      if (this.outputStream() == null) {
         this.openFile();
      }

      this.outputStream().write(bytes, 0, len);
   }

   public void openFile() {
      this.outputStream_$eq(new FileOutputStream(this.org$apache$spark$util$logging$FileAppender$$file, true));
      this.logDebug((Function0)(() -> "Opened file " + this.org$apache$spark$util$logging$FileAppender$$file));
   }

   public void closeFile() {
      this.outputStream().flush();
      this.outputStream().close();
      this.logDebug((Function0)(() -> "Closed file " + this.org$apache$spark$util$logging$FileAppender$$file));
   }

   public FileAppender(final InputStream inputStream, final File file, final int bufferSize, final boolean closeStreams) {
      this.inputStream = inputStream;
      this.org$apache$spark$util$logging$FileAppender$$file = file;
      this.bufferSize = bufferSize;
      this.closeStreams = closeStreams;
      Logging.$init$(this);
      this.outputStream = null;
      this.markedForStop = false;
      this.writingThread = new Thread() {
         // $FF: synthetic field
         private final FileAppender $outer;

         public void run() {
            Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> this.$outer.appendStreamToFile());
         }

         public {
            if (FileAppender.this == null) {
               throw null;
            } else {
               this.$outer = FileAppender.this;
               this.setDaemon(true);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.writingThread().start();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
