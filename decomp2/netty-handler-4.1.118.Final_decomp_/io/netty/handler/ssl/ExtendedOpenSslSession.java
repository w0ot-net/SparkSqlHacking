package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.SuppressJava6Requirement;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.security.cert.X509Certificate;

@SuppressJava6Requirement(
   reason = "Usage guarded by java version check"
)
abstract class ExtendedOpenSslSession extends ExtendedSSLSession implements OpenSslInternalSession {
   private static final String[] LOCAL_SUPPORTED_SIGNATURE_ALGORITHMS = new String[]{"SHA512withRSA", "SHA512withECDSA", "SHA384withRSA", "SHA384withECDSA", "SHA256withRSA", "SHA256withECDSA", "SHA224withRSA", "SHA224withECDSA", "SHA1withRSA", "SHA1withECDSA", "RSASSA-PSS"};
   private final OpenSslInternalSession wrapped;

   ExtendedOpenSslSession(OpenSslInternalSession wrapped) {
      this.wrapped = wrapped;
   }

   public abstract List getRequestedServerNames();

   public List getStatusResponses() {
      return Collections.emptyList();
   }

   public void prepareHandshake() {
      this.wrapped.prepareHandshake();
   }

   public Map keyValueStorage() {
      return this.wrapped.keyValueStorage();
   }

   public OpenSslSessionId sessionId() {
      return this.wrapped.sessionId();
   }

   public void setSessionDetails(long creationTime, long lastAccessedTime, OpenSslSessionId id, Map keyValueStorage) {
      this.wrapped.setSessionDetails(creationTime, lastAccessedTime, id, keyValueStorage);
   }

   public final void setLocalCertificate(Certificate[] localCertificate) {
      this.wrapped.setLocalCertificate(localCertificate);
   }

   public String[] getPeerSupportedSignatureAlgorithms() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   public final void tryExpandApplicationBufferSize(int packetLengthDataOnly) {
      this.wrapped.tryExpandApplicationBufferSize(packetLengthDataOnly);
   }

   public final String[] getLocalSupportedSignatureAlgorithms() {
      return (String[])LOCAL_SUPPORTED_SIGNATURE_ALGORITHMS.clone();
   }

   public final byte[] getId() {
      return this.wrapped.getId();
   }

   public final OpenSslSessionContext getSessionContext() {
      return this.wrapped.getSessionContext();
   }

   public final long getCreationTime() {
      return this.wrapped.getCreationTime();
   }

   public final long getLastAccessedTime() {
      return this.wrapped.getLastAccessedTime();
   }

   public void setLastAccessedTime(long time) {
      this.wrapped.setLastAccessedTime(time);
   }

   public final void invalidate() {
      this.wrapped.invalidate();
   }

   public final boolean isValid() {
      return this.wrapped.isValid();
   }

   public final void putValue(String name, Object value) {
      if (value instanceof SSLSessionBindingListener) {
         value = new SSLSessionBindingListenerDecorator((SSLSessionBindingListener)value);
      }

      this.wrapped.putValue(name, value);
   }

   public final Object getValue(String s) {
      Object value = this.wrapped.getValue(s);
      return value instanceof SSLSessionBindingListenerDecorator ? ((SSLSessionBindingListenerDecorator)value).delegate : value;
   }

   public final void removeValue(String s) {
      this.wrapped.removeValue(s);
   }

   public final String[] getValueNames() {
      return this.wrapped.getValueNames();
   }

   public final Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
      return this.wrapped.getPeerCertificates();
   }

   public boolean hasPeerCertificates() {
      return this.wrapped.hasPeerCertificates();
   }

   public final Certificate[] getLocalCertificates() {
      return this.wrapped.getLocalCertificates();
   }

   public final X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
      return this.wrapped.getPeerCertificateChain();
   }

   public final Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
      return this.wrapped.getPeerPrincipal();
   }

   public final Principal getLocalPrincipal() {
      return this.wrapped.getLocalPrincipal();
   }

   public final String getCipherSuite() {
      return this.wrapped.getCipherSuite();
   }

   public String getProtocol() {
      return this.wrapped.getProtocol();
   }

   public final String getPeerHost() {
      return this.wrapped.getPeerHost();
   }

   public final int getPeerPort() {
      return this.wrapped.getPeerPort();
   }

   public final int getPacketBufferSize() {
      return this.wrapped.getPacketBufferSize();
   }

   public final int getApplicationBufferSize() {
      return this.wrapped.getApplicationBufferSize();
   }

   public void handshakeFinished(byte[] id, String cipher, String protocol, byte[] peerCertificate, byte[][] peerCertificateChain, long creationTime, long timeout) throws SSLException {
      this.wrapped.handshakeFinished(id, cipher, protocol, peerCertificate, peerCertificateChain, creationTime, timeout);
   }

   public boolean equals(Object o) {
      return this.wrapped.equals(o);
   }

   public int hashCode() {
      return this.wrapped.hashCode();
   }

   public String toString() {
      return "ExtendedOpenSslSession{wrapped=" + this.wrapped + '}';
   }

   private final class SSLSessionBindingListenerDecorator implements SSLSessionBindingListener {
      final SSLSessionBindingListener delegate;

      SSLSessionBindingListenerDecorator(SSLSessionBindingListener delegate) {
         this.delegate = delegate;
      }

      public void valueBound(SSLSessionBindingEvent event) {
         this.delegate.valueBound(new SSLSessionBindingEvent(ExtendedOpenSslSession.this, event.getName()));
      }

      public void valueUnbound(SSLSessionBindingEvent event) {
         this.delegate.valueUnbound(new SSLSessionBindingEvent(ExtendedOpenSslSession.this, event.getName()));
      }
   }
}
