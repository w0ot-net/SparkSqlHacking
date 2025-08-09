package io.vertx.core.file.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.AsyncFileLock;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncFileImpl implements AsyncFile {
   private static final Logger log = LoggerFactory.getLogger(AsyncFile.class);
   public static final int DEFAULT_READ_BUFFER_SIZE = 8192;
   private final VertxInternal vertx;
   private final AsynchronousFileChannel ch;
   private final ContextInternal context;
   private boolean closed;
   private Runnable closedDeferred;
   private long writesOutstanding;
   private boolean overflow;
   private Handler exceptionHandler;
   private Handler drainHandler;
   private long writePos;
   private int maxWrites = 131072;
   private int lwm;
   private int readBufferSize;
   private InboundBuffer queue;
   private Handler handler;
   private Handler endHandler;
   private long readPos;
   private long readLength;
   private static CompletionHandler LOCK_COMPLETION = new CompletionHandler() {
      public void completed(FileLock result, PromiseInternal p) {
         p.complete(new AsyncFileLockImpl(p.context().owner(), result));
      }

      public void failed(Throwable t, PromiseInternal p) {
         p.fail(new FileSystemException(t));
      }
   };

   AsyncFileImpl(VertxInternal vertx, String path, OpenOptions options, ContextInternal context) {
      this.lwm = this.maxWrites / 2;
      this.readBufferSize = 8192;
      this.readLength = Long.MAX_VALUE;
      if (!options.isRead() && !options.isWrite()) {
         throw new FileSystemException("Cannot open file for neither reading nor writing");
      } else {
         this.vertx = vertx;
         Path file = Paths.get(path);
         HashSet<OpenOption> opts = new HashSet();
         if (options.isRead()) {
            opts.add(StandardOpenOption.READ);
         }

         if (options.isWrite()) {
            opts.add(StandardOpenOption.WRITE);
         }

         if (options.isCreate()) {
            opts.add(StandardOpenOption.CREATE);
         }

         if (options.isCreateNew()) {
            opts.add(StandardOpenOption.CREATE_NEW);
         }

         if (options.isSync()) {
            opts.add(StandardOpenOption.SYNC);
         }

         if (options.isDsync()) {
            opts.add(StandardOpenOption.DSYNC);
         }

         if (options.isDeleteOnClose()) {
            opts.add(StandardOpenOption.DELETE_ON_CLOSE);
         }

         if (options.isSparse()) {
            opts.add(StandardOpenOption.SPARSE);
         }

         if (options.isTruncateExisting()) {
            opts.add(StandardOpenOption.TRUNCATE_EXISTING);
         }

         try {
            if (options.getPerms() != null) {
               FileAttribute<?> attrs = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(options.getPerms()));
               this.ch = AsynchronousFileChannel.open(file, opts, vertx.getWorkerPool().executor(), attrs);
            } else {
               this.ch = AsynchronousFileChannel.open(file, opts, vertx.getWorkerPool().executor());
            }

            if (options.isAppend()) {
               this.writePos = this.ch.size();
            }
         } catch (IOException e) {
            throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("open", path), e);
         }

         this.context = context;
         this.queue = new InboundBuffer(context, 0L);
         this.queue.handler((buff) -> {
            if (buff.length() > 0) {
               this.handleBuffer(buff);
            } else {
               this.handleEnd();
            }

         });
         this.queue.drainHandler((v) -> this.doRead());
      }
   }

   public Future close() {
      Promise<Void> promise = this.context.promise();
      this.closeInternal(promise);
      return promise.future();
   }

   public void close(Handler handler) {
      this.closeInternal(handler);
   }

   public Future end() {
      Promise<Void> promise = this.context.promise();
      this.close(promise);
      return promise.future();
   }

   public void end(Handler handler) {
      this.close(handler);
   }

   public synchronized AsyncFile read(Buffer buffer, int offset, long position, int length, Handler handler) {
      Objects.requireNonNull(handler, "handler");
      this.read(buffer, offset, position, length).onComplete(handler);
      return this;
   }

   public Future read(Buffer buffer, int offset, long position, int length) {
      Promise<Buffer> promise = this.context.promise();
      Objects.requireNonNull(buffer, "buffer");
      Arguments.require(offset >= 0, "offset must be >= 0");
      Arguments.require(position >= 0L, "position must be >= 0");
      Arguments.require(length >= 0, "length must be >= 0");
      this.check();
      ByteBuffer bb = ByteBuffer.allocate(length);
      this.doRead(buffer, offset, bb, position, promise);
      return promise.future();
   }

   public AsyncFile fetch(long amount) {
      this.queue.fetch(amount);
      return this;
   }

   public void write(Buffer buffer, long position, Handler handler) {
      Objects.requireNonNull(handler, "handler");
      this.doWrite(buffer, position, handler);
   }

   public Future write(Buffer buffer, long position) {
      Promise<Void> promise = this.context.promise();
      this.write(buffer, position, promise);
      return promise.future();
   }

   private synchronized void doWrite(Buffer buffer, long position, Handler handler) {
      Objects.requireNonNull(buffer, "buffer");
      Arguments.require(position >= 0L, "position must be >= 0");
      this.check();
      Handler<AsyncResult<Void>> wrapped = (ar) -> {
         this.checkContext();
         Runnable action;
         synchronized(this) {
            if (this.writesOutstanding == 0L && this.closedDeferred != null) {
               action = this.closedDeferred;
            } else if (this.overflow && this.writesOutstanding <= (long)this.lwm) {
               this.overflow = false;
               Handler<Void> h = this.drainHandler;
               if (h != null) {
                  action = () -> h.handle((Object)null);
               } else {
                  action = null;
               }
            } else {
               action = null;
            }
         }

         if (action != null) {
            action.run();
         }

         if (ar.succeeded()) {
            if (handler != null) {
               handler.handle(ar);
            }
         } else if (handler != null) {
            handler.handle(ar);
         } else {
            this.handleException(ar.cause());
         }

      };
      ByteBuf buf = buffer.getByteBuf();
      if (buf.nioBufferCount() > 1) {
         this.doWrite(buf.nioBuffers(), position, wrapped);
      } else {
         ByteBuffer bb = buf.nioBuffer();
         this.doWrite(bb, position, (long)bb.limit(), wrapped);
      }

   }

   public Future write(Buffer buffer) {
      Promise<Void> promise = this.context.promise();
      this.write((Buffer)buffer, promise);
      return promise.future();
   }

   public synchronized void write(Buffer buffer, Handler handler) {
      int length = buffer.length();
      this.doWrite(buffer, this.writePos, handler);
      this.writePos += (long)length;
   }

   public synchronized AsyncFile setWriteQueueMaxSize(int maxSize) {
      Arguments.require(maxSize >= 2, "maxSize must be >= 2");
      this.check();
      this.maxWrites = maxSize;
      this.lwm = this.maxWrites / 2;
      return this;
   }

   public synchronized AsyncFile setReadBufferSize(int readBufferSize) {
      this.readBufferSize = readBufferSize;
      return this;
   }

   public synchronized boolean writeQueueFull() {
      this.check();
      return this.overflow;
   }

   public synchronized AsyncFile drainHandler(Handler handler) {
      this.check();
      this.drainHandler = handler;
      return this;
   }

   public synchronized AsyncFile exceptionHandler(Handler handler) {
      this.check();
      this.exceptionHandler = handler;
      return this;
   }

   public synchronized AsyncFile handler(Handler handler) {
      this.check();
      if (this.closed) {
         return this;
      } else {
         this.handler = handler;
         if (handler != null) {
            this.doRead();
         } else {
            this.queue.clear();
         }

         return this;
      }
   }

   public synchronized AsyncFile endHandler(Handler handler) {
      this.check();
      this.endHandler = handler;
      return this;
   }

   public synchronized AsyncFile pause() {
      this.check();
      this.queue.pause();
      return this;
   }

   public synchronized AsyncFile resume() {
      this.check();
      if (!this.closed) {
         this.queue.resume();
      }

      return this;
   }

   public Future flush() {
      Promise<Void> promise = this.context.promise();
      this.doFlush(promise);
      return promise.future();
   }

   public AsyncFile flush(Handler handler) {
      this.doFlush(handler);
      return this;
   }

   public synchronized AsyncFile setReadPos(long readPos) {
      this.readPos = readPos;
      return this;
   }

   public synchronized AsyncFile setReadLength(long readLength) {
      this.readLength = readLength;
      return this;
   }

   public synchronized long getReadLength() {
      return this.readLength;
   }

   public synchronized AsyncFile setWritePos(long writePos) {
      this.writePos = writePos;
      return this;
   }

   public synchronized long getWritePos() {
      return this.writePos;
   }

   private void handleException(Throwable t) {
      if (this.exceptionHandler != null && t instanceof Exception) {
         this.exceptionHandler.handle(t);
      } else {
         this.context.reportException(t);
      }

   }

   private synchronized void doWrite(ByteBuffer[] buffers, long position, Handler handler) {
      AtomicInteger cnt = new AtomicInteger();
      AtomicBoolean sentFailure = new AtomicBoolean();

      for(ByteBuffer b : buffers) {
         int limit = b.limit();
         this.doWrite(b, position, (long)limit, (ar) -> {
            if (ar.succeeded()) {
               if (cnt.incrementAndGet() == buffers.length) {
                  handler.handle(ar);
               }
            } else if (sentFailure.compareAndSet(false, true)) {
               handler.handle(ar);
            }

         });
         position += (long)limit;
      }

   }

   private void doRead() {
      this.doRead(ByteBuffer.allocate(this.readBufferSize));
   }

   private synchronized void doRead(ByteBuffer bb) {
      if (this.handler != null) {
         Buffer buff = Buffer.buffer(this.readBufferSize);
         int readSize = (int)Math.min((long)this.readBufferSize, this.readLength);
         bb.limit(readSize);
         Promise<Buffer> promise = this.context.promise();
         promise.future().onComplete((ar) -> {
            if (ar.succeeded()) {
               Buffer buffer = (Buffer)ar.result();
               this.readPos += (long)buffer.length();
               this.readLength -= (long)buffer.length();
               if (this.queue.write((Object)buffer) && buffer.length() > 0) {
                  this.doRead(bb);
               }
            } else {
               this.handleException(ar.cause());
            }

         });
         this.doRead(buff, 0, bb, this.readPos, promise);
      }
   }

   private void handleBuffer(Buffer buff) {
      Handler<Buffer> handler;
      synchronized(this) {
         handler = this.handler;
      }

      if (handler != null) {
         this.checkContext();
         handler.handle(buff);
      }

   }

   private void handleEnd() {
      Handler<Void> endHandler;
      synchronized(this) {
         this.handler = null;
         endHandler = this.endHandler;
      }

      if (endHandler != null) {
         this.checkContext();
         endHandler.handle((Object)null);
      }

   }

   private synchronized void doFlush(Handler handler) {
      this.checkClosed();
      this.context.executeBlockingInternal((fut) -> {
         try {
            this.ch.force(false);
            fut.complete();
         } catch (IOException e) {
            throw new FileSystemException(e);
         }
      }, handler);
   }

   private void doWrite(ByteBuffer buff, long position, long toWrite, Handler handler) {
      if (toWrite > 0L) {
         synchronized(this) {
            this.writesOutstanding += toWrite;
            this.overflow |= this.writesOutstanding >= (long)this.maxWrites;
         }

         this.writeInternal(buff, position, handler);
      } else {
         handler.handle(Future.succeededFuture());
      }

   }

   private void writeInternal(final ByteBuffer buff, final long position, final Handler handler) {
      this.ch.write(buff, position, (Object)null, new CompletionHandler() {
         public void completed(Integer bytesWritten, Object attachment) {
            long pos = position;
            if (buff.hasRemaining()) {
               pos += (long)bytesWritten;
               AsyncFileImpl.this.writeInternal(buff, pos, handler);
            } else {
               AsyncFileImpl.this.context.runOnContext((v) -> {
                  synchronized(AsyncFileImpl.this) {
                     AsyncFileImpl.this.writesOutstanding = AsyncFileImpl.this.writesOutstanding - (long)buff.limit();
                  }

                  handler.handle(Future.succeededFuture());
               });
            }

         }

         public void failed(Throwable exc, Object attachment) {
            if (exc instanceof Exception) {
               AsyncFileImpl.this.context.runOnContext((v) -> {
                  synchronized(AsyncFileImpl.this) {
                     AsyncFileImpl.this.writesOutstanding = AsyncFileImpl.this.writesOutstanding - (long)buff.limit();
                  }

                  handler.handle(Future.failedFuture(exc));
               });
            } else {
               AsyncFileImpl.log.error("Error occurred", exc);
            }

         }
      });
   }

   private void doRead(final Buffer writeBuff, final int offset, final ByteBuffer buff, final long position, final Promise promise) {
      this.ch.read(buff, position, (Object)null, new CompletionHandler() {
         long pos = position;

         private void done() {
            buff.flip();
            writeBuff.setBytes(offset, buff);
            buff.compact();
            promise.complete(writeBuff);
         }

         public void completed(Integer bytesRead, Object attachment) {
            if (bytesRead == -1) {
               this.done();
            } else if (buff.hasRemaining()) {
               this.pos += (long)bytesRead;
               AsyncFileImpl.this.doRead(writeBuff, offset, buff, this.pos, promise);
            } else {
               this.done();
            }

         }

         public void failed(Throwable t, Object attachment) {
            promise.fail(t);
         }
      });
   }

   private void check() {
      this.checkClosed();
   }

   private void checkClosed() {
      if (this.closed) {
         throw new IllegalStateException("File handle is closed");
      }
   }

   private void checkContext() {
      if (!this.vertx.getContext().equals(this.context)) {
         throw new IllegalStateException("AsyncFile must only be used in the context that created it, expected: " + this.context + " actual " + this.vertx.getContext());
      }
   }

   private void doClose(Handler handler) {
      this.context.executeBlockingInternal((res) -> {
         try {
            this.ch.close();
            res.complete((Object)null);
         } catch (IOException e) {
            res.fail((Throwable)e);
         }

      }, handler);
   }

   private synchronized void closeInternal(Handler handler) {
      this.check();
      this.closed = true;
      if (this.writesOutstanding == 0L) {
         this.doClose(handler);
      } else {
         this.closedDeferred = () -> this.doClose(handler);
      }

   }

   public long sizeBlocking() {
      try {
         return this.ch.size();
      } catch (IOException e) {
         throw new FileSystemException(e);
      }
   }

   public Future size() {
      return this.vertx.getOrCreateContext().executeBlockingInternal((Handler)((prom) -> prom.complete(this.sizeBlocking())));
   }

   public AsyncFileLock tryLock() {
      try {
         return new AsyncFileLockImpl(this.vertx, this.ch.tryLock());
      } catch (IOException e) {
         throw new FileSystemException(e);
      }
   }

   public AsyncFileLock tryLock(long position, long size, boolean shared) {
      try {
         return new AsyncFileLockImpl(this.vertx, this.ch.tryLock(position, size, shared));
      } catch (IOException e) {
         throw new FileSystemException(e);
      }
   }

   public Future lock() {
      return this.lock(0L, Long.MAX_VALUE, false);
   }

   public void lock(Handler handler) {
      Future<AsyncFileLock> future = this.lock();
      if (handler != null) {
         future.onComplete(handler);
      }

   }

   public Future lock(long position, long size, boolean shared) {
      PromiseInternal<AsyncFileLock> promise = this.vertx.promise();
      this.vertx.executeBlockingInternal((prom) -> this.ch.lock(position, size, shared, promise, LOCK_COMPLETION), (ar) -> {
         if (ar.failed()) {
            promise.fail(new FileSystemException(ar.cause()));
         }

      });
      return promise.future();
   }

   public void lock(long position, long size, boolean shared, Handler handler) {
      Future<AsyncFileLock> future = this.lock(position, size, shared);
      if (handler != null) {
         future.onComplete(handler);
      }

   }
}
