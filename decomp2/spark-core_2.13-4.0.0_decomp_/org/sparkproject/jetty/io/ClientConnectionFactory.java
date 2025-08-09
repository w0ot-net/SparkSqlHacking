package org.sparkproject.jetty.io;

import java.io.IOException;
import java.util.Collection;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

public interface ClientConnectionFactory {
   String CLIENT_CONTEXT_KEY = "org.sparkproject.jetty.client";

   Connection newConnection(EndPoint var1, Map var2) throws IOException;

   default Connection customize(Connection connection, Map context) {
      ContainerLifeCycle client = (ContainerLifeCycle)context.get("org.sparkproject.jetty.client");
      if (client != null) {
         Collection var10000 = client.getBeans(EventListener.class);
         Objects.requireNonNull(connection);
         var10000.forEach(connection::addEventListener);
      }

      return connection;
   }

   public abstract static class Info extends ContainerLifeCycle {
      private final ClientConnectionFactory factory;

      public Info(ClientConnectionFactory factory) {
         this.factory = factory;
         this.addBean(factory);
      }

      public abstract List getProtocols(boolean var1);

      public ClientConnectionFactory getClientConnectionFactory() {
         return this.factory;
      }

      public boolean matches(List candidates, boolean secure) {
         return this.getProtocols(secure).stream().anyMatch((p) -> candidates.stream().anyMatch((c) -> c.equalsIgnoreCase(p)));
      }

      public void upgrade(EndPoint endPoint, Map context) {
         throw new UnsupportedOperationException(String.valueOf(this) + " does not support upgrade to another protocol");
      }
   }

   public interface Decorator {
      ClientConnectionFactory apply(ClientConnectionFactory var1);
   }
}
