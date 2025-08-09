package org.sparkproject.jetty.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.SSLEngine;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.ssl.SslConnection;

public abstract class NegotiatingServerConnectionFactory extends AbstractConnectionFactory {
   private final List negotiatedProtocols = new ArrayList();
   private String defaultProtocol;

   public NegotiatingServerConnectionFactory(String protocol, String... negotiatedProtocols) {
      super(protocol);
      if (negotiatedProtocols != null) {
         for(String p : negotiatedProtocols) {
            p = p.trim();
            if (!p.isEmpty()) {
               this.negotiatedProtocols.add(p);
            }
         }
      }

   }

   public String getDefaultProtocol() {
      return this.defaultProtocol;
   }

   public void setDefaultProtocol(String defaultProtocol) {
      String dft = defaultProtocol == null ? "" : defaultProtocol.trim();
      this.defaultProtocol = dft.isEmpty() ? null : dft;
   }

   public List getNegotiatedProtocols() {
      return this.negotiatedProtocols;
   }

   public Connection newConnection(Connector connector, EndPoint endPoint) {
      List<String> negotiated = this.negotiatedProtocols;
      if (negotiated.isEmpty()) {
         negotiated = (List)connector.getProtocols().stream().filter((p) -> {
            ConnectionFactory f = connector.getConnectionFactory(p);
            return !(f instanceof SslConnectionFactory) && !(f instanceof NegotiatingServerConnectionFactory);
         }).collect(Collectors.toList());
      }

      String dft = this.defaultProtocol;
      if (dft == null && !negotiated.isEmpty()) {
         Stream var10000 = negotiated.stream();
         HttpVersion var10001 = HttpVersion.HTTP_1_1;
         Objects.requireNonNull(var10001);
         dft = (String)var10000.filter(var10001::is).findFirst().orElse((String)negotiated.get(0));
      }

      SSLEngine engine = null;
      EndPoint ep = endPoint;

      while(engine == null && ep != null) {
         if (ep instanceof SslConnection.DecryptedEndPoint) {
            engine = ((SslConnection.DecryptedEndPoint)ep).getSslConnection().getSSLEngine();
         } else {
            ep = null;
         }
      }

      return this.configure(this.newServerConnection(connector, endPoint, engine, negotiated, dft), connector, endPoint);
   }

   protected abstract AbstractConnection newServerConnection(Connector var1, EndPoint var2, SSLEngine var3, List var4, String var5);

   public String toString() {
      return String.format("%s@%x{%s,%s,%s}", this.getClass().getSimpleName(), this.hashCode(), this.getProtocols(), this.getDefaultProtocol(), this.getNegotiatedProtocols());
   }
}
