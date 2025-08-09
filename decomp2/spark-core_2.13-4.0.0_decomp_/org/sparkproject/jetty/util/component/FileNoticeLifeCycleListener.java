package org.sparkproject.jetty.util.component;

import java.io.FileWriter;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class FileNoticeLifeCycleListener implements LifeCycle.Listener {
   private static final Logger LOG = LoggerFactory.getLogger(FileNoticeLifeCycleListener.class);
   private final String _filename;

   public FileNoticeLifeCycleListener(String filename) {
      this._filename = filename;
   }

   private void writeState(String action, LifeCycle lifecycle) {
      try {
         Writer out = new FileWriter(this._filename, true);

         try {
            out.append(action).append(" ").append(lifecycle.toString()).append("\n");
         } catch (Throwable var7) {
            try {
               out.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         out.close();
      } catch (Exception e) {
         LOG.warn("Unable to write state", e);
      }

   }

   public void lifeCycleStarting(LifeCycle event) {
      this.writeState("STARTING", event);
   }

   public void lifeCycleStarted(LifeCycle event) {
      this.writeState("STARTED", event);
   }

   public void lifeCycleFailure(LifeCycle event, Throwable cause) {
      this.writeState("FAILED", event);
   }

   public void lifeCycleStopping(LifeCycle event) {
      this.writeState("STOPPING", event);
   }

   public void lifeCycleStopped(LifeCycle event) {
      this.writeState("STOPPED", event);
   }
}
