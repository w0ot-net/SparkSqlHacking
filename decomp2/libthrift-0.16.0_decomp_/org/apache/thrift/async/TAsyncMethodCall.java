package org.apache.thrift.async;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

public abstract class TAsyncMethodCall {
   private static final int INITIAL_MEMORY_BUFFER_SIZE = 128;
   private static AtomicLong sequenceIdCounter = new AtomicLong(0L);
   private State state = null;
   protected final TNonblockingTransport transport;
   private final TProtocolFactory protocolFactory;
   protected final TAsyncClient client;
   private final AsyncMethodCallback callback;
   private final boolean isOneway;
   private long sequenceId;
   private final long timeout;
   private ByteBuffer sizeBuffer;
   private final byte[] sizeBufferArray = new byte[4];
   private ByteBuffer frameBuffer;
   private long startTime = System.currentTimeMillis();

   protected TAsyncMethodCall(TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport, AsyncMethodCallback callback, boolean isOneway) {
      this.transport = transport;
      this.callback = callback;
      this.protocolFactory = protocolFactory;
      this.client = client;
      this.isOneway = isOneway;
      this.sequenceId = sequenceIdCounter.getAndIncrement();
      this.timeout = client.getTimeout();
   }

   protected State getState() {
      return this.state;
   }

   protected boolean isFinished() {
      return this.state == TAsyncMethodCall.State.RESPONSE_READ;
   }

   protected long getStartTime() {
      return this.startTime;
   }

   protected long getSequenceId() {
      return this.sequenceId;
   }

   public TAsyncClient getClient() {
      return this.client;
   }

   public boolean hasTimeout() {
      return this.timeout > 0L;
   }

   public long getTimeoutTimestamp() {
      return this.timeout + this.startTime;
   }

   protected abstract void write_args(TProtocol var1) throws TException;

   protected abstract Object getResult() throws Exception;

   protected void prepareMethodCall() throws TException {
      TMemoryBuffer memoryBuffer = new TMemoryBuffer(128);
      TProtocol protocol = this.protocolFactory.getProtocol(memoryBuffer);
      this.write_args(protocol);
      int length = memoryBuffer.length();
      this.frameBuffer = ByteBuffer.wrap(memoryBuffer.getArray(), 0, length);
      TFramedTransport.encodeFrameSize(length, this.sizeBufferArray);
      this.sizeBuffer = ByteBuffer.wrap(this.sizeBufferArray);
   }

   void start(Selector sel) throws IOException {
      SelectionKey key;
      if (this.transport.isOpen()) {
         this.state = TAsyncMethodCall.State.WRITING_REQUEST_SIZE;
         key = this.transport.registerSelector(sel, 4);
      } else {
         this.state = TAsyncMethodCall.State.CONNECTING;
         key = this.transport.registerSelector(sel, 8);
         if (this.transport.startConnect()) {
            this.registerForFirstWrite(key);
         }
      }

      key.attach(this);
   }

   void registerForFirstWrite(SelectionKey key) throws IOException {
      this.state = TAsyncMethodCall.State.WRITING_REQUEST_SIZE;
      key.interestOps(4);
   }

   protected ByteBuffer getFrameBuffer() {
      return this.frameBuffer;
   }

   void transition(SelectionKey key) {
      if (!key.isValid()) {
         key.cancel();
         Exception e = new TTransportException("Selection key not valid!");
         this.onError(e);
      } else {
         try {
            switch (this.state) {
               case CONNECTING:
                  this.doConnecting(key);
                  break;
               case WRITING_REQUEST_SIZE:
                  this.doWritingRequestSize();
                  break;
               case WRITING_REQUEST_BODY:
                  this.doWritingRequestBody(key);
                  break;
               case READING_RESPONSE_SIZE:
                  this.doReadingResponseSize();
                  break;
               case READING_RESPONSE_BODY:
                  this.doReadingResponseBody(key);
                  break;
               default:
                  throw new IllegalStateException("Method call in state " + this.state + " but selector called transition method. Seems like a bug...");
            }
         } catch (Exception e) {
            key.cancel();
            key.attach((Object)null);
            this.onError(e);
         }

      }
   }

   protected void onError(Exception e) {
      this.client.onError(e);
      this.callback.onError(e);
      this.state = TAsyncMethodCall.State.ERROR;
   }

   private void doReadingResponseBody(SelectionKey key) throws TTransportException {
      if (this.transport.read(this.frameBuffer) < 0) {
         throw new TTransportException(4, "Read call frame failed");
      } else {
         if (this.frameBuffer.remaining() == 0) {
            this.cleanUpAndFireCallback(key);
         }

      }
   }

   private void cleanUpAndFireCallback(SelectionKey key) {
      this.state = TAsyncMethodCall.State.RESPONSE_READ;
      key.interestOps(0);
      key.attach((Object)null);

      try {
         T result = (T)this.getResult();
         this.client.onComplete();
         this.callback.onComplete(result);
      } catch (Exception e) {
         key.cancel();
         this.onError(e);
      }

   }

   private void doReadingResponseSize() throws TTransportException {
      if (this.transport.read(this.sizeBuffer) < 0) {
         throw new TTransportException(4, "Read call frame size failed");
      } else {
         if (this.sizeBuffer.remaining() == 0) {
            this.state = TAsyncMethodCall.State.READING_RESPONSE_BODY;
            this.frameBuffer = ByteBuffer.allocate(TFramedTransport.decodeFrameSize(this.sizeBufferArray));
         }

      }
   }

   private void doWritingRequestBody(SelectionKey key) throws TTransportException {
      if (this.transport.write(this.frameBuffer) < 0) {
         throw new TTransportException(4, "Write call frame failed");
      } else {
         if (this.frameBuffer.remaining() == 0) {
            if (this.isOneway) {
               this.cleanUpAndFireCallback(key);
            } else {
               this.state = TAsyncMethodCall.State.READING_RESPONSE_SIZE;
               this.sizeBuffer.rewind();
               key.interestOps(1);
            }
         }

      }
   }

   private void doWritingRequestSize() throws TTransportException {
      if (this.transport.write(this.sizeBuffer) < 0) {
         throw new TTransportException(4, "Write call frame size failed");
      } else {
         if (this.sizeBuffer.remaining() == 0) {
            this.state = TAsyncMethodCall.State.WRITING_REQUEST_BODY;
         }

      }
   }

   private void doConnecting(SelectionKey key) throws IOException {
      if (key.isConnectable() && this.transport.finishConnect()) {
         this.registerForFirstWrite(key);
      } else {
         throw new IOException("not connectable or finishConnect returned false after we got an OP_CONNECT");
      }
   }

   public static enum State {
      CONNECTING,
      WRITING_REQUEST_SIZE,
      WRITING_REQUEST_BODY,
      READING_RESPONSE_SIZE,
      READING_RESPONSE_BODY,
      RESPONSE_READ,
      ERROR;
   }
}
