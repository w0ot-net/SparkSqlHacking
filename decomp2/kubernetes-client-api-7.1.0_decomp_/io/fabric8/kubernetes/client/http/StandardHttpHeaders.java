package io.fabric8.kubernetes.client.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StandardHttpHeaders implements HttpHeaders {
   public static final String CONTENT_TYPE = "Content-Type";
   public static final String CONTENT_LENGTH = "Content-Length";
   public static final String EXPECT = "Expect";
   public static final String EXPECT_CONTINUE = "100-continue";
   public static final String RETRY_AFTER = "Retry-After";
   public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
   private final Map headers;

   public StandardHttpHeaders() {
      this(new LinkedHashMap());
   }

   public StandardHttpHeaders(Map headers) {
      this.headers = headers;
   }

   public List headers(String key) {
      return Collections.unmodifiableList((List)this.headers.getOrDefault(key, Collections.emptyList()));
   }

   public Map headers() {
      return Collections.unmodifiableMap(this.headers);
   }
}
