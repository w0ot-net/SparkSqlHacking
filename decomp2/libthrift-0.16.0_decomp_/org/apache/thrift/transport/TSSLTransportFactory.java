package org.apache.thrift.transport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSSLTransportFactory {
   private static final Logger LOGGER = LoggerFactory.getLogger(TSSLTransportFactory.class);

   public static TServerSocket getServerSocket(int port) throws TTransportException {
      return getServerSocket(port, 0);
   }

   public static TServerSocket getServerSocket(int port, int clientTimeout) throws TTransportException {
      return getServerSocket(port, clientTimeout, false, (InetAddress)null);
   }

   public static TServerSocket getServerSocket(int port, int clientTimeout, boolean clientAuth, InetAddress ifAddress) throws TTransportException {
      SSLServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      return createServer(factory, port, clientTimeout, clientAuth, ifAddress, (TSSLTransportParameters)null);
   }

   public static TServerSocket getServerSocket(int port, int clientTimeout, InetAddress ifAddress, TSSLTransportParameters params) throws TTransportException {
      if (params != null && (params.isKeyStoreSet || params.isTrustStoreSet)) {
         SSLContext ctx = createSSLContext(params);
         return createServer(ctx.getServerSocketFactory(), port, clientTimeout, params.clientAuth, ifAddress, params);
      } else {
         throw new TTransportException("Either one of the KeyStore or TrustStore must be set for SSLTransportParameters");
      }
   }

   private static TServerSocket createServer(SSLServerSocketFactory factory, int port, int timeout, boolean clientAuth, InetAddress ifAddress, TSSLTransportParameters params) throws TTransportException {
      try {
         SSLServerSocket serverSocket = (SSLServerSocket)factory.createServerSocket(port, 100, ifAddress);
         serverSocket.setSoTimeout(timeout);
         serverSocket.setNeedClientAuth(clientAuth);
         if (params != null && params.cipherSuites != null) {
            serverSocket.setEnabledCipherSuites(params.cipherSuites);
         }

         return new TServerSocket((TServerSocket.ServerSocketTransportArgs)(new TServerSocket.ServerSocketTransportArgs()).serverSocket(serverSocket).clientTimeout(timeout));
      } catch (Exception e) {
         throw new TTransportException("Could not bind to port " + port, e);
      }
   }

   public static TSocket getClientSocket(String host, int port, int timeout) throws TTransportException {
      SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
      return createClient(factory, host, port, timeout);
   }

   public static TSocket getClientSocket(String host, int port) throws TTransportException {
      return getClientSocket(host, port, 0);
   }

   public static TSocket getClientSocket(String host, int port, int timeout, TSSLTransportParameters params) throws TTransportException {
      if (params != null && (params.isKeyStoreSet || params.isTrustStoreSet)) {
         SSLContext ctx = createSSLContext(params);
         return createClient(ctx.getSocketFactory(), host, port, timeout);
      } else {
         throw new TTransportException(1, "Either one of the KeyStore or TrustStore must be set for SSLTransportParameters");
      }
   }

   private static SSLContext createSSLContext(TSSLTransportParameters params) throws TTransportException {
      InputStream in = null;
      InputStream is = null;

      SSLContext ctx;
      try {
         ctx = SSLContext.getInstance(params.protocol);
         TrustManagerFactory tmf = null;
         KeyManagerFactory kmf = null;
         if (params.isTrustStoreSet) {
            tmf = TrustManagerFactory.getInstance(params.trustManagerType);
            KeyStore ts = KeyStore.getInstance(params.trustStoreType);
            if (params.trustStoreStream != null) {
               in = params.trustStoreStream;
            } else {
               in = getStoreAsStream(params.trustStore);
            }

            ts.load(in, params.trustPass != null ? params.trustPass.toCharArray() : null);
            tmf.init(ts);
         }

         if (params.isKeyStoreSet) {
            kmf = KeyManagerFactory.getInstance(params.keyManagerType);
            KeyStore ks = KeyStore.getInstance(params.keyStoreType);
            if (params.keyStoreStream != null) {
               is = params.keyStoreStream;
            } else {
               is = getStoreAsStream(params.keyStore);
            }

            ks.load(is, params.keyPass.toCharArray());
            kmf.init(ks, params.keyPass.toCharArray());
         }

         if (params.isKeyStoreSet && params.isTrustStoreSet) {
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), (SecureRandom)null);
         } else if (params.isKeyStoreSet) {
            ctx.init(kmf.getKeyManagers(), (TrustManager[])null, (SecureRandom)null);
         } else {
            ctx.init((KeyManager[])null, tmf.getTrustManagers(), (SecureRandom)null);
         }
      } catch (Exception e) {
         throw new TTransportException(1, "Error creating the transport", e);
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException e) {
               LOGGER.warn("Unable to close stream", e);
            }
         }

         if (is != null) {
            try {
               is.close();
            } catch (IOException e) {
               LOGGER.warn("Unable to close stream", e);
            }
         }

      }

      return ctx;
   }

   private static InputStream getStoreAsStream(String store) throws IOException {
      try {
         return new FileInputStream(store);
      } catch (FileNotFoundException var4) {
         InputStream storeStream = null;

         try {
            storeStream = (new URL(store)).openStream();
            if (storeStream != null) {
               return storeStream;
            }
         } catch (MalformedURLException var3) {
         }

         storeStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(store);
         if (storeStream != null) {
            return storeStream;
         } else {
            throw new IOException("Could not load file: " + store);
         }
      }
   }

   private static TSocket createClient(SSLSocketFactory factory, String host, int port, int timeout) throws TTransportException {
      try {
         SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
         socket.setSoTimeout(timeout);
         return new TSocket(socket);
      } catch (TTransportException tte) {
         throw tte;
      } catch (Exception e) {
         throw new TTransportException(1, "Could not connect to " + host + " on port " + port, e);
      }
   }

   public static class TSSLTransportParameters {
      protected String protocol;
      protected String keyStore;
      protected InputStream keyStoreStream;
      protected String keyPass;
      protected String keyManagerType;
      protected String keyStoreType;
      protected String trustStore;
      protected InputStream trustStoreStream;
      protected String trustPass;
      protected String trustManagerType;
      protected String trustStoreType;
      protected String[] cipherSuites;
      protected boolean clientAuth;
      protected boolean isKeyStoreSet;
      protected boolean isTrustStoreSet;

      public TSSLTransportParameters() {
         this.protocol = "TLS";
         this.keyManagerType = KeyManagerFactory.getDefaultAlgorithm();
         this.keyStoreType = "JKS";
         this.trustManagerType = TrustManagerFactory.getDefaultAlgorithm();
         this.trustStoreType = "JKS";
         this.clientAuth = false;
         this.isKeyStoreSet = false;
         this.isTrustStoreSet = false;
      }

      public TSSLTransportParameters(String protocol, String[] cipherSuites) {
         this(protocol, cipherSuites, false);
      }

      public TSSLTransportParameters(String protocol, String[] cipherSuites, boolean clientAuth) {
         this.protocol = "TLS";
         this.keyManagerType = KeyManagerFactory.getDefaultAlgorithm();
         this.keyStoreType = "JKS";
         this.trustManagerType = TrustManagerFactory.getDefaultAlgorithm();
         this.trustStoreType = "JKS";
         this.clientAuth = false;
         this.isKeyStoreSet = false;
         this.isTrustStoreSet = false;
         if (protocol != null) {
            this.protocol = protocol;
         }

         this.cipherSuites = cipherSuites != null ? (String[])Arrays.copyOf(cipherSuites, cipherSuites.length) : null;
         this.clientAuth = clientAuth;
      }

      public void setKeyStore(String keyStore, String keyPass, String keyManagerType, String keyStoreType) {
         this.keyStore = keyStore;
         this.keyPass = keyPass;
         if (keyManagerType != null) {
            this.keyManagerType = keyManagerType;
         }

         if (keyStoreType != null) {
            this.keyStoreType = keyStoreType;
         }

         this.isKeyStoreSet = true;
      }

      public void setKeyStore(InputStream keyStoreStream, String keyPass, String keyManagerType, String keyStoreType) {
         this.keyStoreStream = keyStoreStream;
         this.setKeyStore("", keyPass, keyManagerType, keyStoreType);
      }

      public void setKeyStore(String keyStore, String keyPass) {
         this.setKeyStore((String)keyStore, keyPass, (String)null, (String)null);
      }

      public void setKeyStore(InputStream keyStoreStream, String keyPass) {
         this.setKeyStore((InputStream)keyStoreStream, keyPass, (String)null, (String)null);
      }

      public void setTrustStore(String trustStore, String trustPass, String trustManagerType, String trustStoreType) {
         this.trustStore = trustStore;
         this.trustPass = trustPass;
         if (trustManagerType != null) {
            this.trustManagerType = trustManagerType;
         }

         if (trustStoreType != null) {
            this.trustStoreType = trustStoreType;
         }

         this.isTrustStoreSet = true;
      }

      public void setTrustStore(InputStream trustStoreStream, String trustPass, String trustManagerType, String trustStoreType) {
         this.trustStoreStream = trustStoreStream;
         this.setTrustStore("", trustPass, trustManagerType, trustStoreType);
      }

      public void setTrustStore(String trustStore, String trustPass) {
         this.setTrustStore((String)trustStore, trustPass, (String)null, (String)null);
      }

      public void setTrustStore(InputStream trustStoreStream, String trustPass) {
         this.setTrustStore((InputStream)trustStoreStream, trustPass, (String)null, (String)null);
      }

      public void requireClientAuth(boolean clientAuth) {
         this.clientAuth = clientAuth;
      }
   }
}
