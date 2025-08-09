package org.apache.parquet.hadoop.codec;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanUtil {
   private static final Logger logger = LoggerFactory.getLogger(CleanUtil.class);
   private static final Object unsafe;
   private static final Method cleanerMethod;
   private static final Method cleanMethod;
   private static final Method invokeCleanerMethod;
   private static final int majorVersion = Integer.parseInt(System.getProperty("java.version").split("\\D+")[0]);

   private CleanUtil() {
   }

   public static void cleanDirectBuffer(ByteBuffer buf) {
      if (cleanMethod != null) {
         try {
            cleanMethod.invoke(cleanerMethod.invoke(buf));
         } catch (IllegalArgumentException | InvocationTargetException | SecurityException | IllegalAccessException e) {
            logger.warn("Error while cleaning up the DirectBuffer", e);
         }
      } else if (invokeCleanerMethod != null) {
         try {
            invokeCleanerMethod.invoke(unsafe, buf);
         } catch (IllegalArgumentException | InvocationTargetException | SecurityException | IllegalAccessException e) {
            logger.warn("Error while cleaning up the DirectBuffer", e);
         }
      }

   }

   static {
      ByteBuffer tempBuffer = ByteBuffer.allocateDirect(0);
      Method cleanerMethodLocal = null;
      Method cleanMethodLocal = null;
      Object unsafeLocal = null;
      Method invokeCleanerMethodLocal = null;
      if (majorVersion >= 9) {
         try {
            Class<?> clazz = Class.forName("sun.misc.Unsafe");
            Field theUnsafe = clazz.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafeLocal = theUnsafe.get((Object)null);
            invokeCleanerMethodLocal = clazz.getMethod("invokeCleaner", ByteBuffer.class);
            invokeCleanerMethodLocal.invoke(unsafeLocal, tempBuffer);
         } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            logger.warn("Cannot use direct ByteBuffer cleaner, memory leaking may occur", e);
            unsafeLocal = null;
            invokeCleanerMethodLocal = null;
         }
      } else {
         try {
            cleanerMethodLocal = tempBuffer.getClass().getMethod("cleaner");
            cleanerMethodLocal.setAccessible(true);
            Object cleanerObject = cleanerMethodLocal.invoke(tempBuffer);
            cleanMethodLocal = cleanerObject.getClass().getMethod("clean");
            cleanMethodLocal.invoke(cleanerObject);
         } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            logger.warn("Cannot use direct ByteBuffer cleaner, memory leaking may occur", e);
            cleanerMethodLocal = null;
            cleanMethodLocal = null;
         }
      }

      cleanerMethod = cleanerMethodLocal;
      cleanMethod = cleanMethodLocal;
      unsafe = unsafeLocal;
      invokeCleanerMethod = invokeCleanerMethodLocal;
   }
}
