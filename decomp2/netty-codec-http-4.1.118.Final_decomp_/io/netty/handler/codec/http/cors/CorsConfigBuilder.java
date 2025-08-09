package io.netty.handler.codec.http.cors;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public final class CorsConfigBuilder {
   final Set origins;
   final boolean anyOrigin;
   boolean allowNullOrigin;
   boolean enabled = true;
   boolean allowCredentials;
   final Set exposeHeaders = new HashSet();
   long maxAge;
   final Set requestMethods = new HashSet();
   final Set requestHeaders = new HashSet();
   final Map preflightHeaders = new HashMap();
   private boolean noPreflightHeaders;
   boolean shortCircuit;
   boolean allowPrivateNetwork;

   public static CorsConfigBuilder forAnyOrigin() {
      return new CorsConfigBuilder();
   }

   public static CorsConfigBuilder forOrigin(String origin) {
      return "*".equals(origin) ? new CorsConfigBuilder() : new CorsConfigBuilder(new String[]{origin});
   }

   public static CorsConfigBuilder forOrigins(String... origins) {
      return new CorsConfigBuilder(origins);
   }

   CorsConfigBuilder(String... origins) {
      this.origins = new LinkedHashSet(Arrays.asList(origins));
      this.anyOrigin = false;
   }

   CorsConfigBuilder() {
      this.anyOrigin = true;
      this.origins = Collections.emptySet();
   }

   public CorsConfigBuilder allowNullOrigin() {
      this.allowNullOrigin = true;
      return this;
   }

   public CorsConfigBuilder disable() {
      this.enabled = false;
      return this;
   }

   public CorsConfigBuilder exposeHeaders(String... headers) {
      this.exposeHeaders.addAll(Arrays.asList(headers));
      return this;
   }

   public CorsConfigBuilder exposeHeaders(CharSequence... headers) {
      for(CharSequence header : headers) {
         this.exposeHeaders.add(header.toString());
      }

      return this;
   }

   public CorsConfigBuilder allowCredentials() {
      this.allowCredentials = true;
      return this;
   }

   public CorsConfigBuilder maxAge(long max) {
      this.maxAge = max;
      return this;
   }

   public CorsConfigBuilder allowedRequestMethods(HttpMethod... methods) {
      this.requestMethods.addAll(Arrays.asList(methods));
      return this;
   }

   public CorsConfigBuilder allowedRequestHeaders(String... headers) {
      this.requestHeaders.addAll(Arrays.asList(headers));
      return this;
   }

   public CorsConfigBuilder allowedRequestHeaders(CharSequence... headers) {
      for(CharSequence header : headers) {
         this.requestHeaders.add(header.toString());
      }

      return this;
   }

   public CorsConfigBuilder preflightResponseHeader(CharSequence name, Object... values) {
      if (values.length == 1) {
         this.preflightHeaders.put(name, new ConstantValueGenerator(values[0]));
      } else {
         this.preflightResponseHeader(name, (Iterable)Arrays.asList(values));
      }

      return this;
   }

   public CorsConfigBuilder preflightResponseHeader(CharSequence name, Iterable value) {
      this.preflightHeaders.put(name, new ConstantValueGenerator(value));
      return this;
   }

   public CorsConfigBuilder preflightResponseHeader(CharSequence name, Callable valueGenerator) {
      this.preflightHeaders.put(name, valueGenerator);
      return this;
   }

   public CorsConfigBuilder noPreflightResponseHeaders() {
      this.noPreflightHeaders = true;
      return this;
   }

   public CorsConfigBuilder shortCircuit() {
      this.shortCircuit = true;
      return this;
   }

   public CorsConfigBuilder allowPrivateNetwork() {
      this.allowPrivateNetwork = true;
      return this;
   }

   public CorsConfig build() {
      if (this.preflightHeaders.isEmpty() && !this.noPreflightHeaders) {
         this.preflightHeaders.put(HttpHeaderNames.DATE, CorsConfigBuilder.DateValueGenerator.INSTANCE);
         this.preflightHeaders.put(HttpHeaderNames.CONTENT_LENGTH, new ConstantValueGenerator("0"));
      }

      return new CorsConfig(this);
   }

   private static final class ConstantValueGenerator implements Callable {
      private final Object value;

      private ConstantValueGenerator(Object value) {
         this.value = ObjectUtil.checkNotNullWithIAE(value, "value");
      }

      public Object call() {
         return this.value;
      }
   }

   private static final class DateValueGenerator implements Callable {
      static final DateValueGenerator INSTANCE = new DateValueGenerator();

      public Date call() throws Exception {
         return new Date();
      }
   }
}
