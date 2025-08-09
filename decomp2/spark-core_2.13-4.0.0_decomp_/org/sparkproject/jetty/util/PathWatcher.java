package org.sparkproject.jetty.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

public class PathWatcher extends AbstractLifeCycle implements Runnable {
   private static final boolean IS_WINDOWS;
   static final Logger LOG;
   private static final WatchEvent.Kind[] WATCH_EVENT_KINDS;
   private static final WatchEvent.Kind[] WATCH_DIR_KINDS;
   private WatchService watchService;
   private final List configs = new ArrayList();
   private final Map keys = new ConcurrentHashMap();
   private final List listeners = new CopyOnWriteArrayList();
   private final Map pending = new LinkedHashMap(32, 0.75F, false);
   private final List events = new ArrayList();
   private long updateQuietTimeDuration = 1000L;
   private TimeUnit updateQuietTimeUnit;
   private Thread thread;
   private boolean _notifyExistingOnStart;

   protected static WatchEvent cast(WatchEvent event) {
      return event;
   }

   public PathWatcher() {
      this.updateQuietTimeUnit = TimeUnit.MILLISECONDS;
      this._notifyExistingOnStart = true;
   }

   public Collection getConfigs() {
      return this.configs;
   }

   public void watch(Path file) {
      Path abs = file;
      if (!file.isAbsolute()) {
         abs = file.toAbsolutePath();
      }

      Config config = null;
      Path parent = abs.getParent();

      for(Config c : this.configs) {
         if (c.getPath().equals(parent)) {
            config = c;
            break;
         }
      }

      if (config == null) {
         config = new Config(abs.getParent());
         config.addIncludeGlobRelative("");
         config.addIncludeGlobRelative(file.getFileName().toString());
         this.watch(config);
      } else {
         config.addIncludeGlobRelative(file.getFileName().toString());
      }

   }

   public void watch(Config config) {
      this.configs.add(config);
   }

   public void addListener(EventListener listener) {
      this.listeners.add(listener);
   }

   private void appendConfigId(StringBuilder s) {
      List<Path> dirs = new ArrayList();

      for(Config config : this.keys.values()) {
         dirs.add(config.path);
      }

      Collections.sort(dirs);
      s.append("[");
      if (dirs.size() > 0) {
         s.append(dirs.get(0));
         if (dirs.size() > 1) {
            s.append(" (+").append(dirs.size() - 1).append(")");
         }
      } else {
         s.append("<null>");
      }

      s.append("]");
   }

   protected void doStart() throws Exception {
      this.watchService = FileSystems.getDefault().newWatchService();
      this.setUpdateQuietTime(this.getUpdateQuietTimeNanos(), TimeUnit.NANOSECONDS);

      for(Config c : this.configs) {
         this.registerTree(c.getPath(), c, this.isNotifyExistingOnStart());
      }

      StringBuilder threadId = new StringBuilder();
      threadId.append("PathWatcher@");
      threadId.append(Integer.toHexString(this.hashCode()));
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} -> {}", this, threadId);
      }

      this.thread = new Thread(this, threadId.toString());
      this.thread.setDaemon(true);
      this.thread.start();
      super.doStart();
   }

   protected void doStop() throws Exception {
      if (this.watchService != null) {
         this.watchService.close();
      }

      this.watchService = null;
      this.thread = null;
      this.keys.clear();
      this.pending.clear();
      this.events.clear();
      super.doStop();
   }

   public void reset() {
      if (!this.isStopped()) {
         throw new IllegalStateException("PathWatcher must be stopped before reset.");
      } else {
         this.configs.clear();
         this.listeners.clear();
      }
   }

   protected boolean isNotifiable() {
      return this.isStarted() || !this.isStarted() && this.isNotifyExistingOnStart();
   }

   public Iterator getListeners() {
      return this.listeners.iterator();
   }

   long getUpdateQuietTimeNanos() {
      return TimeUnit.NANOSECONDS.convert(this.updateQuietTimeDuration, this.updateQuietTimeUnit);
   }

   private void registerTree(Path dir, Config config, boolean notify) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("registerTree {} {} {}", new Object[]{dir, config, notify});
      }

      if (!Files.isDirectory(dir, new LinkOption[0])) {
         throw new IllegalArgumentException(dir.toString());
      } else {
         this.register(dir, config);
         MultiException me = new MultiException();
         Stream<Path> stream = Files.list(dir);

         try {
            stream.forEach((p) -> {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("registerTree? {}", p);
               }

               try {
                  if (notify && config.test(p)) {
                     this.pending.put(p, new PathWatchEvent(p, PathWatcher.PathWatchEventType.ADDED, config));
                  }

                  switch (config.handleDir(p).ordinal()) {
                     case 0:
                     default:
                        break;
                     case 1:
                        this.registerDir(p, config);
                        break;
                     case 2:
                        this.registerTree(p, config.asSubConfig(p), notify);
                  }
               } catch (IOException e) {
                  me.add(e);
               }

            });
         } catch (Throwable var11) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var8) {
                  var11.addSuppressed(var8);
               }
            }

            throw var11;
         }

         if (stream != null) {
            stream.close();
         }

         try {
            me.ifExceptionThrow();
         } catch (IOException e) {
            throw e;
         } catch (Throwable th) {
            throw new IOException(th);
         }
      }
   }

   private void registerDir(Path path, Config config) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("registerDir {} {}", path, config);
      }

      if (!Files.isDirectory(path, new LinkOption[0])) {
         throw new IllegalArgumentException(path.toString());
      } else {
         this.register(path, config.asSubConfig(path), WATCH_DIR_KINDS);
      }
   }

   protected void register(Path path, Config config) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Registering watch on {}", path);
      }

      this.register(path, config, WATCH_EVENT_KINDS);
   }

   private void register(Path path, Config config, WatchEvent.Kind[] kinds) throws IOException {
      WatchKey key = path.register(this.watchService, kinds);
      this.keys.put(key, config);
   }

   public boolean removeListener(Listener listener) {
      return this.listeners.remove(listener);
   }

   public void run() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Starting java.nio file watching with {}", this.watchService);
      }

      long waitTime = this.getUpdateQuietTimeNanos();
      WatchService watch = this.watchService;

      while(this.isRunning() && this.thread == Thread.currentThread()) {
         try {
            long now = NanoTime.now();

            for(Map.Entry e : this.keys.entrySet()) {
               WatchKey k = (WatchKey)e.getKey();
               Config c = (Config)e.getValue();
               if (!c.isPaused(now) && !k.reset()) {
                  this.keys.remove(k);
                  if (this.keys.isEmpty()) {
                     return;
                  }
               }
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Waiting for poll({})", waitTime);
            }

            for(WatchKey key = waitTime < 0L ? watch.take() : (waitTime > 0L ? watch.poll(waitTime, TimeUnit.NANOSECONDS) : watch.poll()); key != null; key = watch.poll()) {
               this.handleKey(key);
            }

            waitTime = this.processPending();
            this.notifyEvents();
         } catch (ClosedWatchServiceException var11) {
            return;
         } catch (InterruptedException e) {
            if (this.isRunning()) {
               LOG.warn("Watch failed", e);
            } else {
               LOG.trace("IGNORED", e);
            }
         }
      }

   }

   private void handleKey(WatchKey key) {
      Config config = (Config)this.keys.get(key);
      if (config == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("WatchKey not recognized: {}", key);
         }

      } else {
         for(WatchEvent event : key.pollEvents()) {
            WatchEvent<Path> ev = cast(event);
            Path name = (Path)ev.context();
            Path path = config.resolve(name);
            if (LOG.isDebugEnabled()) {
               LOG.debug("handleKey? {} {} {}", new Object[]{ev.kind(), config.toShortPath(path), config});
            }

            if (ev.kind() != StandardWatchEventKinds.ENTRY_MODIFY || !Files.exists(path, new LinkOption[0]) || !Files.isDirectory(path, new LinkOption[0])) {
               if (config.test(path)) {
                  this.handleWatchEvent(path, new PathWatchEvent(path, ev, config));
               } else if (config.getRecurseDepth() == -1) {
                  Path parent = path.getParent();
                  Config parentConfig = config.getParent();
                  this.handleWatchEvent(parent, new PathWatchEvent(parent, PathWatcher.PathWatchEventType.MODIFIED, parentConfig));
                  continue;
               }

               if (ev.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                  try {
                     switch (config.handleDir(path).ordinal()) {
                        case 0:
                        default:
                           break;
                        case 1:
                           this.registerDir(path, config);
                           break;
                        case 2:
                           this.registerTree(path, config.asSubConfig(path), true);
                     }
                  } catch (IOException e) {
                     LOG.warn("Unable to register", e);
                  }
               }
            }
         }

      }
   }

   public void handleWatchEvent(Path path, PathWatchEvent event) {
      PathWatchEvent existing = (PathWatchEvent)this.pending.get(path);
      if (LOG.isDebugEnabled()) {
         LOG.debug("handleWatchEvent {} {} <= {}", new Object[]{path, event, existing});
      }

      switch (event.getType().ordinal()) {
         case 0:
            if (existing != null && existing.getType() == PathWatcher.PathWatchEventType.MODIFIED) {
               this.events.add(new PathWatchEvent(path, PathWatcher.PathWatchEventType.DELETED, existing.getConfig()));
            }

            this.pending.put(path, event);
            break;
         case 1:
         case 3:
            if (existing != null) {
               this.pending.remove(path);
            }

            this.events.add(event);
            break;
         case 2:
            if (existing == null) {
               this.pending.put(path, event);
            } else {
               existing.modified();
            }
            break;
         default:
            throw new IllegalStateException(event.toString());
      }

   }

   private long processPending() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("processPending> {}", this.pending.values());
      }

      long now = NanoTime.now();
      long wait = Long.MAX_VALUE;

      for(PathWatchEvent event : new ArrayList(this.pending.values())) {
         Path path = event.getPath();
         if (!this.pending.containsKey(path.getParent())) {
            long quietTime = this.getUpdateQuietTimeNanos();
            if (event.isQuiet(now, quietTime)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("isQuiet {}", event);
               }

               this.pending.remove(path);
               this.events.add(event);
            } else {
               long nsToCheck = event.toQuietCheck(now, quietTime);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("pending {} {}", event, nsToCheck);
               }

               if (nsToCheck < wait) {
                  wait = nsToCheck;
               }
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("processPending< {}", this.pending.values());
      }

      return wait == Long.MAX_VALUE ? -1L : wait;
   }

   private void notifyEvents() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("notifyEvents {}", this.events.size());
      }

      if (!this.events.isEmpty()) {
         boolean eventListeners = false;

         for(EventListener listener : this.listeners) {
            if (listener instanceof EventListListener) {
               try {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("notifyEvents {} {}", listener, this.events);
                  }

                  ((EventListListener)listener).onPathWatchEvents(this.events);
               } catch (Throwable t) {
                  LOG.warn("Unable to notify PathWatch Events", t);
               }
            } else {
               eventListeners = true;
            }
         }

         if (eventListeners) {
            for(PathWatchEvent event : this.events) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("notifyEvent {} {}", event, this.listeners);
               }

               for(EventListener listener : this.listeners) {
                  if (listener instanceof Listener) {
                     try {
                        ((Listener)listener).onPathWatchEvent(event);
                     } catch (Throwable t) {
                        LOG.warn("Unable to notify PathWatch Events", t);
                     }
                  }
               }
            }
         }

         this.events.clear();
      }
   }

   public void setNotifyExistingOnStart(boolean notify) {
      this._notifyExistingOnStart = notify;
   }

   public boolean isNotifyExistingOnStart() {
      return this._notifyExistingOnStart;
   }

   public void setUpdateQuietTime(long duration, TimeUnit unit) {
      long desiredMillis = unit.toMillis(duration);
      if (IS_WINDOWS && desiredMillis < 1000L) {
         LOG.warn("Quiet Time is too low for Microsoft Windows: {} < 1000 ms (defaulting to 1000 ms)", desiredMillis);
         this.updateQuietTimeDuration = 1000L;
         this.updateQuietTimeUnit = TimeUnit.MILLISECONDS;
      } else {
         this.updateQuietTimeDuration = duration;
         this.updateQuietTimeUnit = unit;
      }
   }

   public String toString() {
      StringBuilder s = new StringBuilder(this.getClass().getName());
      this.appendConfigId(s);
      return s.toString();
   }

   static {
      String os = System.getProperty("os.name");
      if (os == null) {
         IS_WINDOWS = false;
      } else {
         String osl = os.toLowerCase(Locale.ENGLISH);
         IS_WINDOWS = osl.contains("windows");
      }

      LOG = LoggerFactory.getLogger(PathWatcher.class);
      WATCH_EVENT_KINDS = new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY};
      WATCH_DIR_KINDS = new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE};
   }

   public static class Config implements Predicate {
      public static final int UNLIMITED_DEPTH = -9999;
      private static final String PATTERN_SEP;
      protected final Config parent;
      protected final Path path;
      protected final IncludeExcludeSet includeExclude;
      protected int recurseDepth;
      protected boolean excludeHidden;
      protected long pauseUntil;

      public Config(Path path) {
         this(path, (Config)null);
      }

      public Config(Path path, Config parent) {
         this.recurseDepth = 0;
         this.excludeHidden = false;
         this.parent = parent;
         this.includeExclude = parent == null ? new IncludeExcludeSet(PathMatcherSet.class) : parent.includeExclude;
         Path dir = path;
         if (!Files.exists(path, new LinkOption[0])) {
            throw new IllegalStateException("Path does not exist: " + String.valueOf(path));
         } else {
            if (!Files.isDirectory(path, new LinkOption[0])) {
               dir = path.getParent();
               this.includeExclude.include((Object)(new ExactPathMatcher(path)));
               this.setRecurseDepth(0);
            }

            this.path = dir;
         }
      }

      public Config getParent() {
         return this.parent;
      }

      public void setPauseUntil(long time) {
         if (NanoTime.isBefore(this.pauseUntil, time)) {
            this.pauseUntil = time;
         }

      }

      public boolean isPaused(long now) {
         if (this.pauseUntil == 0L) {
            return false;
         } else if (NanoTime.isBefore(now, this.pauseUntil)) {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("PAUSED {}", this);
            }

            return true;
         } else {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("unpaused {}", this);
            }

            this.pauseUntil = 0L;
            return false;
         }
      }

      public void addExclude(PathMatcher matcher) {
         this.includeExclude.exclude((Object)matcher);
      }

      public void addExclude(String syntaxAndPattern) {
         if (PathWatcher.LOG.isDebugEnabled()) {
            PathWatcher.LOG.debug("Adding exclude: [{}]", syntaxAndPattern);
         }

         this.addExclude(this.path.getFileSystem().getPathMatcher(syntaxAndPattern));
      }

      public void addExcludeGlobRelative(String pattern) {
         this.addExclude(this.toGlobPattern(this.path, pattern));
      }

      public void addExcludeHidden() {
         if (!this.excludeHidden) {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("Adding hidden files and directories to exclusions");
            }

            this.excludeHidden = true;
         }

      }

      public void addExcludes(List syntaxAndPatterns) {
         for(String syntaxAndPattern : syntaxAndPatterns) {
            this.addExclude(syntaxAndPattern);
         }

      }

      public void addInclude(PathMatcher matcher) {
         this.includeExclude.include((Object)matcher);
      }

      public void addInclude(String syntaxAndPattern) {
         if (PathWatcher.LOG.isDebugEnabled()) {
            PathWatcher.LOG.debug("Adding include: [{}]", syntaxAndPattern);
         }

         this.addInclude(this.path.getFileSystem().getPathMatcher(syntaxAndPattern));
      }

      public void addIncludeGlobRelative(String pattern) {
         this.addInclude(this.toGlobPattern(this.path, pattern));
      }

      public void addIncludes(List syntaxAndPatterns) {
         for(String syntaxAndPattern : syntaxAndPatterns) {
            this.addInclude(syntaxAndPattern);
         }

      }

      public Config asSubConfig(Path dir) {
         Config subconfig = new Config(dir, this);
         if (dir == this.path) {
            String var10002 = dir.toString();
            throw new IllegalStateException("sub " + var10002 + " of " + String.valueOf(this));
         } else {
            if (this.recurseDepth == -9999) {
               subconfig.recurseDepth = -9999;
            } else {
               subconfig.recurseDepth = this.recurseDepth - (dir.getNameCount() - this.path.getNameCount());
            }

            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("subconfig {} of {}", subconfig, this.path);
            }

            return subconfig;
         }
      }

      public int getRecurseDepth() {
         return this.recurseDepth;
      }

      public boolean isRecurseDepthUnlimited() {
         return this.recurseDepth == -9999;
      }

      public Path getPath() {
         return this.path;
      }

      public Path resolve(Path path) {
         if (Files.isDirectory(this.path, new LinkOption[0])) {
            return this.path.resolve(path);
         } else {
            return Files.exists(this.path, new LinkOption[0]) ? this.path : path;
         }
      }

      public boolean test(Path path) {
         if (this.excludeHidden && this.isHidden(path)) {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("test({}) -> [Hidden]", this.toShortPath(path));
            }

            return false;
         } else if (!path.startsWith(this.path)) {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("test({}) -> [!child {}]", this.toShortPath(path), this.path);
            }

            return false;
         } else {
            if (this.recurseDepth != -9999) {
               int depth = path.getNameCount() - this.path.getNameCount() - 1;
               if (depth > this.recurseDepth) {
                  if (PathWatcher.LOG.isDebugEnabled()) {
                     PathWatcher.LOG.debug("test({}) -> [depth {}>{}]", new Object[]{this.toShortPath(path), depth, this.recurseDepth});
                  }

                  return false;
               }
            }

            boolean matched = this.includeExclude.test(path);
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("test({}) -> {}", this.toShortPath(path), matched);
            }

            return matched;
         }
      }

      public void setRecurseDepth(int depth) {
         this.recurseDepth = depth;
      }

      private String toGlobPattern(Path path, String subPattern) {
         StringBuilder s = new StringBuilder();
         s.append("glob:");
         boolean needDelim = false;
         Path root = path.getRoot();
         if (root != null) {
            if (PathWatcher.LOG.isDebugEnabled()) {
               PathWatcher.LOG.debug("Path: {} -> Root: {}", path, root);
            }

            for(char c : root.toString().toCharArray()) {
               if (c == '\\') {
                  s.append(PATTERN_SEP);
               } else {
                  s.append(c);
               }
            }
         } else {
            needDelim = true;
         }

         for(Path segment : path) {
            if (needDelim) {
               s.append(PATTERN_SEP);
            }

            s.append(segment);
            needDelim = true;
         }

         if (subPattern != null && subPattern.length() > 0) {
            if (needDelim) {
               s.append(PATTERN_SEP);
            }

            for(char c : subPattern.toCharArray()) {
               if (c == '/') {
                  s.append(PATTERN_SEP);
               } else {
                  s.append(c);
               }
            }
         }

         return s.toString();
      }

      DirAction handleDir(Path path) {
         try {
            if (!Files.isDirectory(path, new LinkOption[0])) {
               return PathWatcher.DirAction.IGNORE;
            } else if (this.excludeHidden && this.isHidden(path)) {
               return PathWatcher.DirAction.IGNORE;
            } else {
               return this.getRecurseDepth() == 0 ? PathWatcher.DirAction.WATCH : PathWatcher.DirAction.ENTER;
            }
         } catch (Exception e) {
            PathWatcher.LOG.trace("IGNORED", e);
            return PathWatcher.DirAction.IGNORE;
         }
      }

      public boolean isHidden(Path path) {
         try {
            if (!path.startsWith(this.path)) {
               return true;
            } else {
               for(int i = this.path.getNameCount(); i < path.getNameCount(); ++i) {
                  if (path.getName(i).toString().startsWith(".")) {
                     return true;
                  }
               }

               return Files.exists(path, new LinkOption[0]) && Files.isHidden(path);
            }
         } catch (IOException e) {
            PathWatcher.LOG.trace("IGNORED", e);
            return false;
         }
      }

      public String toShortPath(Path path) {
         return !path.startsWith(this.path) ? path.toString() : this.path.relativize(path).toString();
      }

      public String toString() {
         StringBuilder s = new StringBuilder();
         s.append(this.path).append(" [depth=");
         if (this.recurseDepth == -9999) {
            s.append("UNLIMITED");
         } else {
            s.append(this.recurseDepth);
         }

         s.append(']');
         return s.toString();
      }

      static {
         String sep = File.separator;
         if (File.separatorChar == '\\') {
            sep = "\\\\";
         }

         PATTERN_SEP = sep;
      }
   }

   public static enum DirAction {
      IGNORE,
      WATCH,
      ENTER;

      // $FF: synthetic method
      private static DirAction[] $values() {
         return new DirAction[]{IGNORE, WATCH, ENTER};
      }
   }

   public class PathWatchEvent {
      private final Path path;
      private final PathWatchEventType type;
      private final Config config;
      long checked;
      long modified;
      long length;

      public PathWatchEvent(Path path, PathWatchEventType type, Config config) {
         this.path = path;
         this.type = type;
         this.config = config;
         this.checked = NanoTime.now();
         this.check();
      }

      public Config getConfig() {
         return this.config;
      }

      public PathWatchEvent(Path path, WatchEvent event, Config config) {
         this.path = path;
         if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
            this.type = PathWatcher.PathWatchEventType.ADDED;
         } else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
            this.type = PathWatcher.PathWatchEventType.DELETED;
         } else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
            this.type = PathWatcher.PathWatchEventType.MODIFIED;
         } else {
            this.type = PathWatcher.PathWatchEventType.UNKNOWN;
         }

         this.config = config;
         this.checked = NanoTime.now();
         this.check();
      }

      private void check() {
         if (Files.exists(this.path, new LinkOption[0])) {
            try {
               this.modified = Files.getLastModifiedTime(this.path).toMillis();
               this.length = Files.size(this.path);
            } catch (IOException var2) {
               this.modified = -1L;
               this.length = -1L;
            }
         } else {
            this.modified = -1L;
            this.length = -1L;
         }

      }

      public boolean isQuiet(long now, long quietTime) {
         long lastModified = this.modified;
         long lastLength = this.length;
         this.check();
         if (lastModified == this.modified && lastLength == this.length) {
            return NanoTime.elapsed(this.checked, now) >= quietTime;
         } else {
            this.checked = now;
            return false;
         }
      }

      public long toQuietCheck(long now, long quietTime) {
         long check = quietTime - NanoTime.elapsed(this.checked, now);
         return check <= 0L ? quietTime : check;
      }

      public void modified() {
         long now = NanoTime.now();
         this.checked = now;
         this.check();
         this.config.setPauseUntil(now + PathWatcher.this.getUpdateQuietTimeNanos());
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            PathWatchEvent other = (PathWatchEvent)obj;
            if (this.path == null) {
               if (other.path != null) {
                  return false;
               }
            } else if (!this.path.equals(other.path)) {
               return false;
            }

            return this.type == other.type;
         }
      }

      public Path getPath() {
         return this.path;
      }

      public PathWatchEventType getType() {
         return this.type;
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
         result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
         return result;
      }

      public String toString() {
         return String.format("PathWatchEvent[%8s|%s]", this.type, this.path);
      }
   }

   public static enum PathWatchEventType {
      ADDED,
      DELETED,
      MODIFIED,
      UNKNOWN;

      // $FF: synthetic method
      private static PathWatchEventType[] $values() {
         return new PathWatchEventType[]{ADDED, DELETED, MODIFIED, UNKNOWN};
      }
   }

   private static class ExactPathMatcher implements PathMatcher {
      private final Path path;

      ExactPathMatcher(Path path) {
         this.path = path;
      }

      public boolean matches(Path path) {
         return this.path.equals(path);
      }
   }

   public static class PathMatcherSet extends HashSet implements Predicate {
      public boolean test(Path path) {
         for(PathMatcher pm : this) {
            if (pm.matches(path)) {
               return true;
            }
         }

         return false;
      }
   }

   public interface EventListListener extends EventListener {
      void onPathWatchEvents(List var1);
   }

   public interface Listener extends EventListener {
      void onPathWatchEvent(PathWatchEvent var1);
   }
}
