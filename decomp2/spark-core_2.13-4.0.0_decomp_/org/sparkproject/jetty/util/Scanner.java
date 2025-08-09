package org.sparkproject.jetty.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

public class Scanner extends ContainerLifeCycle {
   public static final int DEFAULT_SCAN_DEPTH = 1;
   public static final int MAX_SCAN_DEPTH = Integer.MAX_VALUE;
   private static final Logger LOG = LoggerFactory.getLogger(Scanner.class);
   private static final AtomicInteger SCANNER_IDS = new AtomicInteger();
   private int _scanInterval;
   private final AtomicInteger _scanCount;
   private final List _listeners;
   private Map _prevScan;
   private FilenameFilter _filter;
   private final Map _scannables;
   private boolean _autoStartScanning;
   private boolean _scanningStarted;
   private boolean _reportExisting;
   private boolean _reportDirs;
   private Scheduler.Task _task;
   private final Scheduler _scheduler;
   private int _scanDepth;
   private final LinkOption[] _linkOptions;

   public Scanner() {
      this((Scheduler)null);
   }

   public Scanner(Scheduler scheduler) {
      this(scheduler, true);
   }

   public Scanner(Scheduler scheduler, boolean reportRealPaths) {
      this._scanCount = new AtomicInteger(0);
      this._listeners = new CopyOnWriteArrayList();
      this._scannables = new ConcurrentHashMap();
      this._autoStartScanning = true;
      this._scanningStarted = false;
      this._reportExisting = true;
      this._reportDirs = true;
      this._scanDepth = 1;
      this._scheduler = (Scheduler)(scheduler == null ? new ScheduledExecutorScheduler("Scanner-" + SCANNER_IDS.getAndIncrement(), true, 1) : scheduler);
      this.addBean(this._scheduler);
      this._linkOptions = reportRealPaths ? new LinkOption[0] : new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
   }

   public int getScanInterval() {
      return this._scanInterval;
   }

   public void setScanInterval(int scanInterval) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else {
         this._scanInterval = scanInterval;
      }
   }

   public void setScanDirs(List dirs) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else {
         this._scannables.clear();
         if (dirs != null) {
            for(File f : dirs) {
               if (f.isDirectory()) {
                  this.addDirectory(f.toPath());
               } else {
                  this.addFile(f.toPath());
               }
            }

         }
      }
   }

   public void addFile(Path path) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else if (path == null) {
         throw new IllegalStateException("Null path");
      } else {
         try {
            Path real = path.toRealPath(this._linkOptions);
            if (Files.exists(real, new LinkOption[0]) && !Files.isDirectory(real, new LinkOption[0])) {
               this._scannables.putIfAbsent(real, new IncludeExcludeSet(PathMatcherSet.class));
            } else {
               throw new IllegalStateException("Not file or doesn't exist: " + String.valueOf(path));
            }
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }
   }

   public IncludeExcludeSet addDirectory(Path p) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else if (p == null) {
         throw new IllegalStateException("Null path");
      } else {
         try {
            Path real = p.toRealPath(this._linkOptions);
            if (Files.exists(real, new LinkOption[0]) && Files.isDirectory(real, new LinkOption[0])) {
               IncludeExcludeSet<PathMatcher, Path> includesExcludes = new IncludeExcludeSet(PathMatcherSet.class);
               IncludeExcludeSet<PathMatcher, Path> prev = (IncludeExcludeSet)this._scannables.putIfAbsent(real, includesExcludes);
               if (prev != null) {
                  includesExcludes = prev;
               }

               return includesExcludes;
            } else {
               throw new IllegalStateException("Not directory or doesn't exist: " + String.valueOf(p));
            }
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public void setFilenameFilter(FilenameFilter filter) {
      this._filter = filter;
   }

   /** @deprecated */
   @Deprecated
   public FilenameFilter getFilenameFilter() {
      return this._filter;
   }

   public Set getScannables() {
      return Collections.unmodifiableSet(this._scannables.keySet());
   }

   public int getScanDepth() {
      return this._scanDepth;
   }

   public void setScanDepth(int scanDepth) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else {
         this._scanDepth = scanDepth;
      }
   }

   public boolean isAutoStartScanning() {
      return this._autoStartScanning;
   }

   public void setAutoStartScanning(boolean autostart) {
      this._autoStartScanning = autostart;
   }

   public void setReportExistingFilesOnStartup(boolean reportExisting) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else {
         this._reportExisting = reportExisting;
      }
   }

   public boolean getReportExistingFilesOnStartup() {
      return this._reportExisting;
   }

   public void setReportDirs(boolean dirs) {
      if (this.isRunning()) {
         throw new IllegalStateException("Scanner started");
      } else {
         this._reportDirs = dirs;
      }
   }

   public boolean getReportDirs() {
      return this._reportDirs;
   }

   public void addListener(Listener listener) {
      if (listener != null) {
         this._listeners.add(listener);
      }
   }

   public void removeListener(Listener listener) {
      if (listener != null) {
         this._listeners.remove(listener);
      }
   }

   public void doStart() throws Exception {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Scanner start: autoStartScanning={}, reportExists={}, depth={}, rprtDirs={}, interval={}, filter={}, scannables={}", new Object[]{this.isAutoStartScanning(), this._reportExisting, this._scanDepth, this._reportDirs, this._scanInterval, this._filter, this._scannables});
      }

      super.doStart();
      if (this.isAutoStartScanning()) {
         this.startScanning();
      }

   }

   public void startScanning() {
      if (!this.isRunning()) {
         throw new IllegalStateException("Scanner not started");
      } else if (!this._scanningStarted) {
         this._scanningStarted = true;
         if (LOG.isDebugEnabled()) {
            LOG.debug("{}.startup()", this.getClass().getSimpleName());
         }

         if (this._reportExisting) {
            this.scan();
            this.scan();
         } else {
            this._prevScan = this.scanFiles();
         }

         this.schedule();
      }
   }

   private void schedule() {
      if (this.isRunning() && this.getScanInterval() > 0) {
         this._task = this._scheduler.schedule(new ScanTask(), 1010L * (long)this.getScanInterval(), TimeUnit.MILLISECONDS);
      }

   }

   public void doStop() throws Exception {
      Scheduler.Task task = this._task;
      this._task = null;
      if (task != null) {
         task.cancel();
      }

      this._scanningStarted = false;
   }

   public void reset() {
      if (!this.isStopped()) {
         throw new IllegalStateException("Not stopped");
      } else {
         this._scannables.clear();
         this._prevScan = null;
      }
   }

   public boolean exists(String path) {
      for(Path p : this._scannables.keySet()) {
         if (p.resolve(path).toFile().exists()) {
            return true;
         }
      }

      return false;
   }

   public void nudge() {
      if (!this.isRunning()) {
         throw new IllegalStateException("Scanner not running");
      } else {
         this.scan(Callback.NOOP);
      }
   }

   public void scan(Callback complete) {
      Scheduler scheduler = this._scheduler;
      if (this.isRunning() && scheduler != null) {
         scheduler.schedule(() -> {
            try {
               this.scan();
               complete.succeeded();
            } catch (Throwable t) {
               complete.failed(t);
            }

         }, 0L, TimeUnit.MILLISECONDS);
      } else {
         complete.failed(new IllegalStateException("Scanner not running"));
      }
   }

   void scan() {
      int cycle = this._scanCount.incrementAndGet();
      this.reportScanStart(cycle);
      Map<Path, MetaData> currentScan = this.scanFiles();
      this.reportDifferences(currentScan, this._prevScan == null ? Collections.emptyMap() : Collections.unmodifiableMap(this._prevScan));
      this._prevScan = currentScan;
      this.reportScanEnd(cycle);
   }

   private Map scanFiles() {
      Map<Path, MetaData> currentScan = new HashMap();

      for(Map.Entry entry : this._scannables.entrySet()) {
         try {
            Files.walkFileTree((Path)entry.getKey(), EnumSet.allOf(FileVisitOption.class), this._scanDepth, new Visitor((Path)entry.getKey(), (IncludeExcludeSet)entry.getValue(), currentScan));
         } catch (IOException e) {
            LOG.warn("Error scanning files.", e);
         }
      }

      return currentScan;
   }

   private void reportDifferences(Map currentScan, Map oldScan) {
      Map<Path, Notification> changes = new HashMap();
      Set<Path> oldScanKeys = new HashSet(oldScan.keySet());
      oldScanKeys.removeAll(currentScan.keySet());

      for(Path path : oldScanKeys) {
         changes.put(path, Scanner.Notification.REMOVED);
      }

      for(Map.Entry entry : currentScan.entrySet()) {
         MetaData current = (MetaData)entry.getValue();
         MetaData previous = (MetaData)oldScan.get(entry.getKey());
         if (previous == null) {
            current._status = Scanner.Status.ADDED;
         } else if (current.isModified(previous)) {
            if (previous._status == Scanner.Status.ADDED) {
               current._status = Scanner.Status.ADDED;
            } else {
               current._status = Scanner.Status.CHANGED;
            }
         } else {
            if (previous._status == Scanner.Status.ADDED) {
               changes.put((Path)entry.getKey(), Scanner.Notification.ADDED);
            } else if (previous._status == Scanner.Status.CHANGED) {
               changes.put((Path)entry.getKey(), Scanner.Notification.CHANGED);
            }

            current._status = Scanner.Status.STABLE;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("scanned {}", this._scannables.keySet());
      }

      for(Map.Entry entry : changes.entrySet()) {
         switch (((Notification)entry.getValue()).ordinal()) {
            case 0:
               this.reportAddition((Path)entry.getKey());
               break;
            case 1:
               this.reportChange((Path)entry.getKey());
               break;
            case 2:
               this.reportRemoval((Path)entry.getKey());
               break;
            default:
               LOG.warn("Unknown file change: {}", entry.getValue());
         }
      }

      this.reportBulkChanges(changes.keySet());
   }

   private void warn(Object listener, Path path, Throwable th) {
      LOG.warn("{} failed on '{}'", new Object[]{listener, path, th});
   }

   private void reportAddition(Path path) {
      for(Listener l : this._listeners) {
         try {
            if (l instanceof DiscreteListener) {
               ((DiscreteListener)l).pathAdded(path);
            }
         } catch (Throwable e) {
            this.warn(l, path, e);
         }
      }

   }

   private void reportRemoval(Path path) {
      for(Object l : this._listeners) {
         try {
            if (l instanceof DiscreteListener) {
               ((DiscreteListener)l).pathRemoved(path);
            }
         } catch (Throwable e) {
            this.warn(l, path, e);
         }
      }

   }

   private void reportChange(Path path) {
      if (path != null) {
         for(Listener l : this._listeners) {
            try {
               if (l instanceof DiscreteListener) {
                  ((DiscreteListener)l).pathChanged(path);
               }
            } catch (Throwable e) {
               this.warn(l, path, e);
            }
         }

      }
   }

   private void reportBulkChanges(Set paths) {
      if (paths != null && !paths.isEmpty()) {
         for(Listener l : this._listeners) {
            try {
               if (l instanceof BulkListener) {
                  ((BulkListener)l).pathsChanged(paths);
               }
            } catch (Throwable e) {
               LOG.warn("{} failed on '{}'", new Object[]{l, paths, e});
            }
         }

      }
   }

   private void reportScanStart(int cycle) {
      for(Listener listener : this._listeners) {
         try {
            if (listener instanceof ScanCycleListener) {
               ((ScanCycleListener)listener).scanStarted(cycle);
            }
         } catch (Exception e) {
            LOG.warn("{} failed on scan start for cycle {}", new Object[]{listener, cycle, e});
         }
      }

   }

   private void reportScanEnd(int cycle) {
      for(Listener listener : this._listeners) {
         try {
            if (listener instanceof ScanCycleListener) {
               ((ScanCycleListener)listener).scanEnded(cycle);
            }
         } catch (Exception e) {
            LOG.warn("{} failed on scan end for cycle {}", new Object[]{listener, cycle, e});
         }
      }

   }

   private static enum Status {
      ADDED,
      CHANGED,
      REMOVED,
      STABLE;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{ADDED, CHANGED, REMOVED, STABLE};
      }
   }

   static enum Notification {
      ADDED,
      CHANGED,
      REMOVED;

      // $FF: synthetic method
      private static Notification[] $values() {
         return new Notification[]{ADDED, CHANGED, REMOVED};
      }
   }

   static class PathMatcherSet extends HashSet implements Predicate {
      public boolean test(Path p) {
         for(PathMatcher pm : this) {
            if (pm.matches(p)) {
               return true;
            }
         }

         return false;
      }
   }

   private static class MetaData {
      final long _lastModified;
      final long _size;
      Status _status;

      public MetaData(long lastModified, long size) {
         this._lastModified = lastModified;
         this._size = size;
      }

      public boolean isModified(MetaData m) {
         return m._lastModified != this._lastModified || m._size != this._size;
      }

      public String toString() {
         long var10000 = this._lastModified;
         return "[lm=" + var10000 + ",sz=" + this._size + ",s=" + String.valueOf(this._status) + "]";
      }
   }

   private class ScanTask implements Runnable {
      public void run() {
         Scanner.this.scan();
         Scanner.this.schedule();
      }
   }

   private class Visitor implements FileVisitor {
      Map scanInfoMap;
      IncludeExcludeSet rootIncludesExcludes;
      Path root;

      private Visitor(Path root, IncludeExcludeSet rootIncludesExcludes, Map scanInfoMap) {
         this.root = root;
         this.rootIncludesExcludes = rootIncludesExcludes;
         this.scanInfoMap = scanInfoMap;
      }

      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
         if (!Files.exists(dir, new LinkOption[0])) {
            return FileVisitResult.SKIP_SUBTREE;
         } else {
            dir = dir.toRealPath(Scanner.this._linkOptions);
            File f = dir.toFile();
            if (Scanner.this._reportDirs && !this.scanInfoMap.containsKey(dir)) {
               boolean accepted = false;
               if (this.rootIncludesExcludes != null && !this.rootIncludesExcludes.isEmpty()) {
                  accepted = this.rootIncludesExcludes.test(dir);
               } else if (Scanner.this._filter == null || Scanner.this._filter.accept(f.getParentFile(), f.getName())) {
                  accepted = true;
               }

               if (accepted) {
                  this.scanInfoMap.put(dir, new MetaData(f.lastModified(), f.isDirectory() ? 0L : f.length()));
                  if (Scanner.LOG.isDebugEnabled()) {
                     Scanner.LOG.debug("scan accepted dir {} mod={}", f, f.lastModified());
                  }
               }
            }

            return FileVisitResult.CONTINUE;
         }
      }

      public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
         path = path.toRealPath(Scanner.this._linkOptions);
         if (!Files.exists(path, new LinkOption[0])) {
            return FileVisitResult.CONTINUE;
         } else {
            File f = path.toFile();
            boolean accepted = false;
            if (f.isFile() || f.isDirectory() && Scanner.this._reportDirs && !this.scanInfoMap.containsKey(path)) {
               if (this.rootIncludesExcludes != null && !this.rootIncludesExcludes.isEmpty()) {
                  accepted = this.rootIncludesExcludes.test(path);
               } else if (Scanner.this._filter == null || Scanner.this._filter.accept(f.getParentFile(), f.getName())) {
                  accepted = true;
               }
            }

            if (accepted) {
               this.scanInfoMap.put(path, new MetaData(f.lastModified(), f.isDirectory() ? 0L : f.length()));
               if (Scanner.LOG.isDebugEnabled()) {
                  Scanner.LOG.debug("scan accepted {} mod={}", f, f.lastModified());
               }
            }

            return FileVisitResult.CONTINUE;
         }
      }

      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
         Scanner.LOG.warn("FileVisit failed: {}", file, exc);
         return FileVisitResult.CONTINUE;
      }

      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
         return FileVisitResult.CONTINUE;
      }
   }

   public interface DiscreteListener extends Listener {
      default void pathChanged(Path path) throws Exception {
         path.toString();
         this.fileChanged(path.toString());
      }

      default void pathAdded(Path path) throws Exception {
         this.fileAdded(path.toString());
      }

      default void pathRemoved(Path path) throws Exception {
         this.fileRemoved(path.toString());
      }

      void fileChanged(String var1) throws Exception;

      void fileAdded(String var1) throws Exception;

      void fileRemoved(String var1) throws Exception;
   }

   public interface BulkListener extends Listener {
      default void pathsChanged(Set paths) throws Exception {
         this.filesChanged((Set)paths.stream().map(Path::toString).collect(Collectors.toSet()));
      }

      void filesChanged(Set var1) throws Exception;
   }

   public interface ScanCycleListener extends Listener {
      default void scanStarted(int cycle) throws Exception {
      }

      default void scanEnded(int cycle) throws Exception {
      }
   }

   public interface Listener {
   }
}
