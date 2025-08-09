package org.apache.ivy.util.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProxySelector;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Lookup;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.HostUtil;
import org.apache.ivy.util.Message;

public class HttpClientHandler extends AbstractURLHandler implements TimeoutConstrainedURLHandler, AutoCloseable {
   private static final SimpleDateFormat LAST_MODIFIED_FORMAT;
   static final HttpClientHandler DELETE_ON_EXIT_INSTANCE;
   private final CloseableHttpClient httpClient = this.buildUnderlyingClient();

   private CloseableHttpClient buildUnderlyingClient() {
      return HttpClients.custom().setConnectionManager(createConnectionManager()).setRoutePlanner(createProxyRoutePlanner()).setUserAgent(this.getUserAgent()).setDefaultAuthSchemeRegistry(createAuthSchemeRegistry()).setDefaultCredentialsProvider(new IvyCredentialsProvider()).build();
   }

   private static HttpRoutePlanner createProxyRoutePlanner() {
      Message.verbose("Using JRE standard ProxySelector for configuring HTTP proxy");
      return new SystemDefaultRoutePlanner(ProxySelector.getDefault());
   }

   private static Lookup createAuthSchemeRegistry() {
      return RegistryBuilder.create().register("Digest", new DigestSchemeFactory()).register("Basic", new BasicSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).build();
   }

   private static HttpClientConnectionManager createConnectionManager() {
      return new PoolingHttpClientConnectionManager();
   }

   private static List getAuthSchemePreferredOrder() {
      return Arrays.asList("Digest", "Basic", "NTLM");
   }

   public InputStream openStream(URL url) throws IOException {
      return this.openStream(url, (TimeoutConstraint)null);
   }

   public InputStream openStream(URL url, TimeoutConstraint timeoutConstraint) throws IOException {
      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      CloseableHttpResponse response = this.doGet(url, connectionTimeout, readTimeout);
      this.requireSuccessStatus("GET", url, response);
      Header encoding = this.getContentEncoding(response);
      return this.getDecodingInputStream(encoding == null ? null : encoding.getValue(), response.getEntity().getContent());
   }

   public void download(URL src, File dest, CopyProgressListener l) throws IOException {
      this.download(src, dest, l, (TimeoutConstraint)null);
   }

   public void download(URL src, File dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      CloseableHttpResponse response = this.doGet(src, connectionTimeout, readTimeout);
      Throwable var8 = null;

      try {
         this.requireSuccessStatus("GET", src, response);
         Header encoding = this.getContentEncoding(response);
         InputStream is = this.getDecodingInputStream(encoding == null ? null : encoding.getValue(), response.getEntity().getContent());
         Throwable var11 = null;

         try {
            FileUtil.copy(is, dest, listener);
         } catch (Throwable var34) {
            var11 = var34;
            throw var34;
         } finally {
            if (is != null) {
               if (var11 != null) {
                  try {
                     is.close();
                  } catch (Throwable var33) {
                     var11.addSuppressed(var33);
                  }
               } else {
                  is.close();
               }
            }

         }

         dest.setLastModified(this.getLastModified(response));
      } catch (Throwable var36) {
         var8 = var36;
         throw var36;
      } finally {
         if (response != null) {
            if (var8 != null) {
               try {
                  response.close();
               } catch (Throwable var32) {
                  var8.addSuppressed(var32);
               }
            } else {
               response.close();
            }
         }

      }

   }

   public void upload(File src, URL dest, CopyProgressListener l) throws IOException {
      this.upload(src, dest, l, (TimeoutConstraint)null);
   }

   public void upload(File src, URL dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setAuthenticationEnabled(this.hasCredentialsConfigured(dest)).setTargetPreferredAuthSchemes(getAuthSchemePreferredOrder()).setProxyPreferredAuthSchemes(getAuthSchemePreferredOrder()).setExpectContinueEnabled(true).build();
      HttpPut put = new HttpPut(this.normalizeToString(dest));
      put.setConfig(requestConfig);
      put.setEntity(new FileEntity(src));
      CloseableHttpResponse response = this.httpClient.execute(put);
      Throwable var10 = null;

      try {
         this.validatePutStatusCode(dest, response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
      } catch (Throwable var19) {
         var10 = var19;
         throw var19;
      } finally {
         if (response != null) {
            if (var10 != null) {
               try {
                  response.close();
               } catch (Throwable var18) {
                  var10.addSuppressed(var18);
               }
            } else {
               response.close();
            }
         }

      }

   }

   public URLHandler.URLInfo getURLInfo(URL url) {
      return this.getURLInfo(url, (TimeoutConstraint)null);
   }

   public URLHandler.URLInfo getURLInfo(URL url, int timeout) {
      return this.getURLInfo(url, createTimeoutConstraints(timeout));
   }

   public boolean isReachable(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).isReachable();
   }

   public long getContentLength(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).getContentLength();
   }

   public long getLastModified(URL url, TimeoutConstraint timeoutConstraint) {
      return this.getURLInfo(url, timeoutConstraint).getLastModified();
   }

   public URLHandler.URLInfo getURLInfo(URL url, TimeoutConstraint timeoutConstraint) {
      int connectionTimeout = timeoutConstraint != null && timeoutConstraint.getConnectionTimeout() >= 0 ? timeoutConstraint.getConnectionTimeout() : 0;
      int readTimeout = timeoutConstraint != null && timeoutConstraint.getReadTimeout() >= 0 ? timeoutConstraint.getReadTimeout() : 0;
      CloseableHttpResponse response = null;

      URLHandler.URLInfo var10;
      try {
         String httpMethod;
         if (this.getRequestMethod() == 2) {
            httpMethod = "HEAD";
            response = this.doHead(url, connectionTimeout, readTimeout);
         } else {
            httpMethod = "GET";
            response = this.doGet(url, connectionTimeout, readTimeout);
         }

         if (!this.checkStatusCode(httpMethod, url, response)) {
            return UNAVAILABLE;
         }

         HttpEntity responseEntity = response.getEntity();
         Charset charSet = ContentType.getOrDefault(responseEntity).getCharset();
         String charSetName = charSet != null ? charSet.name() : null;
         var10 = new URLHandler.URLInfo(true, responseEntity == null ? 0L : responseEntity.getContentLength(), this.getLastModified(response), charSetName);
      } catch (IllegalArgumentException | IOException e) {
         Message.error("HttpClientHandler: " + ((Exception)e).getMessage() + " url=" + url);
         return UNAVAILABLE;
      } finally {
         if (response != null) {
            try {
               response.close();
            } catch (IOException var20) {
            }
         }

      }

      return var10;
   }

   private boolean checkStatusCode(String httpMethod, URL sourceURL, HttpResponse response) {
      int status = response.getStatusLine().getStatusCode();
      if (status == 200) {
         return true;
      } else if ("HEAD".equals(httpMethod) && status == 204) {
         return true;
      } else {
         Message.debug("HTTP response status: " + status + " url=" + sourceURL);
         if (status == 407) {
            Message.warn("Your proxy requires authentication.");
         } else if (String.valueOf(status).startsWith("4")) {
            Message.verbose("CLIENT ERROR: " + response.getStatusLine().getReasonPhrase() + " url=" + sourceURL);
         } else if (String.valueOf(status).startsWith("5")) {
            Message.error("SERVER ERROR: " + response.getStatusLine().getReasonPhrase() + " url=" + sourceURL);
         }

         return false;
      }
   }

   private void requireSuccessStatus(String httpMethod, URL sourceURL, CloseableHttpResponse response) throws IOException {
      if (!this.checkStatusCode(httpMethod, sourceURL, response)) {
         try {
            response.close();
         } catch (Exception e) {
            Message.debug("Could not close the HTTP response for url=" + sourceURL, e);
         }

         throw new IOException("Failed response to request '" + httpMethod + " " + sourceURL + "' " + response.getStatusLine().getStatusCode() + " - '" + response.getStatusLine().getReasonPhrase());
      }
   }

   private Header getContentEncoding(HttpResponse response) {
      return response.getFirstHeader("Content-Encoding");
   }

   private long getLastModified(HttpResponse response) {
      Header header = response.getFirstHeader("last-modified");
      if (header == null) {
         return System.currentTimeMillis();
      } else {
         String lastModified = header.getValue();

         try {
            return LAST_MODIFIED_FORMAT.parse(lastModified).getTime();
         } catch (ParseException var5) {
            return System.currentTimeMillis();
         }
      }
   }

   private CloseableHttpResponse doGet(URL url, int connectionTimeout, int readTimeout) throws IOException {
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setAuthenticationEnabled(this.hasCredentialsConfigured(url)).setTargetPreferredAuthSchemes(getAuthSchemePreferredOrder()).setProxyPreferredAuthSchemes(getAuthSchemePreferredOrder()).build();
      HttpGet httpGet = new HttpGet(this.normalizeToString(url));
      httpGet.setConfig(requestConfig);
      httpGet.addHeader("Accept-Encoding", "gzip,deflate");
      return this.httpClient.execute(httpGet);
   }

   private CloseableHttpResponse doHead(URL url, int connectionTimeout, int readTimeout) throws IOException {
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectionTimeout).setAuthenticationEnabled(this.hasCredentialsConfigured(url)).setTargetPreferredAuthSchemes(getAuthSchemePreferredOrder()).setProxyPreferredAuthSchemes(getAuthSchemePreferredOrder()).build();
      HttpHead httpHead = new HttpHead(this.normalizeToString(url));
      httpHead.setConfig(requestConfig);
      return this.httpClient.execute(httpHead);
   }

   private boolean hasCredentialsConfigured(URL url) {
      return CredentialsStore.INSTANCE.hasCredentials(url.getHost());
   }

   public void close() throws Exception {
      if (this.httpClient != null) {
         this.httpClient.close();
      }

   }

   static {
      LAST_MODIFIED_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
      DELETE_ON_EXIT_INSTANCE = new HttpClientHandler();
      Thread shutdownHook = new Thread(new Runnable() {
         public void run() {
            try {
               HttpClientHandler.DELETE_ON_EXIT_INSTANCE.close();
            } catch (Exception var2) {
            }

         }
      });
      shutdownHook.setName("ivy-httpclient-shutdown-handler");
      shutdownHook.setDaemon(true);
      Runtime.getRuntime().addShutdownHook(shutdownHook);
   }

   private static class IvyCredentialsProvider implements CredentialsProvider {
      private final ConcurrentHashMap cachedCreds;

      private IvyCredentialsProvider() {
         this.cachedCreds = new ConcurrentHashMap();
      }

      public void setCredentials(AuthScope authscope, Credentials credentials) {
         if (authscope == null) {
            throw new IllegalArgumentException("AuthScope cannot be null");
         } else {
            this.cachedCreds.put(authscope, credentials);
         }
      }

      public Credentials getCredentials(AuthScope authscope) {
         if (authscope == null) {
            return null;
         } else {
            String realm = authscope.getRealm();
            String host = authscope.getHost();
            org.apache.ivy.util.Credentials ivyConfiguredCred = CredentialsStore.INSTANCE.getCredentials(realm, host);
            return ivyConfiguredCred == null ? null : createCredentials(ivyConfiguredCred.getUserName(), ivyConfiguredCred.getPasswd());
         }
      }

      public void clear() {
         this.cachedCreds.clear();
      }

      private static Credentials createCredentials(String username, String password) {
         int backslashIndex = username.indexOf(92);
         String user;
         String domain;
         if (backslashIndex >= 0) {
            user = username.substring(backslashIndex + 1);
            domain = username.substring(0, backslashIndex);
         } else {
            user = username;
            domain = System.getProperty("http.auth.ntlm.domain", "");
         }

         return new NTCredentials(user, password, HostUtil.getLocalHostName(), domain);
      }
   }
}
