package org.sparkproject.jetty.client;

public class DuplexHttpDestination extends HttpDestination {
   public DuplexHttpDestination(HttpClient client, Origin origin) {
      this(client, origin, false);
   }

   public DuplexHttpDestination(HttpClient client, Origin origin, boolean intrinsicallySecure) {
      super(client, origin, intrinsicallySecure);
   }
}
