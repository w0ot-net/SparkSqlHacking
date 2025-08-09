package org.sparkproject.jetty.io;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Scheduler;

public class ByteArrayEndPoint extends AbstractEndPoint {
   private static final Logger LOG = LoggerFactory.getLogger(ByteArrayEndPoint.class);
   private static final SocketAddress NO_SOCKET_ADDRESS = noSocketAddress();
   private static final int MAX_BUFFER_SIZE = 2147482623;
   private static final ByteBuffer EOF = BufferUtil.allocate(0);
   private final Runnable _runFillable;
   private final AutoLock _lock;
   private final Condition _hasOutput;
   private final Queue _inQ;
   private final int _outputSize;
   private ByteBuffer _out;
   private boolean _growOutput;

   private static SocketAddress noSocketAddress() {
      try {
         return new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0);
      } catch (Throwable x) {
         throw new RuntimeIOException(x);
      }
   }

   public ByteArrayEndPoint() {
      this((Scheduler)null, 0L, (ByteBuffer)null, (ByteBuffer)null);
   }

   public ByteArrayEndPoint(byte[] input, int outputSize) {
      this((Scheduler)null, 0L, input != null ? BufferUtil.toBuffer(input) : null, BufferUtil.allocate(outputSize));
   }

   public ByteArrayEndPoint(String input, int outputSize) {
      this((Scheduler)null, 0L, input != null ? BufferUtil.toBuffer(input) : null, BufferUtil.allocate(outputSize));
   }

   public ByteArrayEndPoint(Scheduler scheduler, long idleTimeoutMs) {
      this(scheduler, idleTimeoutMs, (ByteBuffer)null, (ByteBuffer)null);
   }

   public ByteArrayEndPoint(Scheduler timer, long idleTimeoutMs, byte[] input, int outputSize) {
      this(timer, idleTimeoutMs, input != null ? BufferUtil.toBuffer(input) : null, BufferUtil.allocate(outputSize));
   }

   public ByteArrayEndPoint(Scheduler timer, long idleTimeoutMs, String input, int outputSize) {
      this(timer, idleTimeoutMs, input != null ? BufferUtil.toBuffer(input) : null, BufferUtil.allocate(outputSize));
   }

   public ByteArrayEndPoint(Scheduler timer, long idleTimeoutMs, ByteBuffer input, ByteBuffer output) {
      super(timer);
      this._runFillable = () -> this.getFillInterest().fillable();
      this._lock = new AutoLock();
      this._hasOutput = this._lock.newCondition();
      this._inQ = new ArrayDeque();
      if (BufferUtil.hasContent(input)) {
         this.addInput(input);
      }

      this._outputSize = output == null ? 1024 : output.capacity();
      this._out = output == null ? BufferUtil.allocate(this._outputSize) : output;
      this.setIdleTimeout(idleTimeoutMs);
      this.onOpen();
   }

   public SocketAddress getLocalSocketAddress() {
      return NO_SOCKET_ADDRESS;
   }

   public SocketAddress getRemoteSocketAddress() {
      return NO_SOCKET_ADDRESS;
   }

   public void doShutdownOutput() {
      super.doShutdownOutput();

      try (AutoLock l = this._lock.lock()) {
         this._hasOutput.signalAll();
      }

   }

   public void doClose() {
      super.doClose();

      try (AutoLock l = this._lock.lock()) {
         this._hasOutput.signalAll();
      }

   }

   protected void onIncompleteFlush() {
   }

   protected void execute(Runnable task) {
      (new Thread(task, "BAEPoint-" + Integer.toHexString(this.hashCode()))).start();
   }

   protected void needsFillInterest() throws IOException {
      try (AutoLock lock = this._lock.lock()) {
         if (!this.isOpen()) {
            throw new ClosedChannelException();
         }

         ByteBuffer in = (ByteBuffer)this._inQ.peek();
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} needsFillInterest EOF={} {}", new Object[]{this, in == EOF, BufferUtil.toDetailString(in)});
         }

         if (BufferUtil.hasContent(in) || isEOF(in)) {
            this.execute(this._runFillable);
         }
      }

   }

   public void addInputEOF() {
      this.addInput((ByteBuffer)null);
   }

   public void addInput(ByteBuffer in) {
      boolean fillable = false;

      try (AutoLock lock = this._lock.lock()) {
         if (isEOF((ByteBuffer)this._inQ.peek())) {
            throw new RuntimeIOException(new EOFException());
         }

         boolean wasEmpty = this._inQ.isEmpty();
         if (in == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} addEOFAndRun=true", this);
            }

            this._inQ.add(EOF);
            fillable = true;
         }

         if (BufferUtil.hasContent(in)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} addInputAndRun={} {}", new Object[]{this, wasEmpty, BufferUtil.toDetailString(in)});
            }

            this._inQ.add(in);
            fillable = wasEmpty;
         }
      }

      if (fillable) {
         this._runFillable.run();
      }

   }

   public void addInput(String s) {
      this.addInput(BufferUtil.toBuffer(s, StandardCharsets.UTF_8));
   }

   public void addInput(String s, Charset charset) {
      this.addInput(BufferUtil.toBuffer(s, charset));
   }

   public void addInputAndExecute(ByteBuffer in) {
      boolean fillable = false;

      try (AutoLock lock = this._lock.lock()) {
         if (isEOF((ByteBuffer)this._inQ.peek())) {
            throw new RuntimeIOException(new EOFException());
         }

         boolean wasEmpty = this._inQ.isEmpty();
         if (in == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} addEOFAndExecute=true", this);
            }

            this._inQ.add(EOF);
            fillable = true;
         }

         if (BufferUtil.hasContent(in)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} addInputAndExecute={} {}", new Object[]{this, wasEmpty, BufferUtil.toDetailString(in)});
            }

            this._inQ.add(in);
            fillable = wasEmpty;
         }
      }

      if (fillable) {
         this.execute(this._runFillable);
      }

   }

   public ByteBuffer getOutput() {
      try (AutoLock lock = this._lock.lock()) {
         return this._out;
      }
   }

   public String getOutputString() {
      return this.getOutputString(StandardCharsets.UTF_8);
   }

   public String getOutputString(Charset charset) {
      return BufferUtil.toString(this._out, charset);
   }

   public ByteBuffer takeOutput() {
      ByteBuffer b;
      try (AutoLock lock = this._lock.lock()) {
         b = this._out;
         this._out = BufferUtil.allocate(this._outputSize);
      }

      this.getWriteFlusher().completeWrite();
      return b;
   }

   public ByteBuffer waitForOutput(long time, TimeUnit unit) throws InterruptedException {
      ByteBuffer b;
      try (AutoLock l = this._lock.lock()) {
         while(BufferUtil.isEmpty(this._out) && !this.isOutputShutdown()) {
            if (!this._hasOutput.await(time, unit)) {
               return null;
            }
         }

         b = this._out;
         this._out = BufferUtil.allocate(this._outputSize);
      }

      this.getWriteFlusher().completeWrite();
      return b;
   }

   public String takeOutputString() {
      return this.takeOutputString(StandardCharsets.UTF_8);
   }

   public String takeOutputString(Charset charset) {
      ByteBuffer buffer = this.takeOutput();
      return BufferUtil.toString(buffer, charset);
   }

   public void setOutput(ByteBuffer out) {
      try (AutoLock lock = this._lock.lock()) {
         this._out = out;
      }

      this.getWriteFlusher().completeWrite();
   }

   public boolean hasMore() {
      return this.getOutput().position() > 0;
   }

   public int fill(ByteBuffer buffer) throws IOException {
      int filled = 0;

      try (AutoLock lock = this._lock.lock()) {
         while(true) {
            if (!this.isOpen()) {
               throw new EofException("CLOSED");
            }

            if (this.isInputShutdown()) {
               return -1;
            }

            if (this._inQ.isEmpty()) {
               break;
            }

            ByteBuffer in = (ByteBuffer)this._inQ.peek();
            if (isEOF(in)) {
               filled = -1;
               break;
            } else if (!BufferUtil.hasContent(in)) {
               this._inQ.poll();
            } else {
               filled = BufferUtil.append(buffer, in);
               if (BufferUtil.isEmpty(in)) {
                  this._inQ.poll();
               }
               break;
            }
         }
      }

      if (filled > 0) {
         this.notIdle();
      } else if (filled < 0) {
         this.shutdownInput();
      }

      return filled;
   }

   public boolean flush(ByteBuffer... buffers) throws IOException {
      boolean flushed = true;

      try (AutoLock l = this._lock.lock()) {
         if (!this.isOpen()) {
            throw new IOException("CLOSED");
         }

         if (this.isOutputShutdown()) {
            throw new IOException("OSHUT");
         }

         boolean idle = true;

         for(ByteBuffer b : buffers) {
            if (BufferUtil.hasContent(b)) {
               if (this._growOutput && b.remaining() > BufferUtil.space(this._out)) {
                  BufferUtil.compact(this._out);
                  if (b.remaining() > BufferUtil.space(this._out) && this._out.capacity() < 2147482623) {
                     long newBufferCapacity = Math.min((long)((double)this._out.capacity() + (double)b.remaining() * (double)1.5F), 2147482623L);
                     ByteBuffer n = BufferUtil.allocate(Math.toIntExact(newBufferCapacity));
                     BufferUtil.append(n, this._out);
                     this._out = n;
                  }
               }

               if (BufferUtil.append(this._out, b) > 0) {
                  idle = false;
               }

               if (BufferUtil.hasContent(b)) {
                  flushed = false;
                  break;
               }
            }
         }

         if (!idle) {
            this.notIdle();
            this._hasOutput.signalAll();
         }
      }

      return flushed;
   }

   public void reset() {
      try (AutoLock l = this._lock.lock()) {
         this._inQ.clear();
         this._hasOutput.signalAll();
         BufferUtil.clear(this._out);
      }

      super.reset();
   }

   public Object getTransport() {
      return null;
   }

   public boolean isGrowOutput() {
      return this._growOutput;
   }

   public void setGrowOutput(boolean growOutput) {
      this._growOutput = growOutput;
   }

   public String toString() {
      int q;
      ByteBuffer b;
      String o;
      try (AutoLock lock = this._lock.lock()) {
         q = this._inQ.size();
         b = (ByteBuffer)this._inQ.peek();
         o = BufferUtil.toDetailString(this._out);
      }

      return String.format("%s[q=%d,q[0]=%s,o=%s]", super.toString(), q, b, o);
   }

   private static boolean isEOF(ByteBuffer buffer) {
      boolean isEof = buffer == EOF;
      return isEof;
   }
}
