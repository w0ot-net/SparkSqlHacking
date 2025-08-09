package org.apache.spark.launcher;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractAppHandle implements SparkAppHandle {
   private static final Logger LOG = Logger.getLogger(AbstractAppHandle.class.getName());
   private final LauncherServer server;
   private LauncherServer.ServerConnection connection;
   private List listeners;
   private AtomicReference state;
   private volatile String appId;
   private volatile boolean disposed;

   protected AbstractAppHandle(LauncherServer server) {
      this.server = server;
      this.state = new AtomicReference(SparkAppHandle.State.UNKNOWN);
   }

   public synchronized void addListener(SparkAppHandle.Listener l) {
      if (this.listeners == null) {
         this.listeners = new CopyOnWriteArrayList();
      }

      this.listeners.add(l);
   }

   public SparkAppHandle.State getState() {
      return (SparkAppHandle.State)this.state.get();
   }

   public String getAppId() {
      return this.appId;
   }

   public void stop() {
      CommandBuilderUtils.checkState(this.connection != null, "Application is still not connected.");

      try {
         this.connection.send(new LauncherProtocol.Stop());
      } catch (IOException ioe) {
         throw new RuntimeException(ioe);
      }
   }

   public synchronized void disconnect() {
      if (this.connection != null && this.connection.isOpen()) {
         try {
            this.connection.close();
         } catch (IOException var2) {
         }
      }

      this.dispose();
   }

   void setConnection(LauncherServer.ServerConnection connection) {
      this.connection = connection;
   }

   LauncherConnection getConnection() {
      return this.connection;
   }

   boolean isDisposed() {
      return this.disposed;
   }

   synchronized void dispose() {
      if (!this.isDisposed()) {
         if (this.connection != null) {
            try {
               this.connection.waitForClose();
            } catch (IOException var2) {
            }
         }

         this.server.unregister(this);
         this.setState(SparkAppHandle.State.LOST, false);
         this.disposed = true;
      }

   }

   void setState(SparkAppHandle.State s) {
      this.setState(s, false);
   }

   void setState(SparkAppHandle.State s, boolean force) {
      if (force) {
         this.state.set(s);
         this.fireEvent(false);
      } else {
         SparkAppHandle.State current;
         for(current = (SparkAppHandle.State)this.state.get(); !current.isFinal(); current = (SparkAppHandle.State)this.state.get()) {
            if (this.state.compareAndSet(current, s)) {
               this.fireEvent(false);
               return;
            }
         }

         if (s != SparkAppHandle.State.LOST) {
            LOG.log(Level.WARNING, "Backend requested transition from final state {0} to {1}.", new Object[]{current, s});
         }

      }
   }

   void setAppId(String appId) {
      this.appId = appId;
      this.fireEvent(true);
   }

   private void fireEvent(boolean isInfoChanged) {
      if (this.listeners != null) {
         for(SparkAppHandle.Listener l : this.listeners) {
            if (isInfoChanged) {
               l.infoChanged(this);
            } else {
               l.stateChanged(this);
            }
         }
      }

   }
}
