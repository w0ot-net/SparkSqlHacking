package org.apache.thrift.transport.sasl;

import java.nio.channels.SelectionKey;
import java.nio.charset.StandardCharsets;
import javax.security.sasl.SaslServer;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.transport.TMemoryTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonblockingSaslHandler {
   private static final Logger LOGGER = LoggerFactory.getLogger(NonblockingSaslHandler.class);
   private static final int INTEREST_NONE = 0;
   private static final int INTEREST_READ = 1;
   private static final int INTEREST_WRITE = 4;
   private Phase currentPhase;
   private Phase nextPhase;
   private SelectionKey selectionKey;
   private TNonblockingTransport underlyingTransport;
   private TSaslServerFactory saslServerFactory;
   private TSaslProcessorFactory processorFactory;
   private TProtocolFactory inputProtocolFactory;
   private TProtocolFactory outputProtocolFactory;
   private TServerEventHandler eventHandler;
   private ServerContext serverContext;
   private boolean serverContextCreated;
   private ServerSaslPeer saslPeer;
   private SaslNegotiationFrameReader saslResponse;
   private SaslNegotiationFrameWriter saslChallenge;
   private DataFrameReader requestReader;
   private DataFrameWriter responseWriter;
   private boolean dataProtected;

   public NonblockingSaslHandler(SelectionKey selectionKey, TNonblockingTransport underlyingTransport, TSaslServerFactory saslServerFactory, TSaslProcessorFactory processorFactory, TProtocolFactory inputProtocolFactory, TProtocolFactory outputProtocolFactory, TServerEventHandler eventHandler) {
      this.currentPhase = NonblockingSaslHandler.Phase.INITIIALIIZING;
      this.nextPhase = this.currentPhase;
      this.serverContextCreated = false;
      this.selectionKey = selectionKey;
      this.underlyingTransport = underlyingTransport;
      this.saslServerFactory = saslServerFactory;
      this.processorFactory = processorFactory;
      this.inputProtocolFactory = inputProtocolFactory;
      this.outputProtocolFactory = outputProtocolFactory;
      this.eventHandler = eventHandler;
      this.saslResponse = new SaslNegotiationFrameReader();
      this.saslChallenge = new SaslNegotiationFrameWriter();
      this.requestReader = new DataFrameReader();
      this.responseWriter = new DataFrameWriter();
   }

   public Phase getCurrentPhase() {
      return this.currentPhase;
   }

   public Phase getNextPhase() {
      return this.nextPhase;
   }

   public TNonblockingTransport getUnderlyingTransport() {
      return this.underlyingTransport;
   }

   public SaslServer getSaslServer() {
      return this.saslPeer.getSaslServer();
   }

   public boolean isCurrentPhaseDone() {
      return this.currentPhase != this.nextPhase;
   }

   public void runCurrentPhase() {
      this.currentPhase.runStateMachine(this);
   }

   public void handleRead() {
      this.handleOps(1);
   }

   public void handleWrite() {
      this.handleOps(4);
   }

   private void handleOps(int interestOps) {
      if (this.currentPhase.selectionInterest != interestOps) {
         throw new IllegalStateException("Current phase " + this.currentPhase + " but got interest " + interestOps);
      } else {
         this.runCurrentPhase();
         if (this.isCurrentPhaseDone() && this.nextPhase.selectionInterest == interestOps) {
            this.stepToNextPhase();
            this.handleOps(interestOps);
         }

      }
   }

   public void stepToNextPhase() {
      if (!this.isCurrentPhaseDone()) {
         throw new IllegalArgumentException("Not yet done with current phase: " + this.currentPhase);
      } else {
         LOGGER.debug("Switch phase {} to {}", this.currentPhase, this.nextPhase);
         switch (this.nextPhase) {
            case INITIIALIIZING:
               throw new IllegalStateException("INITIALIZING cannot be the next phase of " + this.currentPhase);
            default:
               if (this.nextPhase.selectionInterest != this.currentPhase.selectionInterest && this.nextPhase.selectionInterest != this.selectionKey.interestOps()) {
                  this.changeSelectionInterest(this.nextPhase.selectionInterest);
               }

               this.currentPhase = this.nextPhase;
         }
      }
   }

   private void changeSelectionInterest(int selectionInterest) {
      this.selectionKey.interestOps(selectionInterest);
   }

   private void failSaslNegotiation(TSaslNegotiationException e) {
      LOGGER.error("Sasl negotiation failed", e);
      String errorMsg = e.getDetails();
      this.saslChallenge.withHeaderAndPayload(new byte[]{e.getErrorType().code.getValue()}, errorMsg.getBytes(StandardCharsets.UTF_8));
      this.nextPhase = NonblockingSaslHandler.Phase.WRITING_FAILURE_MESSAGE;
   }

   private void fail(Exception e) {
      LOGGER.error("Failed io in " + this.currentPhase, e);
      this.nextPhase = NonblockingSaslHandler.Phase.CLOSING;
   }

   private void failIO(TTransportException e) {
      StringBuilder errorMsg = (new StringBuilder("IO failure ")).append(e.getType()).append(" in ").append(this.currentPhase);
      if (e.getMessage() != null) {
         errorMsg.append(": ").append(e.getMessage());
      }

      LOGGER.error(errorMsg.toString(), e);
      this.nextPhase = NonblockingSaslHandler.Phase.CLOSING;
   }

   private void handleInitializing() {
      try {
         this.saslResponse.read(this.underlyingTransport);
         if (this.saslResponse.isComplete()) {
            SaslNegotiationHeaderReader startHeader = (SaslNegotiationHeaderReader)this.saslResponse.getHeader();
            if (startHeader.getStatus() != NegotiationStatus.START) {
               throw new TInvalidSaslFrameException("Expecting START status but got " + startHeader.getStatus());
            }

            String mechanism = new String(this.saslResponse.getPayload(), StandardCharsets.UTF_8);
            this.saslPeer = this.saslServerFactory.getSaslPeer(mechanism);
            this.saslResponse.clear();
            this.nextPhase = NonblockingSaslHandler.Phase.READING_SASL_RESPONSE;
         }
      } catch (TSaslNegotiationException e) {
         this.failSaslNegotiation(e);
      } catch (TTransportException e) {
         this.failIO(e);
      }

   }

   private void handleReadingSaslResponse() {
      try {
         this.saslResponse.read(this.underlyingTransport);
         if (this.saslResponse.isComplete()) {
            this.nextPhase = NonblockingSaslHandler.Phase.EVALUATING_SASL_RESPONSE;
         }
      } catch (TSaslNegotiationException e) {
         this.failSaslNegotiation(e);
      } catch (TTransportException e) {
         this.failIO(e);
      }

   }

   private void handleReadingRequest() {
      try {
         this.requestReader.read(this.underlyingTransport);
         if (this.requestReader.isComplete()) {
            this.nextPhase = NonblockingSaslHandler.Phase.PROCESSING;
         }
      } catch (TTransportException e) {
         this.failIO(e);
      }

   }

   private void executeEvaluatingSaslResponse() {
      if (((SaslNegotiationHeaderReader)this.saslResponse.getHeader()).getStatus() != NegotiationStatus.OK && ((SaslNegotiationHeaderReader)this.saslResponse.getHeader()).getStatus() != NegotiationStatus.COMPLETE) {
         String error = "Expect status OK or COMPLETE, but got " + ((SaslNegotiationHeaderReader)this.saslResponse.getHeader()).getStatus();
         this.failSaslNegotiation(new TSaslNegotiationException(TSaslNegotiationException.ErrorType.PROTOCOL_ERROR, error));
      } else {
         try {
            byte[] response = this.saslResponse.getPayload();
            this.saslResponse.clear();
            byte[] newChallenge = this.saslPeer.evaluate(response);
            if (this.saslPeer.isAuthenticated()) {
               this.dataProtected = this.saslPeer.isDataProtected();
               this.saslChallenge.withHeaderAndPayload(new byte[]{NegotiationStatus.COMPLETE.getValue()}, newChallenge);
               this.nextPhase = NonblockingSaslHandler.Phase.WRITING_SUCCESS_MESSAGE;
            } else {
               this.saslChallenge.withHeaderAndPayload(new byte[]{NegotiationStatus.OK.getValue()}, newChallenge);
               this.nextPhase = NonblockingSaslHandler.Phase.WRITING_SASL_CHALLENGE;
            }
         } catch (TSaslNegotiationException e) {
            this.failSaslNegotiation(e);
         }

      }
   }

   private void executeProcessing() {
      try {
         byte[] inputPayload = this.requestReader.getPayload();
         this.requestReader.clear();
         byte[] rawInput = this.dataProtected ? this.saslPeer.unwrap(inputPayload) : inputPayload;
         TMemoryTransport memoryTransport = new TMemoryTransport(rawInput);
         TProtocol requestProtocol = this.inputProtocolFactory.getProtocol(memoryTransport);
         TProtocol responseProtocol = this.outputProtocolFactory.getProtocol(memoryTransport);
         if (this.eventHandler != null) {
            if (!this.serverContextCreated) {
               this.serverContext = this.eventHandler.createContext(requestProtocol, responseProtocol);
               this.serverContextCreated = true;
            }

            this.eventHandler.processContext(this.serverContext, memoryTransport, memoryTransport);
         }

         TProcessor processor = this.processorFactory.getProcessor(this);
         processor.process(requestProtocol, responseProtocol);
         TByteArrayOutputStream rawOutput = memoryTransport.getOutput();
         if (rawOutput.len() == 0) {
            this.nextPhase = NonblockingSaslHandler.Phase.READING_REQUEST;
            return;
         }

         if (this.dataProtected) {
            byte[] outputPayload = this.saslPeer.wrap(rawOutput.get(), 0, rawOutput.len());
            this.responseWriter.withOnlyPayload(outputPayload);
         } else {
            this.responseWriter.withOnlyPayload(rawOutput.get(), 0, rawOutput.len());
         }

         this.nextPhase = NonblockingSaslHandler.Phase.WRITING_RESPONSE;
      } catch (TTransportException e) {
         this.failIO(e);
      } catch (Exception e) {
         this.fail(e);
      }

   }

   private void handleWritingSaslChallenge() {
      try {
         this.saslChallenge.write(this.underlyingTransport);
         if (this.saslChallenge.isComplete()) {
            this.saslChallenge.clear();
            this.nextPhase = NonblockingSaslHandler.Phase.READING_SASL_RESPONSE;
         }
      } catch (TTransportException e) {
         this.fail(e);
      }

   }

   private void handleWritingSuccessMessage() {
      try {
         this.saslChallenge.write(this.underlyingTransport);
         if (this.saslChallenge.isComplete()) {
            LOGGER.debug("Authentication is done.");
            this.saslChallenge = null;
            this.saslResponse = null;
            this.nextPhase = NonblockingSaslHandler.Phase.READING_REQUEST;
         }
      } catch (TTransportException e) {
         this.fail(e);
      }

   }

   private void handleWritingFailureMessage() {
      try {
         this.saslChallenge.write(this.underlyingTransport);
         if (this.saslChallenge.isComplete()) {
            this.nextPhase = NonblockingSaslHandler.Phase.CLOSING;
         }
      } catch (TTransportException e) {
         this.fail(e);
      }

   }

   private void handleWritingResponse() {
      try {
         this.responseWriter.write(this.underlyingTransport);
         if (this.responseWriter.isComplete()) {
            this.responseWriter.clear();
            this.nextPhase = NonblockingSaslHandler.Phase.READING_REQUEST;
         }
      } catch (TTransportException e) {
         this.fail(e);
      }

   }

   public void close() {
      this.underlyingTransport.close();
      this.selectionKey.cancel();
      if (this.saslPeer != null) {
         this.saslPeer.dispose();
      }

      if (this.serverContextCreated) {
         this.eventHandler.deleteContext(this.serverContext, this.inputProtocolFactory.getProtocol(this.underlyingTransport), this.outputProtocolFactory.getProtocol(this.underlyingTransport));
      }

      this.nextPhase = NonblockingSaslHandler.Phase.CLOSED;
      this.currentPhase = NonblockingSaslHandler.Phase.CLOSED;
      LOGGER.trace("Connection closed: {}", this.underlyingTransport);
   }

   public static enum Phase {
      INITIIALIIZING(1) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleInitializing();
         }
      },
      READING_SASL_RESPONSE(1) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleReadingSaslResponse();
         }
      },
      EVALUATING_SASL_RESPONSE(0) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.executeEvaluatingSaslResponse();
         }
      },
      WRITING_SASL_CHALLENGE(4) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleWritingSaslChallenge();
         }
      },
      WRITING_SUCCESS_MESSAGE(4) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleWritingSuccessMessage();
         }
      },
      WRITING_FAILURE_MESSAGE(4) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleWritingFailureMessage();
         }
      },
      READING_REQUEST(1) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleReadingRequest();
         }
      },
      PROCESSING(0) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.executeProcessing();
         }
      },
      WRITING_RESPONSE(4) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.handleWritingResponse();
         }
      },
      CLOSING(0) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
            statemachine.close();
         }
      },
      CLOSED(0) {
         void unsafeRun(NonblockingSaslHandler statemachine) {
         }
      };

      private int selectionInterest;

      private Phase(int selectionInterest) {
         this.selectionInterest = selectionInterest;
      }

      void runStateMachine(NonblockingSaslHandler statemachine) {
         if (statemachine.currentPhase != this) {
            throw new IllegalArgumentException("State machine is " + statemachine.currentPhase + " but is expected to be " + this);
         } else if (statemachine.isCurrentPhaseDone()) {
            throw new IllegalStateException("State machine should step into " + statemachine.nextPhase);
         } else {
            this.unsafeRun(statemachine);
         }
      }

      abstract void unsafeRun(NonblockingSaslHandler var1);
   }
}
