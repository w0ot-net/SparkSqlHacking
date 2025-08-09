package org.apache.zookeeper.server.admin;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import org.apache.zookeeper.server.ServerMetrics;
import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.io.ssl.SslConnection;
import org.eclipse.jetty.server.AbstractConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnifiedConnectionFactory extends AbstractConnectionFactory {
   private static final Logger LOG = LoggerFactory.getLogger(UnifiedConnectionFactory.class);
   private final SslContextFactory sslContextFactory;
   private final String nextProtocol;

   public UnifiedConnectionFactory(String nextProtocol) {
      this((SslContextFactory)null, nextProtocol);
   }

   public UnifiedConnectionFactory(SslContextFactory factory, String nextProtocol) {
      super("SSL");
      this.sslContextFactory = (SslContextFactory)(factory == null ? new SslContextFactory.Server() : factory);
      this.nextProtocol = nextProtocol;
      this.addBean(this.sslContextFactory);
   }

   protected void doStart() throws Exception {
      super.doStart();
      SSLEngine engine = this.sslContextFactory.newSSLEngine();
      SSLSession session = engine.getSession();
      engine.setUseClientMode(false);
      if (session.getPacketBufferSize() > this.getInputBufferSize()) {
         this.setInputBufferSize(session.getPacketBufferSize());
      }

   }

   public Connection newConnection(Connector connector, EndPoint realEndPoint) {
      ReadAheadEndpoint aheadEndpoint = new ReadAheadEndpoint(realEndPoint, 1);
      byte[] bytes = aheadEndpoint.getBytes();
      boolean isSSL;
      if (bytes != null && bytes.length != 0) {
         byte b = bytes[0];
         isSSL = b == 22;
      } else {
         isSSL = false;
         LOG.warn("Incoming connection has no data");
      }

      LOG.debug(String.format("UnifiedConnectionFactory: newConnection() with SSL = %b", isSSL));
      SslConnection sslConnection;
      EndPoint plainEndpoint;
      if (isSSL) {
         SSLEngine engine = this.sslContextFactory.newSSLEngine(aheadEndpoint.getRemoteAddress());
         engine.setUseClientMode(false);
         sslConnection = this.newSslConnection(connector, aheadEndpoint, engine);
         sslConnection.setRenegotiationAllowed(this.sslContextFactory.isRenegotiationAllowed());
         this.configure(sslConnection, connector, aheadEndpoint);
         plainEndpoint = sslConnection.getDecryptedEndPoint();
      } else {
         sslConnection = null;
         plainEndpoint = aheadEndpoint;
         ServerMetrics.getMetrics().INSECURE_ADMIN.add(1L);
      }

      ConnectionFactory next = connector.getConnectionFactory(this.nextProtocol);
      Connection connection = next.newConnection(connector, plainEndpoint);
      plainEndpoint.setConnection(connection);
      return (Connection)(sslConnection == null ? connection : sslConnection);
   }

   protected SslConnection newSslConnection(Connector connector, EndPoint endPoint, SSLEngine engine) {
      return new SslConnection(connector.getByteBufferPool(), connector.getExecutor(), endPoint, engine);
   }

   public String toString() {
      return String.format("%s@%x{%s->%s}", this.getClass().getSimpleName(), this.hashCode(), this.getProtocol(), this.nextProtocol);
   }
}
