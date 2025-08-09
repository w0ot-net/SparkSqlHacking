package org.apache.hive.service.auth;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import javax.security.auth.Subject;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.TOKEN.;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public final class HttpAuthUtils {
   public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
   public static final String AUTHORIZATION = "Authorization";
   public static final String BASIC = "Basic";
   public static final String NEGOTIATE = "Negotiate";
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(HttpAuthUtils.class);
   private static final String COOKIE_ATTR_SEPARATOR = "&";
   private static final String COOKIE_CLIENT_USER_NAME = "cu";
   private static final String COOKIE_CLIENT_RAND_NUMBER = "rn";
   private static final String COOKIE_KEY_VALUE_SEPARATOR = "=";
   private static final Set COOKIE_ATTRIBUTES = new HashSet(Arrays.asList("cu", "rn"));
   private static final SecureRandom random = new SecureRandom();

   public static String getKerberosServiceTicket(String principal, String host, String serverHttpUrl, boolean assumeSubject) throws Exception {
      String serverPrincipal = ShimLoader.getHadoopThriftAuthBridge().getServerPrincipal(principal, host);
      if (assumeSubject) {
         AccessControlContext context = AccessController.getContext();
         Subject subject = Subject.getSubject(context);
         if (subject == null) {
            throw new Exception("The Subject is not set");
         } else {
            return (String)Subject.doAs(subject, new HttpKerberosClientAction(serverPrincipal, serverHttpUrl));
         }
      } else {
         UserGroupInformation clientUGI = ShimLoader.getHadoopThriftAuthBridge().getCurrentUGIWithConf("kerberos");
         return (String)clientUGI.doAs(new HttpKerberosClientAction(serverPrincipal, serverHttpUrl));
      }
   }

   public static String createCookieToken(String clientUserName) {
      StringBuffer sb = new StringBuffer();
      sb.append("cu").append("=").append(clientUserName).append("&");
      sb.append("rn").append("=").append(random.nextLong());
      return sb.toString();
   }

   public static String getUserNameFromCookieToken(String tokenStr) {
      Map<String, String> map = splitCookieToken(tokenStr);
      if (!map.keySet().equals(COOKIE_ATTRIBUTES)) {
         LOG.error("Invalid token with missing attributes {}", new MDC[]{MDC.of(.MODULE$, tokenStr)});
         return null;
      } else {
         return (String)map.get("cu");
      }
   }

   private static Map splitCookieToken(String tokenStr) {
      Map<String, String> map = new HashMap();
      StringTokenizer st = new StringTokenizer(tokenStr, "&");

      while(st.hasMoreTokens()) {
         String part = st.nextToken();
         int separator = part.indexOf("=");
         if (separator == -1) {
            LOG.error("Invalid token string {}", new MDC[]{MDC.of(.MODULE$, tokenStr)});
            return null;
         }

         String key = part.substring(0, separator);
         String value = part.substring(separator + 1);
         map.put(key, value);
      }

      return map;
   }

   private HttpAuthUtils() {
      throw new UnsupportedOperationException("Can't initialize class");
   }

   public static class HttpKerberosClientAction implements PrivilegedExceptionAction {
      public static final String HTTP_RESPONSE = "HTTP_RESPONSE";
      public static final String SERVER_HTTP_URL = "SERVER_HTTP_URL";
      private final String serverPrincipal;
      private final String serverHttpUrl;
      private final Base64 base64codec;
      private final HttpContext httpContext;

      public HttpKerberosClientAction(String serverPrincipal, String serverHttpUrl) {
         this.serverPrincipal = serverPrincipal;
         this.serverHttpUrl = serverHttpUrl;
         this.base64codec = new Base64(0);
         this.httpContext = new BasicHttpContext();
         this.httpContext.setAttribute("SERVER_HTTP_URL", serverHttpUrl);
      }

      public String run() throws Exception {
         Oid mechOid = new Oid("1.2.840.113554.1.2.2");
         Oid krb5PrincipalOid = new Oid("1.2.840.113554.1.2.2.1");
         GSSManager manager = GSSManager.getInstance();
         GSSName serverName = manager.createName(this.serverPrincipal, krb5PrincipalOid);
         GSSContext gssContext = manager.createContext(serverName, mechOid, (GSSCredential)null, 0);
         gssContext.requestMutualAuth(false);
         byte[] inToken = new byte[0];
         byte[] outToken = gssContext.initSecContext(inToken, 0, inToken.length);
         gssContext.dispose();
         return new String(this.base64codec.encode(outToken));
      }
   }
}
