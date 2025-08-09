package org.apache.thrift.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;

public class TNonblockingServer extends AbstractNonblockingServer {
   private SelectAcceptThread selectAcceptThread_;

   public TNonblockingServer(AbstractNonblockingServer.AbstractNonblockingServerArgs args) {
      super(args);
   }

   protected boolean startThreads() {
      try {
         this.selectAcceptThread_ = new SelectAcceptThread((TNonblockingServerTransport)this.serverTransport_);
         this.selectAcceptThread_.start();
         return true;
      } catch (IOException e) {
         this.LOGGER.error("Failed to start selector thread!", e);
         return false;
      }
   }

   protected void waitForShutdown() {
      this.joinSelector();
   }

   protected void joinSelector() {
      try {
         this.selectAcceptThread_.join();
      } catch (InterruptedException e) {
         this.LOGGER.debug("Interrupted while waiting for accept thread", e);
         Thread.currentThread().interrupt();
      }

   }

   public void stop() {
      this.stopped_ = true;
      if (this.selectAcceptThread_ != null) {
         this.selectAcceptThread_.wakeupSelector();
      }

   }

   protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      frameBuffer.invoke();
      return true;
   }

   public boolean isStopped() {
      return this.selectAcceptThread_.isStopped();
   }

   public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs {
      public Args(TNonblockingServerTransport transport) {
         super(transport);
      }
   }

   protected class SelectAcceptThread extends AbstractNonblockingServer.AbstractSelectThread {
      private final TNonblockingServerTransport serverTransport;

      public SelectAcceptThread(TNonblockingServerTransport serverTransport) throws IOException {
         this.serverTransport = serverTransport;
         serverTransport.registerSelector(this.selector);
      }

      public boolean isStopped() {
         return TNonblockingServer.this.stopped_;
      }

      public void run() {
         try {
            if (TNonblockingServer.this.eventHandler_ != null) {
               TNonblockingServer.this.eventHandler_.preServe();
            }

            while(!TNonblockingServer.this.stopped_) {
               this.select();
               this.processInterestChanges();
            }

            for(SelectionKey selectionKey : this.selector.keys()) {
               this.cleanupSelectionKey(selectionKey);
            }
         } catch (Throwable t) {
            TNonblockingServer.this.LOGGER.error("run() exiting due to uncaught error", t);
         } finally {
            try {
               this.selector.close();
            } catch (IOException e) {
               TNonblockingServer.this.LOGGER.error("Got an IOException while closing selector!", e);
            }

            TNonblockingServer.this.stopped_ = true;
         }

      }

      private void select() {
         try {
            this.selector.select();
            Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();

            while(!TNonblockingServer.this.stopped_ && selectedKeys.hasNext()) {
               SelectionKey key = (SelectionKey)selectedKeys.next();
               selectedKeys.remove();
               if (!key.isValid()) {
                  this.cleanupSelectionKey(key);
               } else if (key.isAcceptable()) {
                  this.handleAccept();
               } else if (key.isReadable()) {
                  this.handleRead(key);
               } else if (key.isWritable()) {
                  this.handleWrite(key);
               } else {
                  TNonblockingServer.this.LOGGER.warn("Unexpected state in select! " + key.interestOps());
               }
            }
         } catch (IOException e) {
            TNonblockingServer.this.LOGGER.warn("Got an IOException while selecting!", e);
         }

      }

      protected AbstractNonblockingServer.FrameBuffer createFrameBuffer(TNonblockingTransport trans, SelectionKey selectionKey, AbstractNonblockingServer.AbstractSelectThread selectThread) throws TTransportException {
         return (AbstractNonblockingServer.FrameBuffer)(TNonblockingServer.this.processorFactory_.isAsyncProcessor() ? TNonblockingServer.this.new AsyncFrameBuffer(trans, selectionKey, selectThread) : TNonblockingServer.this.new FrameBuffer(trans, selectionKey, selectThread));
      }

      private void handleAccept() throws IOException {
         SelectionKey clientKey = null;
         TNonblockingTransport client = null;

         try {
            client = this.serverTransport.accept();
            clientKey = client.registerSelector(this.selector, 1);
            AbstractNonblockingServer.FrameBuffer frameBuffer = this.createFrameBuffer(client, clientKey, this);
            clientKey.attach(frameBuffer);
         } catch (TTransportException tte) {
            TNonblockingServer.this.LOGGER.warn("Exception trying to accept!", tte);
            if (clientKey != null) {
               this.cleanupSelectionKey(clientKey);
            }

            if (client != null) {
               client.close();
            }
         }

      }
   }
}
