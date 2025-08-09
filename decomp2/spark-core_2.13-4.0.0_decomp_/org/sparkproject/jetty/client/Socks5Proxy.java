package org.sparkproject.jetty.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class Socks5Proxy extends ProxyConfiguration.Proxy {
   private static final Logger LOG = LoggerFactory.getLogger(Socks5Proxy.class);
   private final Map authentications;

   public Socks5Proxy(String host, int port) {
      this(new Origin.Address(host, port), false);
   }

   public Socks5Proxy(Origin.Address address, boolean secure) {
      super(address, secure, (SslContextFactory.Client)null, (Origin.Protocol)null);
      this.authentications = new LinkedHashMap();
      this.putAuthenticationFactory(new Socks5.NoAuthenticationFactory());
   }

   public Socks5.Authentication.Factory putAuthenticationFactory(Socks5.Authentication.Factory authenticationFactory) {
      return (Socks5.Authentication.Factory)this.authentications.put(authenticationFactory.getMethod(), authenticationFactory);
   }

   public Socks5.Authentication.Factory removeAuthenticationFactory(byte method) {
      return (Socks5.Authentication.Factory)this.authentications.remove(method);
   }

   public ClientConnectionFactory newClientConnectionFactory(ClientConnectionFactory connectionFactory) {
      return new Socks5ProxyClientConnectionFactory(connectionFactory);
   }

   private class Socks5ProxyClientConnectionFactory implements ClientConnectionFactory {
      private final ClientConnectionFactory connectionFactory;

      private Socks5ProxyClientConnectionFactory(ClientConnectionFactory connectionFactory) {
         this.connectionFactory = connectionFactory;
      }

      public org.sparkproject.jetty.io.Connection newConnection(EndPoint endPoint, Map context) {
         HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
         Executor executor = destination.getHttpClient().getExecutor();
         Socks5ProxyConnection connection = new Socks5ProxyConnection(endPoint, executor, this.connectionFactory, context, Socks5Proxy.this.authentications);
         return this.customize(connection, context);
      }
   }

   private static class Socks5ProxyConnection extends AbstractConnection implements org.sparkproject.jetty.io.Connection.UpgradeFrom {
      private static final Pattern IPv4_PATTERN = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");
      private final ByteBuffer byteBuffer = BufferUtil.allocate(512);
      private final ClientConnectionFactory connectionFactory;
      private final Map context;
      private final Map authentications;
      private State state;

      private Socks5ProxyConnection(EndPoint endPoint, Executor executor, ClientConnectionFactory connectionFactory, Map context, Map authentications) {
         super(endPoint, executor);
         this.state = Socks5Proxy.Socks5ProxyConnection.State.HANDSHAKE;
         this.connectionFactory = connectionFactory;
         this.context = context;
         this.authentications = Map.copyOf(authentications);
      }

      public ByteBuffer onUpgradeFrom() {
         return BufferUtil.copy(this.byteBuffer);
      }

      public void onOpen() {
         super.onOpen();
         this.sendHandshake();
      }

      private void sendHandshake() {
         try {
            int size = this.authentications.size();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2 + size).put((byte)5).put((byte)size);
            Set var10000 = this.authentications.keySet();
            Objects.requireNonNull(byteBuffer);
            var10000.forEach(byteBuffer::put);
            byteBuffer.flip();
            this.getEndPoint().write(Callback.from(this::handshakeSent, this::fail), byteBuffer);
         } catch (Throwable x) {
            this.fail(x);
         }

      }

      private void handshakeSent() {
         if (Socks5Proxy.LOG.isDebugEnabled()) {
            Socks5Proxy.LOG.debug("Written SOCKS5 handshake request");
         }

         this.state = Socks5Proxy.Socks5ProxyConnection.State.HANDSHAKE;
         this.fillInterested();
      }

      private void fail(Throwable x) {
         if (Socks5Proxy.LOG.isDebugEnabled()) {
            Socks5Proxy.LOG.debug("SOCKS5 failure", x);
         }

         this.getEndPoint().close(x);
         Promise<Connection> promise = (Promise)this.context.get("org.sparkproject.jetty.client.connection.promise");
         promise.failed(x);
      }

      public boolean onIdleExpired() {
         this.fail(new TimeoutException("Idle timeout expired"));
         return false;
      }

      public void onFillable() {
         try {
            switch (this.state.ordinal()) {
               case 0:
                  this.receiveHandshake();
                  break;
               case 1:
                  this.receiveConnect();
                  break;
               default:
                  throw new IllegalStateException();
            }
         } catch (Throwable x) {
            this.fail(x);
         }

      }

      private void receiveHandshake() throws IOException {
         int filled = this.getEndPoint().fill(this.byteBuffer);
         if (filled < 0) {
            throw new ClosedChannelException();
         } else if (this.byteBuffer.remaining() < 2) {
            this.fillInterested();
         } else {
            if (Socks5Proxy.LOG.isDebugEnabled()) {
               Socks5Proxy.LOG.debug("Received SOCKS5 handshake response {}", BufferUtil.toDetailString(this.byteBuffer));
            }

            byte version = this.byteBuffer.get();
            if (version != 5) {
               throw new IOException("Unsupported SOCKS5 version: " + version);
            } else {
               byte method = this.byteBuffer.get();
               if (method == -1) {
                  throw new IOException("Unacceptable SOCKS5 authentication methods");
               } else {
                  Socks5.Authentication.Factory factory = (Socks5.Authentication.Factory)this.authentications.get(method);
                  if (factory == null) {
                     throw new IOException("Unknown SOCKS5 authentication method: " + method);
                  } else {
                     factory.newAuthentication().authenticate(this.getEndPoint(), Callback.from(this::sendConnect, this::fail));
                  }
               }
            }
         }
      }

      private void sendConnect() {
         try {
            HttpDestination destination = (HttpDestination)this.context.get("org.sparkproject.jetty.client.destination");
            Origin.Address address = destination.getOrigin().getAddress();
            String host = address.getHost();
            short port = (short)address.getPort();
            Matcher matcher = IPv4_PATTERN.matcher(host);
            ByteBuffer byteBuffer;
            if (!matcher.matches()) {
               if (URIUtil.isValidHostRegisteredName(host)) {
                  byte[] bytes = host.getBytes(StandardCharsets.US_ASCII);
                  if (bytes.length > 255) {
                     throw new IOException("Invalid host name: " + host);
                  }

                  byteBuffer = ByteBuffer.allocate(7 + bytes.length).put((byte)5).put((byte)1).put((byte)0).put((byte)3).put((byte)bytes.length).put(bytes).putShort(port).flip();
               } else {
                  byte[] bytes = InetAddress.getByName(host).getAddress();
                  byteBuffer = ByteBuffer.allocate(22).put((byte)5).put((byte)1).put((byte)0).put((byte)4).put(bytes).putShort(port).flip();
               }
            } else {
               byteBuffer = ByteBuffer.allocate(10).put((byte)5).put((byte)1).put((byte)0).put((byte)1);

               for(int i = 1; i <= 4; ++i) {
                  byteBuffer.put((byte)Integer.parseInt(matcher.group(i)));
               }

               byteBuffer.putShort(port).flip();
            }

            this.getEndPoint().write(Callback.from(this::connectSent, this::fail), byteBuffer);
         } catch (Throwable x) {
            this.fail(x);
         }

      }

      private void connectSent() {
         if (Socks5Proxy.LOG.isDebugEnabled()) {
            Socks5Proxy.LOG.debug("Written SOCKS5 connect request");
         }

         this.state = Socks5Proxy.Socks5ProxyConnection.State.CONNECT;
         this.fillInterested();
      }

      private void receiveConnect() throws IOException {
         int filled = this.getEndPoint().fill(this.byteBuffer);
         if (filled < 0) {
            throw new ClosedChannelException();
         } else if (this.byteBuffer.remaining() < 5) {
            this.fillInterested();
         } else {
            byte addressType = this.byteBuffer.get(3);
            int length = 6;
            if (addressType == 1) {
               length += 4;
            } else if (addressType == 3) {
               length += 1 + (this.byteBuffer.get(4) & 255);
            } else {
               if (addressType != 4) {
                  throw new IOException("Invalid SOCKS5 address type: " + addressType);
               }

               length += 16;
            }

            if (this.byteBuffer.remaining() < length) {
               this.fillInterested();
            } else {
               if (Socks5Proxy.LOG.isDebugEnabled()) {
                  Socks5Proxy.LOG.debug("Received SOCKS5 connect response {}", BufferUtil.toDetailString(this.byteBuffer));
               }

               byte version = this.byteBuffer.get();
               if (version != 5) {
                  throw new IOException("Unsupported SOCKS5 version: " + version);
               } else {
                  byte status = this.byteBuffer.get();
                  switch (status) {
                     case 0:
                        this.byteBuffer.position(length);
                        this.tunnel();
                        return;
                     case 1:
                        throw new IOException("SOCKS5 general failure");
                     case 2:
                        throw new IOException("SOCKS5 connection not allowed");
                     case 3:
                        throw new IOException("SOCKS5 network unreachable");
                     case 4:
                        throw new IOException("SOCKS5 host unreachable");
                     case 5:
                        throw new IOException("SOCKS5 connection refused");
                     case 6:
                        throw new IOException("SOCKS5 timeout expired");
                     case 7:
                        throw new IOException("SOCKS5 unsupported command");
                     case 8:
                        throw new IOException("SOCKS5 unsupported address");
                     default:
                        throw new IOException("SOCKS5 unknown status: " + status);
                  }
               }
            }
         }
      }

      private void tunnel() {
         try {
            HttpDestination destination = (HttpDestination)this.context.get("org.sparkproject.jetty.client.destination");
            InetSocketAddress address = InetSocketAddress.createUnresolved(destination.getHost(), destination.getPort());
            this.context.put("org.sparkproject.jetty.client.connector.remoteSocketAddress", address);
            ClientConnectionFactory connectionFactory = this.connectionFactory;
            if (destination.isSecure()) {
               connectionFactory = destination.newSslClientConnectionFactory((SslContextFactory.Client)null, connectionFactory);
            }

            org.sparkproject.jetty.io.Connection newConnection = connectionFactory.newConnection(this.getEndPoint(), this.context);
            this.getEndPoint().upgrade(newConnection);
            if (Socks5Proxy.LOG.isDebugEnabled()) {
               Socks5Proxy.LOG.debug("SOCKS5 tunnel established: {} over {}", this, newConnection);
            }
         } catch (Throwable x) {
            this.fail(x);
         }

      }

      private static enum State {
         HANDSHAKE,
         CONNECT;

         // $FF: synthetic method
         private static State[] $values() {
            return new State[]{HANDSHAKE, CONNECT};
         }
      }
   }
}
