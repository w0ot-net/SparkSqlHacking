package org.apache.log4j.helpers;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;

public abstract class FileWatchdog extends Thread {
   public static final long DEFAULT_DELAY = 60000L;
   protected String filename;
   protected long delay = 60000L;
   File file;
   long lastModified;
   boolean warnedAlready;
   boolean interrupted;

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The filename comes from a system property."
   )
   protected FileWatchdog(final String fileName) {
      super("FileWatchdog");
      this.filename = fileName;
      this.file = new File(fileName);
      this.setDaemon(true);
      this.checkAndConfigure();
   }

   protected void checkAndConfigure() {
      boolean fileExists;
      try {
         fileExists = this.file.exists();
      } catch (SecurityException var4) {
         LogLog.warn("Was not allowed to read check file existance, file:[" + this.filename + "].");
         this.interrupted = true;
         return;
      }

      if (fileExists) {
         long fileLastMod = this.file.lastModified();
         if (fileLastMod > this.lastModified) {
            this.lastModified = fileLastMod;
            this.doOnChange();
            this.warnedAlready = false;
         }
      } else if (!this.warnedAlready) {
         LogLog.debug("[" + this.filename + "] does not exist.");
         this.warnedAlready = true;
      }

   }

   protected abstract void doOnChange();

   public void run() {
      for(; !this.interrupted; this.checkAndConfigure()) {
         try {
            Thread.sleep(this.delay);
         } catch (InterruptedException var2) {
         }
      }

   }

   public void setDelay(final long delayMillis) {
      this.delay = delayMillis;
   }
}
