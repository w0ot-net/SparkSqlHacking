package org.sparkproject.jetty.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.authentication.DeferredAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.component.DumpableCollection;

public abstract class SecurityHandler extends HandlerWrapper implements Authenticator.AuthConfiguration {
   private static final Logger LOG = LoggerFactory.getLogger(SecurityHandler.class);
   private static final List __knownAuthenticatorFactories = new ArrayList();
   private boolean _checkWelcomeFiles = false;
   private Authenticator _authenticator;
   private Authenticator.Factory _authenticatorFactory;
   private String _realmName;
   private String _authMethod;
   private final Map _initParameters = new HashMap();
   private LoginService _loginService;
   private IdentityService _identityService;
   private boolean _renewSessionOnAuthentication = true;
   private int _sessionMaxInactiveIntervalOnAuthentication = 0;
   public static final Principal __NO_USER;
   public static final Principal __NOBODY;

   protected SecurityHandler() {
      this.addBean(new DumpableCollection("knownAuthenticatorFactories", __knownAuthenticatorFactories));
   }

   public IdentityService getIdentityService() {
      return this._identityService;
   }

   public void setIdentityService(IdentityService identityService) {
      if (this.isStarted()) {
         throw new IllegalStateException("Started");
      } else {
         this.updateBean(this._identityService, identityService);
         this._identityService = identityService;
      }
   }

   public LoginService getLoginService() {
      return this._loginService;
   }

   public void setLoginService(LoginService loginService) {
      if (this.isStarted()) {
         throw new IllegalStateException("Started");
      } else {
         this.updateBean(this._loginService, loginService);
         this._loginService = loginService;
      }
   }

   public Authenticator getAuthenticator() {
      return this._authenticator;
   }

   public void setAuthenticator(Authenticator authenticator) {
      if (this.isStarted()) {
         throw new IllegalStateException("Started");
      } else {
         this.updateBean(this._authenticator, authenticator);
         this._authenticator = authenticator;
         if (this._authenticator != null) {
            this._authMethod = this._authenticator.getAuthMethod();
         }

      }
   }

   public Authenticator.Factory getAuthenticatorFactory() {
      return this._authenticatorFactory;
   }

   public void setAuthenticatorFactory(Authenticator.Factory authenticatorFactory) {
      if (this.isRunning()) {
         throw new IllegalStateException("running");
      } else {
         this.updateBean(this._authenticatorFactory, authenticatorFactory);
         this._authenticatorFactory = authenticatorFactory;
      }
   }

   public List getKnownAuthenticatorFactories() {
      return __knownAuthenticatorFactories;
   }

   public String getRealmName() {
      return this._realmName;
   }

   public void setRealmName(String realmName) {
      if (this.isRunning()) {
         throw new IllegalStateException("running");
      } else {
         this._realmName = realmName;
      }
   }

   public String getAuthMethod() {
      return this._authMethod;
   }

   public void setAuthMethod(String authMethod) {
      if (this.isRunning()) {
         throw new IllegalStateException("running");
      } else {
         this._authMethod = authMethod;
      }
   }

   public boolean isCheckWelcomeFiles() {
      return this._checkWelcomeFiles;
   }

   public void setCheckWelcomeFiles(boolean authenticateWelcomeFiles) {
      if (this.isRunning()) {
         throw new IllegalStateException("running");
      } else {
         this._checkWelcomeFiles = authenticateWelcomeFiles;
      }
   }

   public String getInitParameter(String key) {
      return (String)this._initParameters.get(key);
   }

   public Set getInitParameterNames() {
      return this._initParameters.keySet();
   }

   public String setInitParameter(String key, String value) {
      if (this.isStarted()) {
         throw new IllegalStateException("started");
      } else {
         return (String)this._initParameters.put(key, value);
      }
   }

   protected LoginService findLoginService() throws Exception {
      Collection<LoginService> list = this.getServer().getBeans(LoginService.class);
      LoginService service = null;
      String realm = this.getRealmName();
      if (realm != null) {
         for(LoginService s : list) {
            if (s.getName() != null && s.getName().equals(realm)) {
               service = s;
               break;
            }
         }
      } else if (list.size() == 1) {
         service = (LoginService)list.iterator().next();
      }

      return service;
   }

   protected IdentityService findIdentityService() {
      return (IdentityService)this.getServer().getBean(IdentityService.class);
   }

   protected void doStart() throws Exception {
      ContextHandler.Context context = ContextHandler.getCurrentContext();
      if (context != null) {
         Enumeration<String> names = context.getInitParameterNames();

         while(names != null && names.hasMoreElements()) {
            String name = (String)names.nextElement();
            if (name.startsWith("org.sparkproject.jetty.security.") && this.getInitParameter(name) == null) {
               this.setInitParameter(name, context.getInitParameter(name));
            }
         }
      }

      if (this._loginService == null) {
         this.setLoginService(this.findLoginService());
         if (this._loginService != null) {
            this.unmanage(this._loginService);
         }
      }

      if (this._identityService == null) {
         if (this._loginService != null) {
            this.setIdentityService(this._loginService.getIdentityService());
         }

         if (this._identityService == null) {
            this.setIdentityService(this.findIdentityService());
         }

         if (this._identityService == null) {
            this.setIdentityService(new DefaultIdentityService());
            this.manage(this._identityService);
         } else {
            this.unmanage(this._identityService);
         }
      }

      if (this._loginService != null) {
         if (this._loginService.getIdentityService() == null) {
            this._loginService.setIdentityService(this._identityService);
         } else if (this._loginService.getIdentityService() != this._identityService) {
            throw new IllegalStateException("LoginService has different IdentityService to " + String.valueOf(this));
         }
      }

      if (this._authenticator == null) {
         if (this._authenticatorFactory != null) {
            Authenticator authenticator = this._authenticatorFactory.getAuthenticator(this.getServer(), ContextHandler.getCurrentContext(), this, this._identityService, this._loginService);
            if (authenticator != null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Created authenticator {} with {}", authenticator, this._authenticatorFactory);
               }

               this.setAuthenticator(authenticator);
            }
         } else {
            for(Authenticator.Factory factory : this.getKnownAuthenticatorFactories()) {
               Authenticator authenticator = factory.getAuthenticator(this.getServer(), ContextHandler.getCurrentContext(), this, this._identityService, this._loginService);
               if (authenticator != null) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Created authenticator {} with {}", authenticator, factory);
                  }

                  this.setAuthenticator(authenticator);
                  break;
               }
            }
         }
      }

      if (this._authenticator != null) {
         this._authenticator.setConfiguration(this);
      } else if (this._realmName != null) {
         LOG.warn("No Authenticator for {}", this);
         throw new IllegalStateException("No Authenticator");
      }

      super.doStart();
   }

   protected void doStop() throws Exception {
      if (!this.isManaged(this._identityService)) {
         this.removeBean(this._identityService);
         this._identityService = null;
      }

      if (!this.isManaged(this._loginService)) {
         this.removeBean(this._loginService);
         this._loginService = null;
      }

      super.doStop();
   }

   protected boolean checkSecurity(Request request) {
      switch (request.getDispatcherType()) {
         case REQUEST:
         case ASYNC:
            return true;
         case FORWARD:
            if (this.isCheckWelcomeFiles() && request.getAttribute("org.sparkproject.jetty.server.welcome") != null) {
               request.removeAttribute("org.sparkproject.jetty.server.welcome");
               return true;
            }

            return false;
         default:
            return false;
      }
   }

   public boolean isSessionRenewedOnAuthentication() {
      return this._renewSessionOnAuthentication;
   }

   public void setSessionRenewedOnAuthentication(boolean renew) {
      this._renewSessionOnAuthentication = renew;
   }

   public int getSessionMaxInactiveIntervalOnAuthentication() {
      return this._sessionMaxInactiveIntervalOnAuthentication;
   }

   public void setSessionMaxInactiveIntervalOnAuthentication(int seconds) {
      this._sessionMaxInactiveIntervalOnAuthentication = seconds;
   }

   public void handle(String pathInContext, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Response base_response = baseRequest.getResponse();
      Handler handler = this.getHandler();
      if (handler != null) {
         Authenticator authenticator = this._authenticator;
         if (!this.checkSecurity(baseRequest)) {
            handler.handle(pathInContext, baseRequest, request, response);
         } else {
            if (authenticator != null) {
               authenticator.prepareRequest(baseRequest);
            }

            RoleInfo roleInfo = this.prepareConstraintInfo(pathInContext, baseRequest);
            if (!this.checkUserDataPermissions(pathInContext, baseRequest, base_response, roleInfo)) {
               if (!baseRequest.isHandled()) {
                  response.sendError(403);
                  baseRequest.setHandled(true);
               }

            } else {
               boolean isAuthMandatory = this.isAuthMandatory(baseRequest, base_response, roleInfo);
               if (isAuthMandatory && authenticator == null) {
                  LOG.warn("No authenticator for: {}", roleInfo);
                  if (!baseRequest.isHandled()) {
                     response.sendError(403);
                     baseRequest.setHandled(true);
                  }

               } else {
                  Object previousIdentity = null;

                  try {
                     try {
                        Authentication authentication = baseRequest.getAuthentication();
                        if (authentication == null || authentication == Authentication.NOT_CHECKED) {
                           authentication = authenticator == null ? Authentication.UNAUTHENTICATED : authenticator.validateRequest(request, response, isAuthMandatory);
                        }

                        if (authentication instanceof Authentication.Wrapped) {
                           request = ((Authentication.Wrapped)authentication).getHttpServletRequest();
                           response = ((Authentication.Wrapped)authentication).getHttpServletResponse();
                        }

                        if (authentication instanceof Authentication.ResponseSent) {
                           baseRequest.setHandled(true);
                           return;
                        }

                        if (!(authentication instanceof Authentication.User)) {
                           if (authentication instanceof Authentication.Deferred) {
                              DeferredAuthentication deferred = (DeferredAuthentication)authentication;
                              baseRequest.setAuthentication(authentication);

                              try {
                                 handler.handle(pathInContext, baseRequest, request, response);
                              } finally {
                                 previousIdentity = deferred.getPreviousAssociation();
                              }

                              if (authenticator != null) {
                                 Authentication auth = baseRequest.getAuthentication();
                                 if (auth instanceof Authentication.User) {
                                    Authentication.User userAuth = (Authentication.User)auth;
                                    authenticator.secureResponse(request, response, isAuthMandatory, userAuth);
                                 } else {
                                    authenticator.secureResponse(request, response, isAuthMandatory, (Authentication.User)null);
                                 }

                                 return;
                              }

                              return;
                           } else {
                              if (isAuthMandatory) {
                                 response.sendError(401, "unauthenticated");
                                 baseRequest.setHandled(true);
                              } else {
                                 baseRequest.setAuthentication(authentication);
                                 if (this._identityService != null) {
                                    previousIdentity = this._identityService.associate((UserIdentity)null);
                                 }

                                 handler.handle(pathInContext, baseRequest, request, response);
                                 if (authenticator != null) {
                                    authenticator.secureResponse(request, response, isAuthMandatory, (Authentication.User)null);
                                    return;
                                 }
                              }

                              return;
                           }
                        }

                        Authentication.User userAuth = (Authentication.User)authentication;
                        baseRequest.setAuthentication(authentication);
                        if (this._identityService != null) {
                           previousIdentity = this._identityService.associate(userAuth.getUserIdentity());
                        }

                        if (isAuthMandatory) {
                           boolean authorized = this.checkWebResourcePermissions(pathInContext, baseRequest, base_response, roleInfo, userAuth.getUserIdentity());
                           if (!authorized) {
                              response.sendError(403, "!role");
                              baseRequest.setHandled(true);
                              return;
                           }
                        }

                        handler.handle(pathInContext, baseRequest, request, response);
                        if (authenticator != null) {
                           authenticator.secureResponse(request, response, isAuthMandatory, userAuth);
                           return;
                        }
                     } catch (ServerAuthException e) {
                        response.sendError(500, e.getMessage());
                     }

                  } finally {
                     if (this._identityService != null) {
                        this._identityService.disassociate(previousIdentity);
                     }

                  }
               }
            }
         }
      }
   }

   public static SecurityHandler getCurrentSecurityHandler() {
      ContextHandler.Context context = ContextHandler.getCurrentContext();
      return context == null ? null : (SecurityHandler)context.getContextHandler().getChildHandlerByClass(SecurityHandler.class);
   }

   public void logout(Authentication.User user) {
      LOG.debug("logout {}", user);
      if (user != null) {
         LoginService loginService = this.getLoginService();
         if (loginService != null) {
            loginService.logout(user.getUserIdentity());
         }

         IdentityService identityService = this.getIdentityService();
         if (identityService != null) {
            Object previous = null;
            identityService.disassociate(previous);
         }

      }
   }

   protected abstract RoleInfo prepareConstraintInfo(String var1, Request var2);

   protected abstract boolean checkUserDataPermissions(String var1, Request var2, Response var3, RoleInfo var4) throws IOException;

   protected abstract boolean isAuthMandatory(Request var1, Response var2, Object var3);

   protected abstract boolean checkWebResourcePermissions(String var1, Request var2, Response var3, Object var4, UserIdentity var5) throws IOException;

   static {
      Stream var10000 = TypeUtil.serviceStream(ServiceLoader.load(Authenticator.Factory.class));
      List var10001 = __knownAuthenticatorFactories;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      __knownAuthenticatorFactories.add(new DefaultAuthenticatorFactory());
      __NO_USER = new Principal() {
         public String getName() {
            return null;
         }

         public String toString() {
            return "No User";
         }
      };
      __NOBODY = new Principal() {
         public String getName() {
            return "Nobody";
         }

         public String toString() {
            return this.getName();
         }
      };
   }

   public class NotChecked implements Principal {
      public String getName() {
         return null;
      }

      public String toString() {
         return "NOT CHECKED";
      }

      public SecurityHandler getSecurityHandler() {
         return SecurityHandler.this;
      }
   }
}
