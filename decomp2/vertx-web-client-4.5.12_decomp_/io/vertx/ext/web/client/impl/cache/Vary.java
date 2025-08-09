package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.RequestOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Vary {
   private final MultiMap requestHeaders;
   private final MultiMap responseHeaders;
   private final Set variations;

   public Vary(MultiMap requestHeaders, MultiMap responseHeaders) {
      this.requestHeaders = requestHeaders;
      this.responseHeaders = responseHeaders;
      this.variations = this.parseHeaders(responseHeaders);
   }

   public boolean matchesRequest(RequestOptions request) {
      return this.variations.stream().allMatch((variation) -> this.variationMatches(variation, request));
   }

   public String toString() {
      List<String> parts = new ArrayList(this.variations.size());

      for(CharSequence variation : this.variations) {
         parts.addAll(this.normalizeValues(this.requestHeaders.getAll(variation)));
      }

      return (String)parts.stream().sorted().collect(Collectors.joining(","));
   }

   private boolean variationMatches(CharSequence variation, RequestOptions request) {
      if (HttpHeaders.USER_AGENT.equals(variation)) {
         return this.isUserAgentMatch(request);
      } else {
         return HttpHeaders.ACCEPT_ENCODING.equals(variation) ? true : this.isExactMatch(variation, request);
      }
   }

   private boolean isUserAgentMatch(RequestOptions request) {
      UserAgent original = UserAgent.parse(this.requestHeaders);
      UserAgent current = UserAgent.parse(request.getHeaders());
      return original.equals(current);
   }

   private boolean isExactMatch(CharSequence variation, RequestOptions request) {
      Set<String> a = this.normalizeValues(request.getHeaders().getAll(variation));
      Set<String> b = this.normalizeValues(this.requestHeaders.getAll(variation));
      return a.equals(b);
   }

   private Set normalizeValues(List values) {
      return (Set)values.stream().flatMap((v) -> Arrays.stream(v.split(","))).map((v) -> v.trim().toLowerCase()).collect(Collectors.toSet());
   }

   private Set parseHeaders(MultiMap headers) {
      List<String> varyHeaders = headers.getAll(HttpHeaders.VARY);
      Set<CharSequence> parsed = new HashSet(varyHeaders.size());
      varyHeaders.forEach((names) -> {
         for(String name : names.split(",")) {
            parsed.add(HttpHeaders.createOptimized(name.trim().toLowerCase()));
         }

      });
      return parsed;
   }
}
