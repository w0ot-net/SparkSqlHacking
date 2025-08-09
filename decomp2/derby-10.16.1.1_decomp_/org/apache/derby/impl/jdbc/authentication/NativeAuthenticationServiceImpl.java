package org.apache.derby.impl.jdbc.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Arrays;
import java.util.Properties;
import org.apache.derby.authentication.UserAuthenticator;
import org.apache.derby.catalog.SystemProcedures;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PasswordHasher;
import org.apache.derby.iapi.sql.dictionary.UserDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.shared.common.error.SQLWarningFactory;
import org.apache.derby.shared.common.error.StandardException;

public final class NativeAuthenticationServiceImpl extends AuthenticationServiceBase implements UserAuthenticator {
   private boolean _creatingCredentialsDB = false;
   private String _credentialsDB;
   private boolean _authenticateDatabaseOperationsLocally;
   private long _passwordLifetimeMillis = 2678400000L;
   private double _passwordExpirationThreshold = (double)0.125F;
   private String _badlyFormattedPasswordProperty;

   public boolean canSupport(Properties var1) {
      if (!this.requireAuthentication(var1)) {
         return false;
      } else if (PropertyUtil.nativeAuthenticationEnabled(var1)) {
         this.parseNativeSpecification(var1);
         return true;
      } else {
         return false;
      }
   }

   private void parseNativeSpecification(Properties var1) {
      String var2 = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.provider");
      this._authenticateDatabaseOperationsLocally = PropertyUtil.localNativeAuthenticationEnabled(var1);
      int var3 = var2.indexOf(":") + 1;
      int var4 = this._authenticateDatabaseOperationsLocally ? var2.lastIndexOf(":") : var2.length();
      if (var4 > var3) {
         this._credentialsDB = var2.substring(var3, var4);
         if (this._credentialsDB.length() == 0) {
            this._credentialsDB = null;
         }
      }

      this._badlyFormattedPasswordProperty = null;
      String var5 = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.native.passwordLifetimeMillis");
      if (var5 != null) {
         Long var6 = this.parsePasswordLifetime(var5);
         if (var6 != null) {
            this._passwordLifetimeMillis = var6;
         } else {
            this._badlyFormattedPasswordProperty = "derby.authentication.native.passwordLifetimeMillis";
         }
      }

      String var8 = PropertyUtil.getPropertyFromSet(var1, "derby.authentication.native.passwordLifetimeThreshold");
      if (var8 != null) {
         Double var7 = this.parsePasswordThreshold(var8);
         if (var7 != null) {
            this._passwordExpirationThreshold = var7;
         } else {
            this._badlyFormattedPasswordProperty = "derby.authentication.native.passwordLifetimeThreshold";
         }
      }

   }

   private boolean validAuthenticationProvider() throws StandardException {
      boolean var1 = this.getServiceName() == null;
      if (this._credentialsDB != null) {
         if (getMonitor().getCanonicalServiceName(this._credentialsDB) == null) {
            throw StandardException.newException("4251L", new Object[]{this._credentialsDB});
         } else {
            return true;
         }
      } else {
         return var1 ? false : this._authenticateDatabaseOperationsLocally;
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);
      if (!this.validAuthenticationProvider()) {
         throw StandardException.newException("4251H", new Object[0]);
      } else if (this._badlyFormattedPasswordProperty != null) {
         throw StandardException.newException("4251J", new Object[]{this._badlyFormattedPasswordProperty});
      } else {
         try {
            MessageDigest var3 = MessageDigest.getInstance("SHA-1");
            var3.reset();
         } catch (NoSuchAlgorithmException var4) {
            throw Monitor.exceptionStartingModule(var4);
         }

         if (var1 && this.authenticatingInThisService(this.getCanonicalServiceName())) {
            this._creatingCredentialsDB = true;
         } else {
            this._creatingCredentialsDB = false;
         }

         this.setAuthenticationService(this);
      }
   }

   public String getSystemCredentialsDatabaseName() {
      return this._credentialsDB;
   }

   public boolean authenticateUser(String var1, String var2, String var3, Properties var4) throws SQLException {
      try {
         if (var1 == null) {
            return false;
         } else if (var2 == null) {
            return false;
         } else {
            return var3 != null && this.authenticatingInThisDatabase(var3) ? this.authenticateLocally(var1, var2, var3) : this.authenticateRemotely(var1, var2, var3);
         }
      } catch (StandardException var6) {
         throw Util.generateCsSQLException(var6);
      }
   }

   private boolean authenticatingInThisDatabase(String var1) throws StandardException {
      return this.authenticatingInThisService(getMonitor().getCanonicalServiceName(var1));
   }

   private boolean authenticatingInThisService(String var1) throws StandardException {
      return this._authenticateDatabaseOperationsLocally ? true : this.isCredentialsService(var1);
   }

   private boolean isCredentialsService(String var1) throws StandardException {
      String var2 = this.getCanonicalServiceName(this._credentialsDB);
      String var3 = getMonitor().getCanonicalServiceName(var1);
      return var2 == null ? false : var2.equals(var1);
   }

   private String getCanonicalServiceName() throws StandardException {
      return this.getCanonicalServiceName(this.getServiceName());
   }

   private String getCanonicalServiceName(String var1) throws StandardException {
      return getMonitor().getCanonicalServiceName(var1);
   }

   private boolean authenticateRemotely(String var1, String var2, String var3) throws StandardException, SQLWarning {
      if (this._credentialsDB == null) {
         throw StandardException.newException("4251H", new Object[0]);
      } else {
         Object var4 = null;

         try {
            Properties var5 = new Properties();
            var5.setProperty("user", var1);
            var5.setProperty("password", var2);
            String var10 = "jdbc:derby:" + this._credentialsDB;
            Connection var7 = InternalDriver.activeDriver().connect(var10, var5, 0);
            var9 = var7.getWarnings();
            var7.close();
         } catch (SQLException var8) {
            String var6 = var8.getSQLState();
            if ("08004".equals(var6)) {
               return false;
            }

            if ("XJ004.C".startsWith(var6)) {
               throw StandardException.newException("4251I", new Object[]{this._credentialsDB});
            }

            throw this.wrap(var8);
         }

         if (var9 != null) {
            throw var9;
         } else {
            return true;
         }
      }
   }

   private StandardException wrap(Throwable var1) {
      return StandardException.plainWrapException(var1);
   }

   private boolean authenticateLocally(String var1, String var2, String var3) throws StandardException, SQLException {
      var1 = IdUtil.getUserAuthorizationId(var1);
      if (this._creatingCredentialsDB) {
         this._creatingCredentialsDB = false;
         TransactionController var20 = this.getTransaction();
         SystemProcedures.addUser(var1, var2, var20);
         var20.commit();
         return true;
      } else {
         DataDictionary var4 = (DataDictionary)AuthenticationServiceBase.getServiceModule(this, "org.apache.derby.iapi.sql.dictionary.DataDictionary");
         UserDescriptor var5 = var4.getUser(var1);
         if (var5 == null) {
            PasswordHasher var21 = var4.makePasswordHasher(this.getDatabaseProperties());
            var21.hashPasswordIntoString(var1, var2).toCharArray();
            return false;
         } else {
            PasswordHasher var6 = new PasswordHasher(var5.getHashingScheme());
            char[] var7 = var6.hashPasswordIntoString(var1, var2).toCharArray();
            char[] var8 = var5.getAndZeroPassword();

            try {
               if (var7 == null || var8 == null) {
                  boolean var23 = false;
                  return var23;
               }

               if (var7.length != var8.length) {
                  boolean var22 = false;
                  return var22;
               }

               for(int var9 = 0; var9 < var7.length; ++var9) {
                  if (var7[var9] != var8[var9]) {
                     boolean var10 = false;
                     return var10;
                  }
               }
            } finally {
               if (var7 != null) {
                  Arrays.fill(var7, '\u0000');
               }

               if (var8 != null) {
                  Arrays.fill(var8, '\u0000');
               }

            }

            if (this._passwordLifetimeMillis > 0L) {
               long var24 = System.currentTimeMillis() - var5.getLastModified().getTime();
               long var11 = this._passwordLifetimeMillis - var24;
               if (var11 <= 0L) {
                  if (!var4.getAuthorizationDatabaseOwner().equals(var1)) {
                     return false;
                  }

                  var11 = 0L;
               }

               long var13 = (long)((double)this._passwordLifetimeMillis * this._passwordExpirationThreshold);
               if (var11 <= var13) {
                  if (var4.getAuthorizationDatabaseOwner().equals(var1)) {
                     throw SQLWarningFactory.newSQLWarning("01J16", new Object[]{var3});
                  }

                  long var15 = var11 / 86400000L;
                  throw SQLWarningFactory.newSQLWarning("01J15", new Object[]{Long.toString(var15), var3});
               }
            }

            return true;
         }
      }
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static String getServiceName(Object var0) {
      return Monitor.getServiceName(var0);
   }
}
