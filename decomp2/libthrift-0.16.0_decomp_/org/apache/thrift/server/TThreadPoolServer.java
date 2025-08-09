package org.apache.thrift.server;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TThreadPoolServer extends TServer {
   private static final Logger LOGGER = LoggerFactory.getLogger(TThreadPoolServer.class);
   private ExecutorService executorService_;
   private final TimeUnit stopTimeoutUnit;
   private final long stopTimeoutVal;

   public TThreadPoolServer(Args args) {
      super(args);
      this.stopTimeoutUnit = args.stopTimeoutUnit;
      this.stopTimeoutVal = (long)args.stopTimeoutVal;
      this.executorService_ = args.executorService != null ? args.executorService : createDefaultExecutorService(args);
   }

   private static ExecutorService createDefaultExecutorService(Args args) {
      return new ThreadPoolExecutor(args.minWorkerThreads, args.maxWorkerThreads, 60L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory() {
         final AtomicLong count = new AtomicLong();

         public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName(String.format("TThreadPoolServer WorkerProcess-%d", this.count.getAndIncrement()));
            return thread;
         }
      });
   }

   protected ExecutorService getExecutorService() {
      return this.executorService_;
   }

   protected boolean preServe() {
      try {
         this.serverTransport_.listen();
      } catch (TTransportException ttx) {
         LOGGER.error("Error occurred during listening.", ttx);
         return false;
      }

      if (this.eventHandler_ != null) {
         this.eventHandler_.preServe();
      }

      this.stopped_ = false;
      this.setServing(true);
      return true;
   }

   public void serve() {
      if (this.preServe()) {
         this.execute();
         this.executorService_.shutdownNow();
         if (!this.waitForShutdown()) {
            LOGGER.error("Shutdown is not done after " + this.stopTimeoutVal + this.stopTimeoutUnit);
         }

         this.setServing(false);
      }
   }

   protected void execute() {
      while(!this.stopped_) {
         try {
            TTransport client = this.serverTransport_.accept();

            try {
               this.executorService_.execute(new WorkerProcess(client));
            } catch (RejectedExecutionException var3) {
               if (!this.stopped_) {
                  LOGGER.warn("ThreadPool is saturated with incoming requests. Closing latest connection.");
               }

               client.close();
            }
         } catch (TTransportException ttx) {
            if (!this.stopped_) {
               LOGGER.warn("Transport error occurred during acceptance of message", ttx);
            }
         }
      }

   }

   protected boolean waitForShutdown() {
      long timeoutMS = this.stopTimeoutUnit.toMillis(this.stopTimeoutVal);

      long newnow;
      for(long now = System.currentTimeMillis(); timeoutMS >= 0L; now = newnow) {
         try {
            return this.executorService_.awaitTermination(timeoutMS, TimeUnit.MILLISECONDS);
         } catch (InterruptedException var8) {
            newnow = System.currentTimeMillis();
            timeoutMS -= newnow - now;
         }
      }

      return false;
   }

   public void stop() {
      this.stopped_ = true;
      this.serverTransport_.interrupt();
   }

   public static class Args extends TServer.AbstractServerArgs {
      public int minWorkerThreads = 5;
      public int maxWorkerThreads = Integer.MAX_VALUE;
      public ExecutorService executorService;
      public int stopTimeoutVal = 60;
      public TimeUnit stopTimeoutUnit;

      public Args(TServerTransport transport) {
         super(transport);
         this.stopTimeoutUnit = TimeUnit.SECONDS;
      }

      public Args minWorkerThreads(int n) {
         this.minWorkerThreads = n;
         return this;
      }

      public Args maxWorkerThreads(int n) {
         this.maxWorkerThreads = n;
         return this;
      }

      public Args stopTimeoutVal(int n) {
         this.stopTimeoutVal = n;
         return this;
      }

      public Args stopTimeoutUnit(TimeUnit tu) {
         this.stopTimeoutUnit = tu;
         return this;
      }

      public Args executorService(ExecutorService executorService) {
         this.executorService = executorService;
         return this;
      }
   }

   private class WorkerProcess implements Runnable {
      private TTransport client_;

      private WorkerProcess(TTransport client) {
         this.client_ = client;
      }

      public void run() {
         TProcessor processor = null;
         TTransport inputTransport = null;
         TTransport outputTransport = null;
         TProtocol inputProtocol = null;
         TProtocol outputProtocol = null;
         Optional<TServerEventHandler> eventHandler = Optional.empty();
         ServerContext connectionContext = null;

         try {
            processor = TThreadPoolServer.this.processorFactory_.getProcessor(this.client_);
            inputTransport = TThreadPoolServer.this.inputTransportFactory_.getTransport(this.client_);
            outputTransport = TThreadPoolServer.this.outputTransportFactory_.getTransport(this.client_);
            inputProtocol = TThreadPoolServer.this.inputProtocolFactory_.getProtocol(inputTransport);
            outputProtocol = TThreadPoolServer.this.outputProtocolFactory_.getProtocol(outputTransport);
            eventHandler = Optional.ofNullable(TThreadPoolServer.this.getEventHandler());
            if (eventHandler.isPresent()) {
               connectionContext = ((TServerEventHandler)eventHandler.get()).createContext(inputProtocol, outputProtocol);
            }

            for(; !Thread.currentThread().isInterrupted(); processor.process(inputProtocol, outputProtocol)) {
               if (eventHandler.isPresent()) {
                  ((TServerEventHandler)eventHandler.get()).processContext(connectionContext, inputTransport, outputTransport);
               }
            }

            TThreadPoolServer.LOGGER.debug("WorkerProcess requested to shutdown");
         } catch (Exception x) {
            TThreadPoolServer.LOGGER.debug("Error processing request", x);
            if (!this.isIgnorableException(x)) {
               TThreadPoolServer.LOGGER.error((x instanceof TException ? "Thrift " : "") + "Error occurred during processing of message.", x);
            }
         } finally {
            if (eventHandler.isPresent()) {
               ((TServerEventHandler)eventHandler.get()).deleteContext(connectionContext, inputProtocol, outputProtocol);
            }

            if (inputTransport != null) {
               inputTransport.close();
            }

            if (outputTransport != null) {
               outputTransport.close();
            }

            if (this.client_.isOpen()) {
               this.client_.close();
            }

         }

      }

      private boolean isIgnorableException(Exception x) {
         TTransportException tTransportException = null;
         if (x instanceof TTransportException) {
            tTransportException = (TTransportException)x;
         } else if (x.getCause() instanceof TTransportException) {
            tTransportException = (TTransportException)x.getCause();
         }

         if (tTransportException != null) {
            switch (tTransportException.getType()) {
               case 3:
               case 4:
                  return true;
            }
         }

         return false;
      }
   }
}
