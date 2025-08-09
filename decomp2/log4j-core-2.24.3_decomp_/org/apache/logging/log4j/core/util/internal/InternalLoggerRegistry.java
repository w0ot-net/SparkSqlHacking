package org.apache.logging.log4j.core.util.internal;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.status.StatusLogger;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public final class InternalLoggerRegistry {
   private final Map loggerRefByNameByMessageFactory = new WeakHashMap();
   private final ReadWriteLock lock = new ReentrantReadWriteLock();
   private final Lock readLock;
   private final Lock writeLock;

   public InternalLoggerRegistry() {
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
   }

   public @Nullable Logger getLogger(final String name, final MessageFactory messageFactory) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(messageFactory, "messageFactory");
      this.readLock.lock();

      Logger var3;
      try {
         var3 = (Logger)Optional.of(this.loggerRefByNameByMessageFactory).map((loggerRefByNameByMessageFactory) -> (Map)loggerRefByNameByMessageFactory.get(messageFactory)).map((loggerRefByName) -> (WeakReference)loggerRefByName.get(name)).map(Reference::get).orElse((Object)null);
      } finally {
         this.readLock.unlock();
      }

      return var3;
   }

   public Collection getLoggers() {
      this.readLock.lock();

      Collection var1;
      try {
         var1 = (Collection)this.loggerRefByNameByMessageFactory.values().stream().flatMap((loggerRefByName) -> loggerRefByName.values().stream()).flatMap((loggerRef) -> {
            Logger logger = (Logger)loggerRef.get();
            return logger != null ? Stream.of(logger) : Stream.empty();
         }).collect(Collectors.toList());
      } finally {
         this.readLock.unlock();
      }

      return var1;
   }

   public boolean hasLogger(final String name, final MessageFactory messageFactory) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(messageFactory, "messageFactory");
      return this.getLogger(name, messageFactory) != null;
   }

   public boolean hasLogger(final String name, final Class messageFactoryClass) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(messageFactoryClass, "messageFactoryClass");
      this.readLock.lock();

      boolean var3;
      try {
         var3 = this.loggerRefByNameByMessageFactory.entrySet().stream().filter((entry) -> messageFactoryClass.equals(((MessageFactory)entry.getKey()).getClass())).anyMatch((entry) -> ((Map)entry.getValue()).containsKey(name));
      } finally {
         this.readLock.unlock();
      }

      return var3;
   }

   public Logger computeIfAbsent(final String name, final MessageFactory messageFactory, final BiFunction loggerSupplier) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(messageFactory, "messageFactory");
      Objects.requireNonNull(loggerSupplier, "loggerSupplier");
      Logger logger = this.getLogger(name, messageFactory);
      if (logger != null) {
         return logger;
      } else {
         this.writeLock.lock();

         Logger var7;
         try {
            Map<String, WeakReference<Logger>> loggerRefByName = (Map)this.loggerRefByNameByMessageFactory.computeIfAbsent(messageFactory, (ignored) -> new HashMap());
            WeakReference<Logger> loggerRef = (WeakReference)loggerRefByName.get(name);
            if (loggerRef == null || (logger = (Logger)loggerRef.get()) == null) {
               logger = (Logger)loggerSupplier.apply(name, messageFactory);
               String loggerName = logger.getName();
               MessageFactory loggerMessageFactory = logger.getMessageFactory();
               if (!loggerMessageFactory.equals(messageFactory)) {
                  StatusLogger.getLogger().error("Newly registered logger with name `{}` and message factory `{}`, is requested to be associated with a different name `{}` or message factory `{}`.\nEffectively the message factory of the logger will be used and the other one will be ignored.\nThis generally hints a problem at the logger context implementation.\nPlease report this using the Log4j project issue tracker.", loggerName, loggerMessageFactory, name, messageFactory);
                  ((Map)this.loggerRefByNameByMessageFactory.computeIfAbsent(loggerMessageFactory, (ignored) -> new HashMap())).putIfAbsent(loggerName, new WeakReference(logger));
               }

               loggerRefByName.put(name, new WeakReference(logger));
               Logger var9 = logger;
               return var9;
            }

            var7 = logger;
         } finally {
            this.writeLock.unlock();
         }

         return var7;
      }
   }
}
