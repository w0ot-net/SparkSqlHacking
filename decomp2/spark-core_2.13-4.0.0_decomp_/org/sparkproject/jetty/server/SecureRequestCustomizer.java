package org.sparkproject.jetty.server;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.ssl.SslConnection;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.ssl.X509;

public class SecureRequestCustomizer implements HttpConfiguration.Customizer {
   private static final Logger LOG = LoggerFactory.getLogger(SecureRequestCustomizer.class);
   public static final String JAKARTA_SERVLET_REQUEST_X_509_CERTIFICATE = "jakarta.servlet.request.X509Certificate";
   public static final String JAKARTA_SERVLET_REQUEST_CIPHER_SUITE = "jakarta.servlet.request.cipher_suite";
   public static final String JAKARTA_SERVLET_REQUEST_KEY_SIZE = "jakarta.servlet.request.key_size";
   public static final String JAKARTA_SERVLET_REQUEST_SSL_SESSION_ID = "jakarta.servlet.request.ssl_session_id";
   public static final String X509_CERT = "org.sparkproject.jetty.server.x509_cert";
   private String sslSessionAttribute;
   private boolean _sniRequired;
   private boolean _sniHostCheck;
   private long _stsMaxAge;
   private boolean _stsIncludeSubDomains;
   private HttpField _stsField;

   public SecureRequestCustomizer() {
      this(true);
   }

   public SecureRequestCustomizer(@Name("sniHostCheck") boolean sniHostCheck) {
      this(sniHostCheck, -1L, false);
   }

   public SecureRequestCustomizer(@Name("sniHostCheck") boolean sniHostCheck, @Name("stsMaxAgeSeconds") long stsMaxAgeSeconds, @Name("stsIncludeSubdomains") boolean stsIncludeSubdomains) {
      this(false, sniHostCheck, stsMaxAgeSeconds, stsIncludeSubdomains);
   }

   public SecureRequestCustomizer(@Name("sniRequired") boolean sniRequired, @Name("sniHostCheck") boolean sniHostCheck, @Name("stsMaxAgeSeconds") long stsMaxAgeSeconds, @Name("stsIncludeSubdomains") boolean stsIncludeSubdomains) {
      this.sslSessionAttribute = "org.sparkproject.jetty.servlet.request.ssl_session";
      this._sniRequired = sniRequired;
      this._sniHostCheck = sniHostCheck;
      this._stsMaxAge = stsMaxAgeSeconds;
      this._stsIncludeSubDomains = stsIncludeSubdomains;
      this.formatSTS();
   }

   public boolean isSniHostCheck() {
      return this._sniHostCheck;
   }

   public void setSniHostCheck(boolean sniHostCheck) {
      this._sniHostCheck = sniHostCheck;
   }

   public boolean isSniRequired() {
      return this._sniRequired;
   }

   public void setSniRequired(boolean sniRequired) {
      this._sniRequired = sniRequired;
   }

   public long getStsMaxAge() {
      return this._stsMaxAge;
   }

   public void setStsMaxAge(long stsMaxAgeSeconds) {
      this._stsMaxAge = stsMaxAgeSeconds;
      this.formatSTS();
   }

   public void setStsMaxAge(long period, TimeUnit units) {
      this._stsMaxAge = units.toSeconds(period);
      this.formatSTS();
   }

   public boolean isStsIncludeSubDomains() {
      return this._stsIncludeSubDomains;
   }

   public void setStsIncludeSubDomains(boolean stsIncludeSubDomains) {
      this._stsIncludeSubDomains = stsIncludeSubDomains;
      this.formatSTS();
   }

   private void formatSTS() {
      if (this._stsMaxAge < 0L) {
         this._stsField = null;
      } else {
         this._stsField = new PreEncodedHttpField(HttpHeader.STRICT_TRANSPORT_SECURITY, String.format("max-age=%d%s", this._stsMaxAge, this._stsIncludeSubDomains ? "; includeSubDomains" : ""));
      }

   }

   public void customize(Connector connector, HttpConfiguration channelConfig, Request request) {
      EndPoint endp = request.getHttpChannel().getEndPoint();
      if (endp instanceof SslConnection.DecryptedEndPoint) {
         SslConnection.DecryptedEndPoint sslEndp = (SslConnection.DecryptedEndPoint)endp;
         SslConnection sslConnection = sslEndp.getSslConnection();
         SSLEngine sslEngine = sslConnection.getSSLEngine();
         this.customize(sslEngine, request);
         request.setHttpURI(HttpURI.build(request.getHttpURI()).scheme(HttpScheme.HTTPS));
      } else if (endp instanceof ProxyConnectionFactory.ProxyEndPoint) {
         ProxyConnectionFactory.ProxyEndPoint proxy = (ProxyConnectionFactory.ProxyEndPoint)endp;
         if (request.getHttpURI().getScheme() == null && proxy.getAttribute("TLS_VERSION") != null) {
            request.setHttpURI(HttpURI.build(request.getHttpURI()).scheme(HttpScheme.HTTPS));
         }
      }

      if (HttpScheme.HTTPS.is(request.getScheme())) {
         this.customizeSecure(request);
      }

   }

   protected void customize(SSLEngine sslEngine, Request request) {
      SSLSession sslSession = sslEngine.getSession();
      if (this.isSniRequired() || this.isSniHostCheck()) {
         String sniHost = (String)sslSession.getValue("org.sparkproject.jetty.util.ssl.sniHost");
         X509 x509 = (X509)sslSession.getValue("org.sparkproject.jetty.server.x509_cert");
         if (x509 == null) {
            Certificate[] certificates = sslSession.getLocalCertificates();
            if (certificates == null || certificates.length == 0 || !(certificates[0] instanceof X509Certificate)) {
               throw new BadMessageException(400, "Invalid SNI");
            }

            x509 = new X509((String)null, (X509Certificate)certificates[0]);
            sslSession.putValue("org.sparkproject.jetty.server.x509_cert", x509);
         }

         String serverName = request.getServerName();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Host={}, SNI={}, SNI Certificate={}", new Object[]{serverName, sniHost, x509});
         }

         if (this.isSniRequired() && (sniHost == null || !x509.matches(sniHost))) {
            throw new BadMessageException(400, "Invalid SNI");
         }

         if (this.isSniHostCheck() && !x509.matches(serverName)) {
            throw new BadMessageException(400, "Invalid SNI");
         }
      }

      request.setAttributes(new SslAttributes(request, sslSession));
   }

   protected void customizeSecure(Request request) {
      request.setSecure(true);
      if (this._stsField != null) {
         request.getResponse().getHttpFields().add(this._stsField);
      }

   }

   private X509Certificate[] getCertChain(Connector connector, SSLSession sslSession) {
      SslConnectionFactory sslConnectionFactory = (SslConnectionFactory)connector.getConnectionFactory(SslConnectionFactory.class);
      if (sslConnectionFactory != null) {
         SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
         if (sslContextFactory != null) {
            return sslContextFactory.getX509CertChain(sslSession);
         }
      }

      return SslContextFactory.getCertChain(sslSession);
   }

   public void setSslSessionAttribute(String attribute) {
      this.sslSessionAttribute = attribute;
   }

   public String getSslSessionAttribute() {
      return this.sslSessionAttribute;
   }

   public String toString() {
      return String.format("%s@%x", this.getClass().getSimpleName(), this.hashCode());
   }

   private class SslAttributes extends Attributes.Wrapper {
      private final Request _request;
      private final SSLSession _session;
      private X509Certificate[] _certs;
      private String _cipherSuite;
      private Integer _keySize;
      private String _sessionId;
      private String _sessionAttribute;

      private SslAttributes(Request request, SSLSession sslSession) {
         super(request.getAttributes());
         this._request = request;
         this._session = sslSession;

         try {
            SslSessionData sslSessionData = this.getSslSessionData();
            this._certs = sslSessionData.getCerts();
            this._cipherSuite = this._session.getCipherSuite();
            this._keySize = sslSessionData.getKeySize();
            this._sessionId = sslSessionData.getIdStr();
            this._sessionAttribute = SecureRequestCustomizer.this.getSslSessionAttribute();
         } catch (Exception e) {
            SecureRequestCustomizer.LOG.warn("Unable to get secure details ", e);
         }

      }

      public Object getAttribute(String name) {
         switch (name) {
            case "jakarta.servlet.request.X509Certificate":
               return this._certs;
            case "jakarta.servlet.request.cipher_suite":
               return this._cipherSuite;
            case "jakarta.servlet.request.key_size":
               return this._keySize;
            case "jakarta.servlet.request.ssl_session_id":
               return this._sessionId;
            default:
               return !StringUtil.isEmpty(this._sessionAttribute) && this._sessionAttribute.equals(name) ? this._session : this._attributes.getAttribute(name);
         }
      }

      private SslSessionData getSslSessionData() {
         String key = SslSessionData.class.getName();
         SslSessionData sslSessionData = (SslSessionData)this._session.getValue(key);
         if (sslSessionData == null) {
            String cipherSuite = this._session.getCipherSuite();
            int keySize = SslContextFactory.deduceKeyLength(cipherSuite);
            X509Certificate[] certs = SecureRequestCustomizer.this.getCertChain(this._request.getHttpChannel().getConnector(), this._session);
            byte[] bytes = this._session.getId();
            String idStr = TypeUtil.toHexString(bytes);
            sslSessionData = new SslSessionData(keySize, certs, idStr);
            this._session.putValue(key, sslSessionData);
         }

         return sslSessionData;
      }

      public Set getAttributeNameSet() {
         Set<String> names = new HashSet(this._attributes.getAttributeNameSet());
         names.remove("jakarta.servlet.request.X509Certificate");
         names.remove("jakarta.servlet.request.cipher_suite");
         names.remove("jakarta.servlet.request.key_size");
         names.remove("jakarta.servlet.request.ssl_session_id");
         if (this._certs != null) {
            names.add("jakarta.servlet.request.X509Certificate");
         }

         if (this._cipherSuite != null) {
            names.add("jakarta.servlet.request.cipher_suite");
         }

         if (this._keySize != null) {
            names.add("jakarta.servlet.request.key_size");
         }

         if (this._sessionId != null) {
            names.add("jakarta.servlet.request.ssl_session_id");
         }

         if (!StringUtil.isEmpty(this._sessionAttribute)) {
            names.add(this._sessionAttribute);
         }

         return names;
      }
   }

   private static class SslSessionData {
      private final Integer _keySize;
      private final X509Certificate[] _certs;
      private final String _idStr;

      private SslSessionData(Integer keySize, X509Certificate[] certs, String idStr) {
         this._keySize = keySize;
         this._certs = certs;
         this._idStr = idStr;
      }

      private Integer getKeySize() {
         return this._keySize;
      }

      private X509Certificate[] getCerts() {
         return this._certs;
      }

      private String getIdStr() {
         return this._idStr;
      }
   }
}
