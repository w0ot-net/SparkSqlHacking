package io.fabric8.kubernetes.client.http;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface HttpHeaders {
   List headers(String var1);

   Map headers();

   default String header(String key) {
      List<String> headers = this.headers(key);
      if (headers.size() == 1) {
         return (String)headers.get(0);
      } else {
         return headers.isEmpty() ? null : (String)headers.stream().collect(Collectors.joining(","));
      }
   }
}
