package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public final class ReferenceCountUtil {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ReferenceCountUtil.class);

   public static Object retain(Object msg) {
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).retain() : msg;
   }

   public static Object retain(Object msg, int increment) {
      ObjectUtil.checkPositive(increment, "increment");
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).retain(increment) : msg;
   }

   public static Object touch(Object msg) {
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).touch() : msg;
   }

   public static Object touch(Object msg, Object hint) {
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).touch(hint) : msg;
   }

   public static boolean release(Object msg) {
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).release() : false;
   }

   public static boolean release(Object msg, int decrement) {
      ObjectUtil.checkPositive(decrement, "decrement");
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).release(decrement) : false;
   }

   public static void safeRelease(Object msg) {
      try {
         release(msg);
      } catch (Throwable t) {
         logger.warn("Failed to release a message: {}", msg, t);
      }

   }

   public static void safeRelease(Object msg, int decrement) {
      try {
         ObjectUtil.checkPositive(decrement, "decrement");
         release(msg, decrement);
      } catch (Throwable t) {
         if (logger.isWarnEnabled()) {
            logger.warn("Failed to release a message: {} (decrement: {})", msg, decrement, t);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public static Object releaseLater(Object msg) {
      return releaseLater(msg, 1);
   }

   /** @deprecated */
   @Deprecated
   public static Object releaseLater(Object msg, int decrement) {
      ObjectUtil.checkPositive(decrement, "decrement");
      if (msg instanceof ReferenceCounted) {
         ThreadDeathWatcher.watch(Thread.currentThread(), new ReleasingTask((ReferenceCounted)msg, decrement));
      }

      return msg;
   }

   public static int refCnt(Object msg) {
      return msg instanceof ReferenceCounted ? ((ReferenceCounted)msg).refCnt() : -1;
   }

   private ReferenceCountUtil() {
   }

   static {
      ResourceLeakDetector.addExclusions(ReferenceCountUtil.class, "touch");
   }

   private static final class ReleasingTask implements Runnable {
      private final ReferenceCounted obj;
      private final int decrement;

      ReleasingTask(ReferenceCounted obj, int decrement) {
         this.obj = obj;
         this.decrement = decrement;
      }

      public void run() {
         try {
            if (!this.obj.release(this.decrement)) {
               ReferenceCountUtil.logger.warn("Non-zero refCnt: {}", (Object)this);
            } else {
               ReferenceCountUtil.logger.debug("Released: {}", (Object)this);
            }
         } catch (Exception ex) {
            ReferenceCountUtil.logger.warn("Failed to release an object: {}", this.obj, ex);
         }

      }

      public String toString() {
         return StringUtil.simpleClassName((Object)this.obj) + ".release(" + this.decrement + ") refCnt: " + this.obj.refCnt();
      }
   }
}
