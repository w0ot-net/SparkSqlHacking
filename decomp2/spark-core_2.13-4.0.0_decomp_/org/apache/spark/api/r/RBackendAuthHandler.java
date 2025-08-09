package org.apache.spark.api.r;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Predef;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3A!\u0002\u0004\u0005#!A1\u0006\u0001B\u0001B\u0003%A\u0006C\u00038\u0001\u0011\u0005\u0001\bC\u0003=\u0001\u0011\u0005S\bC\u0003I\u0001\u0011%\u0011JA\nS\u0005\u0006\u001c7.\u001a8e\u0003V$\b\u000eS1oI2,'O\u0003\u0002\b\u0011\u0005\t!O\u0003\u0002\n\u0015\u0005\u0019\u0011\r]5\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001%\u0015\u00022a\u0005\u000e\u001d\u001b\u0005!\"BA\u000b\u0017\u0003\u001d\u0019\u0007.\u00198oK2T!a\u0006\r\u0002\u000b9,G\u000f^=\u000b\u0003e\t!![8\n\u0005m!\"aG*j[BdWm\u00115b]:,G.\u00138c_VtG\rS1oI2,'\u000fE\u0002\u001eA\tj\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003;\rJ!\u0001\n\u0010\u0003\t\tKH/\u001a\t\u0003M%j\u0011a\n\u0006\u0003Q)\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003U\u001d\u0012q\u0001T8hO&tw-\u0001\u0004tK\u000e\u0014X\r\u001e\t\u0003[Qr!A\f\u001a\u0011\u0005=rR\"\u0001\u0019\u000b\u0005E\u0002\u0012A\u0002\u001fs_>$h(\u0003\u00024=\u00051\u0001K]3eK\u001aL!!\u000e\u001c\u0003\rM#(/\u001b8h\u0015\t\u0019d$\u0001\u0004=S:LGO\u0010\u000b\u0003sm\u0002\"A\u000f\u0001\u000e\u0003\u0019AQa\u000b\u0002A\u00021\nAb\u00195b]:,GNU3bIB\"2AP!G!\tir(\u0003\u0002A=\t!QK\\5u\u0011\u0015\u00115\u00011\u0001D\u0003\r\u0019G\u000f\u001f\t\u0003'\u0011K!!\u0012\u000b\u0003+\rC\u0017M\u001c8fY\"\u000bg\u000e\u001a7fe\u000e{g\u000e^3yi\")qi\u0001a\u00019\u0005\u0019Qn]4\u0002\u0015]\u0014\u0018\u000e^3SKBd\u0017\u0010F\u0002?\u00152CQa\u0013\u0003A\u00021\nQA]3qYfDQ!\u0014\u0003A\u00029\u000bAa\u00195b]B\u00111cT\u0005\u0003!R\u0011qa\u00115b]:,G\u000e"
)
public class RBackendAuthHandler extends SimpleChannelInboundHandler implements Logging {
   private final String secret;
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

   public void channelRead0(final ChannelHandlerContext ctx, final byte[] msg) {
      String clientSecret = new String(msg, 0, msg.length - 1, StandardCharsets.UTF_8);

      try {
         boolean var7;
         Predef var10000;
         label23: {
            label22: {
               var10000 = .MODULE$;
               String var10001 = this.secret;
               if (var10001 == null) {
                  if (clientSecret == null) {
                     break label22;
                  }
               } else if (var10001.equals(clientSecret)) {
                  break label22;
               }

               var7 = false;
               break label23;
            }

            var7 = true;
         }

         var10000.require(var7, () -> "Auth secret mismatch.");
         ctx.pipeline().remove(this);
         this.writeReply("ok", ctx.channel());
      } catch (Exception var6) {
         this.logInfo((Function0)(() -> "Authentication failure."), var6);
         this.writeReply("err", ctx.channel());
         ctx.close();
      }

   }

   private void writeReply(final String reply, final Channel chan) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      SerDe$.MODULE$.writeString(new DataOutputStream(out), reply);
      chan.writeAndFlush(out.toByteArray());
   }

   public RBackendAuthHandler(final String secret) {
      this.secret = secret;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
