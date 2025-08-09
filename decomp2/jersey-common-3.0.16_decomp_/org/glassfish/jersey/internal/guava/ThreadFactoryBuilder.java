package org.glassfish.jersey.internal.guava;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadFactoryBuilder {
   private String nameFormat = null;
   private Boolean daemon = null;
   private Integer priority = null;
   private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
   private ThreadFactory backingThreadFactory = null;

   private static ThreadFactory build(ThreadFactoryBuilder builder) {
      final String nameFormat = builder.nameFormat;
      final Boolean daemon = builder.daemon;
      final Integer priority = builder.priority;
      final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
      final ThreadFactory backingThreadFactory = builder.backingThreadFactory != null ? builder.backingThreadFactory : Executors.defaultThreadFactory();
      final AtomicLong count = nameFormat != null ? new AtomicLong(0L) : null;
      return new ThreadFactory() {
         public Thread newThread(Runnable runnable) {
            Thread thread = backingThreadFactory.newThread(runnable);
            if (nameFormat != null) {
               thread.setName(String.format(nameFormat, count.getAndIncrement()));
            }

            if (daemon != null) {
               thread.setDaemon(daemon);
            }

            if (priority != null) {
               thread.setPriority(priority);
            }

            if (uncaughtExceptionHandler != null) {
               thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }

            return thread;
         }
      };
   }

   public ThreadFactoryBuilder setNameFormat(String nameFormat) {
      String.format(nameFormat, 0);
      this.nameFormat = nameFormat;
      return this;
   }

   public ThreadFactoryBuilder setDaemon(boolean daemon) {
      this.daemon = daemon;
      return this;
   }

   public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
      this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler)Preconditions.checkNotNull(uncaughtExceptionHandler);
      return this;
   }

   public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
      this.backingThreadFactory = (ThreadFactory)Preconditions.checkNotNull(backingThreadFactory);
      return this;
   }

   public ThreadFactory build() {
      return build(this);
   }
}
