package org.apache.logging.log4j.core.config;

import java.io.File;
import java.util.List;
import org.apache.logging.log4j.core.util.AbstractWatcher;
import org.apache.logging.log4j.core.util.FileWatcher;
import org.apache.logging.log4j.core.util.Source;
import org.apache.logging.log4j.core.util.Watcher;

public class ConfigurationFileWatcher extends AbstractWatcher implements FileWatcher {
   private File file;
   private long lastModifiedMillis;

   public ConfigurationFileWatcher(final Configuration configuration, final Reconfigurable reconfigurable, final List configurationListeners, long lastModifiedMillis) {
      super(configuration, reconfigurable, configurationListeners);
      this.lastModifiedMillis = lastModifiedMillis;
   }

   public long getLastModified() {
      return this.file != null ? this.file.lastModified() : 0L;
   }

   public void fileModified(final File file) {
      this.lastModifiedMillis = file.lastModified();
   }

   public void watching(final Source source) {
      this.file = source.getFile();
      this.lastModifiedMillis = this.file.lastModified();
      super.watching(source);
   }

   public boolean isModified() {
      return this.lastModifiedMillis != this.file.lastModified();
   }

   public Watcher newWatcher(final Reconfigurable reconfigurable, final List listeners, long lastModifiedMillis) {
      ConfigurationFileWatcher watcher = new ConfigurationFileWatcher(this.getConfiguration(), reconfigurable, listeners, lastModifiedMillis);
      if (this.getSource() != null) {
         watcher.watching(this.getSource());
      }

      return watcher;
   }
}
