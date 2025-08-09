package io.vertx.ext.web.client.impl.cache;

import io.netty.handler.codec.DateFormatter;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CacheControl {
   private final Set directives = new HashSet();
   private final Map timeDirectives = new HashMap();
   private final Instant expires;
   private final Instant date;
   private final String etag;
   private final String vary;
   private final long maxAge;

   static CacheControl parse(MultiMap headers) {
      return new CacheControl(headers);
   }

   private CacheControl(MultiMap headers) {
      this.etag = headers.get(HttpHeaders.ETAG);
      this.vary = headers.get(HttpHeaders.VARY);
      if (headers.contains(HttpHeaders.DATE)) {
         Date date = DateFormatter.parseHttpDate(headers.get(HttpHeaders.DATE));
         this.date = date == null ? null : date.toInstant();
      } else {
         this.date = Instant.now();
      }

      if (headers.contains(HttpHeaders.EXPIRES)) {
         Date expiresDate = DateFormatter.parseHttpDate(headers.get(HttpHeaders.EXPIRES));
         this.expires = expiresDate == null ? null : expiresDate.toInstant();
      } else {
         this.expires = null;
      }

      this.parseAllCacheControl(headers);
      this.maxAge = this.computeMaxAge();
   }

   public Set getDirectives() {
      return this.directives;
   }

   public Map getTimeDirectives() {
      return this.timeDirectives;
   }

   public String getEtag() {
      return this.etag;
   }

   public long getMaxAge() {
      return this.maxAge;
   }

   public Set variations() {
      if (this.vary == null) {
         return Collections.emptySet();
      } else {
         Set<CharSequence> variations = new HashSet();
         String remaining = this.vary;

         for(int nextDelim = remaining.indexOf(44); nextDelim > 0; nextDelim = remaining.indexOf(44)) {
            variations.add(HttpHeaders.createOptimized(remaining.substring(0, nextDelim).trim().toLowerCase()));
            remaining = remaining.substring(nextDelim + 1);
         }

         if (!remaining.isEmpty()) {
            variations.add(HttpHeaders.createOptimized(remaining.trim().toLowerCase()));
         }

         return variations;
      }
   }

   public boolean isCacheable() {
      if (this.directives.contains(CacheControlDirective.NO_STORE)) {
         return false;
      } else if ("*".equals(this.vary)) {
         return false;
      } else {
         return this.maxAge > 0L;
      }
   }

   public boolean isPublic() {
      return this.directives.contains(CacheControlDirective.PUBLIC) && !this.isPrivate();
   }

   public boolean isPrivate() {
      return this.directives.contains(CacheControlDirective.PRIVATE);
   }

   public boolean isVarying() {
      return !this.variations().isEmpty();
   }

   public boolean noStore() {
      return this.directives.contains(CacheControlDirective.NO_STORE);
   }

   public boolean noCache() {
      return !this.noStore() && this.directives.contains(CacheControlDirective.NO_CACHE);
   }

   public boolean mustRevalidate() {
      return this.directives.contains(CacheControlDirective.MUST_REVALIDATE);
   }

   private long computeMaxAge() {
      if (!this.isPrivate() && this.timeDirectives.containsKey(CacheControlDirective.SHARED_MAX_AGE)) {
         return (Long)this.timeDirectives.get(CacheControlDirective.SHARED_MAX_AGE);
      } else if (this.timeDirectives.containsKey(CacheControlDirective.MAX_AGE)) {
         return (Long)this.timeDirectives.get(CacheControlDirective.MAX_AGE);
      } else {
         return this.date != null && this.expires != null ? Duration.between(this.date, this.expires).getSeconds() : Long.MAX_VALUE;
      }
   }

   private void parseAllCacheControl(MultiMap headers) {
      headers.getAll(HttpHeaders.CACHE_CONTROL).forEach((value) -> {
         for(String headerDirectives : value.split(",")) {
            String[] directiveParts = headerDirectives.split("=", 2);
            Optional<CacheControlDirective> directive = CacheControlDirective.fromHeader(directiveParts[0].trim().toLowerCase());
            if (directive.isPresent()) {
               if (directiveParts.length == 1) {
                  this.directives.add(directive.get());
               } else {
                  try {
                     this.timeDirectives.put(directive.get(), Long.parseLong(directiveParts[1].replaceAll("\"", "").trim().toLowerCase()));
                  } catch (NumberFormatException var9) {
                  }
               }
            }
         }

      });
   }
}
