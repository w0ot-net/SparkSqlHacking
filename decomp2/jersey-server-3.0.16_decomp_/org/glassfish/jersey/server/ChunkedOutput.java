package org.glassfish.jersey.server;

import jakarta.inject.Provider;
import jakarta.ws.rs.container.ConnectionCallback;
import jakarta.ws.rs.core.GenericType;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import org.glassfish.jersey.process.internal.RequestContext;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.process.MappableException;

public class ChunkedOutput extends GenericType implements Closeable {
   private static final byte[] ZERO_LENGTH_DELIMITER = new byte[0];
   private final BlockingDeque queue;
   private final byte[] chunkDelimiter;
   private final AtomicBoolean resumed;
   private final Object lock;
   private boolean flushing;
   private boolean touchingEntityStream;
   private volatile boolean closed;
   private volatile AsyncContext asyncContext;
   private volatile RequestScope requestScope;
   private volatile RequestContext requestScopeContext;
   private volatile ContainerRequest requestContext;
   private volatile ContainerResponse responseContext;
   private volatile ConnectionCallback connectionCallback;

   protected ChunkedOutput() {
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      this.queue = new LinkedBlockingDeque();
   }

   protected ChunkedOutput(Builder builder) {
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (builder.queueCapacity > 0) {
         this.queue = new LinkedBlockingDeque(builder.queueCapacity);
      } else {
         this.queue = new LinkedBlockingDeque();
      }

      if (builder.chunkDelimiter != null) {
         this.chunkDelimiter = new byte[builder.chunkDelimiter.length];
         System.arraycopy(builder.chunkDelimiter, 0, this.chunkDelimiter, 0, builder.chunkDelimiter.length);
      } else {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      }

      if (builder.asyncContextProvider != null) {
         this.asyncContext = (AsyncContext)builder.asyncContextProvider.get();
      }

   }

   private ChunkedOutput(TypedBuilder builder) {
      super(builder.chunkType);
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (builder.queueCapacity > 0) {
         this.queue = new LinkedBlockingDeque(builder.queueCapacity);
      } else {
         this.queue = new LinkedBlockingDeque();
      }

      if (builder.chunkDelimiter != null) {
         this.chunkDelimiter = new byte[builder.chunkDelimiter.length];
         System.arraycopy(builder.chunkDelimiter, 0, this.chunkDelimiter, 0, builder.chunkDelimiter.length);
      } else {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      }

      if (builder.asyncContextProvider != null) {
         this.asyncContext = (AsyncContext)builder.asyncContextProvider.get();
      }

   }

   public ChunkedOutput(Type chunkType) {
      super(chunkType);
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      this.queue = new LinkedBlockingDeque();
   }

   protected ChunkedOutput(byte[] chunkDelimiter) {
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (chunkDelimiter.length > 0) {
         this.chunkDelimiter = new byte[chunkDelimiter.length];
         System.arraycopy(chunkDelimiter, 0, this.chunkDelimiter, 0, chunkDelimiter.length);
      } else {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      }

      this.queue = new LinkedBlockingDeque();
   }

   protected ChunkedOutput(byte[] chunkDelimiter, Provider asyncContextProvider) {
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (chunkDelimiter.length > 0) {
         this.chunkDelimiter = new byte[chunkDelimiter.length];
         System.arraycopy(chunkDelimiter, 0, this.chunkDelimiter, 0, chunkDelimiter.length);
      } else {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      }

      this.asyncContext = asyncContextProvider == null ? null : (AsyncContext)asyncContextProvider.get();
      this.queue = new LinkedBlockingDeque();
   }

   public ChunkedOutput(Type chunkType, byte[] chunkDelimiter) {
      super(chunkType);
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (chunkDelimiter.length > 0) {
         this.chunkDelimiter = new byte[chunkDelimiter.length];
         System.arraycopy(chunkDelimiter, 0, this.chunkDelimiter, 0, chunkDelimiter.length);
      } else {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      }

      this.queue = new LinkedBlockingDeque();
   }

   protected ChunkedOutput(String chunkDelimiter) {
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (chunkDelimiter.isEmpty()) {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      } else {
         this.chunkDelimiter = chunkDelimiter.getBytes();
      }

      this.queue = new LinkedBlockingDeque();
   }

   public ChunkedOutput(Type chunkType, String chunkDelimiter) {
      super(chunkType);
      this.resumed = new AtomicBoolean(false);
      this.lock = new Object();
      this.flushing = false;
      this.touchingEntityStream = false;
      this.closed = false;
      if (chunkDelimiter.isEmpty()) {
         this.chunkDelimiter = ZERO_LENGTH_DELIMITER;
      } else {
         this.chunkDelimiter = chunkDelimiter.getBytes();
      }

      this.queue = new LinkedBlockingDeque();
   }

   public static Builder builder() {
      return new Builder();
   }

   public static TypedBuilder builder(Type chunkType) {
      return new TypedBuilder(chunkType);
   }

   public void write(Object chunk) throws IOException {
      if (this.closed) {
         throw new IOException(LocalizationMessages.CHUNKED_OUTPUT_CLOSED());
      } else {
         if (chunk != null) {
            try {
               this.queue.put(chunk);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               throw new IOException(e);
            }
         }

         this.flushQueue();
      }
   }

   protected void flushQueue() throws IOException {
      if (this.resumed.compareAndSet(false, true) && this.asyncContext != null) {
         this.asyncContext.resume(this);
      }

      if (this.requestScopeContext != null && this.requestContext != null && this.responseContext != null) {
         Exception ex = null;

         try {
            this.requestScope.runInScope(this.requestScopeContext, new Callable() {
               public Void call() throws IOException {
                  T t;
                  synchronized(ChunkedOutput.this.lock) {
                     if (ChunkedOutput.this.flushing) {
                        return null;
                     }

                     boolean shouldClose = ChunkedOutput.this.closed;
                     t = (T)ChunkedOutput.this.queue.poll();
                     if (t != null || shouldClose) {
                        ChunkedOutput.this.flushing = true;
                     }
                  }

                  while(t != null) {
                     try {
                        synchronized(ChunkedOutput.this.lock) {
                           ChunkedOutput.this.touchingEntityStream = true;
                        }

                        OutputStream origStream = ChunkedOutput.this.responseContext.getEntityStream();
                        OutputStream writtenStream = ChunkedOutput.this.requestContext.getWorkers().writeTo(t, t.getClass(), ChunkedOutput.this.getType(), ChunkedOutput.this.responseContext.getEntityAnnotations(), ChunkedOutput.this.responseContext.getMediaType(), ChunkedOutput.this.responseContext.getHeaders(), ChunkedOutput.this.requestContext.getPropertiesDelegate(), origStream, Collections.emptyList());
                        if (ChunkedOutput.this.chunkDelimiter != ChunkedOutput.ZERO_LENGTH_DELIMITER) {
                           writtenStream.write(ChunkedOutput.this.chunkDelimiter);
                        }

                        writtenStream.flush();
                        if (origStream != writtenStream) {
                           ChunkedOutput.this.responseContext.setEntityStream(writtenStream);
                        }
                     } catch (UncheckedIOException | IOException ioe) {
                        ChunkedOutput.this.connectionCallback.onDisconnect(ChunkedOutput.this.asyncContext);
                        throw ioe;
                     } catch (MappableException var23) {
                        if (var23.getCause() instanceof IOException || var23.getCause() instanceof UncheckedIOException) {
                           ChunkedOutput.this.connectionCallback.onDisconnect(ChunkedOutput.this.asyncContext);
                        }

                        throw var23;
                     } finally {
                        synchronized(ChunkedOutput.this.lock) {
                           ChunkedOutput.this.touchingEntityStream = false;
                        }
                     }

                     t = (T)ChunkedOutput.this.queue.poll();
                     if (t == null) {
                        synchronized(ChunkedOutput.this.lock) {
                           boolean shouldClosex = ChunkedOutput.this.closed;
                           t = (T)ChunkedOutput.this.queue.poll();
                           if (t == null) {
                              ChunkedOutput.this.responseContext.commitStream();
                              ChunkedOutput.this.flushing = shouldClosex;
                              break;
                           }
                        }
                     }
                  }

                  return null;
               }
            });
         } catch (Exception e) {
            this.closed = true;
            ex = e;
            this.onClose(e);
         } finally {
            if (this.closed) {
               try {
                  synchronized(this.lock) {
                     if (!this.touchingEntityStream) {
                        this.responseContext.close();
                     }
                  }
               } catch (Exception e) {
                  ex = ex == null ? e : ex;
               }

               this.requestScopeContext.release();
               if (ex instanceof IOException) {
                  throw (IOException)ex;
               }

               if (ex instanceof RuntimeException) {
                  throw (RuntimeException)ex;
               }
            }

         }

      }
   }

   public void close() throws IOException {
      this.closed = true;
      this.flushQueue();
   }

   public boolean isClosed() {
      return this.closed;
   }

   protected void onClose(Exception e) {
      this.queue.clear();
   }

   public boolean equals(Object obj) {
      return this == obj;
   }

   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + this.queue.hashCode();
      return result;
   }

   public String toString() {
      return "ChunkedOutput<" + this.getType() + ">";
   }

   void setContext(RequestScope requestScope, RequestContext requestScopeContext, ContainerRequest requestContext, ContainerResponse responseContext, ConnectionCallback connectionCallbackRunner) throws IOException {
      this.requestScope = requestScope;
      this.requestScopeContext = requestScopeContext;
      this.requestContext = requestContext;
      this.responseContext = responseContext;
      this.connectionCallback = connectionCallbackRunner;
      this.flushQueue();
   }

   public static class Builder {
      byte[] chunkDelimiter;
      int queueCapacity;
      Provider asyncContextProvider;

      private Builder() {
         this.queueCapacity = -1;
      }

      public Builder chunkDelimiter(byte[] chunkDelimiter) {
         this.chunkDelimiter = chunkDelimiter;
         return this;
      }

      public Builder queueCapacity(int queueCapacity) {
         this.queueCapacity = queueCapacity;
         return this;
      }

      public Builder asyncContextProvider(Provider asyncContextProvider) {
         this.asyncContextProvider = asyncContextProvider;
         return this;
      }

      public ChunkedOutput build() {
         return new ChunkedOutput(this);
      }
   }

   public static class TypedBuilder extends Builder {
      private Type chunkType;

      private TypedBuilder(Type chunkType) {
         this.chunkType = chunkType;
      }

      public ChunkedOutput build() {
         return new ChunkedOutput(this);
      }
   }
}
