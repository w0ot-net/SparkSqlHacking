package org.sparkproject.jetty.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Objects;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.RetainableByteBufferPool;
import org.sparkproject.jetty.io.ssl.SslConnection;
import org.sparkproject.jetty.io.ssl.SslHandshakeListener;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class SslConnectionFactory extends AbstractConnectionFactory implements ConnectionFactory.Detecting, ConnectionFactory.Configuring {
   private static final int TLS_ALERT_FRAME_TYPE = 21;
   private static final int TLS_HANDSHAKE_FRAME_TYPE = 22;
   private static final int TLS_MAJOR_VERSION = 3;
   private final SslContextFactory.Server _sslContextFactory;
   private final String _nextProtocol;
   private boolean _directBuffersForEncryption;
   private boolean _directBuffersForDecryption;
   private boolean _ensureSecureRequestCustomizer;

   public SslConnectionFactory() {
      this(HttpVersion.HTTP_1_1.asString());
   }

   public SslConnectionFactory(@Name("next") String nextProtocol) {
      this((SslContextFactory.Server)null, nextProtocol);
   }

   public SslConnectionFactory(@Name("sslContextFactory") SslContextFactory.Server factory, @Name("next") String nextProtocol) {
      super("SSL");
      this._directBuffersForEncryption = false;
      this._directBuffersForDecryption = false;
      this._ensureSecureRequestCustomizer = true;
      this._sslContextFactory = factory == null ? new SslContextFactory.Server() : factory;
      this._nextProtocol = nextProtocol;
      this.addBean(this._sslContextFactory);
   }

   public SslContextFactory.Server getSslContextFactory() {
      return this._sslContextFactory;
   }

   public void setDirectBuffersForEncryption(boolean useDirectBuffers) {
      this._directBuffersForEncryption = useDirectBuffers;
   }

   public void setDirectBuffersForDecryption(boolean useDirectBuffers) {
      this._directBuffersForDecryption = useDirectBuffers;
   }

   public boolean isDirectBuffersForDecryption() {
      return this._directBuffersForDecryption;
   }

   public boolean isDirectBuffersForEncryption() {
      return this._directBuffersForEncryption;
   }

   public String getNextProtocol() {
      return this._nextProtocol;
   }

   public boolean isEnsureSecureRequestCustomizer() {
      return this._ensureSecureRequestCustomizer;
   }

   public void setEnsureSecureRequestCustomizer(boolean ensureSecureRequestCustomizer) {
      this._ensureSecureRequestCustomizer = ensureSecureRequestCustomizer;
   }

   protected void doStart() throws Exception {
      super.doStart();
      SSLEngine engine = this._sslContextFactory.newSSLEngine();
      engine.setUseClientMode(false);
      SSLSession session = engine.getSession();
      if (session.getPacketBufferSize() > this.getInputBufferSize()) {
         this.setInputBufferSize(session.getPacketBufferSize());
      }

   }

   public void configure(Connector connector) {
      if (this.isEnsureSecureRequestCustomizer()) {
         connector.getContainedBeans(HttpConfiguration.class).forEach((configuration) -> {
            if (configuration.getCustomizer(SecureRequestCustomizer.class) == null) {
               configuration.addCustomizer(new SecureRequestCustomizer());
            }

         });
      }

   }

   public ConnectionFactory.Detecting.Detection detect(ByteBuffer buffer) {
      if (buffer.remaining() < 2) {
         return ConnectionFactory.Detecting.Detection.NEED_MORE_BYTES;
      } else {
         int tlsFrameType = buffer.get(0) & 255;
         int tlsMajorVersion = buffer.get(1) & 255;
         boolean seemsSsl = (tlsFrameType == 22 || tlsFrameType == 21) && tlsMajorVersion == 3;
         return seemsSsl ? ConnectionFactory.Detecting.Detection.RECOGNIZED : ConnectionFactory.Detecting.Detection.NOT_RECOGNIZED;
      }
   }

   public Connection newConnection(Connector connector, EndPoint endPoint) {
      SocketAddress remoteSocketAddress = endPoint.getRemoteSocketAddress();
      SSLEngine engine = remoteSocketAddress instanceof InetSocketAddress ? this._sslContextFactory.newSSLEngine((InetSocketAddress)remoteSocketAddress) : this._sslContextFactory.newSSLEngine();
      engine.setUseClientMode(false);
      SslConnection sslConnection = this.newSslConnection(connector, endPoint, engine);
      sslConnection.setRenegotiationAllowed(this._sslContextFactory.isRenegotiationAllowed());
      sslConnection.setRenegotiationLimit(this._sslContextFactory.getRenegotiationLimit());
      this.configure(sslConnection, connector, endPoint);
      ConnectionFactory next = connector.getConnectionFactory(this._nextProtocol);
      EndPoint decryptedEndPoint = sslConnection.getDecryptedEndPoint();
      Connection connection = next.newConnection(connector, decryptedEndPoint);
      decryptedEndPoint.setConnection(connection);
      return sslConnection;
   }

   protected SslConnection newSslConnection(Connector connector, EndPoint endPoint, SSLEngine engine) {
      ByteBufferPool byteBufferPool = connector.getByteBufferPool();
      RetainableByteBufferPool retainableByteBufferPool = byteBufferPool.asRetainableByteBufferPool();
      return new SslConnection(retainableByteBufferPool, byteBufferPool, connector.getExecutor(), endPoint, engine, this.isDirectBuffersForEncryption(), this.isDirectBuffersForDecryption());
   }

   protected AbstractConnection configure(AbstractConnection connection, Connector connector, EndPoint endPoint) {
      if (connection instanceof SslConnection) {
         SslConnection sslConnection = (SslConnection)connection;
         if (connector instanceof ContainerLifeCycle) {
            ContainerLifeCycle container = (ContainerLifeCycle)connector;
            Collection var10000 = container.getBeans(SslHandshakeListener.class);
            Objects.requireNonNull(sslConnection);
            var10000.forEach(sslConnection::addHandshakeListener);
         }

         Collection var6 = this.getBeans(SslHandshakeListener.class);
         Objects.requireNonNull(sslConnection);
         var6.forEach(sslConnection::addHandshakeListener);
      }

      return super.configure(connection, connector, endPoint);
   }

   public String toString() {
      return String.format("%s@%x{%s->%s}", this.getClass().getSimpleName(), this.hashCode(), this.getProtocol(), this._nextProtocol);
   }
}
