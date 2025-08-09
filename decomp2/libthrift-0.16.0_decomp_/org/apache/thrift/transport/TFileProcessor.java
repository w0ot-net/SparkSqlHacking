package org.apache.thrift.transport;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

public class TFileProcessor {
   private TProcessor processor_;
   private TProtocolFactory inputProtocolFactory_;
   private TProtocolFactory outputProtocolFactory_;
   private TFileTransport inputTransport_;
   private TTransport outputTransport_;

   public TFileProcessor(TProcessor processor, TProtocolFactory protocolFactory, TFileTransport inputTransport, TTransport outputTransport) {
      this.processor_ = processor;
      this.inputProtocolFactory_ = this.outputProtocolFactory_ = protocolFactory;
      this.inputTransport_ = inputTransport;
      this.outputTransport_ = outputTransport;
   }

   public TFileProcessor(TProcessor processor, TProtocolFactory inputProtocolFactory, TProtocolFactory outputProtocolFactory, TFileTransport inputTransport, TTransport outputTransport) {
      this.processor_ = processor;
      this.inputProtocolFactory_ = inputProtocolFactory;
      this.outputProtocolFactory_ = outputProtocolFactory;
      this.inputTransport_ = inputTransport;
      this.outputTransport_ = outputTransport;
   }

   private void processUntil(int lastChunk) throws TException {
      TProtocol ip = this.inputProtocolFactory_.getProtocol(this.inputTransport_);
      TProtocol op = this.outputProtocolFactory_.getProtocol(this.outputTransport_);
      int curChunk = this.inputTransport_.getCurChunk();

      try {
         while(lastChunk >= curChunk) {
            this.processor_.process(ip, op);
            int newChunk = this.inputTransport_.getCurChunk();
            curChunk = newChunk;
         }

      } catch (TTransportException e) {
         if (e.getType() != 4) {
            throw e;
         }
      }
   }

   public void processChunk(int startChunkNum, int endChunkNum) throws TException {
      int numChunks = this.inputTransport_.getNumChunks();
      if (endChunkNum < 0) {
         endChunkNum += numChunks;
      }

      if (startChunkNum < 0) {
         startChunkNum += numChunks;
      }

      if (endChunkNum < startChunkNum) {
         throw new TException("endChunkNum " + endChunkNum + " is less than " + startChunkNum);
      } else {
         this.inputTransport_.seekToChunk(startChunkNum);
         this.processUntil(endChunkNum);
      }
   }

   public void processChunk(int chunkNum) throws TException {
      this.processChunk(chunkNum, chunkNum);
   }

   public void processChunk() throws TException {
      this.processChunk(this.inputTransport_.getCurChunk());
   }
}
