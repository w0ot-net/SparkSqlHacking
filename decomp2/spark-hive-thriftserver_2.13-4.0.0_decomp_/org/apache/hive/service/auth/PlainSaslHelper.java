package org.apache.hive.service.auth;

import java.io.IOException;
import java.security.Security;
import java.util.HashMap;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.sasl.AuthenticationException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.SaslException;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.transport.TSaslClientTransport;
import org.apache.thrift.transport.TSaslServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public final class PlainSaslHelper {
   public static TProcessorFactory getPlainProcessorFactory(ThriftCLIService service) {
      return new SQLPlainProcessorFactory(service);
   }

   public static TTransportFactory getPlainTransportFactory(String authTypeStr) throws LoginException {
      TSaslServerTransport.Factory saslFactory = new TSaslServerTransport.Factory();

      try {
         saslFactory.addServerDefinition("PLAIN", authTypeStr, (String)null, new HashMap(), new PlainServerCallbackHandler(authTypeStr));
         return saslFactory;
      } catch (AuthenticationException e) {
         throw new LoginException("Error setting callback handler" + String.valueOf(e));
      }
   }

   public static TTransport getPlainTransport(String username, String password, TTransport underlyingTransport) throws SaslException, TTransportException {
      return new TSaslClientTransport("PLAIN", (String)null, (String)null, (String)null, new HashMap(), new PlainCallbackHandler(username, password), underlyingTransport);
   }

   private PlainSaslHelper() {
      throw new UnsupportedOperationException("Can't initialize class");
   }

   static {
      Security.addProvider(new PlainSaslServer.SaslPlainProvider());
   }

   private static final class PlainServerCallbackHandler implements CallbackHandler {
      private final AuthenticationProviderFactory.AuthMethods authMethod;

      PlainServerCallbackHandler(String authMethodStr) throws AuthenticationException {
         this.authMethod = AuthenticationProviderFactory.AuthMethods.getValidAuthMethod(authMethodStr);
      }

      public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
         String username = null;
         String password = null;
         AuthorizeCallback ac = null;

         for(Callback callback : callbacks) {
            if (callback instanceof NameCallback nc) {
               username = nc.getName();
            } else if (callback instanceof PasswordCallback pc) {
               password = new String(pc.getPassword());
            } else {
               if (!(callback instanceof AuthorizeCallback)) {
                  throw new UnsupportedCallbackException(callback);
               }

               ac = (AuthorizeCallback)callback;
            }
         }

         PasswdAuthenticationProvider provider = AuthenticationProviderFactory.getAuthenticationProvider(this.authMethod);
         provider.Authenticate(username, password);
         if (ac != null) {
            ac.setAuthorized(true);
         }

      }
   }

   public static class PlainCallbackHandler implements CallbackHandler {
      private final String username;
      private final String password;

      public PlainCallbackHandler(String username, String password) {
         this.username = username;
         this.password = password;
      }

      public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
         for(Callback callback : callbacks) {
            if (callback instanceof NameCallback nameCallback) {
               nameCallback.setName(this.username);
            } else {
               if (!(callback instanceof PasswordCallback)) {
                  throw new UnsupportedCallbackException(callback);
               }

               PasswordCallback passCallback = (PasswordCallback)callback;
               passCallback.setPassword(this.password.toCharArray());
            }
         }

      }
   }

   private static final class SQLPlainProcessorFactory extends TProcessorFactory {
      private final ThriftCLIService service;

      SQLPlainProcessorFactory(ThriftCLIService service) {
         super((TProcessor)null);
         this.service = service;
      }

      public TProcessor getProcessor(TTransport trans) {
         return new TSetIpAddressProcessor(this.service);
      }
   }
}
