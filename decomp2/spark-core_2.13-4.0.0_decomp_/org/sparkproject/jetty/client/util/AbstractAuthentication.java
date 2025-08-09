package org.sparkproject.jetty.client.util;

import java.net.URI;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.api.Authentication;

public abstract class AbstractAuthentication implements Authentication {
   private final URI uri;
   private final String realm;

   public AbstractAuthentication(URI uri, String realm) {
      this.uri = uri;
      this.realm = realm;
   }

   public abstract String getType();

   public URI getURI() {
      return this.uri;
   }

   public String getRealm() {
      return this.realm;
   }

   public boolean matches(String type, URI uri, String realm) {
      if (!this.getType().equalsIgnoreCase(type)) {
         return false;
      } else {
         return !this.realm.equals("<<ANY_REALM>>") && !this.realm.equals(realm) ? false : matchesURI(this.uri, uri);
      }
   }

   public static boolean matchesURI(URI uri1, URI uri2) {
      String scheme = uri1.getScheme();
      if (scheme.equalsIgnoreCase(uri2.getScheme()) && uri1.getHost().equalsIgnoreCase(uri2.getHost())) {
         int thisPort = HttpClient.normalizePort(scheme, uri1.getPort());
         int thatPort = HttpClient.normalizePort(scheme, uri2.getPort());
         if (thisPort == thatPort) {
            return uri2.getPath().startsWith(uri1.getPath());
         }
      }

      return false;
   }
}
