package io.netty.internal.tcnative;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class SSLContext {
   private static final int MAX_ALPN_NPN_PROTO_SIZE = 65535;

   private SSLContext() {
   }

   public static native long make(int var0, int var1) throws Exception;

   public static native int free(long var0);

   public static native void setContextId(long var0, String var2);

   public static native void setOptions(long var0, int var2);

   public static native int getOptions(long var0);

   public static native void clearOptions(long var0, int var2);

   /** @deprecated */
   @Deprecated
   public static boolean setCipherSuite(long ctx, String ciphers) throws Exception {
      return setCipherSuite(ctx, ciphers, false);
   }

   public static native boolean setCipherSuite(long var0, String var2, boolean var3) throws Exception;

   public static native boolean setCertificateChainFile(long var0, String var2, boolean var3);

   public static native boolean setCertificateChainBio(long var0, long var2, boolean var4);

   public static native boolean setCertificate(long var0, String var2, String var3, String var4) throws Exception;

   public static native boolean setCertificateBio(long var0, long var2, long var4, String var6) throws Exception;

   public static native long setSessionCacheSize(long var0, long var2);

   public static native long getSessionCacheSize(long var0);

   public static native long setSessionCacheTimeout(long var0, long var2);

   public static native long getSessionCacheTimeout(long var0);

   public static native long setSessionCacheMode(long var0, long var2);

   public static native long getSessionCacheMode(long var0);

   public static native long sessionAccept(long var0);

   public static native long sessionAcceptGood(long var0);

   public static native long sessionAcceptRenegotiate(long var0);

   public static native long sessionCacheFull(long var0);

   public static native long sessionCbHits(long var0);

   public static native long sessionConnect(long var0);

   public static native long sessionConnectGood(long var0);

   public static native long sessionConnectRenegotiate(long var0);

   public static native long sessionHits(long var0);

   public static native long sessionMisses(long var0);

   public static native long sessionNumber(long var0);

   public static native long sessionTimeouts(long var0);

   public static native long sessionTicketKeyNew(long var0);

   public static native long sessionTicketKeyResume(long var0);

   public static native long sessionTicketKeyRenew(long var0);

   public static native long sessionTicketKeyFail(long var0);

   public static void setSessionTicketKeys(long ctx, SessionTicketKey[] keys) {
      if (keys != null && keys.length != 0) {
         byte[] binaryKeys = new byte[keys.length * 48];

         for(int i = 0; i < keys.length; ++i) {
            SessionTicketKey key = keys[i];
            int dstCurPos = 48 * i;
            System.arraycopy(key.name, 0, binaryKeys, dstCurPos, 16);
            dstCurPos += 16;
            System.arraycopy(key.hmacKey, 0, binaryKeys, dstCurPos, 16);
            dstCurPos += 16;
            System.arraycopy(key.aesKey, 0, binaryKeys, dstCurPos, 16);
         }

         setSessionTicketKeys0(ctx, binaryKeys);
      } else {
         throw new IllegalArgumentException("Length of the keys should be longer than 0.");
      }
   }

   private static native void setSessionTicketKeys0(long var0, byte[] var2);

   public static native boolean setCACertificateBio(long var0, long var2);

   public static native void setVerify(long var0, int var2, int var3);

   public static native void setCertVerifyCallback(long var0, CertificateVerifier var2);

   /** @deprecated */
   @Deprecated
   public static native void setCertRequestedCallback(long var0, CertificateRequestedCallback var2);

   public static native void setCertificateCallback(long var0, CertificateCallback var2);

   public static native void setSniHostnameMatcher(long var0, SniHostNameMatcher var2);

   public static native boolean setKeyLogCallback(long var0, KeyLogCallback var2);

   private static byte[] protocolsToWireFormat(String[] protocols) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();

      byte[] var16;
      try {
         for(String p : protocols) {
            byte[] bytes = p.getBytes(StandardCharsets.US_ASCII);
            if (bytes.length <= 65535) {
               out.write(bytes.length);
               out.write(bytes);
            }
         }

         var16 = out.toByteArray();
      } catch (IOException e) {
         throw new IllegalStateException(e);
      } finally {
         try {
            out.close();
         } catch (IOException var13) {
         }

      }

      return var16;
   }

   public static void setNpnProtos(long ctx, String[] nextProtos, int selectorFailureBehavior) {
      setNpnProtos0(ctx, protocolsToWireFormat(nextProtos), selectorFailureBehavior);
   }

   private static native void setNpnProtos0(long var0, byte[] var2, int var3);

   public static void setAlpnProtos(long ctx, String[] alpnProtos, int selectorFailureBehavior) {
      setAlpnProtos0(ctx, protocolsToWireFormat(alpnProtos), selectorFailureBehavior);
   }

   private static native void setAlpnProtos0(long var0, byte[] var2, int var3);

   public static native void setTmpDHLength(long var0, int var2);

   public static native boolean setSessionIdContext(long var0, byte[] var2);

   public static native int setMode(long var0, int var2);

   public static native int getMode(long var0);

   public static native void enableOcsp(long var0, boolean var2);

   public static native void disableOcsp(long var0);

   public static native long getSslCtx(long var0);

   public static native void setUseTasks(long var0, boolean var2);

   public static int addCertificateCompressionAlgorithm(long ctx, int direction, CertificateCompressionAlgo algorithm) {
      return addCertificateCompressionAlgorithm0(ctx, direction, algorithm.algorithmId(), algorithm);
   }

   private static native int addCertificateCompressionAlgorithm0(long var0, int var2, int var3, CertificateCompressionAlgo var4);

   public static void setPrivateKeyMethod(long ctx, SSLPrivateKeyMethod method) {
      setPrivateKeyMethod(ctx, (AsyncSSLPrivateKeyMethod)(new AsyncSSLPrivateKeyMethodAdapter(method)));
   }

   public static void setPrivateKeyMethod(long ctx, AsyncSSLPrivateKeyMethod method) {
      setPrivateKeyMethod0(ctx, method);
   }

   private static native void setPrivateKeyMethod0(long var0, AsyncSSLPrivateKeyMethod var2);

   public static native void setSSLSessionCache(long var0, SSLSessionCache var2);

   public static native boolean setNumTickets(long var0, int var2);

   public static boolean setCurvesList(long ctx, String... curves) {
      if (curves == null) {
         throw new NullPointerException("curves");
      } else if (curves.length == 0) {
         throw new IllegalArgumentException();
      } else {
         StringBuilder sb = new StringBuilder();

         for(String curve : curves) {
            sb.append(curve);
            sb.append(':');
         }

         sb.setLength(sb.length() - 1);
         return setCurvesList0(ctx, sb.toString());
      }
   }

   private static native boolean setCurvesList0(long var0, String var2);

   public static native void setMaxCertList(long var0, int var2);
}
