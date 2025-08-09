package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.http.BasicBuilder;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.Interceptor;
import io.fabric8.kubernetes.client.internal.SSLUtils;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
   private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
   private static final String HEADER_INTERCEPTOR = "HEADER";
   private static final String KUBERNETES_BACKWARDS_COMPATIBILITY_INTERCEPTOR_DISABLE = "kubernetes.backwardsCompatibilityInterceptor.disable";
   private static final String BACKWARDS_COMPATIBILITY_DISABLE_DEFAULT = "true";
   private static final Pattern IP_PATTERN = Pattern.compile("(http(s?)://)?(?<ipAddressOrSubnet>((\\d{1,3}(.\\d{1,3}){3})|([a-f\\d]{1,4}(\\:[a-f\\d]{0,4}){2,7}))(/\\d+)?)", 2);
   private static final Pattern INVALID_HOST_PATTERN = Pattern.compile("[^\\da-zA-Z.\\-/:]+");

   private HttpClientUtils() {
   }

   static URI getProxyUri(URL master, Config config) throws URISyntaxException {
      String proxy = config.getHttpsProxy();
      if (master.getProtocol().equals("http")) {
         proxy = config.getHttpProxy();
      }

      if (proxy != null) {
         String completedProxy;
         if (proxy.contains("://")) {
            completedProxy = proxy;
         } else {
            String var10000 = master.getProtocol();
            completedProxy = var10000 + "://" + proxy;
         }

         URI proxyUrl = new URI(completedProxy);
         if (proxyUrl.getPort() < 0) {
            throw new IllegalArgumentException("Failure in creating proxy URL. Proxy port is required!");
         } else {
            return proxyUrl;
         }
      } else {
         return null;
      }
   }

   public static Map createApplicableInterceptors(Config config, HttpClient.Factory factory) {
      Map<String, Interceptor> interceptors = new LinkedHashMap();
      interceptors.put("HEADER", new HeaderInterceptor(config));
      interceptors.put("IMPERSONATOR", new ImpersonatorInterceptor(config.getRequestConfig()));
      interceptors.put("TOKEN", new TokenRefreshInterceptor(config, factory, Instant.now()));
      String shouldDisableBackwardsCompatibilityInterceptor = Utils.getSystemPropertyOrEnvVar("kubernetes.backwardsCompatibilityInterceptor.disable", "true");
      if (!Boolean.parseBoolean(shouldDisableBackwardsCompatibilityInterceptor)) {
         interceptors.put("BACKWARDS", new BackwardsCompatibilityInterceptor());
      }

      return interceptors;
   }

   public static String basicCredentials(String usernameAndPassword) {
      String encoded = Base64.getEncoder().encodeToString(usernameAndPassword.getBytes(StandardCharsets.UTF_8));
      return "Basic " + encoded;
   }

   public static String basicCredentials(String username, String password) {
      return basicCredentials(username + ":" + password);
   }

   public static String[] decodeBasicCredentials(String basicCredentials) {
      if (basicCredentials == null) {
         return null;
      } else {
         try {
            String encodedCredentials = basicCredentials.replaceFirst("Basic ", "");
            String decodedProxyAuthorization = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
            String[] userPassword = decodedProxyAuthorization.split(":");
            if (userPassword.length == 2) {
               return userPassword;
            }
         } catch (Exception var4) {
         }

         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static HttpClient createHttpClient(Config config) {
      HttpClient.Factory factory = getHttpClientFactory();
      return factory.newBuilder(config).build();
   }

   public static HttpClient.Factory getHttpClientFactory() {
      HttpClient.Factory factory = getFactory(ServiceLoader.load(HttpClient.Factory.class, Thread.currentThread().getContextClassLoader()));
      if (factory == null) {
         factory = getFactory(ServiceLoader.load(HttpClient.Factory.class, HttpClientUtils.class.getClassLoader()));
         if (factory == null) {
            throw new KubernetesClientException("No httpclient implementations found on the context classloader, please ensure your classpath includes an implementation jar");
         }
      }

      LOGGER.debug("Using httpclient {} factory", factory.getClass().getName());
      return factory;
   }

   private static HttpClient.Factory getFactory(ServiceLoader loader) {
      List<HttpClient.Factory> factories = new ArrayList();
      Objects.requireNonNull(factories);
      loader.forEach(factories::add);
      if (factories.isEmpty()) {
         return null;
      } else {
         Collections.sort(factories, (f1, f2) -> Integer.compare(f2.priority(), f1.priority()));
         HttpClient.Factory factory = (HttpClient.Factory)factories.get(0);
         if (factories.size() > 1) {
            if (((HttpClient.Factory)factories.get(1)).priority() == factory.priority()) {
               LOGGER.warn("The following httpclient factories were detected on your classpath: {}, multiple of which had the same priority ({}) so one was chosen randomly. You should exclude dependencies that aren't needed or use an explicit association of the HttpClient.Factory.", factories.stream().map((f) -> f.getClass().getName()).toArray(), factory.priority());
            } else if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("The following httpclient factories were detected on your classpath: {}", factories.stream().map((f) -> f.getClass().getName()).toArray());
            }
         }

         return factory;
      }
   }

   public static void applyCommonConfiguration(Config config, HttpClient.Builder builder, HttpClient.Factory factory) {
      builder.followAllRedirects();
      builder.tag(config.getRequestConfig());
      if (config.getConnectionTimeout() > 0) {
         builder.connectTimeout((long)config.getConnectionTimeout(), TimeUnit.MILLISECONDS);
      }

      if (config.isHttp2Disable()) {
         builder.preferHttp11();
      }

      try {
         configureProxy(config, builder);
         TrustManager[] trustManagers = SSLUtils.trustManagers(config);
         KeyManager[] keyManagers = SSLUtils.keyManagers(config);
         builder.sslContext(keyManagers, trustManagers);
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(e);
      }

      if (config.getTlsVersions() != null && config.getTlsVersions().length > 0) {
         builder.tlsVersions(config.getTlsVersions());
      }

      Map var10000 = createApplicableInterceptors(config, factory);
      Objects.requireNonNull(builder);
      var10000.forEach(builder::addOrReplaceInterceptor);
   }

   static void configureProxy(Config config, HttpClient.Builder builder) throws URISyntaxException, MalformedURLException {
      URL master;
      try {
         master = new URL(config.getMasterUrl());
      } catch (MalformedURLException var6) {
         return;
      }

      URI proxyUri = getProxyUri(master, config);
      if (proxyUri != null) {
         String host = master.getHost();
         if (isHostMatchedByNoProxy(host, config.getNoProxy())) {
            builder.proxyType(HttpClient.ProxyType.DIRECT);
         } else {
            builder.proxyAddress(new InetSocketAddress(proxyUri.getHost(), proxyUri.getPort()));
            if (config.getProxyUsername() != null) {
               builder.proxyAuthorization(basicCredentials(config.getProxyUsername(), config.getProxyPassword()));
            }

            String userInfo = proxyUri.getUserInfo();
            if (userInfo != null) {
               builder.proxyAuthorization(basicCredentials(userInfo));
            }

            builder.proxyType(toProxyType(proxyUri.getScheme()));
         }

      }
   }

   static HttpClient.ProxyType toProxyType(String scheme) throws MalformedURLException {
      if (scheme == null) {
         throw new MalformedURLException("No protocol specified on proxy URL");
      } else {
         scheme = scheme.toLowerCase();
         if (scheme.startsWith("http")) {
            return HttpClient.ProxyType.HTTP;
         } else if (scheme.equals("socks4")) {
            return HttpClient.ProxyType.SOCKS4;
         } else if (scheme.equals("socks5")) {
            return HttpClient.ProxyType.SOCKS5;
         } else {
            throw new MalformedURLException("Unsupported protocol specified on proxy URL");
         }
      }
   }

   static boolean isHostMatchedByNoProxy(String host, String[] noProxies) throws MalformedURLException {
      for(String noProxy : noProxies == null ? new String[0] : noProxies) {
         if (INVALID_HOST_PATTERN.matcher(noProxy).find()) {
            throw new MalformedURLException("NO_PROXY URL contains invalid entry: '" + noProxy + "'");
         }

         Optional<String> noProxyIpOrSubnet = extractIpAddressOrSubnet(noProxy);
         if (noProxyIpOrSubnet.isPresent()) {
            if ((new IpAddressMatcher((String)noProxyIpOrSubnet.get())).matches(host)) {
               return true;
            }
         } else if (host.endsWith(noProxy)) {
            return true;
         }
      }

      return false;
   }

   private static Optional extractIpAddressOrSubnet(String ipAddressOrSubnet) {
      Matcher ipMatcher = IP_PATTERN.matcher(ipAddressOrSubnet);
      return ipMatcher.matches() ? Optional.of(ipMatcher.group("ipAddressOrSubnet")) : Optional.empty();
   }

   private static final class HeaderInterceptor implements Interceptor {
      private final Config config;

      private HeaderInterceptor(Config config) {
         this.config = config;
      }

      public void before(BasicBuilder builder, HttpRequest request, Interceptor.RequestTags tags) {
         if (this.config.getCustomHeaders() != null && !this.config.getCustomHeaders().isEmpty()) {
            for(Map.Entry entry : this.config.getCustomHeaders().entrySet()) {
               builder.header((String)entry.getKey(), (String)entry.getValue());
            }
         }

         if (this.config.getUserAgent() != null && !this.config.getUserAgent().isEmpty()) {
            builder.setHeader("User-Agent", this.config.getUserAgent());
         }

      }
   }
}
