package org.apache.hive.service.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.security.sasl.AuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hive.service.ServiceUtils;

public class LdapAuthenticationProviderImpl implements PasswdAuthenticationProvider {
   private final String ldapURL;
   private final String baseDN;
   private final String ldapDomain;
   private final String userDNPattern;

   LdapAuthenticationProviderImpl() {
      HiveConf conf = new HiveConf();
      this.ldapURL = conf.getVar(ConfVars.HIVE_SERVER2_PLAIN_LDAP_URL);
      this.baseDN = conf.getVar(ConfVars.HIVE_SERVER2_PLAIN_LDAP_BASEDN);
      this.ldapDomain = conf.getVar(ConfVars.HIVE_SERVER2_PLAIN_LDAP_DOMAIN);
      this.userDNPattern = conf.getVar(ConfVars.HIVE_SERVER2_PLAIN_LDAP_USERDNPATTERN);
   }

   public void Authenticate(String user, String password) throws AuthenticationException {
      if (!this.hasDomain(user) && this.ldapDomain != null) {
         user = user + "@" + this.ldapDomain;
      }

      if (password != null && !password.isEmpty() && password.getBytes()[0] != 0) {
         List<String> candidatePrincipals = new ArrayList();
         if (StringUtils.isBlank(this.userDNPattern)) {
            if (StringUtils.isNotBlank(this.baseDN)) {
               String pattern = "uid=" + user + "," + this.baseDN;
               candidatePrincipals.add(pattern);
            }
         } else {
            String[] patterns = this.userDNPattern.split(":");

            for(String pattern : patterns) {
               if (StringUtils.contains(pattern, ",") && StringUtils.contains(pattern, "=")) {
                  candidatePrincipals.add(pattern.replaceAll("%s", user));
               }
            }
         }

         if (candidatePrincipals.isEmpty()) {
            candidatePrincipals = Collections.singletonList(user);
         }

         Iterator<String> iterator = candidatePrincipals.iterator();

         while(iterator.hasNext()) {
            String principal = (String)iterator.next();
            Hashtable<String, Object> env = new Hashtable();
            env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
            env.put("java.naming.provider.url", this.ldapURL);
            env.put("java.naming.security.authentication", "simple");
            env.put("java.naming.security.principal", principal);
            env.put("java.naming.security.credentials", password);

            try {
               Context ctx = new InitialDirContext(env);
               ctx.close();
               break;
            } catch (NamingException e) {
               if (!iterator.hasNext()) {
                  throw new AuthenticationException("Error validating LDAP user", e);
               }
            }
         }

      } else {
         throw new AuthenticationException("Error validating LDAP user: a null or blank password has been provided");
      }
   }

   private boolean hasDomain(String userName) {
      return ServiceUtils.indexOfDomainMatch(userName) > 0;
   }
}
