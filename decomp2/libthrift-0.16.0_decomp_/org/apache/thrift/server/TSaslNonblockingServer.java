package org.apache.thrift.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.security.auth.callback.CallbackHandler;
import org.apache.thrift.TProcessor;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.sasl.NonblockingSaslHandler;
import org.apache.thrift.transport.sasl.TBaseSaslProcessorFactory;
import org.apache.thrift.transport.sasl.TSaslProcessorFactory;
import org.apache.thrift.transport.sasl.TSaslServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSaslNonblockingServer extends TServer {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSaslNonblockingServer.class);
   private static final int DEFAULT_NETWORK_THREADS = 1;
   private static final int DEFAULT_AUTHENTICATION_THREADS = 1;
   private static final int DEFAULT_PROCESSING_THREADS = Runtime.getRuntime().availableProcessors();
   private final AcceptorThread acceptor;
   private final NetworkThreadPool networkThreadPool;
   private final ExecutorService authenticationExecutor;
   private final ExecutorService processingExecutor;
   private final TSaslServerFactory saslServerFactory;
   private final TSaslProcessorFactory saslProcessorFactory;

   public TSaslNonblockingServer(Args args) throws IOException {
      super(args);
      this.acceptor = new AcceptorThread((TNonblockingServerSocket)this.serverTransport_);
      this.networkThreadPool = new NetworkThreadPool(args.networkThreads);
      this.authenticationExecutor = Executors.newFixedThreadPool(args.saslThreads);
      this.processingExecutor = Executors.newFixedThreadPool(args.processingThreads);
      this.saslServerFactory = args.saslServerFactory;
      this.saslProcessorFactory = args.saslProcessorFactory;
   }

   public void serve() {
      if (this.eventHandler_ != null) {
         this.eventHandler_.preServe();
      }

      this.networkThreadPool.start();
      this.acceptor.start();
      this.setServing(true);
   }

   public void stop() {
      if (!this.stopped_) {
         this.setServing(false);
         this.stopped_ = true;
         this.acceptor.wakeup();
         this.networkThreadPool.wakeupAll();
         this.authenticationExecutor.shutdownNow();
         this.processingExecutor.shutdownNow();
      }

   }

   public void shutdown() throws InterruptedException {
      this.stop();
      this.acceptor.join();

      for(NetworkThread networkThread : this.networkThreadPool.networkThreads) {
         networkThread.join();
      }

      while(!this.authenticationExecutor.isTerminated()) {
         this.authenticationExecutor.awaitTermination(10L, TimeUnit.SECONDS);
      }

      while(!this.processingExecutor.isTerminated()) {
         this.processingExecutor.awaitTermination(10L, TimeUnit.SECONDS);
      }

   }

   private class AcceptorThread extends Thread {
      private final TNonblockingServerTransport serverTransport;
      private final Selector acceptSelector;

      private AcceptorThread(TNonblockingServerSocket serverTransport) throws IOException {
         super("acceptor-thread");
         this.serverTransport = serverTransport;
         this.acceptSelector = Selector.open();
         serverTransport.registerSelector(this.acceptSelector);
      }

      public void run() {
         try {
            this.serverTransport.listen();

            while(!TSaslNonblockingServer.this.stopped_) {
               this.select();
               this.acceptNewConnection();
            }
         } catch (TTransportException e) {
            TSaslNonblockingServer.LOGGER.error("Failed to listen on server socket, error " + e.getType(), e);
         } catch (Throwable e) {
            TSaslNonblockingServer.LOGGER.error("Unexpected error in acceptor thread.", e);
         } finally {
            TSaslNonblockingServer.this.stop();
            this.close();
         }

      }

      void wakeup() {
         this.acceptSelector.wakeup();
      }

      private void acceptNewConnection() {
         Iterator<SelectionKey> selectedKeyItr = this.acceptSelector.selectedKeys().iterator();

         while(!TSaslNonblockingServer.this.stopped_ && selectedKeyItr.hasNext()) {
            SelectionKey selected = (SelectionKey)selectedKeyItr.next();
            selectedKeyItr.remove();
            if (selected.isAcceptable()) {
               try {
                  while(true) {
                     TNonblockingTransport connection = this.serverTransport.accept();
                     if (connection == null) {
                        break;
                     }

                     if (!TSaslNonblockingServer.this.networkThreadPool.acceptNewConnection(connection)) {
                        TSaslNonblockingServer.LOGGER.error("Network thread does not accept: " + connection);
                        connection.close();
                     }
                  }
               } catch (TTransportException e) {
                  TSaslNonblockingServer.LOGGER.warn("Failed to accept incoming connection.", e);
               }
            } else {
               TSaslNonblockingServer.LOGGER.error("Not acceptable selection: " + selected.channel());
            }
         }

      }

      private void select() {
         try {
            this.acceptSelector.select();
         } catch (IOException e) {
            TSaslNonblockingServer.LOGGER.error("Failed to select on the server socket.", e);
         }

      }

      private void close() {
         TSaslNonblockingServer.LOGGER.info("Closing acceptor thread.");
         this.serverTransport.close();

         try {
            this.acceptSelector.close();
         } catch (IOException e) {
            TSaslNonblockingServer.LOGGER.error("Failed to close accept selector.", e);
         }

      }
   }

   private class NetworkThread extends Thread {
      private final BlockingQueue incomingConnections = new LinkedBlockingQueue();
      private final BlockingQueue stateTransitions = new LinkedBlockingQueue();
      private final Selector ioSelector = Selector.open();

      NetworkThread(String name) throws IOException {
         super(name);
      }

      public void run() {
         try {
            while(!TSaslNonblockingServer.this.stopped_) {
               this.handleIncomingConnections();
               this.handleStateChanges();
               this.select();
               this.handleIO();
            }
         } catch (Throwable e) {
            TSaslNonblockingServer.LOGGER.error("Unreoverable error in " + this.getName(), e);
         } finally {
            this.close();
         }

      }

      private void handleStateChanges() {
         while(true) {
            NonblockingSaslHandler statemachine = (NonblockingSaslHandler)this.stateTransitions.poll();
            if (statemachine == null) {
               return;
            }

            this.tryRunNextPhase(statemachine);
         }
      }

      private void select() {
         try {
            this.ioSelector.select();
         } catch (IOException e) {
            TSaslNonblockingServer.LOGGER.error("Failed to select in " + this.getName(), e);
         }

      }

      private void handleIO() {
         Iterator<SelectionKey> selectedKeyItr = this.ioSelector.selectedKeys().iterator();

         while(!TSaslNonblockingServer.this.stopped_ && selectedKeyItr.hasNext()) {
            SelectionKey selected = (SelectionKey)selectedKeyItr.next();
            selectedKeyItr.remove();
            if (!selected.isValid()) {
               this.closeChannel(selected);
            }

            NonblockingSaslHandler saslHandler = (NonblockingSaslHandler)selected.attachment();
            if (selected.isReadable()) {
               saslHandler.handleRead();
            } else {
               if (!selected.isWritable()) {
                  TSaslNonblockingServer.LOGGER.error("Invalid intrest op " + selected.interestOps());
                  this.closeChannel(selected);
                  continue;
               }

               saslHandler.handleWrite();
            }

            if (saslHandler.isCurrentPhaseDone()) {
               this.tryRunNextPhase(saslHandler);
            }
         }

      }

      private synchronized void handleIncomingConnections() {
         while(true) {
            TNonblockingTransport connection = (TNonblockingTransport)this.incomingConnections.poll();
            if (connection == null) {
               return;
            }

            if (!connection.isOpen()) {
               TSaslNonblockingServer.LOGGER.warn("Incoming connection is already closed");
            } else {
               try {
                  SelectionKey selectionKey = connection.registerSelector(this.ioSelector, 1);
                  if (selectionKey.isValid()) {
                     NonblockingSaslHandler saslHandler = new NonblockingSaslHandler(selectionKey, connection, TSaslNonblockingServer.this.saslServerFactory, TSaslNonblockingServer.this.saslProcessorFactory, TSaslNonblockingServer.this.inputProtocolFactory_, TSaslNonblockingServer.this.outputProtocolFactory_, TSaslNonblockingServer.this.eventHandler_);
                     selectionKey.attach(saslHandler);
                  }
               } catch (IOException e) {
                  TSaslNonblockingServer.LOGGER.error("Failed to register connection for the selector, close it.", e);
                  connection.close();
               }
            }
         }
      }

      private synchronized void close() {
         TSaslNonblockingServer.LOGGER.warn("Closing " + this.getName());

         while(true) {
            TNonblockingTransport incomingConnection = (TNonblockingTransport)this.incomingConnections.poll();
            if (incomingConnection == null) {
               for(SelectionKey selection : this.ioSelector.keys()) {
                  this.closeChannel(selection);
               }

               try {
                  this.ioSelector.close();
               } catch (IOException e) {
                  TSaslNonblockingServer.LOGGER.error("Failed to close io selector " + this.getName(), e);
               }

               return;
            }

            incomingConnection.close();
         }
      }

      private synchronized void closeChannel(SelectionKey selectionKey) {
         if (selectionKey.attachment() == null) {
            try {
               selectionKey.channel().close();
            } catch (IOException e) {
               TSaslNonblockingServer.LOGGER.error("Failed to close channel.", e);
            } finally {
               selectionKey.cancel();
            }
         } else {
            NonblockingSaslHandler saslHandler = (NonblockingSaslHandler)selectionKey.attachment();
            saslHandler.close();
         }

      }

      private void tryRunNextPhase(NonblockingSaslHandler saslHandler) {
         NonblockingSaslHandler.Phase nextPhase = saslHandler.getNextPhase();
         saslHandler.stepToNextPhase();
         switch (nextPhase) {
            case EVALUATING_SASL_RESPONSE:
               TSaslNonblockingServer.this.authenticationExecutor.submit(new Computation(saslHandler));
               break;
            case PROCESSING:
               TSaslNonblockingServer.this.processingExecutor.submit(new Computation(saslHandler));
               break;
            case CLOSING:
               saslHandler.runCurrentPhase();
         }

      }

      public boolean accept(TNonblockingTransport connection) {
         if (TSaslNonblockingServer.this.stopped_) {
            return false;
         } else if (this.incomingConnections.offer(connection)) {
            this.wakeup();
            return true;
         } else {
            return false;
         }
      }

      private void wakeup() {
         this.ioSelector.wakeup();
      }

      private class Computation implements Runnable {
         private final NonblockingSaslHandler statemachine;

         private Computation(NonblockingSaslHandler statemachine) {
            this.statemachine = statemachine;
         }

         public void run() {
            try {
               while(true) {
                  if (!this.statemachine.isCurrentPhaseDone()) {
                     this.statemachine.runCurrentPhase();
                  } else {
                     NetworkThread.this.stateTransitions.add(this.statemachine);
                     NetworkThread.this.wakeup();
                     break;
                  }
               }
            } catch (Throwable e) {
               TSaslNonblockingServer.LOGGER.error("Damn it!", e);
            }

         }
      }
   }

   private class NetworkThreadPool {
      private final List networkThreads;
      private int accepted = 0;

      NetworkThreadPool(int size) throws IOException {
         this.networkThreads = new ArrayList(size);
         int digits = (int)Math.log10((double)size) + 1;
         String threadNamePattern = "network-thread-%0" + digits + "d";

         for(int i = 0; i < size; ++i) {
            this.networkThreads.add(TSaslNonblockingServer.this.new NetworkThread(String.format(threadNamePattern, i)));
         }

      }

      boolean acceptNewConnection(TNonblockingTransport connection) {
         return ((NetworkThread)this.networkThreads.get(this.accepted++ % this.networkThreads.size())).accept(connection);
      }

      public void start() {
         for(NetworkThread thread : this.networkThreads) {
            thread.start();
         }

      }

      void wakeupAll() {
         for(NetworkThread networkThread : this.networkThreads) {
            networkThread.wakeup();
         }

      }
   }

   public static class Args extends TServer.AbstractServerArgs {
      private int networkThreads = 1;
      private int saslThreads = 1;
      private int processingThreads;
      private TSaslServerFactory saslServerFactory;
      private TSaslProcessorFactory saslProcessorFactory;

      public Args(TNonblockingServerTransport transport) {
         super(transport);
         this.processingThreads = TSaslNonblockingServer.DEFAULT_PROCESSING_THREADS;
         this.saslServerFactory = new TSaslServerFactory();
      }

      public Args networkThreads(int networkThreads) {
         this.networkThreads = networkThreads <= 0 ? 1 : networkThreads;
         return this;
      }

      public Args saslThreads(int authenticationThreads) {
         this.saslThreads = authenticationThreads <= 0 ? 1 : authenticationThreads;
         return this;
      }

      public Args processingThreads(int processingThreads) {
         this.processingThreads = processingThreads <= 0 ? TSaslNonblockingServer.DEFAULT_PROCESSING_THREADS : processingThreads;
         return this;
      }

      public Args processor(TProcessor processor) {
         this.saslProcessorFactory = new TBaseSaslProcessorFactory(processor);
         return this;
      }

      public Args saslProcessorFactory(TSaslProcessorFactory saslProcessorFactory) {
         if (saslProcessorFactory == null) {
            throw new NullPointerException("Processor factory cannot be null");
         } else {
            this.saslProcessorFactory = saslProcessorFactory;
            return this;
         }
      }

      public Args addSaslMechanism(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
         this.saslServerFactory.addSaslMechanism(mechanism, protocol, serverName, props, cbh);
         return this;
      }

      public Args saslServerFactory(TSaslServerFactory saslServerFactory) {
         if (saslServerFactory == null) {
            throw new NullPointerException("saslServerFactory cannot be null");
         } else {
            this.saslServerFactory = saslServerFactory;
            return this;
         }
      }
   }
}
