package org.apache.zookeeper.server.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.ServerCnxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigestAuthenticationProvider implements AuthenticationProvider {
   private static final Logger LOG = LoggerFactory.getLogger(DigestAuthenticationProvider.class);
   private static final String DEFAULT_DIGEST_ALGORITHM = "SHA1";
   public static final String DIGEST_ALGORITHM_KEY = "zookeeper.DigestAuthenticationProvider.digestAlg";
   private static final String DIGEST_ALGORITHM = System.getProperty("zookeeper.DigestAuthenticationProvider.digestAlg", "SHA1");
   private static final String DIGEST_AUTH_ENABLED = "zookeeper.DigestAuthenticationProvider.enabled";
   private static final String superDigest;

   public static boolean isEnabled() {
      boolean enabled = Boolean.parseBoolean(System.getProperty("zookeeper.DigestAuthenticationProvider.enabled", "true"));
      LOG.info("{} = {}", "zookeeper.DigestAuthenticationProvider.enabled", enabled);
      return enabled;
   }

   public String getScheme() {
      return "digest";
   }

   private static String base64Encode(byte[] b) {
      StringBuilder sb = new StringBuilder();
      int i = 0;

      while(i < b.length) {
         int pad = 0;
         int v = (b[i++] & 255) << 16;
         if (i < b.length) {
            v |= (b[i++] & 255) << 8;
         } else {
            ++pad;
         }

         if (i < b.length) {
            v |= b[i++] & 255;
         } else {
            ++pad;
         }

         sb.append(encode(v >> 18));
         sb.append(encode(v >> 12));
         if (pad < 2) {
            sb.append(encode(v >> 6));
         } else {
            sb.append('=');
         }

         if (pad < 1) {
            sb.append(encode(v));
         } else {
            sb.append('=');
         }
      }

      return sb.toString();
   }

   private static char encode(int i) {
      i &= 63;
      if (i < 26) {
         return (char)(65 + i);
      } else if (i < 52) {
         return (char)(97 + i - 26);
      } else if (i < 62) {
         return (char)(48 + i - 52);
      } else {
         return (char)(i == 62 ? '+' : '/');
      }
   }

   public static String generateDigest(String idPassword) throws NoSuchAlgorithmException {
      String[] parts = idPassword.split(":", 2);
      byte[] digest = digest(idPassword);
      return parts[0] + ":" + base64Encode(digest);
   }

   public static byte[] digest(String idPassword) throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(DIGEST_ALGORITHM).digest(idPassword.getBytes(StandardCharsets.UTF_8));
   }

   public KeeperException.Code handleAuthentication(ServerCnxn cnxn, byte[] authData) {
      List<Id> ids = this.handleAuthentication(authData);
      if (ids.isEmpty()) {
         return KeeperException.Code.AUTHFAILED;
      } else {
         for(Id id : ids) {
            cnxn.addAuthInfo(id);
         }

         return KeeperException.Code.OK;
      }
   }

   public List handleAuthentication(HttpServletRequest request, byte[] authData) {
      return this.handleAuthentication(authData);
   }

   public boolean isAuthenticated() {
      return true;
   }

   public boolean isValid(String id) {
      String[] parts = id.split(":");
      return parts.length == 2;
   }

   public boolean matches(String id, String aclExpr) {
      return id.equals(aclExpr);
   }

   public String getUserName(String id) {
      return id.split(":")[0];
   }

   public static void main(String[] args) throws NoSuchAlgorithmException {
      for(int i = 0; i < args.length; ++i) {
         System.out.println(args[i] + "->" + generateDigest(args[i]));
      }

   }

   private List handleAuthentication(byte[] authData) {
      List<Id> ids = new ArrayList();
      String id = new String(authData);

      try {
         String digest = generateDigest(id);
         if (digest.equals(superDigest)) {
            ids.add(new Id("super", ""));
         }

         ids.add(new Id(this.getScheme(), digest));
      } catch (NoSuchAlgorithmException e) {
         LOG.error("Missing algorithm", e);
      }

      return Collections.unmodifiableList(ids);
   }

   static {
      try {
         generateDigest(DIGEST_ALGORITHM);
      } catch (NoSuchAlgorithmException var1) {
         throw new RuntimeException("don't support this ACL digest algorithm: " + DIGEST_ALGORITHM + " in the current environment");
      }

      LOG.info("ACL digest algorithm is: {}", DIGEST_ALGORITHM);
      superDigest = System.getProperty("zookeeper.DigestAuthenticationProvider.superDigest");
   }
}
