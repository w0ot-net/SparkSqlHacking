package org.apache.spark.launcher;

import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class ChildProcAppHandle extends AbstractAppHandle {
   private static final Logger LOG = Logger.getLogger(ChildProcAppHandle.class.getName());
   private volatile Process childProc;
   private volatile OutputRedirector redirector;

   ChildProcAppHandle(LauncherServer server) {
      super(server);
   }

   public synchronized void disconnect() {
      try {
         super.disconnect();
      } finally {
         if (this.redirector != null) {
            this.redirector.stop();
         }

      }

   }

   public Optional getError() {
      return this.redirector != null ? Optional.ofNullable(this.redirector.getError()) : Optional.empty();
   }

   public synchronized void kill() {
      if (!this.isDisposed()) {
         this.setState(SparkAppHandle.State.KILLED);
         this.disconnect();
         if (this.childProc != null) {
            if (this.childProc.isAlive()) {
               this.childProc.destroyForcibly();
            }

            this.childProc = null;
         }
      }

   }

   void setChildProc(Process childProc, String loggerName, InputStream logStream) {
      this.childProc = childProc;
      if (logStream != null) {
         this.redirector = new OutputRedirector(logStream, loggerName, SparkLauncher.REDIRECTOR_FACTORY, this);
      } else {
         SparkLauncher.REDIRECTOR_FACTORY.newThread(this::monitorChild).start();
      }

   }

   void monitorChild() {
      Process proc = this.childProc;
      if (proc != null) {
         while(proc.isAlive()) {
            try {
               proc.waitFor();
            } catch (Exception e) {
               LOG.log(Level.WARNING, "Exception waiting for child process to exit.", e);
            }
         }

         synchronized(this) {
            if (!this.isDisposed()) {
               int ec;
               try {
                  ec = proc.exitValue();
               } catch (Exception e) {
                  LOG.log(Level.WARNING, "Exception getting child process exit code, assuming failure.", e);
                  ec = 1;
               }

               if (ec != 0) {
                  SparkAppHandle.State currState = this.getState();
                  if (!currState.isFinal() || currState == SparkAppHandle.State.FINISHED) {
                     this.setState(SparkAppHandle.State.FAILED, true);
                  }
               }

               this.dispose();
            }
         }
      }
   }
}
