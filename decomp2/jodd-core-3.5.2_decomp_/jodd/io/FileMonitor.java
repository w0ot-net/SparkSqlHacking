package jodd.io;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class FileMonitor {
   protected final Map files;
   protected final Set listeners;
   protected final long pollingInterval;
   protected Timer timer;
   protected final Object lock = new Object();

   public FileMonitor(long pollingInterval) {
      this.pollingInterval = pollingInterval;
      this.files = new HashMap();
      this.listeners = new HashSet();
      this.start();
   }

   public void start() {
      if (this.timer == null) {
         this.timer = new Timer(true);
         this.timer.schedule(new FileMonitorNotifier(), 0L, this.pollingInterval);
      }

   }

   public void stop() {
      if (this.timer != null) {
         this.timer.cancel();
         this.timer = null;
      }

   }

   public void monitorFile(File file) {
      synchronized(this.lock) {
         if (!this.files.containsKey(file)) {
            long modifiedTime = file.exists() ? file.lastModified() : -1L;
            this.files.put(file, new Long(modifiedTime));
         }

      }
   }

   public void releaseFile(File file) {
      synchronized(this.lock) {
         this.files.remove(file);
      }
   }

   public void registerListener(FileChangeListener fileChangeListener) {
      synchronized(this.lock) {
         for(FileChangeListener listener : this.listeners) {
            if (listener == fileChangeListener) {
               return;
            }
         }

         this.listeners.add(fileChangeListener);
      }
   }

   public void removeListener(FileChangeListener fileChangeListener) {
      synchronized(this.lock) {
         Iterator<FileChangeListener> i = this.listeners.iterator();

         while(i.hasNext()) {
            FileChangeListener listener = (FileChangeListener)i.next();
            if (listener == fileChangeListener) {
               i.remove();
               break;
            }
         }

      }
   }

   public void removeAllListeners() {
      synchronized(this.lock) {
         this.listeners.clear();
      }
   }

   protected class FileMonitorNotifier extends TimerTask {
      public void run() {
         synchronized(FileMonitor.this.lock) {
            for(Map.Entry entry : FileMonitor.this.files.entrySet()) {
               File file = (File)entry.getKey();
               long lastModifiedTime = (Long)entry.getValue();
               long newModifiedTime = file.exists() ? file.lastModified() : -1L;
               if (newModifiedTime != lastModifiedTime) {
                  entry.setValue(new Long(newModifiedTime));

                  for(FileChangeListener listener : FileMonitor.this.listeners) {
                     listener.onFileChange(file);
                  }
               }
            }

         }
      }
   }
}
