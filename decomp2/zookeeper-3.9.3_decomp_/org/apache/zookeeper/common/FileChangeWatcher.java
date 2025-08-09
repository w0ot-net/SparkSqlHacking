package org.apache.zookeeper.common;

import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Consumer;
import org.apache.zookeeper.server.ZooKeeperThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileChangeWatcher {
   private static final Logger LOG = LoggerFactory.getLogger(FileChangeWatcher.class);
   private final WatcherThread watcherThread;
   private State state;

   public FileChangeWatcher(Path dirPath, Consumer callback) throws IOException {
      FileSystem fs = dirPath.getFileSystem();
      WatchService watchService = fs.newWatchService();
      LOG.debug("Registering with watch service: {}", dirPath);
      dirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
      this.state = FileChangeWatcher.State.NEW;
      this.watcherThread = new WatcherThread(watchService, callback);
      this.watcherThread.setDaemon(true);
   }

   public synchronized State getState() {
      return this.state;
   }

   synchronized void waitForState(State desiredState) throws InterruptedException {
      while(this.state != desiredState) {
         this.wait();
      }

   }

   private synchronized void setState(State newState) {
      this.state = newState;
      this.notifyAll();
   }

   private synchronized boolean compareAndSetState(State expected, State update) {
      if (this.state == expected) {
         this.setState(update);
         return true;
      } else {
         return false;
      }
   }

   private synchronized boolean compareAndSetState(State[] expectedStates, State update) {
      for(State expected : expectedStates) {
         if (this.state == expected) {
            this.setState(update);
            return true;
         }
      }

      return false;
   }

   public void start() {
      if (this.compareAndSetState(FileChangeWatcher.State.NEW, FileChangeWatcher.State.STARTING)) {
         this.watcherThread.start();
      }
   }

   public void stop() {
      if (this.compareAndSetState(new State[]{FileChangeWatcher.State.RUNNING, FileChangeWatcher.State.STARTING}, FileChangeWatcher.State.STOPPING)) {
         this.watcherThread.interrupt();
      }

   }

   public static enum State {
      NEW,
      STARTING,
      RUNNING,
      STOPPING,
      STOPPED;
   }

   private class WatcherThread extends ZooKeeperThread {
      private static final String THREAD_NAME = "FileChangeWatcher";
      final WatchService watchService;
      final Consumer callback;

      WatcherThread(WatchService watchService, Consumer callback) {
         super("FileChangeWatcher");
         this.watchService = watchService;
         this.callback = callback;
      }

      public void run() {
         try {
            FileChangeWatcher.LOG.info("{} thread started", this.getName());
            if (FileChangeWatcher.this.compareAndSetState(FileChangeWatcher.State.STARTING, FileChangeWatcher.State.RUNNING)) {
               this.runLoop();
               return;
            }

            State state = FileChangeWatcher.this.getState();
            if (state != FileChangeWatcher.State.STOPPING) {
               throw new IllegalStateException("Unexpected state: " + state);
            }
         } catch (Exception e) {
            FileChangeWatcher.LOG.warn("Error in runLoop()", e);
            throw e;
         } finally {
            try {
               this.watchService.close();
            } catch (IOException e) {
               FileChangeWatcher.LOG.warn("Error closing watch service", e);
            }

            FileChangeWatcher.LOG.info("{} thread finished", this.getName());
            FileChangeWatcher.this.setState(FileChangeWatcher.State.STOPPED);
         }

      }

      private void runLoop() {
         while(true) {
            if (FileChangeWatcher.this.getState() == FileChangeWatcher.State.RUNNING) {
               WatchKey key;
               try {
                  key = this.watchService.take();
               } catch (ClosedWatchServiceException | InterruptedException var6) {
                  FileChangeWatcher.LOG.debug("{} was interrupted and is shutting down...", this.getName());
                  return;
               }

               for(WatchEvent event : key.pollEvents()) {
                  FileChangeWatcher.LOG.debug("Got file changed event: {} with context: {}", event.kind(), event.context());

                  try {
                     this.callback.accept(event);
                  } catch (Throwable e) {
                     FileChangeWatcher.LOG.error("Error from callback", e);
                  }
               }

               boolean isKeyValid = key.reset();
               if (isKeyValid) {
                  continue;
               }

               FileChangeWatcher.LOG.error("Watch key no longer valid, maybe the directory is inaccessible?");
            }

            return;
         }
      }
   }
}
