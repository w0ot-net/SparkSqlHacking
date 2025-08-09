package org.apache.derby.impl.jdbc.authentication;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PasswordHasher;
import org.apache.derby.iapi.sql.dictionary.UserDescriptor;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public abstract class AuthenticationServiceBase implements AuthenticationService, ModuleControl, ModuleSupportable, PropertySetCallback {
   protected UserAuthenticator authenticationScheme;
   private AccessFactory store;
   public static final String AuthenticationTrace = null;
   protected static final int SECMEC_USRSSBPWD = 8;

   protected void setAuthenticationService(UserAuthenticator var1) {
      this.authenticationScheme = var1;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.store = (AccessFactory)getServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");
      PropertyFactory var3 = (PropertyFactory)getServiceModule(this, "org.apache.derby.iapi.services.property.PropertyFactory");
      if (var3 != null) {
         var3.addPropertySetNotification(this);
      }

   }

   public void stop() {
   }

   public boolean authenticate(String var1, Properties var2) throws SQLException {
      if (var2 == (Properties)null) {
         return false;
      } else {
         String var3 = var2.getProperty("user");
         return var3 != null && var3.length() > 128 ? false : this.authenticationScheme.authenticateUser(var3, var2.getProperty("password"), var1, var2);
      }
   }

   public String getSystemCredentialsDatabaseName() {
      return null;
   }

   public String getProperty(String var1) {
      String var2 = null;
      TransactionController var3 = null;

      try {
         var3 = this.getTransaction();
         var2 = PropertyUtil.getServiceProperty(var3, var1, (String)null);
         if (var3 != null) {
            var3.commit();
            var3 = null;
         }
      } catch (StandardException var5) {
      }

      return var2;
   }

   protected TransactionController getTransaction() throws StandardException {
      return this.store == null ? null : this.store.getTransaction(getContextService().getCurrentContextManager());
   }

   Properties getDatabaseProperties() throws StandardException {
      Properties var1 = null;
      TransactionController var2 = this.getTransaction();
      if (var2 != null) {
         try {
            var1 = var2.getProperties();
         } finally {
            var2.commit();
         }
      }

      return var1;
   }

   protected String getServiceName() {
      return this.store == null ? null : getServiceName(this.store);
   }

   public String getDatabaseProperty(String var1) {
      String var2 = null;
      TransactionController var3 = null;

      try {
         if (this.store != null) {
            var3 = this.store.getTransaction(getContextService().getCurrentContextManager());
         }

         var2 = PropertyUtil.getDatabaseProperty(var3, var1);
         if (var3 != null) {
            var3.commit();
            Object var6 = null;
         }
      } catch (StandardException var5) {
      }

      return var2;
   }

   public String getSystemProperty(String var1) {
      boolean var2 = false;
      var2 = Boolean.valueOf(this.getDatabaseProperty("derby.database.propertiesOnly"));
      return var2 ? null : PropertyUtil.getSystemProperty(var1);
   }

   public void init(boolean var1, Dictionary var2) {
   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var1.startsWith("derby.user.")) {
         return true;
      } else {
         String var4 = (String)var2;
         boolean var5 = "NATIVE::LOCAL".equals(var4);
         if ("derby.authentication.provider".equals(var1)) {
            if (var4 != null && var4.startsWith("NATIVE:") && !var5) {
               throw StandardException.newException("XCY05.S.3", new Object[0]);
            }

            String var6 = (String)var3.get("derby.authentication.provider");
            if (var6 != null && var6.startsWith("NATIVE:")) {
               throw StandardException.newException("XCY05.S.2", new Object[0]);
            }

            if (var5) {
               DataDictionary var7 = getDataDictionary();
               String var8 = var7.getAuthorizationDatabaseOwner();
               UserDescriptor var9 = var7.getUser(var8);
               if (var9 == null) {
                  throw StandardException.newException("XCY05.S.3", new Object[0]);
               }
            }
         }

         if ("derby.authentication.native.passwordLifetimeMillis".equals(var1) && this.parsePasswordLifetime(var4) == null) {
            throw StandardException.newException("4251J", new Object[]{"derby.authentication.native.passwordLifetimeMillis"});
         } else if ("derby.authentication.native.passwordLifetimeThreshold".equals(var1) && this.parsePasswordThreshold(var4) == null) {
            throw StandardException.newException("4251J", new Object[]{"derby.authentication.native.passwordLifetimeThreshold"});
         } else {
            return false;
         }
      }
   }

   protected Long parsePasswordLifetime(String var1) {
      try {
         long var2 = Long.parseLong(var1);
         if (var2 < 0L) {
            var2 = 0L;
         }

         return var2;
      } catch (Exception var4) {
         return null;
      }
   }

   protected Double parsePasswordThreshold(String var1) {
      try {
         double var2 = Double.parseDouble(var1);
         return var2 <= (double)0.0F ? null : var2;
      } catch (Exception var4) {
         return null;
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (!var1.startsWith("derby.user.")) {
         return null;
      } else {
         String var4 = (String)var3.get("derby.authentication.provider");
         if (var4 != null && StringUtil.SQLEqualsIgnoreCase(var4, "LDAP")) {
            return null;
         } else {
            String var5 = (String)var2;
            if (var5 != null) {
               String var6 = var1.substring("derby.user.".length());
               var5 = this.hashUsingDefaultAlgorithm(var6, var5, var3);
            }

            return var5;
         }
      }
   }

   protected final boolean requireAuthentication(Properties var1) {
      String var2 = PropertyUtil.getPropertyFromSet(var1, "derby.connection.requireAuthentication");
      return Boolean.valueOf(var2) ? true : PropertyUtil.nativeAuthenticationEnabled(var1);
   }

   protected String hashPasswordSHA1Scheme(String var1) {
      if (var1 == null) {
         return null;
      } else {
         MessageDigest var2 = null;

         try {
            var2 = MessageDigest.getInstance("SHA-1");
         } catch (NoSuchAlgorithmException var6) {
         }

         var2.reset();
         Object var3 = null;
         byte[] var7 = toHexByte(var1);
         var2.update(var7);
         byte[] var4 = var2.digest();
         String var5 = "3b60" + StringUtil.toHexString(var4, 0, var4.length);
         return var5;
      }
   }

   private static byte[] toHexByte(String var0) {
      byte[] var1 = new byte[var0.length() * 2];

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         int var4 = (var3 & 240) >>> 4;
         int var5 = var3 & 15;
         var1[var2] = (byte)var4;
         var1[var2 + 1] = (byte)var5;
      }

      return var1;
   }

   String hashUsingDefaultAlgorithm(String var1, String var2, Dictionary var3) throws StandardException {
      if (var2 == null) {
         return null;
      } else {
         PasswordHasher var4 = getDataDictionary().makePasswordHasher(var3);
         return var4 != null ? var4.hashAndEncode(var1, var2) : this.hashPasswordSHA1Scheme(var2);
      }
   }

   private static DataDictionary getDataDictionary() {
      LanguageConnectionContext var0 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
      return var0.getDataDictionary();
   }

   protected String substitutePassword(String var1, String var2, Properties var3, boolean var4) {
      MessageDigest var5 = null;
      byte[] var6 = new byte[]{0, 0, 0, 0, 0, 0, 0, 1};

      try {
         var5 = MessageDigest.getInstance("SHA-1");
      } catch (NoSuchAlgorithmException var16) {
      }

      var5.reset();
      Object var8 = null;
      byte[] var9 = toHexByte(var1);
      String var10 = var3.getProperty("drdaSecTokenIn");
      String var11 = var3.getProperty("drdaSecTokenOut");
      byte[] var12 = StringUtil.fromHexString(var10, 0, var10.length());
      byte[] var13 = StringUtil.fromHexString(var11, 0, var11.length());
      Object var14 = null;
      String var18;
      if (!var4) {
         byte[] var17 = toHexByte(var2);
         var5.update(var17);
         byte[] var15 = var5.digest();
         var18 = "3b60" + StringUtil.toHexString(var15, 0, var15.length);
      } else {
         var18 = var2;
      }

      var5.update(var9);
      var5.update(toHexByte(var18));
      byte[] var19 = var5.digest();
      var5.update(var19);
      var5.update(var13);
      var5.update(var12);
      var5.update(var9);
      var5.update(var6);
      byte[] var7 = var5.digest();
      return StringUtil.toHexString(var7, 0, var7.length);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }

   private static String getServiceName(Object var0) {
      return Monitor.getServiceName(var0);
   }

   static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }
}
