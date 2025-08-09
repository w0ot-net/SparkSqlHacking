package org.apache.hadoop.hive.common.auth;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveAuthUtils {
   private static final Logger LOG = LoggerFactory.getLogger(HiveAuthUtils.class);

   public static TTransport getSocketTransport(String host, int port, int loginTimeout) throws TTransportException {
      return new TSocket(new TConfiguration(), host, port, loginTimeout);
   }

   public static TTransport getSSLSocket(String host, int port, int loginTimeout) throws TTransportException {
      TSocket tSSLSocket = TSSLTransportFactory.getClientSocket(host, port, loginTimeout);
      return getSSLSocketWithHttps(tSSLSocket);
   }

   public static TTransport getSSLSocket(String host, int port, int loginTimeout, String trustStorePath, String trustStorePassWord) throws TTransportException {
      return getSSLSocket(host, port, loginTimeout, loginTimeout, trustStorePath, trustStorePassWord);
   }

   public static TTransport getSSLSocket(String host, int port, int socketTimeout, int connectionTimeout, String trustStorePath, String trustStorePassWord) throws TTransportException {
      TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
      params.setTrustStore(trustStorePath, trustStorePassWord);
      params.requireClientAuth(true);
      TSocket tSSLSocket = TSSLTransportFactory.getClientSocket(host, port, socketTimeout, params);
      tSSLSocket.setConnectTimeout(connectionTimeout);
      return getSSLSocketWithHttps(tSSLSocket);
   }

   private static TSocket getSSLSocketWithHttps(TSocket tSSLSocket) throws TTransportException {
      SSLSocket sslSocket = (SSLSocket)tSSLSocket.getSocket();
      SSLParameters sslParams = sslSocket.getSSLParameters();
      if (sslSocket.getLocalAddress().getHostAddress().equals("127.0.0.1")) {
         sslParams.setEndpointIdentificationAlgorithm((String)null);
      } else {
         sslParams.setEndpointIdentificationAlgorithm("HTTPS");
      }

      sslSocket.setSSLParameters(sslParams);
      return new TSocket(sslSocket);
   }

   public static TServerSocket getServerSocket(String hiveHost, int portNum) throws TTransportException {
      InetSocketAddress serverAddress;
      if (hiveHost != null && !hiveHost.isEmpty()) {
         serverAddress = new InetSocketAddress(hiveHost, portNum);
      } else {
         serverAddress = new InetSocketAddress(portNum);
      }

      return new TServerSocket(serverAddress);
   }

   public static TServerSocket getServerSSLSocket(String hiveHost, int portNum, String keyStorePath, String keyStorePassWord, List sslVersionBlacklist) throws TTransportException, UnknownHostException {
      TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
      params.setKeyStore(keyStorePath, keyStorePassWord);
      InetSocketAddress serverAddress;
      if (hiveHost != null && !hiveHost.isEmpty()) {
         serverAddress = new InetSocketAddress(hiveHost, portNum);
      } else {
         serverAddress = new InetSocketAddress(portNum);
      }

      TServerSocket thriftServerSocket = TSSLTransportFactory.getServerSocket(portNum, 0, serverAddress.getAddress(), params);
      if (thriftServerSocket.getServerSocket() instanceof SSLServerSocket) {
         List<String> sslVersionBlacklistLocal = new ArrayList();

         for(String sslVersion : sslVersionBlacklist) {
            sslVersionBlacklistLocal.add(sslVersion.trim().toLowerCase());
         }

         SSLServerSocket sslServerSocket = (SSLServerSocket)thriftServerSocket.getServerSocket();
         List<String> enabledProtocols = new ArrayList();

         for(String protocol : sslServerSocket.getEnabledProtocols()) {
            if (sslVersionBlacklistLocal.contains(protocol.toLowerCase())) {
               LOG.debug("Disabling SSL Protocol: " + protocol);
            } else {
               enabledProtocols.add(protocol);
            }
         }

         sslServerSocket.setEnabledProtocols((String[])enabledProtocols.toArray(new String[0]));
         LOG.info("SSL Server Socket Enabled Protocols: " + Arrays.toString(sslServerSocket.getEnabledProtocols()));
      }

      return thriftServerSocket;
   }
}
