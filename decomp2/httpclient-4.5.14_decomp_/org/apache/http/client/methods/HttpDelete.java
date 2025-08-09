package org.apache.http.client.methods;

import java.net.URI;

public class HttpDelete extends HttpRequestBase {
   public static final String METHOD_NAME = "DELETE";

   public HttpDelete() {
   }

   public HttpDelete(URI uri) {
      this.setURI(uri);
   }

   public HttpDelete(String uri) {
      this.setURI(URI.create(uri));
   }

   public String getMethod() {
      return "DELETE";
   }
}
