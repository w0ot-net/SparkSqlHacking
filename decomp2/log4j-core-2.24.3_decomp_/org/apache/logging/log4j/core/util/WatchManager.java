package org.apache.logging.log4j.core.util;

import aQute.bnd.annotation.spi.ServiceConsumer;
import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.config.ConfigurationFileWatcher;
import org.apache.logging.log4j.core.config.ConfigurationScheduler;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.apache.logging.log4j.util.Supplier;

@ServiceConsumer(
   value = WatchEventService.class,
   resolution = "optional",
   cardinality = "multiple"
)
public class WatchManager extends AbstractLifeCycle {
   private static final Logger logger = StatusLogger.getLogger();
   private final ConcurrentMap watchers = new ConcurrentHashMap();
   private int intervalSeconds = 0;
   private ScheduledFuture future;
   private final ConfigurationScheduler scheduler;
   private final List eventServiceList;
   private final UUID id = WatchManager.LocalUUID.get();

   public WatchManager(final ConfigurationScheduler scheduler) {
      this.scheduler = (ConfigurationScheduler)Objects.requireNonNull(scheduler, "scheduler");
      this.eventServiceList = (List)ServiceLoaderUtil.safeStream(WatchEventService.class, ServiceLoader.load(WatchEventService.class), logger).collect(Collectors.toList());
   }

   public void checkFiles() {
      (new WatchRunnable()).run();
   }

   public Map getConfigurationWatchers() {
      Map<Source, Watcher> map = new HashMap(this.watchers.size());

      for(Map.Entry entry : this.watchers.entrySet()) {
         map.put((Source)entry.getKey(), ((ConfigurationMonitor)entry.getValue()).getWatcher());
      }

      return map;
   }

   public UUID getId() {
      return this.id;
   }

   public int getIntervalSeconds() {
      return this.intervalSeconds;
   }

   /** @deprecated */
   @Deprecated
   public Map getWatchers() {
      Map<File, FileWatcher> map = new HashMap(this.watchers.size());

      for(Map.Entry entry : this.watchers.entrySet()) {
         if (((ConfigurationMonitor)entry.getValue()).getWatcher() instanceof ConfigurationFileWatcher) {
            map.put(((Source)entry.getKey()).getFile(), (FileWatcher)((ConfigurationMonitor)entry.getValue()).getWatcher());
         } else {
            map.put(((Source)entry.getKey()).getFile(), new WrappedFileWatcher((FileWatcher)((ConfigurationMonitor)entry.getValue()).getWatcher()));
         }
      }

      return map;
   }

   public boolean hasEventListeners() {
      return !this.eventServiceList.isEmpty();
   }

   private String millisToString(final long millis) {
      return (new Date(millis)).toString();
   }

   public void reset() {
      logger.debug("Resetting {}", this);

      for(Source source : this.watchers.keySet()) {
         this.reset(source);
      }

   }

   public void reset(final File file) {
      if (file != null) {
         Source source = new Source(file);
         this.reset(source);
      }
   }

   public void reset(final Source source) {
      if (source != null) {
         ConfigurationMonitor monitor = (ConfigurationMonitor)this.watchers.get(source);
         if (monitor != null) {
            Watcher watcher = monitor.getWatcher();
            if (watcher.isModified()) {
               long lastModifiedMillis = watcher.getLastModified();
               if (logger.isDebugEnabled()) {
                  logger.debug("Resetting file monitor for '{}' from {} ({}) to {} ({})", source.getLocation(), this.millisToString(monitor.lastModifiedMillis), monitor.lastModifiedMillis, this.millisToString(lastModifiedMillis), lastModifiedMillis);
               }

               monitor.setLastModifiedMillis(lastModifiedMillis);
            }
         }

      }
   }

   public void setIntervalSeconds(final int intervalSeconds) {
      if (!this.isStarted()) {
         if (this.intervalSeconds > 0 && intervalSeconds == 0) {
            this.scheduler.decrementScheduledItems();
         } else if (this.intervalSeconds == 0 && intervalSeconds > 0) {
            this.scheduler.incrementScheduledItems();
         }

         this.intervalSeconds = intervalSeconds;
      }

   }

   public void start() {
      super.start();
      if (this.intervalSeconds > 0) {
         this.future = this.scheduler.scheduleWithFixedDelay(new WatchRunnable(), (long)this.intervalSeconds, (long)this.intervalSeconds, TimeUnit.SECONDS);
      }

      for(WatchEventService service : this.eventServiceList) {
         service.subscribe(this);
      }

   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();

      for(WatchEventService service : this.eventServiceList) {
         service.unsubscribe(this);
      }

      boolean stopped = this.stop(this.future);
      this.setStopped();
      return stopped;
   }

   public String toString() {
      return "WatchManager [intervalSeconds=" + this.intervalSeconds + ", watchers=" + this.watchers + ", scheduler=" + this.scheduler + ", future=" + this.future + "]";
   }

   public void unwatch(final Source source) {
      logger.debug("Unwatching configuration {}", source);
      this.watchers.remove(source);
   }

   public void unwatchFile(final File file) {
      this.unwatch(new Source(file));
   }

   public void watch(final Source source, final Watcher watcher) {
      watcher.watching(source);
      long lastModified = watcher.getLastModified();
      if (logger.isDebugEnabled()) {
         logger.debug("Watching configuration '{}' for lastModified {} ({})", source, this.millisToString(lastModified), lastModified);
      }

      this.watchers.put(source, new ConfigurationMonitor(lastModified, watcher));
   }

   public void watchFile(final File file, final FileWatcher fileWatcher) {
      Watcher watcher;
      if (fileWatcher instanceof Watcher) {
         watcher = (Watcher)fileWatcher;
      } else {
         watcher = new WrappedFileWatcher(fileWatcher);
      }

      Source source = new Source(file);
      this.watch(source, watcher);
   }

   private static final class ConfigurationMonitor {
      private final Watcher watcher;
      private volatile long lastModifiedMillis;

      public ConfigurationMonitor(final long lastModifiedMillis, final Watcher watcher) {
         this.watcher = watcher;
         this.lastModifiedMillis = lastModifiedMillis;
      }

      public Watcher getWatcher() {
         return this.watcher;
      }

      private void setLastModifiedMillis(final long lastModifiedMillis) {
         this.lastModifiedMillis = lastModifiedMillis;
      }

      public String toString() {
         return "ConfigurationMonitor [watcher=" + this.watcher + ", lastModifiedMillis=" + this.lastModifiedMillis + "]";
      }
   }

   private static class LocalUUID {
      private static final long LOW_MASK = 4294967295L;
      private static final long MID_MASK = 281470681743360L;
      private static final long HIGH_MASK = 1152640029630136320L;
      private static final int SHIFT_2 = 16;
      private static final int SHIFT_4 = 32;
      private static final int SHIFT_6 = 48;
      private static final int HUNDRED_NANOS_PER_MILLI = 10000;
      private static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 122192928000000000L;
      private static final AtomicInteger COUNT = new AtomicInteger(0);
      private static final long TYPE1 = 4096L;

      public static UUID get() {
         long time = System.currentTimeMillis() * 10000L + 122192928000000000L + (long)(COUNT.incrementAndGet() % 10000);
         long timeLow = (time & 4294967295L) << 32;
         long timeMid = (time & 281470681743360L) >> 16;
         long timeHi = (time & 1152640029630136320L) >> 48;
         long most = timeLow | timeMid | 4096L | timeHi;
         return new UUID(most, (long)COUNT.incrementAndGet());
      }
   }

   private final class WatchRunnable implements Runnable {
      private final String SIMPLE_NAME;

      private WatchRunnable() {
         this.SIMPLE_NAME = WatchRunnable.class.getSimpleName();
      }

      public void run() {
         WatchManager.logger.trace("{} run triggered.", this.SIMPLE_NAME);

         for(Map.Entry entry : WatchManager.this.watchers.entrySet()) {
            Source source = (Source)entry.getKey();
            ConfigurationMonitor monitor = (ConfigurationMonitor)entry.getValue();
            if (monitor.getWatcher().isModified()) {
               long lastModified = monitor.getWatcher().getLastModified();
               WatchManager.logger.info("Configuration source at `{}` was modified on `{}`, previous modification was on `{}`", new Supplier[]{() -> source, () -> Instant.ofEpochMilli(lastModified), () -> Instant.ofEpochMilli(monitor.lastModifiedMillis)});
               monitor.lastModifiedMillis = lastModified;
               monitor.getWatcher().modified();
            }
         }

         WatchManager.logger.trace("{} run ended.", this.SIMPLE_NAME);
      }
   }
}
