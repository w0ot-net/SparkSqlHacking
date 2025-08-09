package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.http.AsyncBody;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.utils.internal.SerialExecutor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWatchCallback implements LogWatch, AutoCloseable {
   private static final Logger LOGGER = LoggerFactory.getLogger(LogWatchCallback.class);
   private final OutputStream out;
   private WritableByteChannel outChannel;
   private volatile InputStream output;
   private final AtomicBoolean closed = new AtomicBoolean(false);
   private final CompletableFuture asyncBody = new CompletableFuture();
   private final SerialExecutor serialExecutor;

   public LogWatchCallback(OutputStream out, OperationContext context) {
      this.out = out;
      if (out != null) {
         this.outChannel = Channels.newChannel(out);
      }

      this.serialExecutor = new SerialExecutor(context.getExecutor());
   }

   public void close() {
      this.cleanUp();
   }

   private void cleanUp() {
      if (this.closed.compareAndSet(false, true)) {
         this.asyncBody.thenAccept(AsyncBody::cancel);
         this.serialExecutor.shutdownNow();
      }
   }

   public LogWatchCallback callAndWait(HttpClient client, URL url) {
      HttpRequest request = client.newHttpRequestBuilder().url(url).build();
      if (this.out == null) {
         client.sendAsync(request, InputStream.class).whenComplete((r, e) -> {
            if (e != null) {
               this.onFailure(e);
            }

            if (r != null) {
               this.output = (InputStream)r.body();
            }

         }).join();
      } else {
         client.consumeBytes(request, (buffers, a) -> CompletableFuture.runAsync(() -> {
               for(ByteBuffer byteBuffer : buffers) {
                  try {
                     this.outChannel.write(byteBuffer);
                  } catch (IOException e1) {
                     throw KubernetesClientException.launderThrowable(e1);
                  }
               }

            }, this.serialExecutor).whenComplete((v, t) -> {
               if (t != null) {
                  a.cancel();
                  this.onFailure(t);
               } else if (!this.closed.get()) {
                  a.consume();
               } else {
                  a.cancel();
               }

            })).whenComplete((a, e) -> {
            if (e != null) {
               this.onFailure(e);
            }

            if (a != null) {
               this.asyncBody.complete((AsyncBody)a.body());
               ((AsyncBody)a.body()).consume();
               ((AsyncBody)a.body()).done().whenComplete((v, t) -> CompletableFuture.runAsync(() -> {
                     if (t != null) {
                        this.onFailure(t);
                     } else {
                        this.cleanUp();
                     }

                  }, this.serialExecutor));
            }

         });
      }

      return this;
   }

   public InputStream getOutput() {
      return this.output;
   }

   public void onFailure(Throwable u) {
      if (!this.closed.get()) {
         LOGGER.error("Log Callback Failure.", u);
         this.cleanUp();
      }
   }
}
