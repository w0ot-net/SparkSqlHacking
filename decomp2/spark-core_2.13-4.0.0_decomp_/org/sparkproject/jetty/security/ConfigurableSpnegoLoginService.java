package org.sparkproject.jetty.security;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.net.InetAddress;
import java.nio.file.Path;
import java.security.PrivilegedAction;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.authentication.AuthorizationService;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

public class ConfigurableSpnegoLoginService extends ContainerLifeCycle implements LoginService {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurableSpnegoLoginService.class);
   private final GSSManager _gssManager = GSSManager.getInstance();
   private final String _realm;
   private final AuthorizationService _authorizationService;
   private IdentityService _identityService = new DefaultIdentityService();
   private String _serviceName;
   private Path _keyTabPath;
   private String _hostName;
   private SpnegoContext _context;

   public ConfigurableSpnegoLoginService(String realm, AuthorizationService authorizationService) {
      this._realm = realm;
      this._authorizationService = authorizationService;
   }

   public String getName() {
      return this._realm;
   }

   public Path getKeyTabPath() {
      return this._keyTabPath;
   }

   public void setKeyTabPath(Path keyTabFile) {
      this._keyTabPath = keyTabFile;
   }

   public String getServiceName() {
      return this._serviceName;
   }

   public void setServiceName(String serviceName) {
      this._serviceName = serviceName;
   }

   public String getHostName() {
      return this._hostName;
   }

   public void setHostName(String hostName) {
      this._hostName = hostName;
   }

   protected void doStart() throws Exception {
      if (this._hostName == null) {
         this._hostName = InetAddress.getLocalHost().getCanonicalHostName();
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Retrieving credentials for service {}/{}", this.getServiceName(), this.getHostName());
      }

      LoginContext loginContext = new LoginContext("", (Subject)null, (CallbackHandler)null, new SpnegoConfiguration());
      loginContext.login();
      Subject subject = loginContext.getSubject();
      this._context = (SpnegoContext)Subject.doAs(subject, this.newSpnegoContext(subject));
      super.doStart();
   }

   private PrivilegedAction newSpnegoContext(Subject subject) {
      return () -> {
         try {
            GSSName serviceName = this._gssManager.createName(this.getServiceName() + "@" + this.getHostName(), GSSName.NT_HOSTBASED_SERVICE);
            Oid kerberosOid = new Oid("1.2.840.113554.1.2.2");
            Oid spnegoOid = new Oid("1.3.6.1.5.5.2");
            Oid[] mechanisms = new Oid[]{kerberosOid, spnegoOid};
            GSSCredential serviceCredential = this._gssManager.createCredential(serviceName, 0, mechanisms, 2);
            SpnegoContext context = new SpnegoContext();
            context._subject = subject;
            context._serviceCredential = serviceCredential;
            return context;
         } catch (GSSException x) {
            throw new RuntimeException(x);
         }
      };
   }

   public UserIdentity login(String username, Object credentials, ServletRequest req) {
      Subject subject = this._context._subject;
      HttpServletRequest request = (HttpServletRequest)req;
      HttpSession httpSession = request.getSession(false);
      GSSContext gssContext = null;
      if (httpSession != null) {
         GSSContextHolder holder = (GSSContextHolder)httpSession.getAttribute(ConfigurableSpnegoLoginService.GSSContextHolder.ATTRIBUTE);
         gssContext = holder == null ? null : holder.gssContext;
      }

      if (gssContext == null) {
         gssContext = (GSSContext)Subject.doAs(subject, this.newGSSContext());
      }

      byte[] input = Base64.getDecoder().decode((String)credentials);
      byte[] output = (byte[])Subject.doAs(this._context._subject, this.acceptGSSContext(gssContext, input));
      String token = Base64.getEncoder().encodeToString(output);
      String userName = this.toUserName(gssContext);
      SpnegoUserPrincipal principal = new SpnegoUserPrincipal(userName, token);
      if (gssContext.isEstablished()) {
         if (httpSession != null) {
            httpSession.removeAttribute(ConfigurableSpnegoLoginService.GSSContextHolder.ATTRIBUTE);
         }

         UserIdentity roles = this._authorizationService.getUserIdentity(request, userName);
         return new SpnegoUserIdentity(subject, principal, roles);
      } else {
         if (httpSession == null) {
            httpSession = request.getSession(true);
         }

         GSSContextHolder holder = new GSSContextHolder(gssContext);
         httpSession.setAttribute(ConfigurableSpnegoLoginService.GSSContextHolder.ATTRIBUTE, holder);
         return new SpnegoUserIdentity(subject, principal, (UserIdentity)null);
      }
   }

   private PrivilegedAction newGSSContext() {
      return () -> {
         try {
            return this._gssManager.createContext(this._context._serviceCredential);
         } catch (GSSException x) {
            throw new RuntimeException(x);
         }
      };
   }

   private PrivilegedAction acceptGSSContext(GSSContext gssContext, byte[] token) {
      return () -> {
         try {
            return gssContext.acceptSecContext(token, 0, token.length);
         } catch (GSSException x) {
            throw new RuntimeException(x);
         }
      };
   }

   private String toUserName(GSSContext gssContext) {
      try {
         String name = gssContext.getSrcName().toString();
         int at = name.indexOf(64);
         return at < 0 ? name : name.substring(0, at);
      } catch (GSSException x) {
         throw new RuntimeException(x);
      }
   }

   public boolean validate(UserIdentity user) {
      return false;
   }

   public IdentityService getIdentityService() {
      return this._identityService;
   }

   public void setIdentityService(IdentityService identityService) {
      this._identityService = identityService;
   }

   public void logout(UserIdentity user) {
   }

   private class SpnegoConfiguration extends Configuration {
      public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
         String var10000 = ConfigurableSpnegoLoginService.this.getServiceName();
         String principal = var10000 + "/" + ConfigurableSpnegoLoginService.this.getHostName();
         Map<String, Object> options = new HashMap();
         if (ConfigurableSpnegoLoginService.LOG.isDebugEnabled()) {
            options.put("debug", "true");
         }

         options.put("doNotPrompt", "true");
         options.put("refreshKrb5Config", "true");
         options.put("principal", principal);
         options.put("useKeyTab", "true");
         Path keyTabPath = ConfigurableSpnegoLoginService.this.getKeyTabPath();
         if (keyTabPath != null) {
            options.put("keyTab", keyTabPath.toAbsolutePath().toString());
         }

         options.put("storeKey", "true");
         options.put("isInitiator", "false");
         String moduleClass = "com.sun.security.auth.module.Krb5LoginModule";
         AppConfigurationEntry config = new AppConfigurationEntry(moduleClass, LoginModuleControlFlag.REQUIRED, options);
         return new AppConfigurationEntry[]{config};
      }
   }

   private static class SpnegoContext {
      private Subject _subject;
      private GSSCredential _serviceCredential;
   }

   private static class GSSContextHolder implements Serializable {
      public static final String ATTRIBUTE = GSSContextHolder.class.getName();
      private final transient GSSContext gssContext;

      private GSSContextHolder(GSSContext gssContext) {
         this.gssContext = gssContext;
      }
   }
}
