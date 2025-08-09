package org.apache.logging.log4j.spi;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.jspecify.annotations.Nullable;

public class LoggerRegistry {
   private final Map loggerByMessageFactoryByName;
   private final ReadWriteLock lock;
   private final Lock readLock;
   private final Lock writeLock;

   public LoggerRegistry() {
      this.loggerByMessageFactoryByName = new HashMap();
      this.lock = new ReentrantReadWriteLock();
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
   }

   public LoggerRegistry(final MapFactory mapFactory) {
      this();
   }

   public @Nullable ExtendedLogger getLogger(final String name) {
      Objects.requireNonNull(name, "name");
      return this.getLogger(name, (MessageFactory)null);
   }

   public ExtendedLogger getLogger(final String name, final MessageFactory messageFactory) {
      Objects.requireNonNull(name, "name");
      this.readLock.lock();

      ExtendedLogger var5;
      try {
         Map<MessageFactory, T> loggerByMessageFactory = (Map)this.loggerByMessageFactoryByName.get(name);
         MessageFactory effectiveMessageFactory = (MessageFactory)(messageFactory != null ? messageFactory : ParameterizedMessageFactory.INSTANCE);
         var5 = loggerByMessageFactory == null ? null : (ExtendedLogger)loggerByMessageFactory.get(effectiveMessageFactory);
      } finally {
         this.readLock.unlock();
      }

      return var5;
   }

   public Collection getLoggers() {
      this.readLock.lock();

      Collection var1;
      try {
         var1 = (Collection)this.loggerByMessageFactoryByName.values().stream().flatMap((loggerByMessageFactory) -> loggerByMessageFactory.values().stream()).collect(Collectors.toList());
      } finally {
         this.readLock.unlock();
      }

      return var1;
   }

   public Collection getLoggers(final Collection destination) {
      Objects.requireNonNull(destination, "destination");
      destination.addAll(this.getLoggers());
      return destination;
   }

   public boolean hasLogger(final String name) {
      Objects.requireNonNull(name, "name");
      T logger = (T)this.getLogger(name);
      return logger != null;
   }

   public boolean hasLogger(final String name, final MessageFactory messageFactory) {
      Objects.requireNonNull(name, "name");
      T logger = (T)this.getLogger(name, messageFactory);
      return logger != null;
   }

   public boolean hasLogger(final String name, final Class messageFactoryClass) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(messageFactoryClass, "messageFactoryClass");
      this.readLock.lock();

      boolean var3;
      try {
         var3 = ((Map)this.loggerByMessageFactoryByName.getOrDefault(name, Collections.emptyMap())).keySet().stream().anyMatch((messageFactory) -> messageFactoryClass.equals(messageFactory.getClass()));
      } finally {
         this.readLock.unlock();
      }

      return var3;
   }

   public void putIfAbsent(final String name, final @Nullable MessageFactory messageFactory, final ExtendedLogger logger) {
      Objects.requireNonNull(name, "name");
      Objects.requireNonNull(logger, "logger");
      this.writeLock.lock();

      try {
         MessageFactory effectiveMessageFactory = (MessageFactory)(messageFactory != null ? messageFactory : ParameterizedMessageFactory.INSTANCE);
         ((Map)this.loggerByMessageFactoryByName.computeIfAbsent(name, this::createLoggerRefByMessageFactoryMap)).putIfAbsent(effectiveMessageFactory, logger);
         if (!name.equals(logger.getName()) || !effectiveMessageFactory.equals(logger.getMessageFactory())) {
            ((Map)this.loggerByMessageFactoryByName.computeIfAbsent(logger.getName(), this::createLoggerRefByMessageFactoryMap)).putIfAbsent(logger.getMessageFactory(), logger);
         }
      } finally {
         this.writeLock.unlock();
      }

   }

   private Map createLoggerRefByMessageFactoryMap(final String ignored) {
      return new WeakHashMap();
   }

   public static class ConcurrentMapFactory implements MapFactory {
      public Map createInnerMap() {
         return new ConcurrentHashMap();
      }

      public Map createOuterMap() {
         return new ConcurrentHashMap();
      }

      public void putIfAbsent(final Map innerMap, final String name, final ExtendedLogger logger) {
         innerMap.putIfAbsent(name, logger);
      }
   }

   public static class WeakMapFactory implements MapFactory {
      public Map createInnerMap() {
         return new WeakHashMap();
      }

      public Map createOuterMap() {
         return new WeakHashMap();
      }

      public void putIfAbsent(final Map innerMap, final String name, final ExtendedLogger logger) {
         innerMap.put(name, logger);
      }
   }

   public interface MapFactory {
      Map createInnerMap();

      Map createOuterMap();

      void putIfAbsent(Map innerMap, String name, ExtendedLogger logger);
   }
}
