package org.apache.hive.service.auth;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import javax.security.sasl.SaslServerFactory;

public class PlainSaslServer implements SaslServer {
   public static final String PLAIN_METHOD = "PLAIN";
   private String user;
   private final CallbackHandler handler;

   PlainSaslServer(CallbackHandler handler, String authMethodStr) throws SaslException {
      this.handler = handler;
      AuthenticationProviderFactory.AuthMethods.getValidAuthMethod(authMethodStr);
   }

   public String getMechanismName() {
      return "PLAIN";
   }

   public byte[] evaluateResponse(byte[] response) throws SaslException {
      try {
         Deque<String> tokenList = new ArrayDeque();
         StringBuilder messageToken = new StringBuilder();

         for(byte b : response) {
            if (b == 0) {
               tokenList.addLast(messageToken.toString());
               messageToken = new StringBuilder();
            } else {
               messageToken.append((char)b);
            }
         }

         tokenList.addLast(messageToken.toString());
         if (tokenList.size() >= 2 && tokenList.size() <= 3) {
            String passwd = (String)tokenList.removeLast();
            this.user = (String)tokenList.removeLast();
            String authzId;
            if (tokenList.isEmpty()) {
               authzId = this.user;
            } else {
               authzId = (String)tokenList.removeLast();
            }

            if (this.user != null && !this.user.isEmpty()) {
               if (passwd != null && !passwd.isEmpty()) {
                  NameCallback nameCallback = new NameCallback("User");
                  nameCallback.setName(this.user);
                  PasswordCallback pcCallback = new PasswordCallback("Password", false);
                  pcCallback.setPassword(passwd.toCharArray());
                  AuthorizeCallback acCallback = new AuthorizeCallback(this.user, authzId);
                  Callback[] cbList = new Callback[]{nameCallback, pcCallback, acCallback};
                  this.handler.handle(cbList);
                  if (!acCallback.isAuthorized()) {
                     throw new SaslException("Authentication failed");
                  } else {
                     return null;
                  }
               } else {
                  throw new SaslException("No password name provided");
               }
            } else {
               throw new SaslException("No user name provided");
            }
         } else {
            throw new SaslException("Invalid message format");
         }
      } catch (IllegalStateException eL) {
         throw new SaslException("Invalid message format", eL);
      } catch (IOException eI) {
         throw new SaslException("Error validating the login", eI);
      } catch (UnsupportedCallbackException eU) {
         throw new SaslException("Error validating the login", eU);
      }
   }

   public boolean isComplete() {
      return this.user != null;
   }

   public String getAuthorizationID() {
      return this.user;
   }

   public byte[] unwrap(byte[] incoming, int offset, int len) {
      throw new UnsupportedOperationException();
   }

   public byte[] wrap(byte[] outgoing, int offset, int len) {
      throw new UnsupportedOperationException();
   }

   public Object getNegotiatedProperty(String propName) {
      return null;
   }

   public void dispose() {
   }

   public static class SaslPlainServerFactory implements SaslServerFactory {
      public SaslServer createSaslServer(String mechanism, String protocol, String serverName, Map props, CallbackHandler cbh) {
         if ("PLAIN".equals(mechanism)) {
            try {
               return new PlainSaslServer(cbh, protocol);
            } catch (SaslException var7) {
               return null;
            }
         } else {
            return null;
         }
      }

      public String[] getMechanismNames(Map props) {
         return new String[]{"PLAIN"};
      }
   }

   public static class SaslPlainProvider extends Provider {
      public SaslPlainProvider() {
         super("HiveSaslPlain", (double)1.0F, "Hive Plain SASL provider");
         this.put("SaslServerFactory.PLAIN", SaslPlainServerFactory.class.getName());
      }
   }
}
