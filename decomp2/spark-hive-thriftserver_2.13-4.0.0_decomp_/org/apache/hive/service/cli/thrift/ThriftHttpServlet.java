package org.apache.hive.service.cli.thrift;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpUtils;
import jakarta.ws.rs.core.NewCookie;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.CookieSigner;
import org.apache.hive.service.auth.AuthenticationProviderFactory;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.auth.HttpAuthUtils;
import org.apache.hive.service.auth.HttpAuthenticationException;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.session.SessionManager;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.USER_NAME.;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public class ThriftHttpServlet extends TServlet {
   private static final long serialVersionUID = 1L;
   public static final SparkLogger LOG = SparkLoggerFactory.getLogger(ThriftHttpServlet.class);
   private final String authType;
   private final UserGroupInformation serviceUGI;
   private final UserGroupInformation httpUGI;
   private HiveConf hiveConf = new HiveConf();
   private CookieSigner signer;
   public static final String AUTH_COOKIE = "hive.server2.auth";
   private static final SecureRandom RAN = new SecureRandom();
   private boolean isCookieAuthEnabled;
   private String cookieDomain;
   private String cookiePath;
   private int cookieMaxAge;
   private boolean isCookieSecure;
   private boolean isHttpOnlyCookie;
   private final HiveAuthFactory hiveAuthFactory;
   private static final String HIVE_DELEGATION_TOKEN_HEADER = "X-Hive-Delegation-Token";

   public ThriftHttpServlet(TProcessor processor, TProtocolFactory protocolFactory, String authType, UserGroupInformation serviceUGI, UserGroupInformation httpUGI, HiveAuthFactory hiveAuthFactory) {
      super(processor, protocolFactory);
      this.authType = authType;
      this.serviceUGI = serviceUGI;
      this.httpUGI = httpUGI;
      this.hiveAuthFactory = hiveAuthFactory;
      this.isCookieAuthEnabled = this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_AUTH_ENABLED);
      if (this.isCookieAuthEnabled) {
         String secret = Long.toString(RAN.nextLong());
         LOG.debug("Using the random number as the secret for cookie generation " + secret);
         this.signer = new CookieSigner(secret.getBytes());
         this.cookieMaxAge = (int)this.hiveConf.getTimeVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_MAX_AGE, TimeUnit.SECONDS);
         this.cookieDomain = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_DOMAIN);
         this.cookiePath = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_PATH);
         this.isCookieSecure = this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_IS_SECURE);
         this.isHttpOnlyCookie = this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_COOKIE_IS_HTTPONLY);
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String clientUserName = null;
      boolean requireNewCookie = false;

      try {
         if (this.isCookieAuthEnabled) {
            clientUserName = this.validateCookie(request);
            requireNewCookie = clientUserName == null;
            if (requireNewCookie) {
               LOG.info("Could not validate cookie sent, will try to generate a new cookie");
            }
         }

         if (clientUserName == null) {
            if (this.isKerberosAuthMode(this.authType)) {
               String delegationToken = request.getHeader("X-Hive-Delegation-Token");
               if (delegationToken != null && !delegationToken.isEmpty()) {
                  clientUserName = this.doTokenAuth(request, response);
               } else {
                  clientUserName = this.doKerberosAuth(request);
               }
            } else {
               clientUserName = this.doPasswdAuth(request, this.authType);
            }
         }

         LOG.debug("Client username: " + clientUserName);
         SessionManager.setUserName(clientUserName);
         String doAsQueryParam = getDoAsQueryParam(request.getQueryString());
         if (doAsQueryParam != null) {
            SessionManager.setProxyUserName(doAsQueryParam);
         }

         String clientIpAddress = request.getRemoteAddr();
         LOG.debug("Client IP Address: " + clientIpAddress);
         SessionManager.setIpAddress(clientIpAddress);
         if (requireNewCookie && !this.authType.equalsIgnoreCase(HiveAuthFactory.AuthTypes.NOSASL.toString())) {
            String cookieToken = HttpAuthUtils.createCookieToken(clientUserName);
            Cookie hs2Cookie = this.createCookie(this.signer.signCookie(cookieToken));
            if (this.isHttpOnlyCookie) {
               response.setHeader("SET-COOKIE", getHttpOnlyCookieHeader(hs2Cookie));
            } else {
               response.addCookie(hs2Cookie);
            }

            LOG.info("Cookie added for clientUserName {}", new MDC[]{MDC.of(.MODULE$, clientUserName)});
         }

         super.doPost(request, response);
      } catch (HttpAuthenticationException e) {
         LOG.error("Error: ", e);
         response.setStatus(401);
         if (this.isKerberosAuthMode(this.authType)) {
            response.addHeader("WWW-Authenticate", "Negotiate");
         }

         response.getWriter().println("Authentication Error: " + e.getMessage());
      } finally {
         SessionManager.clearUserName();
         SessionManager.clearIpAddress();
         SessionManager.clearProxyUserName();
      }

   }

   private String getClientNameFromCookie(Cookie[] cookies) {
      for(Cookie currCookie : cookies) {
         String currName = currCookie.getName();
         if (currName.equals("hive.server2.auth")) {
            String currValue = currCookie.getValue();
            currValue = this.signer.verifyAndExtract(currValue);
            if (currValue != null) {
               String userName = HttpAuthUtils.getUserNameFromCookieToken(currValue);
               if (userName != null) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Validated the cookie for user " + userName);
                  }

                  return userName;
               }

               LOG.warn("Invalid cookie token {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.TOKEN..MODULE$, currValue)});
            }
         }
      }

      return null;
   }

   private String toCookieStr(Cookie[] cookies) {
      String cookieStr = "";

      for(Cookie c : cookies) {
         cookieStr = cookieStr + c.getName() + "=" + c.getValue() + " ;\n";
      }

      return cookieStr;
   }

   private String validateCookie(HttpServletRequest request) throws UnsupportedEncodingException {
      Cookie[] cookies = request.getCookies();
      if (cookies == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("No valid cookies associated with the request " + String.valueOf(request));
         }

         return null;
      } else {
         if (LOG.isDebugEnabled()) {
            SparkLogger var10000 = LOG;
            String var10001 = this.toCookieStr(cookies);
            var10000.debug("Received cookies: " + var10001);
         }

         return this.getClientNameFromCookie(cookies);
      }
   }

   private Cookie createCookie(String str) throws UnsupportedEncodingException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Cookie name = hive.server2.auth value = " + str);
      }

      Cookie cookie = new Cookie("hive.server2.auth", str);
      cookie.setMaxAge(this.cookieMaxAge);
      if (this.cookieDomain != null) {
         cookie.setDomain(this.cookieDomain);
      }

      if (this.cookiePath != null) {
         cookie.setPath(this.cookiePath);
      }

      cookie.setSecure(this.isCookieSecure);
      return cookie;
   }

   private static String getHttpOnlyCookieHeader(Cookie cookie) {
      NewCookie newCookie = new NewCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(), cookie.getVersion(), cookie.getComment(), cookie.getMaxAge(), cookie.getSecure());
      return String.valueOf(newCookie) + "; HttpOnly";
   }

   private String doPasswdAuth(HttpServletRequest request, String authType) throws HttpAuthenticationException {
      String userName = this.getUsername(request, authType);
      if (!authType.equalsIgnoreCase(HiveAuthFactory.AuthTypes.NOSASL.toString())) {
         try {
            AuthenticationProviderFactory.AuthMethods authMethod = AuthenticationProviderFactory.AuthMethods.getValidAuthMethod(authType);
            PasswdAuthenticationProvider provider = AuthenticationProviderFactory.getAuthenticationProvider(authMethod);
            provider.Authenticate(userName, this.getPassword(request, authType));
         } catch (Exception e) {
            throw new HttpAuthenticationException(e);
         }
      }

      return userName;
   }

   private String doTokenAuth(HttpServletRequest request, HttpServletResponse response) throws HttpAuthenticationException {
      String tokenStr = request.getHeader("X-Hive-Delegation-Token");

      try {
         return this.hiveAuthFactory.verifyDelegationToken(tokenStr);
      } catch (HiveSQLException e) {
         throw new HttpAuthenticationException(e);
      }
   }

   private String doKerberosAuth(HttpServletRequest request) throws HttpAuthenticationException {
      if (this.httpUGI != null) {
         try {
            return (String)this.httpUGI.doAs(new HttpKerberosServerAction(request, this.httpUGI));
         } catch (Exception var4) {
            LOG.info("Failed to authenticate with http/_HOST kerberos principal, trying with hive/_HOST kerberos principal");
         }
      }

      try {
         return (String)this.serviceUGI.doAs(new HttpKerberosServerAction(request, this.serviceUGI));
      } catch (Exception e) {
         LOG.error("Failed to authenticate with hive/_HOST kerberos principal");
         throw new HttpAuthenticationException(e);
      }
   }

   private String getUsername(HttpServletRequest request, String authType) throws HttpAuthenticationException {
      String[] creds = this.getAuthHeaderTokens(request, authType);
      if (creds[0] != null && !creds[0].isEmpty()) {
         return creds[0];
      } else {
         throw new HttpAuthenticationException("Authorization header received from the client does not contain username.");
      }
   }

   private String getPassword(HttpServletRequest request, String authType) throws HttpAuthenticationException {
      String[] creds = this.getAuthHeaderTokens(request, authType);
      if (creds[1] != null && !creds[1].isEmpty()) {
         return creds[1];
      } else {
         throw new HttpAuthenticationException("Authorization header received from the client does not contain password.");
      }
   }

   private String[] getAuthHeaderTokens(HttpServletRequest request, String authType) throws HttpAuthenticationException {
      String authHeaderBase64 = this.getAuthHeader(request, authType);
      String authHeaderString = StringUtils.newStringUtf8(Base64.decodeBase64(authHeaderBase64.getBytes()));
      String[] creds = authHeaderString.split(":");
      return creds;
   }

   private String getAuthHeader(HttpServletRequest request, String authType) throws HttpAuthenticationException {
      String authHeader = request.getHeader("Authorization");
      if (authHeader != null && !authHeader.isEmpty()) {
         int beginIndex;
         if (this.isKerberosAuthMode(authType)) {
            beginIndex = "Negotiate ".length();
         } else {
            beginIndex = "Basic ".length();
         }

         String authHeaderBase64String = authHeader.substring(beginIndex);
         if (authHeaderBase64String != null && !authHeaderBase64String.isEmpty()) {
            return authHeaderBase64String;
         } else {
            throw new HttpAuthenticationException("Authorization header received from the client does not contain any data.");
         }
      } else {
         throw new HttpAuthenticationException("Authorization header received from the client is empty.");
      }
   }

   private boolean isKerberosAuthMode(String authType) {
      return authType.equalsIgnoreCase(HiveAuthFactory.AuthTypes.KERBEROS.toString());
   }

   private static String getDoAsQueryParam(String queryString) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("URL query string:" + queryString);
      }

      if (queryString == null) {
         return null;
      } else {
         Map<String, String[]> params = HttpUtils.parseQueryString(queryString);

         for(String key : params.keySet()) {
            if (key.equalsIgnoreCase("doAs")) {
               return ((String[])params.get(key))[0];
            }
         }

         return null;
      }
   }

   class HttpKerberosServerAction implements PrivilegedExceptionAction {
      HttpServletRequest request;
      UserGroupInformation serviceUGI;

      HttpKerberosServerAction(HttpServletRequest request, UserGroupInformation serviceUGI) {
         this.request = request;
         this.serviceUGI = serviceUGI;
      }

      public String run() throws HttpAuthenticationException {
         GSSManager manager = GSSManager.getInstance();
         GSSContext gssContext = null;
         String serverPrincipal = this.getPrincipalWithoutRealm(this.serviceUGI.getUserName());

         String var11;
         try {
            Oid kerberosMechOid = new Oid("1.2.840.113554.1.2.2");
            Oid spnegoMechOid = new Oid("1.3.6.1.5.5.2");
            Oid krb5PrincipalOid = new Oid("1.2.840.113554.1.2.2.1");
            GSSName serverName = manager.createName(serverPrincipal, krb5PrincipalOid);
            GSSCredential serverCreds = manager.createCredential(serverName, 0, new Oid[]{kerberosMechOid, spnegoMechOid}, 2);
            gssContext = manager.createContext(serverCreds);
            String serviceTicketBase64 = ThriftHttpServlet.this.getAuthHeader(this.request, ThriftHttpServlet.this.authType);
            byte[] inToken = Base64.decodeBase64(serviceTicketBase64.getBytes());
            gssContext.acceptSecContext(inToken, 0, inToken.length);
            if (!gssContext.isEstablished()) {
               throw new HttpAuthenticationException("Kerberos authentication failed: unable to establish context with the service ticket provided by the client.");
            }

            var11 = this.getPrincipalWithoutRealmAndHost(gssContext.getSrcName().toString());
         } catch (GSSException e) {
            throw new HttpAuthenticationException("Kerberos authentication failed: ", e);
         } finally {
            if (gssContext != null) {
               try {
                  gssContext.dispose();
               } catch (GSSException var19) {
               }
            }

         }

         return var11;
      }

      private String getPrincipalWithoutRealm(String fullPrincipal) throws HttpAuthenticationException {
         HadoopShims.KerberosNameShim fullKerberosName;
         try {
            fullKerberosName = ShimLoader.getHadoopShims().getKerberosNameShim(fullPrincipal);
         } catch (IOException e) {
            throw new HttpAuthenticationException(e);
         }

         String serviceName = fullKerberosName.getServiceName();
         String hostName = fullKerberosName.getHostName();
         String principalWithoutRealm = serviceName;
         if (hostName != null) {
            principalWithoutRealm = serviceName + "/" + hostName;
         }

         return principalWithoutRealm;
      }

      private String getPrincipalWithoutRealmAndHost(String fullPrincipal) throws HttpAuthenticationException {
         try {
            HadoopShims.KerberosNameShim fullKerberosName = ShimLoader.getHadoopShims().getKerberosNameShim(fullPrincipal);
            return fullKerberosName.getShortName();
         } catch (IOException e) {
            throw new HttpAuthenticationException(e);
         }
      }
   }
}
