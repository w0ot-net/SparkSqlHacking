package io.fabric8.kubernetes.client.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBasicBuilder implements BasicBuilder {
   private URI uri;
   private final Map headers = new HashMap();

   public BasicBuilder uri(URI uri) {
      this.uri = uri;
      return this;
   }

   public BasicBuilder header(String name, String value) {
      this.headers.compute(name, (k, v) -> {
         if (v == null) {
            v = new ArrayList();
         }

         v.add(value);
         return v;
      });
      return this;
   }

   public BasicBuilder setHeader(String name, String value) {
      this.headers.put(name, new ArrayList(Collections.singletonList(value)));
      return this;
   }

   protected final URI getUri() {
      return this.uri;
   }

   protected final Map getHeaders() {
      return this.headers;
   }

   protected final void setHeaders(Map headers) {
      this.headers.clear();
      headers.forEach((header, values) -> values.forEach((value) -> this.header(header, value)));
   }
}
