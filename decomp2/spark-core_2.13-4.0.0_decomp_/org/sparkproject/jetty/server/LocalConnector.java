package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpParser;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.ByteArrayEndPoint;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.ByteArrayOutputStream2;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.Scheduler;

public class LocalConnector extends AbstractConnector {
   private final BlockingQueue _connects;

   public LocalConnector(Server server, Executor executor, Scheduler scheduler, ByteBufferPool pool, int acceptors, ConnectionFactory... factories) {
      super(server, executor, scheduler, pool, acceptors, factories);
      this._connects = new LinkedBlockingQueue();
      this.setIdleTimeout(30000L);
   }

   public LocalConnector(Server server) {
      this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, new HttpConnectionFactory());
   }

   public LocalConnector(Server server, SslContextFactory.Server sslContextFactory) {
      this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, AbstractConnectionFactory.getFactories(sslContextFactory, new HttpConnectionFactory()));
   }

   public LocalConnector(Server server, ConnectionFactory connectionFactory) {
      this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, connectionFactory);
   }

   public LocalConnector(Server server, ConnectionFactory connectionFactory, SslContextFactory.Server sslContextFactory) {
      this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, AbstractConnectionFactory.getFactories(sslContextFactory, connectionFactory));
   }

   public Object getTransport() {
      return this;
   }

   public LocalEndPoint executeRequest(String rawRequest) {
      return this.executeRequest(BufferUtil.toBuffer(rawRequest, StandardCharsets.UTF_8));
   }

   private LocalEndPoint executeRequest(ByteBuffer rawRequest) {
      if (!this.isStarted()) {
         throw new IllegalStateException("!STARTED");
      } else if (this.isShutdown()) {
         throw new IllegalStateException("Shutdown");
      } else {
         LocalEndPoint endp = new LocalEndPoint();
         endp.addInput(rawRequest);
         this._connects.add(endp);
         return endp;
      }
   }

   public LocalEndPoint connect() {
      LocalEndPoint endp = new LocalEndPoint();
      this._connects.add(endp);
      return endp;
   }

   protected void accept(int acceptorID) throws InterruptedException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("accepting {}", acceptorID);
      }

      LocalEndPoint endPoint = (LocalEndPoint)this._connects.take();
      Connection connection = this.getDefaultConnectionFactory().newConnection(this, endPoint);
      endPoint.setConnection(connection);
      endPoint.onOpen();
      this.onEndPointOpened(endPoint);
      connection.onOpen();
   }

   public ByteBuffer getResponse(ByteBuffer requestsBuffer) throws Exception {
      return this.getResponse(requestsBuffer, false, 10L, TimeUnit.SECONDS);
   }

   public ByteBuffer getResponse(ByteBuffer requestBuffer, long time, TimeUnit unit) throws Exception {
      boolean head = BufferUtil.toString(requestBuffer).toLowerCase().startsWith("head ");
      if (LOG.isDebugEnabled()) {
         LOG.debug("requests {}", BufferUtil.toUTF8String(requestBuffer));
      }

      LocalEndPoint endp = this.executeRequest(requestBuffer);
      return endp.waitForResponse(head, time, unit);
   }

   public ByteBuffer getResponse(ByteBuffer requestBuffer, boolean head, long time, TimeUnit unit) throws Exception {
      if (LOG.isDebugEnabled()) {
         LOG.debug("requests {}", BufferUtil.toUTF8String(requestBuffer));
      }

      LocalEndPoint endp = this.executeRequest(requestBuffer);
      return endp.waitForResponse(head, time, unit);
   }

   public String getResponse(String rawRequest) throws Exception {
      return this.getResponse(rawRequest, false, 30L, TimeUnit.SECONDS);
   }

   public String getResponse(String rawRequest, long time, TimeUnit unit) throws Exception {
      boolean head = rawRequest.toLowerCase().startsWith("head ");
      ByteBuffer requestsBuffer = BufferUtil.toBuffer(rawRequest, StandardCharsets.ISO_8859_1);
      if (LOG.isDebugEnabled()) {
         LOG.debug("request {}", BufferUtil.toUTF8String(requestsBuffer));
      }

      LocalEndPoint endp = this.executeRequest(requestsBuffer);
      return BufferUtil.toString(endp.waitForResponse(head, time, unit), StandardCharsets.ISO_8859_1);
   }

   public String getResponse(String rawRequest, boolean head, long time, TimeUnit unit) throws Exception {
      ByteBuffer requestsBuffer = BufferUtil.toBuffer(rawRequest, StandardCharsets.ISO_8859_1);
      if (LOG.isDebugEnabled()) {
         LOG.debug("request {}", BufferUtil.toUTF8String(requestsBuffer));
      }

      LocalEndPoint endp = this.executeRequest(requestsBuffer);
      return BufferUtil.toString(endp.waitForResponse(head, time, unit), StandardCharsets.ISO_8859_1);
   }

   public class LocalEndPoint extends ByteArrayEndPoint {
      private final CountDownLatch _closed = new CountDownLatch(1);
      private ByteBuffer _responseData;

      public LocalEndPoint() {
         super(LocalConnector.this.getScheduler(), LocalConnector.this.getIdleTimeout());
         this.setGrowOutput(true);
      }

      protected void execute(Runnable task) {
         LocalConnector.this.getExecutor().execute(task);
      }

      public void onClose(Throwable cause) {
         Connection connection = this.getConnection();
         if (connection != null) {
            connection.onClose(cause);
         }

         LocalConnector.this.onEndPointClosed(this);
         super.onClose(cause);
         this._closed.countDown();
      }

      public void doShutdownOutput() {
         super.shutdownOutput();
         this.close();
      }

      public void waitUntilClosed() {
         while(true) {
            if (this.isOpen()) {
               try {
                  if (this._closed.await(10L, TimeUnit.SECONDS)) {
                     continue;
                  }
               } catch (Exception e) {
                  AbstractConnector.LOG.warn("Close wait failed", e);
                  continue;
               }
            }

            return;
         }
      }

      public void waitUntilClosedOrIdleFor(long idleFor, TimeUnit units) {
         Thread.yield();
         int size = this.getOutput().remaining();

         while(this.isOpen()) {
            try {
               if (!this._closed.await(idleFor, units)) {
                  if (size == this.getOutput().remaining()) {
                     if (AbstractConnector.LOG.isDebugEnabled()) {
                        AbstractConnector.LOG.debug("idle for {} {}", idleFor, units);
                     }

                     return;
                  }

                  size = this.getOutput().remaining();
               }
            } catch (Exception e) {
               AbstractConnector.LOG.warn("Close wait failed", e);
            }
         }

      }

      public ByteBuffer getResponseData() {
         return this._responseData;
      }

      public String getResponse() throws Exception {
         return this.getResponse(false, 30L, TimeUnit.SECONDS);
      }

      public String getResponse(boolean head, long time, TimeUnit unit) throws Exception {
         ByteBuffer response = this.waitForResponse(head, time, unit);
         return response != null ? BufferUtil.toString(response) : null;
      }

      public ByteBuffer waitForResponse(boolean head, long time, TimeUnit unit) throws Exception {
         HttpParser.ResponseHandler handler = new HttpParser.ResponseHandler() {
            public void parsedHeader(HttpField field) {
            }

            public boolean contentComplete() {
               return false;
            }

            public boolean messageComplete() {
               return true;
            }

            public boolean headerComplete() {
               return false;
            }

            public void earlyEOF() {
            }

            public boolean content(ByteBuffer item) {
               return false;
            }

            public void startResponse(HttpVersion version, int status, String reason) {
            }
         };
         HttpParser parser = new HttpParser(handler);
         parser.setHeadResponse(head);
         ByteArrayOutputStream2 bout = new ByteArrayOutputStream2();

         Object var11;
         label72: {
            ByteBuffer chunk;
            label71: {
               try {
                  label62:
                  while(true) {
                     if (BufferUtil.hasContent(this._responseData)) {
                        chunk = this._responseData;
                     } else {
                        chunk = this.waitForOutput(time, unit);
                        if (BufferUtil.isEmpty(chunk) && (!this.isOpen() || this.isOutputShutdown() || LocalConnector.this.isShutdown())) {
                           parser.atEOF();
                           parser.parseNext(BufferUtil.EMPTY_BUFFER);
                           break;
                        }
                     }

                     while(BufferUtil.hasContent(chunk)) {
                        int pos = chunk.position();
                        boolean complete = parser.parseNext(chunk);
                        if (chunk.position() == pos) {
                           if (BufferUtil.isEmpty(chunk)) {
                              break;
                           }

                           var11 = null;
                           break label72;
                        }

                        bout.write(chunk.array(), chunk.arrayOffset() + pos, chunk.position() - pos);
                        if (complete) {
                           if (BufferUtil.hasContent(chunk)) {
                              this._responseData = chunk;
                           }
                           break label62;
                        }
                     }
                  }

                  if (bout.getCount() == 0 && this.isOutputShutdown()) {
                     chunk = null;
                     break label71;
                  }

                  chunk = ByteBuffer.wrap(bout.getBuf(), 0, bout.getCount());
               } catch (Throwable var13) {
                  try {
                     bout.close();
                  } catch (Throwable var12) {
                     var13.addSuppressed(var12);
                  }

                  throw var13;
               }

               bout.close();
               return chunk;
            }

            bout.close();
            return chunk;
         }

         bout.close();
         return (ByteBuffer)var11;
      }
   }
}
