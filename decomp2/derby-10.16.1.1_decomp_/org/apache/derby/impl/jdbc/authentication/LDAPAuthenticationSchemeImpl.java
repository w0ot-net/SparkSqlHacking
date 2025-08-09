package org.apache.derby.impl.jdbc.authentication;

import java.sql.SQLException;
import java.util.Properties;
import javax.naming.AuthenticationException;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.StringUtil;

public final class LDAPAuthenticationSchemeImpl extends JNDIAuthenticationSchemeBase {
   private static final String dfltLDAPURL = "ldap://";
   private String searchBaseDN;
   private String leftSearchFilter;
   private String rightSearchFilter;
   private boolean useUserPropertyAsDN;
   private String searchAuthDN;
   private String searchAuthPW;
   private static final String[] attrDN = new String[]{"dn"};
   private static final String LDAP_SEARCH_BASE = "derby.authentication.ldap.searchBase";
   private static final String LDAP_SEARCH_FILTER = "derby.authentication.ldap.searchFilter";
   private static final String LDAP_SEARCH_AUTH_DN = "derby.authentication.ldap.searchAuthDN";
   private static final String LDAP_SEARCH_AUTH_PW = "derby.authentication.ldap.searchAuthPW";
   private static final String LDAP_LOCAL_USER_DN = "derby.user";
   private static final String LDAP_SEARCH_FILTER_USERNAME = "%USERNAME%";

   public LDAPAuthenticationSchemeImpl(JNDIAuthenticationService var1, Properties var2) {
      super(var1, var2);
   }

   public boolean authenticateUser(String var1, String var2, String var3, Properties var4) throws SQLException {
      if (var1 != null && var1.length() != 0 && var2 != null && var2.length() != 0) {
         try {
            Properties var6 = (Properties)this.initDirContextEnv.clone();
            String var7 = null;
            if (this.useUserPropertyAsDN) {
               var7 = this.authenticationService.getProperty("derby.user.");
            }

            if (var7 == (String)null) {
               var7 = this.getDNFromUID(var1);
            }

            var6.put("java.naming.security.principal", var7);
            var6.put("java.naming.security.credentials", var2);
            this.privInitialDirContext(var6);
            return true;
         } catch (AuthenticationException var9) {
            return false;
         } catch (NameNotFoundException var10) {
            return false;
         } catch (NamingException var11) {
            throw getLoginSQLException(var11);
         }
      } else {
         return false;
      }
   }

   private DirContext privInitialDirContext(Properties var1) throws NamingException {
      return new InitialDirContext(var1);
   }

   protected void setJNDIProviderProperties() {
      if (this.initDirContextEnv.getProperty("java.naming.factory.initial") == (String)null) {
         this.initDirContextEnv.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
      }

      if (this.initDirContextEnv.getProperty("java.naming.provider.url") == (String)null) {
         String var1 = this.authenticationService.getProperty("derby.authentication.server");
         if (var1 == (String)null) {
            Monitor.logTextMessage("A011", "derby.authentication.server");
            this.providerURL = "ldap:///";
         } else if (!var1.startsWith("ldap://") && !var1.startsWith("ldaps://")) {
            if (var1.startsWith("//")) {
               this.providerURL = "ldap:" + var1;
            } else {
               this.providerURL = "ldap://" + var1;
            }
         } else {
            this.providerURL = var1;
         }

         this.initDirContextEnv.put("java.naming.provider.url", this.providerURL);
      }

      if (this.initDirContextEnv.getProperty("java.naming.security.authentication") == (String)null) {
         this.initDirContextEnv.put("java.naming.security.authentication", "simple");
      }

      String var3 = this.authenticationService.getProperty("derby.authentication.ldap.searchBase");
      if (var3 != (String)null) {
         this.searchBaseDN = var3;
      } else {
         this.searchBaseDN = "";
      }

      this.searchAuthDN = this.authenticationService.getProperty("derby.authentication.ldap.searchAuthDN");
      this.searchAuthPW = this.authenticationService.getProperty("derby.authentication.ldap.searchAuthPW");
      String var2 = this.authenticationService.getProperty("derby.authentication.ldap.searchFilter");
      if (var2 == (String)null) {
         this.leftSearchFilter = "(&(objectClass=inetOrgPerson)(uid=";
         this.rightSearchFilter = "))";
      } else if (StringUtil.SQLEqualsIgnoreCase(var2, "derby.user")) {
         this.leftSearchFilter = "(&(objectClass=inetOrgPerson)(uid=";
         this.rightSearchFilter = "))";
         this.useUserPropertyAsDN = true;
      } else if (var2.indexOf("%USERNAME%") != -1) {
         this.leftSearchFilter = var2.substring(0, var2.indexOf("%USERNAME%"));
         this.rightSearchFilter = var2.substring(var2.indexOf("%USERNAME%") + "%USERNAME%".length());
      } else {
         this.leftSearchFilter = "(&(" + var2 + ")(objectClass=inetOrgPerson)(uid=";
         this.rightSearchFilter = "))";
      }

   }

   private String getDNFromUID(String var1) throws NamingException {
      Object var2 = null;
      Properties var10;
      if (this.searchAuthDN != (String)null) {
         var10 = (Properties)this.initDirContextEnv.clone();
         var10.put("java.naming.security.principal", this.searchAuthDN);
         var10.put("java.naming.security.credentials", this.searchAuthPW);
      } else {
         var10 = this.initDirContextEnv;
      }

      DirContext var3 = this.privInitialDirContext(var10);
      SearchControls var4 = new SearchControls();
      var4.setSearchScope(2);
      var4.setReturningAttributes(attrDN);
      String var5 = this.leftSearchFilter + var1 + this.rightSearchFilter;
      NamingEnumeration var6 = var3.search(this.searchBaseDN, var5, var4);
      if (var6 != null && var6.hasMore()) {
         SearchResult var7 = (SearchResult)var6.next();
         if (var6.hasMore()) {
            throw new NameNotFoundException();
         } else {
            NameParser var8 = var3.getNameParser(this.searchBaseDN);
            Name var9 = var8.parse(this.searchBaseDN);
            if (var9 == (Name)null) {
               throw new NameNotFoundException();
            } else {
               var9.addAll(var8.parse(var7.getName()));
               return var9.toString();
            }
         }
      } else {
         throw new NameNotFoundException();
      }
   }
}
