package org.apache.thrift.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.TAsyncProcessor;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractNonblockingServer extends TServer {
   protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
   final long MAX_READ_BUFFER_BYTES;
   final AtomicLong readBufferBytesAllocated = new AtomicLong(0L);

   public AbstractNonblockingServer(AbstractNonblockingServerArgs args) {
      super(args);
      this.MAX_READ_BUFFER_BYTES = args.maxReadBufferBytes;
   }

   public void serve() {
      if (this.startThreads()) {
         if (this.startListening()) {
            this.setServing(true);
            this.waitForShutdown();
            this.setServing(false);
            this.stopListening();
         }
      }
   }

   protected abstract boolean startThreads();

   protected abstract void waitForShutdown();

   protected boolean startListening() {
      try {
         this.serverTransport_.listen();
         return true;
      } catch (TTransportException ttx) {
         this.LOGGER.error("Failed to start listening on server socket!", ttx);
         return false;
      }
   }

   protected void stopListening() {
      this.serverTransport_.close();
   }

   protected abstract boolean requestInvoke(FrameBuffer var1);

   public abstract static class AbstractNonblockingServerArgs extends TServer.AbstractServerArgs {
      public long maxReadBufferBytes = 268435456L;

      public AbstractNonblockingServerArgs(TNonblockingServerTransport transport) {
         super(transport);
         this.transportFactory(new TFramedTransport.Factory());
      }
   }

   protected abstract class AbstractSelectThread extends Thread {
      protected Selector selector = SelectorProvider.provider().openSelector();
      protected final Set selectInterestChanges = new HashSet();

      public AbstractSelectThread() throws IOException {
      }

      public void wakeupSelector() {
         this.selector.wakeup();
      }

      public void requestSelectInterestChange(FrameBuffer frameBuffer) {
         synchronized(this.selectInterestChanges) {
            this.selectInterestChanges.add(frameBuffer);
         }

         this.selector.wakeup();
      }

      protected void processInterestChanges() {
         synchronized(this.selectInterestChanges) {
            for(FrameBuffer fb : this.selectInterestChanges) {
               fb.changeSelectInterests();
            }

            this.selectInterestChanges.clear();
         }
      }

      protected void handleRead(SelectionKey key) {
         FrameBuffer buffer = (FrameBuffer)key.attachment();
         if (!buffer.read()) {
            this.cleanupSelectionKey(key);
         } else {
            if (buffer.isFrameFullyRead() && !AbstractNonblockingServer.this.requestInvoke(buffer)) {
               this.cleanupSelectionKey(key);
            }

         }
      }

      protected void handleWrite(SelectionKey key) {
         FrameBuffer buffer = (FrameBuffer)key.attachment();
         if (!buffer.write()) {
            this.cleanupSelectionKey(key);
         }

      }

      protected void cleanupSelectionKey(SelectionKey key) {
         FrameBuffer buffer = (FrameBuffer)key.attachment();
         if (buffer != null) {
            buffer.close();
         }

         key.cancel();
      }
   }

   private static enum FrameBufferState {
      READING_FRAME_SIZE,
      READING_FRAME,
      READ_FRAME_COMPLETE,
      AWAITING_REGISTER_WRITE,
      WRITING,
      AWAITING_REGISTER_READ,
      AWAITING_CLOSE;
   }

   public class FrameBuffer {
      private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
      protected final TNonblockingTransport trans_;
      protected final SelectionKey selectionKey_;
      protected final AbstractSelectThread selectThread_;
      protected FrameBufferState state_;
      protected ByteBuffer buffer_;
      protected final TByteArrayOutputStream response_;
      protected final TMemoryInputTransport frameTrans_;
      protected final TTransport inTrans_;
      protected final TTransport outTrans_;
      protected final TProtocol inProt_;
      protected final TProtocol outProt_;
      protected final ServerContext context_;

      public FrameBuffer(TNonblockingTransport trans, SelectionKey selectionKey, AbstractSelectThread selectThread) throws TTransportException {
         this.state_ = AbstractNonblockingServer.FrameBufferState.READING_FRAME_SIZE;
         this.trans_ = trans;
         this.selectionKey_ = selectionKey;
         this.selectThread_ = selectThread;
         this.buffer_ = ByteBuffer.allocate(4);
         this.frameTrans_ = new TMemoryInputTransport();
         this.response_ = new TByteArrayOutputStream();
         this.inTrans_ = AbstractNonblockingServer.this.inputTransportFactory_.getTransport(this.frameTrans_);
         this.outTrans_ = AbstractNonblockingServer.this.outputTransportFactory_.getTransport(new TIOStreamTransport(this.response_));
         this.inProt_ = AbstractNonblockingServer.this.inputProtocolFactory_.getProtocol(this.inTrans_);
         this.outProt_ = AbstractNonblockingServer.this.outputProtocolFactory_.getProtocol(this.outTrans_);
         if (AbstractNonblockingServer.this.eventHandler_ != null) {
            this.context_ = AbstractNonblockingServer.this.eventHandler_.createContext(this.inProt_, this.outProt_);
         } else {
            this.context_ = null;
         }

      }

      public boolean read() {
         if (this.state_ == AbstractNonblockingServer.FrameBufferState.READING_FRAME_SIZE) {
            if (!this.internalRead()) {
               return false;
            }

            if (this.buffer_.remaining() != 0) {
               return true;
            }

            int frameSize = this.buffer_.getInt(0);
            if (frameSize <= 0) {
               this.LOGGER.error("Read an invalid frame size of " + frameSize + ". Are you using TFramedTransport on the client side?");
               return false;
            }

            if ((long)frameSize > AbstractNonblockingServer.this.MAX_READ_BUFFER_BYTES) {
               this.LOGGER.error("Read a frame size of " + frameSize + ", which is bigger than the maximum allowable buffer size for ALL connections.");
               return false;
            }

            if (AbstractNonblockingServer.this.readBufferBytesAllocated.get() + (long)frameSize > AbstractNonblockingServer.this.MAX_READ_BUFFER_BYTES) {
               return true;
            }

            AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet((long)(frameSize + 4));
            this.buffer_ = ByteBuffer.allocate(frameSize + 4);
            this.buffer_.putInt(frameSize);
            this.state_ = AbstractNonblockingServer.FrameBufferState.READING_FRAME;
         }

         if (this.state_ == AbstractNonblockingServer.FrameBufferState.READING_FRAME) {
            if (!this.internalRead()) {
               return false;
            } else {
               if (this.buffer_.remaining() == 0) {
                  this.selectionKey_.interestOps(0);
                  this.state_ = AbstractNonblockingServer.FrameBufferState.READ_FRAME_COMPLETE;
               }

               return true;
            }
         } else {
            this.LOGGER.error("Read was called but state is invalid (" + this.state_ + ")");
            return false;
         }
      }

      public boolean write() {
         if (this.state_ != AbstractNonblockingServer.FrameBufferState.WRITING) {
            this.LOGGER.error("Write was called, but state is invalid (" + this.state_ + ")");
            return false;
         } else {
            try {
               if (this.trans_.write(this.buffer_) < 0) {
                  return false;
               }
            } catch (TTransportException e) {
               this.LOGGER.warn("Got an Exception during write", e);
               return false;
            }

            if (this.buffer_.remaining() == 0) {
               this.prepareRead();
            }

            return true;
         }
      }

      public void changeSelectInterests() {
         switch (this.state_) {
            case AWAITING_REGISTER_WRITE:
               this.selectionKey_.interestOps(4);
               this.state_ = AbstractNonblockingServer.FrameBufferState.WRITING;
               break;
            case AWAITING_REGISTER_READ:
               this.prepareRead();
               break;
            case AWAITING_CLOSE:
               this.close();
               this.selectionKey_.cancel();
               break;
            default:
               this.LOGGER.error("changeSelectInterest was called, but state is invalid ({})", this.state_);
         }

      }

      public void close() {
         if (this.state_ == AbstractNonblockingServer.FrameBufferState.READING_FRAME || this.state_ == AbstractNonblockingServer.FrameBufferState.READ_FRAME_COMPLETE || this.state_ == AbstractNonblockingServer.FrameBufferState.AWAITING_CLOSE) {
            AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet((long)(-this.buffer_.array().length));
         }

         this.trans_.close();
         if (AbstractNonblockingServer.this.eventHandler_ != null) {
            AbstractNonblockingServer.this.eventHandler_.deleteContext(this.context_, this.inProt_, this.outProt_);
         }

      }

      public boolean isFrameFullyRead() {
         return this.state_ == AbstractNonblockingServer.FrameBufferState.READ_FRAME_COMPLETE;
      }

      public void responseReady() {
         AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet((long)(-this.buffer_.array().length));
         if (this.response_.len() == 0) {
            this.state_ = AbstractNonblockingServer.FrameBufferState.AWAITING_REGISTER_READ;
            this.buffer_ = null;
         } else {
            this.buffer_ = ByteBuffer.wrap(this.response_.get(), 0, this.response_.len());
            this.state_ = AbstractNonblockingServer.FrameBufferState.AWAITING_REGISTER_WRITE;
         }

         this.requestSelectInterestChange();
      }

      public void invoke() {
         this.frameTrans_.reset(this.buffer_.array());
         this.response_.reset();

         try {
            if (AbstractNonblockingServer.this.eventHandler_ != null) {
               AbstractNonblockingServer.this.eventHandler_.processContext(this.context_, this.inTrans_, this.outTrans_);
            }

            AbstractNonblockingServer.this.processorFactory_.getProcessor(this.inTrans_).process(this.inProt_, this.outProt_);
            this.responseReady();
            return;
         } catch (TException te) {
            this.LOGGER.warn("Exception while invoking!", te);
         } catch (Throwable t) {
            this.LOGGER.error("Unexpected throwable while invoking!", t);
         }

         this.state_ = AbstractNonblockingServer.FrameBufferState.AWAITING_CLOSE;
         this.requestSelectInterestChange();
      }

      private boolean internalRead() {
         try {
            return this.trans_.read(this.buffer_) >= 0;
         } catch (TTransportException e) {
            this.LOGGER.warn("Got an Exception in internalRead", e);
            return false;
         }
      }

      private void prepareRead() {
         this.selectionKey_.interestOps(1);
         this.buffer_ = ByteBuffer.allocate(4);
         this.state_ = AbstractNonblockingServer.FrameBufferState.READING_FRAME_SIZE;
      }

      protected void requestSelectInterestChange() {
         if (Thread.currentThread() == this.selectThread_) {
            this.changeSelectInterests();
         } else {
            this.selectThread_.requestSelectInterestChange(this);
         }

      }
   }

   public class AsyncFrameBuffer extends FrameBuffer {
      public AsyncFrameBuffer(TNonblockingTransport trans, SelectionKey selectionKey, AbstractSelectThread selectThread) throws TTransportException {
         super(trans, selectionKey, selectThread);
      }

      public TProtocol getInputProtocol() {
         return this.inProt_;
      }

      public TProtocol getOutputProtocol() {
         return this.outProt_;
      }

      public void invoke() {
         this.frameTrans_.reset(this.buffer_.array());
         this.response_.reset();

         try {
            if (AbstractNonblockingServer.this.eventHandler_ != null) {
               AbstractNonblockingServer.this.eventHandler_.processContext(this.context_, this.inTrans_, this.outTrans_);
            }

            ((TAsyncProcessor)AbstractNonblockingServer.this.processorFactory_.getProcessor(this.inTrans_)).process(this);
            return;
         } catch (TException te) {
            AbstractNonblockingServer.this.LOGGER.warn("Exception while invoking!", te);
         } catch (Throwable t) {
            AbstractNonblockingServer.this.LOGGER.error("Unexpected throwable while invoking!", t);
         }

         this.state_ = AbstractNonblockingServer.FrameBufferState.AWAITING_CLOSE;
         this.requestSelectInterestChange();
      }
   }
}
