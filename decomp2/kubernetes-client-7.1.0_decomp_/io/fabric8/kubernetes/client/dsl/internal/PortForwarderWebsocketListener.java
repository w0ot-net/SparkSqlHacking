package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.client.utils.internal.SerialExecutor;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortForwarderWebsocketListener implements WebSocket.Listener {
   private static final Logger logger = LoggerFactory.getLogger(PortForwarderWebsocketListener.class);
   private static final String LOG_PREFIX = "FWD";
   private static final String PROTOCOL_ERROR = "Protocol error";
   private static final int BUFFER_SIZE = 4096;
   private final ExecutorService pumperService = Executors.newSingleThreadExecutor();
   private final SerialExecutor serialExecutor;
   private final AtomicBoolean alive = new AtomicBoolean(true);
   final Collection clientThrowables = new CopyOnWriteArrayList();
   final Collection serverThrowables = new CopyOnWriteArrayList();
   private final ReadableByteChannel in;
   private final WritableByteChannel out;
   private int messagesRead = 0;

   public PortForwarderWebsocketListener(ReadableByteChannel in, WritableByteChannel out, Executor executor) {
      this.in = in;
      this.out = out;
      this.serialExecutor = new SerialExecutor(executor);
   }

   public void onOpen(WebSocket webSocket) {
      logger.debug("{}: onOpen", "FWD");
      if (this.in != null) {
         this.pumperService.execute(() -> {
            try {
               ReadableByteChannel var10000 = this.in;
               AtomicBoolean var10002 = this.alive;
               Objects.requireNonNull(var10002);
               pipe(var10000, webSocket, var10002::get);
            } catch (InterruptedException | IOException e) {
               if (e instanceof InterruptedException) {
                  Thread.currentThread().interrupt();
               }

               this.clientError(webSocket, "writing client data", e);
            }

         });
      }

   }

   private void clientError(WebSocket webSocket, String operation, Exception e) {
      if (this.alive.get()) {
         logger.debug("Error while " + operation, e);
         this.clientThrowables.add(e);
         this.closeBothWays(webSocket, 1001, "Client error");
      }

   }

   public void onMessage(WebSocket webSocket, String text) {
      logger.debug("{}: onMessage(String)", "FWD");
      this.onMessage(webSocket, ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
   }

   public void onMessage(WebSocket webSocket, ByteBuffer buffer) {
      ++this.messagesRead;
      if (this.messagesRead <= 2) {
         webSocket.request();
      } else if (!buffer.hasRemaining()) {
         KubernetesClientException e = new KubernetesClientException("Received an empty message");
         this.serverThrowables.add(e);
         logger.debug("Protocol error", e);
         this.closeBothWays(webSocket, 1002, "Protocol error");
      } else {
         byte channel = buffer.get();
         if (channel >= 0 && channel <= 1) {
            if (channel == 1) {
               KubernetesClientException e = new KubernetesClientException(String.format("Received an error from the remote socket %s", ExecWebSocketListener.toString(buffer)));
               this.serverThrowables.add(e);
               logger.debug("Server error", e);
               this.closeForwarder();
            } else if (this.out != null) {
               try {
                  this.serialExecutor.execute(() -> {
                     try {
                        while(true) {
                           if (buffer.hasRemaining()) {
                              int written = this.out.write(buffer);
                              if (written == 0) {
                                 Thread.sleep(50L);
                              }
                           } else {
                              webSocket.request();
                              break;
                           }
                        }
                     } catch (InterruptedException | IOException e) {
                        if (e instanceof InterruptedException) {
                           Thread.currentThread().interrupt();
                        }

                        this.clientError(webSocket, "forwarding data to the client", e);
                     }

                  });
               } catch (RejectedExecutionException var5) {
               }
            }
         } else {
            KubernetesClientException e = new KubernetesClientException(String.format("Received a wrong channel from the remote socket: %s", channel));
            this.serverThrowables.add(e);
            logger.debug("Protocol error", e);
            this.closeBothWays(webSocket, 1002, "Protocol error");
         }

      }
   }

   public void onClose(WebSocket webSocket, int code, String reason) {
      logger.debug("{}: onClose. Code={}, Reason={}", new Object[]{"FWD", code, reason});
      if (this.alive.get()) {
         this.closeForwarder();
      }

   }

   public void onError(WebSocket webSocket, Throwable t) {
      logger.debug("{}: Throwable received from websocket", "FWD", t);
      if (this.alive.get()) {
         this.serverThrowables.add(t);
         this.closeForwarder();
      }

   }

   boolean isAlive() {
      return this.alive.get();
   }

   boolean errorOccurred() {
      return !this.clientThrowables.isEmpty() || !this.serverThrowables.isEmpty();
   }

   Collection getClientThrowables() {
      return this.clientThrowables;
   }

   Collection getServerThrowables() {
      return this.serverThrowables;
   }

   void closeBothWays(WebSocket webSocket, int code, String message) {
      logger.debug("{}: Closing with code {} and reason: {}", new Object[]{"FWD", code, message});
      this.alive.set(false);

      try {
         webSocket.sendClose(code, message);
      } catch (Exception e) {
         this.serverThrowables.add(e);
         logger.debug("Error while closing the websocket", e);
      }

      this.closeForwarder();
   }

   private void closeForwarder() {
      this.serialExecutor.execute(() -> {
         this.alive.set(false);
         if (this.in != null) {
            Utils.closeQuietly(new Closeable[]{this.in});
         }

         if (this.out != null && this.out != this.in) {
            Utils.closeQuietly(new Closeable[]{this.out});
         }

         this.pumperService.shutdownNow();
         this.serialExecutor.shutdownNow();
      });
   }

   private static void pipe(ReadableByteChannel in, WebSocket webSocket, BooleanSupplier isAlive) throws IOException, InterruptedException {
      ByteBuffer buffer = ByteBuffer.allocate(4096);

      int read;
      do {
         buffer.clear();
         buffer.put((byte)0);
         read = in.read(buffer);
         if (read > 0) {
            buffer.flip();
            webSocket.send(buffer);
         } else if (read == 0) {
            Thread.sleep(50L);
         }
      } while(isAlive.getAsBoolean() && read >= 0);

   }
}
