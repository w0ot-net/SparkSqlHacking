package org.sparkproject.jetty.server;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.sparkproject.jetty.http.CookieCompliance;
import org.sparkproject.jetty.http.HttpCompliance;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.UriCompliance;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.Jetty;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;

@ManagedObject("HTTP Configuration")
public class HttpConfiguration implements Dumpable {
   public static final String SERVER_VERSION;
   private final List _customizers = new CopyOnWriteArrayList();
   private final Index.Mutable _formEncodedMethods = (new Index.Builder()).caseSensitive(false).mutable().build();
   private int _outputBufferSize = 32768;
   private int _outputAggregationSize;
   private int _requestHeaderSize;
   private int _responseHeaderSize;
   private int _headerCacheSize;
   private boolean _headerCacheCaseSensitive;
   private int _securePort;
   private long _idleTimeout;
   private String _secureScheme;
   private boolean _sendServerVersion;
   private boolean _sendXPoweredBy;
   private boolean _sendDateHeader;
   private boolean _delayDispatchUntilContent;
   private boolean _persistentConnectionsEnabled;
   private int _maxErrorDispatches;
   private boolean _useInputDirectByteBuffers;
   private boolean _useOutputDirectByteBuffers;
   private long _minRequestDataRate;
   private long _minResponseDataRate;
   private HttpCompliance _httpCompliance;
   private UriCompliance _uriCompliance;
   private CookieCompliance _requestCookieCompliance;
   private CookieCompliance _responseCookieCompliance;
   private MultiPartFormDataCompliance _multiPartCompliance;
   private boolean _notifyRemoteAsyncErrors;
   private boolean _relativeRedirectAllowed;
   private HostPort _serverAuthority;
   private SocketAddress _localAddress;

   public HttpConfiguration() {
      this._outputAggregationSize = this._outputBufferSize / 4;
      this._requestHeaderSize = 8192;
      this._responseHeaderSize = 8192;
      this._headerCacheSize = 1024;
      this._headerCacheCaseSensitive = false;
      this._idleTimeout = -1L;
      this._secureScheme = HttpScheme.HTTPS.asString();
      this._sendServerVersion = true;
      this._sendXPoweredBy = false;
      this._sendDateHeader = true;
      this._delayDispatchUntilContent = true;
      this._persistentConnectionsEnabled = true;
      this._maxErrorDispatches = 10;
      this._useInputDirectByteBuffers = true;
      this._useOutputDirectByteBuffers = true;
      this._httpCompliance = HttpCompliance.RFC7230;
      this._uriCompliance = UriCompliance.DEFAULT;
      this._requestCookieCompliance = CookieCompliance.RFC6265;
      this._responseCookieCompliance = CookieCompliance.RFC6265;
      this._multiPartCompliance = MultiPartFormDataCompliance.RFC7578;
      this._notifyRemoteAsyncErrors = true;
      this._formEncodedMethods.put(HttpMethod.POST.asString(), Boolean.TRUE);
      this._formEncodedMethods.put(HttpMethod.PUT.asString(), Boolean.TRUE);
   }

   public HttpConfiguration(HttpConfiguration config) {
      this._outputAggregationSize = this._outputBufferSize / 4;
      this._requestHeaderSize = 8192;
      this._responseHeaderSize = 8192;
      this._headerCacheSize = 1024;
      this._headerCacheCaseSensitive = false;
      this._idleTimeout = -1L;
      this._secureScheme = HttpScheme.HTTPS.asString();
      this._sendServerVersion = true;
      this._sendXPoweredBy = false;
      this._sendDateHeader = true;
      this._delayDispatchUntilContent = true;
      this._persistentConnectionsEnabled = true;
      this._maxErrorDispatches = 10;
      this._useInputDirectByteBuffers = true;
      this._useOutputDirectByteBuffers = true;
      this._httpCompliance = HttpCompliance.RFC7230;
      this._uriCompliance = UriCompliance.DEFAULT;
      this._requestCookieCompliance = CookieCompliance.RFC6265;
      this._responseCookieCompliance = CookieCompliance.RFC6265;
      this._multiPartCompliance = MultiPartFormDataCompliance.RFC7578;
      this._notifyRemoteAsyncErrors = true;
      this._customizers.addAll(config._customizers);

      for(String s : config._formEncodedMethods.keySet()) {
         this._formEncodedMethods.put(s, Boolean.TRUE);
      }

      this._outputBufferSize = config._outputBufferSize;
      this._outputAggregationSize = config._outputAggregationSize;
      this._requestHeaderSize = config._requestHeaderSize;
      this._responseHeaderSize = config._responseHeaderSize;
      this._headerCacheSize = config._headerCacheSize;
      this._headerCacheCaseSensitive = config._headerCacheCaseSensitive;
      this._secureScheme = config._secureScheme;
      this._securePort = config._securePort;
      this._idleTimeout = config._idleTimeout;
      this._sendDateHeader = config._sendDateHeader;
      this._sendServerVersion = config._sendServerVersion;
      this._sendXPoweredBy = config._sendXPoweredBy;
      this._delayDispatchUntilContent = config._delayDispatchUntilContent;
      this._persistentConnectionsEnabled = config._persistentConnectionsEnabled;
      this._maxErrorDispatches = config._maxErrorDispatches;
      this._useInputDirectByteBuffers = config._useInputDirectByteBuffers;
      this._useOutputDirectByteBuffers = config._useOutputDirectByteBuffers;
      this._minRequestDataRate = config._minRequestDataRate;
      this._minResponseDataRate = config._minResponseDataRate;
      this._httpCompliance = config._httpCompliance;
      this._requestCookieCompliance = config._requestCookieCompliance;
      this._responseCookieCompliance = config._responseCookieCompliance;
      this._notifyRemoteAsyncErrors = config._notifyRemoteAsyncErrors;
      this._relativeRedirectAllowed = config._relativeRedirectAllowed;
      this._uriCompliance = config._uriCompliance;
      this._serverAuthority = config._serverAuthority;
      this._localAddress = config._localAddress;
   }

   public void addCustomizer(Customizer customizer) {
      this._customizers.add(customizer);
   }

   public List getCustomizers() {
      return this._customizers;
   }

   public Object getCustomizer(Class type) {
      for(Customizer c : this._customizers) {
         if (type.isAssignableFrom(c.getClass())) {
            return c;
         }
      }

      return null;
   }

   @ManagedAttribute("The size in bytes of the output buffer used to aggregate HTTP output")
   public int getOutputBufferSize() {
      return this._outputBufferSize;
   }

   @ManagedAttribute("The maximum size in bytes for HTTP output to be aggregated")
   public int getOutputAggregationSize() {
      return this._outputAggregationSize;
   }

   @ManagedAttribute("The maximum allowed size in bytes for the HTTP request line and HTTP request headers")
   public int getRequestHeaderSize() {
      return this._requestHeaderSize;
   }

   @ManagedAttribute("The maximum allowed size in bytes for an HTTP response header")
   public int getResponseHeaderSize() {
      return this._responseHeaderSize;
   }

   @ManagedAttribute("The maximum allowed size in Trie nodes for an HTTP header field cache")
   public int getHeaderCacheSize() {
      return this._headerCacheSize;
   }

   @ManagedAttribute("True if the header field cache is case sensitive")
   public boolean isHeaderCacheCaseSensitive() {
      return this._headerCacheCaseSensitive;
   }

   @ManagedAttribute("The port to which Integral or Confidential security constraints are redirected")
   public int getSecurePort() {
      return this._securePort;
   }

   @ManagedAttribute("The scheme with which Integral or Confidential security constraints are redirected")
   public String getSecureScheme() {
      return this._secureScheme;
   }

   @ManagedAttribute("Whether persistent connections are enabled")
   public boolean isPersistentConnectionsEnabled() {
      return this._persistentConnectionsEnabled;
   }

   @ManagedAttribute("The idle timeout in ms for I/O operations during the handling of an HTTP request")
   public long getIdleTimeout() {
      return this._idleTimeout;
   }

   public void setIdleTimeout(long timeoutMs) {
      this._idleTimeout = timeoutMs;
   }

   public void setPersistentConnectionsEnabled(boolean persistentConnectionsEnabled) {
      this._persistentConnectionsEnabled = persistentConnectionsEnabled;
   }

   public void setSendServerVersion(boolean sendServerVersion) {
      this._sendServerVersion = sendServerVersion;
   }

   @ManagedAttribute("Whether to send the Server header in responses")
   public boolean getSendServerVersion() {
      return this._sendServerVersion;
   }

   public void writePoweredBy(Appendable out, String preamble, String postamble) throws IOException {
      if (this.getSendServerVersion()) {
         if (preamble != null) {
            out.append(preamble);
         }

         out.append(Jetty.POWERED_BY);
         if (postamble != null) {
            out.append(postamble);
         }
      }

   }

   public void setSendXPoweredBy(boolean sendXPoweredBy) {
      this._sendXPoweredBy = sendXPoweredBy;
   }

   @ManagedAttribute("Whether to send the X-Powered-By header in responses")
   public boolean getSendXPoweredBy() {
      return this._sendXPoweredBy;
   }

   public void setSendDateHeader(boolean sendDateHeader) {
      this._sendDateHeader = sendDateHeader;
   }

   @ManagedAttribute("Whether to send the Date header in responses")
   public boolean getSendDateHeader() {
      return this._sendDateHeader;
   }

   public void setDelayDispatchUntilContent(boolean delay) {
      this._delayDispatchUntilContent = delay;
   }

   @ManagedAttribute("Whether to delay the application dispatch until content is available")
   public boolean isDelayDispatchUntilContent() {
      return this._delayDispatchUntilContent;
   }

   public void setUseInputDirectByteBuffers(boolean useInputDirectByteBuffers) {
      this._useInputDirectByteBuffers = useInputDirectByteBuffers;
   }

   @ManagedAttribute("Whether to use direct ByteBuffers for reading")
   public boolean isUseInputDirectByteBuffers() {
      return this._useInputDirectByteBuffers;
   }

   public void setUseOutputDirectByteBuffers(boolean useOutputDirectByteBuffers) {
      this._useOutputDirectByteBuffers = useOutputDirectByteBuffers;
   }

   @ManagedAttribute("Whether to use direct ByteBuffers for writing")
   public boolean isUseOutputDirectByteBuffers() {
      return this._useOutputDirectByteBuffers;
   }

   public void setCustomizers(List customizers) {
      this._customizers.clear();
      this._customizers.addAll(customizers);
   }

   public void setOutputBufferSize(int outputBufferSize) {
      this._outputBufferSize = outputBufferSize;
      this.setOutputAggregationSize(outputBufferSize / 4);
   }

   public void setOutputAggregationSize(int outputAggregationSize) {
      this._outputAggregationSize = outputAggregationSize;
   }

   public void setRequestHeaderSize(int requestHeaderSize) {
      this._requestHeaderSize = requestHeaderSize;
   }

   public void setResponseHeaderSize(int responseHeaderSize) {
      this._responseHeaderSize = responseHeaderSize;
   }

   public void setHeaderCacheSize(int headerCacheSize) {
      this._headerCacheSize = headerCacheSize;
   }

   public void setHeaderCacheCaseSensitive(boolean headerCacheCaseSensitive) {
      this._headerCacheCaseSensitive = headerCacheCaseSensitive;
   }

   public void setSecurePort(int securePort) {
      this._securePort = securePort;
   }

   public void setSecureScheme(String secureScheme) {
      this._secureScheme = secureScheme;
   }

   public void setFormEncodedMethods(String... methods) {
      this._formEncodedMethods.clear();

      for(String method : methods) {
         this.addFormEncodedMethod(method);
      }

   }

   public Set getFormEncodedMethods() {
      return this._formEncodedMethods.keySet();
   }

   public void addFormEncodedMethod(String method) {
      this._formEncodedMethods.put(method, Boolean.TRUE);
   }

   public boolean isFormEncodedMethod(String method) {
      return this._formEncodedMethods.get(method) != null;
   }

   @ManagedAttribute("The maximum ERROR dispatches for a request for loop prevention (default 10)")
   public int getMaxErrorDispatches() {
      return this._maxErrorDispatches;
   }

   public void setMaxErrorDispatches(int max) {
      this._maxErrorDispatches = max;
   }

   @ManagedAttribute("The minimum request content data rate in bytes per second")
   public long getMinRequestDataRate() {
      return this._minRequestDataRate;
   }

   public void setMinRequestDataRate(long bytesPerSecond) {
      this._minRequestDataRate = bytesPerSecond;
   }

   @ManagedAttribute("The minimum response content data rate in bytes per second")
   public long getMinResponseDataRate() {
      return this._minResponseDataRate;
   }

   public void setMinResponseDataRate(long bytesPerSecond) {
      this._minResponseDataRate = bytesPerSecond;
   }

   public HttpCompliance getHttpCompliance() {
      return this._httpCompliance;
   }

   public void setHttpCompliance(HttpCompliance httpCompliance) {
      this._httpCompliance = httpCompliance;
   }

   public UriCompliance getUriCompliance() {
      return this._uriCompliance;
   }

   public void setUriCompliance(UriCompliance uriCompliance) {
      this._uriCompliance = uriCompliance;
   }

   public CookieCompliance getRequestCookieCompliance() {
      return this._requestCookieCompliance;
   }

   public void setRequestCookieCompliance(CookieCompliance cookieCompliance) {
      this._requestCookieCompliance = cookieCompliance == null ? CookieCompliance.RFC6265 : cookieCompliance;
   }

   public CookieCompliance getResponseCookieCompliance() {
      return this._responseCookieCompliance;
   }

   public void setResponseCookieCompliance(CookieCompliance cookieCompliance) {
      this._responseCookieCompliance = cookieCompliance == null ? CookieCompliance.RFC6265 : cookieCompliance;
   }

   public void setMultiPartFormDataCompliance(MultiPartFormDataCompliance multiPartCompliance) {
      this._multiPartCompliance = multiPartCompliance == null ? MultiPartFormDataCompliance.RFC7578 : multiPartCompliance;
   }

   public MultiPartFormDataCompliance getMultipartFormDataCompliance() {
      return this._multiPartCompliance;
   }

   public void setNotifyRemoteAsyncErrors(boolean notifyRemoteAsyncErrors) {
      this._notifyRemoteAsyncErrors = notifyRemoteAsyncErrors;
   }

   @ManagedAttribute("Whether remote errors, when detected, are notified to async applications")
   public boolean isNotifyRemoteAsyncErrors() {
      return this._notifyRemoteAsyncErrors;
   }

   public void setRelativeRedirectAllowed(boolean allowed) {
      this._relativeRedirectAllowed = allowed;
   }

   @ManagedAttribute("Whether relative redirection locations are allowed")
   public boolean isRelativeRedirectAllowed() {
      return this._relativeRedirectAllowed;
   }

   @ManagedAttribute("Local SocketAddress override")
   public SocketAddress getLocalAddress() {
      return this._localAddress;
   }

   public void setLocalAddress(SocketAddress localAddress) {
      this._localAddress = localAddress;
   }

   @ManagedAttribute("The server authority if none provided by requests")
   public HostPort getServerAuthority() {
      return this._serverAuthority;
   }

   public void setServerAuthority(HostPort authority) {
      if (authority == null) {
         this._serverAuthority = null;
      } else {
         if (!authority.hasHost()) {
            throw new IllegalStateException("Server Authority must have host declared");
         }

         this._serverAuthority = authority;
      }

   }

   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this, new DumpableCollection("customizers", this._customizers), new DumpableCollection("formEncodedMethods", this._formEncodedMethods.keySet()), "outputBufferSize=" + this._outputBufferSize, "outputAggregationSize=" + this._outputAggregationSize, "requestHeaderSize=" + this._requestHeaderSize, "responseHeaderSize=" + this._responseHeaderSize, "headerCacheSize=" + this._headerCacheSize, "secureScheme=" + this._secureScheme, "securePort=" + this._securePort, "idleTimeout=" + this._idleTimeout, "sendDateHeader=" + this._sendDateHeader, "sendServerVersion=" + this._sendServerVersion, "sendXPoweredBy=" + this._sendXPoweredBy, "delayDispatchUntilContent=" + this._delayDispatchUntilContent, "persistentConnectionsEnabled=" + this._persistentConnectionsEnabled, "maxErrorDispatches=" + this._maxErrorDispatches, "minRequestDataRate=" + this._minRequestDataRate, "minResponseDataRate=" + this._minResponseDataRate, "requestCookieCompliance=" + String.valueOf(this._requestCookieCompliance), "responseCookieCompliance=" + String.valueOf(this._responseCookieCompliance), "notifyRemoteAsyncErrors=" + this._notifyRemoteAsyncErrors, "relativeRedirectAllowed=" + this._relativeRedirectAllowed);
   }

   public String toString() {
      return String.format("%s@%x{%d/%d,%d/%d,%s://:%d,%s}", this.getClass().getSimpleName(), this.hashCode(), this._outputBufferSize, this._outputAggregationSize, this._requestHeaderSize, this._responseHeaderSize, this._secureScheme, this._securePort, this._customizers);
   }

   static {
      SERVER_VERSION = "Jetty(" + Jetty.VERSION + ")";
   }

   public interface ConnectionFactory {
      HttpConfiguration getHttpConfiguration();
   }

   public interface Customizer {
      void customize(Connector var1, HttpConfiguration var2, Request var3);
   }
}
