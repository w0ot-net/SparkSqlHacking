package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.LocalPortForward;
import io.fabric8.kubernetes.client.PortForward;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.utils.URLUtils;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortForwarderWebsocket {
   private static final Logger LOG = LoggerFactory.getLogger(PortForwarderWebsocket.class);
   private final HttpClient client;
   private final Executor executor;
   private final long connectTimeoutMills;

   public PortForwarderWebsocket(HttpClient client, Executor executor, long connectTimeoutMillis) {
      this.client = client;
      this.executor = executor;
      this.connectTimeoutMills = connectTimeoutMillis;
   }

   public LocalPortForward forward(URL resourceBaseUrl, int port, InetAddress localHost, int localPort) {
      try {
         InetSocketAddress inetSocketAddress = this.createNewInetSocketAddress(localHost, localPort);
         final ServerSocketChannel server = ServerSocketChannel.open().bind(inetSocketAddress);
         final AtomicBoolean alive = new AtomicBoolean(true);
         final CopyOnWriteArrayList<PortForward> handles = new CopyOnWriteArrayList();
         final ExecutorService executorService = Executors.newSingleThreadExecutor();
         LocalPortForward localPortForwardHandle = new LocalPortForward() {
            public void close() throws IOException {
               alive.set(false);

               try {
                  server.close();
               } finally {
                  Utils.closeQuietly(handles);
                  executorService.shutdownNow();
               }

            }

            public boolean isAlive() {
               return alive.get();
            }

            public boolean errorOccurred() {
               for(PortForward handle : handles) {
                  if (handle.errorOccurred()) {
                     return true;
                  }
               }

               return false;
            }

            public InetAddress getLocalAddress() {
               try {
                  return ((InetSocketAddress)server.getLocalAddress()).getAddress();
               } catch (IOException e) {
                  throw new IllegalStateException("Cannot determine local address", e);
               }
            }

            public int getLocalPort() {
               try {
                  return ((InetSocketAddress)server.getLocalAddress()).getPort();
               } catch (IOException e) {
                  throw new IllegalStateException("Cannot determine local address", e);
               }
            }

            public Collection getClientThrowables() {
               Collection<Throwable> clientThrowables = new ArrayList();

               for(PortForward handle : handles) {
                  clientThrowables.addAll(handle.getClientThrowables());
               }

               return clientThrowables;
            }

            public Collection getServerThrowables() {
               Collection<Throwable> serverThrowables = new ArrayList();

               for(PortForward handle : handles) {
                  serverThrowables.addAll(handle.getServerThrowables());
               }

               return serverThrowables;
            }
         };
         executorService.execute(() -> {
            while(alive.get()) {
               try {
                  SocketChannel socket = server.accept();
                  handles.add(this.forward(resourceBaseUrl, port, socket, socket));
               } catch (IOException e) {
                  if (alive.get()) {
                     LOG.error("Error while listening for connections", e);
                  }

                  Utils.closeQuietly(new Closeable[]{localPortForwardHandle});
               }
            }

         });
         return localPortForwardHandle;
      } catch (IOException e) {
         throw new IllegalStateException("Unable to port forward", e);
      }
   }

   public PortForward forward(URL resourceBaseUrl, int port, ReadableByteChannel in, WritableByteChannel out) {
      final PortForwarderWebsocketListener listener = new PortForwarderWebsocketListener(in, out, this.executor);
      final CompletableFuture<WebSocket> socket = this.client.newWebSocketBuilder().uri(URI.create(URLUtils.join(new String[]{resourceBaseUrl.toString(), "portforward?ports=" + port}))).connectTimeout(this.connectTimeoutMills, TimeUnit.MILLISECONDS).subprotocol("v4.channel.k8s.io").buildAsync(listener);
      socket.whenComplete((w, t) -> {
         if (t != null) {
            listener.onError(w, t);
         }

      });
      return new PortForward() {
         private final AtomicBoolean closed = new AtomicBoolean();

         public void close() {
            if (this.closed.compareAndSet(false, true)) {
               socket.whenComplete((w, t) -> {
                  if (w != null) {
                     listener.closeBothWays(w, 1001, "User closing");
                  }

               });
            }
         }

         public boolean isAlive() {
            return listener.isAlive();
         }

         public boolean errorOccurred() {
            return listener.errorOccurred();
         }

         public Collection getClientThrowables() {
            return listener.getClientThrowables();
         }

         public Collection getServerThrowables() {
            return listener.getServerThrowables();
         }
      };
   }

   InetSocketAddress createNewInetSocketAddress(InetAddress localHost, int localPort) {
      return localHost == null ? new InetSocketAddress(localPort) : new InetSocketAddress(localHost, localPort);
   }
}
