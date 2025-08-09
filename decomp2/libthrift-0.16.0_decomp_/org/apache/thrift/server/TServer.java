package org.apache.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

public abstract class TServer {
   protected TProcessorFactory processorFactory_;
   protected TServerTransport serverTransport_;
   protected TTransportFactory inputTransportFactory_;
   protected TTransportFactory outputTransportFactory_;
   protected TProtocolFactory inputProtocolFactory_;
   protected TProtocolFactory outputProtocolFactory_;
   private volatile boolean isServing;
   protected TServerEventHandler eventHandler_;
   protected volatile boolean stopped_ = false;

   protected TServer(AbstractServerArgs args) {
      this.processorFactory_ = args.processorFactory;
      this.serverTransport_ = args.serverTransport;
      this.inputTransportFactory_ = args.inputTransportFactory;
      this.outputTransportFactory_ = args.outputTransportFactory;
      this.inputProtocolFactory_ = args.inputProtocolFactory;
      this.outputProtocolFactory_ = args.outputProtocolFactory;
   }

   public abstract void serve();

   public void stop() {
   }

   public boolean isServing() {
      return this.isServing;
   }

   protected void setServing(boolean serving) {
      this.isServing = serving;
   }

   public void setServerEventHandler(TServerEventHandler eventHandler) {
      this.eventHandler_ = eventHandler;
   }

   public TServerEventHandler getEventHandler() {
      return this.eventHandler_;
   }

   public boolean getShouldStop() {
      return this.stopped_;
   }

   public void setShouldStop(boolean shouldStop) {
      this.stopped_ = shouldStop;
   }

   public static class Args extends AbstractServerArgs {
      public Args(TServerTransport transport) {
         super(transport);
      }
   }

   public abstract static class AbstractServerArgs {
      final TServerTransport serverTransport;
      TProcessorFactory processorFactory;
      TTransportFactory inputTransportFactory = new TTransportFactory();
      TTransportFactory outputTransportFactory = new TTransportFactory();
      TProtocolFactory inputProtocolFactory = new TBinaryProtocol.Factory();
      TProtocolFactory outputProtocolFactory = new TBinaryProtocol.Factory();

      public AbstractServerArgs(TServerTransport transport) {
         this.serverTransport = transport;
      }

      public AbstractServerArgs processorFactory(TProcessorFactory factory) {
         this.processorFactory = factory;
         return this;
      }

      public AbstractServerArgs processor(TProcessor processor) {
         this.processorFactory = new TProcessorFactory(processor);
         return this;
      }

      public AbstractServerArgs transportFactory(TTransportFactory factory) {
         this.inputTransportFactory = factory;
         this.outputTransportFactory = factory;
         return this;
      }

      public AbstractServerArgs inputTransportFactory(TTransportFactory factory) {
         this.inputTransportFactory = factory;
         return this;
      }

      public AbstractServerArgs outputTransportFactory(TTransportFactory factory) {
         this.outputTransportFactory = factory;
         return this;
      }

      public AbstractServerArgs protocolFactory(TProtocolFactory factory) {
         this.inputProtocolFactory = factory;
         this.outputProtocolFactory = factory;
         return this;
      }

      public AbstractServerArgs inputProtocolFactory(TProtocolFactory factory) {
         this.inputProtocolFactory = factory;
         return this;
      }

      public AbstractServerArgs outputProtocolFactory(TProtocolFactory factory) {
         this.outputProtocolFactory = factory;
         return this;
      }
   }
}
