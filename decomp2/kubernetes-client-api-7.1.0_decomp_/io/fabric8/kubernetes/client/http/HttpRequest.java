package io.fabric8.kubernetes.client.http;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public interface HttpRequest extends HttpHeaders {
   static String formURLEncode(String value) {
      try {
         return URLEncoder.encode(value, StandardCharsets.UTF_8.displayName());
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
   }

   UUID id();

   URI uri();

   String method();

   String bodyString();

   public interface Builder extends BasicBuilder {
      HttpRequest build();

      Builder uri(String var1);

      Builder url(URL var1);

      Builder uri(URI var1);

      default Builder put(String contentType, String writeValueAsString) {
         return this.method("PUT", contentType, writeValueAsString);
      }

      default Builder put(String contentType, InputStream stream, long length) {
         return this.method("PUT", contentType, stream, length);
      }

      default Builder post(String contentType, String writeValueAsString) {
         return this.method("POST", contentType, writeValueAsString);
      }

      Builder post(String var1, byte[] var2);

      default Builder post(String contentType, InputStream stream, long length) {
         return this.method("POST", contentType, stream, length);
      }

      default Builder delete(String contentType, String writeValueAsString) {
         return this.method("DELETE", contentType, writeValueAsString);
      }

      default Builder patch(String contentType, String patchForUpdate) {
         return this.method("PATCH", contentType, patchForUpdate);
      }

      Builder method(String var1, String var2, String var3);

      Builder method(String var1, String var2, InputStream var3, long var4);

      Builder header(String var1, String var2);

      Builder setHeader(String var1, String var2);

      default Builder post(Map formData) {
         return this.post("application/x-www-form-urlencoded", (String)formData.entrySet().stream().map((e) -> {
            String var10000 = HttpRequest.formURLEncode((String)e.getKey());
            return var10000 + "=" + HttpRequest.formURLEncode((String)e.getValue());
         }).collect(Collectors.joining("&")));
      }

      Builder expectContinue();

      Builder timeout(long var1, TimeUnit var3);

      Builder forStreaming();
   }
}
