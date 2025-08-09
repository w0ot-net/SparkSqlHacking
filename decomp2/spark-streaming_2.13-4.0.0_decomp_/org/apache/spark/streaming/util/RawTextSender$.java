package org.apache.spark.streaming.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.slf4j.Logger;
import scala.Array;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple4;
import scala.Array.;
import scala.runtime.BoxesRunTime;

public final class RawTextSender$ implements Logging {
   public static final RawTextSender$ MODULE$ = new RawTextSender$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void main(final String[] args) {
      if (args.length != 4) {
         System.err.println("Usage: RawTextSender <port> <file> <blockSize> <bytesPerSec>");
         System.exit(1);
      }

      if (args != null) {
         Object var5 = .MODULE$.unapplySeq(args);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var5) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 4) == 0) {
            String var6 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 0);
            String file = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 1);
            String var8 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 2);
            String var9 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var5), 3);
            if (var6 != null) {
               Option var10 = org.apache.spark.util.IntParam..MODULE$.unapply(var6);
               if (!var10.isEmpty()) {
                  int port = BoxesRunTime.unboxToInt(var10.get());
                  if (var8 != null) {
                     Option var12 = org.apache.spark.util.IntParam..MODULE$.unapply(var8);
                     if (!var12.isEmpty()) {
                        int blockSize = BoxesRunTime.unboxToInt(var12.get());
                        if (var9 != null) {
                           Option var14 = org.apache.spark.util.IntParam..MODULE$.unapply(var9);
                           if (!var14.isEmpty()) {
                              int bytesPerSec = BoxesRunTime.unboxToInt(var14.get());
                              Tuple4 var3 = new Tuple4(BoxesRunTime.boxToInteger(port), file, BoxesRunTime.boxToInteger(blockSize), BoxesRunTime.boxToInteger(bytesPerSec));
                              int port = BoxesRunTime.unboxToInt(var3._1());
                              String file = (String)var3._2();
                              int blockSize = BoxesRunTime.unboxToInt(var3._3());
                              int bytesPerSec = BoxesRunTime.unboxToInt(var3._4());
                              String[] lines = (String[])org.apache.spark.util.Utils..MODULE$.tryWithResource(() -> scala.io.Source..MODULE$.fromFile(file, scala.io.Codec..MODULE$.fallbackSystemCodec()), (x$2) -> (String[])x$2.getLines().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
                              ByteArrayOutputStream bufferStream = new ByteArrayOutputStream(blockSize + 1000);
                              SerializerInstance ser = (new KryoSerializer(new SparkConf())).newInstance();
                              SerializationStream serStream = ser.serializeStream(bufferStream);

                              for(int i = 0; bufferStream.size() < blockSize; i = (i + 1) % lines.length) {
                                 serStream.writeObject(lines[i], scala.reflect.ClassTag..MODULE$.apply(String.class));
                              }

                              byte[] array = bufferStream.toByteArray();
                              ByteBuffer countBuf = ByteBuffer.wrap(new byte[4]);
                              countBuf.putInt(array.length);
                              countBuf.flip();
                              ServerSocket serverSocket = new ServerSocket(port);
                              this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listening on port ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(port))})))));

                              while(true) {
                                 Socket socket = serverSocket.accept();
                                 this.logInfo((Function0)(() -> "Got a new connection"));
                                 RateLimitedOutputStream out = new RateLimitedOutputStream(socket.getOutputStream(), bytesPerSec);

                                 try {
                                    while(true) {
                                       out.write(countBuf.array());
                                       out.write(array);
                                    }
                                 } catch (IOException var34) {
                                    this.logError((Function0)(() -> "Client disconnected"));
                                 } finally {
                                    socket.close();
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      throw new MatchError(args);
   }

   private RawTextSender$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
