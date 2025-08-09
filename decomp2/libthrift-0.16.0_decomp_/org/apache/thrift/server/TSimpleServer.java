package org.apache.thrift.server;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSimpleServer extends TServer {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSimpleServer.class.getName());

   public TSimpleServer(TServer.AbstractServerArgs args) {
      super(args);
   }

   public void serve() {
      try {
         this.serverTransport_.listen();
      } catch (TTransportException ttx) {
         LOGGER.error("Error occurred during listening.", ttx);
         return;
      }

      if (this.eventHandler_ != null) {
         this.eventHandler_.preServe();
      }

      this.setServing(true);

      while(!this.stopped_) {
         TTransport client = null;
         TProcessor processor = null;
         TTransport inputTransport = null;
         TTransport outputTransport = null;
         TProtocol inputProtocol = null;
         TProtocol outputProtocol = null;
         ServerContext connectionContext = null;

         try {
            client = this.serverTransport_.accept();
            if (client != null) {
               processor = this.processorFactory_.getProcessor(client);
               inputTransport = this.inputTransportFactory_.getTransport(client);
               outputTransport = this.outputTransportFactory_.getTransport(client);
               inputProtocol = this.inputProtocolFactory_.getProtocol(inputTransport);
               outputProtocol = this.outputProtocolFactory_.getProtocol(outputTransport);
               if (this.eventHandler_ != null) {
                  connectionContext = this.eventHandler_.createContext(inputProtocol, outputProtocol);
               }

               while(true) {
                  if (this.eventHandler_ != null) {
                     this.eventHandler_.processContext(connectionContext, inputTransport, outputTransport);
                  }

                  processor.process(inputProtocol, outputProtocol);
               }
            }
         } catch (TTransportException ttx) {
            LOGGER.debug("Client Transportation Exception", ttx);
         } catch (TException tx) {
            if (!this.stopped_) {
               LOGGER.error("Thrift error occurred during processing of message.", tx);
            }
         } catch (Exception x) {
            if (!this.stopped_) {
               LOGGER.error("Error occurred during processing of message.", x);
            }
         }

         if (this.eventHandler_ != null) {
            this.eventHandler_.deleteContext(connectionContext, inputProtocol, outputProtocol);
         }

         if (inputTransport != null) {
            inputTransport.close();
         }

         if (outputTransport != null) {
            outputTransport.close();
         }
      }

      this.setServing(false);
   }

   public void stop() {
      this.stopped_ = true;
      this.serverTransport_.interrupt();
   }
}
