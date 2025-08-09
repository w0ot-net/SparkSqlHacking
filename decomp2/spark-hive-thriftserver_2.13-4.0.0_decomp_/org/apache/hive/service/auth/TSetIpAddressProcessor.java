package org.apache.hive.service.auth;

import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSaslClientTransport;
import org.apache.thrift.transport.TSaslServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TSetIpAddressProcessor extends TCLIService.Processor {
   private static final SparkLogger LOGGER = SparkLoggerFactory.getLogger(TSetIpAddressProcessor.class);
   private static final ThreadLocal THREAD_LOCAL_IP_ADDRESS = new ThreadLocal() {
      protected synchronized String initialValue() {
         return null;
      }
   };
   private static final ThreadLocal THREAD_LOCAL_USER_NAME = new ThreadLocal() {
      protected synchronized String initialValue() {
         return null;
      }
   };

   public TSetIpAddressProcessor(TCLIService.Iface iface) {
      super(iface);
   }

   public void process(TProtocol in, TProtocol out) throws TException {
      this.setIpAddress(in);
      this.setUserName(in);

      try {
         super.process(in, out);
      } finally {
         THREAD_LOCAL_USER_NAME.remove();
         THREAD_LOCAL_IP_ADDRESS.remove();
      }

   }

   private void setUserName(TProtocol in) {
      TTransport transport = in.getTransport();
      if (transport instanceof TSaslServerTransport) {
         String userName = ((TSaslServerTransport)transport).getSaslServer().getAuthorizationID();
         THREAD_LOCAL_USER_NAME.set(userName);
      }

   }

   protected void setIpAddress(TProtocol in) {
      TTransport transport = in.getTransport();
      TSocket tSocket = this.getUnderlyingSocketFromTransport(transport);
      if (tSocket == null) {
         LOGGER.warn("Unknown Transport, cannot determine ipAddress");
      } else {
         THREAD_LOCAL_IP_ADDRESS.set(tSocket.getSocket().getInetAddress().getHostAddress());
      }

   }

   private TSocket getUnderlyingSocketFromTransport(TTransport transport) {
      while(true) {
         if (transport != null) {
            if (transport instanceof TSaslServerTransport) {
               transport = ((TSaslServerTransport)transport).getUnderlyingTransport();
            }

            if (transport instanceof TSaslClientTransport) {
               transport = ((TSaslClientTransport)transport).getUnderlyingTransport();
            }

            if (!(transport instanceof TSocket)) {
               continue;
            }

            return (TSocket)transport;
         }

         return null;
      }
   }

   public static String getUserIpAddress() {
      return (String)THREAD_LOCAL_IP_ADDRESS.get();
   }

   public static String getUserName() {
      return (String)THREAD_LOCAL_USER_NAME.get();
   }
}
