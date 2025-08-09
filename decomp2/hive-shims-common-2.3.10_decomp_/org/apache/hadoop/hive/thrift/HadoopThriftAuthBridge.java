package org.apache.hadoop.hive.thrift;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.RealmChoiceCallback;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.thrift.client.TUGIAssumingTransport;
import org.apache.hadoop.security.SaslRpcServer;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.SaslRpcServer.AuthMethod;
import org.apache.hadoop.security.UserGroupInformation.AuthenticationMethod;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.TokenIdentifier;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSaslClientTransport;
import org.apache.thrift.transport.TSaslServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HadoopThriftAuthBridge {
   private static final Logger LOG = LoggerFactory.getLogger(HadoopThriftAuthBridge.class);

   public Client createClient() {
      return new Client();
   }

   public Client createClientWithConf(String authMethod) {
      UserGroupInformation ugi;
      try {
         ugi = UserGroupInformation.getLoginUser();
      } catch (IOException e) {
         throw new IllegalStateException("Unable to get current login user: " + e, e);
      }

      if (this.loginUserHasCurrentAuthMethod(ugi, authMethod)) {
         LOG.debug("Not setting UGI conf as passed-in authMethod of " + authMethod + " = current.");
         return new Client();
      } else {
         LOG.debug("Setting UGI conf as passed-in authMethod of " + authMethod + " != current.");
         Configuration conf = new Configuration();
         conf.set("hadoop.security.authentication", authMethod);
         UserGroupInformation.setConfiguration(conf);
         return new Client();
      }
   }

   public Server createServer(String keytabFile, String principalConf) throws TTransportException {
      return new Server(keytabFile, principalConf);
   }

   public String getServerPrincipal(String principalConfig, String host) throws IOException {
      String serverPrincipal = SecurityUtil.getServerPrincipal(principalConfig, host);
      String[] names = SaslRpcServer.splitKerberosName(serverPrincipal);
      if (names.length != 3) {
         throw new IOException("Kerberos principal name does NOT have the expected hostname part: " + serverPrincipal);
      } else {
         return serverPrincipal;
      }
   }

   public UserGroupInformation getCurrentUGIWithConf(String authMethod) throws IOException {
      UserGroupInformation ugi;
      try {
         ugi = UserGroupInformation.getCurrentUser();
      } catch (IOException e) {
         throw new IllegalStateException("Unable to get current user: " + e, e);
      }

      if (this.loginUserHasCurrentAuthMethod(ugi, authMethod)) {
         LOG.debug("Not setting UGI conf as passed-in authMethod of " + authMethod + " = current.");
         return ugi;
      } else {
         LOG.debug("Setting UGI conf as passed-in authMethod of " + authMethod + " != current.");
         Configuration conf = new Configuration();
         conf.set("hadoop.security.authentication", authMethod);
         UserGroupInformation.setConfiguration(conf);
         return UserGroupInformation.getCurrentUser();
      }
   }

   private boolean loginUserHasCurrentAuthMethod(UserGroupInformation ugi, String sAuthMethod) {
      UserGroupInformation.AuthenticationMethod authMethod;
      try {
         authMethod = (UserGroupInformation.AuthenticationMethod)Enum.valueOf(UserGroupInformation.AuthenticationMethod.class, sAuthMethod.toUpperCase(Locale.ENGLISH));
      } catch (IllegalArgumentException iae) {
         throw new IllegalArgumentException("Invalid attribute value for hadoop.security.authentication of " + sAuthMethod, iae);
      }

      LOG.debug("Current authMethod = " + ugi.getAuthenticationMethod());
      return ugi.getAuthenticationMethod().equals(authMethod);
   }

   public abstract Map getHadoopSaslProperties(Configuration var1);

   public static class Client {
      public TTransport createClientTransport(String principalConfig, String host, String methodStr, String tokenStrForm, final TTransport underlyingTransport, final Map saslProps) throws IOException {
         final SaslRpcServer.AuthMethod method = (SaslRpcServer.AuthMethod)AuthMethod.valueOf(SaslRpcServer.AuthMethod.class, methodStr);
         TTransport saslTransport = null;
         switch (method) {
            case DIGEST:
               Token<DelegationTokenIdentifier> t = new Token();
               t.decodeFromUrlString(tokenStrForm);

               try {
                  saslTransport = new TSaslClientTransport(method.getMechanismName(), (String)null, (String)null, "default", saslProps, new SaslClientCallbackHandler(t), underlyingTransport);
               } catch (TTransportException e) {
                  e.printStackTrace();
               }

               return new TUGIAssumingTransport(saslTransport, UserGroupInformation.getCurrentUser());
            case KERBEROS:
               String serverPrincipal = SecurityUtil.getServerPrincipal(principalConfig, host);
               final String[] names = SaslRpcServer.splitKerberosName(serverPrincipal);
               if (names.length != 3) {
                  throw new IOException("Kerberos principal name does NOT have the expected hostname part: " + serverPrincipal);
               } else {
                  try {
                     return (TTransport)UserGroupInformation.getCurrentUser().doAs(new PrivilegedExceptionAction() {
                        public TUGIAssumingTransport run() throws IOException, TTransportException {
                           TTransport saslTransport = new TSaslClientTransport(method.getMechanismName(), (String)null, names[0], names[1], saslProps, (CallbackHandler)null, underlyingTransport);
                           return new TUGIAssumingTransport(saslTransport, UserGroupInformation.getCurrentUser());
                        }
                     });
                  } catch (SaslException | InterruptedException se) {
                     throw new IOException("Could not instantiate SASL transport", se);
                  }
               }
            default:
               throw new IOException("Unsupported authentication method: " + method);
         }
      }

      private static class SaslClientCallbackHandler implements CallbackHandler {
         private final String userName;
         private final char[] userPassword;

         public SaslClientCallbackHandler(Token token) {
            this.userName = encodeIdentifier(token.getIdentifier());
            this.userPassword = encodePassword(token.getPassword());
         }

         public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
            NameCallback nc = null;
            PasswordCallback pc = null;
            RealmCallback rc = null;

            for(Callback callback : callbacks) {
               if (!(callback instanceof RealmChoiceCallback)) {
                  if (callback instanceof NameCallback) {
                     nc = (NameCallback)callback;
                  } else if (callback instanceof PasswordCallback) {
                     pc = (PasswordCallback)callback;
                  } else {
                     if (!(callback instanceof RealmCallback)) {
                        throw new UnsupportedCallbackException(callback, "Unrecognized SASL client callback");
                     }

                     rc = (RealmCallback)callback;
                  }
               }
            }

            if (nc != null) {
               if (HadoopThriftAuthBridge.LOG.isDebugEnabled()) {
                  HadoopThriftAuthBridge.LOG.debug("SASL client callback: setting username: " + this.userName);
               }

               nc.setName(this.userName);
            }

            if (pc != null) {
               if (HadoopThriftAuthBridge.LOG.isDebugEnabled()) {
                  HadoopThriftAuthBridge.LOG.debug("SASL client callback: setting userPassword");
               }

               pc.setPassword(this.userPassword);
            }

            if (rc != null) {
               if (HadoopThriftAuthBridge.LOG.isDebugEnabled()) {
                  HadoopThriftAuthBridge.LOG.debug("SASL client callback: setting realm: " + rc.getDefaultText());
               }

               rc.setText(rc.getDefaultText());
            }

         }

         static String encodeIdentifier(byte[] identifier) {
            return new String(Base64.encodeBase64(identifier));
         }

         static char[] encodePassword(byte[] password) {
            return (new String(Base64.encodeBase64(password))).toCharArray();
         }
      }
   }

   public static class Server {
      protected final UserGroupInformation realUgi;
      protected DelegationTokenSecretManager secretManager;
      static final ThreadLocal remoteAddress = new ThreadLocal() {
         protected InetAddress initialValue() {
            return null;
         }
      };
      static final ThreadLocal authenticationMethod = new ThreadLocal() {
         protected UserGroupInformation.AuthenticationMethod initialValue() {
            return AuthenticationMethod.TOKEN;
         }
      };
      private static ThreadLocal remoteUser = new ThreadLocal() {
         protected String initialValue() {
            return null;
         }
      };
      private static final ThreadLocal userAuthMechanism = new ThreadLocal() {
         protected String initialValue() {
            return AuthMethod.KERBEROS.getMechanismName();
         }
      };

      public Server() throws TTransportException {
         try {
            this.realUgi = UserGroupInformation.getCurrentUser();
         } catch (IOException ioe) {
            throw new TTransportException(ioe);
         }
      }

      protected Server(String keytabFile, String principalConf) throws TTransportException {
         if (keytabFile != null && !keytabFile.isEmpty()) {
            if (principalConf != null && !principalConf.isEmpty()) {
               try {
                  String kerberosName = SecurityUtil.getServerPrincipal(principalConf, "0.0.0.0");
                  UserGroupInformation.loginUserFromKeytab(kerberosName, keytabFile);
                  this.realUgi = UserGroupInformation.getLoginUser();

                  assert this.realUgi.isFromKeytab();

               } catch (IOException ioe) {
                  throw new TTransportException(ioe);
               }
            } else {
               throw new TTransportException("No principal specified");
            }
         } else {
            throw new TTransportException("No keytab specified");
         }
      }

      public void setSecretManager(DelegationTokenSecretManager secretManager) {
         this.secretManager = secretManager;
      }

      public TTransportFactory createTransportFactory(Map saslProps) throws TTransportException {
         TSaslServerTransport.Factory transFactory = this.createSaslServerTransportFactory(saslProps);
         return new TUGIAssumingTransportFactory(transFactory, this.realUgi);
      }

      public TSaslServerTransport.Factory createSaslServerTransportFactory(Map saslProps) throws TTransportException {
         String kerberosName = this.realUgi.getUserName();
         String[] names = SaslRpcServer.splitKerberosName(kerberosName);
         if (names.length != 3) {
            throw new TTransportException("Kerberos principal should have 3 parts: " + kerberosName);
         } else {
            TSaslServerTransport.Factory transFactory = new TSaslServerTransport.Factory();
            transFactory.addServerDefinition(AuthMethod.KERBEROS.getMechanismName(), names[0], names[1], saslProps, new SaslRpcServer.SaslGssCallbackHandler());
            transFactory.addServerDefinition(AuthMethod.DIGEST.getMechanismName(), (String)null, "default", saslProps, new SaslDigestCallbackHandler(this.secretManager));
            return transFactory;
         }
      }

      public TTransportFactory wrapTransportFactory(TTransportFactory transFactory) {
         return new TUGIAssumingTransportFactory(transFactory, this.realUgi);
      }

      public TProcessor wrapProcessor(TProcessor processor) {
         return new TUGIAssumingProcessor(processor, this.secretManager, true);
      }

      public TProcessor wrapNonAssumingProcessor(TProcessor processor) {
         return new TUGIAssumingProcessor(processor, this.secretManager, false);
      }

      public InetAddress getRemoteAddress() {
         return (InetAddress)remoteAddress.get();
      }

      public String getRemoteUser() {
         return (String)remoteUser.get();
      }

      public String getUserAuthMechanism() {
         return (String)userAuthMechanism.get();
      }

      public static enum ServerMode {
         HIVESERVER2,
         METASTORE;
      }

      static class SaslDigestCallbackHandler implements CallbackHandler {
         private final DelegationTokenSecretManager secretManager;

         public SaslDigestCallbackHandler(DelegationTokenSecretManager secretManager) {
            this.secretManager = secretManager;
         }

         private char[] getPassword(DelegationTokenIdentifier tokenid) throws SecretManager.InvalidToken {
            return this.encodePassword(this.secretManager.retrievePassword(tokenid));
         }

         private char[] encodePassword(byte[] password) {
            return (new String(Base64.encodeBase64(password))).toCharArray();
         }

         public void handle(Callback[] callbacks) throws SecretManager.InvalidToken, UnsupportedCallbackException {
            NameCallback nc = null;
            PasswordCallback pc = null;
            AuthorizeCallback ac = null;

            for(Callback callback : callbacks) {
               if (callback instanceof AuthorizeCallback) {
                  ac = (AuthorizeCallback)callback;
               } else if (callback instanceof NameCallback) {
                  nc = (NameCallback)callback;
               } else if (callback instanceof PasswordCallback) {
                  pc = (PasswordCallback)callback;
               } else if (!(callback instanceof RealmCallback)) {
                  throw new UnsupportedCallbackException(callback, "Unrecognized SASL DIGEST-MD5 Callback");
               }
            }

            if (pc != null) {
               DelegationTokenIdentifier tokenIdentifier = (DelegationTokenIdentifier)SaslRpcServer.getIdentifier(nc.getDefaultName(), this.secretManager);
               char[] password = this.getPassword(tokenIdentifier);
               if (HadoopThriftAuthBridge.LOG.isDebugEnabled()) {
                  HadoopThriftAuthBridge.LOG.debug("SASL server DIGEST-MD5 callback: setting password for client: " + tokenIdentifier.getUser());
               }

               pc.setPassword(password);
            }

            if (ac != null) {
               String authid = ac.getAuthenticationID();
               String authzid = ac.getAuthorizationID();
               if (authid.equals(authzid)) {
                  ac.setAuthorized(true);
               } else {
                  ac.setAuthorized(false);
               }

               if (ac.isAuthorized()) {
                  if (HadoopThriftAuthBridge.LOG.isDebugEnabled()) {
                     String username = ((DelegationTokenIdentifier)SaslRpcServer.getIdentifier(authzid, this.secretManager)).getUser().getUserName();
                     HadoopThriftAuthBridge.LOG.debug("SASL server DIGEST-MD5 callback: setting canonicalized client ID: " + username);
                  }

                  ac.setAuthorizedID(authzid);
               }
            }

         }
      }

      protected class TUGIAssumingProcessor implements TProcessor {
         final TProcessor wrapped;
         DelegationTokenSecretManager secretManager;
         boolean useProxy;

         TUGIAssumingProcessor(TProcessor wrapped, DelegationTokenSecretManager secretManager, boolean useProxy) {
            this.wrapped = wrapped;
            this.secretManager = secretManager;
            this.useProxy = useProxy;
         }

         public void process(final TProtocol inProt, final TProtocol outProt) throws TException {
            TTransport trans = inProt.getTransport();
            if (!(trans instanceof TSaslServerTransport)) {
               throw new TException("Unexpected non-SASL transport " + trans.getClass());
            } else {
               TSaslServerTransport saslTrans = (TSaslServerTransport)trans;
               SaslServer saslServer = saslTrans.getSaslServer();
               String authId = saslServer.getAuthorizationID();
               HadoopThriftAuthBridge.LOG.debug("AUTH ID ======>" + authId);
               String endUser = authId;
               Socket socket = ((TSocket)((TSocket)saslTrans.getUnderlyingTransport())).getSocket();
               HadoopThriftAuthBridge.Server.remoteAddress.set(socket.getInetAddress());
               String mechanismName = saslServer.getMechanismName();
               HadoopThriftAuthBridge.Server.userAuthMechanism.set(mechanismName);
               if (AuthMethod.PLAIN.getMechanismName().equalsIgnoreCase(mechanismName)) {
                  HadoopThriftAuthBridge.Server.remoteUser.set(authId);
                  this.wrapped.process(inProt, outProt);
               } else {
                  HadoopThriftAuthBridge.Server.authenticationMethod.set(AuthenticationMethod.KERBEROS);
                  if (AuthMethod.TOKEN.getMechanismName().equalsIgnoreCase(mechanismName)) {
                     try {
                        TokenIdentifier tokenId = SaslRpcServer.getIdentifier(authId, this.secretManager);
                        endUser = tokenId.getUser().getUserName();
                        HadoopThriftAuthBridge.Server.authenticationMethod.set(AuthenticationMethod.TOKEN);
                     } catch (SecretManager.InvalidToken e) {
                        throw new TException(e.getMessage());
                     }
                  }

                  UserGroupInformation clientUgi = null;

                  try {
                     if (!this.useProxy) {
                        UserGroupInformation endUserUgi = UserGroupInformation.createRemoteUser(endUser);
                        HadoopThriftAuthBridge.Server.remoteUser.set(endUserUgi.getShortUserName());
                        HadoopThriftAuthBridge.LOG.debug("Set remoteUser :" + (String)HadoopThriftAuthBridge.Server.remoteUser.get() + ", from endUser :" + endUser);
                        this.wrapped.process(inProt, outProt);
                        return;
                     }

                     clientUgi = UserGroupInformation.createProxyUser(endUser, UserGroupInformation.getLoginUser());
                     HadoopThriftAuthBridge.Server.remoteUser.set(clientUgi.getShortUserName());
                     HadoopThriftAuthBridge.LOG.debug("Set remoteUser :" + (String)HadoopThriftAuthBridge.Server.remoteUser.get());
                     clientUgi.doAs(new PrivilegedExceptionAction() {
                        public Boolean run() {
                           try {
                              TUGIAssumingProcessor.this.wrapped.process(inProt, outProt);
                              return true;
                           } catch (TException te) {
                              throw new RuntimeException(te);
                           }
                        }
                     });
                  } catch (RuntimeException rte) {
                     if (rte.getCause() instanceof TException) {
                        throw (TException)rte.getCause();
                     }

                     throw rte;
                  } catch (InterruptedException ie) {
                     throw new RuntimeException(ie);
                  } catch (IOException ioe) {
                     throw new RuntimeException(ioe);
                  } finally {
                     if (clientUgi != null) {
                        try {
                           FileSystem.closeAllForUGI(clientUgi);
                        } catch (IOException exception) {
                           HadoopThriftAuthBridge.LOG.error("Could not clean up file-system handles for UGI: " + clientUgi, exception);
                        }
                     }

                  }

               }
            }
         }
      }

      static class TUGIAssumingTransportFactory extends TTransportFactory {
         private final UserGroupInformation ugi;
         private final TTransportFactory wrapped;

         public TUGIAssumingTransportFactory(TTransportFactory wrapped, UserGroupInformation ugi) {
            assert wrapped != null;

            assert ugi != null;

            this.wrapped = wrapped;
            this.ugi = ugi;
         }

         public TTransport getTransport(final TTransport trans) {
            return (TTransport)this.ugi.doAs(new PrivilegedAction() {
               public TTransport run() {
                  try {
                     return TUGIAssumingTransportFactory.this.wrapped.getTransport(trans);
                  } catch (TTransportException e) {
                     e.printStackTrace();
                     return null;
                  }
               }
            });
         }
      }
   }
}
