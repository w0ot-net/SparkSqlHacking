package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.internal.SSLUtils;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import lombok.Generated;
import org.slf4j.LoggerFactory;

public abstract class StandardHttpClientBuilder implements HttpClient.Builder {
   protected LinkedHashMap interceptors = new LinkedHashMap();
   protected Duration connectTimeout;
   protected SSLContext sslContext;
   protected String proxyAuthorization;
   protected InetSocketAddress proxyAddress;
   protected boolean followRedirects;
   protected boolean preferHttp11;
   protected TlsVersion[] tlsVersions;
   protected boolean authenticatorNone;
   protected HttpClient client;
   protected HttpClient.Factory clientFactory;
   protected TrustManager[] trustManagers;
   protected KeyManager[] keyManagers;
   protected LinkedHashMap tags = new LinkedHashMap();
   protected HttpClient.ProxyType proxyType;

   protected StandardHttpClientBuilder(HttpClient.Factory clientFactory) {
      this.proxyType = HttpClient.ProxyType.HTTP;
      this.clientFactory = clientFactory;
      if (LoggerFactory.getLogger(HttpLoggingInterceptor.class).isTraceEnabled()) {
         this.interceptors.put("HttpLogging", new HttpLoggingInterceptor());
      }

   }

   public StandardHttpClientBuilder connectTimeout(long connectTimeout, TimeUnit unit) {
      this.connectTimeout = Duration.ofNanos(unit.toNanos(connectTimeout));
      return this;
   }

   public StandardHttpClientBuilder addOrReplaceInterceptor(String name, Interceptor interceptor) {
      if (interceptor == null) {
         this.interceptors.remove(name);
      } else {
         this.interceptors.put(name, interceptor);
      }

      return this;
   }

   public StandardHttpClientBuilder authenticatorNone() {
      this.authenticatorNone = true;
      return this;
   }

   public StandardHttpClientBuilder sslContext(KeyManager[] keyManagers, TrustManager[] trustManagers) {
      this.sslContext = SSLUtils.sslContext(keyManagers, trustManagers);
      this.keyManagers = keyManagers;
      this.trustManagers = trustManagers;
      return this;
   }

   public StandardHttpClientBuilder followAllRedirects() {
      this.followRedirects = true;
      return this;
   }

   public StandardHttpClientBuilder proxyAddress(InetSocketAddress proxyAddress) {
      this.proxyAddress = proxyAddress;
      return this;
   }

   public StandardHttpClientBuilder proxyAuthorization(String credentials) {
      this.proxyAuthorization = credentials;
      return this;
   }

   public StandardHttpClientBuilder proxyType(HttpClient.ProxyType type) {
      this.proxyType = type;
      return this;
   }

   public StandardHttpClientBuilder tlsVersions(TlsVersion... tlsVersions) {
      this.tlsVersions = tlsVersions;
      return this;
   }

   public StandardHttpClientBuilder preferHttp11() {
      this.preferHttp11 = true;
      return this;
   }

   public StandardHttpClientBuilder clientFactory(HttpClient.Factory clientFactory) {
      this.clientFactory = clientFactory;
      return this;
   }

   public HttpClient.DerivedClientBuilder tag(Object value) {
      if (value != null) {
         this.tags.put(value.getClass(), value);
      }

      return this;
   }

   protected abstract StandardHttpClientBuilder newInstance(HttpClient.Factory var1);

   public StandardHttpClientBuilder copy(HttpClient client) {
      T copy = (T)this.newInstance(this.clientFactory);
      copy.connectTimeout = this.connectTimeout;
      copy.sslContext = this.sslContext;
      copy.trustManagers = this.trustManagers;
      copy.keyManagers = this.keyManagers;
      copy.interceptors = new LinkedHashMap(this.interceptors);
      copy.proxyAddress = this.proxyAddress;
      copy.proxyAuthorization = this.proxyAuthorization;
      copy.tlsVersions = this.tlsVersions;
      copy.preferHttp11 = this.preferHttp11;
      copy.followRedirects = this.followRedirects;
      copy.authenticatorNone = this.authenticatorNone;
      copy.client = client;
      copy.tags = new LinkedHashMap(this.tags);
      copy.proxyType = this.proxyType;
      return copy;
   }

   protected void addProxyAuthInterceptor() {
      if (this.proxyAuthorization != null) {
         this.interceptors.put("PROXY-AUTH", new Interceptor() {
            public void before(BasicBuilder builder, HttpRequest httpRequest, Interceptor.RequestTags tags) {
               builder.setHeader("Proxy-Authorization", StandardHttpClientBuilder.this.proxyAuthorization);
            }
         });
      }

   }

   @Generated
   public LinkedHashMap getInterceptors() {
      return this.interceptors;
   }

   @Generated
   public Duration getConnectTimeout() {
      return this.connectTimeout;
   }

   @Generated
   public SSLContext getSslContext() {
      return this.sslContext;
   }

   @Generated
   public String getProxyAuthorization() {
      return this.proxyAuthorization;
   }

   @Generated
   public InetSocketAddress getProxyAddress() {
      return this.proxyAddress;
   }

   @Generated
   public boolean isFollowRedirects() {
      return this.followRedirects;
   }

   @Generated
   public boolean isPreferHttp11() {
      return this.preferHttp11;
   }

   @Generated
   public TlsVersion[] getTlsVersions() {
      return this.tlsVersions;
   }

   @Generated
   public boolean isAuthenticatorNone() {
      return this.authenticatorNone;
   }

   @Generated
   public HttpClient getClient() {
      return this.client;
   }

   @Generated
   public HttpClient.Factory getClientFactory() {
      return this.clientFactory;
   }

   @Generated
   public TrustManager[] getTrustManagers() {
      return this.trustManagers;
   }

   @Generated
   public KeyManager[] getKeyManagers() {
      return this.keyManagers;
   }

   @Generated
   public LinkedHashMap getTags() {
      return this.tags;
   }

   @Generated
   public HttpClient.ProxyType getProxyType() {
      return this.proxyType;
   }
}
