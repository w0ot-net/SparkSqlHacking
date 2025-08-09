package org.glassfish.jersey.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.server.internal.LocalizationMessages;

public class Broadcaster implements BroadcasterListener {
   private final CopyOnWriteArrayList listeners;
   private final ConcurrentLinkedQueue chunkedOutputs;

   public Broadcaster() {
      this(Broadcaster.class);
   }

   protected Broadcaster(Class subclass) {
      this.listeners = new CopyOnWriteArrayList();
      this.chunkedOutputs = new ConcurrentLinkedQueue();
      if (subclass != this.getClass()) {
         this.listeners.add(this);
      }

   }

   public boolean add(ChunkedOutput chunkedOutput) {
      return this.chunkedOutputs.offer(chunkedOutput);
   }

   public boolean remove(ChunkedOutput chunkedOutput) {
      return this.chunkedOutputs.remove(chunkedOutput);
   }

   public boolean add(BroadcasterListener listener) {
      return this.listeners.add(listener);
   }

   public boolean remove(BroadcasterListener listener) {
      return this.listeners.remove(listener);
   }

   public void broadcast(final Object chunk) {
      this.forEachOutput(new Task() {
         public void run(ChunkedOutput cr) throws IOException {
            cr.write(chunk);
         }
      });
   }

   public void closeAll() {
      this.forEachOutput(new Task() {
         public void run(ChunkedOutput cr) throws IOException {
            cr.close();
         }
      });
   }

   public void onException(ChunkedOutput chunkedOutput, Exception exception) {
   }

   public void onClose(ChunkedOutput chunkedOutput) {
   }

   private void forEachOutput(Task t) {
      Iterator<ChunkedOutput<T>> iterator = this.chunkedOutputs.iterator();

      while(iterator.hasNext()) {
         ChunkedOutput<T> chunkedOutput = (ChunkedOutput)iterator.next();
         if (!chunkedOutput.isClosed()) {
            try {
               t.run(chunkedOutput);
            } catch (Exception e) {
               this.fireOnException(chunkedOutput, e);
            }
         }

         if (chunkedOutput.isClosed()) {
            iterator.remove();
            this.fireOnClose(chunkedOutput);
         }
      }

   }

   private void forEachListener(Task t) {
      for(BroadcasterListener listener : this.listeners) {
         try {
            t.run(listener);
         } catch (Exception e) {
            Logger.getLogger(Broadcaster.class.getName()).log(Level.WARNING, LocalizationMessages.BROADCASTER_LISTENER_EXCEPTION(e.getClass().getSimpleName()), e);
         }
      }

   }

   private void fireOnException(final ChunkedOutput chunkedOutput, final Exception exception) {
      this.forEachListener(new Task() {
         public void run(BroadcasterListener parameter) throws IOException {
            parameter.onException(chunkedOutput, exception);
         }
      });
   }

   private void fireOnClose(final ChunkedOutput chunkedOutput) {
      this.forEachListener(new Task() {
         public void run(BroadcasterListener parameter) throws IOException {
            parameter.onClose(chunkedOutput);
         }
      });
   }

   private interface Task {
      void run(Object var1) throws IOException;
   }
}
