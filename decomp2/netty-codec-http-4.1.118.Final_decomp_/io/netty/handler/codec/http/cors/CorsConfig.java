package io.netty.handler.codec.http.cors;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public final class CorsConfig {
   private final Set origins;
   private final boolean anyOrigin;
   private final boolean enabled;
   private final Set exposeHeaders;
   private final boolean allowCredentials;
   private final long maxAge;
   private final Set allowedRequestMethods;
   private final Set allowedRequestHeaders;
   private final boolean allowNullOrigin;
   private final Map preflightHeaders;
   private final boolean shortCircuit;
   private final boolean allowPrivateNetwork;

   CorsConfig(CorsConfigBuilder builder) {
      this.origins = new LinkedHashSet(builder.origins);
      this.anyOrigin = builder.anyOrigin;
      this.enabled = builder.enabled;
      this.exposeHeaders = builder.exposeHeaders;
      this.allowCredentials = builder.allowCredentials;
      this.maxAge = builder.maxAge;
      this.allowedRequestMethods = builder.requestMethods;
      this.allowedRequestHeaders = builder.requestHeaders;
      this.allowNullOrigin = builder.allowNullOrigin;
      this.preflightHeaders = builder.preflightHeaders;
      this.shortCircuit = builder.shortCircuit;
      this.allowPrivateNetwork = builder.allowPrivateNetwork;
   }

   public boolean isCorsSupportEnabled() {
      return this.enabled;
   }

   public boolean isAnyOriginSupported() {
      return this.anyOrigin;
   }

   public String origin() {
      return this.origins.isEmpty() ? "*" : (String)this.origins.iterator().next();
   }

   public Set origins() {
      return this.origins;
   }

   public boolean isNullOriginAllowed() {
      return this.allowNullOrigin;
   }

   public boolean isPrivateNetworkAllowed() {
      return this.allowPrivateNetwork;
   }

   public Set exposedHeaders() {
      return Collections.unmodifiableSet(this.exposeHeaders);
   }

   public boolean isCredentialsAllowed() {
      return this.allowCredentials;
   }

   public long maxAge() {
      return this.maxAge;
   }

   public Set allowedRequestMethods() {
      return Collections.unmodifiableSet(this.allowedRequestMethods);
   }

   public Set allowedRequestHeaders() {
      return Collections.unmodifiableSet(this.allowedRequestHeaders);
   }

   public HttpHeaders preflightResponseHeaders() {
      if (this.preflightHeaders.isEmpty()) {
         return EmptyHttpHeaders.INSTANCE;
      } else {
         HttpHeaders preflightHeaders = new DefaultHttpHeaders();

         for(Map.Entry entry : this.preflightHeaders.entrySet()) {
            Object value = getValue((Callable)entry.getValue());
            if (value instanceof Iterable) {
               preflightHeaders.add((CharSequence)entry.getKey(), (Iterable)value);
            } else {
               preflightHeaders.add((CharSequence)entry.getKey(), value);
            }
         }

         return preflightHeaders;
      }
   }

   public boolean isShortCircuit() {
      return this.shortCircuit;
   }

   /** @deprecated */
   @Deprecated
   public boolean isShortCurcuit() {
      return this.isShortCircuit();
   }

   private static Object getValue(Callable callable) {
      try {
         return callable.call();
      } catch (Exception e) {
         throw new IllegalStateException("Could not generate value for callable [" + callable + ']', e);
      }
   }

   public String toString() {
      return StringUtil.simpleClassName(this) + "[enabled=" + this.enabled + ", origins=" + this.origins + ", anyOrigin=" + this.anyOrigin + ", exposedHeaders=" + this.exposeHeaders + ", isCredentialsAllowed=" + this.allowCredentials + ", maxAge=" + this.maxAge + ", allowedRequestMethods=" + this.allowedRequestMethods + ", allowedRequestHeaders=" + this.allowedRequestHeaders + ", preflightHeaders=" + this.preflightHeaders + ", isPrivateNetworkAllowed=" + this.allowPrivateNetwork + ']';
   }

   /** @deprecated */
   @Deprecated
   public static Builder withAnyOrigin() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public static Builder withOrigin(String origin) {
      return "*".equals(origin) ? new Builder() : new Builder(new String[]{origin});
   }

   /** @deprecated */
   @Deprecated
   public static Builder withOrigins(String... origins) {
      return new Builder(origins);
   }

   /** @deprecated */
   @Deprecated
   public static class Builder {
      private final CorsConfigBuilder builder;

      /** @deprecated */
      @Deprecated
      public Builder(String... origins) {
         this.builder = new CorsConfigBuilder(origins);
      }

      /** @deprecated */
      @Deprecated
      public Builder() {
         this.builder = new CorsConfigBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder allowNullOrigin() {
         this.builder.allowNullOrigin();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder disable() {
         this.builder.disable();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder exposeHeaders(String... headers) {
         this.builder.exposeHeaders(headers);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder allowCredentials() {
         this.builder.allowCredentials();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder maxAge(long max) {
         this.builder.maxAge(max);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder allowedRequestMethods(HttpMethod... methods) {
         this.builder.allowedRequestMethods(methods);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder allowedRequestHeaders(String... headers) {
         this.builder.allowedRequestHeaders(headers);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder preflightResponseHeader(CharSequence name, Object... values) {
         this.builder.preflightResponseHeader(name, values);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder preflightResponseHeader(CharSequence name, Iterable value) {
         this.builder.preflightResponseHeader(name, value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder preflightResponseHeader(String name, Callable valueGenerator) {
         this.builder.preflightResponseHeader(name, (Callable)valueGenerator);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder noPreflightResponseHeaders() {
         this.builder.noPreflightResponseHeaders();
         return this;
      }

      /** @deprecated */
      @Deprecated
      public CorsConfig build() {
         return this.builder.build();
      }

      /** @deprecated */
      @Deprecated
      public Builder shortCurcuit() {
         this.builder.shortCircuit();
         return this;
      }
   }

   /** @deprecated */
   @Deprecated
   public static final class DateValueGenerator implements Callable {
      public Date call() throws Exception {
         return new Date();
      }
   }
}
