package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.StatusCause;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.http.WebSocketHandshakeException;
import io.fabric8.kubernetes.client.utils.InputStreamPumper;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.internal.SerialExecutor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecWebSocketListener implements ExecWatch, AutoCloseable, WebSocket.Listener {
   static final String CAUSE_REASON_EXIT_CODE = "ExitCode";
   static final String REASON_NON_ZERO_EXIT_CODE = "NonZeroExitCode";
   static final String STATUS_SUCCESS = "Success";
   private static final long MAX_QUEUE_SIZE = 16777216L;
   static final Logger LOGGER = LoggerFactory.getLogger(ExecWebSocketListener.class);
   private static final String HEIGHT = "Height";
   private static final String WIDTH = "Width";
   private final InputStream in;
   private final OutputStream input;
   private final ListenerStream out;
   private final ListenerStream error;
   private final ListenerStream errorChannel;
   private final boolean terminateOnError;
   private final ExecListener listener;
   private final AtomicReference webSocketRef = new AtomicReference();
   private final ExecutorService executorService = Executors.newSingleThreadExecutor();
   private final SerialExecutor serialExecutor;
   private final AtomicBoolean closed = new AtomicBoolean(false);
   private final CompletableFuture exitCode = new CompletableFuture();
   private KubernetesSerialization serialization;

   public static String toString(ByteBuffer buffer) {
      return StandardCharsets.UTF_8.decode(buffer).toString();
   }

   public ExecWebSocketListener(PodOperationContext context, Executor executor, KubernetesSerialization serialization) {
      this.serialization = serialization;
      this.listener = context.getExecListener();
      Integer bufferSize = context.getBufferSize();
      if (context.isRedirectingIn()) {
         this.input = InputStreamPumper.writableOutputStream(this::sendWithErrorChecking, bufferSize);
         this.in = null;
      } else {
         this.input = null;
         this.in = context.getIn();
      }

      this.terminateOnError = context.isTerminateOnError();
      this.out = this.createStream("stdOut", context.getOutput());
      this.error = this.createStream("stdErr", context.getError());
      this.errorChannel = this.createStream("errorChannel", context.getErrorChannel());
      this.serialExecutor = new SerialExecutor(executor);
   }

   private ListenerStream createStream(String name, PodOperationContext.StreamContext streamContext) {
      ListenerStream stream = new ListenerStream(name);
      if (streamContext == null) {
         return stream;
      } else {
         OutputStream os = streamContext.getOutputStream();
         if (os == null) {
            stream.inputStream = new ExecWatchInputStream(() -> ((WebSocket)this.webSocketRef.get()).request());
            CompletableFuture var10000 = this.exitCode;
            ExecWatchInputStream var10001 = stream.inputStream;
            Objects.requireNonNull(var10001);
            var10000.whenComplete(var10001::onExit);
            stream.handler = (b) -> stream.inputStream.consume(Arrays.asList(b));
         } else {
            WritableByteChannel channel = Channels.newChannel(os);
            stream.handler = (b) -> this.asyncWrite(channel, b);
         }

         return stream;
      }
   }

   private void asyncWrite(WritableByteChannel channel, ByteBuffer b) {
      CompletableFuture.runAsync(() -> {
         try {
            channel.write(b);
         } catch (IOException e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }, this.serialExecutor).whenComplete((v, t) -> {
         ((WebSocket)this.webSocketRef.get()).request();
         if (t != null) {
            if (this.closed.get()) {
               LOGGER.debug("Stream write failed after close", t);
            } else {
               LOGGER.warn("Stream write failed", t);
            }
         }

      });
   }

   public void close() {
      if (!this.closed.get()) {
         this.closeWebSocketOnce(1000, "Closing...");
      }
   }

   private void cleanUpOnce() {
      this.executorService.shutdownNow();
      this.serialExecutor.shutdownNow();
   }

   private void closeWebSocketOnce(int code, String reason) {
      try {
         WebSocket ws = (WebSocket)this.webSocketRef.get();
         if (ws != null) {
            ws.sendClose(code, reason);
         }
      } catch (Throwable t) {
         LOGGER.debug("Error closing WebSocket.", t);
      }

   }

   public void onOpen(WebSocket webSocket) {
      try {
         this.exitCode.whenComplete((i, t) -> webSocket.request());
         this.webSocketRef.set(webSocket);
         if (this.in != null && !this.executorService.isShutdown()) {
            InputStreamPumper.pump(InputStreamPumper.asInterruptible(this.in), this::send, this.executorService);
         }
      } finally {
         if (this.listener != null) {
            this.listener.onOpen();
         }

      }

   }

   public void onError(WebSocket webSocket, Throwable t) {
      this.closed.set(true);
      HttpResponse<?> response = null;

      try {
         if (t instanceof WebSocketHandshakeException) {
            response = ((WebSocketHandshakeException)t).getResponse();
            if (response != null) {
               Status status = OperationSupport.createStatus(response, this.serialization);
               status.setMessage(t.getMessage());
               t = (new KubernetesClientException(status)).initCause(t);
            }
         }

         this.cleanUpOnce();
      } finally {
         if (this.exitCode.isDone()) {
            LOGGER.debug("Exec failure after done", t);
         } else {
            try {
               if (this.listener != null) {
                  ExecListener.Response execResponse = null;
                  if (response != null) {
                     execResponse = new SimpleResponse(response);
                  }

                  this.listener.onFailure(t, execResponse);
               } else {
                  LOGGER.error("Exec Failure", t);
               }
            } finally {
               this.exitCode.completeExceptionally(t);
            }
         }

      }

   }

   public void onMessage(WebSocket webSocket, String text) {
      LOGGER.debug("Exec Web Socket: onMessage(String)");
      this.onMessage(webSocket, ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
   }

   public void onMessage(WebSocket webSocket, ByteBuffer bytes) {
      boolean close = false;

      try {
         byte streamID = bytes.get(0);
         bytes.position(1);
         ByteBuffer byteString = bytes.slice();
         if (byteString.remaining() != 0) {
            switch (streamID) {
               case 1:
                  this.out.handle(byteString, webSocket);
                  return;
               case 2:
                  if (this.terminateOnError) {
                     String stringValue = toString(bytes);
                     this.exitCode.completeExceptionally(new KubernetesClientException(stringValue));
                     close = true;
                  } else {
                     this.error.handle(byteString, webSocket);
                  }

                  return;
               case 3:
                  close = true;

                  try {
                     this.errorChannel.handle(bytes, webSocket);
                     return;
                  } finally {
                     this.handleExitStatus(byteString);
                  }
               default:
                  throw new IOException("Unknown stream ID " + streamID);
            }
         }

         webSocket.request();
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      } finally {
         if (close) {
            this.close();
         }

      }

   }

   private void handleExitStatus(ByteBuffer bytes) {
      Status status = null;
      int code = -1;

      try {
         String stringValue = toString(bytes);
         status = (Status)this.serialization.unmarshal(stringValue, Status.class);
         if (status != null) {
            code = parseExitCode(status);
         }
      } catch (Exception e) {
         LOGGER.warn("Could not determine exit code", e);
      }

      try {
         if (this.listener != null) {
            this.listener.onExit(code, status);
         }
      } finally {
         this.exitCode.complete(code);
      }

   }

   public void onClose(WebSocket webSocket, int code, String reason) {
      if (this.closed.compareAndSet(false, true)) {
         this.closeWebSocketOnce(code, reason);
         LOGGER.debug("Exec Web Socket: On Close with code:[{}], due to: [{}]", code, reason);

         try {
            this.serialExecutor.execute(() -> {
               try {
                  if (this.exitCode.complete((Object)null)) {
                     LOGGER.debug("Exec Web Socket: completed with a null exit code - no status was received prior to onClose");
                  }

                  this.cleanUpOnce();
               } finally {
                  if (this.listener != null) {
                     this.listener.onClose(code, reason);
                  }

               }

            });
         } catch (RejectedExecutionException e) {
            LOGGER.debug("Client already shutdown, aborting normal closure", e);
         }

      }
   }

   public OutputStream getInput() {
      return this.input;
   }

   public InputStream getOutput() {
      return this.out.inputStream;
   }

   public InputStream getError() {
      return this.error.inputStream;
   }

   public InputStream getErrorChannel() {
      return this.errorChannel.inputStream;
   }

   public void resize(int cols, int rows) {
      if (cols >= 0 && rows >= 0) {
         try {
            Map<String, Integer> map = new HashMap(4);
            map.put("Height", rows);
            map.put("Width", cols);
            byte[] bytes = this.serialization.asJson(map).getBytes(StandardCharsets.UTF_8);
            this.send(bytes, 0, bytes.length, (byte)4);
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }
   }

   private void send(byte[] bytes, int offset, int length, byte flag) {
      if (length > 0) {
         this.waitForQueue(length);
         WebSocket ws = (WebSocket)this.webSocketRef.get();
         byte[] toSend = new byte[length + 1];
         toSend[0] = flag;
         System.arraycopy(bytes, offset, toSend, 1, length);
         if (!ws.send(ByteBuffer.wrap(toSend))) {
            this.exitCode.completeExceptionally(new IOException("could not send"));
         }
      }

   }

   private void send(byte[] bytes, int offset, int length) {
      this.send(bytes, offset, length, (byte)0);
   }

   void sendWithErrorChecking(byte[] bytes, int offset, int length) {
      this.checkError();
      this.send(bytes, offset, length);
      this.checkError();
   }

   public static int parseExitCode(Status status) {
      if ("Success".equals(status.getStatus())) {
         return 0;
      } else if ("NonZeroExitCode".equals(status.getReason())) {
         if (status.getDetails() == null) {
            return -1;
         } else {
            List<StatusCause> causes = status.getDetails().getCauses();
            return causes == null ? -1 : (Integer)causes.stream().filter((c) -> "ExitCode".equals(c.getReason())).map(StatusCause::getMessage).map(Integer::valueOf).findFirst().orElse(-1);
         }
      } else {
         return -1;
      }
   }

   public CompletableFuture exitCode() {
      return this.exitCode;
   }

   final void waitForQueue(int length) {
      try {
         while(((WebSocket)this.webSocketRef.get()).queueSize() + (long)length > 16777216L && !Thread.interrupted()) {
            this.checkError();
            Thread.sleep(50L);
         }
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      }

   }

   final void checkError() {
      if (this.exitCode.isDone()) {
         try {
            this.exitCode.getNow((Object)null);
         } catch (CompletionException e) {
            throw KubernetesClientException.launderThrowable(e.getCause());
         }
      }

   }

   private final class SimpleResponse implements ExecListener.Response {
      private final HttpResponse response;

      private SimpleResponse(HttpResponse response) {
         this.response = response;
      }

      public int code() {
         return this.response.code();
      }

      public String body() throws IOException {
         return this.response.bodyString();
      }
   }

   private final class ListenerStream {
      private MessageHandler handler;
      private ExecWatchInputStream inputStream;
      private String name;

      public ListenerStream(String name) {
         this.name = name;
      }

      private void handle(ByteBuffer byteString, WebSocket webSocket) throws IOException {
         if (this.handler != null) {
            if (ExecWebSocketListener.LOGGER.isDebugEnabled()) {
               ExecWebSocketListener.LOGGER.debug("exec message received {} bytes on channel {}", byteString.remaining(), this.name);
            }

            this.handler.handle(byteString);
         } else {
            if (ExecWebSocketListener.LOGGER.isDebugEnabled()) {
               String message = ExecWebSocketListener.toString(byteString);
               if (message.length() > 200) {
                  message = message.substring(0, 197) + "...";
               }

               ExecWebSocketListener.LOGGER.debug("exec message received on channel {}: {}", this.name, message);
            }

            webSocket.request();
         }

      }
   }

   @FunctionalInterface
   public interface MessageHandler {
      void handle(ByteBuffer var1) throws IOException;
   }
}
