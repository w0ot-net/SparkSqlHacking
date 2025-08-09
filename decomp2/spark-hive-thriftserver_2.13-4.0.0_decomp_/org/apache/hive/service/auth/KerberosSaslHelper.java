package org.apache.hive.service.auth;

import java.io.IOException;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.SaslException;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.transport.TSaslClientTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public final class KerberosSaslHelper {
   public static TProcessorFactory getKerberosProcessorFactory(HadoopThriftAuthBridge.Server saslServer, ThriftCLIService service) {
      return new CLIServiceProcessorFactory(saslServer, service);
   }

   public static TTransport getKerberosTransport(String principal, String host, TTransport underlyingTransport, Map saslProps, boolean assumeSubject) throws SaslException {
      try {
         String[] names = principal.split("[/@]");
         if (names.length != 3) {
            throw new IllegalArgumentException("Kerberos principal should have 3 parts: " + principal);
         } else if (assumeSubject) {
            return createSubjectAssumedTransport(principal, underlyingTransport, saslProps);
         } else {
            HadoopThriftAuthBridge.Client authBridge = ShimLoader.getHadoopThriftAuthBridge().createClientWithConf("kerberos");
            return authBridge.createClientTransport(principal, host, "KERBEROS", (String)null, underlyingTransport, saslProps);
         }
      } catch (IOException e) {
         throw new SaslException("Failed to open client transport", e);
      }
   }

   public static TTransport createSubjectAssumedTransport(String principal, TTransport underlyingTransport, Map saslProps) throws IOException {
      String[] names = principal.split("[/@]");

      try {
         TTransport saslTransport = new TSaslClientTransport("GSSAPI", (String)null, names[0], names[1], saslProps, (CallbackHandler)null, underlyingTransport);
         return new TSubjectAssumingTransport(saslTransport);
      } catch (TTransportException | SaslException se) {
         throw new IOException("Could not instantiate transport", se);
      }
   }

   public static TTransport getTokenTransport(String tokenStr, String host, TTransport underlyingTransport, Map saslProps) throws SaslException {
      HadoopThriftAuthBridge.Client authBridge = ShimLoader.getHadoopThriftAuthBridge().createClientWithConf("kerberos");

      try {
         return authBridge.createClientTransport((String)null, host, "DIGEST", tokenStr, underlyingTransport, saslProps);
      } catch (IOException e) {
         throw new SaslException("Failed to open client transport", e);
      }
   }

   private KerberosSaslHelper() {
      throw new UnsupportedOperationException("Can't initialize class");
   }

   private static class CLIServiceProcessorFactory extends TProcessorFactory {
      private final ThriftCLIService service;
      private final HadoopThriftAuthBridge.Server saslServer;

      CLIServiceProcessorFactory(HadoopThriftAuthBridge.Server saslServer, ThriftCLIService service) {
         super((TProcessor)null);
         this.service = service;
         this.saslServer = saslServer;
      }

      public TProcessor getProcessor(TTransport trans) {
         TProcessor sqlProcessor = new TCLIService.Processor(this.service);
         return this.saslServer.wrapNonAssumingProcessor(sqlProcessor);
      }
   }
}
