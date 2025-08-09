package org.apache.spark.network.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.io.IOException;
import java.io.InputStream;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class NettyLogger {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(NettyLogger.class);
   private final LoggingHandler loggingHandler;

   public NettyLogger() {
      if (logger.isTraceEnabled()) {
         this.loggingHandler = new LoggingHandler(NettyLogger.class, LogLevel.TRACE);
      } else if (logger.isDebugEnabled()) {
         this.loggingHandler = new NoContentLoggingHandler(NettyLogger.class, LogLevel.DEBUG);
      } else {
         this.loggingHandler = null;
      }

   }

   public LoggingHandler getLoggingHandler() {
      return this.loggingHandler;
   }

   private static class NoContentLoggingHandler extends LoggingHandler {
      NoContentLoggingHandler(Class clazz, LogLevel level) {
         super(clazz, level);
      }

      protected String format(ChannelHandlerContext ctx, String eventName, Object arg) {
         if (arg instanceof ByteBuf byteBuf) {
            String var11 = this.format(ctx, eventName);
            return var11 + " " + byteBuf.readableBytes() + "B";
         } else if (arg instanceof ByteBufHolder byteBufHolder) {
            String var10 = this.format(ctx, eventName);
            return var10 + " " + byteBufHolder.content().readableBytes() + "B";
         } else if (arg instanceof InputStream inputStream) {
            int available = -1;

            try {
               available = inputStream.available();
            } catch (IOException var9) {
            }

            String var10000 = this.format(ctx, eventName, arg);
            return var10000 + " " + available + "B";
         } else {
            return super.format(ctx, eventName, arg);
         }
      }
   }
}
