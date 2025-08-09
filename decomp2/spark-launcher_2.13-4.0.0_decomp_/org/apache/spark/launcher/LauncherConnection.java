package org.apache.spark.launcher;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class LauncherConnection implements Closeable, Runnable {
   private static final Logger LOG = Logger.getLogger(LauncherConnection.class.getName());
   private final Socket socket;
   private final ObjectOutputStream out;
   private volatile boolean closed;

   LauncherConnection(Socket socket) throws IOException {
      this.socket = socket;
      this.out = new ObjectOutputStream(socket.getOutputStream());
      this.closed = false;
   }

   protected abstract void handle(LauncherProtocol.Message var1) throws IOException;

   public void run() {
      try {
         FilteredObjectInputStream in = new FilteredObjectInputStream(this.socket.getInputStream());

         while(this.isOpen()) {
            LauncherProtocol.Message msg = (LauncherProtocol.Message)in.readObject();
            this.handle(msg);
         }
      } catch (EOFException var5) {
         try {
            this.close();
         } catch (Exception var4) {
         }
      } catch (Exception e) {
         if (!this.closed) {
            LOG.log(Level.WARNING, "Error in inbound message handling.", e);

            try {
               this.close();
            } catch (Exception var3) {
            }
         }
      }

   }

   protected synchronized void send(LauncherProtocol.Message msg) throws IOException {
      try {
         CommandBuilderUtils.checkState(!this.closed, "Disconnected.");
         this.out.writeObject(msg);
         this.out.flush();
      } catch (IOException var5) {
         if (!this.closed) {
            LOG.log(Level.WARNING, "Error when sending message.", var5);

            try {
               this.close();
            } catch (Exception var4) {
            }
         }

         throw var5;
      }
   }

   public synchronized void close() throws IOException {
      if (this.isOpen()) {
         this.closed = true;
         this.socket.close();
      }

   }

   boolean isOpen() {
      return !this.closed;
   }
}
