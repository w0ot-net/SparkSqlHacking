package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;

/** @deprecated */
@Deprecated
public class OptionalSslConnectionFactory extends DetectorConnectionFactory {
   private static final Logger LOG = LoggerFactory.getLogger(OptionalSslConnectionFactory.class);
   private final String _nextProtocol;

   public OptionalSslConnectionFactory(SslConnectionFactory sslConnectionFactory, String nextProtocol) {
      super(sslConnectionFactory);
      this._nextProtocol = nextProtocol;
   }

   protected void nextProtocol(Connector connector, EndPoint endPoint, ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("OptionalSSL TLS detection unsuccessful, attempting to upgrade to {}", this._nextProtocol);
      }

      if (this._nextProtocol != null) {
         ConnectionFactory connectionFactory = connector.getConnectionFactory(this._nextProtocol);
         if (connectionFactory == null) {
            String var10002 = this._nextProtocol;
            throw new IllegalStateException("Cannot find protocol '" + var10002 + "' in connector's protocol list " + String.valueOf(connector.getProtocols()) + " for " + String.valueOf(endPoint));
         }

         upgradeToConnectionFactory(connectionFactory, connector, endPoint);
      } else {
         this.otherProtocol(buffer, endPoint);
      }

   }

   /** @deprecated */
   @Deprecated
   protected void otherProtocol(ByteBuffer buffer, EndPoint endPoint) {
      LOG.warn("Detected non-TLS bytes, but no other protocol to upgrade to for {}", endPoint);
      int byte1 = buffer.get(0) & 255;
      int byte2 = buffer.get(1) & 255;
      if (byte1 == 71 && byte2 == 69) {
         String body = "<!DOCTYPE html>\r\n<html>\r\n<head><title>Bad Request</title></head>\r\n<body><h1>Bad Request</h1><p>HTTP request to HTTPS port</p></body>\r\n</html>";
         int var10000 = body.length();
         String response = "HTTP/1.1 400 Bad Request\r\nContent-Type: text/html\r\nContent-Length: " + var10000 + "\r\nConnection: close\r\n\r\n" + body;
         Callback.Completable completable = new Callback.Completable();
         endPoint.write(completable, ByteBuffer.wrap(response.getBytes(StandardCharsets.US_ASCII)));
         completable.whenComplete((r, x) -> endPoint.close());
      } else {
         endPoint.close();
      }

   }
}
