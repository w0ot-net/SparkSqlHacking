package org.apache.thrift.async;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TAsyncClientManager {
   private static final Logger LOGGER = LoggerFactory.getLogger(TAsyncClientManager.class.getName());
   private final SelectThread selectThread = new SelectThread();
   private final ConcurrentLinkedQueue pendingCalls = new ConcurrentLinkedQueue();

   public TAsyncClientManager() throws IOException {
      this.selectThread.start();
   }

   public void call(TAsyncMethodCall method) throws TException {
      if (!this.isRunning()) {
         throw new TException("SelectThread is not running");
      } else {
         method.prepareMethodCall();
         this.pendingCalls.add(method);
         this.selectThread.getSelector().wakeup();
      }
   }

   public void stop() {
      this.selectThread.finish();
   }

   public boolean isRunning() {
      return this.selectThread.isAlive();
   }

   private class SelectThread extends Thread {
      private final Selector selector = SelectorProvider.provider().openSelector();
      private volatile boolean running = true;
      private final TreeSet timeoutWatchSet = new TreeSet(new TAsyncMethodCallTimeoutComparator());

      public SelectThread() throws IOException {
         this.setName("TAsyncClientManager#SelectorThread " + this.getId());
         this.setDaemon(true);
      }

      public Selector getSelector() {
         return this.selector;
      }

      public void finish() {
         this.running = false;
         this.selector.wakeup();
      }

      public void run() {
         while(this.running) {
            try {
               try {
                  if (this.timeoutWatchSet.size() == 0) {
                     this.selector.select();
                  } else {
                     long nextTimeout = ((TAsyncMethodCall)this.timeoutWatchSet.first()).getTimeoutTimestamp();
                     long selectTime = nextTimeout - System.currentTimeMillis();
                     if (selectTime > 0L) {
                        this.selector.select(selectTime);
                     } else {
                        this.selector.selectNow();
                     }
                  }
               } catch (IOException e) {
                  TAsyncClientManager.LOGGER.error("Caught IOException in TAsyncClientManager!", e);
               }

               this.transitionMethods();
               this.timeoutMethods();
               this.startPendingMethods();
            } catch (Exception exception) {
               TAsyncClientManager.LOGGER.error("Ignoring uncaught exception in SelectThread", exception);
            }
         }

         try {
            this.selector.close();
         } catch (IOException ex) {
            TAsyncClientManager.LOGGER.warn("Could not close selector. This may result in leaked resources!", ex);
         }

      }

      private void transitionMethods() {
         try {
            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();

            while(keys.hasNext()) {
               SelectionKey key = (SelectionKey)keys.next();
               keys.remove();
               if (key.isValid()) {
                  TAsyncMethodCall methodCall = (TAsyncMethodCall)key.attachment();
                  methodCall.transition(key);
                  if (methodCall.isFinished() || methodCall.getClient().hasError()) {
                     this.timeoutWatchSet.remove(methodCall);
                  }
               }
            }
         } catch (ClosedSelectorException e) {
            TAsyncClientManager.LOGGER.error("Caught ClosedSelectorException in TAsyncClientManager!", e);
         }

      }

      private void timeoutMethods() {
         Iterator<TAsyncMethodCall> iterator = this.timeoutWatchSet.iterator();
         long currentTime = System.currentTimeMillis();

         while(iterator.hasNext()) {
            TAsyncMethodCall methodCall = (TAsyncMethodCall)iterator.next();
            if (currentTime < methodCall.getTimeoutTimestamp()) {
               break;
            }

            iterator.remove();
            methodCall.onError(new TimeoutException("Operation " + methodCall.getClass() + " timed out after " + (currentTime - methodCall.getStartTime()) + " ms."));
         }

      }

      private void startPendingMethods() {
         TAsyncMethodCall methodCall;
         while((methodCall = (TAsyncMethodCall)TAsyncClientManager.this.pendingCalls.poll()) != null) {
            try {
               methodCall.start(this.selector);
               TAsyncClient client = methodCall.getClient();
               if (client.hasTimeout() && !client.hasError()) {
                  this.timeoutWatchSet.add(methodCall);
               }
            } catch (Exception exception) {
               TAsyncClientManager.LOGGER.warn("Caught exception in TAsyncClientManager!", exception);
               methodCall.onError(exception);
            }
         }

      }
   }

   private static class TAsyncMethodCallTimeoutComparator implements Comparator, Serializable {
      private TAsyncMethodCallTimeoutComparator() {
      }

      public int compare(TAsyncMethodCall left, TAsyncMethodCall right) {
         return left.getTimeoutTimestamp() == right.getTimeoutTimestamp() ? (int)(left.getSequenceId() - right.getSequenceId()) : (int)(left.getTimeoutTimestamp() - right.getTimeoutTimestamp());
      }
   }
}
