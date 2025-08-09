package io.vertx.ext.web.client.impl.cache;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CacheControlDirective {
   PUBLIC("public"),
   PRIVATE("private"),
   NO_STORE("no-store"),
   NO_CACHE("no-cache"),
   SHARED_MAX_AGE("s-maxage"),
   MAX_AGE("max-age"),
   STALE_IF_ERROR("stale-if-error"),
   STALE_WHILE_REVALIDATE("stale-while-revalidate"),
   MUST_REVALIDATE("must-revalidate");

   private final String value;
   private static final Map VALUE_MAP = (Map)Arrays.stream(values()).collect(Collectors.toMap((d) -> d.value, Function.identity()));

   public static Optional fromHeader(String headerValue) {
      return VALUE_MAP.containsKey(headerValue) ? Optional.of(VALUE_MAP.get(headerValue)) : Optional.empty();
   }

   private CacheControlDirective(String value) {
      this.value = value;
   }
}
