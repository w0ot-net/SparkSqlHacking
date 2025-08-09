package org.apache.logging.log4j.core.util;

import java.io.File;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationListener;
import org.apache.logging.log4j.core.config.Reconfigurable;

public class WrappedFileWatcher extends AbstractWatcher implements FileWatcher {
   private final FileWatcher watcher;
   private volatile long lastModifiedMillis;

   public WrappedFileWatcher(FileWatcher watcher, final Configuration configuration, final Reconfigurable reconfigurable, final List configurationListeners, final long lastModifiedMillis) {
      super(configuration, reconfigurable, configurationListeners);
      this.watcher = watcher;
      this.lastModifiedMillis = lastModifiedMillis;
   }

   public WrappedFileWatcher(final FileWatcher watcher) {
      super((Configuration)null, (Reconfigurable)null, (List)null);
      this.watcher = watcher;
   }

   public long getLastModified() {
      return this.lastModifiedMillis;
   }

   public void fileModified(final File file) {
      this.watcher.fileModified(file);
   }

   public boolean isModified() {
      long lastModified = this.getSource().getFile().lastModified();
      if (this.lastModifiedMillis != lastModified) {
         this.lastModifiedMillis = lastModified;
         return true;
      } else {
         return false;
      }
   }

   public List getListeners() {
      return super.getListeners() != null ? Collections.unmodifiableList(super.getListeners()) : null;
   }

   public void modified() {
      if (this.getListeners() != null) {
         super.modified();
      }

      this.fileModified(this.getSource().getFile());
      this.lastModifiedMillis = this.getSource().getFile().lastModified();
   }

   public void watching(final Source source) {
      this.lastModifiedMillis = source.getFile().lastModified();
      super.watching(source);
   }

   public Watcher newWatcher(final Reconfigurable reconfigurable, final List listeners, long lastModifiedMillis) {
      WrappedFileWatcher watcher = new WrappedFileWatcher(this.watcher, this.getConfiguration(), reconfigurable, listeners, lastModifiedMillis);
      if (this.getSource() != null) {
         watcher.watching(this.getSource());
      }

      return watcher;
   }
}
