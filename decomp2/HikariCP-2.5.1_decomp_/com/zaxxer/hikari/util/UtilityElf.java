package com.zaxxer.hikari.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class UtilityElf {
   public static String getNullIfEmpty(String text) {
      return text == null ? null : (text.trim().isEmpty() ? null : text.trim());
   }

   public static void quietlySleep(long millis) {
      try {
         Thread.sleep(millis);
      } catch (InterruptedException var3) {
      }

   }

   public static Object createInstance(String className, Class clazz, Object... args) {
      if (className == null) {
         return null;
      } else {
         try {
            Class<?> loaded = UtilityElf.class.getClassLoader().loadClass(className);
            if (args.length == 0) {
               return clazz.cast(loaded.newInstance());
            } else {
               Class<?>[] argClasses = new Class[args.length];

               for(int i = 0; i < args.length; ++i) {
                  argClasses[i] = args[i].getClass();
               }

               Constructor<?> constructor = loaded.getConstructor(argClasses);
               return clazz.cast(constructor.newInstance(args));
            }
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public static ThreadPoolExecutor createThreadPoolExecutor(int queueSize, String threadName, ThreadFactory threadFactory, RejectedExecutionHandler policy) {
      if (threadFactory == null) {
         threadFactory = new DefaultThreadFactory(threadName, true);
      }

      LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue(queueSize);
      ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, queue, threadFactory, policy);
      executor.allowCoreThreadTimeOut(true);
      return executor;
   }

   public static int getTransactionIsolation(String transactionIsolationName) {
      if (transactionIsolationName != null) {
         try {
            String upperName = transactionIsolationName.toUpperCase(Locale.ENGLISH);
            if (upperName.startsWith("TRANSACTION_")) {
               Field field = Connection.class.getField(upperName);
               return field.getInt((Object)null);
            } else {
               int level = Integer.parseInt(transactionIsolationName);
               switch (level) {
                  case 0:
                  case 1:
                  case 2:
                  case 4:
                  case 8:
                     return level;
                  case 3:
                  case 5:
                  case 6:
                  case 7:
                  default:
                     throw new IllegalArgumentException();
               }
            }
         } catch (Exception var3) {
            throw new IllegalArgumentException("Invalid transaction isolation value: " + transactionIsolationName);
         }
      } else {
         return -1;
      }
   }

   public static final class DefaultThreadFactory implements ThreadFactory {
      private final String threadName;
      private final boolean daemon;

      public DefaultThreadFactory(String threadName, boolean daemon) {
         this.threadName = threadName;
         this.daemon = daemon;
      }

      public Thread newThread(Runnable r) {
         Thread thread = new Thread(r, this.threadName);
         thread.setDaemon(this.daemon);
         return thread;
      }
   }
}
