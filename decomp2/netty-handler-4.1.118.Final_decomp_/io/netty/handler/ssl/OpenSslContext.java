package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

public abstract class OpenSslContext extends ReferenceCountedOpenSslContext {
   OpenSslContext(Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apnCfg, int mode, Certificate[] keyCertChain, ClientAuth clientAuth, String[] protocols, boolean startTls, String endpointIdentificationAlgorithm, boolean enableOcsp, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      super(ciphers, cipherFilter, toNegotiator(apnCfg), mode, keyCertChain, clientAuth, protocols, startTls, endpointIdentificationAlgorithm, enableOcsp, false, resumptionController, options);
   }

   OpenSslContext(Iterable ciphers, CipherSuiteFilter cipherFilter, OpenSslApplicationProtocolNegotiator apn, int mode, Certificate[] keyCertChain, ClientAuth clientAuth, String[] protocols, boolean startTls, boolean enableOcsp, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      super(ciphers, cipherFilter, apn, mode, keyCertChain, clientAuth, protocols, startTls, (String)null, enableOcsp, false, resumptionController, options);
   }

   final SSLEngine newEngine0(ByteBufAllocator alloc, String peerHost, int peerPort, boolean jdkCompatibilityMode) {
      return new OpenSslEngine(this, alloc, peerHost, peerPort, jdkCompatibilityMode, this.endpointIdentificationAlgorithm);
   }

   protected final void finalize() throws Throwable {
      super.finalize();
      OpenSsl.releaseIfNeeded(this);
   }
}
